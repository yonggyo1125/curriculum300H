# 사용자 인터페이스

## 이벤트와 이벤트 처리의 개념 
- 이벤트는 사용자의 입력, 키보드나 마우스 등의 장치나 소프트웨어적으로 발생하는 모든 사건을 의미한다.
- 이벤트가 발생하면 발생된 이벤트에 반응하여 필요한 것을 처리하는데 이를 <b>이벤트 핸들러(event handler)</b>이다.
- 자바에서 이벤트 핸들어는 메소드로 구현되며, 이벤트의 동작에 등답하는 방식으로 처리되는 프로그램을 이벤트 지향(event-driven) 프로그램이라 한다. 
- 이벤트 지향 프로그램은 무한 루프를 돌면서 사용자의 이벤트가 발생하기를 기다린다. 사용자의 이벤트가 밸생하면 이벤트를 처리하고 다시 무한 루프로 대기한다.

### 이벤트의 종류


#### AWT 이벤트 관련 클래스
- 자바에서 각 컴포넌트들은 다양한 종류의 이벤트를 발생시키며, 이벤트는 객체로 처리한다.
- 스윙은 AWT에서 제공하는 이벤트(java.awt.event 패키지로 제공)를 처리할 수 있을 뿐 아니라 스윙 컴포넌트에서 발생하는 이벤트를 따로 정의해 두었다.
- 스윙의 이벤트 클래스는 javax.swing.event 패키지에서 제공한다.

![이벤트1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/23%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%9D%B4%EB%B2%A4%ED%8A%B81.png)


#### 컴포넌트별 발생 이벤트

![이벤트2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/23%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%9D%B4%EB%B2%A4%ED%8A%B82.png)

- 자바에서 대부분의 이벤트는 사용자가 GUI화면에서 마우스나 키보드를 조작함으로써 발생한다.
- 이벤트의 발생은 마우스나 키보드를 조작함으로서 이루어지지만, 실제 이벤트 객체를 생성시키는 역할은 JVM에 의해서 이루어진다. 즉, JVM은 사용자의 조작을 인지하고 조작과 관련된 이벤트 객체를 이벤트 클래스로부터 자동 생성한다.

#### 이벤트의 종류

<table>
<tr>
	<thead>
		<tr>
			<th>구분</th>
			<th>이벤트</th>
			<th>설명</th>
		</tr>
	</thead>
	<tbody>
	<tr>
		<td rowspan='10'>AWT</td>
		<td>ActionEvent</td>
		<td>버튼이 눌려지거나 리스트나 메뉴의 항목이 선택되었을 경우에 발생</td>
	</tr>
	<tr>
		<td>AdjustmentEvent</td>
		<td>스크롤바를 움직였을 떄 발생</td>
	</tr>
	<tr>
		<td>ComponentEvent</td>
		<td>컴포넌트를 선택하거나 조절했을 때 발생</td>
	</tr>
	<tr>
		<td>ContainerEvent</td>
		<td>컨테이너에 컴포넌트가 추가, 제거되었을 때 발생</td>
	</tr>
	<tr>
		<td>FocusEvent</td>
		<td>키보드 입력을 받아들일 수 있는 초점(focus)을 획득하거나 읽었을 때 발생</td>
	</tr>
	<tr>
		<td>ItemEvent</td>
		<td>체크박스, 리스트, 메뉴의 항목이 선택, 해제되었을 때 발생</td>
	</tr>
	<tr>
		<td>KeyEvent</td>
		<td>키보드로 부터 입력이 일어났을 때 발생</td>
	</tr>
	<tr>
		<td>MouseEvent</td>
		<td>마우스의 행위가 일어났을 떄 발생</td>
	</tr>
	<tr>
		<td>TextEvent</td>
		<td>텍스트 필드나 에이러어 값에 입력될 때 발생</td>
	</tr>
	<tr>
		<td>WindowEvent</td>
		<td>윈도우가 활성화, 비활성화, 아이콘화, 아이콘 복구, open, close 등에서 발생</td>
	</tr>
	<tr>
		<td rowspan='13'>스윙</td>
		<td>CaretEvent</td>
		<td>컴포넌트에 있는 텍스트의 캐럿이 변할 경우에 발생</td>
	</tr>
	<tr>
		<td>ChangeEvent</td>
		<td>컴포넌트의 상태에 변화가 생길 경우 발생</td>
	</tr>
	<tr>
		<td>HyperlinkEvent</td>
		<td>하이퍼링크와 관련하여 무엇인가가 발생할 경우 발생</td>
	</tr>
	<tr>
		<td>ListSelectionEvent</td>
		<td>리스트의 선택과 관련해서 변화가 생길 경우 발생</td>
	</tr>
	<tr>
		<td>MenuDragMouseEvent</td>
		<td>마우스가 드래그 되는 상태에서 메뉴구성요소가 마우스 이벤트를 받을 때 발생</td>
	</tr>
	<tr>
		<td>MenuEvent</td>
		<td>메뉴가 선택, 축소될 때 발생</td>
	</tr>
	<tr>
		<td>MenuKeyEvent</td>
		<td>메뉴 트리에서 메뉴 구성요소가 키 이벤트를 받을 때 발생</td>
	</tr>
	<tr>
		<td>PopupMenuEvent</td>
		<td>팝업 메뉴 안에 있는 컴포넌트의 변화에 발생</td>
	</tr>
	<tr>
		<td>TableColumnModelEvent</td>
		<td>테이블 칼럼에 변화가 생길 때 발생</td>
	</tr>
	<tr>
		<td>TableModelEvent</td>
		<td>테이블 모델에 변화가 생길 때 발생</td>
	</tr>
	<tr>
		<td>TreeExpansionEvent</td>
		<td>트리에서 한 개의 패스를 확인할 때 발생</td>
	</tr>
	<tr>
		<td>TreeModelEvent</td>
		<td>트리 모델에 변화가 생길 때 발생</td>
	</tr>
	<tr>
		<td>TreeSelectionEvent</td>
		<td>트리에서 선택의 변화가 생길 때 발생</td>
	</tr>
	</tbody>
