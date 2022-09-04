# 드로어블 만들기

## 뷰의 배경 이미지

- 뷰의 background 속성은 배경색을 설정하거나 이미지 파일을 설정할 때 사용합니다. 그런데 이미지를 배경으로 설정하면 그 이미지는 아무런 변화가 없습니다. 예를 들어 버튼의 배경으로 이미지를 설정하면 버튼이 눌리거나 눌리지 않았을 때를 구분할 수 있어야 하는데 동일한 이미지가 보여 구분이 되지 않는 문제가 생깁니다. 만약 버튼이 눌렸을 때 눌린 이미지가 보이게 하고 싶다면 드로어블(Drawable)을 사용하면 됩니다. 드로어블은 상태에 따라 그래픽이나 이미지가 선택적으로 보이게 할 수 있게 해줍니다.

- SampleDrawable이라는 이름의 새로운 프로젝트를 만듭니다. 그런 다음 activity_main.xml 파일을 열어 화면에서 가운데 있는 텍스트뷰를 삭제하고 새 버튼을 화면 한가운데에 추가하세요. 이미지 파일은 학습 소스를 참고합니다. 하나는 finger.png이고 다른 하나는 finger_pressed.png입니다. 직접 다른 이미지 파일 두 개를 만들어 사용해도 됩니다. 두 이미지 파일을 /app/res/  drawable 폴더에 복사하세요.
- 만약 윈도우 파일 탐색기에서 복사한다면 /app/src/main/res/drawable 폴더에 복사해야 합니다.

- 이제 복사한 이미지를 화면 중앙에 추가한 버튼의 배경으로 설정하겠습니다. 버튼이 선택된 상태에서 오른쪽 속성 창을 보면 background 속성을 찾습니다. 속성 입력창 오른쪽 끝에 있는 [...] 버튼(Pick a Resource)을 누르면 이미지를 선택할 수 있는 [Resource] 대화상자가 나타납니다.

- 대화상자 왼쪽의 탭 항목 중에서 가장 위쪽에 있는 [Drawable] 탭이 선택되어 있습니다. [Drawable]탭은 이미지나 드로어블을 선택할 때 사용되는 탭으로 오른쪽에는 Project, android 폴더 등으로 나누어져 있습니다. Project 폴더는 여러분이 만든 프로젝트에 포함된 이미지나 드로어블을 가리키며, android 폴더는 안드로이드 기본 API에 포함된 이미지나 드로어블을 가리킵니다. Project 폴더 안에 넣은 finger 이미지를 선택한 후 [OK] 버튼을 누르면 버튼의 배경 이미지로 설정됩니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/2.%20%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image1.png)

- 그런 다음 버튼의 text 속성 창에서 Button이라는 글자를 삭제하세요. 그래야 이미지만 보이는 버튼이 됩니다. text 속성의 값을 삭제한 후 에뮬레이터로 실행하면 다음과 같은 화면이 나타납니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/2.%20%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image2.png)

- 앱이 실행되면 이미지 버튼이 나타납니다. 하지만 버튼을 눌러도 모양은 그대로 유지됩니다. 그래서 버튼이 아니라 이미지뷰처럼 느껴지죠. 이제 드로어블을 사용해서 버튼이 눌렸을 때 다른 이미지가 보이도록 만들겠습니다.

## 드로어블

- 드로어블은 뷰에 설정할 수 있는 객체이며 그 위에 그래픽을 그릴 수 있습니다. 그래픽은 흔히 원이나 선을 그리는 것을 말하죠. 이런 작업은 보통 소스 코드에서 작성하게 됩니다. 하지만 소스 코드가 아닌 XML로 그래픽을 그릴 수 있다면 좀 더 편리할 겁니다.

- 드로어블 XML 파일은 이미지를 버튼 배경으로 설정한 것처럼 /app/res/drawable 폴더 안에 넣어 버튼(뷰)의 배경으로 설정할 수 있습니다. 즉, drawable 폴더 안에 이미지가 아닌 XML 파일이 들어가 이미지처럼 설정되는 것입니다.

