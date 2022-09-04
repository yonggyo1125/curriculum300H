# 프레임 레이아웃과 뷰의 전환 

- 프레임 레이아웃은 가장 기본적이고 단순한 레이아웃입니다. 프레임 레이아웃에 뷰를 넣으면 그 중에서 하나의 뷰만 화면에 표시합니다. 의외로 이 레이아웃이 자주 사용됩니다. 
- 일반적으로 생각하면 한 번에 하나의 뷰만 보여주기 때문에 너무 단순한 레이아웃이라고 부르기조차 힘든 것이 아닌가 하고 생각할 수도 있습니다. 하지만 프레임 레이아웃이 갖고 있는 중첩(Overlay) 기능을 알고 나면 조금 달라집니다.

- 프레임 레이아웃은 뷰를 하나 이상 추가할 경우 추가된 순서로 차곡차곡 쌓입니다. 가장 먼저 추가한 뷰가 가장 아래쪽에 쌓이고 그다음에 추가한 뷰는 그 위에 쌓여서 가장 나중에 쌓인 뷰만 보이게 됩니다. 이때 가장 위에 있는 뷰를 보이지 않게 하면 그다음 뷰가 보입니다. 이렇게 보이거나 보이지 않게 하는 속성이 가시성(Visibility) 속성입니다. 이런 특성은 여러 개의 뷰를서로 전환할 때 사용할 수 있습니다.

- 뷰를 추가할 때는 디자인 화면에서 추가할 수도 있고 자바 소스 코드에서 addView 메서드를 사용해서추가할 수도 있습니다. 그리고 가시성(Visibility) 속성을 사용해서 특정 뷰를 보이거나 보이지 않게 하면 화면에 보이는 뷰가 전환되는 효과도 만들 수 있습니다. 이 속성의 이름은 visibility로 되어있으며 그값은 visible, invisible 또는 gone 중의 하나로 설정할 수 있습니다. 만약 자바 소스 코드에서 설정하고싶다면 setVisibility 메서드를 사용하면 됩니다.


- SampleFrameLayout 프로젝트를 새로 만듭니다. 그런 다음 activity_main.xml 파일을 열고 화면 레이아웃을 구성합니다. 화면 위쪽에 버튼을 하나 두고 그 아래에는 이미지가 보이게 합니다. 그런데 이미지는 버튼을 누를 때마다 전환되어야 하므로 프레임 레이아웃 안에 두 개의 이미지뷰를 중첩하여 넣어줍니다.


> 이미지뷰에 이미지를 설정하려면 먼저 학습 소스에 있는 이미지인 dream01.png dream02.png 파일을 프로젝트 폴더 안의 /app/res/drawable 폴더에 복사해야 합니다.  직접다른 이미지를 넣어줄 수도 있는데 이때 이미지의 이름에는 영문소문자, 숫자 또는 기호만 들어가야 한다는 점에 주의해야 합니다. 만약 영문 대문자가 들어가거나 이미지의 이름이 숫자로 시작하면오류가 발생할 수 있습니다. 파일 탐색기에서 이미지 파일을 추가할 때는 프로젝트 폴더 안에서 /app/src/main/res/drawable 폴더를 찾은 후 그 안에 넣어야 합니다.

- 먼저 최상위 레이아웃을 LinearLayout으로 바꾼 후 위쪽에 버튼 하나를 추가하고 그 아래쪽에는 FrameLayout을 추가합니다. FrameLayout의 layout_width와 layout_height 속성 값은 모두 match_parent로 설정해서 아래쪽 영역을 꽉 채우도록 만듭니다. 그리고 두 개의 이미지뷰를 추가합니다. 이미지뷰는 이미지를 보여줄 때 사용하는 것으로 팔레트에서 ImageView를 끌어다 추가하면 됩니다. 이미지뷰를 끌어다 놓으면 처음 보일 기본 이미지를 설정하는 대화상자가 표시됩니다. 대화상자에서 /app/res/drawable 폴더에 들어 있는 이미지를 지정합니다.

#### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:onClick="onButtonClicked"
        android:text="이미지 바꾸기" />

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:visibility="invisible"
            app:srcCompat="@drawable/dream01" />

        <ImageView
            android:id="@+id/imageView2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:visibility="visible"
            app:srcCompat="@drawable/dream02" />
    </FrameLayout>