</tr>
</table>


## 리스너 인터페이스를 이용한 이벤트 처리
- 리스너 인터페이스는 이벤트와 이벤트 핸들러 사이를 연결시켜주는 역할을 한다.
- 이벤트가 발생한 해당 컴포넌트를 리스너에 등록시켜야 프로그램의 제어가 해당 이벤트가 제공하는 이벤트 핸들러로 옮겨진다. 
- 자바에서 이벤트 핸들러는 메서드로 표현되며, 이벤트를 처리하는 메서드이다.

- 리스너 인터페이스를 이용하여 이벤트를 처리하기 위해서는 다음과 같은 순서로 프로그램을 작성해야 한다.
	1. 발생하는 이벤트를 처리할 이벤트 종류 결정
	2. 이벤트 처리에 적합한 리스너 인터페이스를 사용하여 클래스 작성
	3. 이벤트를 받아들일 각 컴포넌트에 리스너 등록
	4. 리스너 인터페이스에 선언된 메서드를 오버라이딩하여 이벤트 처리 루틴 작성(이벤트 핸들러 작성)
	
- AWT에서 제공하는 java.awt.event 패키지와 스윙에서 제공하는 javax.swing.event 패키지는 각각의 이벤트 클래스와 연관된 이벤트 리스너 인터페이스를 제공하고 있다.
- 이벤트 리스너 인터페이스는 사용자로부터 받아들인 이벤트를 처리할 메소드들을 선언하고 있다.
- 프로그램에는 이벤트 처리를 위해 이벤트 리스너 인터페이스에서 선언된 모든 메서드들을 오버라이딩하여 구현하여야 한다.

#### 이벤트 클래스와 리스터 인터페이스

