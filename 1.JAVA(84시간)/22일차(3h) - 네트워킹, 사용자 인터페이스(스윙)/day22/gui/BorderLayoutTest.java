package day22.gui;

import java.awt.*;
import javax.swing.*;

public class BorderLayoutTest extends JFrame {
	public BorderLayoutTest() {
		super("Borderlayout");
		
		add("North", new Button("North"));
		add(BorderLayout.EAST, new Button("EAST"));
		add(new Button("Center"), BorderLayout.CENTER);
		add(new Button("South"), BorderLayout.SOUTH);
		add(new Button("West"), BorderLayout.WEST);
		
		setSize(300, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		BorderLayoutTest blt = new BorderLayoutTest();
		blt.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}