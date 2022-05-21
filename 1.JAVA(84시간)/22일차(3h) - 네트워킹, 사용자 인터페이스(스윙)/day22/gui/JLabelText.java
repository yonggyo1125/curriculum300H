package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JLabelText extends JFrame {
	JTextField tf;
	JTextArea ta;
	JPasswordField pf;
	
	JLabelText() {
		super("JLabelText");
		setLayout(new FlowLayout());
		setSize(300, 200);
		setLocation(300, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel lb1 = new JLabel("이름");
		JLabel lb2 = new JLabel("주소");
		JLabel lb3 = new JLabel("비밀");
		
		tf = new JTextField(20);
		ta = new JTextArea(7, 20);
		pf = new JPasswordField(20);
		
		add(lb1);
		add(tf);
		add(lb2);
		add(ta);
		add(lb3);
		add(pf);
	}
	
	public static void main(String[] args) {
		JLabelText jlt = new JLabelText();
	}
}