-  드로어블에는 이미지 파일을 보여줄 때 사용하는 <b>비트맵 드로어블(BitmapDrawable)</b>, 상태별로 다른 그래픽을 참조할 수 있는 <b>상태 드로어블(StateListDrawable)</b>, 두 개의 드로어블이 서로 바뀌도록 만들 수 있는 <b>전환 드로어블(TransitionDrawable)</b>, 색상과 그러데이션을 포함하여 도형 모양을 정의할 수 있는 <b>셰이프 드로어블(ShapeDrawable)</b> 등이 있습니다. 지정한 거리만큼 안쪽으로 들어오도록 만들 수 있는 <b>인셋 드로어블(InsetDrawable)</b>이나 다른 드로어블을 클리핑하는 <b>클립 드로어블(ClipDrawable)</b>, 다른 드로어블의 크기를 바꿀 수 있는 <b>스케일 드로어블(ScaleDrawable)</b>도 있습니다.

|드로어블|설명|
|----|------|
|비트맵 드로어블(BitmapDrawable)|이미지 파일을 보여줄 때 사용함<br>비트맵 그래픽 파일(png, jpg, gif 등)을 사용해서 생성함|
|상태 드로어블(StateListDrawable)|상태별로 다른 비트맵 그래픽을 참조함|
|전환 드로어블(TransitionDrawable)|두 개의 드로어블을 서로 전환할 수 있음|
|세이프 드로어블(ShapeDrawable)|색상과 그라데이션을 포함하여 도형 모양을 정의할 수 있음|
|인셋 드로어블(InsetDrawable)|지정된 거리만큼 다른 드로어블을 들어서 보여줄 수 있음|
|클립 드로어블(ClipDrawable)|레벨 값을 기준으로 다른 드로어블을 클리핑할 수 있음|
|스케일 드로어블(ScaleDrawable)|레벨 값을 기준으로 다른 드로어블의 크기를 변경할 수 있음|

- 다양한 기능의 드로어블이 있지만 앱을 만들 때 가장 많이 사용하는 드로어블은 상태 드로어블과 셰이프 드로어블입니다.

## 상태 드로어블 만들기

- 상태 드로어블은 뷰의 상태에 따라 뷰에 보여줄 그래픽을 다르게 지정할 수 있습니다. 왼쪽 프로젝트창에서 /app/res/drawable 폴더를 선택한 후 마우스 오른쪽 버튼을 누릅니다. 메뉴가 보이면 [New→ Drawable resource file]을 선택합니다. 이 메뉴는 새로운 드로어블 XML 파일을 만들 수 있게 도와줍니다. [New Resource File] 대화상자의 File name:에 finger_drawable.xml을 입력하고 [OK] 버튼을 누릅니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/2.%20%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image3.png)

- finger_drawable.xml 파일 안에서 \<selector\> 태그를 확인할 수 있습니다. 최상위 태그인 \<selector\>태그 안에는 \<item\> 태그를 넣을 수 있으며 \<item\> 태그의 drawable 속성에는 이미지나 다른 그래픽을 설정하여 화면에 보여줄 수 있습니다. state_로 시작하는 속성은 상태를 나타내는데 간단한 예로 state_pressed 속성은 눌린 상태를 의미하고 state_focused는 포커스를 받은 상태를 의미합니다.

- finger_drawable.xml 파일 안의 소스 코드를 다음과 같이 수정합니다.

#### SampleDrawable>/app/res/drawable/finger_drawable.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true"
            android:drawable="@drawable/finger_pressed" />

    <item android:drawable="@drawable/finger" />
    
