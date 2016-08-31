package factory;


import calculation.Vector;
import model.Piece;
import temppieces.ArcherPiece;
import temppieces.HealerPiece;
import temppieces.MeleePiece;

public class PieceFactory {
	/*
	public Piece createPiece(String unitType, String name, int attack, int health, int travelRad, ArrayList<Vector2> killOffsets){
		switch(unitType.toLowerCase()){
		
		case "healer": 
			return new HealerPiece(name, travelRad, killOffsets.get(0).getX(), attack, health);
		case "melee": 
			return new MeleePiece(name, travelRad, attack, health);
		case "archer": 
			return new ArcherPiece(name, travelRad, killOffsets, attack, health);
		default: 
			return null;
		
		}
	}
	*/
	
	public Piece createPiece(String unitType, String name, int travelRad, int trojectoryRad, int explosionRad, int health, int attack, Vector position){
		switch(unitType.toLowerCase()){
		
		case "healer": 
			return new HealerPiece(name, travelRad, trojectoryRad, explosionRad, health, attack, position);
		case "melee": 
			return new MeleePiece(name, travelRad, health, attack, position);
		case "archer": 
			return new ArcherPiece(name, travelRad, trojectoryRad, explosionRad, health, attack, position);
		default: 
			return null;
		
		}
	}
}
