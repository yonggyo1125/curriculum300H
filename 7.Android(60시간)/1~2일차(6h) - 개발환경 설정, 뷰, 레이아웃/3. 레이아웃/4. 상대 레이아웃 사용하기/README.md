# 상대 레이아웃 사용하기

- 상대 레이아웃으로 만들 수 있는 화면 레이아웃은 대부분 제약 레이아웃으로 만들 수 있습니다. 따라서 상대 레이아웃을 사용하는 것은 권항하지 않습니다. 다만 예전에 만든 레이아웃이 상대 레이아웃을 사용한 경우가 있기 때문에 상대 레이아웃에 대해 이해하는 것은 필요합니다.

- 상대 레이아웃은 부모 컨테이너나 다른 뷰와의 상대적인 위치를 이용해 뷰의 위치를 결정할 수 있도록 합니다. 예를 들어, 버튼의 아래쪽에 또 다른 버튼을 배치하고 싶을 때 이미 추가되어 있는 버튼의 아래쪽에 붙여 달라는 속성을 XML 레리아웃에서 설정할 수 있습니다. 이 경우 이미 추가되어 있는 버튼이 어떤 것인지 지정해야 하는데 그 버튼의 id 속성 값이 사용됩니다.

![image44](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image44.png)

- 안드로이드 스튜디오 시작화면에서 [New Project] 메뉴를 눌러 새로운 프로젝트를 만듭니다. 프로젝트 첫 화면의 유형은 Empty Activity를 그대로 두고 [Next] 버튼을 누른 다음 프로젝트의 이름에는 SampleRelativeLayout을 입력합니다. [Finish] 버튼을 눌러 새로운 프로젝트를 만듭니다.

- 프로젝트 창이 보이면 activity_main.xml 파일을 열고 가운데 있는 'Hello World!' 글자를 삭제합니다. 그리고 좌측 하단의 Component Tree 창에서 최상위 레이아웃인 ContraintLayout을 선택합니다. 마우스 오른쪽 버튼을 눌러 [Convert view ...] 메뉴를 선택하고 대화상자가 표시되면 RelativeLayout을 선택하고 [Apply] 버튼을 누릅니다.

- 좌측 상단의 팔레트 창에서 버튼 두 개를 끌어다 화면에 추가합니다. 첫 번째 버튼은 부모 레이아웃의 좌측 상단에 붙인 후 layout_width와 layout_height 속성을 모두 match_parent로 설정하여 화면을 채우도록 만듭니다. 두 번째 버튼은 부모 레이아웃의 좌측 하단에 붙인 후 layout_width 속성은 match_parent, layout_height 속성은 wrap_content로 설정합니다. 
- 버튼을 끌어다 놓는 과정에서 layout_margin 값이 자동으로 설정되는 경우가 있는데 자동으로 설정되었다면 layout_margin의 값은 Delete를 눌러 모두 삭제하고 layout_alignParentBottom의 값이 true로 되어 있는지 확인합니다.

![image45](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image45.png)

- 이렇게 상대 레이아웃 안에 들어가는 뷰는 부모 레이아웃의 가운데 위치시킬 수도 있고, 상하좌우로 드래그해서 붙일 수도 있습니다. 그런데 부모 레이아웃의 아래쪽에 붙여놓은 버튼이 화면 전체를 채우는 첫 번째 버튼의 위에 올라와 있습니다. 따라서 첫 번째 버튼의 일부를 가리게 됩니다. 만약 가운데 보이는 버튼의 영역이 아래쪽에 있는 버튼 위쪽까지만 차지하게 만들려면 다음과 같이 하시면 됩니다.
- 버튼 색상을 눈에 잘 띄는 색으로 바꾸기 위해 첫 번째 버튼의 background 속성 값은 #0088ff, backgroundTint 속성 값은 #00000000, backgroundTintMode 속성 값은 Add로 설정합니다.

