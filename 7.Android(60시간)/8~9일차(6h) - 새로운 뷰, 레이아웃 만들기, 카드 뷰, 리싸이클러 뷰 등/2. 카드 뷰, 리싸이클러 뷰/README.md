# 레이아웃 정의하고 카드뷰 넣기

- 버튼을 상속해서 새로운 버튼을 만들어 보았습니다. 이제 뷰들을 담아두는 레이아웃을 상속해서 새로운 레이아웃을 만들어 보겠습니다. 그리고 그 안에 카드뷰(CardView)를 넣어봅니다. 카드뷰는 프로필과 같은 간단 정보를 넣기 위해 각 영역을 구분하는 역할을 합니다. 예를 들어, 쇼핑몰 앱에서 하나의 상품 정보를 상품 이미지와 가격 그리고 평점으로 표시하고자 한다면 이것들을 카드뷰 안에 넣어서 표시할 수 있습니다. 이렇게 하면 카드뷰가 모서리를 둥글게 보여주거나 다른 배경에 비해 약간 돌출된 것처럼 표현할 수 있습니다. 물론 카드뷰의 배경 색상도 설정할 수 있죠.

- 레이아웃은 뷰들을 그 안에 배치하고 보여주어야 하므로 하나의 XML 레이아웃 파일과 하나의 소스 파일로 구성됩니다. 즉, 액티비티가 그렇듯이 레이아웃으로 만들어지는 부분 화면도 두 개의 파일로 구성됩니다 SampleLayout이라는 이름의 새로운 프로젝트를 만듭니다. 이때 패키지 이름은 org.koreait.layout으로 입력합니다. 프로젝트 창이 보이면 먼저 /app/res/layout 폴더를 선택하고 마우스오른쪽 버튼을 누릅니다. 메뉴가 보이면 [New → Layout resource file] 메뉴를 선택합니다. 새로운 레이아웃의 이름은 layout1.xml로 입력하고 Root element:에는 LinearLayout을 입력합니다. [OK] 버튼을 누르면 새 파일이 만들어집니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image1.png)


- layout1.xml 파일이 만들어지면 디자인 화면에서 최상위 레이아웃인 LinearLayout의 orientation 속성을 horizontal로 변경하고 layout_height 속성 값은 wrap_content로 설정합니다. 그리고 왼쪽에 이미지뷰 하나와 오른쪽에 텍스트뷰 두 개를 추가합니다. 왼쪽에 이미지를 추가하면 어떤 이미지를 넣을것인지 선택 가능한 대화상자가 표시됩니다. 이 대화상자에서 [Mip Map] 탭을 누른 후 ic_launcher이미지를 선택합니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image2.png)

- 추가한 이미지뷰의 layout_width와 layout_height 속성 값은 80dp로 입력합니다. 만약 layout_weight 값에 1이 들어 있다면 그 값을 삭제합니다. 이제 오른쪽에 텍스트뷰 두 개를 추가합니다. 오른쪽에 추가하는 텍스트뷰 두 개는 세로 방향으로 들어갈 수 있도록 orientation 속성 값이 vertical인 LinearLayout을 먼저 추가하고 그 안에 넣어줍니다. 새로 추가한 LinearLayout의 layout_margin속성 값은 5dp로 설정하여 테두리를 약간 띄워줍니다. 이때 추가한 텍스트뷰 2개의 layout_width는match_parent로 수정합니다. 첫 번째 텍스트뷰의 크기는 30sp로 설정하고 두 번째 텍스트뷰의 크기는 25sp, 글자색은 파란색(#FF0000FF)로 설정합니다. text 속성에는 각각 '이름’과 ‘전화번호'를 설정하여 화면에 표시되도록 합니다. 두 개의 텍스트뷰 속성 설정이 끝났다면 이 두 개의 텍스트뷰를 담고있는 리니어 레이아웃의 layout_height 속성 값을 wrap_content로 수정합니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image3.png)

- 이미지뷰 하나와 텍스트뷰 두 개가 들어간 XML 레이아웃 파일을 만들었으니 이 XML 파일과 매칭될 클래스 파일을 만들 차례입니다. /app/java/org.koreait.layout 폴더를 선택한 상태에서 마우스 오﻿른쪽 버튼을 누르고 [New → Java Class] 메뉴를 선택하여 새로운 클래스를 만듭니다. 클래스를 만들기 위한 대화상자가 보이면 입력란에 Layout 을 입력하고 Enter를 누릅니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image4.png)

- [OK] 버튼을 누르면 Layoutl.java 파일이 만들어집니다. Layout1 클래스는 LinearLayout을 상속하도록 코드를 수정합니다. 그러면 Layout1 클래스 아래에 빨간줄이 표시되면서 생성자가 없다는 것을 알려줍니다. 클래스를 위한 중괄호 안쪽에 커서를 두고 마우스 오른쪽 버튼을 누릅니다. 그리고 팝업 메뉴가 보이면 [Generate → Constructor] 메뉴를 눌러 생성자 선택 대화상자가 보이도록 합니다. 앞에서 뷰를 상속하여 새로운 뷰를 정의할 때처럼 생성자는 두 개를 선택합니다.

```java
LinearLayout(context:Context)
LinearLayout(context:Context, attrs:AttributeSet)
```

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image5.png)

- 두 개의 생성자가 추가되면 객체가 생성될 때 호출될 수 있도록 init 메서드를 추가합니다. init 메서드에는 Context 객체가 전달되며 그 안에서 XML 레이아웃 파일을 인플레이션하여 이 소스 파일과 매칭될 수 있도록 합니다.

#### SampleLayout>/app/java/org.koreait.layout/Layout1.java

```java
package org.koreait.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

// LinearLayout 클래스를 상속하여 새로운 클래스 정의하기
public class Layout1 extends LinearLayout {
    public Layout1(Context context) {
        super(context);
        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // 인플레이션 진행하기
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout1, this, true);
    }
}
```

