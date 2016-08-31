package view;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import calculation.Vector;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TiledMap {
	private final String MAP_LAYOUT_TAG = "layer";
	private final String TILE_SHEET_TAG = "image";
	
	private Document XMLDocument;
	private String tiledSheetURL;
	private Image image;
	private double imageWidth;
	private double imageHeight;
	private double cellWidth;
	private double cellHeight;
	private int[][] mapMatrix;
	private int columns;
	private int rows;
	
	/*
	public void setSpriteSheet(String path, double cellWidth, double cellHeight){
		image = new Image(path);
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
	}
	
	public void setTMX(String path){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			XMLDocument = builder.parse(path);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	*/
	public void init(String path){
		//READ FILE
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			XMLDocument = builder.parse(path);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		
		//SET TILED SHEET IMAGE
		NodeList tiledSheetNodeList = XMLDocument.getElementsByTagName(TILE_SHEET_TAG);
		Element imageElement = (Element)tiledSheetNodeList.item(0);
		tiledSheetURL = "C:/Users/Yixiu Liu/Desktop/"+imageElement.getAttribute("source");/////////////////HAS PROBLEMSSSS
		imageWidth = Integer.parseInt(imageElement.getAttribute("width"));
		imageHeight = Integer.parseInt(imageElement.getAttribute("height"));
		
		
		//SET TILED SHEET PROPERTIES
		NodeList tileSheetPropertiesList = XMLDocument.getElementsByTagName("tileset"); 
		Element tiledSheetElement = (Element)tileSheetPropertiesList.item(0);
		cellWidth = Integer.parseInt(tiledSheetElement.getAttribute("tilewidth"));
		cellHeight = Integer.parseInt(tiledSheetElement.getAttribute("tileheight"));
		
		
		//SET MAP MATRIX
		NodeList mapLayoutNodeList = XMLDocument.getElementsByTagName(MAP_LAYOUT_TAG);
		String mapString = mapLayoutNodeList.item(0).getTextContent().trim();
		Element mapLayerElement = ((Element)mapLayoutNodeList.item(0));
		columns = Integer.parseInt(mapLayerElement.getAttribute("width")); 
		rows = Integer.parseInt(mapLayerElement.getAttribute("height"));
		
		mapMatrix = new int[rows][columns];
		String[] rowList = mapString.split("\n");
		for(int i=0; i<rows; i++){
			String[] number = rowList[i].split(",");
			for(int j=0; j<columns; j++){
				mapMatrix[i][j] = Integer.parseInt(number[j]);
			}
		}
	}
	
	public double getImageWidth(){
		return imageWidth;
	}
	
	public double getImageHeight(){
		return imageHeight;
	}
	
	public double getCellWidth(){
		return cellWidth;
	}
	
	public double getCellHeight(){
		return cellHeight;
	}
	
	public int[][]getMapMatrix(){
		return mapMatrix;
	}
	
	public String getImageURL(){
		return tiledSheetURL;
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return columns;
	}
	
	public int portCoordinateToSpriteIndex(int x, int y){
		y = y/(int)cellHeight;
		x = x/(int)cellWidth;
		return (int)(imageWidth/cellWidth)*y + x;
	}
	
	public Vector spriteIndexToPortCoordinates(int tileNumber){
		tileNumber--;//because want first position to be 0 instead of 1, this will also make whole map not skipping the unedited parts
		int y = (int)( tileNumber/ (imageWidth/cellWidth) );
		int x = (int)( tileNumber% (imageWidth/cellWidth) );
		return new Vector(x*(int)cellWidth, y*(int)cellHeight);
	}
	
	public Vector spriteIndexToPortCells(int tileNumber){
		int y = (int)( tileNumber/ (imageWidth/cellWidth) );
		int x = (int)( tileNumber% (imageWidth/cellWidth) );
		return new Vector(x, y);
	}
	
	/*
	public String getSpriteSheetURL(){
		NodeList nodeList = XMLDocument.getElementsByTagName(TILE_SHEET_TAG);
		Element imageElement = (Element)nodeList.item(0);
		
		//THE SOURCE IS THE DESKTOP URL
		return "C:/Users/Yixiu Liu/Desktop/"+imageElement.getAttribute("source");
	}
	
	public int[][] getMapLayout(){
		NodeList nodeList = XMLDocument.getElementsByTagName(MAP_LAYOUT_TAG);
		
		String mapString = nodeList.item(0).getTextContent().trim();
		
		//For creating matrix and ensuring last comma is not counted and parsed as int 
		Element layerElement = ((Element)nodeList.item(0));
		int width = Integer.parseInt(layerElement.getAttribute("width")); 
		int height = Integer.parseInt(layerElement.getAttribute("height"));
		
		int[][]numMatrix = new int[width][height];
		
		String[] rowList = mapString.split("\n");
		for(int i=0; i<height; i++){
			String[] number = rowList[i].split(",");
			for(int j=0; j<width; j++){
				numMatrix[i][j] = Integer.parseInt(number[j]);
			}
		}
		return numMatrix;
	}
	
	public ImageView createImageView(double xPos, double yPos, double portX, double portY){
		ImageView view = new ImageView();
		view.setImage(image);
		view.setViewport(new Rectangle2D(portX, portY, cellWidth, cellHeight));
		view.setX(xPos);
		view.setY(yPos);
		return view;
	}
	*/
}
