# 리니어 레이아웃 사용하기

- 다섯 가지 대표적인 레이아웃 중에서 가장 쉬운 레이아웃은 리니어 레이아웃입니다.
- 리니어 레이아웃을 이해하면 다른 레이아웃도 쉽게 이해할 수 있습니다.
- 특히 리니어 레이아웃에 리니어 레이아웃을 포함시키는 방식을 공부하고 나면 대부분의 레이아웃은 문제없이 구성할 수 있습니다.

## 리니어 레이아웃의 방향 설정하기

- 한 방향으로만 뷰를 쌓는 리니어 레이아웃의 필수 속성은 방향입니다. 방향을 설정할 때는 orientation 속성을 사용하여 가로 방향은 horizontal, 세로 방향은 vertical이라는 값으로 설정합니다.
- 최상위 레이아웃을 LinearLayout으로 바꿔서 사용해도 됩니다.

> 새로운 프로젝트를 만들면 첫 화면에 필요한 XML 레이아웃 파일이 생깁니다. 그리고 그 안에 최상위 레이아웃으로 ConstraintLayout이 자동으로 들어갑니다. 이것을 LinearLayout으로 바꾸려면 좌측 하단의 Component Tree 창에서 ContraintLayout을 선택한 후 마우스 오른쪽 버튼을 누릅니다. 메뉴가 보이면 [Convert view ...] 메뉴를 눌러서 변경하면 됩니다.

- 새 SampleLinearLayout 프로젝트를 만듭니다. 첫 화면의 유형은 기본 값인 Empty Activity를 그대로 사용하고 그다음 화면에서는 프로젝트의 이름으로 SampleLinearLayout을 입력합니다. [Finish] 버튼을 누르면 새로운 프로젝트 창이 열립니다.

- 가운데 작업 영역의 [activity_main.xml] 탭을 선택한 다음 디자인 화면 가운데 있는 'Hello World' 글자를 삭제합니다. 그리고 좌측 하단의 Component Tree 창에서 최상위 레이아웃인 ConstraintLayout을 선택합니다. 마우스 오른쪽 버튼을 눌러서 [Convert view...] 메뉴를 누르세요. 대화상자가 표시되면 LinearLayout을 선택한 후 [Apply] 버튼을 누릅니다. 그러면 화면 전체를 채우는 최상위 레이아웃이 리니어 레이아웃으로 변경됩니다.
- 리니어 레이아웃의 방향 속성에 따라 뷰를 채워나가는 방향이 결정되므로 orientation 속성의 값을 선택해야 합니다. LinearLayout이 선택된 상태에서 오른쪽 속성창을 보면 orientation 속성이 보입니다. 이 속성 값을 vertical로 선택합니다.

![image26](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image26.png)

- 리니어 레이아웃 안에 버튼 세 개를 순서대로 추가합니다. 좌측 상단의 팔레트에서 버튼을 끌어다 차례대로 놓습니다.
- 버튼을 끌어다 놓으면 각 버튼의 layout_width 속성 값은 match_parent, layout_height 속성 값은 wrap_content로 설정됩니다.

![image27](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image27.png)

- 차례대로 추가한 버튼의 id는 자동으로 button, button2, button3이 부여됩니다. 그리고 버튼 위에 button이라는 글자가 표시되도록 text 속성 값으로 Button이 입력되어 있습니다.

- LinearLayout의 필수 속성은 layout_width, layout_height 그리고 orientation 속성입니다. 앱을 애뮬레이터로 실행하면 디자인 화면에서 보이는 모양 그래도 앱 화면에 세 개의 버튼이 나타납니다. 

![image28](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image28.png)

- 가로 방향으로 버튼이 쌓이게 많들고 싶다면 간단히 리니어 레이아웃의 orientation 속성 값만 horizontal로 바꾸면 됩니다. 
- 디자인 화면에서 화면 전체를 나타내고 있는 LinearLayout을 선택하고 오른쪽 속성 창에서 orientation 속성을 찾아 horizontal로 값을 바꿔줍니다. 또는 오른쪽 위에 있는 [Code] 아이콘을 눌러 XML 원본 코드가 보이도록 한 후 다음 코드처럼 android:orientation 속성 값을 "horizontal"로 수정해도 됩니다.

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal"
    tools:context=".MainActivity" >