- init 메서드 안에서는 LayoutInflater 객체를 참조했습니다. 이 객체는 시스템 서비스로 제공되므로 getSystemService 메서드를 호출하면서 파라미터로 Context.LAYOUT_INFLATER_SERVICE 상수를전달하면 객체가 반환됩니다. 이 객체의 inflate 메서드를 호출하면서 XML 레이아웃 파일을 파라미터로 전달하면 인플레이션이 진행되면서 이 소스 파일에 설정됩니다.

- 인플레이션 과정이 끝나면 XML 레이아웃 파일 안에 넣어둔 이미지뷰나 텍스트뷰를 찾아서 참조할 수있습니다. 액티비티에서 사용했던 findViewById 메서드를 동일하게 호출할 수 있으므로 inflate 메서드를 호출한 코드 아래에 findViewById를 이용해 이미지뷰와 텍스트뷰를 찾아내는 코드를 추가합니다.

#### SampleLayout>/app/java/org.koreait.layout/Layout1.java

```java

... 생략

// LinearLayout 클래스를 상속하여 새로운 클래스 정의하기
public class Layout1 extends LinearLayout {
    ImageView imageView;
    TextView textView;
    TextView textView2;

	... 생략 

    private void init(Context context) {
        // 인플레이션 진행하기
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout1, this, true);

        // XML 레이아웃에서 정의했던 뷰 참조하기
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    // 뷰에 데이터 설정하기
    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }

    public void setName(String name) {
        textView.setText(name);
    }

    public void setMobile(String mobile) {
        textView2.setText(mobile);
    }
}
```

- findViewById 메서드로 찾아낸 뷰들은 이 클래스 안의 어느 코드에서건 접근할 수 있도록 클래스 상단에 변수를 선언한 후 그 변수에 할당합니다. 그리고 새로 정의한 Layout1이라는 이름의 뷰는 메인 레이아웃에 추가되어 사용할 것이므로 소스 코드에서 이미지뷰의 이미지나 텍스트뷰의 글자를 바꿀 수 있도록 setImage, setName, setMobile이라는 이름의 메서드를 정의합니다. setImage 메서드는 정수를 전달받아 이미지뷰의 이미지를 변경할 수 있도록 합니다. 이미지뷰에 보이는 이미지를 바꿀 수 있는 메서드 중의 하나가 <b>setImageResource 메서드</b>인데 이 메서드는 /app/res/drawable 폴더 안에 들어있는 이미지 파일을 참조하는 정수 값을 파라미터로 전달받습니다. 예를 들어, /app/res/drawable 폴더 안에 house.png라는 이름의 이미지 파일이 들어있다면 이 이미지 파일은 @drawable/house라는 리소스 id로 참조할 수 있는데 이 id 값은 내부적으로 정수 값으로 표현됩니다. 따라서 이런 이미지 리소스 id가 전달되도록 만듭니다.

- Layout1.java 파일과 layout1.xml 파일이 완성되었으니 여러분이 레이아웃을 상속한 새로운 뷰를 하나 만든 것이 됩니다. 이제 이 뷰를 메인 액티비티에 추가해 보겠습니다. activity_main.xml 파일을 열고 디자인 화면에서 최상위 레이아웃을 LinearLayout으로 변경합니다. LinearLayout의 orientation 속성 값은 vertical로 바꾸고 그 안에 있던 텍스트뷰는 삭제합니다. 좌측 상단의 팔레트에서 버튼 두 개를 추가하고 버튼의 layout_width 속성은 wrap_content로 바꿉니다.

그다음 오른쪽 위에 있는 [Code] 아이콘을 눌러 XML 원본 코드를 열고 \<org.koreait.layout.Layout1\> 태그를 \<LinearLayout\> 태그 안에 추가합니다. 이 태그의 layout_width 속성 값은 match_parent로 하고 layout_height 속성 값은 wrap_content로 합니다. 그리고 id 값은 Layout1로 설정합니다.

#### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity" >

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="첫 번째 이미지" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="두 번째 이미지" />

    <org.koreait.layout.Layout1
        android:id="@+id/layout1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
</LinearLayout>
```

- 이제 MainActivity.java 파일을 연 후 onCreate 메서드 안에 다음 코드를 추가합니다.

#### SampleLayout>/app/java/org.koreait.layout/MainActivity.java

```java
package org.koreait.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML 레이아웃에 추가한 뷰 참조하기
        Layout1 layout1 = findViewById(R.id.layout1);

        // 뷰의 메서드 호출하여 데이터 설정하기
        layout1.setImage(R.drawable.ic_launcher_foreground);
        layout1.setName("김민수");
        layout1.setMobile("010-1000-1000");
    }
}
```

- Layout1 클래스에는 setImage, setName, setMobile이라는 이름의 메서드를 정의했었기 때문에 이 세 개의 메서드를 호출하여 Layout1 뷰에 이미지와 글자를 설정할 수 있습니다. 만약 버튼을 눌렀을 때 이미지가 바뀌도록 만들고 싶다면 /app/res/drawable 폴더에 profile1.png profile2.png라는 두 개의 이미지 파일을 넣은 후 다음과 같이 코드를 수정합니다.

> 이미지는 무료로 아이콘을 다운로드받을 수 있는 사이트(http://www.iconfinder.com)에서 받을 수 있으며파일 탐색기에서 저장할 경우 프로젝트 폴더의 /app/src/main/res/drawable 폴더에 저장해야 한다는 점에 주의합니다.

#### SamploLayout>/app/java/org.koreait.layout/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML 레이아웃에 추가한 뷰 참조하기
        Layout1 layout1 = findViewById(R.id.layout1);

        // 뷰의 메서드 호출하여 데이터 설정하기
        layout1.setImage(R.drawable.ic_launcher_foreground);
        layout1.setName("김민수");
        layout1.setMobile("010-1000-1000");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼을 클릭했을 때 이미지 설정하기
                layout1.setImage(R.drawable.profile1);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setImage(R.drawable.profile2);
            }
        });
    }
}
```

