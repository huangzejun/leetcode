package datastructure;

public class DenseGraph {
	int n,m;
	boolean directed;
	boolean[][] g;
	public DenseGraph(int n, boolean directed) {
		// TODO Auto-generated constructor stub
		this.n = n;
		this.m = 0;
		this.directed = directed;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				g[i][j] = false;
			}
		}
	}
	
	public void addEdge(int v, int w){
		assert ( v >= 0 && v < n );
		assert ( w >= 0 && w < n );
		
		if( hasEdge(v, w) )
			return;
		g[v][w] = true;
		
	}
	
	public boolean hasEdge( int v, int w){
		assert ( v >= 0 && v < n );
		assert ( w >= 0 && w < n );
		return g[v][w];
	}
}
