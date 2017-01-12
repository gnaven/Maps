
public class Vertex {
	String id;
	double Latitude,Longitude;
	public Vertex(String id, double Latitude, double Longitude){
		this.id=id;
		this.Latitude= Latitude;
		this.Longitude=Longitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	
	
	
	
}