|이벤트 클래스|리스너 인터페이스|리스너 인터페이스 메소드(이벤트 핸들러)|
|------|-----|------|
|ActionEvent|ActionListener|actionPerformed(ActionEvent e)|
|ChangeEvent|ChangeListener|stateChanged(ChangeEvent e)|
|ItemEvent|ItemListener|itemStateChanged(ItemEvent e)|
|KeyEvent|KeyListener|keyPressed(KeyEvent e)<br>keyReleased(KeyEvent e)<br>keyTyped(KeyEvent e)|
|ListSelectionEvent|ListSelectionListener|valueChanged(ListSelectionEvent e)|
|MouseEvent|MouseListener|mouseClicked(MouseEvent e)<br>mouseEntered(MouseEvent e)<br>mouseExited(MouseEvent e)<br>mousePressed(MouseEvent e)<br>mouseReleased(MouseEvent e)|
|MouseEvent|MouseMotionListener|mouseDragged(MouseEvent e)<br>mouseMoved(MouseEvent e)|
|WindowEvent|WindowListener|windowActivated(WindowEvent e)<br>windowClosed(WindowEvent e)<br>windowClosing(WindowEvent e)<br>windowDeactivated(WindowEvent e)<br>windowDeiconified(WindowEvent e)<br>windowIconified(WindowEvent e)<br>windowOpened(WindowEvent e)|


### ActionEvent
- ActionEvent는 버튼을 눌렀을 때, 텍스트 필드에서 엔터키를 눌렀을 때, 리스트 항목이 선택되었을 때, 메뉴의 한 항목이 선택되었을 때 발생한다.

#### ActionEvent 생성자

|생성자|설명|
|-----|------|
|ActionEvent(Object src, int type, String cmd)|src는 이벤트를 발생한 객체, type은 이벤트 타입을, cmd는 이벤트를 발생시킨 컴포넌트의 테이블을 의미한다.|
|ActionEvent(Object src, int type, String cmd, int modifiers)|src는 이벤트를 발생한 객체, type은 이벤트 타입을, cmd는 이벤트를 발생시킨 컴포넌트의 레이블을, modifiers는 이벤트가 발생할 때 같이 사용된 수정자의 키의 상수를 의미한다.|

- ActionEvent는 마우스를 누를 때 같이 사용된 수정자(modifier)키가 있는 경우에 구분하기 위하여 4개의 상수를 제공합니다.

#### 수정자 키

|수정자 키|설명|
|-----|------|
|ALT_MASK|수정자 키로 ALT키를 사용|
|CTRL_MASK|수정자 키로 CTRL 키를 사용|
|META_MASK|수정자 키로 META 키를 사용|
|SHIFT_MASK|수정자 키로 SHIFT 키를 사용|

#### ActionEvent의 메서드

|메서드|설명|
|----|------|
|String getActionCommand()|ActionEvent를 발생시킨 객체의 이름을 구한다.|
|int getModifiers()|ActionEvent 발생 시에 같이 사용된 수정자 키(ALT, CTRL, META, SHIFT)를 나타내는 상수값을 구한다.|
|String getSource()|이벤트를 발생시킨 이벤트 소스 객체(이벤트를 발생시킨 컴포넌트)를 구한다.|

- ActionEvent는 ActionListener 인터페이스를 구현(implements)하여 사용하는데, ActionListener가 가지고 있는 메서드인 actionPerformed(ActionEvent e)를 반드시 오버라이딩 해주어야 한다.


#### day23/ActionEventTest.java
```
package day23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionEventTest extends JFrame implements ActionListener {
	JButton jbtn1, jbtn2, jbtn3, jbtn4;
	
	ActionEventTest() {
		super("ActionEvent 처리");
		setLayout(new FlowLayout());
		jbtn1 = new JButton("입력");
		add(jbtn1);
		jbtn2 = new JButton("확인");
		add(jbtn2);
		jbtn3 = new JButton("옵션");
		add(jbtn3);
		jbtn4 = new JButton("메시지");
		add(jbtn4);
		
		jbtn1.addActionListener(this);
		jbtn2.addActionListener(this);
		jbtn3.addActionListener(this);
		jbtn4.addActionListener(this);
		
		setSize(300, 200);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtn1) {
			String name = JOptionPane.showInputDialog(this, "이름을 입력하세요");
			System.out.println(name);
		}
		
		if (e.getSource() == jbtn2) {
			int con = JOptionPane.showConfirmDialog(this, "확인?");
			/** 옵션 타입은 YES_OPTION, YES_NO_OPTION, YES_NO_CANCEL_OPTION이 있다 */
			System.out.println(con);
		}
		
		if (e.getSource() == jbtn3) {
			String[] option = {"검색", "추가", "취소"};
			JOptionPane.showOptionDialog(this, "원하는 직업 선택", "옵션 대화상자" , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
		}
		
		if (e.getSource() == jbtn4) {
			JOptionPane.showMessageDialog(this, "메세지 대화상자");
		}
	}
	
	public static void main(String[] args) {
		ActionEventTest aet = new ActionEventTest();
		aet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
```
- 실행결과

