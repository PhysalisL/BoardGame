package model;

import java.util.ArrayList;

import calculation.Vector;

public class Tile {
	private Vector position;
	private int maxOccupants = 2;
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	
	public Tile(Vector position){
		this.position = position;
	}
	
	public Tile(int x, int y){
		this(new Vector(x, y));
	}
	
	public Vector getPosition(){
		return position;
	}
	
	public Piece getPiece(){
		if(pieces.size()<=0)
			return new NullPiece();
		else
			return pieces.get(0);
	}
	
	public void clear(){
		pieces.clear();
	}
	
	public Piece popPiece(){
		if(pieces.size()<=0){
			return new NullPiece();
		}
		else{
			Piece piece = pieces.get(0);
			pieces.remove(piece);
			return piece;
		}
	}
	
	public ArrayList<Piece> getPieceList(){
		return pieces;
	}
	
	public boolean addPiece(Piece piece){
		if(pieces.size()< maxOccupants && !piece.isNull()){
			pieces.add(piece);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean removePiece(Piece piece){
		return pieces.remove(piece);
	}
	
	public boolean isOccupied(){
		return pieces.size()>0;
	}
}
