import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
	private int vertexCount, edgeCount;
	 boolean directed; // false for undirected graphs, true for directed
	 private boolean adj[][];
	 public Graph(int numVerticies, boolean isDirected) { // your code here 
		 vertexCount = numVerticies;
		 directed = isDirected;
		 adj = new boolean [numVerticies][numVerticies];
		 edgeCount=0;
		 
		 }
	 public Graph(){}
	 
	 public boolean isDirected() { // your  code here 
		 return directed;
		 }
	 
	 public int vertices() { // return the number of vertices 
		 return vertexCount;
		 }
	
	 public int edges() { // return number of edges 
		 return edgeCount;	
	 }

	 GNode No = new GNode();
	 static HashMap<String, LinkedList<GNode>> AdjListMap = new HashMap<String, LinkedList<GNode>>();
	 public void InsertAdj (GNode newN1, GNode newN2){
		 // calls to see if there is any node for that id
		 LinkedList<GNode> g1= AdjListMap.get(newN1.id);
		 // if there is nothing under that it simply makes a new one
		 //checks if the node is present, and creates one for it if not
		 if (g1== null){
			 LinkedList<GNode> newG = new LinkedList<GNode>();
			 newG.add(newN1);
			 g1=newG;
			 AdjListMap.put(newN1.id,g1);
			 g1= AdjListMap.get(newN1.id);

		 }
		 // then inserts the node connecting to that
		 newN2.setWeight(weight(newN2,newN1));

		 g1.add(newN2);
		 AdjListMap.put(newN1.id, g1);
//		 LinkedList<GNode> g = AdjListMap.get(newN1.id);
//		 for (int i=0; i<g.size();i++){
//			 System.out.println(g.get(i).weight);
//		 }
//		 System.out.println();
		 g1= AdjListMap.get(newN2.id);
		 if (g1==null){
			 g1= new LinkedList<GNode>();
			 g1.add(newN2);
			 AdjListMap.put(newN2.id, g1);
			 g1= AdjListMap.get(newN2.id);
		 }
		    newN1.setWeight(weight(newN2,newN1));
		    g1.add(newN1);
			 AdjListMap.put(newN2.id, g1);

	}
	
	 public double weight (GNode firstNode, GNode secNode){
		 double lat1= Math.toRadians(firstNode.v.Latitude);
		 double lat2= Math.toRadians(secNode.v.Latitude);
		 double latDiff= Math.abs(lat1-lat2);
		 double longt1=Math.toRadians(firstNode.v.Longitude);
		 double longt2=Math.toRadians(secNode.v.Longitude);
		 double theta= Math.abs(longt1-longt2);
			double dist = Math.sin((lat1)) * Math.sin((lat2)) + Math.cos((lat1)) * Math.cos((lat2)) * Math.cos((theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
		
		
		 
		return dist;
		 
	 }
// method to check if all the vertices are visted
	 public boolean Know (HashMap <String, GNode> weight){
		 boolean f = false;
		 for (GNode val: weight.values()){
			 if (val.known==false){
				 f= true;
			 }
		 }
		 return f;
	 }
	 
	// method to find the lowest weight
	 // method also takes in to account that the vertice is also false/ not been visited
	 public GNode smallestWeigh(HashMap <String, GNode> weight){
		 GNode V = null;
		 double  x = Double.POSITIVE_INFINITY;
		  for (GNode val: weight.values()){
			 if (val.weight<=x && val.known==false){
				 V= val;
				 x= val.weight;
			 }
		 }
		  
		return V;
		 
	 }
	 HashMap <String, GNode> WeighStore;
	// pseudo-code from Data Structures and Analysis book, Weiss, Fig 9.31 
	 public void Dijkstra(String s){
		
		 WeighStore= new HashMap<String, GNode>();
		 for (LinkedList<GNode> value :AdjListMap.values()){ //O(n)
			 WeighStore.put(value.get(0).id, value.get(0));
		 }
		
		 for (GNode value :WeighStore.values()){ //O(n)
			 value.weight= Double.POSITIVE_INFINITY;
			 value.known=false;
			 WeighStore.put(value.id, value);
		 }

		 
		GNode start = WeighStore.get(s);
		start.setWeight(0.00);
		WeighStore.put(s,start);

		 System.out.println();
		//O(n^2)
			while (Know(WeighStore)){
				
				GNode vmain = smallestWeigh(WeighStore);
				 
				vmain.known=true;
				WeighStore.put(vmain.id, vmain);
				// getting the linked list which contains the Adjacency list for vertex vmain
					LinkedList<GNode> adj = AdjListMap.get(vmain.id);
				for (int i =1 ; i<adj.size();i ++){
					boolean kn = WeighStore.get(adj.get(i).id).known;
				
					if (kn == false){
						double cvw = weight(vmain,adj.get(i));
					//	System.out.println(cvw+" xx");
	//compares the weight of comparing vertex + comparing vertex and the vertex to check if it is lower than that of the vertex from the previous time 
						if (WeighStore.get(vmain.id).weight+cvw<WeighStore.get(adj.get(i).id).weight){

							GNode bingo = adj.get(i);
							bingo.setWeight(WeighStore.get(vmain.id).weight+cvw);
							bingo.setPath(vmain);
							WeighStore.put(bingo.id, bingo);
						}
					}
				}	
			} 
	 }
	
	 
	 
	 ArrayList<String> Path = new ArrayList<String>();
	 public ArrayList<String> ShortestPath(String x, String y){
			 System.out.println();

		 Dijkstra(x);
			System.out.println("Distance is "+ (WeighStore.get(y).weight+" miles."));
			System.out.println("route:");
		 if (x.equals(y)){
			 System.out.println(x);
			 Path.add(x);
			 return Path;
		 }else {
			return Help(x,y);
		 }
	

	 }
	 String g="";
	 //O(n)
	 public ArrayList<String> Help(String x, String y){
		 
		 if (x.equals(y)){
			 System.out.print(x+", ");
			 Path.add(x);
			 return Path;
		 }
		 Help(x, WeighStore.get(y).path.id);
		 System.out.print(y +", ");
		 Path.add(y);
		 return Path;
		 
	 }
	 
	 HashMap <String, GNode> MWTWeight;
	// using prim's algorithm
		 public void MWT (String s){
			 MWTWeight = new HashMap<String,GNode>();
			 for (LinkedList<GNode> value :AdjListMap.values()){       //O(n)
				 value.get(0).weight=Double.POSITIVE_INFINITY;
				 value.get(0).known=false;
				 MWTWeight.put(value.get(0).id, value.get(0));
			 }
			 
			 GNode start = MWTWeight.get(s);
				start.setWeight(0.00);
				MWTWeight.put(s,start);
				while (Know(MWTWeight)){                       //O(n^2)
					GNode vmain = smallestWeigh(MWTWeight);
					vmain.known=true;
					MWTWeight.put(vmain.id, vmain);
					LinkedList<GNode> adj = AdjListMap.get(vmain.id);
					for (int i= 1; i<adj.size();i++){
						double dist = adj.get(i).weight;
					
						if (dist<MWTWeight.get(adj.get(i).id).weight){
							
							GNode bingo = adj.get(i);
							bingo.setWeight(dist);
							bingo.setPath(vmain);
							MWTWeight.put(bingo.id, bingo);
						}
					}
					
				}
				System.out.println("again "+ MWTWeight.get("ANDERSON").known);


		 }
		 
		 ArrayList <String>Roadid = new ArrayList<String>();
		 public ArrayList<String> MWTRoad(String i){
			 MWT(i);
			 for (GNode node: MWTWeight.values()){                                 //O(n^2)
//				 System.out.println("node "+node.path.id);
				 
				// if (!node.id.equals(i)){
					LinkedList<GNode> r= AdjListMap.get(node.id);
					for (int x=1; x<r.size();x++){
//						System.out.println("this "+x);
//						System.out.println("first "+ node.path.id);
//						System.out.println("second "+ r.get(x).id);
					if (!(node.path==null)){
						if ((node.path.id).equals(r.get(x).id)){
							Roadid.add(r.get(x).road);
						}
					}
					//}
				 }
			 }
			 System.out.println(Roadid);
			 return Roadid;
		 }
	 
	 public boolean connected(int node1, int node2) { //are they connected? 
		 return adj[node1][node2];
		 }
	 //Question 4
	 public AdjList getAdjList(int vertex) { // your code here 
	return new AdjArray(vertex);
}
	 
	 private class AdjArray implements AdjList {
		 private int v; // what vertex we are interested in
		 private int i; // so we can keep track of where we are
		 public AdjArray(int v) {
			 this.v= v;
			 i=-1;
		 
		 }
		 public int next() { 
			 for (++i; i < vertices(); i++){
				 if (connected (v,i)==true){
					 return i;
				 }
			 }
		return -1;
		 }
		  public int begin() {
			  i = -1;
		      return next();
		  }
		  public boolean end() {
		 // if “i” is less than the number of vertices return false
			  if (i < vertices()) {
			        return false;
			      }
			      return true;
		  }
		 }

		
}
