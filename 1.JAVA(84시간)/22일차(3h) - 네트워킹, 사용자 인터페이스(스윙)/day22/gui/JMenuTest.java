package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JMenuTest extends JFrame {
	JMenuTest() {
		super("JMenuTest");
		setSize(300, 300);
		setLocation(300, 300);
		
		JMenuBar jmb = new JMenuBar();
		JMenu jmu1 = new JMenu("파일");
		JMenu jmu2 = new JMenu("편집");
		JMenu jmu3 = new JMenu("보기");
		
		JMenuItem jmi1 = new JMenuItem("새로만들기");
		JMenuItem jmi2 = new JMenuItem("열기");
		JMenuItem jmi3 = new JMenuItem("저장");
		
		jmu1.add(jmi1);
		jmu1.add(jmi2);
		jmu1.add(jmi3);
		
		JMenuItem jmi4 = new JMenuItem("잘라내기");
		JMenuItem jmi5 = new JMenuItem("복사");
		JMenuItem jmi6 = new JMenuItem("붙여넣기");
		
		jmu2.add(jmi4);
		jmu2.add(jmi5);
		jmu2.add(jmi6);
		
		JMenuItem jmi7 = new JMenuItem("도구모음");
		JMenuItem jmi8 = new JMenuItem("상태표시줄");
		
		jmu3.add(jmi7);
		jmu3.add(jmi8);
		
		jmb.add(jmu1);
		jmb.add(jmu2);
		jmb.add(jmu3);
		
		setJMenuBar(jmb);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new JMenuTest();
	}
}
