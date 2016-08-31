package unusedclasses;
import model.Piece;

public interface BoardObserver {
	void beNotifiedOfSelection(boolean success, Piece piece);
	void beNotifiedOfAttack(boolean success, Piece fromPiece, Piece toPiece);
	void beNotifiedOfMove(boolean success, Piece fromPiece, Piece toPiece);
}
