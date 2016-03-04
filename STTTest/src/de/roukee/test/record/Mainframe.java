package de.roukee.test.record;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Mainframe extends JFrame {
	
	private JPanel contentPane;

	JButton btnRecord = new JButton("record");
	static JTextArea textArea_1 = new JTextArea();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Mainframe frame = new Mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Recordflac rec = new Recordflac();
	STT stt = new STT();
	Cmd com = new Cmd();
	Speak tts = new Speak();
	static String command;
	String text;
		
	public Mainframe() {		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(btnRecord, BorderLayout.SOUTH);
		contentPane.add(textArea_1, BorderLayout.CENTER);
		setContentPane(contentPane);
		
		textArea_1.setLineWrap(true);
		textArea_1.setRows(1);
		
		
		btnRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				try {
					
					textArea_1.setText(""); //Clear text window
					
					rec.captureAudio(); //Record spoken command
				    Thread.sleep(3000); //Wait 1 second (Record time)
				    rec.targetDataLine.stop(); //Stop recording 1/2
					rec.targetDataLine.close(); //Stop recording 2/2
					
					command = stt.sendPost(); //Convert Speech to Text
					
					Thread.sleep(250); //Wait 250 milliseconds
					
					text = com.command(command); //Analyze command, returns result for TTS
					
					tts.speak(text); //Speak result out loud
					textArea_1.setText(text); //Show result in text area
					
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}

}
