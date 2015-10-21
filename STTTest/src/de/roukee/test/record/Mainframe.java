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

public class Mainframe extends JFrame {
	
	recorder2 rec = new recorder2();
	private JPanel contentPane;

	JButton btnRecord = new JButton("record");
	JButton btnStop = new JButton("stop");
	static JTextArea textArea_1 = new JTextArea();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

	public Mainframe() {		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(btnRecord, BorderLayout.SOUTH);
		contentPane.add(btnStop, BorderLayout.NORTH);
		contentPane.add(textArea_1, BorderLayout.CENTER);
		setContentPane(contentPane);
		
		textArea_1.setLineWrap(true);
		textArea_1.setRows(1);
		
		
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("");
				rec.captureAudio();
				try {
				    Thread.sleep(3000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				rec.targetDataLine.stop();
				rec.targetDataLine.close();				
				try {
					Thread.sleep(3000);
					act.takeAction();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rec.targetDataLine.stop();
				rec.targetDataLine.close();
			}
		});
		
	}

}
