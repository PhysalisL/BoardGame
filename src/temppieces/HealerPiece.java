package temppieces;

import calculation.Vector;
import model.DoubleRadialMoveset;
import model.Piece;
import model.RadialMoveset;
import model.RealPiece;

public class HealerPiece extends RealPiece{

	public HealerPiece(String name, int moveRadius, int throwRad, int explodeRad, int health, int attack, Vector position) {
		super(name, new RadialMoveset(moveRadius), new DoubleRadialMoveset(throwRad, explodeRad), health, attack, position);
	}
	
	public void attack(Piece piece){
		piece.setHealth(piece.getAttack() + this.getAttack());
	}
	
}
