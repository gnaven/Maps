import java.util.LinkedList;

public class GNode {
String id;
Vertex v;
GNode next;
double weight;
boolean known;
GNode path;
String road;
	public GNode getPath() {
	return path;
}
public void setPath(GNode path) {
	this.path = path;
}
	public GNode(String i, Vertex v, String road){
		this.id= i;
		this.v= v;
		this.road=road;
		
	}
	public boolean isKnown() {
		return known;
	}
	public void setKnown(boolean known) {
		this.known = known;
	}
	public GNode(){}
//	public void insertion(GNode node, LinkedList<GNode> list){
//		if (node.next==null){
//			node.next= new GNode (node.id,node.v);
//			//need to get weight incorporated
//		}else {
//			insertion (node.next);
//		}
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Vertex getV() {
		return v;
	}

	public void setV(Vertex v) {
		this.v = v;
	}

	public GNode getNext() {
		return next;
	}

	public void setNext(GNode next) {
		this.next = next;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