![이벤트3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/23%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%9D%B4%EB%B2%A4%ED%8A%B83.png)

- JOpionPane 클래스(대화상자)를 처리하는데 ActionEvent를 사용하였다.
- 팝업 다이얼로그인 JOptionPane은 대화상자를 출력하는 클래스인데 showInputDialog, showMessageDialog, showConfirmDialog, showOptionDialog의 네 종류가 있다.

### ItemEvent
- ItemEvent는 체크박스, 라디오버튼, 리스트의 항목, 메뉴의 항목이 선택되었거나 해제될 때에 발생한다.

#### ItemEvent 생성자

|생성자|설명|
|----|------|
|ItemEvent(ItemSelectable src, int type, Object entry, int state)|src는 이벤트를 발생시킨 컴포넌트, type은 이벤트 유형, entry는 이벤트 발생 시에 전달하고자 하는 특수한 아이템 객체, static은 아이템의 현재 상태를 의미한다.|

- ItemEvent는 이벤트 유형을 구분하기 위한 두 개의 상수를 제공한다.

#### 이벤트 유형 상수

|이벤트 유형|설명|
|----|------|
|SELECTED|한 항목이 선택되었을 때의 상수|
|DESELECTED|선택된 항목이 해제되었을 때의 상수|

#### ItemEvent의 메서드

|메서드|설명|
|----|------|
|Object getItem()|이벤트를 발생시킨 객체를 반환한다.|
|ItemSelectable getItemSelectable()|이벤트를 발생시킨 ItemSelectable 객체를 반환한다. 선택 박스나 리스트 등은 ItemSelectable 인터페이스를 이용하여 구현한다.|
|int getStateChange()|이벤트의 발생으로 변환된 상태를 상수로 반환한다.|

- ItemEvent는 ItemListener 인터페이스를 구현(implements)하여 사용하는데, ItemListener가 가지고 있는 메서드인 itemStateChanged(ItemEvent e)를 반드시 오버라이딩 해 주어야 한다.


#### day23/ItemEventTest.java
```
package day23;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ItemEventTest extends JFrame {
	JLabel txt1, txt2;
	JRadioButton r1, r2, r3;
	
	public ItemEventTest() {
		super("ItemEvent 처리");
		setLayout(new BorderLayout());
		
		ButtonGroup rgroup = new ButtonGroup();
		r1 = new JRadioButton("선택1");
		r2 = new JRadioButton("선택2");
		r3 = new JRadioButton("선택3");
		
		rgroup.add(r1);
		rgroup.add(r2);
		rgroup.add(r3);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new FlowLayout());
		jp1.add(r1);
		jp1.add(r2);
		jp1.add(r3);
		
		add(jp1, BorderLayout.CENTER);
		
		RBHandler rh = new RBHandler();
		r1.addItemListener(rh);
		r2.addItemListener(rh);
		r3.addItemListener(rh);
		
		JPanel jp2 = new JPanel(new FlowLayout());
		txt1 = new JLabel("선택 항목 : ");
		txt2 = new JLabel();
		jp2.add(txt1);
		jp2.add(txt2);
		add(jp2, BorderLayout.SOUTH);
	}
	
	private class RBHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (e.getSource() == r1) {
					txt2.setText("선택1");
				} else if (e.getSource() == r2 ) {
					txt2.setText("선택2");
				} else if (e.getSource() == r3) {
					txt2.setText("선택3");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ItemEventTest iet = new ItemEventTest();
		iet.setDefaultCloseOperation(EXIT_ON_CLOSE);
		iet.setSize(400, 200);
		iet.setVisible(true);
	}
} 
```
- 실행결과

![이벤트4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/23%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%9D%B4%EB%B2%A4%ED%8A%B84.png)


### ChangeEvent와 JSlider
- ChangeEvent는 JSlider와 같은 컴포넌트에서 값이 변경될 때에 발생된다.
- ChangeEvent는 ChangeListener 인터페이스를 구현하여 사용하는데, ChangeListener가 가지고 있는 메서드인 stateChanged(ChangeEvent e)를 재정의 해 주어야 한다.

