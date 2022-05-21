package day22.gui;

import java.awt.*;
import javax.swing.*;

public class FlowLayoutTest extends JFrame {
	public FlowLayoutTest() {
		super("FlowLayout");
		
		// FlowLayout 매니저 생성
		FlowLayout flayout = new FlowLayout();
		
		// 컨테이너에 설정 
		setLayout(flayout);
		
		add(new JButton("첫번째"));
		add(new JButton("두번째"));
		
		setSize(300, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		FlowLayoutTest flt = new FlowLayoutTest();
		flt.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}