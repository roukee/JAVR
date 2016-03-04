package de.roukee.test.record;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Cmd {
	
	  String text;
	  String word;	  AudioFormat audioFormat;
	  TargetDataLine targetDataLine;
	  File input = new File("voice.wav");
	  File output = new File("voice.flac");
	  
	  String command(String command) throws TransformerException, SAXException, IOException, ParserConfigurationException {
		  command = command.replaceAll("\\s+","+").toLowerCase();;
		  
		  //List of commands following
		  if (command.contains("weather")){
			  
			  String city = "Mannheim"; //Your city
			  String country = "DE"; //Your country
			  
			  WeatherXML xml = new WeatherXML();
			  
			  //Please get your own API-Key at api.openweathermap.org
			  URL API =  new URL("http://api.openweathermap.org/data/2.5/forecast?q="+city+","+country+"&mode=xml&appid=44db6a862fba0b067b1930da0d769e98");
			  URLConnection conn = API.openConnection();

		       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		       DocumentBuilder builder = factory.newDocumentBuilder();
		       Document doc = builder.parse(conn.getInputStream());

		       TransformerFactory tfactory = TransformerFactory.newInstance();
		       Transformer xform = tfactory.newTransformer();

		       xform.transform(new DOMSource(doc), new StreamResult(System.out));
		       
		       File XML = new File("API.xml");
		       xform.transform(new DOMSource(doc), new StreamResult(XML));
		       text = xml.main();
		  }
		  else if (command.contains("time")){
			  //Time formats: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
			  DateFormat df = new SimpleDateFormat("h:mm a");
			  Calendar cal = Calendar.getInstance();
			  Date currentTime = cal.getTime();
			  text = "It's " + df.format(currentTime);
		  }
		  //If input is not recognized
		  else{
			  text = "Unknown command: " + command;
		  }
		 return text; 
	  }
}

