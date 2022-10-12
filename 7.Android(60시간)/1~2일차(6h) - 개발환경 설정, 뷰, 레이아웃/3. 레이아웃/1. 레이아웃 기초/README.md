# 레이아웃 기초 익히기
- 뷰를 배치할 때 사용하는 레이아웃에 대해 살펴봅니다. 안드로이드 스튜디오에서는 기본적인 레이아웃을 제약 레이아웃(Contraint Layout)으로 자동 설정합니다.

## 제약 조건 이해하기

- 제약 레이아웃의 가장 큰 특징은 뷰의 위치를 결정할 때 제약 조건(Constraint)을 사용한다는 것입니다.
- 제약 조건이란 뷰가 레이아웃 안의 다른 요소와 어떻게 연결되었는지 알려주는 것으로, 뷰의 연결점(Anchor Point)과 대상(Target)을 연결합니다. 
- 다음 그림을 보면 제약 레이아웃 안에 버튼이 하나 있습니다. 이때 버튼의 입장에서 자신을 감싸고 있는 레이아웃을 부모 레이아웃(Parent Layout)이라고 부를 수 있습니다. 그리고 버튼은 위, 아래, 왼쪽, 오른쪽 각 연결점을 가지고 있으며 작은 동그라미로 표시됩니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image1.png)

- 핸들(Side Contraints Handle)이라고 부르는 연결점은 마우스 커서로 잡아서 조절할 수 있습니다. 이 연결점이 무엇을 어떻게 할 수 있는지가 중요합니다. 여기서 적용하고 싶은 제약 조건은 '버튼의 왼쪽을 부모 레이아웃과 연결해 주세요.'입니다. 이 경우에 버튼의 왼쪽 연결점을 부모 레이아웃의 왼쪽 벽면과 연결합니다. 그러면 부모 레이아웃이 타깃(Target)이 되어 연결점과 해당 타깃이 연결됩니다. 이렇게 연결점과 타깃이 연결되면 연결선이 만들어지고 연결점은 파랗게 표시됩니다.

- 버튼이 레이아웃 안에서 어디에 위치하고 있는지 결정하려면 적어도 왼쪽과 위쪽에 연결되어 있어야 합니다. 따라서 버튼의 위쪽 연결점과 부모 레이아웃의 위쪽 벽면을 연결하는 것까지 진행하면 버튼의 제약 조건은 완성이 됩니다. 물론 오른쪽 연결점과 아래쪽 연결점도 타깃과 연결할 수 있지만 꼭 필요한 제약조건은 두 개면 됩니다.

- 연결선을 만들 때는 뷰의 연결점과 타깃이 필요한데, 다음과 같은 것들이 타깃이 될 수 있습니다.
	- 같은 부모 레이아웃 안에 들어 있는 다른 뷰의 연결점
	- 부모 레이아웃의 연결점
	- 가이드라인(Guideline)
	
- 그리고 대상 뷰의 타깃의 연결점으로는 다음과 같은 것들이 될 수 있습니다.
	- 위쪽(Top), 아래쪽(Bottom), 왼쪽(Left), 오른쪽(Right)
	- 가로축의 가운데(CenterX), 세로축의 가운데(CenterY)
	- 베이스라인(Baseline) -> 텍스트를 보여주는 뷰인 경우에만 적용됨

- 여기에서 왼쪽과 오른쪽은 각각 Left, Right라는 단어로 표현하기도 하고 Start, End라는 단어로 표현하기도 합니다. 이런 단어들은 나중에 XML 코드를 직접 변경할 때 필요하므로 기억하면 좋습니다. 가운데에도 넓쩍한 표시의 베이스라인 연결점이 있는데, 이것은 텍스트가 있을 경우 다른 뷰의 텍스트와 높이를 맞춰주는 역할을 합니다.

### 예제

- 먼저 열려있는 안드로이드 스튜디오 창의 위쪽 메뉴 중에서 [File -> Close Project] 메뉴를 선택하면 시작화면으로 돌아갑니다.
- 시작화면에서 [New Project] 메뉴를 눌러 새로운 프로젝트를 만드는 대화상자를 띄웁니다. 
- 화면은 Empty Activity로 선택하고 [Next] 버튼을 누른 후 새로운 프로젝트의 이름은 SampleConstraintLayout으로 입력합니다.
- 패키지 이름이나 프로젝트가 저장될 폴더는 이전에 설정했던 정보를 사용하기 때문에 자동으로 입력됩니다.
- [Finish] 버튼을 눌러서 새로운 프로젝트를 만듭니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image2.png)

