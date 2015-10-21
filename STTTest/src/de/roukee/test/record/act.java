package de.roukee.test.record;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class act {
	
	  AudioFormat audioFormat;
	  TargetDataLine targetDataLine;
	  File input = new File("voice.wav");
	  File output = new File("voice.flac");
	  String word;
	
	  static void takeAction() throws Exception {
			 
			 //Do NOT use my API Key, get your own at ChromiumDev
			 //Change "de-DE" to your language (e.g. "en-US", "fr-FR")
			 
		     String command = Mainframe.textArea_1.getText().replaceAll("\\s+","+");
			 String decodedString;
			 String ALPHA_URL = "http://api.wolframalpha.com/v1/query?input=" + command + "&appid=XVRT2J-9QRPRGTY7Y";
		     String request = ALPHA_URL;
		     URL url = new URL(request);
		     HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		     
		     connection.setDoOutput(true);
		     connection.setDoInput(true);
		     connection.setInstanceFollowRedirects(false);
		     connection.setRequestMethod("POST");
		     connection.setConnectTimeout(60000);
		     connection.setUseCaches(false);
		     
		     DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		     
		     wr.flush();
		     wr.close();
		     
		     connection.disconnect();
		     
		     BufferedReader in = new BufferedReader(
		             new InputStreamReader(
		             connection.getInputStream()));		     
		             while ((decodedString = in.readLine()) != null) { System.out.println(decodedString);		             
		             Mainframe.textArea_1.setText(decodedString);
		             }
		     }
}

