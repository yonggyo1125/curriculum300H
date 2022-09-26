# 시크바 사용하기

- 프로그레스바처럼 사용할 수 있는 대표적인 상태 표시 위젯으로 시크바(SeekBar)가 있습니다. 시크바는 프로그레스바를 확장하여 만든 것인데, 프로그레스바의 속성을 갖고 있으면서 사용자가 값을 조정할 수 있게 합니다. 즉, 시크바의 일부분을 터치하면 터치한 부분으로 즉시 이동할 수 있는 방법을 제공하며, 가운데 있는 핸들(Handle)을 드래그하여 좌우로 이동시킬 수도 있습니다. 따라서 시크바를 사용하면 동영상 재생 시 볼륨 조절이나 재생 위치 조절이 가능합니다. 이 위젯은 프로그레스바를 상속한 것이라서 프로그레스바의 속성을 그대로 사용할 수 있습니다. 그리고 추가적으로 OnSeekBarChangeListener라는 리스너를 설정하여 이벤트를 처리할 수 있습니다. 이 리스너의 메서드들은 시크바의 상태가 바뀔 때마다 호출되며 다음과 같은 메서드들이 정의되어 있습니다.

```java
void onStartTrackingTouch (SeekBar seekBar)
void onStopTrackingTouch (SeekBar seekBar)
void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser)
```

- 이중에서 onProgressChanged 메서드는 사용자에 의해 변경된 progress 값을 전달받을 수 있습니다.fromUser 파라미터를 사용하면 변경된 값이 사용자가 입력한 것인지 아니면 코드에서 변경된 것인지구별할 수 있습니다.

- 이제 시크바로 단말의 화면 밝기를 조정하는 기능을 만들어 보겠습니다. SampleSeekbar라는 이름의새로운 프로젝트를 만들고 패키지 이름은 org.koreait.seekbar로 수정합니다. 프로젝트 창이 열리면 activity_main.xml 파일을 열고 레이아웃을 구성합니다.

- 최상위 레이아웃을 LinearLayout으로 변경한 후 orientation 속성 값을 vertical로 설정합니다. 기존에 있던 텍스트뷰를 삭제하고 팔레트에서 Widgets 폴더 안에 있는 SeekBar를 화면에 끌어다 놓습니다. SeekBar에는 max 속성이 있어 최댓값을 설정할 수 있습니다. max 속성 값에 100을 입력합니다. 시크바의 아래쪽에 텍스트뷰를 하나 추가하고 '변경된 값'이라고 글자를 수정합니다. 텍스트뷰의 layout_margin 속성 중 top 속성의 값을 20dp로 입력하고 textSize 속성 값은 30sp로 입력합니다.

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

    <SeekBar
        android:id="@+id/seekBar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:max="100" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="변경된 값"
        android:textSize="30sp" />
</LinearLayout>
```

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/7.%20%EC%8B%9C%ED%81%AC%EB%B0%94/images/image1.png)

- 이제 MainActivity.java 파일을 열고 시크바의 값이 바뀌었을 때 그 값을 텍스트뷰에 표시하도록 다음코드를 입력합니다.

#### SampleSeekbar>/app/java/org.koreait.seekbar/MainActivity.java

```java
package org.koreait.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        SeekBar seekBar = findViewById(R.id.seekBar);

        // 시크바에 리스너 설정하기
         seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                 setBrightness(i);
                 textView.setText("변경된 값 : " + i);
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {}

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {}
         });
    }

    private void setBrightness(int value) {
        if (value < 10) {
            value = 10;
        } else if (value > 100) {
            value = 100;
        }

        // 윈도우 매니저를 이용해 화면 밝기 설정하기
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float) value / 100;
        getWindow().setAttributes(params);
    }
}
```

- 이 코드는 XML 레이아웃에 들어 있는 시크바 객체를 참조한 후 그 값이 바뀔 때마다 리스너를 통해 알 수 있도록 했습니다. 변경된 값은 화면 밝기를 조정하는 데 사용되는데, 화면 밝기는 윈도우 매니저로 설정할 수 있습니다. getWindow 메서드를 사용해 참조한 객체의 윈도우 관련 정보를 getAttributes로 확인하거나 새로 설정할 수 있습니다.
- 화면 밝기를 설정하는 속성은 screenBrightness이며 getAttributes 메서드로 참조한 속성 정보에 새로운 값을 지정한 후 setAttributes 메서드를 사용해서 설정합니다. 시크바에 설정한 리스너는 시크바의 값이 바뀔 때마다 onProgressChanged 메서드가 호출되도록 되어 있으므로 그 메서드 안에서 화면 밝기를 지정하는 setBrightness 메서드를 호출한 후 텍스트뷰에 현재 밝기 수준을 텍스트로 표시합니다.

- 앱을 실행하면 시크바가 보이고 시크바를 스크롤해서 움직이면 움직인 값만큼 단말기 화면의 밝기가 조절됩니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/7.%20%EC%8B%9C%ED%81%AC%EB%B0%94/images/image2.png)