- 새로운 프로젝트가 만들어지면 프로젝트 창의 가운데 작업 영역에서 [activity_main.xml] 탭을 눌러 XML 레이아웃 파일을 엽니다. 
- 디자인 화면이 표시되면 사각형 폰 모양 안에 글자가 하나 들어 있습니다. 먼저 화면 가운데에 있는 글자를 선택하고 [Delete]를 눌러 삭제합니다. 그리고 왼쪽의 팔레트 \<Palette\>에서 버튼을 가져다 화면 왼쪽 윗부분에 딱 맞게 붙이지 말고 약간 떨어뜨립니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image3.png)

- 버튼의 왼쪽 연결점을 끌어다가 부모 레이아웃의 벽면에 같다 놓으면 새로운 제약조건이 생성되면서 연결선이 표시됩니다.
- 오른쪽 속성 창의 가운데 부분에는 뷰의 제약조건을 표시하는 그림이 있는데 그 그림에도 왼쪽 연결선이 표시됩니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image4.png)

- 이렇게 속성 창의 그림에서는 설정된 제약조건(Contraint)이 표시됩니다. 오른쪽 창에 보이는 연결선에는 0이라는 숫자가 적혀 있습니다. 이 숫자는 여러분이 버튼을 어느 위치에 놓았는지에 따라 달라질 수 있습니다.
- 이 숫자는 보통 마진(Margin)이라고 부르며 연결점과 타깃과의 거리를 나타냅니다. 즉, 버튼이 왼쪽 벽면과 얼마나 떨어져있는지를 나타내는 값입니다. 화면에서 버튼을 끌어당기면 이 값을 조절할 수 있습니다.
- 또는 직접 숫자를 입력하여 얼마나 떨어뜨릴지 거리를 지정할 수도 있습니다. 이 숫자를 클릭한 후 마진(Left Margin)을 80으로 수정하고 [Enter]를 누릅니다. 그러면 버튼과 왼쪽 벽면 사이의 간격이 입력된 수치만큼 넓어집니다.
- 이어서 버튼의 위쪽 연결점과 위쪽 벽면도 연결한 후 연결선에 표시된 마진(Top Margin) 값을 80으로 수정합니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image5.png)

- 왼쪽과 위쪽에서 어느 정도 떨어진 위치에 배치되었습니다. 
- 이번에는 왼쪽 팔레트에서 버튼 하나를 더 끌어다가 화면에 배치되어 있는 버튼 오른쪽에 놓습니다. 새로운 버튼을 추가할 때 기존에 있던 버튼의 세로 위치를 맞출 수 있도록 점선이 표시되기 때문에 버튼 오른쪽에 쉽게 배치할 수 있습니다. 
- 새로 추가한 버튼의 왼쪽 연결점을 끌어다 기존 버튼의 오른쪽 연결점과 연결합니다. 그리고 위쪽 연결점을 끌어다 화면의 위쪽 벽면과 연결합니다.
- 오른쪽 속성 창에서 위쪽 연결선의 마진(Top Margin)값은 80으로 변경하고 왼쪽 연결선의 마진(Left Margin) 값은 40으로 변경합니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image6.png)

- 새로 추가한 버튼의 왼쪽 연결점을 기존 버튼과 연결하면 기존 버튼의 타깃이 되면서 새로운 연결선이 만들어집니다. 그리고 마진을 조절하면 두 개의 버튼이 적절한 위치에 배치되도록 만들 수 있습니다.


## 화면 가운데 뷰 배치하기

- 화면에 뷰를 넣고 배치할 때는 왼쪽 윗부분부터 차례대로 추가하는 것이 일반적이지만 가운데나 아래쪽 부터 추가할 때도 있습니다. 그중에서 가운데 또는 가운데에서 약간 아래쪽이나 위쪽에 뷰를 추가하는 경우도 생깁니다. 
- 예를 들어, 로그인 화면을 만들 때 아이디나 비밀번호를 입력하는 입력상자와 로그인 버튼은 가운데 부분에 모아둡니다. 이런 경우에는 입력상자와 버튼이 함께 모여 있으니 이것들을 담아둘 레이아웃을 하나 만든 후 최상위 레이아웃의 가운데 부분에 넣는 것이 좋습니다.

- 먼저 왼쪽 팔레트에서 버튼을 화면 가운데 부분에 끌어다 놓습니다. 그러면 버튼의 위, 아래, 왼쪽, 오른쪽 연결점이 자동으로 연결되면서 화면 가운데 버튼이 배치됩니다.
- 만약 디자인 화면 상단에 있는 자석 모양의 아이콘(Autoconnect)이 활성화된 상태가 아니면 각각의 연결점을 직접 연결해야 합니다. 
- 즉, 버튼의 위, 아래, 왼쪽, 오른쪽 연결점을 각각 화면의 네 벽면에 직접 드래그해서 끌어다 놓아야만 버튼의 연결점이 연결됩니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image7.png)

