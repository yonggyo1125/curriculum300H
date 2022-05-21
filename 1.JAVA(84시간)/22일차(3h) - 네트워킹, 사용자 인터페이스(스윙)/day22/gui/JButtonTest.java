package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JButtonTest extends JFrame {
	JButton jbtn1, jbtn2, jbtn3;
	JButtonTest() {
		super("버튼(JButton) 추가");
		setLayout(new FlowLayout());
		
		jbtn1 = new JButton("입력");
		add(jbtn1);
		jbtn2 = new JButton("추가");
		add(jbtn2);
		jbtn3 = new JButton("삭제");
		add(jbtn3);
		
		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new JButtonTest();
	}
}
