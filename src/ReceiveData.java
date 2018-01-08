

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiveData {
	private String ip = "122.226.9.35";
	private int port = 7908;
	private Object lock;
	private Map<String,Long> mmsi2TimeMap;

	public void setLock(Object lock) {
		this.lock = lock;
	}
	public Object getLock() {
		return lock;
	}
	

	public Map<String, Long> getMmsi2TimeMap() {
		return mmsi2TimeMap;
	}

	public void setMmsi2TimeMap(Map<String, Long> mmsi2TimeMap) {
		this.mmsi2TimeMap = mmsi2TimeMap;
	}

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
	    mongoLogger.setLevel(Level.SEVERE); 
	    
		String s = new File("").getAbsolutePath();
        Object lock=new Object();
		
		ExecutorService executor =null;		
		ReceiveData receiveDataUtil =null;
		
		while (true) {
				
//			if(receiveDataUtil!=null)
//			{
//				executor.shutdownNow();
//				while(!executor.isTerminated()){};
//				
//				System.out.println("停止executor。。。。");
//			}			
			lock=new Object();
			executor = Executors.newFixedThreadPool(5);	
			Map<String,Long> mmsi2TimeMap=new HashMap<String,Long>();
		    receiveDataUtil = new ReceiveData();
			receiveDataUtil.setLock(lock);
			receiveDataUtil.setMmsi2TimeMap(mmsi2TimeMap);
			
			Socket server = receiveDataUtil.getSocketConnection(
					receiveDataUtil.ip, receiveDataUtil.port);
			if (server == null) {
				continue;
			}
			if (server.isConnected()) {
				System.out.println("连接成功");
			} else {
				System.out.println("连接失败");
				continue;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(
					server.getInputStream()));
			PrintWriter out = new PrintWriter(server.getOutputStream());
			receiveDataUtil.sendAuthentication(out);
			Runnable receiveDataRunnable = new ReceiveDataRunnable(in,
					receiveDataUtil, executor, server);
			Thread thread = new Thread(receiveDataRunnable);
			thread.start();
			try {
				thread.join();
				System.err.println("子线程异常");
				if (server.isConnected()) {
					server.close();
					System.err.println("关闭Socket连接");
				}
				in.close();
				out.close();
				executor=null;
				//ctx=null;
			} catch (InterruptedException e) {
				System.err.println("等待出现错误");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取服务器Socket连接
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	public Socket getSocketConnection(String ip, int port) {
		try {
			String[] ipStr = ip.split("\\.");
			byte[] ipBuf = new byte[4];
			for (int i = 0; i < 4; i++) {
				ipBuf[i] = (byte) (Integer.parseInt(ipStr[i]) & 0xff);
			}
			return new Socket(InetAddress.getByAddress(ipBuf), port);
		} catch (UnknownHostException e) {
			System.out.println("unknown Host exception");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("IO exception");
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 向服务器发送认证信息
	 * 
	 * @param out
	 */
	public void sendAuthentication(PrintWriter out) {
		out.println("i data,123\r\n");
		out.flush();
	}

}

/**
 * 接收数据线程
 * 
 * @author Administrator
 *
 */
class ReceiveDataRunnable implements Runnable {
	private BufferedReader in;
	private ReceiveData receiveDataUtil;
	private ExecutorService executor;
	private List<String> yDataList;
	private List<String> pDataList;
	private static int LIST_LENGTH = 500;
	private Socket server;
	
	BlockingQueue<List<String>> yDataQueue = new LinkedBlockingQueue<List<String>>(
			50);
	BlockingQueue<List<String>> pDataQueue = new LinkedBlockingQueue<List<String>>(
			50);

	public ReceiveDataRunnable(BufferedReader in, ReceiveData receiveDataUtil,
			ExecutorService executor, Socket server) {
		super();
		this.in = in;
		this.receiveDataUtil = receiveDataUtil;
		this.executor = executor;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			// 延时2s开启接收
			Thread.sleep(2000);
			yDataList = new ArrayList<String>(LIST_LENGTH);
			pDataList = new ArrayList<String>(LIST_LENGTH);
			
			int count=0;//当读到null到指定次数时就重新启动
			Date t1=new Date();
			
			while (true) {
				String s = in.readLine();
			   // System.out.println("------->s: "+s);
				if (s != null) 
				{
					if (s.startsWith("p") && pDataQueue.size() < 50) 
					{
						pDataList.add(s);
						if (pDataList.size() == LIST_LENGTH) 
						{
							boolean success = pDataQueue.offer(pDataList, 100,
									TimeUnit.MILLISECONDS);
							if (success) 
							{
								Runnable locationParseRunnable = new LocationParseRunnable(
										pDataQueue,receiveDataUtil.getLock(),receiveDataUtil.getMmsi2TimeMap());
								executor.execute(locationParseRunnable);
								pDataList = new ArrayList<String>(LIST_LENGTH);
							}
							else{
								System.out.println(pDataQueue.size()+"**********");
								pDataList = new ArrayList<String>(LIST_LENGTH);
							}
							//Thread.sleep(4000);
						}
					} 
					else if (s.startsWith("y") && yDataQueue.size() < 50) 
					{
						yDataList.add(s);
						if (yDataList.size() == LIST_LENGTH) {
							boolean success = yDataQueue.offer(yDataList, 100,
									TimeUnit.MILLISECONDS);
							if (success) {
								Runnable shipParseRunnable = new ShipParseRunnable(
										yDataQueue,receiveDataUtil.getLock());
								executor.execute(shipParseRunnable);
								yDataList = new ArrayList<String>(LIST_LENGTH);
							} else {
								yDataList = new ArrayList<String>(LIST_LENGTH);
							}
						}
					}
					// 重复登录
					else if (s.startsWith("x")) {
						System.out.println(s);
						break;
					} else if (s.startsWith("i")) {
						System.out.println(s);
					}
				} else {				
					/*synchronized (receiveDataUtil.getCtx())*/{						
					if (server.isConnected()) 
					{
						server.close();
						System.out.println("关闭Socket连接");
						System.out.println("Socket连接是否已关闭" + server.isClosed());
						in.close();																			
					}
					
					executor.shutdownNow();
					Date t11=new Date();
					while(!executor.isTerminated())
					{
						Date t22=new Date();
						long ll=(t22.getTime()-t11.getTime());
						if(ll>120000)
						{
						   throw new Exception();
						}							
					};	
					
					System.out.println("停止executor。。。。");
					executor=Executors.newFixedThreadPool(5);
					
					if(pDataQueue.size()>0)
					{
						pDataQueue.clear();
						System.err.println("清空pDataQueue集合");
					}
					receiveDataUtil.getMmsi2TimeMap().clear();
					
					server = receiveDataUtil.getSocketConnection(
							"122.226.9.35", 7908);
					if (server.isConnected()) {
						System.out.println("连接成功");
					} else {
						System.out.println("连接失败");
						continue;
					}
					in = new BufferedReader(new InputStreamReader(
							server.getInputStream()));
					PrintWriter out = new PrintWriter(server.getOutputStream());
					receiveDataUtil.sendAuthentication(out);
					//receiveDataUtil.getCtx().notify();
					
					System.out.println();
					System.out.println();
					System.gc();
					System.gc();
					System.gc();
					System.gc();
				}
				}
			}
		} catch (Exception ex) {
			System.out.println("ReceiveDataRunnable线程出现异常");
			ex.printStackTrace();
		}
	}

}

class LocationParseRunnable implements Runnable {
	private List<String> pDataList;
	BlockingQueue<List<String>> pDataQueue;
    private Object lock;
    private Map<String, Long> mmsi2TimeMap;
	public LocationParseRunnable(BlockingQueue<List<String>> pDataQueue
			,Object lock,Map<String, Long> mmsi2TimeMap) {
		super();
		this.pDataQueue = pDataQueue;
		this.lock=lock;
	    this.mmsi2TimeMap=mmsi2TimeMap;
	}

	@Override
	public void run() {
		int random = new Random().nextInt(1000);
		try {	
			 pDataList = (List<String>) pDataQueue.take();
				
			
			Date t1=new Date();
			System.out.println("线程名称："+Thread.currentThread().getName()+"	"+random
					+ " 开始存轨迹"	+ " 队列大小:" + pDataQueue.size());
			//System.out.println(pDataList);
			for (String str : pDataList) 
			{
				System.out.println(str);
			}
			pDataList = null;
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

class ShipParseRunnable implements Runnable {
	private List<String> yDataList;
	private Object lock;
	BlockingQueue<List<String>> yDataQueue;

	public ShipParseRunnable(BlockingQueue<List<String>> yDataQueue,
			Object lock) {
		super();
		this.yDataQueue = yDataQueue;
		this.lock=lock;
	}

	@Override
	public void run() {
		int random = new Random().nextInt(1000);
		try {
			yDataList = (List<String>) yDataQueue.take();
			System.out.println(random
					+ " 开始存船只"
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date()) + " 队列大小:" + yDataQueue.size());
			for (String str : yDataList) {
				System.out.println(str);
			}
			yDataList = null;
			System.out.println(random
					+ " 结束存船只"
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.format(new Date()) + " 队列大小:" + yDataQueue.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