- ChangeEvent는 JSlider의 값이 변경될 때마다 발생된다. 슬라이더 손잡이를 움직이거나 애플리케이션에서 JSlider의 setValue() 메서드를 호출하여 value값을 변경할 때 ChangeEvent가 발생한다.

#### day23/JSliderChangeEvent.java
```
package day23;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class JSliderChangeEvent extends JFrame {
	
	JLabel colorLabel;
	JSlider jsl = new JSlider();
	JSliderChangeEvent() {
		setTitle("슬라이더와 ChangeEvent");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		colorLabel = new JLabel("    SLIDER EXAMPLE      ");
		//0~100 사이의 값을 선택할 수 있는 슬라이더 생성. 초기값은 50
		jsl = new JSlider(JSlider.HORIZONTAL,0, 255, 50);
		jsl.setPaintLabels(true);
		jsl.setPaintTicks(true);
		jsl.setPaintTrack(true);
		jsl.setMajorTickSpacing(50);
		jsl.setMinorTickSpacing(10);
		
		jsl.addChangeListener(new MyChangeListener());
		add(jsl);
		
		jsl.setForeground(Color.RED);
		colorLabel.setOpaque(true);
		
		colorLabel.setBackground(new Color(0, jsl.getValue(), 0));
		add(colorLabel);
		setSize(300, 300);
		setVisible(true);
	}
	
	
	class MyChangeListener implements ChangeListener {
		// 슬라이더 컴포넌트의 값이 변경되면 호출됨
		public void stateChanged(ChangeEvent e) {
			colorLabel.setBackground(new Color(0, jsl.getValue(), 0));
		}
	}
	
	public static void main(String[] args) {
		new JSliderChangeEvent();
	}
}
```
- 실행결과 

#### WindowEvent
- WindowEvent는 윈도우와 관련되어 있다. 윈도우의 창을 닫거나 열 때, 크기를 변경할 때, 윈도우가 활성화 되거나 아이콘화 될 때에 WindowEvent가 발생한다.

#### WindowEvent 생성자

|생성자|설명|
|----|------|
|WindowEvent(Window src, int type)|src는 이벤트를 발생시킨 윈도우 객체, type은 이벤트 유형을 의미한다.|

#### 윈도우 유형 상수

|윈도우 유형|설명|
|----|------|
|WINDOW_ACTIVATED|윈도우가 활성화될 때|
|WINDOW_CLOSED|윈도우가 닫힐 때|
|WINDOW_CLOSING|윈도우가 사용자의 요청으로 닫힐 때|
|WINDOW_ICONFIED|윈도우가 아이콘화 될 때|
|WINDOW_OPENED|왼도우가 생성될 때|

#### WindowEvent 메서드

|메서드|설명|
|----|------|
|Window getWindow()|이벤트를 발생시킨 윈도우 객체를 가져온다.|

- WindowEvent는 WindowListener 인터페이스를 구현(implements)하여 사용하기 보다는 WIndowAdapter 클래스를 상속받아 필요한 메서드를 오버라이딩하는 방법으로 많이 사용한다.


### MouseEvent
- MouseEvent는 마우스를 움직이거나 마우스의 버튼을 눌렀을 때 발생하는 이벤트이다. 
- Component 클래스는 버튼이나 마우스나 리스트와 같은 콤포넌트들의 수퍼클래스이다.
- MouseEvent는 Component 클래스가 발생시키기 때문에 자바의 거의 모든 컴포넌트에서 마우스 이벤트를 사용할 수 있다.

- MouseListener는 마우스 버튼을 클릭하거나 해제하는 것과 관련된 리스너이다.
- MouseMotionListener는 마우스의 이동과 관련된 리스너이다. 
- MouseEvent가 발생할 때에는 상기 필요한 메서드를 가지고 있는 리스너를 상속받아서 구현하면 된다.

#### MouseEvent의 리스너 인터페이스

