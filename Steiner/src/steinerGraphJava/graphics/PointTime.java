package steinerGraphJava.graphics;

public class PointTime {
	
	private int time;
	private String point;
	
	public PointTime(String point, int time) {
		this.point = point;
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}

	public String getPoint() {
		return point;
	}

}
