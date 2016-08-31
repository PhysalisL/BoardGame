package model;

import java.util.ArrayList;

import Utils.IOUtil;
import calculation.Vector;
import factory.PieceFactory;

public class GameBoard implements Model{
	
	private Tile selectedTile;
	private ArrayList<Piece> activePieces = new ArrayList<Piece>();
	private ArrayList<Piece> deadPieces = new ArrayList<Piece>();
	private Tile[][] tiledBoard;
	
	//inRange?
	//isBlocked?
	//use hashmap instead of Tiles?
	
	public GameBoard(int width, int height){
		tiledBoard = new Tile[height][width];
		for(int i=0; i<tiledBoard.length; i++){
			for(int j=0; j<tiledBoard[i].length; j++){
				tiledBoard[i][j] = new Tile(j, i);
			}
		}
	}
	
	/**
	 * temp constructor
	 */
	public GameBoard(PieceCreationData data){
		tiledBoard = new Tile[data.getMatrix().length][data.getMatrix()[0].length];
		populateBoard(data);
	}
	
	public void addPiece(Piece piece, int x, int y){
		if(inBound(x, y)){
			tiledBoard[y][x].addPiece(piece);
		}
	}
	
	public Tile[][] getTiles(){
		return tiledBoard;
	}
	
	public ArrayList<Piece> getActivePieces(){
		return activePieces;
	}
	
	public ArrayList<Piece> getDeadPieces(){
		return deadPieces;
	}
	/**
	 * Tile and Piece share position object
	 * @param data
	 */
	public void populateBoard(PieceCreationData data){
		PieceFactory factory = new PieceFactory();
		for(int i=0; i < data.getMatrix().length; i++){
			for(int j=0; j < data.getMatrix()[i].length; j++){
				//Create Tile
				tiledBoard[i][j] = new Tile(new Vector(j, i));
				//Create Piece
				Piece generatedPiece;
				if(i < data.getMatrix().length && j < data.getMatrix()[0].length){
					switch(data.getMatrix()[i][j]){
					case M: generatedPiece = factory.createPiece("melee", "M", 5, 0, 1, 10, 4, new Vector(j, i)); break;
					case H: generatedPiece = factory.createPiece("healer", "H", 1, 0, 3, 10, 5, new Vector(j, i)); break;
					case A: generatedPiece = factory.createPiece("archer", "A", 2, 2, 0, 10, 3, new Vector(j, i)); break;
					default: generatedPiece = new NullPiece(); break;
					}
					if(!generatedPiece.isNull()){
						tiledBoard[i][j].addPiece(generatedPiece);
						activePieces.add(generatedPiece);
					}
				}
				System.out.print(" \n");
			}
		}
		selectedTile = tiledBoard[0][0];
	}
	
	public void addPiece(String name,int moveRadius, int throwRad, int explodeRad, int health, int attack, Vector position){
		//Piece piece = new Piece();
	}
	
	/**
	 * testing stage
	 * @param requestedPiece
	 * @return
	 */
	public ArrayList<Tile> getMovableTiles(Piece requestedPiece){
		ArrayList<Tile> movableTiles = new ArrayList<>();
		if(!requestedPiece.isNull()){
			ArrayList<Vector> movesetPositions = requestedPiece.getTravelMoveset().getVectorsInRange(requestedPiece.getPosition());
			for(int i=0; i<movesetPositions.size(); i++){
				Vector p = movesetPositions.get(i);
				if(inBound(p)){
					if(!tileAt(p).isOccupied()){
						movableTiles.add(tileAt(p));
					}
				}
			}
		}
		return movableTiles;
	}
	
