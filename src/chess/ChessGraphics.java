package chess;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import java.awt.*;
//import javax.swing.*;


public class ChessGraphics  {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("board");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		paint(panel);
		
		frame.add(panel);
		frame.setSize(1000,1000);
		frame.setVisible(true);
		
		/*
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		ImageIcon board= new ImageIcon("images/board.png");
		ImageIcon blackqueen = new ImageIcon("images/BQ.png");
		label.setIcon(board);
		label.add(label2);
		panel.add(label);
		panel.add(label2);
		frame.add(panel);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
		
		*/
	}
	
	public static void paint(JPanel panel) {
		ImageIcon background = new ImageIcon("images/board.png");
	}
	

	
	
}

