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
						System.out.println(Thread.currentThread().getName() + "���н���,��ǰʱ��:" + new Date());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}, "Thread" + i).start();
		}
		System.out.println("���������н���,��ǰʱ��:" + new Date());
	}
}
