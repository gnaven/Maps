import java.awt.Graphics;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapMain {
	static HashMap<String, Vertex> VerStore = new HashMap<String, Vertex>();
	static HashMap<String, GNode> AdjList = new HashMap<String, GNode>();
	static Graph graph = new Graph();

	static double minlat=  Double.POSITIVE_INFINITY;
	static double maxlat= Double.NEGATIVE_INFINITY;
	static double minlongt=  Double.POSITIVE_INFINITY;
	static double maxlongt= Double.NEGATIVE_INFINITY;
	static ArrayList <Vertex> drawingOrder = new ArrayList<Vertex>();
	static HashMap<String, Edge> RoadList = new HashMap <String,Edge>();
			public static void Read(String file) throws IOException{
	
			String[] split = new String[4];
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String line;
			    
			    while ((line = br.readLine()) != null) {
			       split=line.split("\t");
			       		if (split[0].equals("i")){
			       			double lat=Double.parseDouble(split[2]);
			       			double longt=Double.parseDouble(split[3]);
			       				if (lat < minlat){minlat=lat;}
			       				if (lat>maxlat){maxlat=lat;}
			       				if (longt<minlongt)minlongt=longt;
			       				if (longt>maxlongt)maxlongt=longt;
			       			Vertex v = new Vertex(split[1],Double.parseDouble(split[2]),Double.parseDouble(split[3]));
			       		
			       			VerStore.put(split[1], v);
			       		}else if (split[0].equals("r")){
			       			GNode g1 = new GNode (split[2],VerStore.get(split[2]),split[1]);
			       			GNode g2 = new GNode (split[3],VerStore.get(split[3]),split[1]);
			       			Edge ed = new Edge (split[1],VerStore.get(split[2]),VerStore.get(split[3]) );
			       			graph.InsertAdj(g1,g2);
			       			RoadList.put(split[1], ed);
			       			drawingOrder.add(g1.v);
			       			drawingOrder.add(g2.v);

			       		}

			    }
			}
		}
//	static double xUnit ;
//	static double yUnit;

//		@Override 
//		public void paintComponent(Graphics g){
//			super.paintComponent(g);
//		//double xUnit 
//			
//		}
		
	
		

		
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("To just print the graph, enter 1");
		System.out.println("To print the graph to find the shotest path between two points, enter 2");
		System.out.println("To print the graph and highlight the minimum weight tree, enter 3");
int selection = scan.nextInt();
		
		
		// enter name of the txt file which the preoject will map
		Read("ur.txt");
		
		MainCanvas draw1;
		if (selection ==1){
		// this constructor is for drawing only the map
		draw1 = new MainCanvas(minlat,maxlat,minlongt,maxlongt,drawingOrder,VerStore);
		}else
		
		if (selection==2){
			System.out.println("Enter starting point");
			String point1 = scan.next();
			System.out.println("Enter ending point");
			String point2 =scan.next();
		// the print list takes in the list or the pathway of the name of vertices that will be entered
		ArrayList<String> print = (graph.ShortestPath(point1,point2));
		// this contructor is used for finding the dijkstra
		draw1 = new MainCanvas(minlat,maxlat,minlongt,maxlongt,drawingOrder,VerStore,true,print);
		}else{
			System.out.println("Enter starting point ");
			String x = scan.next();
		ArrayList<String>roadPrint	= graph.MWTRoad(x);	
		draw1 = new MainCanvas(minlat,maxlat,minlongt,maxlongt,drawingOrder,RoadList,roadPrint,true);
		}
		JFrame frame= new JFrame();
		frame.setVisible(true);
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//draw.GetShortestPath("i40","i8");
		
		frame.add(draw1);
		//frame.add(draw);

		
	}

}
