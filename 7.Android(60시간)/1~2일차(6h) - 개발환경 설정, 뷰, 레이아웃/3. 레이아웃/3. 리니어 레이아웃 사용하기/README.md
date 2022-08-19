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
        setContentView(R.layout.activity_main);
    }
}
```
- 하나의 화면은 액티비티라고 부릅니다. 앱을 실행했을 때 처음 보이는 화면은 메인 액티비라고 부릅니다. 즉, 프로젝트를 처음 만들면 자동으로 메인 액티비티가 만들어지고 그 액티비티를 위한 자바 소스 파일의 이름은 MainActivity.java가 됩니다. 그리고 이 메인 액티비티는 AndroidManifest.xml 파일 안에 자동으로 등록됩니다. 이 메인 액티비티를 여러분이 새로 추가한 LayoutCodeActivity로 변경하면 앱을 실행했을 때 새로 만든 액티비티가 나타나게 됩니다.
