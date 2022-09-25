# 페이지 슬라이딩 사용하기

- 앞서 설명한 애니메이션은 사용자에게 좀 더 동적인 화면을 보여줄 수 있습니다. 그리고 때로는 데이터를 좀 더 효율적으로 보여주기 위한 UI를 구성할 때 사용되기도 합니다. 그중에 페이지 슬라이딩은 버튼을 눌렀을 때 보이지 않던 뷰가 슬라이딩 방식으로 나타나는 기능입니다. 여러 뷰를 중첩해 두었다가 하나씩 전환하면서 보여주는 방식에 애니메이션을 결합한 것이죠. 대표적인 예시는 여러분이 이미 만들었던 바로가기 메뉴입니다. 바로가기 메뉴는 햄버거 모양의 목록 아이콘을 눌렀을 때 왼쪽 또는 오른쪽에서 슬라이딩 방식으로 표시됩니다. 바로가기 메뉴는 API에서 제공되는 기능을 그대로 사용하면 만들 수 있습니다. 하지만 이 기능을 직접 만들어 볼 수도 있습니다. 지금부터 페이지 슬라이딩에 대해서 살펴보겠습니다.

- 다음은 페이지 슬라이딩을 구현하는 전형적인 방법을 그림으로 표현한 것입니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/5.%20%ED%8E%98%EC%9D%B4%EC%A7%80%20%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%94%A9/images/image1.png)

- 만약 두 개의 뷰를 중첩시켰다면 위쪽의 뷰는 보이거나 보이지 않는 상태로 만들 수 있습니다. 오른쪽에서 왼쪽으로 보이는 애니메이션을 만들 때는 애니메이션 액션 정보를 XML에 저장한 후 로딩하여 위쪽의 뷰에 적용합니다. 왼쪽에서 오른쪽으로 닫히는 애니메이션을 만들 때는 마찬가지 방법으로 적용하되 애니메이션 액션 정보가 반대 방향으로 정의됩니다. 이 두 가지 모두 뷰의 이동과 관련되므로 애니메이션을 위한 XML에는 \<translate\> 태그를 사용합니다.

- SamplePageSliding 프로젝트를 만들고 패키지 이름은 org.koreait.sliding으로 수정해서 새로운 프로젝트를 만듭니다. 새로운 프로젝트 창이 열리면 activity_main.xml 파일을 수정합니다.

- 먼저 최상위 레이아웃을 FrameLayout으로 바꿉니다. 프레임 레이아웃 안에는 세 개의 LinearLayout을 추가하고 중첩시킵니다. 첫 번째 레이아웃은 가로와 세로 크기 모두 match_parent로 지정하여 화면 전체를 채우도록 하였는데 파란색 배경색을 지정하여 다른 것과 구분되도록 만듭니다. 두 번째 레이아웃은 슬라이딩으로 보여줄 뷰가 되는데 마찬가지로 노란색 배경색을 지정하여 화면 전체를 채우는 뷰와 구분되도록 합니다. 이 뷰는 layout_width의 속성 값을 숫자로 지정하여 화면 전체가 아니라 일부를 채우도록 설정합니다. 이 뷰는 사용자가 원하는 시점에 보여야 하므로 처음에는 visibility 속성을 gone으로 설정하여 보이지 않도록 합니다. 세 번째 뷰는 버튼을 포함하고 있는데 이 뷰의 배경을 투명하게 하여 버튼만 보이도록 설정합니다. 결국 앱을 처음 실행하면 전체를 채우고 있는 첫 번째 뷰와 버튼이 포함된 세 번째 뷰가 보이게 되고 세 번째 뷰의 경우에는 배경 부분이 보이지 않으므로 첫 번째 뷰위에 버튼이 하나 있는 형태로 보이게 됩니다. 버튼을 담고 있는 세 번째 레이아웃의 layout_gravity의속성 값을 right\|center_vertical로 설정하여 화면의 오른쪽 중간에 보이게 합니다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ff5555ff"
        android:orientation="vertical">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Base Area"
            android:textColor="#ffffffff" />
    </LinearLayout>

    <LinearLayout
        android:id="@+id/page"
        android:layout_width="200dp"
        android:layout_height="match_parent"
        android:layout_gravity="right"
        android:background="#ffffff66"
        android:orientation="vertical"
        android:visibility="gone">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Area #1"
            android:textColor="#ff000000" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Area #2"
            android:textColor="#ff000000" />

    </LinearLayout>

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="right|center_vertical"
        android:background="#00000000"
        android:orientation="vertical">

        <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Open" />

    </LinearLayout>
    