- 뷰는 가로축과 세로축을 기준으로 배치됩니다. 그런데 가로축에 해당하는 왼쪽과 오른쪽에 모두 연결선이 만들어지는 경우에는 양쪽 타깃 중간에 위치하게 됩니다. 세로축도 마찬가지여서 위쪽과 아래쪽에 모두 연결선이 만들어지는 경우라고 타깃 중간에 위치하게 됩니다. 결국 화면 전체를 차지하고 있는 최상위 레이아웃 안에 추가된 버튼에 가로축과 세로축 모두 양쪽으로 연결선을 만들어주면 버튼을 화면 가운데 부분에 위치시킬 수 있습니다.

- 만약 정확히 한 가운데가 아니라 약간 위쪽으로 치우치거나 왼쪽으로 치우치게 만들고 싶다면, 오른쪽 속성 창에 보이는 레이아웃 속성에서 왼쪽 끝에 있는 줄이 세로 방향 바이어스(Vertical Bias)입니다. 그리고 아래쪽 끝에 있는 줄이 가로방향 바이어스(Horizontal Bias)입니다.

- 바이어스는 한쪽으로 얼마나 치우쳐 있는지를 나타내는 것으로 기본 값은 50입니다. 즉, 앞에서 설명한 대로 양쪽 타깃의 중간에 위치하도록 설정되어 있습니다. 왼쪽에 있는 세로 방향 바이어스의 가운데 원 모양을 잡아서 위쪽으로 잡아끌면 그 값이 변경되면서 버튼이 위쪽으로 올라갑니다. 세로 방향 바이어스의 값이 35가 될 때까지 올린 후 놓으면 버튼은 위쪽으로 부터 35%되는 지점에 위치하게 됩니다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image8.png)

- 바이어스는 화면의 비율로 나눈 후 어느 곳에 위치시킬지를 결정하는 값입니다. 바이어스의 값은 0부터 1사이의 값이 되므로 35% 지점은 0.35로 설정할 수 있습니다. 다만 XML 원본 파일에서는 0.35 값으로 설정한다고 하더라도 디자인 화면에 표시될 때는 % 단위로 표시됩니다.

- 뷰의 크기를 더 크게 하고 싶다면 버튼을 선택한 후 각 모서리에 표시된 작은 사각형 점을 끌어당기면 됩니다. 가운데에 추가한 버튼을 선택한 후 오른쪽 아래의 모서리를 끌어당겨 좀 더 크게 만듭니다.

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image9.png)

- 오른쪽 속성 창의 제약 조건이 표시되는 그림을 보면 뷰의 안쪽에 있는 선들이 방향을 나타내던 꺽은선 에서 직선으로 바뀐 것을 확인할 수 있습니다. 
- 사각현 안쪽에 있는 선은 뷰의 layout_width와 layout_height가 어떻게 설정되었는지 표시합니다. 사각형 안쪽의 선을 마우스로 클릭하면 layout_width와 layout_height의 값을 손쉽게 바꿀 수도 있습니다. 

- 다음 세가지의 경우를 비교하면서 살펴보세요.
- <b>(1) 부모 여유 공간 채우기</b> : 연결선이 구불한 선으로 표시됨(layout_width의 값은 match_contraint나 0dp로 설정됨)
	
![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image10.png)

- <b>(2) 뷰의 내용 채우기</b> : 사각형 안쪽의 선이 중앙을 향하는 화살표로 표시됨(layout_width 값은 wrap_content로 설정됨)

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image11.png)

- <b>(3) 고정 크기</b> : 사각형 안쪽의 선이 직선으로 표시됨(layout_width와 layout_height의 값은 지정한 값으로 설정됨)

![image12](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image12.png)

- 안쪽에 있는 가로 방향 선을 클릭하면 구불구불한 선이나 중앙으로 향하는 화살표가 표시되거나 또는 직선이 표시됩니다. 이 각각의 선 모양이 의미하는 것이 무엇인지 이해하면 설정되어 있는 제약조건을 파악할 수 있습니다.

- 예를 들어, 부모 레이아웃의 여유 공간을 꽉 채워주는 구불구불한 선의 크기를 match_constraint나 0dp로 설정하고, 제약 조건(Contraint)을 이용해 부모 레이아웃의 여유 공간을 채우는 방식으로 적용됩니다. 여기에서 여유 공간(Available Space)이란 해당 뷰가 차지할 수 있는 공간을 의미합니다.

