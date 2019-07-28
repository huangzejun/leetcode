package ssm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		for(int i=0;i<10;i++){
			System.out.println(list.get(0));
			list.remove(0);
		}
//		long mod = 998244353;
//		Scanner scanner = new Scanner(System.in);
//		long  n = scanner.nextInt();
//		long  m = scanner.nextInt();
//		double t = (double)m/(n*n);
//		long p = mod*m/(n*n);
//        System.out.println((long)(mod - p));
//		int n = scanner.nextInt();
//		long[] x1 = new long[n];
//		long[] y1 = new long[n];
//		long[] x2 = new long[n];
//		long[] y2 = new long[n];
//		for(int i=0;i<n;i++){
//			x1[i] = scanner.nextLong();
//		}
//		for(int i=0;i<n;i++){
//			y1[i] = scanner.nextLong();
//		}
//		for(int i=0;i<n;i++){
//			x2[i] = scanner.nextLong();
//		}
//		for(int i=0;i<n;i++){
//			y2[i] = scanner.nextLong();
//		}
//		int result = 1;
//		for(int t=0;t<n-1;t++){
//			long a1 = x1[t];
//			long b1 = y1[t];
//			long a2 = x2[t];
//			long b2 = y2[t];
//			for(int i=t+1;i<n;i++){
//				if(x1[i]>=a1&&y1[i]>=b1){
//					
//				}
//			}
//		}
		
		//Scanner scanner = new Scanner(System.in);
//		int n = scanner.nextInt();
//		int k  =scanner.nextInt();
//		int result = 0;
//		for(int i=k;i<=n;i++){
//			result += (n-i);
//			//for(int j=1;j<i;j++){
//			//	if(i%j>=k){
//			//		result++;
//			//	}
//			//}
//            int add = (i-2*k)>0?(i-2*k):0;
//            result += add;
//		}
		//System.out.println(result);
	}
}