<table>
	<thead>
		<tr>
			<th>리스너 인터페이스</th>
			<th>리스너 인터페이스 메서드</th>
			<th>설명</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td rowspan='5'>MouseListener</td>
			<td>mouseClicked(MouseEvent e)</td>
			<td>마우스 버튼을 눌렀다 놓았을 때에 실행</td>
		</tr>
		<tr>
			<td>mouseEntered(MouseEvent e)</td>
			<td>마우스가 컴포넌트 영역 안으로 들어올 때에 실행</td>
		</tr>
		<tr>
			<td>mouseExited(MouseEvent e)</td>
			<td>마우스가 컴포넌트 영역 밖으로 나갈때에 실행</td>
		</tr>
		<tr>
			<td>mousePressed(MouseEvent e)</td>
			<td>마우스 버튼을 누를 때에 실행</td>
		</tr>
		<tr>
			<td>mouseReleased(MouseEvent e)</td>
			<td>마우스 버튼을 누른 상태에서 움직일때에 실행</td>
		</tr>
		<tr>
			<td rowspan='2'>MouseMotionListener</td>
			<td>mouseDragged(MouseEvent e)</td>
			<td>마우스 버튼을 누른 상태에서 움직일 때에 실행</td>
		</tr>
		<tr>
			<td>mouseMoved(MouseEvent e)</td>
			<td>마우스 버튼을 누르지 않고 움직일 때에 실행</td>
		</tr>
	</tbody>
</table>

#### MouseEvent 클래스의 주요 메서드

|메서드|설명|
|----|------|
|int getX()|마우스 이벤트가 발생한 위치의 x좌표를 구한다.|
|int getY()|마우스 이벤트가 발생한 위치의 y좌표를 구한다.|
|int getClickCount()|마우스가 클릭한 횟수를 구한다.|
|Point getPoint()|마우스 이벤트가 발생한 위치의 좌표(x, y)를 구한다.|
|boolean isPopupTrigger()|팝업 메뉴를 나타내게 할 이벤트인지를 구한다.|
|void translatePoint(int x, int y)|현재 이벤트가 발생한 좌표에서 (x, y)좌표로 위치를 이동한다.|


#### day23/MouseEventDraw.java
```
package day23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseEventDraw extends JFrame implements MouseListener {
	
	
	public MouseEventDraw() {
		super("MouseEvent");
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX() + "," + e.getY());
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public static void main(String[] args) {
		new MouseEventDraw();
	}
}

- 실행결과 

157,113
309,127
171,85
111,202
```

### KeyEvent 
- KeyEvent는 키보드의 키를 누르거나 놓을 때에 발생하는 이벤트이다.
- Component 클래스를 상속받은 컴포넌트들인 JLabel, JButton, JCheckBox, JList 등은 모두 키 이벤트를 발생시킬 수 있다.
- 키 이벤트도 마우스 이벤트와 마찬가지로 리스너 인터페이스인 KeyListener를 상속받아서 구현한다. 

#### KeyEvent의 리스너 인터페이스

<table>
<thead>
	<tr>
		<th>리스너 인터페이스</th>
		<th>리스너 인터페이스 메서드</th>
		<th>설명</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td rowspan='3'>KeyListener</td>
		<td>keyPressed(KeyEvent e)</td>
		<td>키가 눌려졌을 때에 실행</td>
	</tr>
	<tr>
		<td>keyReleased(KeyEvent e)</td>
		<td>키를 놓았을 때에 실행</td>
	</tr>
	<tr>
		<td>keyTyped(KeyEvent e)</td>
		<td>키를 타이핑 했을 때에 실행</td>
	</tr>
</tbody>
</table>

#### KeyEvent 클래스의 주요 메서드

|메서드|설명|
|----|------|
|char getKeyChar()|키 이벤트가 발생된 유니코드 문자를 구한다.|
|int geKeyCode()|키 이벤트가 발생된 문자의 코드값을 구한다.|
|String getKeyText(int keyCode)|Function 키, Delete 키, 일반문자와 같은 문자열을 구한다.|

#### day23/KeyEventDraw.java
```
package day23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyEventDraw extends JFrame implements KeyListener {
	String kname= " ";
	public KeyEventDraw() {
		super("KeyEvent");
		
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 키를 눌렀을 때 실행
	public void keyPressed(KeyEvent e) {
			kname = "Key Pressed : " + e.getKeyText(e.getKeyCode());
			System.out.println(kname);
	}
	
	// 키를 놓았을때 는 
	public void keyReleased(KeyEvent e) {
		
	}
	
	// 키를 눌렀다가 놓았을 때 실행
	public void keyTyped(KeyEvent e) {
		
	}
	
	
	public static void main(String[] args) {
		new KeyEventDraw();
	}
}

```