- 만약 화면의 왼쪽 상단에 버튼을 하나 추가했다면 버튼의 오른쪽와 아래쪽에 공간이 많이 남아있을 것입니다. 이 여유 공간은 부모 레이아웃이 어떤 것인가에 따라 달라집니다. 
- 예를 들어, 리니어 레이아웃(LinearLayout)의 경우에는 한쪽 방향으로만 뷰를 추가하는 레이아웃이므로 하나의 버튼을 세로 방향으로 추가했다면 그 버튼의 좌우에 공간이 남아 있어도 버튼을 추가할 수 없습니다.
- 하지만 지금 사용하고 있는 제약 레이아웃의 경우에는 뷰를 담고 있는 부모 레이아웃 안에서 크기나 위치를 마음대로 조절할 수 있습니다.

## 가이드라인 사용하기

- 가이드라인(Guideline)은 여러 개의 뷰를 일정한 기준 선에 정렬할 때 사용합니다.

![image13](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image13.png)

- 새로운 XML 레이아웃 파일을 추가한 후 가이드라인을 사용해보겠습니다. 
- 왼쪽 가장자리의 숨겨둔 프로젝트 창을 열고 app 폴더 안에 있는 res/layout 폴더를 선택합니다. 그리고 마이수 오른쪽 버튼을 누르면 팝업 메뉴가 표시되는데 여기에서 [New -> Layout resource file] 메뉴를 선택합니다.

![image14](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image14.png)

> 왼쪽 프로젝트 창이 보이지 않고 왼쪽 가장자리에 숨겨진 프로젝트 창도 보이지 않을 때가 있습니다. 이때는 상단 메뉴에서 [View -> Tool Windows -> Project] 메뉴를 선택하면 됩니다.

- [New Resource File] 대화상자가 나타나면 XML 레이아웃 파일 이름을 입력합니다. File name: 입력 상자에는 activity_menu.xml을 입력합니다. [OK] 버튼을 누르면 새로운 activity_menu.xml 파일이 생성됩니다.

![image15](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image15.png)

- 왼쪽 프로젝트 창을 보면 이 파일이 만들어진 위치가 app 폴더 아래에 있는 res/layout 폴더라는 것을 알수 있습니다. 
- 저장 위치를 확인했으면 디자인 화면이 크게 보이도록 [-]모양의 [Hide] 버튼을 눌러서 프로젝트 창을 숨깁니다. 
- 디자인 화면의 위쪽에 있는 아이콘 중에서 [Guidelines] 아이콘을 클릭합니다. 그러면 가로 방향으로 추가할 것인지, 세로 방향으로 추가할 것인지 묻는 메뉴가 나타납니다.

![image16](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image16.png)

- [Vertical Guideline] 메뉴를 선택하면 세로 줄이 추가되고 [Horizontal Guideline] 메뉴를 선택하면 가로 줄이 추가됩니다. 
- [Vertical Guideline] 메뉴를 선택하여 세로 줄을 추가합니다. 왼쪽 하단의 Component Tree 창을 보면 화면에 추가된 뷰의 계층도를 볼 수 있는데, ContraintLayout 안에 guideline이 추가된 것을 확인할 수 있습니다. 추가된 가이드라인을 오른쪽으로 끌어당기면 점선의 위치를 조절할 수 있습니다.

![image17](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image17.png)

- 위치를 수정한 가이드라인을 기준으로 버튼 세 개를 추가합니다. 왼쪽 팔레트 창에서 버튼을 끌어다가 가이드라인의 오른쪽에 놓은 후 버튼의 왼족 연결점을 가이드라인과 연결합니다. 그리고 버튼의 왼쪽 연결점을 화면 위쪽 벽면과 연결합니다. 그러면 가이드라인에 붙어있는 모양이 됩니다.
- 첫 번째 버튼은 속성 창에서 위쪽 마진(Top Margin) 값을 '16', 왼쪽 마진(Left Margin) 값은 '0'으로 설정합니다.
- 두 번째와 세 번째 버튼도 동일한 방식으로 추가한 후 왼쪽 연결점은 가이드라인과 연결하고 위쪽 연결점은 이전 버튼의 아래쪽 연결점과 연결합니다.
- 추가한 버튼도 왼쪽 마진(Left Margin) 값을 '0'으로 설정해서 가이드라인에 완전히 붙게 만듭니다.

![image18](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image18.png)

## XML 원본에 추가된 속성 확인하기