- findViewById 메서드로 찾은 Layout1 객체는 클래스 안의 어디서든 참조할 수 있도록 클래스 안에 변수를 선언한 후 그 변수에 할당했습니다. onCreate 메서드 안에서는 Layout1 뷰의 이미지를 profile1.png로 설정하지만 첫 번째 버튼을 눌렀을 때는 profile1.png 이미지로, 두 번째 버튼을 눌렀을 때는 profile2.png 이미지로 바뀌게 합니다. 마지막으로 activity_main.xml 파일에 들어있는 버튼의 글자를 '첫 번째 이미지'와 '두 번째 이미지'로 변경한 후 앱을 실행합니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image6.png)

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image7.png)

- 레이아웃을 상속하여 새로운 뷰를 정의하는 방법을 알게 되었습니다. 그리고 새로운 뷰 안에 들어있는이미지나 텍스트뷰의 속성을 바꾸기 위해 메서드를 정의하고 뷰 바깥에서 그 메서드를 호출하면 이미지나 글자를 바꿀 수 있다는 것도 알게 되었습니다.

- 이제 새로 만든 Layout1의 모양을 카드뷰 모양으로 바꿔보겠습니다. 카드뷰는 다른 뷰들을 담고 있는레이아웃의 테두리를 카드 모양으로 둥글게 바꿔줍니다. layout1.xml 파일을 열고 팔레트에서 CardView를 화면에 끌어다 놓으면 카드뷰가 추가됩니다. 이미 기존에 만들었던 레이아웃이 있으므로 원본XML을 수정하는 방식으로 만들어 가겠습니다. 디자인 화면의 오른쪽 위에 있는 [Code] 아이콘을 눌러 XML 원본 코드가 보이게 한 다음 XML 레이아웃을 수정합니다. 기존에 만들었던 레이아웃은 CardView 태그 안에 들어가도록 하고 최상위 레이아웃이 CardView를 담고 있도록 했습니다. 최상위 레이아웃은 LinearLayout으로 하고 orientation 속성은 vertical로 설정했습니다.

#### SampleLayout>/app/res/layout/layout1.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_margin="5dp"
    android:orientation="horizontal">

    <!-- CardView 태그 추가하기 -->
    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cardBackgroundColor="#FFFFFFFF"
        app:cardCornerRadius="10dp"
        app:cardElevation="5dp"
        app:cardUseCompatPadding="true">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">
            <ImageView
                android:id="@+id/imageView"
                android:layout_width="80dp"
                android:layout_height="80dp"
                app:srcCompat="@mipmap/ic_launcher" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical">

                <TextView
                    android:id="@+id/textView"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="이름"
                    android:textSize="30sp" />

                <TextView
                    android:id="@+id/textView2"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="전화번호"
                    android:textColor="#FF0000FF"
                    android:textSize="25sp" />
            </LinearLayout>
        </LinearLayout>
    </androidx.cardview.widget.CardView>