	/**
	 * Get a list of movable vector positions that are within bound and doesn't have a <code>piece</code> occupied
	 * @param x
	 * @param y
	 * @return
	 */
	public ArrayList<Vector> getMovableTiles(int x, int y){
		ArrayList<Vector> movablePositions = new ArrayList<>();
		Piece requestedPiece = pieceAt(x, y);
		if(!requestedPiece.isNull()){
			ArrayList<Vector> movesetPositions = requestedPiece.getTravelMoveset().getVectorsInRange(requestedPiece.getPosition());
			for(int i=0; i<movesetPositions.size(); i++){
				Vector p = movesetPositions.get(i);
				if(inBound(p)){
					if(!tileAt(p).isOccupied()){//////NEED TO CHECK IF PIECE IS ITSELF!!!!!!!!!!!!!!!!
						movablePositions.add(p);
					}
				}
			}
		}
		return movablePositions;
	}
	
	/**
	 * Get a list of attackable vector positions that are within bound
	 * @param x
	 * @param y
	 * @return
	 */
	public ArrayList<Vector> getAttackableTiles(int x, int y){
		ArrayList<Vector> attackablePositions = new ArrayList<>();
		Piece requestedPiece = pieceAt(x, y);
		if(!requestedPiece.isNull()){
			ArrayList<Vector> movesetPositions = requestedPiece.getKillMoveset().getVectorsInRange(requestedPiece.getPosition());
			for(int i=0; i<movesetPositions.size(); i++){
				Vector p = movesetPositions.get(i);
				if(inBound(p)){/////////////////////NEED TO CHECK PIECE FACTION!!!!!!!!!!
					attackablePositions.add(p);
				}
			}
		}
		return attackablePositions;
	}
	
	public boolean inBound(Vector position){
		return inBound(position.getX(), position.getY());
	}
	
	private boolean inBound(int x, int y){
		return x>=0 && y>=0 && x<tiledBoard[0].length && y<tiledBoard.length;
	}
	
	public boolean inMoveRange(Piece src, Tile srcTile, Tile destTile){
		return src.getTravelMoveset().inRange(srcTile.getPosition(), destTile.getPosition());
	}
	
	public boolean inAttackRange(Piece src, Tile srcTile, Tile destTile){
		return src.getKillMoveset().inRange(srcTile.getPosition(), destTile.getPosition());
	}
	
	public boolean inAttackRange(Tile destTile){
		return selectedTile.getPiece().getKillMoveset().inRange(selectedTile.getPosition(), destTile.getPosition());
	}
	
	public boolean inMoveRange(Tile destTile){
		return selectedTile.getPiece().getTravelMoveset().inRange(selectedTile.getPosition(), destTile.getPosition());
	}
	
	public void selectTile(Tile tile){
		selectedTile = tile;
	}
	
	public void selectTile(int x, int y){
		if(x>=0 && y>=0 && x<tiledBoard[0].length && y<tiledBoard.length)
			selectedTile = tiledBoard[y][x];
	}
	
	public void selectTile(Vector position){
		if(inBound(position))
			selectedTile = tiledBoard[position.getY()][position.getX()];
	}
	
	public Tile tileAt(int x, int y){
			return tiledBoard[y][x];
	}
	public Tile tileAt(Vector position){return tileAt(position.getX(), position.getY());}
	
	public Piece pieceAt(int x, int y){
		if(inBound(x, y))
			return tiledBoard[y][x].getPiece();
		else
			return new NullPiece();
	}
	public Piece pieceAt(Vector position){return pieceAt(position.getX(), position.getY());}
	public Piece pieceAt(Tile tile){return tile.getPiece();}
	
	public Vector getPointerPosition(){
		return selectedTile.getPosition();
	}
	
	public void houseKeepingRemove(Piece piece){
		deadPieces.add(piece);
		activePieces.remove(piece);
		tileAt(piece.getPosition()).clear();
	}
	
	public boolean attack(Vector position){
		return attack(position.getX(), position.getY());
	}
	