- 실제 앱을 만들 때는 모든 것을 디자이너 도구의 화면에서 해결할 수 없는 경우가 많습니다. 화면에 들어가는 뷰가 복잡하게 배치되기도 하고 뷰들을 서로 중첩하여 쌓아두어야 할 수도 있으므로 XML 원본 코드를 수정할 때가 점점 많아질 것입니다. 따라서 XML 원본 코드가 어떻게 구성되어 있는지 살펴보고 수정해보는 것이 필요합니다.

- 디자이너 도구 [Code]의 아이콘을 눌러 원본 XML을 보면 자동으로 입력된 속성이 들어 있습니다.

#### SampleConstraintLayout > /app/res/layout/activity_menu.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_begin="50dp" />

    <Button
        android:id="@+id/button4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Button"
        app:layout_constraintStart_toStartOf="@+id/guideline2"
        app:layout_constraintTop_toBottomOf="@+id/button5" />

    <Button
        android:id="@+id/button5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Button"
        app:layout_constraintStart_toStartOf="@+id/guideline2"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button6"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Button"
        app:layout_constraintStart_toStartOf="@+id/guideline2"
        app:layout_constraintTop_toBottomOf="@+id/button4" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

- 가장 위쪽에 있는 줄은 XML 파일에 일반적으로 추가하는 정보이며, 이 파일이 XML 형식으로 된 것임을 알려줍니다.

```xml
<?xml version="1.0" encoding="utf-8"?>
```

- 그 다음에 입력되어 있는 태그가 화면 전체를 감싸고 있는 레이아웃이며, Component Tree 창의 계층도에서 가장 위쪽에 있으므로 최상위 레이아웃이라고 부를 수 있습니다. 여기에서는 ConstraintLayout이 태그의 이름으로 사용되었습니다. 
- 그 앞에는 패키지 이름(androidx.constraintLayout.widget)이 같이 붙어 있습니다. 만약 위젯이나 레이아웃이 안드로이드 기본 API에 포함되어 있다면 그 위젯이나 레이아웃의 이름만 입력하면 됩니다. 하지만 외부 라이브러리에서 불러온 것이라면 패키지 이름까지 입력해야 합니다. 
- ConstraintLayout은 안드로이드 SDK에 나중에 추가되면서 외부 라이브러리로 분류되어 있습니다. 따라서 그 앞에 피키지 이름까지 함께 기록한 것입니다.

```
androidx.constraintlayout.widget.ConstraintLayout 
```

- 이 태그의 속성을 살펴보면 xmlns:로 시작하는 속성들이 있습니다. 그 중에서 xmls:android 속성은 XML 레이아웃 파일이라면 한 번씩 넣어주어야 하는 속성입니다. 그리고 하나의 파일에 한 번만 사용되면 됩니다. 
- 이 xmlns 뒤에 있는 android라는 이름이 나머지 속성의 접두어(Prefix)로 사용됩니다. 예를 들어, android:layout_width 속성에서 앞에 있는 android:는 xmlns:android로 지정된 정보를 참조하여 사용한다는 의미 입니다.

#### xmlns 뒤에 있는 접두어의 의미 

|접두어|의미|
|----|---------|
|xmlns:android|안드오리드 기본 SDK에 포함되어 있는 속성을 사용합니다.|
|xmlns:app|프로젝트에서 사용하는 외부 라이브러리에 포함되어 있는 속성을 사용합니다.<br>app라는 단어는 다른 것으로 바꿀 수 있습니다.|
|xmlns:tools|안드로이드 스튜디오의 디자이너 도구 등에서 화면에 보여줄 때 사용합니다. 이 속성은 앱이 실행될 때 는 적용되지 않고 안드로이드 스튜디오에서만 적용됩니다.|

- android:layout_width와 android:layout_height 속성이 실제로는 안드로이드 기본 SDK에 들어 있는 layout_width와 layout_height 속성을 사용한다는 의미 입니다. 이 두 개 속성은 화면에 추가되면 모든 뷰의 기본 속성이라고 하였으므로 이 태그 안에 포함된 다른 태그들도 모두 갖고 있는 속성입니다.

- ConstraintLayout 태그 안에 들어 있는 태그 속성 중에서는 android:id 속성이 있습니다. 이 id 속성은 뷰를 구분하는 구분자 역할을 합니다. id속성이 사용되는 용도를 크게 두 가지로 나눌 수 있습니다.
	- XML 레이아웃 파일 안에서 뷰를 구분할 때
	- XML 레이아웃 파일에서 정의한 뷰를 자바 소스 파일에서 찾을 때 
	
