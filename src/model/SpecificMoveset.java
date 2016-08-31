package model;

import java.util.ArrayList;

import calculation.Vector;

public class SpecificMoveset implements Moveset{
	private ArrayList<Vector> offsets;
	
	public SpecificMoveset(ArrayList<Vector> offsets){
		this.offsets = offsets;
	}
	
	@Override
	public boolean inRange(Vector from, Vector to) {
		for(int i=0; i<offsets.size(); i++){
			if(offsets.get(i).equals(Vector.sub(from, to))) 
				return true;
		}
		return false;
	}

	@Override
	public ArrayList<Vector> getVectorsInRange(Vector position) {
		ArrayList<Vector> returnList = new ArrayList<>();
		for(int i=0; i<offsets.size(); i++){
			returnList.add(Vector.add(position, offsets.get(i)));
		}
		return returnList;
	}

}