	public boolean attack(int x, int y) {
		if(!inBound(x, y)) {
			return false;
		}
		Tile targetTile = tileAt(x, y);
		Piece targetPiece = targetTile.getPiece();
		Boolean succeeded = false;
		if(targetTile.isOccupied() && inAttackRange(selectedTile.getPiece(), selectedTile, targetTile)){
			selectedTile.getPiece().attack(targetPiece);
			if(targetPiece.isDead()){////////////////////////////////////////////////////////////////////////////
				houseKeepingRemove(targetPiece);
			}
			succeeded = true;
		}
		else{
			succeeded = false;
		}
		return succeeded;
	}
	
	/**
	 * Removes the elements in the deadPieces, list from the board and the alivePieces list.
	 * Also returns a list of positions of the removed pieces.
	 * @return
	 */
	public ArrayList<Vector> removeDeadPieces(){
		ArrayList<Vector> deadPositions = new ArrayList<>();
		
		for(int i=0; i<deadPieces.size(); i++){
			activePieces.remove(deadPieces.get(i));
			tileAt(deadPieces.get(i).getPosition()).clear();
			deadPositions.add(deadPieces.get(i).getPosition());
		};
		deadPieces.clear();
		
		return deadPositions;
	}
	
	public boolean move(Vector position){
		return move(position.getX(), position.getY());
	}
	
	public boolean move(int x, int y){
		Tile targetTile = tileAt(x, y);
		Boolean succeeded = false;
		if(!targetTile.isOccupied() && inMoveRange(selectedTile.getPiece(), selectedTile, targetTile)){
			Piece piece = selectedTile.getPiece();
			targetTile.addPiece(piece);
			piece.setPosition(targetTile.getPosition());
			selectedTile.clear();
			selectedTile = targetTile;
			succeeded = true;
		}
		else{
			succeeded = false;
		}
		return succeeded;
	}
	
	
	//Debug===========================================================================
	
	public void debugMove(){
		move(promptForVector());
	}
	
	public void debugAttack(){
		attack(promptForVector());
	}
	
	public void debugSelctTile(){
		selectTile(promptForVector());
	}
	
	public void printMenu(){
		System.out.printf("Q. Quit\nS. Select Tile\nP. Print Info\nM. Move\nA. Attack\n");
	}
	
	public void printPieceInfo(){
		System.out.println(selectedTile.getPiece().getDescription());
	}
	
	private Vector promptForVector(){
		IOUtil sc = new IOUtil();
		int x;
		int y;
		sc.print("Enter x: ");
		x = sc.readInt(-100);
		sc.print("Enter y: ");
		y = sc.readInt(-200);
		sc.close();
		return new Vector(x, y);
	}
	
	public String toString(){
		StringBuilder string = new StringBuilder();
		string.append(" ");
		for(int i=0; i < tiledBoard[0].length; i++){
			string.append("|");
			string.append(i);
			string.append("|");
		}
		string.append("\n");
		for(int i=0; i < tiledBoard.length; i++){
			string.append(i);
			for(int j=0; j < tiledBoard[i].length; j++){
				string.append("|");
				if(tiledBoard[i][j].isOccupied()) string.append(tiledBoard[i][j].getPiece().getName());
				else string.append("_");
				string.append("|");
			}
			string.append("\n");
		}
		return string.toString();
	}
	

	/*
	public static void main(String[]args){
		GameBoard board = new GameBoard(3, 5);
		board.populateBoard(new PieceCreationData());
		IOUtil sc = new IOUtil();
		Boolean keepGoing = true;
		
		while(keepGoing){
			System.out.println(board.toString());
			board.printMenu();
			System.out.printf("Select an option: ");
			String choice = sc.readString().toUpperCase();
			switch(choice){
			case "Q" : 
				keepGoing = false;
				break;
			case "S" : 
				board.debugSelctTile();
				break;
			case "P" : 
				board.printPieceInfo();
				break;
			case "M" : 
				board.debugMove();
				break;
			case "A" : 
				board.debugAttack();
				break;
			default: System.out.println("Please Enter a valid choice");
			}
			System.out.println();
		}
		sc.close();
	}
	*/
	
}
