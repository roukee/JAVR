package de.roukee.test.record;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;

public class act {
	
	  AudioFormat audioFormat;
	  TargetDataLine targetDataLine;
	  File input = new File("voice.wav");
	  File output = new File("voice.flac");
	  String word;
	
	  static void takeAction() throws Exception {

		  String command = Mainframe.textArea_1.getText().replaceAll("\\s+","+");
		  
		  if (command.contains("Wetter")){
			  
		  }
		  
	  }
}