</selector>
```

- 상태 속성이 설정되지 않은 \<item\> 태그에는 drawable 속성 값으로 @drawable/finger를 입력했습니다. 이렇게 하면 finger.png 이미지가 보이게 됩니다. state_pressed라는 상태 속성이 설정된 \<item\>태그에는 drawable 속성 값으로 @drawable/finger_pressed를 입력했습니다. 이 이미지는 뷰가 눌렸을 때 보이게 됩니다.

- 이렇게 만든 XML 파일은 뷰의 background 속성으로 설정할 수 있습니다. activity_main.xml 파일에서 버튼을 선택한 후 background 속성의 값을 @drawable/finger_drawable로 변경합니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/2.%20%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image4.png)

- 앱을 실행하여 버튼을 눌러보세요. 그러면 배경 이미지가 바뀌는 것을 확인할 수 있습니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/2.%20%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image5.png)

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/2.%20%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image6.png)

## 셰이프 드로어블 만들기

- 이번에는 XML로 도형을 그릴 수 있게 해주는 셰이프 드로어블을 만들어 보겠습니다. 왼쪽 프로젝트 창에서 /app/res/drawable 폴더를 선택한 후 마우스 오른쪽 버튼을 눌러 [New Drawable resourcefile] 메뉴를 누릅니다. 대화상자가 보이면 File name 에 rect_drawable.xml을 입력하고 [OK] 버튼을 눌러 새로운 파일을 만듭니다. rect_drawable.xml 파일이 열리면 다음과 같이 소스 코드를 수정합니다.

#### SampleDrawable>/app/res/drawable/rect_drawable.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

    <size android:width="200dp" android:height="120dp" />
    <stroke android:width="1dp" android:color="#0000ff" />
    <solid android:color="#aaddff" />
    <padding android:bottom="1dp" />
    
</shape>
```

- 이때 최상위 태그는 \<selector\> 태그에서 \<shape\> 태그로 변경했으며 shape라는 속성을 추가하여 속성 값으로 rectangle을 입력했습니다. 이렇게 하면 사각형을 그릴 수 있습니다. 만약 shape 속성에 oval을 입력하면 타원을 그릴 수 있습니다. \<shape\> 속성 안에 있는 \<size\> 태그는 도형의 크기를 지정하는 태그로 여기서는 가로가 200dp, 세로가 120dp로 설정되었습니다. \<stroke\> 태그는 테두리 선의 속성을 지정할 수 있으며 width는 선의 굵기, color는 선의 색상을 설정할 때 사용됩니다. \<solid\> 태그는 도형의 안쪽을 채울 때 사용합니다. \<padding\> 태그는 테두리 안쪽 공간을 띄우고 싶을 때 사용합니다. 여기서는 bottom 속성을 사용해 아래쪽에만 padding 속성을 부여했습니다.

- 이제 rect_drawable.xml 파일을 이용하여 도형을 화면에 표시할 차례입니다. activity_main.xml 파일을 열고 디자인 화면에서 새 버튼을 가운데 있는 버튼의 아래쪽에 배치하세요. 이때 버튼의 background 속성을 @drawable/rect_drawable로 설정하고 backgroundTint 속성 값은 #00000000, backgroundTintMode 속성 값은 Add로 설정하면 버튼의 모양이 바뀌는 것을 확인할 수 있습니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/2.%20%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image7.png)

- 이번에는 앱의 배경색을 바꿔볼까요? 앱의 배경색에 그러데이션 효과를 주고 싶다면 \<gradient\> 태그를 사용하면 됩니다. /app/res/drawable 폴더 안에 back_drawable xml 파일을 만들고 다음과 같이 입력해 보세요.

#### SampleDrawable>/app/res/drawable/back_drawable.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">

    <gradient
        android:startColor="#7288D8"
        android:centerColor="#3250B4"
        android:endColor="#254095"
        android:angle="90"
        android:centerY="0.5"
        />

    <corners android:radius="2dp" />

