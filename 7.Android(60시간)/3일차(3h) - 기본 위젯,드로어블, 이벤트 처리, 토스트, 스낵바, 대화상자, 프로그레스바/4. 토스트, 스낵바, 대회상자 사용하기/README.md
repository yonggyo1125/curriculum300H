# 토스트, 스낵바 그리고 대화상자 사용하기

- 코드를 만들어서 실행하다 보면 중간 중간 디버깅 메시지를 확인해 보거나 사용자에게 간단한 메시지를 보여줘야 하는 경우가 생깁니다. 디버깅을 위해서는 일반적으로 Log 클래스를 사용해서 로그를 출력할 수 있습니다. 이 로그는 안드로이드 스튜디오 하단에 보이는 Logcat 창에서 확인할 수 있습니다.그리고 화면에 간단히 뿌려지는 정보를 보고 싶은 경우에는 앞에서 자주 보았던 토스트 메시지를 사용합니다.

- 토스트는 간단한 메시지를 잠깐 보여주었다가 없어지는 뷰로 앱 위에 떠 있는 뷰라고 할 수 있습니다.이것은 대화상자와 함께 사용자에게 필요한 정보를 알려주는 역할을 하는 대표적인 위젯입니다. 토스트는 포커스를 받지 않으므로 대화상자보다 더 쉽고 간단하게 사용할 수 있으며 디버깅 등의 목적으로도 사용할 수 있습니다. 특히, 앱이 화면에서 사라지더라도 필요한 메시지가 그대로 표시되므로 앱의상태와 관계없이 보여줄 수 있다는 장점이 있습니다.

- 토스트 메시지를 만들어서 보여주는 전형적인 방법은 다음과 같습니다.

```java
Toast.makeText(Context context, String message, int duration).show();
```

- Context 객체는 일반적으로 Context 클래스를 상속한 액티비티를 사용할 수 있으며 액티비티를 참조할 수 없는 경우에는 getApplicationContext 메서드를 호출하면 Context 객체가 반환됩니다. 토스트를 보여주고 싶다면 보여주려는 메시지와 디스플레이 시간을 파라미터로 전달하여 객체를 생성한 후에 show 메서드를 호출하면 됩니다. 토스트는 그 위치나 모양을 바꿀 수 있는데 다음의 두 메서드는 토스트의 위치와 여백을 지정할 수 있도록 합니다.

```java
public void setGravity(int gravity, int xOffset, int yOffset)
public void setMargin(float horizontalMargin, float verticalMargin)
```

- setGravity 메서드는 토스트 뷰가 보이는 위치를 지정하는 데 사용됩니다. 첫 번째 파라미터인 gravity값은 Gravity.CENTER와 같이 정렬 위치를 지정합니다. setMargin 메서드는 외부 여백을 지정하는 것으로 이 값을 이용해 토스트를 중앙이나 우측 하단에 배치할 수 있습니다.

> API 30 이상부터 글자만 들어 있는 토스트의 위치는 바꿀 수 없습니다. 항상 화면의 아래쪽 가운데에 보이게 됩니다. 토스트의 모양을 바꾼 경우에만 토스트가 보이는 위치를 바꿀 수 있으니 참고하세요.

## 토스트 모양과 위치 바꿔 보여주기

- 이번에는 토스트의 모양을 바꾼 후 위치를 바꿔보겠습니다. 새로운 SampleToast 프로젝트를 만듭니다. activity_main.xml 파일을 열고 화면에 버튼을 하나 추가하고 '모양 바꿔 띄우기'라는 글자가 보이도록 text 속성 값을 설정합니다. 그리고 버튼이 눌렸을 때 onButton1Clicked 메서드가 호출되도록 버튼의 onClick 속성 값을 설정합니다. 그다음 MainActivity.java 파일을 열고 다음과 같이 onButton1Clicked 메서드를 추가합니다.

#### SampleToast>/app/java/org.koreait.sampletoast/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v) {
        // 레이아웃 인플레이터 객체 참조
        LayoutInflater inflater = getLayoutInflater();

        // 토스트를 위한 레이아웃 인플레이션
        View layout = inflater.inflate(
                R.layout.toastborder,
                (ViewGroup) findViewById(R.id.toast_layout_root)
        );

        TextView text = layout.findViewById(R.id.text);

        // 토스트 객체 생성
        Toast toast = new Toast(this);
        text.setText("모양 바꾼 토스트");
        toast.setGravity(Gravity.CENTER, 0, -100);
        // 토스트가 보이는 뷰 설정
        toast.setView(layout);
        toast.show();
    }
}

```

- 버튼을 눌렀을 때 처리되는 부분을 보면 LayoutInflater 객체를 사용해 XML로 정의된 레이아웃(toastborder.xml)을 메모리에 객체화하고 있습니다. 이것은 XML 레이아웃을 메모리에 로딩하는 데 사용됩니다. 액티비티를 위해 만든 XML 레이아웃 파일은 setContentView 메서드를 사용해 액티비티에 설정되지만 토스트만을 위한 레이아웃을 정의한다면 이 레이아웃은 액티비티를 위한 것이 아니기 때문에 LayoutInflater 객체를 사용해 직접 메모리에 객체화해야 합니다. 이에 대해서는 나중에 다시 자세하게 살펴봅니다.

- 여기서는 XML 레이아웃을 메모리에 로딩한다고 생각하면 됩니다. 앞서 살펴본 코드에는 그 대상이 되는 레이아웃의 이름이 R.layout.toastborder로 되어 있습니다. 그러면 /app/res/layout 폴더에 toastborder.xml이라는 이름의 새로운 레이아웃 파일을 만들고 다음 코드를 입력합니다.


#### SampleToast>/app/res/layout/toastborder.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/toast_layout_root"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal"
    android:padding="10dp">

    <TextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="20dp"
        android:textSize="32sp"
        android:background="@drawable/toast" />
</LinearLayout>
```

- 이 XML 레이아웃은 새롭게 만들 토스트의 형태를 정의한 것으로 이 레이아웃을 이용해 토스트 메시지가 보이게 됩니다. 이 레이아웃 안에는 텍스트뷰 태그가 하나 정의되어 있고 그 ID는 text로 되어있는데 토스트 뷰를 위한 레이아웃은 항상 이 형태로 정의되어야 합니다. 배경으로 지정된 그리기 객체는 @drawable/toast이므로 /app/res/drawable 폴더 안에는 toast라는 이름을 가진 이미지가 있어야 합니다. 하지만 이 toast라는 이름의 파일은 이미지가 아니라 XML 파일입니다. toast.xml이라는 파일을 /app/res/drawable 폴더에 만든 후 드로어블 객체를 정의합니다. toast.xml 파일은 토스트의 색상 등을 지정할 수 있게 다음 코드처럼 셰이프 드로어블로 수정합니다.


#### SampleToast>/app/res/drawable/toast.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <stroke
        android:width="4dp"
        android:color="#ffffff00"
        />
    <solid
        android:color="#ff883300"
        />

    <padding
        android:left="20dp"
        android:top="20dp"
        android:right="20dp"
        android:bottom="20dp"
        />
    <corners
        android:radius="15dp"
        />
</shape>
```

- 이렇게 정의한 XML 정보는 자바 코드의 setView 메서드를 이용해 토스트 객체에 설정됩니다. 이 앱을 실행하고 [모양 바꿔 띄우기] 버튼을 누르면 다음과 같은 화면을 볼 수 있습니다.



- 이제 앱에서 토스트 메시지를 자주 사용하는 경우 필요한 모양과 색상으로 변경하여 사용할 수 있을 것입니다.
