
public class UniquePath {
	
	static int count = 0;
	public static void main(String[] args){
		System.out.println(uniquePaths(7, 3));
		
	}
	public static int uniquePaths(int m, int n) {
		path(0, 0, m - 1, n - 1);
		return count;
	}

	public static void path(int i, int j, int m, int n) {
		if (i == m && j == n) {
			count++;
			return;
		}
		if (i < m) {
			path(i + 1, j, m, n);
		}
		if (j < n) {
			path(i, j + 1, m, n);
		}
	}
}
