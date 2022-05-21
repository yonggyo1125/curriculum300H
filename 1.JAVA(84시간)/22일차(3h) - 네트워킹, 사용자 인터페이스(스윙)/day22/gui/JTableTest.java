package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JTableTest extends JFrame {
	JTableTest() {
		super("JTable Test");
		setSize(300, 300);
		setLocation(300, 300);
		setLayout(new BorderLayout());
		String[] title = {"사번", "성명", "부서"};
		String[][] data = {{"1", "고애신", "총무과"}, {"2", "최유신", "인사과"}, {"3", "구동매", "전산과"}};
		JTable table = new JTable(data, title);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		
		JScrollPane js = new JScrollPane(table, v, h);
		add(js, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new JTableTest();
	}
}
