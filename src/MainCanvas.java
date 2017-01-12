import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class MainCanvas extends JPanel {
	double minlat;
	double maxlat;
	double minlongt;
	double maxlongt;
	ArrayList<Vertex> drawG = new ArrayList<Vertex>();
	static HashMap<String, Vertex> VerStore = new HashMap<String, Vertex>();

	boolean Dijkstra=false;
	ArrayList<String> print ;
	ArrayList<String> Roadprint ;
	boolean MWT = false;
	HashMap<String, Edge> RoadsList;
	public MainCanvas (double minlat, double maxlat, double minlongt, double maxlongt, ArrayList<Vertex> drawG, HashMap<String, Vertex> VerStore){
		this.minlat= minlat;
		this.maxlat= maxlat;
		this.minlongt= minlongt;
		this.maxlongt= maxlongt;
		this.drawG= drawG;
		this.VerStore= VerStore;
		
	}
	public MainCanvas (double minlat, double maxlat, double minlongt, double maxlongt, ArrayList<Vertex> drawG, HashMap<String, Vertex> VerStore, boolean Dij, 	ArrayList<String> print){
		this.minlat= minlat;
		this.maxlat= maxlat;
		this.minlongt= minlongt;
		this.maxlongt= maxlongt;
		this.drawG= drawG;
		this.VerStore= VerStore;
		this.Dijkstra=Dij;
		this.print=print;
	}
	public MainCanvas (double minlat, double maxlat, double minlongt, double maxlongt, ArrayList<Vertex> drawG, HashMap<String, Edge> RoadsList,
			ArrayList<String> Roadprint, boolean MWT){
		this.minlat= minlat;
		this.maxlat= maxlat;
		this.minlongt= minlongt;
		this.maxlongt= maxlongt;
		this.drawG= drawG;
		this.RoadsList=RoadsList;
		this.MWT=MWT;
		this.Roadprint=Roadprint;
	}

	@Override
	public void paintComponent(Graphics g){
		  System.out.println("here");
		double xScale= getWidth()/(maxlongt - minlongt);
	   double  yUnit = getHeight()/(maxlat - minlat);
	   
	   for (int i =0; i < drawG.size(); i+=2){
	   Vertex DrawV1= drawG.get(i);
	   Vertex DrawV2= drawG.get(i+1);
		   
		double lat1= DrawV1.Latitude;
		double lon1= DrawV1.Longitude;
		double lat2= DrawV2.Latitude;
		double lon2= DrawV2.Longitude;
	
		
	      int x1 = (int) ((getHeight()- Math.abs(lat1 - Math.abs(minlat)) * yUnit));
	      int y1 = (int) (((lon1 * xScale)) - minlongt * xScale);
	      int x2 = (int) ((getHeight() - Math.abs(lat2 - Math.abs(minlat)) * yUnit));
	      int y2 = (int) (((lon2 * xScale)) - minlongt * xScale);
	      
	      g.drawLine(y1, x1, y2, x2);
	     
	     
	   }
	
	 
		// stores the path in an array list
		
	if (Dijkstra){	
		g.setColor(Color.red);
	
		for (int i= 0; i<print.size()-1;i++){
			   Vertex DrawV1= VerStore.get(print.get(i));
			   Vertex DrawV2= VerStore.get(print.get(i+1));
				   
				double lat1= DrawV1.Latitude;
				double lon1= DrawV1.Longitude;
				double lat2= DrawV2.Latitude;
				double lon2= DrawV2.Longitude;
			
				
			      int x1 = (int) ((getHeight()- Math.abs(lat1 - Math.abs(minlat)) * yUnit));
			      int y1 = (int) (((lon1 * xScale)) - minlongt * xScale);
			      int x2 = (int) ((getHeight() - Math.abs(lat2 - Math.abs(minlat)) * yUnit));
			      int y2 = (int) (((lon2 * xScale)) - minlongt * xScale);
			      
			      g.drawLine(y1, x1, y2, x2);
	
			
		}
	}
	if (MWT){
		g.setColor(Color.BLUE);
		for (int i= 0; i<Roadprint.size();i++){
				Edge drawR= RoadsList.get(Roadprint.get(i));
			
			   Vertex DrawV1= drawR.v1;
			   Vertex DrawV2= drawR.v2;
				   
				double lat1= DrawV1.Latitude;
				double lon1= DrawV1.Longitude;
				double lat2= DrawV2.Latitude;
				double lon2= DrawV2.Longitude;
			
				
			      int x1 = (int) ((getHeight()- Math.abs(lat1 - Math.abs(minlat)) * yUnit));
			      int y1 = (int) (((lon1 * xScale)) - minlongt * xScale);
			      int x2 = (int) ((getHeight() - Math.abs(lat2 - Math.abs(minlat)) * yUnit));
			      int y2 = (int) (((lon2 * xScale)) - minlongt * xScale);
			      
			      g.drawLine(y1, x1, y2, x2);
	
			
		}
	}

	   System.out.println("here");

	}

	   public void GetShortestPath(String x, String y){
				Graph graph = new Graph();
				double xScale= getWidth()/(maxlongt - minlongt);
			    double  yUnit = getHeight()/(maxlat - minlat);
				// stores the path in an array list
				ArrayList<String> print = (graph.ShortestPath(x,y));
				Graphics g = getGraphics();
				
				//g.setColor(Color.red);
			
				for (int i= 0; i<print.size()-1;i++){
					   Vertex DrawV1= VerStore.get(print.get(i));
					   Vertex DrawV2= VerStore.get(print.get(i+1));
						   
						double lat1= DrawV1.Latitude;
						double lon1= DrawV1.Longitude;
						double lat2= DrawV2.Latitude;
						double lon2= DrawV2.Longitude;
					
						
					      int x1 = (int) ((getHeight()- Math.abs(lat1 - Math.abs(minlat)) * yUnit));
					      int y1 = (int) (((lon1 * xScale)) - minlongt * xScale);
					      int x2 = (int) ((getHeight() - Math.abs(lat2 - Math.abs(minlat)) * yUnit));
					      int y2 = (int) (((lon2 * xScale)) - minlongt * xScale);
					      
					      g.drawLine(y1+1, x1, y2, x2);
					System.out.println("did");
					
				}

			}

}