```

- orientation 속성을 바꾼 후에 실행해보면 다음과 같은 화면을 볼 수 있습니다. 에뮬레이터로 실행한 앱 화면을 보면 세 개의 버튼 중 첫 번째 버튼만 보입니다. layout_width나 layout_height 속성 값을 match_parent로 하면 부모 레이아웃에 남아 있는 여유 공간을 모두 채운다고 했습니다. 첫 번째 버튼이 갖는 layout_width 속성 값이 match_parent로 되어 있으므로 첫 번째 버튼이 가로 공간을 모두 차지한 것입니다. 이 떄문에 가로 방향으로 버튼을 쌓아가는 경우에는 첫 번째 버튼이 부모 컨테이너의 공간을 모두 차지하게 되고 나머지 두 개 버튼을 추가할 수 있는 공간은 없게 된 것입니다. 결과적으로 버튼은 세 개가 추가되었지만 나머지 버튼은 화면에서 보이지 않게 되어버린 것입니다.

![image29](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image29.png)

- 처음 레이아웃을 접할 때는 왜 이렇게 되는지 이해하지 못하는 경우가 많습니다. 따라서 리니어 레이아웃의 방향 속성과 함께 layout_width, layout_height 속성을 이해하는 것이 매우 중요합니다.
- 리니어 레이아웃 방향 속성을 horizontal로 설정했을 때도 세 개의 버튼이 모두 보이게 하려면 버튼 세 개의 layout_width 속성 값을 모두 wrap_content로 바꾼 후 다시 실행하면 버튼의 폭이 그 안에 들어 있는 글자의 크기에 맞춰지므로 다음 그림처럼 버튼 세 개가 모두 나타납니다. 각각의 버튼을 Component Tree 창에서 선택한 다음 속성 창에서 layout_width 속성 값을 wrap_content로 바꿔보세요.

![image30](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image30.png)

> wrap_content는 뷰 안에 들어 있는 내용물의 크기에 맞게 뷰의 크기를 정해준다고 했습니다. 텍스트뷰나 버튼의 경우에는 내용물이 텍스트(글자)가 되고 이미지를 보여주는 뷰의 경우에는 내용물이 이미지가 됩니다. 물론 패딩 값을 조절하면 테두리선과 내용물의 간격을 띄울 수도 있습니다.

## 자바 코드에서 화면 구성하기 

- 지금까지 디자인 화면에서 리니어 레이아웃을 만들고 리니어 레이아웃에 3개의 버튼을 추가했습니다. 그러면 XML 파일이 만들어집니다. 이렇게 만들어진 XML 파일은 화면을 위해 만들어진 소스 파일과 연결됩니다. [MainActivity.java] 탭을 눌러서 자바 파일을 열어보면 setContentView 메서드를 호출하는 부분이 있습니다. 이 메서드를 호출하면서 activity_main.xml 파일을 파라미터로 전달하면 이 레이아웃 파일이 액티비티라 불리는 소스 파일과 연결됩니다. 이 두 개의 파일이 서로 연결되어 하나의 화면을 만들게 되는 것입니다. 

- 즉, 화면에 보이는 레이아웃 소스 코드와 자바 소스 코드는 서로 분리되어 있습니다. 화면 모양을 결정하는 파일과 화면에 기능을 부여하는 소스 파일이 분리되어 있으면 여러 가지 장점이 생깁니다. 특히 화면의 모양을 바꿀 때 자바 소스 코드는 그래도 두고 레아우웃 소스 코드만 수정할 수 있게 됩니다.

- 그러나 화면 레이아웃을 미리 만들 수 없는 경우 또는 필요할 때마다 바로바로 레이아웃을 만들어야 하는 경우에는 자바 소스 코드에서 화면 레이아웃을 구성해야 할 수도 있습니다. 예를 들어, 사용자가 입력한 데이터나 파일에서 읽어 들인 데이터 또는 인터넷을 통해 다른 곳에서 받아온 데이터의 유형에 따라 화면의 구성을 바꾸고 싶다면 XML로 정의하는 것보다 자바 코드에서 화면을 구성하는 것이 훨씬 더 효율적입니다.

- XML로 만든 화면을 자바 코드로 직접 만들기 위해 MainActivity.java 파일을 복사해서 LayoutCodeActivity.java 파일을 만든 후 수정해 보겠습니다. MainActivity.java 파일을 복사할 때는 프로젝트 안에 들어 있는 src 폴더를 열고 org.koreait.samplelinearlayout 패키지 안에 들어 있는 MainActivity라는 java 파일을 선택한 후 Ctrl + C를 눌러 복사했다가 Ctrl + V를 눌러 붙여넣기를 합니다. 붙여넣기를 할 때 같은 이름의 파일이 이미 있으므로 이름을 바꾸라는 대화상자가 나타나면 LayoutCodeActivity를 입력한 후 [OK]를 누릅니다.

![image31](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image31.png)

다음은 앞에서 만든 레이아웃을 자바 코드로 구성한 것입니다. 이 코드를 LayoutCodeActivity.java 파일 안에 입력합니다.

```java
package org.koreait.samplelinearlayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class LayoutCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // new 연산자로 리니어 레이아웃을 만들고 방향을 설정
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        // new 연산자로 레이아웃 안에 추가될 뷰들에 설정할 파라미터 생성
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // 버튼에 파라미터 설정하고 레이아웃에 추가
        Button button1 = new Button(this);
        button1.setText("Button1");
        button1.setLayoutParams(params);
        mainLayout.addView(button1);

        // 새로 만든 레이아웃을 화면에 설정
        setContentView(mainLayout);
    }
}
```
- 하나의 화면은 액티비티라고 부릅니다. 앱을 실행했을 때 처음 보이는 화면은 메인 액티비라고 부릅니다. 즉, 프로젝트를 처음 만들면 자동으로 메인 액티비티가 만들어지고 그 액티비티를 위한 자바 소스 파일의 이름은 MainActivity.java가 됩니다. 그리고 이 메인 액티비티는 AndroidManifest.xml 파일 안에 자동으로 등록됩니다. 이 메인 액티비티를 여러분이 새로 추가한 LayoutCodeActivity로 변경하면 앱을 실행했을 때 새로 만든 액티비티가 나타나게 됩니다.

#### SampleLinearLayout > app > manifests > AndroidManifest.xml 

```xml
<activity
	android:name=".LayoutCodeActivity"
    android:exported="true">
	<intent-filter>
		<action android:name="android.intent.action.MAIN" />
		<category android:name="android.intent.category.LAUNCHER" />
	</intent-filter>
