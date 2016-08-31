package model;

import java.util.ArrayList;

import calculation.Vector;

public class DoubleRadialMoveset implements Moveset{
	private int trojectoryRadius;
	private int explosionRadius;
	
	public DoubleRadialMoveset(int trojectory, int explosion){
		trojectoryRadius = trojectory;
		explosionRadius = explosion;
	}
	
	public boolean inRange(Vector from, Vector to) {
		int distance = Vector.distance(from, to);
		return distance >= trojectoryRadius-explosionRadius && distance <= trojectoryRadius+explosionRadius;
	}

	@Override
	public ArrayList<Vector> getVectorsInRange(Vector position) {
		int outer = trojectoryRadius+explosionRadius;
		int inner = trojectoryRadius-explosionRadius;
		
		ArrayList<Vector> inRangeVectors = new ArrayList<>();
		
		for(int i=0; i<=outer; i++){
			for(int j=0; j<=outer; j++){
				if(i+j <= outer && i+j >= inner){
					if(i+j==0){//prevent overlap in center
						inRangeVectors.add(new Vector(position.getX()+i, position.getY()+j));
					}
					else{
						inRangeVectors.add(new Vector(position.getX()+i, position.getY()+j));
						inRangeVectors.add(new Vector(position.getX()-i, position.getY()-j));
					}
					
					//Prevent overlap
					if(i!=0 && j!=0){
						inRangeVectors.add(new Vector(position.getX()-i, position.getY()+j));
						inRangeVectors.add(new Vector(position.getX()+i, position.getY()-j));
					}
				}
			}
		}
		return inRangeVectors;
		
		/*
		ArrayList<Vector2> outer = position.generateVectorsInRange(position, trojectoryRadius+explosionRadius);
		ArrayList<Vector2> inner = position.generateVectorsInRange(position, trojectoryRadius-explosionRadius);
		outer.removeAll(inner);
		return outer;
		*/
	}

}
