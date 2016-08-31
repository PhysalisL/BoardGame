package model;


import calculation.Vector;

public final class NullPiece extends Piece{

	public NullPiece() {
		super("NULLPIECE", new RadialMoveset(0), new RadialMoveset(0), 0, 0, new Vector(-10, -10));
	}

	public void attack(Piece target) { }

	public boolean isNull() {
		return true;
	}

}
