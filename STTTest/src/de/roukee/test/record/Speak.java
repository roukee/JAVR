package de.roukee.test.record;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import jaco.mp3.player.MP3Player;

public class Speak {
 void speak(String text) throws MalformedURLException, IOException{
	 
	 text = text.replaceAll("\\s+","%20");
	 
	//Please get your own API-Key at api.voicerss.org
	 URLConnection conn = new URL("https://api.voicerss.org/?key=19eeef30be214ff296079bc724ebd9c9&hl=en-us&src=" + text).openConnection();
	    InputStream is = conn.getInputStream();

	    OutputStream outstream = new FileOutputStream(new File("voice.mp3"));
	    byte[] buffer = new byte[4096];
	    int len;
	    while ((len = is.read(buffer)) > 0) {
	        outstream.write(buffer, 0, len);
	    }
	    outstream.close();
	    
	    new MP3Player(new File("voice.mp3")).play();
	    
 }
}

