package day22.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JPopupMenuTest extends JFrame {
	
	
	JPopupMenuTest() {
		super("팝업메뉴에서 항목 선택");
		
		String[] title = {"사번", "성명", "부서" };
		JRadioButtonMenuItem[] rbm = new JRadioButtonMenuItem[3];
		
		JPopupMenu pmenu = new JPopupMenu();
		ButtonGroup tgroup = new ButtonGroup();
		
		for (int i = 0; i < rbm.length; i++) {
			rbm[i] = new JRadioButtonMenuItem(title[i]);
			pmenu.add(rbm[i]);
			tgroup.add(rbm[i]);
		}
		
		// 마우스 이벤트를 리스터에 등록
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				checkForTriggerEvent(e);
			}
			
			public void mouseReleased(MouseEvent e) {
				checkForTriggerEvent(e);
			}
			
			// 마우스 오른쪽 버튼을 누르거나 해제할 때 발생
			private void checkForTriggerEvent(MouseEvent e) {
				if (e.isPopupTrigger()) 
					pmenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
		setSize(300, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new JPopupMenuTest();
	}
}
