package unusedclasses;
import model.Piece;

public interface ObservableBoard {
	void addObverser(BoardObserver observer);
	void notifyOfSelection(boolean success, Piece piece);
	void notifyOfAttack(boolean success, Piece fromPiece, Piece toPiece);
	void notifyOfMove(boolean success, Piece fromPiece, Piece toPiece);
}
