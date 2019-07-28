import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GetSum {

	public int GetSum2(int a, int b){
		//Integer aInteger = a;
		StringBuilder sBuilder = new StringBuilder();
		System.out.println(Integer.sum(a, b));
		return 0;
	}
	public static void main(String[] args){
//		char[] matrix = {'A','B','C','E','S','F','C','S','A','D','E','E'};
//		char[] str = {'A','B','C','B'};
//		GetSum getSum = new GetSum();
		//int[] prices = {3,3,5,0,0,3,1,4};
		int[] prices = {2,1,4,5,2,9,7};
		GetSum getSum = new GetSum();
		System.out.println(getSum.maxProfit(prices));
	}
	public int maxProfit(int[] prices){
		int[] p = new int[prices.length-1];
		int[] lprofit = new int[prices.length];
		int mm = 0;
		for(int i=0;i<prices.length-1;i++){
          p[i] = prices[i+1]-prices[i];
		}
		for(int i=1;i<lprofit.length;i++){
			lprofit[i] = lprofit[i-1];
			if(p[i-1]<0){
				//lprofit[i] = lprofit[i-1];
			}else{
				int index = i;
				int sum = 0;
				int max = 0;
				int t = i;
				do{
					sum += p[index-1];
					if(max<sum){
						max = sum;
						t = index;
					}
					if(p[index-1]>0){
						if(t-3>0){
							lprofit[i] = (lprofit[t-3] + max)>lprofit[i]?(lprofit[t-3] + max):lprofit[i]; 
						}else{
							lprofit[i] = max>lprofit[i]?max:lprofit[i];
						}
					}
					index--;		
				}while(sum>0&&index>0);
//				if(t-3>0){
//					lprofit[i] = lprofit[t-3] + max; 
//				}else{
//					lprofit[i] = max;
//				}
			}
		}
		return lprofit[lprofit.length-1];
	}
//	public int maxProfit(int[] prices) {
//        int sumprofit = 0;
//        int last = -1;
//        int a = 0;
//        int[] lprofit = new int[prices.length-1];
//        for(int i=0;i<prices.length-1;i++){
//            lprofit[i] = prices[i+1]-prices[i];
//        }
//        for(int i=0;i<lprofit.length;i++){
//            if(lprofit[i]>0&&i==(last+1)){
//                last = i;
//                sumprofit += lprofit[i];
//            }
//            if(lprofit[i]>0&&(i-(last+1))==1){
//                if(last==-1){
//                    last = i;
//                    sumprofit += lprofit[i];
//                }else{
//                    if(lprofit[i]>lprofit[last]){
//                        sumprofit = sumprofit-lprofit[last]+lprofit[i];
//                        last = i;
//                    }
//                }
//            }
//            if(lprofit[i]>0&&(i-(last+1))>1){
//                last = i;
//                sumprofit += lprofit[i];
//            }
//            a += lprofit[i];
//            if(a<0){
//            	a=0;
//            }
//            sumprofit = sumprofit>a?sumprofit:a;
//            
//        }
//        return sumprofit;
//    }
	
}
