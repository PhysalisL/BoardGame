package model;

import calculation.Vector;

public abstract class RealPiece extends Piece{

	public RealPiece(String name, Moveset travelMoveset, Moveset killMoveset, int health, int attack, Vector position) {
		super(name, travelMoveset, killMoveset, health, attack, position);
	}
	
	public boolean isNull(){
		return false;
	}

}
