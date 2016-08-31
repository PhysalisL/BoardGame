package temppieces;

import calculation.Vector;
import model.DoubleRadialMoveset;
import model.RadialMoveset;
import model.RealPiece;

public class MeleePiece extends RealPiece{

	public MeleePiece(String name, int moveRadius, int health, int attack, Vector position) {
		super(name, new RadialMoveset(moveRadius), new DoubleRadialMoveset(0, 1), health, attack, position);
	}

}
