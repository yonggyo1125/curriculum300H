# 스크롤뷰 사용하기

- 스크롤뷰는 추가된 뷰의 영역이 한눈에 다 보이지 않을 때 사용됩니다. 이때 단순히 스크롤뷰를 추가하고 그 안에 뷰를 넣으면 스크롤이 생기게 됩니다.

- 스크롤뷰 안에 이미지를 넣고 이미지에 스크롤이 나타나는지 알아보기 위해 SamplescrollView 프로젝트를 새로 만듭니다. 새 프로젝트 창이 열리면 왼쪽 프로젝트 영역에서 /app/res/drawable 폴더를 찾은 후 그 안에 두 개의 이미지를 넣어줍니다. 이미지는 각자 원하는 것으로 넣어도 되고 학습 소스에서 제공되는 이미지를 넣어도 됩니다. res/drawable 폴더 안에 이미지를 넣으면 XML 레이아웃 파일에서 사용할 수 있습니다.

> <b>이미지의 파일 이름에는 대문자를 사용할 수 없습니다.</b><br>앞에서도 설명했지만, /app/res/drawable 폴더 안에 이미지를 넣을 때 이미지의 이름에 대문자가 포함되어 있으면 오류가 발생합니다. 또한 특수 문자는 \_ 기호 정도만 넣을 수 있으니 다른 특수 문자는 사용하지 않도록 합니다. 그리고 이미지 파일 이름의 첫 글자는 숫자가 아닌 알파벳이어야 합니다.


- activity_main.xml 파일을 열고 스크롤뷰를 추가합니다.

- 스크롤뷰는 기본적으로 수직 방향의 스크롤을 지원합니다. 만약 수평 방향의 스크롤을 사용하려면 HorizontalScrollView를 사용하면 됩니다. 이 레이아웃에서는 이미지뷰에 설정된 이미지가 큰 경우, 수평과 수직 스크롤을 모두 나타나도록 HorizontalScrollView 안에 ScrollView을 추가한 후 다시 그 안에 이미지뷰를 추가했습니다. 이때 이미지뷰에 보이는 이미지가 화면 영역을 벗어날 경우에는 스크롤이 나타납니다. 
- 이미지뷰 위쪽 버튼에는 onClick 속성을 추가하고 그 값으로 onButton1Clicked를 입력했습니다. 이렇게 하면 버튼을 클릭했을 때 XML 레이아웃 파일과 매칭되는 소스 파일에서 onButton1Clicked라는 이름의 메서드를 찾아 자동으로 실행합니다.

#### activity_main.xml

```xml 
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:id="@+id/button"
        android:onClick="onButton1Clicked"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="이미지 바꾸어 보여주기" />

    <HorizontalScrollView
        android:id="@+id/horScrollView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" >

        <ScrollView
            android:id="@+id/scrollView"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <ImageView
                android:id="@+id/imageView"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />

        </ScrollView>

    </HorizontalScrollView>

</LinearLayout>
```

- 이제 자바 소스 코드를 수정합니다.  XML 레이아웃은 액티비티라는 화면을 위한 것이며, 이 액티비티라는 화면은 소스 코드가 있어야 동작합니다. 따라서 소스 코드에서 어떤 XML 레이아웃을 화면에 보여줄 것인지를 setContentView 메서드로 지정합니다. MainActivity.java 파일을 열고 다음 코드를 입력합니다.

#### SampleScrollView>/app/java/it.koreait.sample.scrollview/MainActivity.java 

```java
package org.koreait.samplescrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ImageView imageView;
    BitmapDrawable bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 레이아웃에 정의된 뷰 객체 참조
        scrollView = findViewById(R.id.scrollView); 
        imageView = findViewById(R.id.imageView);

        // 수평 스크롤바 사용 기능 설정
        scrollView.setHorizontalScrollBarEnabled(true);

        // 리소스의 이미지 참조
        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();
        
        // 이미지 리소스와 이미지 크기 설정
        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }

    public void onButton1Clicked(View v) {
        changeImage();
    }

    // 다른 이미지 리소스로 변경
    private void changeImage() {
        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }
}
```

