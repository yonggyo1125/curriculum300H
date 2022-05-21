package day22.gui;

import java.awt.*;
import javax.swing.*;

public class JFrameEx extends JFrame {
	JFrameEx() {
		super("JFrame");
		setSize(300, 200);
		setLocation(300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		JFrameEx jfe = new JFrameEx();
		jfe.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