</activity>
```

- \<activity\> 태그 안에 있는 android:name 속성 값을 ".MainActivity"에서 ".LayoutCodeActivity"로 변경했습니다. 이렇게 바꾼 후 실행하면 버튼 하나가 들어간 화면을 볼 수 있습니다. 이 때 버튼의 글자를 소문자로 입력했다면 자동으로 대문자로 바뀌어 표시합니다.

![image32](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image32.png)

## 화면 생성 과정 분석하기

- LayoutCodeActivity.java 소스 코드를 보면 onCreate 메서드 안에 setContentView 메서드를 호출하는 부분이 들어 있습니다. 그런데 setContentView 메서드의 소괄호 안에 R.layout.activity_main과 같이 레이아웃 파일을 가리키도록 설정하지 않고 자바 코드에서 만든 레이아웃 객체를 가리키도록 수정했습니다. 이렇게 레이아웃으로 만든 객체를 setContentView 메서드의 파라미터로 전달하면 그 레이아웃이 화면에 표시됩니다.

- XML 레이아웃 파일에서 정의할 수 있는 대부분의 속성은 자바 소스 코드에서도 사용할 수 있도록 메서드로 제공됩니다. 예를 들어, 리니어 레이아웃은 new LinearLayout()을 통해 리니어 레이아웃 객체를 만든 다음 setOrientation() 메서드에 방향 값 LinearLayout.VERTICAL을 파라미터로 전달하면 세로 방향(또는 가로방향)으로 뷰를 추가할 수 있습니다.

- 앞에서 입력한 코드를 보면 this라는 키워드가 보입니다. 이것은 Context 객체가 전달된 것인데, new 연산자를 사용해서 뷰 객체를 만들 때는 항상 Context 객체가 전달되어야 합니다. 이것은 표준 자바에는 없고 안드로이드에만 있는 특징입니다. 즉, 안드로이드의 모든 UI 객체들은 Context 객체를 전달받도록 되어 있습니다. 
- AppCompatActivity 클래스는 Context를 상속하므로 이 클래스 안에서는 this를 Context 객체로 사용할 수 있습니다. 만약 Context를 상속받지 않은 클래스에서 Context 객체를 전달해야 한다면 getApplicationContext라는 메서드를 호출하여 앱에서 참조 가능한 Context 객체를 사용할 수도 있습니다.

> 컨텍스트(Context)란 일반적으로 어떤 일이 발생한 상황을 의미하는 말인데, 프로그래밍 언어에서는 객체의 정보를 담고 있는 객체를 의미하는 경우가 많습니다. 안드로이드는 UI 구성 요소인 뷰에 대한 정보를 손쉽게 확인하거나 설정할 수 있도록 뷰의 생성자에 Context 객체를 전달하도록 합니다.

- 자바 소스 코드에서 뷰를 만들어 뷰그룹에 추가할 때는 뷰 배치를 위한 속성을 설정할 수 있는 LayoutParams 객체를 사용합니다. 이 객체는 레이아웃에 추가되는 뷰의 속성 중에서 레이아웃과 관련된 속성을 담고 있습니다. LayoutParams 객체를 새로 만들 경우에는 반드시 뷰의 가로와 세로 속성을 지정해야 하며, 이때 사용하는 두 가시 상수인 LayoutPrams.MATCH_PARENT와 LayoutParams.WRAP_CONTENT 중 하나를 사용할 수 있습니다. 필요한 경우에는 이 두 가지 상수가 아닌 가로와 세로의 크기 값을 직접 숫자로 설정할 수도 있습니다.

- 소스 코드에서 레이아웃에 뷰를 추가하려면 addView 메서드를 사용하면 됩니다. 앞에서 실습한 예제는 addView 메서드로 버튼을 추가하고 레이아웃 설정을 위해 버튼 객체의 setLayoutParams 메서드를 사용했습니다. 물론 addView 메서드의 두 번째 파라미터로 LayoutParams 객체를 전달해도 레이아웃을 설정할 수 있습니다. 
- 레이아웃을 만들 때 대부분 XML 레이아웃 파일을 사용하지만 가끔 소스 코드에서 만드는 경우도 있으므로 자바 소스 코드에서 뷰 객체를 만들고 레이아웃에 추가하는 방법을 알아두는 것이 좋습니다.

## 뷰 정렬하기 

- 리니어 레이아웃 안에 들어있는 뷰는 왼쪽, 가운데, 오른쪽 등의 방향을 정렬할 수 있습니다. 이때 사용하는 정렬 속성의 이름은 일반적인 용어와 약간 다릅니다.
- 일반적으로 정렬(align)은 순서대로 놓인다는 의미로 이해할 수 있는데 안드로이드에서는 gravity라는 속성 이름을 사용합니다. 이 속성은 어느 쪽에 무게 중심을 놓을 것인가를 의미로 이해할 수 있습니다. 하지만 똑같이 정렬이라고 생각해도 됩니다. 레이아웃에서 정렬 기능이 필요한 경우는 다음과 같이 두 가지 경우로 나눌 수 있습니다.

|정렬 속성|설 명|
|----|-------|
|layout_gravity|부모의 여유 공간에 뷰가 모두 채워지지 않아 여유 공간이 생겼을 때 여유 공간 안에서 뷰를 정렬함|
|gravity|뷰 안에 표시하는 내용물을 정렬함<br>(텍스트뷰의 경우 내용물은 글자가 되고, 이미지뷰의 경우 내용물은 이미지가 됨)|

- 먼저 뷰를 담고 있는 여유 공간의 뷰가 모두 채워지지 않았을 때 사용하는 layout_gravit는 뷰의 layout_width나 layout_height 속성을 wrap_content로 만든 후에 같이 사용할 수 있습니다. 예를 들어, 세로 방향으로 설정된 리니어 레이아웃에 추가된 추가된 버튼들의 layout_width 속성을 wrap_content로 하면 각각의 버튼들은 한 줄에 한 개씩 추가되면서 글자가 보이는 만큼만 가로 공간을 차지하므로 나머지 가로 공간은 여유 공간으로 남게 됩니다. 이 여유 공간은 다른 뷰가 들어올 수도 없어서 해당 버튼에게 할당된 여유 공간이 됩니다. 안드로이드는 이렇게 여유 공간이 있을 때는 일반적으로 왼쪽 정렬을 하게 되는데 layout_gravity 속성을 직접 설정하면 왼쪽, 중앙 또는 오른쪽 정렬도 할 수 있습니다.

- layout_gravity 속성이 뷰가 어디에 위치할 것인지를 결정하는 것이라면 gravity 속성은 뷰의 위치가 아니라 뷰 안에 들어 있는 내용물의 위치를 결정하는 것입니다. 이 속성은 뷰가 화면에서 차지하는 영역이 충분히 큰 경우 뷰 안에 여유 공간이 생기므로 이 여유 공간 안에서 내용물을 어떻게 정렬할 것인지를 결정합니다. gravity가 적용될 수 있는 대표적인 내용물로는 텍스트뷰 안에 표시되는 텍스트나 이미지뷰에 표시되는 이미지를 들 수 있습니다.

## 뷰 정렬 속성 layout_gravity 

- 먼저 layout_gravity 속성이 어떻게 적용되는지 알아보기 위한 코드를 살펴봅니다. 
- 왼쪽 프로젝트 창에서 /app/res/layout 폴더를 선택한 후 마우스 오른쪽 버튼을 누릅니다.
- 나타난 메뉴에서 [New -> Layout resource file] 메뉴를 선택합니다. 새로운 레이아웃 파일을 만드는 대화상자가 보이면, File name: 입력상자에서 파일이름으로 gravity.xml을 입력합니다. 
- Root element: 입력상자에는 LinearLayout을 입력합니다. 이 항목은 최상위 레이아웃이 어떤 것인지를 지정합니다. 나머지는 그대로 두고 [OK] 버튼을 누르면 새로운 레이아웃 파일이 만들어집니다.

![image33](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image33.png)

- gravity.xml 파일이 열리면 베튼 세 개를 추가합니다. 그리고 각 버튼의 가로(layout_width) 속성을 모두 wrap_content로 바꿔줍니다. 
- 버튼의 text 속성을 찾아 각각 left, center, right로 수정합니다. 마지막으로 layout_gravity 속성 값을 각각 left, center, right로 설정합니다. 그런데 layout_gravity 속성이 보이지 않습니다. 속성 창 하단에 있는 All Attributes의 화살표(▶)를 누르세요. 그러면 모든 속성이 나타납니다. 만약 원래대로 몇 개의 대표적인 속성만 보고 싶다면 다시 한 번 아이콘을 누르면 됩니다.
- 스크롤바를 조금 내리면 layout_gravity가 있습니다. layout_gravity의 목록을 열어 각 버튼의 속성을 left, center, right로 체크하세요.

![image34](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image34.png)

## 내용물 정렬 속성 gravity

- gravity 속성에 넣을 수 있는 값들은 layout_gravity와 같으며, 필요한 경우에는 | 연산자를 사용해서 여러 개의 값을 같이 설정할 수도 있습니다. 이때 주의할 점은 | 연산자 양쪽에 공백이 없어야 한다는 점입니다.

- 디자인 화면에서 세 개의 텍스트뷰를 버튼 아래쪽에 추가합니다. 텍스트뷰를 추가하면 layout_width 속성의 추기 값이 match_parent이므로 가로 방향으로 꽉 채우게 됩니다. 이 상태에서 글자를 정렬해 보겠습니다.

- 첫 번째와 두 번째는 텍스트뷰는 그대로 두고  세 번째 텍스트 뷰의 laytout_height 속성을 match_parent로 변경합니다. 이렇게 하면 세 번째 텍스트뷰는 화면 아래쪽 공간을 모두 차지하게 됩니다. 이렇게 하는 이유는 세 번째 텍스트뷰를 넓게 만들어 여유 공간을 확보하기 위함입니다. 
- 이제 각각의 텍스트 뷰 gravity 속성에 "left", "right", "center_horizontal|center_layout" 값을 설정합니다.
- textColor 속성 값으로는 #ffff0000을 설정하여 글자가 빨강색으로 보이게 합니다. 
- textSize 속성은 글자의 크기를 설정하는데 32sp로 지정하여 약간 크게 보이게 합니다. 그리고 text 속성 값은 각각 "left", "right", "center"가 글자로 표시되도록 설정합니다. 
- textStyle 속성을 "bold" 값으로 지정하면 텍스트가 굵게 표현됩니다. 
- 이 레이아웃을 적용하면 다음과 같은 화면을 볼 수 있습니다.

![image35](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image35.png)

> 버튼이나 텍스트뷰의 크기를 wrap_content로 지정하면 버튼 안에 들어 있는 글자에 맞게 뷰의 크기가 결정되므로 내부의 여유 공간이 없어집니다. 따라서 gravity 속성을 지정해도 아무런 변화가 없습니다.

#### gravity 속성으로 지정할 수 있는 값

|정렬 속성 값|설 명|
|----|--------|
|top|대상 객체를 위쪽 끝에 배치하기|
|bottom|대상 객체를 아래쪽 끝에 배치하기|
|left|대상 객체를 왼쪽 끝에 배치하기|
|right|대상 객체를 오른쪽 끝에 배치하기|
|center_vertical|대상 객체를 수직 방향의 중앙에 배치하기|
|center_horizontal|대상 객체를 수평 방향의 중앙에 배치하기|
|fill_vertical|대상 객체를 수직 방향으로 여유 공간만큼 확대하여 채우기|
|fill_horizontal|대상 객체를 수평 방향으로 여유 공간만큼 확대하여 채우기|
|center|대상 객체를 수직 방향과 수평 방향의 중앙에 배치하기|
|fill|대상 객체를 수직 방향과 수평 방향으로 여유 공간만큼 확대하여 채우기|
|clip_vertical|대상 객체의 상하 길이가 여유 공간보다 클 경우에 남는 부분을 잘라내기<br>top\|clip_vertical로 설정한 경우 아래쪽으로 남는 부분 잘라내기<br>bottom\|clip_vertical로 설정한 경우 위쪽에 남는 부분 잘라내기<br>center_vertical\|clip_vertical로 설정한 경우 위쪽과 아래쪽에 남는 부분 잘라내기|
|clip_horizontal|대상 객체의 좌우 길이가 여유 공간보다 클 경우 남는 부분을 잘라내기<br>right\|clip_horizontal로 설정한 경우 왼쪽에 남는 부분 잘라내기<br>left\|clip_horizontal로 설정한 경우 오른쪽에 남는 부분 잘라내기<br>center_horizontal\|clip_horizontal로 설정한 경우 왼쪽과 오른쪽에 남는 부분 잘라내기|

- 그런데 텍스트뷰로 화면을 구성하다 보면 텍스트가 옆의 텍스트뷰나 버튼에 들어 있는 텍스트와 높이가 맞지 않는 경우를 종종 볼 수 있습니다. 이런 경우는 단순히 layout_gravity나 gravity 속성 값을 설정하는 것만으로 정렬을 맞추기 어렵습니다. 이런 경우는 baselineAligned 속성을 사용할 수 있습니다.

> 제약 레이아웃에서는 화면에 연결선을 만들어 텍스트 높이를 맞출 수 있습니다.<br>앞 장에서 살펴본 제약 레이아웃은 버튼이나 텍스트뷰에 표시된 글자를 다른 뷰의 글자와 맞추기 위해 뷰의 가운데 있는 연결점을 서로 연결합니다. 이렇게 연결하면 두 개의 뷰에 들어 있는 텍스트의 높이를 간단하게 맞출 수 있습니다.

- 다음은 폰트 크기를 다르게 하여 뷰가 차지하는 영역이 달라진 세 개의 뷰를 만든 후 각각의 뷰 안에 표시된 텍스트의 아랫줄을 서로 일렬로 맞추는 XML 레이아웃입니다. /app/res/layout 폴더 안에 새로운 baseline.xml 파일을 새로 만듭니다.

- 새로운 레이아웃 파일을 만들 때는 /app/res/layout 폴더를 선택한 후 마우스 오른쪽 버튼을 눌러서 [New -> Layout resource file] 메뉴를 선택합니다. 대화상자가 보이면 File name: 입력상자에 baseline.xml을 입력하고 Root element: 입력상자에는 LinearLayout을 입력합니다.

- 디자인 화면이 열리면 전체 화면을 클릭해서 최상위 레이아웃의  orientation 속성 값을 horizontal로 변경합니다. 그리고 텍스트뷰 두 개와 버튼 한 개를 추가합니다. 그런데 디자인 화면에 텍스트뷰나 버튼을 추가할 때 layout_weight의 속성 값에 자동으로 1이 설정됩니다. 일단 텍스트뷰나 버튼에 설정된 layout_weight의 값을 지우세요.

- 텍스트뷰와 버튼이 갖는 layout_width와 layout_height의 속성 값은 모두 wrap_content로 설정됩니다. 만약 wrap_content값이 아니라면 wrap_content값으로 변경하세요. 두 개의 텍스트뷰와 한 개의 버튼이 수평 방향으로 나란히 추가되었으며 각각의 뷰들은 뷰 안에 들어 있는 텍스트의 크기만큼 가로의 크기가 정해집니다. 
- 텍스트뷰와 버튼의 크기를 textSize 속성에서 각각 40sp, 20sp, 14sp로 수정합니다.
- text 속성 값에는 각각 '큰 글씨', '중간 글씨', '작은 글씨'를 입력하고 textColor의 속성 값은 각각 #ff0000, #00ff00, #0000ff로 입력합니다.

![image36](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image36.png)

- 이렇게 속성을 수정하면 각각의 뷰에 들어 있는 텍스트의 아래쪽 바닥면이 똑같이 맞춰집니다. 이것은 baselineAligned 속성의 디폴트 값이 true이기 때문입니다. 이번에는 최상위 레이아웃인 리니어 레이아웃을 선택하고 baselineAligned 속성 값을 false로 설정합니다.

- 디자인 화면에서 최상위 레이아웃을 선택할 때는 왼쪽 아래쪽의 Component Tree 창에서 가장 위에 있는 LinearLayout을 선택하면 됩니다. 그런 다음 오른쪽 속성 창에서 [View all attributes] 아이콘을 누르고 baselineAligned을 선택하면 됩니다. 그런 다음 오른쪽 속성 창에서 [View all attributes] 아이콘을 누르고 baselineAligned 속성을 찾아서 체크 박스를 해제합니다. 그러면 다음 그림처럼 글자의 높이가 서로 달라집니다.

![image37](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image37.png)

- baselineAligned 속성으로 정렬을 맞춘 경우 텍스트의 정렬이 우선이기 때문에 뷰의 배치가 이상하게 될 수도 있습니다. 따라서 어떤 정렬을 우선 적용할 것인가에 따라 선택적으로 지정해야 합니다.

## 뷰의 마진과 패딩 설정하기

- 뷰를 담고 있는 부모 레이아웃이 차지하는 공간 중에서 남아 있는 공간에 새로운 뷰를 추가하면 그 뷰의 크기를 늘려 나머지 공간을 모두 채우거나 정렬 속성으로 위치를 지정할 수 있습니다. 그런데 뷰가 부모 레이아웃의 여유 공간을 꽉 채울 경우, 뷰가 서로 붙거나 그 안에 표시된 텍스트가 너무 꽉 차 보여서 화면 구성이 복잡하고 답답해 보이게 됩니다. 
- XML 레이아웃을 만들 때도 뷰의 내부 공간을 띄울 수 있는 속성이 있습니다.

- 뷰의 영역은 테두리선으로 표시할 수 있는데 보이게 할 수도 있고 보이지 않게 할 수도 있습니다. 뷰는 테두리선을 기준으로 바깥 공간과 안쪽 공간이 있으며, 이 모든 공간을 포함한 뷰의 공간을 셀(Cell) 이라고 합니다. 
- 버튼이나 텍스트뷰를 위젯이라고 부르기 때문에 이 공간을 위젯 셀(Widget Cell)이라고 부르기도 합니다. 테두리선을 기준으로 테두리선의 바깥쪽 공간을 마진(Margin)이라 하고 layout_margin 속성을 얼마나 간격을 벌릴 것인지 지정할 수 있습니다. 그리고 테두리선 안쪽의 공간을 패딩(Padding)이라고 합니다. 즉, 뷰 안의 내용물인 텍스트나 이미지가 테두리선과 얼마나 떨어지게 할 것인지를 지정할 수 있으며 padding 속성을 이용합니다.

- 마진이나 패딩은 상하좌우의 간격을 한꺼번에 조절하거나 각각 조절할 수도 있습니다. 예를 들어, padding 속성에 따라 안쪽 내용물과의 거리가 결정되는데 paddingTop, paddingBottom, paddingLeft, paddingRight 속성으로 지정할 수 있습니다.

![image38](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image38.png)

- layout_margin은 padding 속성과 달리 테두리 바깥 부분에 여백을 주는 것입니다. 부모 컨테이너 여유 공간에서 위젯 셀이 차지하는 공간을 결정하는 역할을 합니다. 이 두 가지 속성은 모두 상하좌우 각 면에 여백을 지정하는 방법을 제공합니다. layout_margin은 각각 layout_marginLeft, layout_marginRight, layout_marginTop, layout_marginBottom 속성을 사용합니다.

- 다음은 세 개의 텍스트뷰를 리니어 레이아웃 안에 넣고 layout_margin과 padding 속성으로 뷰가 차지하는 공간을 조절한 XML 레이아웃입니다. 각각의 텍스트뷰가 차지하는 영역을 확실히 구별하려고 background 속성 값으로 배경색을 설정했습니다. 이렇게 하면 뷰의 테두리 부분까지 배경색이 보이게 됩니다. 부모 컨테이너인 리니어 레이아웃의 배경색이 흰색이므로 마진을 주면 마진을 준 부분은 흰색으로 나타납니다.

- /app/res/layout 폴더 안에 padding.xml 파일을 만듭니다. 새로운 레이아웃 파일을 만ㄷ늘 땐 Root element: 입력상자에 LinearLayout을 입력하여 만듭니다. 파일이 열리면 먼저 최상위 레이아웃인 리니어 레이아웃의 orientation 속성을 horizontal로 설정합니다.
- 그런 다음 텍스트뷰 세 개를 추가하고 layout_width 속성 값을 wrap_content로 설정합니다. textSize 속성 값은 모두 24sp로 설정하고 textColor 속성의 값은 모두 #ff0000으로 설정합니다. background 속성의 값은 각각 #ffff00, #00ffff, #ff00ff로 설정합니다.

![image39](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image39.png)

- 그런데 이 XML 레이아웃에 들어 있는 텍스트뷰들의 layout_margin과 padding 속성 값을 50dp로 늘리면 다음과 같은 이상한 모양이 됩니다.

![image40](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image40.png)

- 이것은 수평 방향으로 추가된 세 개의 텍스트뷰 중에서 마지막 뷰(세 번째 텍스트 뷰)에 할당될 수 있는 부모 레이아웃의 여유 공간이 부족하기 때문입니다. 세 번째 텍스트뷰는 공간이 없다보니 아래쪽으로 밀리는 현상이 발생한 것입니다. 이런 문제는 실제 앱을 구성할 때도 발생할 수 있으므로 padding이나 layout_margin 속성을 지정할 때 부모 레이아웃이 남아 있는 공간이 충분한지를 고려해야 레이아웃의 형태가 이상해지지 않습니다.

## 여유 공간을 분할하는 layout_weight 속성

- 부모 레이아웃에 추가한 뷰들의 공간을 제외한 여유 공간은 layout_weight 속성으로 분할할 수 있습니다. 다시말해 layout_weight 속성은 부모 레이아웃에 남아 있는 여유 공간을 분할하여 기존에 추가했던 뷰들에게 할당할 수 있습니다. layout_weight 속성에 숫자 값을 넣으면 그 숫자는 분할 비율이 되며, 그 값의 비율만큼 여유 공간을 분할한 후 해당 뷰에게 할당합니다.
- 예를 들어, layout_weight 속성에 각각 '1'의 값을 지정하면 두 개의 뷰는 1:1의 비율이 적용되어 반씩 여유 공간을 나눠 갖게 됩니다. 같은 방법으로 두 개의 뷰에 각각 '1'과 '2'의 값을 지정하면 각각 1/3과 2/3만큼 여유 공간을 분할한 후 나눠 갖게 됩니다.

> layout_weight 속성으로 각 뷰에 할당하는 크기는 추가적인 값입니다. 즉, 뷰에 layout_width나 layout_height로 지정한 크기에 추가되는 값입니다. 예를 들어, 가로 방향으로 두 개의 버튼을 추가했을 떄 두 개의 버튼을 제외한 나머지 여유 공간이 있다면 그 여유 공간을 분할한 후 이 두개의 버튼에 각각 할당하게 됩니다.

- 여기에서 주의할 점은 layout_width나 layout_height로 지정하는 뷰의 크기는 wrap_content나 숫자 값으로 지정되어야 한다는 것입니다. 만약 match_parent로 지정하면 예상하지 못한 결과가 발생할 수 있습니다.

- layout_weight 속성이 갖고 있는 특징을 알아보기 위해 /app/res/layout 폴더 안에 weight.xml 파일을 만들고 코드를 입력합니다.
- 리니어 레이아웃 안에 다시 리니어 레이아웃을 추가하는 방식으로 구성합니다. 리니어 레이아웃은 수직방향과 수평방향을 지정할 수 있으므로 격자 모양으로 구성하기 위해 최상위 레이아웃은 수직 방향으로 하고 그 안에 추가하는 리니어 레이아웃은 수평방향으로 지정합니다. 이렇게 하면 한 줄씩 뷰가 추가되도록 만들 수 있습니다. 
- 최상위 레이아웃 안에 리니어 레이아웃을 추가한 후 그 안에 두 개의 텍스트뷰를 추가합니다.
- 첫 번째 줄에 추가한 두 개의 뷰들은 layout_weight 속성 값을 똑같이 '1'로 설정하고 text 속성 값은 '텍스트'로 입력합니다. 두 개의 텍스트뷰는 layout_width 속성 값이 wrap_content로 되어 있습니다. 그리고 layout_weight 속성 값이 1로 설정되어 있습니다. 이렇게 하면 오른쪽에 남아 있던 여유 공간은 반반씩 분할되어 두 개의 뷰에 할당됩니다. 

![image41](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image41.png)

- 두 개의 텍스트뷰는 layout_width 속성을 wrap_content로 지정해서 가로 길이가 같습니다. 그래서 여기에 같은 크기의 여유 공간을 더해도 두 개의 텍스트뷰는 똑같은 가로 길이를 갖게 됩니다. 
- 이번에는 리니어 레이아웃 안에 다시 리니어 레이아웃을 하나 더 추가해서 한 줄을 더 만들겠습니다. 추가한 리니어 레이아웃 안에 두 개의 텍스트뷰를 추가한 후 layout_weight 속성 값을 1과 2로 지정합니다.

- 이렇게 하면 오른쪽에 남아 있던 여유 공간은 1/3, 2/3씩 분할되어 두 개의 뷰에 할당됩니다. 디자인 화면에서 결과 화면을 확인하면 다음과 같습니다.

![image42](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image42.png)

- 두 번째 줄에 추가한 두 개의 텍스트뷰는 가로 길이가 서로 다르게 보입니다. 이것은 두 개의 뷰가 남아 있던 여유 공간을 분할하여 가졌기 때문입니다. 그런데 만약 부모 레이아웃의 가로 공간을 1:2의 비율로 나눠 갖도록 하고 싶다면 다음과 같이 layout_width 속성 값을 0dp로 설정해야 합니다. 
- 방향 속성(orientation 속성) 값이 horizontal인 리니어 레이아웃을 하나 더 추가하고 두 개의 텍스트뷰를 추가합니다. 텍스트뷰 두 개의 가로 길이를 모두 0dp로 설정합니다.

- 이렇게 하면 부모 컨테이너의 가로 공간이 모두 남아있게 되고 layout_weight로 분할하면 그 값 그대로 두 개의 뷰가 갖는 가로 길이를 결정하게 됩니다. 

![image43](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image43.png)

- 이렇게 layout_weight 속성으로 뷰의 가로 길이를 결정한 경우 layout_width 속성 값에 match_parent를 설정하면 안 됩니다. 또한 공간 분할 목적으로 사용할 때는 분할하려면 방향에 따라 layout_width나 layout_height의 값을 0dp로 만들어야 합니다.

> 리니어 레이아웃은 한 방향으로만 뷰를 넣을 수 있기 때문에 처음에는 원하는 화면을 만들기 어렵다는 생각이 들 수도 있습니다. 그러나 레이아웃 안에 다시 레이아웃을 추가할 수 있기 때문에 어떤 모양이라도 대부분 만들수 있습니다.

- 정렬 속성, 마진 속성, 패딩 속성 등은 리니어 레이아웃이 아닌 다른 레이아웃에도 적용될 수 있습니다.
