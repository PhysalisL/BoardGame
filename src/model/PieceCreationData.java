package model;

public class PieceCreationData {
	
	public enum Symbol{
		EMPTY, M, H, A
	}
	
	//need to refactor: TileMatrix = FULL SCAN, CharArray = Linear scan (cuz char has position properties)
	private Symbol[][]matrix = {
			{Symbol.EMPTY,	Symbol.EMPTY,	Symbol.A,		Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.M,		Symbol.EMPTY,	Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.H,		Symbol.EMPTY, 	Symbol.A,		Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.H,		Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.H,		Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.M	,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.M	,	Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.A,		Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
			{Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY, 	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY,	Symbol.EMPTY},
	};
	
	public PieceCreationData(){
		
	}
	
	public Symbol[][] getMatrix(){
		return matrix;
	}
	
	public int numberOfRows(){
		return matrix.length;
	}
	
	public int numberOfCol(){
		return matrix[0].length;
	}
	
	private class DataBean{
		
		//getName getAtt getHealth getPos getSprite getblah
	}
	
}