</LinearLayout>
```

- CardView를 태그로 추가할 때는 패키지 이름인 androidx.cardview.widget까지 같이 입력합니다. CardView 위젯에는 배경색을 설정할 수 있는 cardBackgroundColor, 모서리를 둥글게 만들 수 있는 cardCornerRadius, 그리고 뷰가 올라온 느낌이 들도록 하는 cardElevation 속성이 있습니다. 여기에﻿서는 하얀색 배경에 모서리는 10dp 크기만큼 둥글게 만들도록 했습니다. 올라온 느낌은 5dp 크기만큼 설정했으며 cardUseCompatPadding 속성을 이용해 기본 패딩이 적용되도록 했습니다. 리니어 레이아웃 안에 들어있던 이미지뷰에 대해서는 패딩 값을 5dp 설정하여 이미지가 리니어 레이아웃에 너무 붙어 보이지 않도록 했습니다. 앱을 실행하면 다음과 같이 카드뷰 모양으로 보입니다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image8.png)

* * * 
# 리싸이클러뷰 만들기

- 모바일 단말에서 가장 많이 사용되는 UI 모양 중의 하나가 바로 리스트입니다. 리스트는 일반적으로 여러 개의 아이템 중 하나를 선택할 수 있는 세로 모양으로 된 화면 컨트롤(Control)을 말하는데 이런 UI 모양은 다른 언어에서도 많이 사용됩니다. 특히 아이폰이나 안드로이드처럼 손가락으로 터치하는 방식을 사용하는 스마트폰 단말에서는 리스트가 쉽고 직관적이기 때문에 여러 개의 아이템 중에 선택하는 기능을 넣을 때 더 자주 사용됩니다.

> 손가락을 사용하는 아이폰과 안드로이드폰은 작은 크기의 테이블 셀을 터치하기 힘들기 때문에 리스트 모양으로 큼직하게 만드는 것이 사용성을 높이는 대표적인 방법입니다.

- 안드로이드에서는 여러 개의 아이템 중에 하나를 선택할 수 있는 리스트 모양의 위젯을 특별히 '<b>선택위젯(Selection Widget)</b>'이라고 부릅니다. 선택 기능을 가진 위젯을 특별히 구별하는 이유는 사용되는 방식이 다른 위젯과 약간 다르기 때문입니다.

> 선택 위젯을 일반 위젯과 구분하는 이유는 <b>선택 위젯이 어댑터 (Adapter) 패턴을 사용</b>하기 때문입니다. 선택 위젯에 데이터를 넣을 때 위젯이 아닌 어댑터에 설정해야 하며 화면에 보이는 뷰도 어댑터에서 만듭니다. 즉, 리스트 모양의 뷰에 보이는 각각의 아이템은 뷰가 아닌 어댑터에서 관리합니다.

- 다음은 선택 위젯이 사용되는 방식을 다이어그램으로 표현한 것입니다. 가장 큰 특징은 원본 데이터를 뷰에 직접 설정하지 않고 어댑터라는 클래스를 사용한다는 점입니다

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image9.png)

- 선택할 수 있는 여러 개의 아이템이 표시되는 선택 위젯은 어댑터 (Adapter)를 통해 각각의 아이템을 화면에 보여줍니다. 따라서 원본 데이터는 어댑터에 설정해야 하며 어댑터가 데이터 관리 기능을 담당합니다. 그리고 각각의 아이템을 위한 뷰도 위젯에 전달합니다. 즉, 위젯은 각 아이템을 보여주기만 할 뿐 각 아이템을 위한 뷰는 어댑터가 만듭니다.

- 만약 어댑터에서 만들어 반환하는 뷰가 텍스트뷰나 버튼과 같은 하나의 뷰가 아니라 리니어 레이아웃처럼 여러 개의 뷰들을 담고 있는 레이아웃이라면 하나의 레이아웃 안에 여러 개의 뷰들이 들어가게 됩니다. 실제로 스마트폰 단말에서 다운로드 받을 수 있는 여러 앱을 구경하다 보면 리스트 모양으로 만들어진 UI에 들어가는 각각의 아이템이 글자 하나만으로 구성된 경우는 거의 볼 수 없습니다. 그 이유는 대부분이 어댑터에서 반환하는 객체가 리니어 레이아웃과 같은 컨테이너 객체이기 때문입니다.

- ﻿리스트 모양으로 보여줄 수 있는 위젯으로 <b>리싸이클러뷰(RecyclerView)</b>가 있습니다. 리싸이클러뷰는 기본적으로 상하 스크롤이 가능하지만 좌우 스크롤도 만들 수 있습니다. 왜냐하면 처음 만들어질 때부더 레이아웃을 유연하게 구성할 수 있도록 설계되었기 때문입니다. 그리고 각각의 아이템이 화면에 보일 때 메모리를 효율적으로 사용하도록 <b>캐시(Cache) 메커니즘이 구현</b>되어 있습니다.

- 이제 리싸이클러뷰를 이용해 리스트 모양으로 보여주는 방법을 알아보겠습니다. SampleRecyclerView라는 이름의 새로운 프로젝트를 만듭니다. 이때 패키지 이름은 org.koreait.recyclerview로 입력합니다. 프로젝트 창이 열리면 activity_main.xml 파일을 엽니다. 최상위 레이아웃을 LinearLayout으로 변경하고 orientation 속성 값은 vertical로 설정합니다. 기존에 있던 텍스트뷰는 삭제한 후 RecyclerView를 끌어다 화면에 놓습니다.

- RecyclerView의 layout_width 속성과 Layout_height 속성 값은 모두 match_parent로 설정하여 이뷰가 화면 전체를 채우게 합니다. 그리고 id 속성 값을 recyclerView로 설정합니다.

- 리싸이클러뷰는 선택 위젯이기 때문에 어댑터가 데이터 관리와 뷰 객체 관리를 담당합니다. 따라서 리싸이클러뷰는 껍데기 역할을 한다고 생각하면 쉽습니다. 어댑터를 만들기 전에 어댑터 안에 들어갈 각 아이템의 데이터를 담아둘 클래스를 하나 정의합니다. 여기에서는 전화번호부처럼 사람 목록을 보여줄 예정이므로 Person 이라는 이름의 클래스를 하나 만듭니다. 왼쪽 프로젝트 창에서 /app/java/org.koreait.recyclerview 폴더를 선택한 후 마우스 오른쪽 버튼을 누릅니다. 메뉴가 보이면 [New → Java Class] 메뉴를 눌러 새로운 클래스를 만듭니다. Person.java 파일이 만들어지면 다음 코드를 입력합니다.

#### SampleRecyclerView>/app/java/org.techtown.recyclerview/Person.java

```java
package org.koreait.recyclerview;

public class Person {
    String name;
    String mobile;
}
```

- name은 사람 이름, mobile은 전화번호를 저장해두기 위한 변수입니다. 이 클래스에 생성자 하나와 get, set 메서드를 추가해 보겠습니다. 우선 클래스를 위한 중괄호 안에 커서를 두고 마우스 오른쪽 버튼을 누릅니다. 메뉴가 보이면 [Generate → Constructor] 메뉴를 누릅니다. [Ctrl] 을 누른 상태에서 name, mobile 두 개의 파라미터를 선택한 후 [OK] 버튼을 눌러 생성자를 추가합니다.

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image10.png)

- ﻿생성자를 추가했으면 이제 get, set 메서드를 추가합니다. 클래스를 위한 중괄호 안에 커서를 두고 다시 마우스 오른쪽 버튼을 누릅니다. 메뉴가 보이면 [Generate → Getter and Setter] 메뉴를 누릅니다. Ctrl 을 누른 상태에서 name, mobile 두 개의 파라미터를 선택한 후 [OK] 버튼을 눌러 get, set 메서드를 추가합니다.

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image11.png)

#### SampleRecyclerView>/app/java/org.koreait.recyclerview/Person.java

```java
package org.koreait.recyclerview;

public class Person {
    String name;
    String mobile;

