package calculation;

public class Vector{
	private int x;
	private int y;
	
	public static Vector sub(Vector a, Vector b){
		return new Vector(a.x - b.x, a.y-b.y);
	}
	
	public static Vector add(Vector a, Vector b){
		return new Vector(a.x + b.x, a.y+b.y);
	}
	
	public static void reverse(Vector vector){
		vector.x *= -1;
		vector.y *= -1;
	}
	
	public static int distance(Vector a, Vector b){
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	public Vector(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void setVector(int newX, int newY){
		x = newX;
		y = newY;
	}
	
	public void setVector(Vector newVector){
		x = newVector.x;
		y = newVector.y;
	}
	
	public boolean sameSlopeAs(Vector other){
		return x*other.y == y*other.x;
	}
	
	public void sub(Vector other){
		x -= other.x;
		y -= other.y;
	}
	
	public boolean sameX(Vector target){
		return this.x == target.x;
	}
	
	public boolean sameY(Vector target){
		return this.y == target.y;
	}
	
	public boolean equals(Vector other){
		return x==other.x && y==other.y;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	
}