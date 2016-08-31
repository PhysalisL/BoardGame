package unusedclasses;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UnitDataBase {
	private final String UNIT_DATA_TAG = "unitdata";
	private final String UNIT_ON_BOARD_TAG = "unit";
	
	private Document XMLDocument;
	
	public void init(String path){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			XMLDocument = builder.parse(path);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		//id="Barbarian" attack="20" health="100" travelradius="2" trojectoryradius="0" explosionradius="1"/>
		
		//SET TILED SHEET IMAGE
		NodeList unitDataList = XMLDocument.getElementsByTagName(UNIT_DATA_TAG);
		
		NodeList boardUnitList = XMLDocument.getElementsByTagName(UNIT_ON_BOARD_TAG);
		
		//LOOP BELOW TO MAKE ALL BOARD PIECES
		Element unit = (Element) boardUnitList.item(0); //<----loop this item
		double xPos = Integer.parseInt(unit.getAttribute("x"));
		double yPos = Integer.parseInt(unit.getAttribute("y"));
		String searchID = unit.getAttribute("searchID");
		Element unitData = null;
		for(int i=0; i<unitDataList.getLength(); i++){
			Element unitFromDataBase = (Element)unitDataList.item(i);
			if(searchID.equals(unitFromDataBase.getAttribute("id"))){
				unitData = unitFromDataBase;
				break;
			}
		}
		double id = Integer.parseInt(unitData.getAttribute("id"));
		double attack = Integer.parseInt(unitData.getAttribute("attack"));
		double health = Integer.parseInt(unitData.getAttribute("health"));
		double travelradius = Integer.parseInt(unitData.getAttribute("travelradius"));
		double trojectoryradius = Integer.parseInt(unitData.getAttribute("trojectoryradius"));
		double explosionradius = Integer.parseInt(unitData.getAttribute("explosionradius"));
	}
}