- XML 레이아웃 파일 안에는 여러 개의 뷰를 추가할 수 있고, 추가한 각각의 뷰는 다른 뷰의 왼쪽이나 오른쪽 등에 연결될 수 있습니다. 이때 다른 뷰가 어떤 것인지 지정할 필요가 있는데 그 목적으로 id 속성 값이 사용됩니다.

- 제약 레이아웃에서 하나의 뷰를 다른 뷰와 연결할 때 사용하는 XML 속성의 이름은 다음과 같은 규칙을 갖습니다.

```
layout_constraint[소스 뷰의 연결점]_[타깃 뷰의 연결점]="[타깃 뷰의 id]"
```

![image19](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image19.png)

- 예를 들어, 두 개의 버튼이 각각 button과 button2라는 id 값을 가지고 있을 때 두 번째 버튼의 위쪽 연결점으로 부터 첫 번째 버튼의 아래쪽 연결점까지 연결하려고 한다면 두 번째 버튼이 소스 뷰(Source View)가 되고 첫 번째 버튼이 타깃 뷰(Target View)가 됩니다.
- 따라서 layout_constraint 뒤에 Top과 \_기호, 그리고 toBottomOf라는 속성 이름이 사용됩니다. 
- 속성 값으로는 첫 번째 뷰의 id 값이 사용됩니다. 
- 여기에서 id 속성 값은 다음과 같은 형식으로 정의하고 사용됩니다.

```
@+id/아이디 값
```

- 디자이너 도구 화면의 오른쪽 속성 창을 살펴보면 첫 번째 뷰의 id 값으로 button이라는 단어만 들어간 것을 확인할 수 있습니다. 하지만 XML 레이아웃 파일의 원본 XML에서는 위의 형식으로 id를 지정해야 합니다. 

> 안드로이드 초기 버전에는 @+id의 형식이 XML 레이아웃 파일에 id 값을 추가한다는 의미로 만들어졌습니다. 따라서 이렇게 만들어진 id 값을 사용할 때는 @id 형식과 @+id 형식을 혼용하여 사용했습니다. 하지만 지금은 @+id 형식을 주로 사용하고 @id 형식은 거의 사용하지 않습니다.

- 다음과 같이 여러개의 속성들을 사용할 수 있습니다.
	- layout_constraintTop_toTopOf
	- layout_constraintTop_toBottomOf
	- layout_constraintBottom_toTopOf
	- layout_constraintBottom_toBottomOf
	- layout_constraintLeft_toTopOf
	- layout_constraintLeft_toBottomOf
	- layout_constraintLeft_toLeftOf
	- layout_constraintLeft_toRightOf
	- layout_constraintRight_toTopOf
	- layout_constraintRight_toBottomOf
	- layout_constraintRight_toLeftOf
	- layout_constraintRight_toRightOf

- 이 외에도 Left 대신 Start를 사용할 수 있고, Right 대신 End를 사용할 수 있습니다.
- 가이드라인을 추가해서 생긴 태그를 살펴보겠습니다.

```xml 
... 생략 

<androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_begin="50dp" />
		
... 생략

```

- Guideline 클래스는 외부 라이브러리에 들어 있어 패키지 이름인 androidx.constraintLayout.widget을 함께 붙여줍니다. 
- 태그에는 필수 속성인 layout_width와 layout_height 속성과 값이 들어 있습니다. 
- 그리고 id값으로 @+id/guideline이 설정되어 있습니다. 
- 그 다음 속성으로는 android:orientation이 있는데 이 가이드라인은 가로 또는 세로 방향 중에서 어느 방향인지를 지정해야 하므로 orientation도 필수 속성입니다.

```
Guideline의 필수 속성 -> android:orientation
```

- 그 아래에는 layout_constraintGuide_begin 속성이 보입니다. 이 속성은 부모 레이아웃의 벽면에서 얼마나 떨어뜨려 배치할지 지정하는 속성 중에 하나입니다. 
- 부모 레이아웃의 벽면에서 얼마나 떨어뜨릴 건지 지정하려면 다음과 같이 세 가지 속성 중의 하나를 사용합니다.

|속성|설명|
|----|----------|
|layout_constraintGuide_begin|세로 방향인 경우 왼쪽부터, 가로 방향인 경우 위쪽부터의 거리 지정|
|layout_constraintGuide_end|세로 방향인 경우 오른쪽부터, 가로 방향인 경우 아래쪽부터 거리 지정|
|layout_constraintGuide_percent|layout_constraintGuide_begin|속성 대신 지정하되 % 단위로 거리 지정|

- 제약 조건을 설정하는 속성은 외부 라이브러리의 속성이므로 이름 앞에 모두 app: 접두어가 붙어 있습니다. 
- layout_constraintGuide_begin 속성 값이 50dp 이므로 왼쪽에서 부터 100dp 떨어진 곳에 배치됩니다.

