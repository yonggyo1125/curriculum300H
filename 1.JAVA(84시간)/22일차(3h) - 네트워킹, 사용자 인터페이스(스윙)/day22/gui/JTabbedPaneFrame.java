package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JTabbedPaneFrame extends JFrame {
	public JTabbedPaneFrame() {
		super("사원 개인정보 조회(JTabbedPane)");
		
		JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
		JPanel jpn1 = new JPanel();
		JPanel jpn2 = new JPanel();
		JPanel jpn3 = new JPanel();
		bTable jt1 = new bTable();
		eTable jt2 = new eTable();
		fTable jt3 = new fTable();
		
		jpn1.add(jt1);
		jpn2.add(jt2);
		jpn3.add(jt3);
		
		jtp.addTab("기본내용", jpn1);
		jtp.addTab("추가내용", jpn2);
		jtp.addTab("보안내용", jpn3);
		
		add(jtp, BorderLayout.CENTER);
		
		setSize(500, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		JTabbedPaneFrame jt = new JTabbedPaneFrame();
		jt.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}


class bTable extends JPanel {
	public bTable() {
		String[] title = {"사번", "성명", "부서"};
		String[][] data = {{"1", "이름1", "총무과"}, {"2", "이승엽", "인사과"}, {"3", "박태환", "전산과"}};
		JTable table = new JTable(data, title);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		JScrollPane js = new JScrollPane(table, v, h);
		add(js);
	}
}

class eTable extends JPanel {
	public eTable() {
		String[] title = {"입사일", "주소", "전화" };
		String[][] data = {{"2001-1-1", "은평구 응암동", "303-5555"}, {"2000-5-30", "마토구 도화동", "5555-6666"}, {"2008-1-1", "구로구 신림동", "777-1234"}};
		JTable table = new JTable(data, title);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		JScrollPane js = new JScrollPane(table, v, h);
		add(js);
	}
}

class fTable extends JPanel {
	public fTable() {
		String[] title = {"호봉", "근무평점"};
		String[][] data = {{"0506", "보통"}, {"0401", "우수"}, {"0701", "미흡"}};
		JTable table = new JTable(data, title);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		JScrollPane js = new JScrollPane(table, v, h);
		add(js);
	}
}