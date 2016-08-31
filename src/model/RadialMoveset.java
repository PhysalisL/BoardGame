package model;

import java.util.ArrayList;

import calculation.Vector;

public class RadialMoveset implements Moveset{
	private int radius;
	
	public RadialMoveset(int radius){
		this.radius = radius;
	}
	
	public void setRadius(int radius){
		this.radius = radius;
	}

	@Override
	public boolean inRange(Vector from, Vector to) {
		return Vector.distance(from, to) <= radius;
	}

	@Override
	public ArrayList<Vector> getVectorsInRange(Vector position) {
		ArrayList<Vector> inRangeVectors = new ArrayList<>();
		
		for(int i=0; i<=radius; i++){
			for(int j=0; j<=radius; j++){
				if(i+j <= radius){
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
		/*
		System.out.printf("Movable positions:");
		for(int i=0; i<inRangeVectors.size(); i++){
			System.out.printf(" (%d, %d) ", inRangeVectors.get(i).getX(), inRangeVectors.get(i).getY());
		}
		System.out.printf("\n");
		*/
		return inRangeVectors;
		//return position.generateVectorsInRange(position, radius);
	}
}