    public Person(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
```

- 어댑터도 새로운 자바 클래스로 만듭니다. 클래스의 이름은 PersonAdapter로 합니다.

#### SampleRecyclerView>/app/java/org.koreait.recyclerview/PersonAdapter.java

```java
package org.koreait.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PersonAdapter {
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        // 뷰홀더 생성자로 전달되는 뷰 객체 참조하기
        public ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 들어 있는 텍스트뷰 참조하기
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
        }

        public void setItem(Person item) {
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }
}
```

- 리스트 형태로 보일 때 각각의 아이템은 뷰로 만들어지며 각각의 아이템을 위한 뷰는 뷰홀더에 담아두게 됩니다. 이 뷰홀더 역할을 하는 클래스를 PersonAdapter 클래스 안에 넣어둔다고 생각하면 됩니다. RecyclerView.ViewHolder 클래스를 상속하여 정의된 ViewHolder 클래스의 생성자에는 뷰 객체가 전달됩니다. 그리고 전달 받은 이 객체를 부모 클래스의 변수에 담아두게 되는데 생성자 안에서 super메서드를 호출하면 됩니다. 그리고 전달받은 뷰 객체의 이미지나 텍스트뷰를 findViewById 메서드로 찾아 변수에 할당하면 setItem 메서드에서 참조할 수 있습니다. setItem 메서드는 이 뷰홀더에 들어있는 뷰 객체의 데이터를 다른 것으로 보이도록 하는 역할을 합니다.

- 이제 PersonAdapter 클래스가 RecyclerView.Adapter 클래스를 상속하도록 수정합니다. 이때 RecyclerView.Adapter 뒤에 \<PersonAdapter.ViewHolder\>를 지정합니다.

#### SamploRecyclerView>/app/java/org.koreait.recyclervlow/PorsonAdapter.java

```java
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
	... 생략
}
```

- 클래스 이름 밑에 빨간 줄이 보이는 것은 필요한 메서드가 구현되지 않았기 때문입니다. PersonAdapter 클래스의 중괄호 안에 커서를 두고 마우스오른쪽 버튼을 누른 후 [Generate → Implement Methods...] 메뉴를 선택합니다. 대화상자가 보이면 세 개의 메서드가 표시됩니다. 선택된 그대로 [OK] 버튼을 누릅니다.

![image12](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image12.png)

- 이 어댑터에 구현되어야 하는 중요한 메서드는 3가지입니다. <b>getItemCount</b>메서드는 어댑터에서 관리하는 아이템의 개수를 반환합니다. 이 메서드는 리싸이클러뷰에서 어댑터가 관리하는 아이템의 개수를 알아야 할 때 사용됩니다. <b>onCreateViewHolder</b>와 <b>onBindViewHolder</b> 메서드는 뷰홀더 객체가만들어질 때와 재사용될 때 자동으로 호출됩니다. <b>리싸이클러뷰에 보이는 여러 개의 아이템은 내부에서 캐시되기 때문에 아이템 개수만큼 객체로 만들어지지는 않습니다.</b> 예를 들어, 아이템이 천 개라고 하더라도 이 아이템을 위해천 개의 뷰 객체가 만들어지지 않습니다. 메모리를 효율적으로 사용하려면 뷰홀더에 뷰 객체를 넣어두고 사용자가 스크롤하여 보이지 않게 된 뷰 객체를 새로 보일 쪽에 재사용하는 것이 효율적이기 때문입니다. 이 과정에서 뷰홀더가 재사용됩니다.

![image13](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image13.png)

- 뷰홀더가 새로 만들어지는 시점에는 <b>onCreateViewHolder</b> 메서드가 호출되므로 그 안에서는 각 아이템을 위해 정의한 XML 레이아웃을 이용해 뷰 객체를 만들어줍니다. 그리고 뷰 객체를 새로 만든 뷰홀더 객체에 담아 반환합니다. onBindViewHolder 메서드는 뷰홀더가 재사용될 때 호출되므로 뷰 객체는 기존 것을 그대로 사용하고 데이터만 바꿔줍니다.

- <b>onCreateViewHolder</b> 메서드에는 뷰 타입을 위한 정수값이 파라미터로 전달됩니다. 이것은 각 아이템을 위한 뷰를 여러 가지로 나누어 보여주고 싶을 때 사용됩니다. 예를 들어, 어떤 때는 이미지를 보여주고 어떤 때는 이미지와 텍스트를 같이 보여주고 싶다면 뷰 타입을 정하고 각각의 뷰 타입에 따라 다른 XML 레이아웃을 인플레이션하여 보여줄 수 있습니다. 일반적인 경우에는 뷰 타입을 한 가지로 하는경우가 많기 때문에 여기에서는 뷰 타입 파라미터를 사용하지 않습니다.

- <b>onCreateViewHolder</b> 메서드 안에서 인플레이션을 진행하기 위해서는 Context 객체가 필요한데 파라미터로 전달되는 뷰그룹 객체의 getContext 메서드를 이용하면 Context 객체를 참조할 수 있습니﻿다. 파라미터로 전달되는 뷰그룹 객체는 각 아이템을 위한 뷰그룹 객체이므로 XML 레이아웃을 인플레이션하여 이 뷰그룹 객체에 설정합니다.

- <b>onBindViewHolder</b> 메서드는 재활용할 수 있는 뷰홀더 객체를 파라미터로 전달하기 때문에 그 뷰홀더에 현재 아이템에 맞는 데이터만 설정합니다. 데이터는 Person 객체로 만드는데 여러 아이템을 이어댑터에서 관리해야 하기 때문에 클래스 안에 ArrayList 자료형으로 된 items라는 변수를 하나 만들어줍니다. 그러면 on BindViewHolder 메서드로 전달된 position 파라미터를 이용해 ArrayList에서 Person 객체를 꺼내어 설정할 수 있습니다. 어댑터가 ArrayList 안에 들어 있는 전체 아이템의 개수를 알아야 하므로 getItemCount 메서드는 ArrayList의 size 메서드를 호출하여 전체 아이템이 몇 개인지를 확인한 후 그 값을 반환합니다.

#### SampleRecyclerView>/app/java/org.koreait.recyclerview/PersonAdapter.java

```java

... 생략

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> items = new ArrayList<Person>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        // 인플레이션을 통해 뷰 객체 만들기
        View itemView = inflater.inflate(R.layout.person_item, viewGroup, false);

        //  뷰홀더 객체를 생성하면서 뷰 객체를 전달하고 그 뷰홀더 객체를 반환하기
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Person item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

   ... 생략 
   
}