## 크기를 표시하는 단위와 마진

- XML 레이아웃 파일의 코드를 살펴보면 거리 단위의 dp를 자주 사용하는 것을 알 수 있습니다. 이런 거리 단위는 dp 뿐만 아니라 sp, px등 여러가지가 있습니다.
- 뷰와 폭의 높이는 일반적으로 match_parent와 wrap_content 값을 지정하는 방식으로 사용하는데 그 이유는 단말마다 해상도나 화면의 크기가 달라도 전체 화면을 기준으로 뷰를 배치할 수 있기 때문입니다.
- 뷰의 크기를 픽셀 값으로 지정하면 해상도에 따라 크기가 다르게 보이지만 match_parent나 wrap_content를 사용하면 아주 쉽게 여러 단말의 해상도를 지원할 수 있습니다.

- 뷰의 폭과 높이를 정수 값으로 지정하면서 픽셀 단위인 px가 아니라 dp나 sp와 같은 단위를 사용하면 해상도가 다른 단말에서 뷰의 크기를 비슷하게 보이도록 만들 수 있습니다.

|단위|단위 표현|설 명|
|----|-----|----------|
|px|픽셀|화면의 픽셀 수|
|dp또는 dip|밀도 독립적 픽셀<br>(density independent pixel)|160dpi 화면을 기준으로 한 픽셀<br>예) 1인치 당 160개의 점이 있는 디스플레이 화면에서 1dp는 1px와 같음. 1인치당 320개의 점이 있는 디스플레이 화면에서 1dp는 2px와 같음.|
|sp또는 sip|축척 독립적 픽셀<br>(scale independent pixel)|텍스트 크기를 지정할 때 사용하는 단위, 가변 글꼴을 기준으로 한 픽셀로 dp와 유사하거나 글꼴의 설정에 따라 1sp당 픽셀 수가 달라짐.|
|in|인치|1인치로 된 물리적 길이|
|mm|밀리미터|1밀리미터로 된 물리적 길이|
|em|텍스트 크기|글꼴과 상관없이 동일한 텍스트 크기 표시|


- dp나 dip는 해상도에 비례하는 비슷한 크기로 보이게 할 때 사용합니다. 이 단위를 사용하면 해상도가 160dpi인 작은 화면에서는 20픽셀이던 버튼의 크기를 320dpi인 약간 큰 화면에서는 40픽셀의 크기로 보이게 합니다. 따라서 해상도별로 일일이 크기를 다시 지정하지 않아도 됩니다. 
- 이 단위들은 뷰의 크기 뿐만 아니라 텍스트의 크기를 지정하는 데도 사용합니다. sp또는 sip는 글꼴을 기준으로 한 텍스트 크기를 나타내므로 뷰의 크기에는 사용되지 않습니다. 글자를 표시하는 텍스트뷰나 버튼의 크기는 sp 단위 사용을 권장합니다. 사용자의 단말기 설정에서 글꼴이나 폰트 크기를 바꾸는 경우가 있는데 sp 단위를 사용해야 설정에 맞추어 글자 크기가 바뀌기 때문입니다.

> 실제로 앱 화면을 만들 때는 dp 단위를 주로 사용합니다. 안드로이드 단말은 다양한 화면 크기와 해상도를 가지고 있는데 dp 단위를 사용하면 단말의 해상도에 따라 비율로 픽셀 값이 계산되므로 대부분의 화면에서 비슷한 크기로 보이게 만들 수 있습니다. 텍스트의 크기를 지정할 때는 sp를 권장합니다. 텍스트의 크기에 dp를 지정한다고 해서 문제가 생기지는 않지만 sp 단위를 사용해야 단말의 글꼴 설정에 맞추어 표시되는 글자 크기가 바뀔 수 있습니다.

- 예를 들어, 일반적인 스마트폰 크기(3\~4인치)의 단말과 태블릿 크기(7\~10인치)의 단말은 화면 크기와 해상도가 다르지만 이 화면의 중앙에 버튼을 배치하고 화면의 절반 크기 정도로 보여주고 싶을 때, dp 단위를 쓰면 두 개 단말에서 비슷한 크기로 보이도록 자동으로 맞춰줍니다.

```xml
... 생략

	<Button
        android:id="@+id/button4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Button"
        app:layout_constraintStart_toStartOf="@+id/guideline2"
        app:layout_constraintTop_toBottomOf="@+id/button5" />

    <Button
        android:id="@+id/button5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Button"
        app:layout_constraintStart_toStartOf="@+id/guideline2"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button6"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Button"
        app:layout_constraintStart_toStartOf="@+id/guideline2"
        app:layout_constraintTop_toBottomOf="@+id/button4" />
		
... 생략

```

