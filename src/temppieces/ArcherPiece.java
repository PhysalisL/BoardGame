package temppieces;


import calculation.Vector;
import model.DoubleRadialMoveset;
import model.RadialMoveset;
import model.RealPiece;

public class ArcherPiece extends RealPiece{
	
	public ArcherPiece(String name, int moveRadius, int throwRad, int explodeRad, int health, int attack, Vector position) {
		super(name, new RadialMoveset(moveRadius), new DoubleRadialMoveset(throwRad, explodeRad), health, attack, position);
	}

}
