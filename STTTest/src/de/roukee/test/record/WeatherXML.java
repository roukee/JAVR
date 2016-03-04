package de.roukee.test.record;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class WeatherXML {

  public String main() {

	  String result = null;
	  
    try {

	File fXmlFile = new File("API.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	
			
	NodeList sky = doc.getElementsByTagName("symbol");
	NodeList temp = doc.getElementsByTagName("temperature");
			
	System.out.println("----------------------------");

	for (int num = 0; num < 4; num++) {

		Node skyNode = sky.item(num);
		Node tempNode = temp.item(num);
		
		Element skyElement = (Element) skyNode;
		Element tempElement = (Element) tempNode;

		
		if (num == 1) {
			
			String Sky = skyElement.getAttribute("name");
			Sky = Sky.replace("is", "will be");
			System.out.println(Sky);
			Double temperature = Double.parseDouble(tempElement.getAttribute("value"));
			String temp1 = Math.round(temperature) + " degrees celcius";
			System.out.println(temp1);
			
			result = ("The forecast calls for " + temp1 + " and " +  Sky);
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
	return result;
  }

}