package de.roukee.test.record;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;

public class STT {
	
	  AudioFormat audioFormat;
	  TargetDataLine targetDataLine;
	  File input = new File("voice.wav");
	  File output = new File("voice.flac");
	  String word;
	
	  String sendPost() throws Exception {
			 
			 //Do NOT use my API Key, get your own at https://www.chromium.org/developers/how-tos/api-keys
			 //Change "en-US" to your language (e.g. "de-DE", "fr-FR")
			 
			 String command = null;
		     String decodedString;
			 String GOOGLE_RECOGNIZER_URL = "http://www.google.com/speech-api/v2/recognize?lang=en-US&key=AIzaSyBLW59JXuz93_NbV1HNKj-F3oqXTJzkgKE&output=json" ;
			 Path path = Paths.get("voice.flac");
		     byte[] data = Files.readAllBytes(path);
		     String request = GOOGLE_RECOGNIZER_URL;
		     URL url = new URL(request);
		     HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		     
		     connection.setDoOutput(true);
		     connection.setDoInput(true);
		     connection.setInstanceFollowRedirects(false);
		     connection.setRequestMethod("POST");
		     connection.setRequestProperty("Content-Type", "audio/x-flac; rate=8000");
		     connection.setRequestProperty("User-Agent", "speech2text");
		     connection.setConnectTimeout(60000);
		     connection.setUseCaches (false);
		     
		     DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		     
		     wr.write(data);
		     wr.flush();
		     wr.close();
		     
		     connection.disconnect();
		     
		     BufferedReader in = new BufferedReader(
		             new InputStreamReader(
		             connection.getInputStream()));		     
		             while ((decodedString = in.readLine()) != null) { System.out.println(decodedString);
		             
		             JSONParser parser = new JSONParser();
		             try{
		                Object obj = parser.parse(decodedString);
		                JSONObject object = (JSONObject)obj;
		                JSONArray array = (JSONArray) object.get("result");
		                JSONObject obj1 = (JSONObject)array.get(0);
		                JSONArray arr1 = (JSONArray) obj1.get("alternative");
		                JSONObject obj2 = (JSONObject)arr1.get(0);
		                
		                command = ((String) obj2.get("transcript"));

		             }catch(IndexOutOfBoundsException pe){
		       		
		                
		             }
		             
		             }
					return command;
		     }
}
