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

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;

public class recognize {
	
	  AudioFormat audioFormat;
	  TargetDataLine targetDataLine;
	  File input = new File("voice.wav");
	  File output = new File("voice.flac");
	
	  void sendPost() throws Exception {
			 
			 //Do NOT use my API Key, get your own at ChromiumDev
			 //Change "de-DE" to your language (e.g. "en-US", "fr-FR")
			 
			 String decodedString;
			 String GOOGLE_RECOGNIZER_URL = "http://www.google.com/speech-api/v2/recognize?lang=de-DE&key=AIzaSyBLW59JXuz93_NbV1HNKj-F3oqXTJzkgKE&output=json" ;
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
		             Mainframe.textArea_1.setText(decodedString);
		             }
		     }
}