```
- 코드의 양이 좀 많지만 이 3가지 메서드가 하는 각각의 역할을 이해하면 그 안에 들어 있는 코드가 어느 정도 이해될 것입니다. 여기에서는 각 아이템을 위해 XML 레이아웃을 person_item.xml로 만들었다는 것을 전제로 했습니다. 따라서 /app/res/layout 폴더 안에 person_item.xml 파일을 만들고 레이아웃을 구성합니다.

﻿- 이 레이아웃은 앞 단락에서 만들었던 것과 동일합니다. 카드뷰를 사용하고 있고 왼쪽에는 이미지 하나, 오른쪽에는 텍스트뷰 두 개를 보여주는 레이아웃입니다. 텍스트뷰에는 textView와 textView2는 id 속성 값을 설정합니다. 이 id 값은 뷰홀더 안에서 텍스트뷰를 findViewById 메서드로 찾을 때 사용되므로 XML 레이아웃에 설정된 id 값과 어댑터 소스 코드에서 사용하는 id 값이 동일해야 합니다.

- 앞 단락에서 레이아웃을 상속하여 새로운 뷰를 만들 때는 XML 레이아웃과 뷰 소스 코드가 한 쌍으로 필요했지만 여기에서는 XML 레이아웃만 있으면 됩니다. 왜냐하면 이 XML 레이아웃을 ViewGroup객체에 인플레이션한 후 ViewHolder 객체에 넣어 둘 것이기 때문입니다.

- 이 어댑터가 각각의 아이템을 위한 Person 객체를 ArrayList 안에 넣어 관리하기 때문에 이 어댑터를 사용하는 소스 코드에서 어댑터에 Person 객체를 넣거나 가져갈 수 있도록 addItem, setItems, getItem, setItem 메서드를 PersonAdapter에 추가합니다.

#### SampleRecyclerView>/app/java/org.koreait.recyclerview/PersonAdapter.java

```java

... 생략

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
	
	... 생략

    public void addItem(Person item) {
        items.add(item);
    }

    public void setItems(ArrayList<Person> items) {
        this.items = items;
    }

    public Person getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Person item) {
        items.set(position, item);
    }
    
	... 생략
}
```

- 이제 리싸이클러뷰를 위한 어댑터 코드가 만들어졌습니다. 이 어댑터는 리싸이클러뷰 객체에 설정되어야 하고 어댑터 안에 Person 객체들을 만들어 넣어야 하므로 MainActivity.java 파일을 열고 onCreate메서드 안에 코드를 추가합니다.

#### SampleRecyclerView>/app/java/org.koreait.recyclerview/PersonAdapter.java

```java
package org.koreait.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰에 레이아웃 매니저 설정하기
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        PersonAdapter adapter = new PersonAdapter();

        adapter.addItem(new Person("김민수", "010-1000-1000"));
        adapter.addItem(new Person("김하늘", "010-2000-2000"));
        adapter.addItem(new Person("홍길동", "010-3000-3000"));

        // 리싸이클러뷰에 어댑터 설정하기
        recyclerView.setAdapter(adapter);
    }
}
```

- 리싸이클러뷰에는 레이아웃 매니저를 설정할 수 있습니다. 레이아웃 매니저는 리싸이클러뷰가 보일 기본적인 형태를 설정할 때 사용하는데 자주 사용하는 형태는 세로 방향, 가로 방향, 격자 모양입니다. 다시 말해 보통 리스트 모양으로 표시할 때는 세로 방향 스크롤을 사용하는데 다양한 모양으로 보일 수 있도록 가로 방향으로도 설정할 수 있고 격자 모양으로도 보여줄 수 있는 거죠. 여기에서는 LinearLayoutManager 객체를 사용하면서 방향을 VERTICAL로 설정했기 때문에 세로 방향 스크롤로 보이게 됩니다. 만약 가로 방향 스크롤로 보여주고 싶다면 HORIZONTAL로 설정하면 되고 격자 모양으로 보여주고 싶다면 GridLayoutManager 객체를 사용하면서 칼럼의 수를 지정하면 됩니다.

- 리싸이클러뷰에 레이아웃 매니저 객체를 설정하기 위해 setLayoutManager 메서드를 호출합니다. 그리고 그 아래에서는 PersonAdapter 객체를 만든 후 setAdapter 메서드를 호출하면서 파라미터로 전달합니다. 이렇게 하면 리싸이클러뷰가 어댑터와 상호작용하면서 리스트 모양으로 보여주게 됩니다. 어댑터에 Person 객체를 추가할 때는 addItem 메서드를 사용합니다. 여기에서는 세 개의 Person 객체를 만들어 추가했으니 화면에는 세 개의 아이템이 보이게 될 것입니다.

- 이제 앱을 실행하면 다음과 같이 세로 방향으로 아이템 세 개가 표시됩니다. 아이템이 충분히 많아져 화면 영역을 벗어나면 스크롤이 생기게 됩니다. adapter.addItem 메서드를 이용하여 아이템을 많이 추가한 다음 결과 화면을 직접 확인해 보세요.

#### person_item.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cardBackgroundColor="#FFFFFFFF"
        app:cardCornerRadius="10dp"
        app:cardElevation="5dp"
        app:cardUseCompatPadding="true" >

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <ImageView
                android:id="@+id/imageView"
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:padding="5dp"
                app:srcCompat="@mipmap/ic_launcher" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_margin="5dp"
                android:layout_weight="1"
                android:orientation="vertical">

                <TextView
                    android:id="@+id/textView"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="이름"
                    android:textSize="30sp" />

                <TextView
                    android:id="@+id/textView2"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="전화번호"
                    android:textColor="#FF0000FF"
                    android:textSize="25sp" />
            </LinearLayout>

        </LinearLayout>

    </androidx.cardview.widget.CardView>

</LinearLayout>
```

![image14](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image14.png)

