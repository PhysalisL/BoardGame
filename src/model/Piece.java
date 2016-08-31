package model;

import calculation.Vector;

public abstract class Piece {
	private String name;
	private int health;
	private int attack;
	private Moveset killMoveset;
	private Moveset travelMoveset;
	private Vector position;
	private Faction faction;
	
	//walk range, attack range, health, attackPoint, can be changed
	
	public Piece(String name, Moveset travelMoveset, Moveset killMoveset, int health, int attack, Vector position){
		this.name = name;
		this.killMoveset = killMoveset;
		this.travelMoveset = travelMoveset;
		this.health = health;
		this.attack = attack;
		this.position = position;
	}
	
	public String getName(){return name;}
	public Moveset getKillMoveset(){return killMoveset;}
	public Moveset getTravelMoveset(){return travelMoveset;}
	public int getHealth(){return health;}
	public int getAttack(){return attack;}
	public void setHealth(int health){ this.health = health;}
	public void setAttack(int attack){ this.attack = attack;}
	public Vector getPosition(){return position;}
	public void setPosition(Vector position){ this.setPosition(position.getX(), position.getY());}
	public void setPosition(int x, int y){ position.setVector(x, y); }
	public boolean isDead(){ return health <= 0;}
	public boolean isSelf(Piece piece){ return piece == this; }
	public boolean isFriendFaction(Faction otherPieceFaction){ return faction.isFriendlyWith(otherPieceFaction); }
	public abstract boolean isNull();
	
	public void attack(Piece target){
		target.setHealth(target.getHealth() - this.getAttack());
	};
	
	public String getDescription(){
		StringBuilder desc = new StringBuilder();
		desc.append("Name:");
		desc.append(name);
		desc.append(" Health:");
		desc.append(health);
		desc.append(" Attack:");
		desc.append(attack);
		return desc.toString();
	}
}