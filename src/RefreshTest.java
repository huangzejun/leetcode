import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RefreshTest {
	public static int a = 1;
	public static void main(String[] args){
		Timer timer = new Timer();
		timer.schedule(new test(), 2000, 2000);
		List<Integer> list = new ArrayList<>();

		while(true){
			System.out.println(a);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class test extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		RefreshTest.a ++;
	}
	
}