- XML 레이아웃을 만들 때 스크롤뷰와 이미지뷰에 id 값으로 scrollView imageView를 지정했으므로 먼저 코드에서 그 id 값으로 객체를 찾아야 사용할 수 있습니다. findViewById 메서드를 호출하면서 R.id.scrollView와 R.id.imageView라는 id 값을 파라미터로 전달하여 두 객체를 참조합니다.

- 이 프로젝트에는 두 개의 큰 이미지를 사용합니다. 책에서 제공하는 이미지를 찾아 탐색기에서 프로젝트 폴더 안에 있는 app/src/main/res/drawable 폴더에 복사합니다. 이 이미지들은 안드로이드 스튜디오의 프로젝트 창에서는 /res/drawable 폴더 아래에 표시됩니다. 프로젝트에 추가된 이미지들은 getDrawable 메서드를 이용해 코드에서 BitmapDrawable 객체로 만들어집니다. 이 getDrawable 메서드는 Resources 객체에 정의되어 있으며 액티비티에 정의된 getResources 메서드를 이용하면 Resources 객체를 참조할 수 있습니다. 

- 학습 소스에서 두 개의 이미지는 그 크기가 화면보다 커서 스크롤뷰 안에 들어가면 스크롤이 생깁니다. BitmapDrawable 객체의 getIntrinsicWidth getIntrinsicHeight 메서드를 사용하면 원본 이미지의 가로와 세로 크기를 알 수 있으며, 이렇게 알아낸 가로와 세로 크기 값은 이미지뷰에 설정된 LayoutParams 객체의 width height 속성으로 설정할 수 있습니다.

> 이미지뷰에 이미지를 설정하는 가장 간단한 방법은 소스 코드에서 setImageResource 메서드를 사용하는 것입니다. 이것은 XML 레이아웃 파일에서 \<ImageView\> 태그의 속성으로 src 속성을 추가하면서 그 값으로 R.drawable.image01처럼 이미지 파일을 지정하는 것과 같은 결과를 보여줍니다. 그런데 이미지뷰에 이미지를 설정하는 방법은 여러 가지가 있습니다. 대표적인 것이 <b>setImageDrawable</b> 메서드입니다. 이 메서드는 이미지 파일을 Drawable 객체로 만든 후 이미지뷰에 설정할 때 사용합니다. 그런데 이 메서드를 사용하면 이미지뷰가 이미지의 크기를 화면 크기에 맞게 자동으로 줄입니다. 따라서 원본 이미지의 크기 그대로 이미지뷰에 보일 수 있게 이미지의 가로와 세로 크기를 직접 설정해야 스크롤뷰 안에서 스크롤을 사용해 원본 이미지를 볼 수 있습니다.

- changeImage 메서드는 화면 위쪽에 추가된 버튼을 눌렀을 때 이미지뷰에 들어갈 이미지를 바꿔주는 역할을 하는데 메인 액티비티를 초기화하는 onCreate 메서드 안의 코드와 유사한 코드가 들어가 있는 것을 알 수 있습니다.

- 이렇게 만든 메인 액티비티를 실행하면 다음과 같은 화면을 볼 수 있습니다.

![image53](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image53.png)

![image54](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image54.png)

- 첫 번째 화면은 앱을 실행했을 때 보이는 것으로 이미지의 일부분만 보입니다. 두 번째는 이미지의 오른쪽 아래 끝부분으로 스크롤하여 이동한 화면인데 이동할 때마다 수평과 수직 스크롤이 생기는 것을 볼 수 있습니다. 위쪽에 있는 \[이미지 바꾸어 보여주기\] 버튼을 누르면 이미지뷰에 보이는 이미지가 바뀌게 됩니다.

