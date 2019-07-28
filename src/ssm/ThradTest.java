package ssm;

import java.util.Date;

public class ThradTest {
	public static void main(String[] args) {
		for(int i=0;i<3;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(2000);
						System.out.println(Thread.currentThread().getName() + "运行结束,当前时间:" + new Date());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}, "Thread" + i).start();
		}
		System.out.println("主函数运行结束,当前时间:" + new Date());
	}
}