</FrameLayout>
```

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/5.%20%ED%8E%98%EC%9D%B4%EC%A7%80%20%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%94%A9/images/image2.png)

- 다음은 이렇게 정의한 XML 레이아웃을 이용해 페이지 슬라이딩 기능을 구현하는 메인 액티비티 코드입니다. MainActivity.java 파일을 열고 코드를 입력합니다.

#### SamplePageSliding>/app/java/org.koreait.sliding/MainActivity.java

```java
package org.koreait.sliding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean isPageOpen = false;

    Animation translateLeftAnim;
    Animation translateRightAnim;

    LinearLayout page;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);

        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        // 애니메이션 리스너 설정하기
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPageOpen) {
                    page.startAnimation(translateRightAnim);
                } else {
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeftAnim);
                }
            }
        });
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        // 애니메이션이 끝났을 때 호출되는 메서드 안에 코드 넣기
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                page.setVisibility(View.INVISIBLE);

                button.setText("Open");
                isPageOpen = false;
            } else {
                button.setText("Close");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationStart(Animation animation) {}

        @Override
        public void onAnimationRepeat(Animation animation) {}
    }
}
```

- 버튼을 누르면 서브 화면이 애니메이션으로 보이도록 두 개의 애니메이션 액션을 만들었습니다. /app/res 폴더 안에 anim 폴더를 만들고 그 안에 translate_left.xml과 translate_right.xml 파일을 생성합니다. 두 파일의 내용은 \<translate\> 태그를 사용해서 좌측으로 이동하는 것과 우측으로 이동하는 애니메이션을 정의합니다. 우측으로 이동하는 애니메이션은 translate_left에서 fromXDelta와 toXDelta의 값을 바꾸면 됩니다.

#### SamplePageSliding>/app/res/anim/translate_left.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:interpolator="@android:anim/accelerate_decelerate_interpolator">
    <translate
        android:fromXDelta="100%p"
        android:toXDelta="0%p"
        android:duration="500"
        android:repeatCount="0"
        android:fillAfter="true"
        />
</set>
```

#### SamplePageSliding>/app/res/anim/translate_right.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:interpolator="@android:anim/accelerate_decelerate_interpolator">
    <translate
        android:fromXDelta="0%p"
        android:toXDelta="100%p"
        android:duration="500"
        android:repeatCount="0"
        android:fillAfter="true"
        />
</set>
```

- 이렇게 정의된 액션 정보는 AnimationUtils 클래스의 loadAnimation 메서드로 로딩됩니다. 이 메서드로 로딩한 애니메이션 객체를 참조한 후 버튼을 누를 때마다 애니메이션이 번갈아 적용됩니다. 서브화면은 XML 레이아웃에서 visibility 속성이 gone으로 설정되어 있어 화면에서 보이지 않았으므로 좌측으로 슬라이딩되어 나올 때는 visible로 만들어 화면에 보이게 합니다. 그리고 우측으로 슬라이딩될 때는 invisible이나 gone으로 만들어 화면에서 보이지 않도록 합니다. 서브 화면이 보이거나 보이지 않게 되는 시점은 애니메이션이 끝나는 시점이어야 합니다. 그렇다면 애니메이션이 끝나는 시점을 알아야 할 텐데, 애니메이션이 끝나는 시점은 AnimationListener 인터페이스를 구현한 객체를 Animation 객체의 setAnimationListener 메서드로 설정하면 알 수 있습니다. 이 프로젝트에서 만든 코드를 보면 AnimationListener를 구현하는 SlidingPageAnimationListener 클래스를 정의한 후 이 클래스의 인스턴스 객체를 생성하여 Animation 객체에 설정했습니다. 이 AnimationListener에는 onAnimationEnd 메서드가 정의되어 있으며 애니메이션이 끝날 때 자동으로 호출됩니다.

- 다음은 이 앱을 실행한 화면입니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/5.%20%ED%8E%98%EC%9D%B4%EC%A7%80%20%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%94%A9/images/image3.png)

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/5.%20%ED%8E%98%EC%9D%B4%EC%A7%80%20%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%94%A9/images/image4.png)

- 오른쪽 중간에 위치한 버튼을 누를 때마다 노란색 바탕의 서브 화면이 나타났다 사라졌다 하는 것을 볼 수 있습니다. 파랑 바탕으로 전체를 채우고 있는 레이아웃에는 'Base Area' 텍스트가 들어 있으며 노랑 바탕의 서브 화면은 'Area #1'과 'Area #2'로 나뉘어 있는데 실제 앱을 만들 때는 이 각각의 영역에 필요한 뷰를 넣어 화면을 구성할 수 있습니다. 페이지 슬라이딩 방식으로 화면을 보여주면 좀 더 많은 내용을 한 화면에 보여줄 있다는 장점이 있습니다. 