### InputEvent
- InputEvent는 Component 클래스로부터 상속된 추상 클래스이다.
- 추상 클래스는 객체를 생성할 수 없기 때문에 이 클래스로부터 이벤트를 생성하여 사용할 수 가 없다.
- InputEvent는 하위 클래스에서 사용하는 다양한 상수와 메서드를 정의하고 있다.
- InputEvent는 하위 클래스로 MouseEvent와 KeyEvent 클래스가 있다. InputEvent는 이벤트 발생 시에 같이 사용된 수정자 키를 구분하기 위하여 다음의 표과 같은 상수를 제공한다.

#### InputEvent의 수정자 키 상수

|수정자 키 상수|설명|
|----|------|
|ALT_MASK|수정자 키로 ALT키를 사용|
|BUTTON1_MASK|마우스의 첫 번째 버튼 사용(왼쪽 버튼)|
|BUTOTN2_MASK|마우스의 두 번째 버튼 사용
|BUTTON3_MASK|마우스의 세 번째 버튼 사용(오른쪽 버튼)|
|CTRL_MASK|수정자 키로 CTRL 키를 사용|
|META_MASK|수정자 키로 META 키를 사용|
|SHIFT_MASK|수정자 키로 SHIFT 키를 사용|

#### InputEvent의 메서드

|메서드|설명|
|----|------|
|boolean isAltDown()|수정자 키로 ALT키가 사용되었다면 true, 아니면 false를 반환|
|boolean isControlDown()|수정자 키로 CTRL 키가 사용되었다면 true, 아니면 false를 반환|
|boolean isMetaDown()|수정자 키로 META 키가 사용되었다면 true, 아니면 false를 반환|
|boolean isShiftDown()|수정자 키로 SHIFT 키가 사용되었다면 true, 아니면 false를 반환|
|int getModifiers()|이벤트 발생 시에 같이 사용된 수정자 키를 반환|

#### day23/MouseInputEvent.java
```
package day23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseInputEvent extends JFrame implements MouseListener {
	String msg;
	JLabel sbar;
	
	public MouseInputEvent() {
		super("마우스에서 InputEvent");
		setLayout(new BorderLayout());
		sbar = new JLabel();
		add(sbar, BorderLayout.SOUTH);
		addMouseListener(this);
		setSize(300, 200);
		setVisible(true);
	}
	
	// 마우스 버튼을 클릭했을 때 실행
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	// 프레임 안의 마우스 커서가 프레임 밖으로 나갈 때 발생
	public void mouseExited(MouseEvent e) {
		
	}
	
	// 마우스 버튼을 눌렀을 때 실행
	public void mousePressed(MouseEvent e) {
		if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
			msg = "마우스 오른쪽 버튼을 눌렀습니다.";
		} else {
			msg = "마우스 왼쪽 버튼을 눌렀습니다.";
		}
		sbar.setText(msg);
	}
	
	public void mouseReleased(MouseEvent e) {
		msg = "마우스 버튼을 누르세요";
		sbar.setText(msg);
	}
	
	public static void main(String[] args) {
		MouseInputEvent mie = new MouseInputEvent();
		mie.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
```

- 실행화면

![이벤트6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/23%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%9D%B4%EB%B2%A4%ED%8A%B86.png)


