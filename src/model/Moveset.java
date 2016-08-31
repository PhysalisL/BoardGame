package model;

import java.util.ArrayList;

import calculation.Vector;

public interface Moveset {
	public boolean inRange(Vector from, Vector to);
	
	/**
	 * Returns list of vectors in range of this moveset with <code>position</code>
	 * as the origin
	 * 
	 * @param position
	 * origin
	 * @return
	 * list of vectors
	 */
	public ArrayList<Vector> getVectorsInRange(Vector position);
}
