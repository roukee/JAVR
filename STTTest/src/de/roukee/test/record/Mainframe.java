package de.roukee.test.record;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Mainframe extends JFrame {
	record rec = new record();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Mainframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnRecord = new JButton("record");
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rec.captureAudio();
			}
		});
		contentPane.add(btnRecord, BorderLayout.SOUTH);
		
		JButton btnStop = new JButton("stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rec.running = false;
			}
		});
		contentPane.add(btnStop, BorderLayout.NORTH);
		
		JButton btnPlay = new JButton("play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rec.playAudio();
			}
		});
		contentPane.add(btnPlay, BorderLayout.CENTER);
	}

}
