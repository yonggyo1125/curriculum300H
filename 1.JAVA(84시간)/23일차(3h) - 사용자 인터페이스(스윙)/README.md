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

## 어댑터를 이용한 이벤트 처리