- 리스트 모양으로 보여줄 수 있게 되었으니 이번에는 격자 모양으로 보이도록 변경해보고 각 아이템을 클릭했을 때 동작하도록 만들어 봅니다. 프로젝트를 새로 만들 수도 있지만 만들어둔 소스 코드를 일부 수정하기만 할 예정이므로 파일 탐색기를 열고 프로젝트 폴더가 저장된 위치를 찾은 후 폴더 전체를 복사합니다. 복사하여 만들어진 폴더의 이름은 SampleRecyclerView2로 합니다.

- 안드로이드 스튜디오에서 새로 만들어진 프로젝트를 열어줍니다. 기존 프로젝트 창은 [File → Close Project] 메뉴를 눌러 닫아줍니다. 시작 화면이 보이면 두 번째 [Open an existing Android Studioproject] 메뉴를 누르고 새로 만들어진 프로젝트 폴더 (SampleRecyclerView2)를 지정합니다. 그러면 새로운 프로젝트가 열립니다. 새로 연 프로젝트의 내용은 이전과 동일합니다.

- 이제 리싸이클러뷰가 격자 형태로 보이도록 수정해 보겠습니다. 리싸이클러뷰가 보이는 모양은 레이아웃 매니저를 통해 결정한다고 했으니 MainActivity.java 파일을 열고 레이아웃 매니저를 GridLayoutManager로 변경합니다.

#### SampleRecyclerView2>/app/java/org.koreait.recyclerview/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰에 GridLayoutManager를 레이아웃 매니저로 설정하기
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
		
		... 생략
		
    }
}
```

- GridLayoutManager의 생성자에는 전달된 두 번째 파라미터는 칼럼의 개수를 의미합니다. 여기에서는 2를 전달하여 두 칼럼으로 표시되는 격자 모양의 레이아웃으로 설정했습니다. 화면에 보이는 아이템의 개수를 늘리기 위해 어댑터에 추가하는 Person 객체를 더 많이 늘려줍니다.

- 기존 SampleRecyclerView 프로젝트를 복사했으므로 앱의 패키지 이름이 동일합니다. 이것을 변경하면 기존 앱과 다른 앱으로 인식될 것입니다. 앱의 패키지 이름을 바꾸기 위해 왼쪽 프로젝트 창에서 /app/java/org.koreait.recyclerview 를 선택한 후 마우스 오른쪽 버튼을 누릅니다. 메뉴가 보이면 [Refactor → Rename] 메뉴를 누릅니다.

- 그러면 다음과 같은 경고 화면이 나옵니다. 이것은 여러 폴더가 바뀔 것이라는 것을 알려주는 안내 글이므로 안심해도 좋습니다. [Rename package] 버튼을 누르세요.

![image15](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image15.png)

- 대화상자가 표시되면 새로운 패키지명을 입력하면 됩니다. 기존 패키지명의 마지막 글자가 recyclerview였는데 이것을 recyclerview2로 변경합니다.

![image16](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image16.png)

- ﻿[Do Refactor] 버튼을 누르면 소스 파일의 패키지명뿐만 아니라 프로젝트 내의 패키지명까지 찾아 모두 변경합니다. 프로젝트 안에 사용된 패키지명은 자동으로 검색되고 아래쪽에 표시됩니다.

#### SampleRecyclerView2>/Gradle Scripts/build.gradle(Module: SampleRecyclerView.app)

```

... 생략

android {
    compileSdk 32

    defaultConfig {
        applicationId "org.koreait.recyclerview2"
        
		... 생략
    }
	
	... 생략
}
```

- 패키지명을 변경했으므로 기존에 빌드된 파일들을 삭제합니다. 파일 탐색기에서 프로젝트 폴더를 찾은 후 그 안에 있는 app/build 폴더를 삭제합니다. 이 폴더는 앱이 빌드되면서 만들어지는 폴더로 새로운 프로젝트로 복사하거나 소스 코드 등의 변경이 일어난 경우에는 새로 빌드될 수 있도록 삭제하는 것이 좋습니다. 물론 여러분이 만든 프로젝트를 다른 PC에 옮길 경우에도 마찬가지로 build 폴더를 삭제하고 압축한 후 옮기는 것이 좋습니다. 이 폴더의 크기가 상당히 크기 때문에 간단한 변경인 경우에는 안드로이드 스튜디오 창의 오른쪽 위에 있는 아이콘 중에서 [Sync Project...] 아이콘을 눌러도 됩니다. 다만 이 아이콘을 눌러 수정 사항을 반영해도 기존 빌드 코드가 남아있는 경우가 있으니 그럴 때는 build폴더를 삭제해보기 바랍니다.

- 앱을 실행하면 격자 모양으로 화면이 표시됩니다. 그리고 아이템이 충분히 추가되었다면 스크롤도 되는 것을 확인할 수 있습니다. 다만 글자의 크기가 커서 이름이나 전화번호가 다 보이지 않을 수 있습니다. 이때는 텍스트뷰의 글자 크기를 더 작게 변경하면 됩니다. person_item.xml 파일에서 텍스트뷰의 글자 크기를 줄인 후 다시 실행해보기 바랍니다.

![image17](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image17.png)

- 이제 사용자가 각 아이템을 클릭했을 때 토스트 메시지가 표시되도록 수정해 보겠습니다. 클릭 이벤트는 리싸이클러뷰가 아니라 각 아이템에 발생하게 되므로 뷰홀더 안에서 클릭 이벤트를 처리할 수 있도록 만드는 것이 좋습니다. 뷰홀더의 생성자로 뷰 객체가 전달되므로 이 뷰 객체에 OnClickListener를 설정합니다. 그러면 이 뷰를 클릭했을 때 그 리스너의 onClick 메서드가 호출됩니다. <b>그런데 이 리스너안에서 토스트 메시지를 띄우게 되면 클릭했을 때의 기능이 변경될 때마다 어댑터를 수정해야 하는 문제가 생깁니다.</b> 따라서 <b>어댑터 객체 밖에서 리스너를 설정하고 설정된 리스너 쪽으로 이벤트를 전달받도록 하는 것이 좋습니다.</b>

- 이를 위해 OnPersonItemClickListener 인터페이스를 먼저 정의합니다. /app/java/org.koreait.recyclerview2 폴더를 선택한 상태에서 마우스 오른쪽 버튼을 누르고 [New → Java Class] 메뉴를 누르세요. 입력란에 OnPersonItemClickListener를 입력하고 Interface를 선택한 후 Enter를 칩니다. 파일이 만들어지면 다음과 같은 인터페이스를 정의합니다.

#### SampleRecyclerView2>/app/java/org.techtown.recyclerview2/OnPersonItemClickListener.java 
```java
package org.koreait.recyclerview2;

