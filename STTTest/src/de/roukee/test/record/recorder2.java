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

import javax.net.ssl.HttpsURLConnection;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/*File AudioRecorder02.java
Copyright 2003, Richard G. Baldwin

This program demonstrates the capture of audio
data from a microphone into an audio file.

A GUI appears on the screen containing the
following buttons:
  Capture
  Stop

In addition, five radio buttons appear on the
screen allowing the user to select one of the
following five audio output file formats:

  AIFC
  AIFF
  AU
  SND
  WAVE

When the user clicks the Capture button, input
data from a microphone is captured and saved in
an audio file named junk.xx having the specified
file format.  (xx is the file extension for the
specified file format.  You can easily change the
file name to something other than junk if you
choose to do so.)

Data capture stops and the output file is closed
when the user clicks the Stop button.

It should be possible to play the audio file
using any of a variety of readily available
media players, such as the Windows Media Player.

Not all file types can be created on all systems.
For example, types AIFC and SND produce a "type
not supported" error on my system.

Be sure to release the old file from the media
player before attempting to create a new file
with the same extension.

Tested using SDK 1.4.1 under Win2000
************************************************/
import javax.swing.JFrame;

import javaFlacEncoder.FLAC_FileEncoder;

public class recorder2 extends JFrame{

  AudioFormat audioFormat;
  TargetDataLine targetDataLine;
  String decodedString;

  //This method captures audio input from a
  // microphone and saves it in an audio file.
  void captureAudio(){
    try{
      //Get things set up for capture
      audioFormat = getAudioFormat();
      DataLine.Info dataLineInfo =
                          new DataLine.Info(
                            TargetDataLine.class,
                            audioFormat);
      targetDataLine = (TargetDataLine)
               AudioSystem.getLine(dataLineInfo);

      //Create a thread to capture the microphone
      // data into an audio file and start the
      // thread running.  It will run until the
      // Stop button is clicked.  This method
      // will return after starting the thread.
      new CaptureThread().start();
    }catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }//end catch
  }//end captureAudio method

  //This method creates and returns an
  // AudioFormat object for a given set of format
  // parameters.  If these parameters don't work
  // well for you, try some of the other
  // allowable parameter values, which are shown
  // in comments following the declarations.
  private AudioFormat getAudioFormat(){
    float sampleRate = 8000.0F;
    //8000,11025,16000,22050,44100
    int sampleSizeInBits = 16;
    //8,16
    int channels = 1;
    //1,2
    boolean signed = true;
    //true,false
    boolean bigEndian = false;
    //true,false
    return new AudioFormat(sampleRate,
                           sampleSizeInBits,
                           channels,
                           signed,
                           bigEndian);
  }//end getAudioFormat
//=============================================//

//Inner class to capture data from microphone
// and write it to an output audio file.
class CaptureThread extends Thread{
  public void run(){
    AudioFileFormat.Type fileType = null;
    File audioFile = null;
    fileType = AudioFileFormat.Type.WAVE;
    audioFile = new File("voice.wav");
    try{
      targetDataLine.open(audioFormat);
      targetDataLine.start();
      AudioSystem.write(
            new AudioInputStream(targetDataLine),
            fileType,
            audioFile);
      FLAC_FileEncoder ffe = new FLAC_FileEncoder();
      ffe.encode(new File("voice.wav"), new File("voice.flac"));
      sendPost();
    }catch (Exception e){
      e.printStackTrace();
    }//end catch

  }//end run
}//end inner class CaptureThread
//=============================================//
/**
 * Send post to google
 */
void sendPost() throws Exception {
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
     
     DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
     wr.write(data);
     wr.flush();
     wr.close();
     connection.disconnect();
     
     BufferedReader in = new BufferedReader(
             new InputStreamReader(
             connection.getInputStream()));
              while ((decodedString = in.readLine()) != null) {
              System.out.println(decodedString);
              Mainframe.textArea_1.setText(decodedString);
              }
}
}//end outer class AudioRecorder02.java