package view;

public abstract class BoardViewComponent {
	protected double tileWidth;
	protected double tileHeight;
	protected double boardWidth;
	protected double boardHeight;
	
	public boolean inBound(double x, double y){
		return x<=tileWidth*boardWidth && y<=tileHeight*boardHeight && x>=0 && y>=0;
	}
	
	public static int mouseXCellIndex(double mouseX, double tileWidth){
		return (int)(mouseX/tileWidth);
	}
	public static int mouseYCellIndex(double mouseY, double tileHeight){
		return (int)(mouseY/tileHeight);
	}
	public static int mouseXCellIndex(double mouseX, double tileWidth, double offset, double scale){
		return (int)((mouseX+offset)/(tileWidth*scale));
	}
	public static int mouseYCellIndex(double mouseY, double tileHeight, double offset, double scale){
		return (int)((mouseY+offset)/(tileHeight*scale));
	}
	
	public void setTileSize(double width, double height){
		this.tileWidth = width;
		this.tileHeight = height;
	}
	public void setBoardSize(double width, double height){
		this.boardWidth = width;
		this.boardHeight = height;
	}
	public double getTileWidth() {
		return tileWidth;
	}
	public double getTileHeight() {
		return tileHeight;
	}
	public double getBoardWidth() {
		return boardWidth;
	}
	public double getBoardHeight() {
		return boardHeight;
	}
}