import android.view.View;

public interface OnPersonItemClickListener {
    public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position);
}
```

- onItemClick 메서드가 호출될 때 파라미터로 뷰홀더 객체와 뷰 객체 그리고 뷰의 position 정보가 전달되도록 합니다. position 정보는 몇 번째 아이템인지를 구분할 수 있는 인덱스 값입니다. 이 인터페이스를 사용하도록 ViewHolder 클래스를 수정합니다.

#### SampleRecyclerView2>/app/java/org.koreait.recyclerview2/PersonAdapter.java

```java
	
... 생략
	
	static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        // 뷰홀더 생성자로 전달되는 뷰 객체 참조하기
        public ViewHolder(View itemView, final OnPersonItemClickListener listener) {
            super(itemView);

            // 뷰 객체에 들어 있는 텍스트뷰 참조하기
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            // 아이템 뷰에 OnClickListener 설정하기
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Person item) {
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }
		
... 생략

```

- 뷰홀더 객체의 생성자가 호출될 때 리스너 객체가 파라미터로 전달되도록 수정되었습니다. 이 리스너 객체는 어댑터 밖에서 설정할 것이며 뷰홀더까지 전달됩니다. 이렇게 전달된 리스너 객체의 onItemClick 이벤트는 뷰가 클릭되었을 때 호출됩니다. 이 코드에서 getAdapterPosition 메서드를 볼 수 있는데 이 메서드는 이 뷰홀더에 표시할 아이템이 어댑터에서 몇 번째인지 정보를 반환합니다. 다시 말해 아이템의 인덱스 정보를 반환합니다. 따라서 이 메서드를 호출하여 반환된 정수 값을 리스너에 전달할 수 있습니다.

- 뷰홀더(static class ViewHolder ...) 코드를 수정했으니 이제 어댑터(public class PersonAdapter ...)코드를 수정합니다.

#### SampleRecyclerView2>/app/java/org.koreait.recyclerview2/PersonAdapter.java

```java 

... 생략

// 어댑터 클래스가 새로 정의한 리스너 인터페이스 구현하도록 하기
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> implements OnPersonItemClickListener {
    ArrayList<Person> items = new ArrayList<Person>();

    static OnPersonItemClickListener listener;

    ... 생략

    // 외부에서 리스너를 설정할 수 있도록 메서드 추가하기
    public void setOnItemClickListener(OnPersonItemClickListener listener) {
        PersonAdapter.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }
	
	... 생략
}
```

- 어댑터 클래스는 새로 정의한 OnPersonItemClickListener 인터페이스를 구현하도록 합니다. 그리고 이 인터페이스에서 정의한 onItemClick 메서드를 추가합니다. 이 메서드는 뷰홀더 클래스 안에서 뷰가 클릭되었을 때 호출되는 메서드입니다. 그런데 이 어댑터 클래스 안에서가 아니라 밖에서 이벤트 처리를 하는 것이 일반적이므로 listener라는 이름의 변수를 하나 선언하고 setOnItemClickListener 메서드를 추가하여 이 메서드가 호출되었을 때 리스너 객체를 변수에 할당하도록 합니다. 이렇게 하면 onltemClick 메서드가 호출되었을 때 다시 외부에서 설정된 메서드가 호출되도록 만들 수 있습니다. 마지막으로 onCreateViewHolder 메서드 안에서 new 연산자를 이용해 ViewHolder 객체를 생﻿성하는 코드를 수정합니다. 이전에는 뷰 객체만 파라미터로 전달했지만 여기에 리스너인 this 를 추가로 전달합니다.

- 어댑터 코드가 수정되었으니 이제 MainActivity.java 파일을 열고 어댑터에 리스너 객체를 설정하는 코드를 추가합니다.

#### SampleRecyclerView2>/app/java/org.koreait.recyclerview2/MainActivity.java

```java
package org.koreait.recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰에 GridLayoutManager를 레이아웃 매니저로 설정하기
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PersonAdapter();

        adapter.addItem(new Person("김민수", "010-1000-1000"));
        adapter.addItem(new Person("김하늘", "010-2000-2000"));
        adapter.addItem(new Person("홍길동", "010-3000-3000"));

        // 리싸이클러뷰에 어댑터 설정하기
        recyclerView.setAdapter(adapter);
        
        // 어댑터에 리스너 설정하기
        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                // 아이템 클릭 시 어댑터에서 해당 아이템의 Person 객체 가져오기
                Person item = adapter.getItem(position);
                Toast.makeText(getApplication(), "아이템 선택됨: " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
```

- 리싸이클러뷰 객체와 어댑터 객체는 이 클래스 안의 어디서든 접근할 수 있도록 클래스 안에 선언된 변수에 할당되었습니다. 그리고 어댑터 객체에는 setOnItemClickListener 메서드를 호출하면서 리스너 객체를 설정했습니다. 이렇게 하면 각 아이템이 클릭되었을 때 이 리스너의 onItemClick 메서드가 호출됩니다. onItemClick 메서드 안에서는 어댑터 객체의 getItem 메서드를 이용해 클릭된 아이템 객체를 확인합니다.

- 앱을 실행하고 리스트 모양으로 보이는 뷰 안의 아이템을 하나 선택하면 토스트가 선택된 아이템의 이름을 표시합니다.

![image18](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/2.%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0/images/image18.png)
