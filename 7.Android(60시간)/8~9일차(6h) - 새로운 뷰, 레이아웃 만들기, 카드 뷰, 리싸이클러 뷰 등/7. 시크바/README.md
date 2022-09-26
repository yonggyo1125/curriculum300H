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

