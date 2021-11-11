package map;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Background {
	private JLabel laBackground;
	
	public Background(JFrame app) {
		laBackground = new JLabel(new ImageIcon("images/mappng/map.png"));
		app.setContentPane(laBackground);
		
		JLabel label = new JLabel("X");
		label.setSize(20,20);
		label.setLocation(40,50);
		label.setFont(new Font("dd",Font.BOLD,25));
		label.setForeground(Color.white);
		app.add(label);
		
		JLabel label2 = new JLabel("X");
		label.setSize(20,20);
		label.setLocation(40,93);
		label.setFont(new Font("dd",Font.BOLD,25));
		label.setForeground(Color.white);
		app.add(label2);
		
	}
}
