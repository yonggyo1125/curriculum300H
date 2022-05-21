package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JScrollPaneTest extends JFrame {
	JPanel jp;
	
	JScrollPaneTest() {
		super("JScrollPane Test");
		setSize(300, 300);
		setLayout(new BorderLayout());
		setLocation(300, 300);
		setVisible(true);
		
		jp = new JPanel();
		jp.setLayout(new GridLayout(10, 5));
		int cnt = 1;
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 5; j++) {
				jp.add(new JButton("버튼" + cnt));
				cnt++;
			}
		}
		
		// 수직, 수평 스크롤바를 설정하기 위한 상수를 얻음
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		JScrollPane  js = new JScrollPane(jp, v, h);
		add(js, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new JScrollPaneTest();
		
	}
}
