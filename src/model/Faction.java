package model;

public enum Faction {
	RED(0), BLUE(1);
	
	private boolean[][] friend = {
			//RED, BLUE
			{true, false},//RED,
			{false, true}//BLUE
			
	};
	
	private int index;
	
	private Faction(int index){
		this.index = index;
	}
	
	public boolean isSame(Faction other){
		return this == other;
	}
	
	public boolean isFriendlyWith(Faction other){
		return friend[this.index][other.index];
	}
}