</shape>
```

- \<shape\> 태그 안에 \<gradient\> 태그를 넣으면 그러데이션이 만들어집니다. startColor에는 시작 부분의 색상, centerColor에는 가운데 부분의 색상, endColor에는 끝 부분의 색상을 지정할 수 있습니다.이렇게 하면 위쪽에서부터 아래쪽으로 색상이 바뀌는 그러데이션 모양이 생깁니다.

- activity_main.xml 파일을 열고 디자인 화면의 좌측 하단에 있는 Component Tree 창에서 최상위 레이아웃인 ConstraintLayout을 선택합니다. 그리고 오른쪽 속성 창에서 background 속성 값을 @drawable/back_drawable로 설정합니다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/2.%20%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image8.png)

- 최상위 태그의 background 속성을 설정하면 화면 전체의 배경이 바뀌게 됩니다. 화면 전체의 배경이그러데이션으로 바뀌니 앱의 분위기가 많이 달라진 것을 느낄 수 있습니다.

- 이번에는 버튼의 배경을 투명하게 만들어 테두리만 있는 버튼을 만들어 보겠습니다. 앱 화면을 좀 더간결하게 보이도록 만들 때는 버튼 테두리만 보이게 만드는 방법을 많이 사용합니다. 이때는 드로어블을 만들어 배경으로 설정하면 됩니다. 만약 버튼의 테두리만 보이는 드로어블을 만들고 싶다면 도형 안쪽은 투명하게 채우고 테두리 선에만 색상을 지정하면 됩니다.

- \<layer-list\> 태그를 사용하면 여러 개의 그래픽을 하나의 XML 파일에 넣을 수도 있습니다. 하나의 그래픽으로 정의할 것인지 아니면 여러 개의 그래픽으로 정의할 것인지는 선택의 문제입니다. 하지만 여러 개의 그래픽으로 나누어서 중첩시키면 좀 더 예쁜 배경을 만들 수 있습니다.

- 이제 테두리만 있는 버튼을 만들어 보겠습니다. /app/res/drawable 폴더에 border_drawable.xml이라는 새로운 XML 파일을 만들어 다음을 입력해 보세요.

#### SampleDrawable>/app/res/drawable/bordor_drawable.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <item>
        <shape android:shape="rectangle">
            <stroke android:width="1dp" android:color="#BE55DA" />
            <solid android:color="#00000000" />
            <size android:width="200dp" android:height="100dp" />
        </shape>
    </item>

    <item android:top="1dp" android:bottom="1dp"
            android:right="1dp" android:left="1dp">
        <shape android:shape="rectangle">
            <stroke android:width="1dp" android:color="#FF55DA" />
            <solid android:color="#00000000" />
        </shape>
    </item>
    
</layer-list>
```

- \<layer-list\> 태그 안에는 두 개의 \<item\> 태그가 있습니다. 첫 번째 \<item\> 태그는 \<shape\> 태그를 포함하고 있고 \<shape\> 태그 속성 값으로 rectangle을 지정하여 사각형을 그렸습니다. 두 번째 \<item\> 태그에는 top, bottom, right, left 속성을 지정했습니다. 이 값은 뷰의 테두리 선으로부터 바깥으로 얼마만큼 공간을 띄울 것인지 정합니다. \<shape\> 태그 안에 있는 \<stroke\> 태그는 테두리 선과 관련 있는 태그입니다. 이 태그의 color 속성 값을 각각 다르게 넣어 테두리 색상을 서로 다르게 표시했습니다. 마지막으로 \<solid\> 태그의 color 속성 값으로 #0000000을 설정해 뷰의 안쪽 공간을 투명하게 만들었습니다.

- 드로어블을 만들었으니 이제 화면에 추가할 차례입니다. activity_main.xml 파일을 연 다음 디자인 화면에서 가운데 버튼의 위쪽에 새로운 버튼을 추가하세요. 그런 다음 버튼의 background 속성 값을 @drawable/border_drawable로 설정하고 backgroundTint 속성 값은 #00000000, backgroundTintMode 속성 값은 Add로 설정하세요. 그러면 다음과 같은 디자인 화면을 볼 수 있습니다.

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/2.%20%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image9.png)

- 이렇게 드로어블은 이미지나 그래픽을 사용해서 좀 더 다양한 효과를 줄 수 있어 실제 앱을 만들 때도 자주 사용합니다.


