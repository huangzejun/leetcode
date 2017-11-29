import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;  
import java.util.List;

public class MapTools {
	
		public static void main(String[] args) {
			final List<Point2D.Double> pList = new ArrayList<>() ;
//			Point2D.Double p = new Point2D.Double() ;
//			p.setLocation(122.44972222222222, 30.033333333333335);
			Point2D.Double point1 = new Point2D.Double(122.44972222222222, 30.033333333333335);
			Point2D.Double point2 = new Point2D.Double(122.45777777777778, 30.044722222222223);
			Point2D.Double point3 = new Point2D.Double(122.4775, 30.044444444444444);
			Point2D.Double point4 = new Point2D.Double(122.4825, 30.033055555555556);
			Point2D.Double point5 = new Point2D.Double(122.4713888888889, 30.040277777777778);
			Point2D.Double point6 = new Point2D.Double(122.47027777777778, 30.033055555555556);
			Point2D.Double point7 = new Point2D.Double(122.4775, 30.029444444444444);
			Point2D.Double point8 = new Point2D.Double(122.4738888888889, 30.025277777777777);
			Point2D.Double point9 = new Point2D.Double(122.46388888888889, 30.03527777777778);
			Point2D.Double point10 = new Point2D.Double(122.4625, 30.040555555555557);
			Point2D.Double point11 = new Point2D.Double(122.45722222222223, 30.0375);
			Point2D.Double point12 = new Point2D.Double(122.4588888888889, 30.031388888888888);
			pList.add(point1) ;
			pList.add(point2) ;
			pList.add(point3) ;
			pList.add(point4) ;
			pList.add(point5) ;
			pList.add(point6) ;
			pList.add(point7) ;
			pList.add(point8) ;
			pList.add(point9) ;
			pList.add(point10) ;
			pList.add(point11) ;
			pList.add(point12) ;
			Point2D.Double point = new Point2D.Double(122.4661111111111, 30.02888888888889);
			GeneralPath path = GeneretePath(pList);
			long start = System.currentTimeMillis();
			for(int i=0;i<100000;i++){
				point = new Point2D.Double(122.4661111111111+0.00000000001*i, 30.02888888888889+0.00000000001*i);
				path.contains(point);
			}
			long end = System.currentTimeMillis();
			System.out.println(end-start);
		}
		
		private static GeneralPath GeneretePath(List<Point2D.Double> polygon){
	           GeneralPath p = new java.awt.geom.GeneralPath();  	  
	           Point2D.Double first = polygon.get(0);  
	           p.moveTo(first.x, first.y);  
	           //System.out.println(polygon);
	           polygon.remove(0); 
	           //System.out.println(polygon);
	           for (Point2D.Double d : polygon) {  
	              p.lineTo(d.x, d.y);  
	           }
	           p.lineTo(first.x, first.y);  	  
	           p.closePath(); 
	           return p;
		}
	       
	    private static boolean checkWithJdkGeneralPath(Point2D.Double point, GeneralPath p) { 	    	
//	           GeneralPath p = new java.awt.geom.GeneralPath();  	  
//	           Point2D.Double first = polygon.get(0);  
//	           p.moveTo(first.x, first.y);  
//	           System.out.println(polygon);
//	           polygon.remove(0); 
//	           System.out.println(polygon);
//	           for (Point2D.Double d : polygon) {  
//	              p.lineTo(d.x, d.y);  
//	           }  
//	           p.lineTo(first.x, first.y);  	  
//	           p.closePath();  	  
	           return p.contains(point);  	           
	    }  
}
