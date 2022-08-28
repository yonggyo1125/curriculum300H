# 상대 레이아웃 사용하기

- 상대 레이아웃으로 만들 수 있는 화면 레이아웃은 대부분 제약 레이아웃으로 만들 수 있습니다. 따라서 상대 레이아웃을 사용하는 것은 권항하지 않습니다. 다만 예전에 만든 레이아웃이 상대 레이아웃을 사용한 경우가 있기 때문에 상대 레이아웃에 대해 이해하는 것은 필요합니다.

- 상대 레이아웃은 부모 컨테이너나 다른 뷰와의 상대적인 위치를 이용해 뷰의 위치를 결정할 수 있도록 합니다. 예를 들어, 버튼의 아래쪽에 또 다른 버튼을 배치하고 싶을 때 이미 추가되어 있는 버튼의 아래쪽에 붙여 달라는 속성을 XML 레리아웃에서 설정할 수 있습니다. 이 경우 이미 추가되어 있는 버튼이 어떤 것인지 지정해야 하는데 그 버튼의 id 속성 값이 사용됩니다.

![image44](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image44.png)

- 안드로이드 스튜디오 시작화면에서 [New Project] 메뉴를 눌러 새로운 프로젝트를 만듭니다. 프로젝트 첫 화면의 유형은 Empty Activity를 그대로 두고 [Next] 버튼을 누른 다음 프로젝트의 이름에는 SampleRelativeLayout을 입력합니다. [Finish] 버튼을 눌러 새로운 프로젝트를 만듭니다.

- 프로젝트 창이 보이면 activity_main.xml 파일을 열고 가운데 있는 'Hello World!' 글자를 삭제합니다. 그리고 좌측 하단의 Component Tree 창에서 최상위 레이아웃인 ContraintLayout을 선택합니다. 마우스 오른쪽 버튼을 눌러 [Convert view ...] 메뉴를 선택하고 대화상자가 표시되면 RelativeLayout을 선택하고 [Apply] 버튼을 누릅니다.

- 좌측 상단의 팔레트 창에서 버튼 두 개를 끌어다 화면에 추가합니다. 첫 번째 버튼은 부모 레이아웃의 좌측 상단에 붙인 후 layout_width와 layout_height 속성을 모두 match_parent로 설정하여 화면을 채우도록 만듭니다. 두 번째 버튼은 부모 레이아웃의 좌측 하단에 붙인 후 layout_width 속성은 match_parent, layout_height 속성은 wrap_content로 설정합니다. 
- 버튼을 끌어다 놓는 과정에서 layout_margin 값이 자동으로 설정되는 경우가 있는데 자동으로 설정되었다면 layout_margin의 값은 Delete를 눌러 모두 삭제하고 layout_alignParentBottom의 값이 true로 되어 있는지 확인합니다.

![image45](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image45.png)


