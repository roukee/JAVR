package de.roukee.test.record;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/*AudioRecorder; Copyright 2003, Richard G. Baldwin 
 Updated and edited by Thimo Merke, 2015 */

import javax.swing.JFrame;

import javaFlacEncoder.FLAC_FileEncoder;

@SuppressWarnings("serial")
public class Recordflac extends JFrame{

  AudioFormat audioFormat;
  TargetDataLine targetDataLine;
  File input = new File("voice.wav");
  File output = new File("voice.flac");
 

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
    }
  }

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
    return new AudioFormat(sampleRate, sampleSizeInBits,channels,signed,bigEndian);
  }

//Inner class to capture data from microphone
// and write it to an output audio file.
class CaptureThread extends Thread{
  @Override
public void run(){
    AudioFileFormat.Type fileType = null;
    File audioFile = null;
    fileType = AudioFileFormat.Type.WAVE;
    audioFile = new File("voice.wav");
    AudioInputStream stream = new AudioInputStream(targetDataLine);
    try{
      targetDataLine.open(audioFormat);
      targetDataLine.start();
      AudioSystem.write(stream, fileType, audioFile);
      FLAC_FileEncoder ffe = new FLAC_FileEncoder();
      ffe.encode(input, output);
     }catch (Exception e){
      e.printStackTrace();
    }

  }
}
}