package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JCheckBoxButton extends JFrame {
	JCheckBox jcb1, jcb2, jcb3;
	JRadioButton jrb1, jrb2, jrb3, jrb4, jrb5;
	JPanel jp1, jp2, jp3;
	
	JCheckBoxButton() {
		super("체크박스와 라디오 버튼 만들기");
		
		// 체크 박스 등록 
		jp1 = new JPanel();
		jcb1 = new JCheckBox("음악감상", true);
		jcb2 = new JCheckBox("등산", true);
		jcb3 = new JCheckBox("조깅", false);
		jp1.add(jcb1);
		jp1.add(jcb2);
		jp1.add(jcb3);
		
		add(jp1, "North");
		
		// 결혼여부 라디오 버튼 등록
		jp2 = new JPanel();
		jrb1 = new JRadioButton("결혼", true);
		jrb2 = new JRadioButton("미혼", false);
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(jrb1);
		bg1.add(jrb2);
		
		jp2.add(jrb1);
		jp2.add(jrb2);
		add(jp2, "Center");
		
		// 주거형 라디오 버튼 등록
		jp3 = new JPanel();
		jrb3 = new JRadioButton("자가", true);
		jrb4 = new JRadioButton("전세", false);
		jrb5 = new JRadioButton("월세", false);
		
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(jrb3); 
		bg2.add(jrb4);
		bg2.add(jrb5);
		
		jp3.add(jrb3);
		jp3.add(jrb4);
		jp3.add(jrb5);
		add(jp3, "South");
		
		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new JCheckBoxButton();
	}
}