- 디자이너 도구에서 버튼을 추가하면 버튼의 id가 자동으로 만들어집니다. 코드에서 첫 번째와 두 번째 그리고 세 번째 버튼의 id가 각각 button4, button5, button6로 붙어 있지만 이 파일 안에서 고유한 다른 값으로 바꿔도 상관없습니다. 예를 들면 button, button2, button3으로 바꿔도 됩니다.
- 다만 이 id 값은 같은 파일 안에서 중복되면 안 됩니다. 또한 하나의 뷰에 설정한 id 값은 다른 뷰에서 참조하여 사용할 수 있으므로 이 id 값도 함께 변경되어야 합니다.

- 첫 번째 버튼을 보면 필수 속성인 layout_width와 layout_height 그리고 id 속성 외에 text 속성이 있습니다. 이 속성은 버튼에 글자를 넣을 때 사용됩니다. 그리고 그 아래에는 app:layout_constraintLeft_toLeftOf 속성이 있습니다. 이 속성을 해석하면 다음과 같습니다.

```
app:layout_constraintLeft_toLeftOf 속성
- 이 버튼의 왼쪽의 연결점과 타깃 뷰의 왼쪽 연결점을 연결하여 제약 조건을 만듭니다.
```

- 이 속성 값으로는 타깃 뷰의 id가 설정되어야 하는데 그 위에 있는 가이드라인을 타깃 뷰로 하므로 @+id/guideline이 설정됩니다. 또 다른 속성으로 layout_marginTop이 보입니다. 이 속성은 버튼의 위쪽을 얼마나 뛰울 것인가를 지정합니다.

|속성 이름|설 명|
|-----|--------|
|layout_marginTop|뷰의 위쪽을 얼마나 띄울지 지정함|
|layout_marginBottom|뷰의 아래쪽을 얼마나 띄울지 지정함|
|layout_marginLeft|뷰의 왼쪽을 얼마나 띄울지 지정함|
|layout_marginRight|뷰의 오른쪽을 얼마나 띄울지 지정함|
|layout_margin|뷰의 위, 아래, 왼쪽, 오른쪽을 얼마나 띄울지 한꺼번에 지정함|


- 프로젝트를 만들면 필요한 파일들이 자동으로 만들어집니다. 프로젝트가 만들어지는 기본 위치는 윈도우 사용자 계정을 폴더 아래에 AndroidStudioProjects 폴더 입니다. 프로젝트를 만들 때 다른 위치에 저장되도록 바꿀 수도 있지만 별도로 지정하지 않으면 이 폴더가 사용됩니다.

- 안드로이드 스튜디오의 프로젝트 창에는 실제 파일 경로를 보여주지 않고 중요한 파일의 위치만 정리해서 보여주는 [Android]가 기본으로 설정되어 있습니다. 따라서 파일 탐색기를 열고 파일을 찾을 때는 그 경로가 다르게 보일 수 있으니 주의해야 합니다. 
- 프로젝트 안에 만들어지는 소스 파일과 XML 레이아웃 파일, 그리고 이미지 파일 등의 위치는 다음과 같습니다.

|구분|저장 위치|
|-----|-------|
|소스 파일|프로젝트 창 : /app/java/\<패키지이름\>/\<파일이름\><br>파일 탐색기 : /app/src/main/java/\<패키지이름\>/\<파일이름\>|
|XML 레이아웃 파일|프로젝트 창 : /app/res/layout/\<파일이름\><br>파일 탐색기 : /app/src/main/res/layout\<파일이름\>|
|이미지 파일|프로젝트 창 : /app/res/drawable/\<파일이름\><br>파일 탐색기 : /app/src/main/res/drawable/\<파일이름\>|

- 만약 이미지 파일을 프로젝트 안에 추가하고 싶다면 파일 탐색기를 열고 프로젝트 폴더를 찾은 후 그 안에 있는 /app/src/main/res/drawable 폴더에 넣으면 됩니다. 다만 res 폴더 아래에 있는 폴더에 파일을 추가할 경우 파일의 이름에는 소문자와 숫자, 그리고 \_ 기호만 들어갈 수 있다는 점도 주의해야 합니다. 
- 또한 첫 글자는 숫자가 될 수 없습니다. 예를 들어, 이미지를 저장할 때 첫 글자를 대문자로 하면 앱이 빌드되거나 실행되는 과정에서 에러가 발생할 수 있습니다.