- 그리고 첫 번쨰 버튼에 layout_above 속성을 찾아 두 번쨰 버튼의 id를 지정합니다. 이렇게 하면 첫 번째 버튼은 두 번째 버튼의 바로 윗부분까지만 공간을 차지합니다. 마찬가지로 layout_margin의 속성값이 아직 남아 있다면 [Delete]를 눌러 모두 삭제합니다.

![image46](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image46.png)

- 이번에는 제목이 표시되는 위쪽 공간에도 버튼을 배치해 보겠습니다. 디자이너 도구 화면에 보이는 레이아웃 버튼을 하나 더 추가합니다. 그리고 화면의 위쪽으로 끌어다 붙인 후 layout_width의 속성 값은 match_parent, layout_height 속성의 값은 wrap_content로 설정합니다. 첫 번째 버튼(파란색)이 차지하는 영역이 위쪽에 붙여놓은 버튼의 아래쪽까지만 차지하도록 하려면 우선 첫 번째 버튼(파란색)에 layout_below 속성 값으로 세 번째 버튼의 id 값을 지정합니다. 그런 다음 layout_alignParentTop 속성 값을 없앱니다.

- 이렇게 만든 레이아웃을 화면이 어떻게 보이는지 확인해 보면 위와 아래의 일부 공간을 뺀 나머지 부분을 첫 번째 버튼이 꽉 채운 모양입니다.

![image47](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image47.png)

- 이런 모양은 화면 레이아웃을 만들 때 가장 많이 사용되는 형태입니다. 가운데 부분에 리스트나 이미지 등을 보여주고 위와 아래에 필요한 정보나 위젯들을 표시하게 됩니다.

- 상대 레이아웃에서 부모 레이아웃과의 상대적 위치를 이용해 뷰를 배치할 수 있는 속성들은 다음과 같습니다.

|속성|설명|
|-----|-------|
|layout_alignParentTop|부모 컨터이너의 위쪽과 뷰의 위쪽을 맞춤|
|layout_alignParentBottom|부모 컨테이너의 아래쪽과 뷰의 아래쪽을 맞춤|
|layout_alignParentLeft|부모 컨테이너의 왼쪽 끝과 뷰의 왼쪽 끝을 맞춤|
|layout_alignParentRight|부모 컨테이너의 오른쪽 끝과 뷰의 오른쪽 끝을 맞춤|
|layout_centerHorizontal|부모 컨테이너의 수평 방향 중앙에 배치함|
|layout_centerVertical|부모 컨테이너의 수직 방향 중앙에 배치함|
|layout_centerInParent|부모 컨테이너의 수평과 수직 방향 중앙에 배치함|
|layout_above|지정된 뷰의 위쪽에 배치함|
|layout_below|지정된 뷰의 아래쪽에 배치함|
|layout_toLeftOf|지정된 뷰의 왼쪽에 배치함|
|layout_toRightOf|지정된 뷰의 오른쪽에 배치함|
|layout_alignTop|지정된 뷰의 위쪽과 맞춤|
|layout_alignBottom|지정된 뷰의 아래쪽과 맞춤|
|layout_alignLeft|지정된 뷰의 왼쪽과 맞춤|
|layout_alignRight|지정된 뷰의 오른쪽과 맞춤|
|layout_alignBaseline|지정된 뷰와 내용물의 아래쪽 기준선(baseline)을 맞춤|

- 안드로이드 스튜디오에서 XML 레이아웃 파일을 열고 코드를 직접 편집할 때는 각각의 태그가 갖는 속성을 미리 보여주므로 이런 속성들을 모두 외워둘 필요는 없습니다. 다만 실제 앱을 만들 때는 짧은 시간 안에 화면을 구성해야 하므로 자주 사용되는 속성들은 기억하고 있는 것이 좋습니다.

- 상대 레이아웃을 사용할 때도 상대 레이아웃만드로 화면 레이아웃을 만들지는 않습니다. 상대 레이아웃과 리니어 레이아웃 또는 다른 레이아웃을 함께 사용하면 화면을 좀 더 쉽게 구성할 수 있습니다.