#### day23/KeyInputEvent.java
```
package day23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyInputEvent extends JFrame implements KeyListener {
	String msg;
	JLabel sbar;
	
	public KeyInputEvent() {
		super("키보드에서의 InputEvent");
		sbar = new JLabel();
		add(sbar);
		addKeyListener(this);
		setSize(300, 200);
		setVisible(true);
	}
	
	// 키를 눌렀을 때 실행
	public void keyPressed(KeyEvent e) {
		msg = "keyPressed : " + e.getKeyText(e.getKeyCode());
		if (e.isShiftDown()) { 
			msg += "<Shift>"; 
		}
		
		if (e.isControlDown()) {
			msg += "<Ctrl>";
		}
		
		if (e.isAltDown()) {
			msg += "<Alt>";
		}
		
		sbar.setText(msg);
	}
	
	// 키를 놓았을 때 실행
	public void keyReleased(KeyEvent e) {
		msg = "KeyReleased : " + KeyEvent.getKeyText(e.getKeyCode());
		if (e.isShiftDown()) {
			msg += "<Shift>";
		}
		
		if (e.isControlDown()) {
			msg += "<Ctrl>";
		}
		
		if (e.isAltDown()) {
			msg += "<Alt>";
		}
		
		sbar.setText(msg);
	}
	
	// 키를 눌렀다가 놓았을 때 실행
	public void keyTyped(KeyEvent e) {
		
	}
	
	public static void main(String[] args) {
		KeyInputEvent kie = new KeyInputEvent();
		kie.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
```
- 실행화면

![이벤트7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/23%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%9D%B4%EB%B2%A4%ED%8A%B87.png)



## 어댑터를 이용한 이벤트 처리
- 리스너 인터페이스를 구현(implements)하기 위해서는 반드시 재정의 해 주어야 한다. 
- 만약에 MouseListener를 구현해야 하는 경우에, 필요가 없더라도 이 인터페이스가 가지고 있는 5개의 메서드를 모두 재정의 해 주어야 한다.
- 이러한 문제를 해결해 주는 방법이 <b>어댑터 클래스(Adapter Class)</b>이다.
- 어댑터 클래스는 리스너가 가지고 있는 메서드 중에서 필요한 메서드만 재정의 해주면 된다.
- 필요한 경우에 일부 메서드만 어댑터 클래스를 상속 받는 것이 리스너 인터페이스를 구현하는 것 보다 더 편리하다.
- 어댑터 클래스는 인터페이스의 기능을 그대로 옮겨놓은 추상 클래스이다.

#### 리스너 인터페이스와 관련된 어댑터 클래스

|리스너 인터페이스|어댑터 클래스|
|----|------|
|ActionListener|없음|
|ChangeListener|없음|
|ItemListener|없음|
|KeyListener|KeyAdapter|
|ListSelectionListener|없음|
|MouseListener|MouseAdapter, MouseMotionAdapter|
|WindowListener|WindowAdapter|

- 자바에서의 어댑터는 클래스이기 때문에 앞의 리스너 인터페이스처럼 구현으로 사용할 수가 없고, 내부 클래스를 이용하여 사용해야 한다.

#### day23/KeyEventAdapter.java
```
package day23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyEventAdapter extends JFrame {
	String msg;
	JLabel sbar;
	
	public KeyEventAdapter() {
		super("키보드에서의  InputEvent");
		sbar = new JLabel();
		add(sbar);
		addKeyListener(new KeyHandler());
		setSize(300, 200);
		setVisible(true);
	}
	
	// 어댑터를 상속받은 내부 클래스로 선언 
	class KeyHandler extends KeyAdapter {
		// 키를 눌럿을 때 실행
		public void keyPressed(KeyEvent e) {
			msg = e.getKeyText(e.getKeyCode());
			if (e.isShiftDown()) msg = "KeyPressed : <Shift> +" + msg;
			if (e.isControlDown()) msg = "KeyPressed : <Ctrl> + " + msg;
			if (e.isAltDown()) msg = "KeyPressed : <Alt> + " + msg;
			
			sbar.setText(msg);
		}
		
		// 키를 놓았을 때 실행
		public void keyReleased(KeyEvent e) {
			msg = "KeyReleased : " + e.getKeyText(e.getKeyCode());
			if (e.isShiftDown()) msg = e.getKeyText(e.getKeyCode());
			if (e.isControlDown()) msg = e.getKeyText(e.getKeyCode());
			if (e.isAltDown()) msg = e.getKeyText(e.getKeyCode());
			
			sbar.setText(msg);
		}
	}
	
	public static void main(String[] args) {
		KeyEventAdapter kea = new KeyEventAdapter();
		kea.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
```

- 실행결과

![이벤트8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/23%EC%9D%BC%EC%B0%A8(3h)%20-%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%9D%B4%EB%B2%A4%ED%8A%B88.png)