</LinearLayout>
```

- 두 개의 이미지뷰에는 모두 visibility라는 속성이 정의되어 있는데 먼저 추가한 이미지뷰에는 "invisible", 나중에 추가한 이미지뷰에는 "visible" 값을 설정합니다. 이렇게 하면 나중에 추가한 이미지만 화면에 보입니다. 물론 이 속성 값을 설정하지 않아도 프레임 레이아웃이 갖는 특성 때문에 두 번째 이미지뷰만 보입니다. 왜냐하면 프레임 레이아웃은 먼저 추가한 뷰 위에 나중에 추가한 뷰가 쌓이기 때문입니다.

- 이제 자바 소스 코드에서 버튼을 누를 때마다 두 개의 이미지뷰가 가지는 visibility 속성을 바꿔주면 두개의 이미지가 서로 전환되는 효과를 만들 수 있습니다. MainActivity.java 소스 파일을 열고 다음 코드를 입력합니다.


#### SampleFrameLayout>/app/java/org.koreait.sampleframelayout/MainActivity.java

```java
package org.koreait.sampleframelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;

    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
    }

    public void onButtonClicked(View v) {
        changeImage();
    }

    private void changeImage() {
        if (imageIndex == 0) {  // 첫 번째 이미지뷰가 보이게 설정
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);

            imageIndex = 1;
        } else if (imageIndex == 1) { // 두 번째 이미지뷰가 보이게 설정
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);

            imageIndex = 0;
        }
    }
}
```


- MainActivity.java 파일 안에서는 XML 레이아웃에 들어 있는 두 개의 이미지뷰를 <b>findViewById</b> 메서드로 찾은 후 변수에 할당합니다.


- XML 레이아웃 파일에서 id 값으로 "@+id/imageView"를 부여했다면 자바 소스 코드에서는 R.id.imageView라는 값으로 id를 참조할 수 있습니다. 즉, 다음과 같은 형식입니다.
	- XML 레이아웃 파일에서 id 지정할 때 -> @+id/아이디
	- 자바 소스 코드에서 참조할 때 -> R.id.아이디

> 하나의 화면은 XML 파일과 소스 파일로 나누어져 있습니다. 이 두 개의 파일이 쌍을 이루면서 하나의 화면을 만들게 됩니다. 어떤 XML 파일과 어떤 소스 파일이 하나의 쌍을 이루는지에 대한 정보는 소스 파일에 들어가 있습니다. 액티비티 소스 파일에는 <b>setContentView</b> 라는 메서드가 있어서 파라미터로 XML 파일을 넘겨줄 수 있는데, 이 파라미터를 이용해 소스 파일과 XML 파일이 연결됩니다.<br>이렇게 두 개의 파일이 쌍으로 연결되게 되면 소스 파일에서는 XML 파일에 들어 있는 뷰를 찾아 사용할 수 있습니다. <b>findViewByld</b> 라는 메서드 이름을 잘 살펴보면 ID를 이용해 뷰를 찾는다는 의미라는 것을 알 수 있습니다. XML 파일에 추가한 뷰에는 ID 값을 할당할 수 있는데 소스 파일에서는 그 ID를 이용해 메모리에 만들어진 뷰 객체를 찾을 수 있습니다.


- 그리고 버튼을 눌렀을 때 호출되는 changeImage 메서드 안에서는 두 개의 이미지뷰가 갖는 가시성 속성을 변경해 줍니다. <b>View.VISIBLE</b> 또는 <b>View.INVISIBLE</b> 상수로 설정하면 이 값은 XML 레이아웃에서 "visible"과 "invisible"로 설정한 것과 같습니다. imageIndex 변수는 어떤 이미지뷰가 프레임 레이아웃에 보이고 있는지 알 수 있도록 선언한 것으로, 첫 번째 이미지뷰가 화면에 보일 때는 값을 0으로 설정하고, 두 번째 이미지뷰가 화면에 보일 때는 값을 1로 설정했습니다. 앱을 실행하고 버튼을 눌러보면 다음과 같이 이미지가 변경되는 것을 확인할 수 있습니다.

![image51](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image51.png)

![image52](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image52.png)

- 화면에서는 이미지가 바뀌는 것처럼 보이지만 실제로는 두 개의 이미지뷰가 중첩되어 있는 상태에서 하나를 보이게 하거나 또는 보이지 않게 하는 것입니다.

> 프레임 레이아웃으로 뷰를 전환하는 방법은 그렇게 복잡하지 않지만 안드로이드에서는 이 과정을 더 단순하고 편하게 사용할 수 있는 위젯들을 제공합니다. 뷰페이저(ViewPager)가 그것인데 이 위젯을 사용하는 방법은 뒷부분에서 안내해 드리겠습니다.


