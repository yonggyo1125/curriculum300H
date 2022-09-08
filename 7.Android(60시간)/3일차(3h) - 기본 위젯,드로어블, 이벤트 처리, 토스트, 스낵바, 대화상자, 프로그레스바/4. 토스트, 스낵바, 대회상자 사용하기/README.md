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

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/4.%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%9A%8C%EC%83%81%EC%9E%90%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/images/image1.png)

- 이제 앱에서 토스트 메시지를 자주 사용하는 경우 필요한 모양과 색상으로 변경하여 사용할 수 있을 것입니다.

* * * 
## 스낵바 보여주기

- 간단한 메시지를 보여줄 때 토스트 대신 스낵바(Snackbar)를 사용하는 경우도 많습니다. activity_main.xml 파일에 새로운 버튼을 하나 더 추가하고 '스낵바 띄우기'라는 글자가 보이도록 코드를 수정합니다. 버튼을 클릭했을 때는 onButton2Clicked 메서드가 호출되도록 onClick 속성을 추가합니다. 그다음 MainActivity.java 파일을 열고 MainActivity 클래스 안에 onButton2Clicked 메서드를 추가합니다.

#### SampleToast>/app/java/org.koreait.sampletoast/MainActivity.java

```

... 생략

public class MainActivity extends AppCompatActivity {

	... 생략

    public void onButton2Clicked(View v) {
        Snackbar.make(v, "스낵바입니다.", Snackbar.LENGTH_LONG).show();
    }
}
```

- 앱을 실행하고 [스낵바 띄우기] 버튼을 누르면 화면 아래쪽에서 메시지가 올라왔다가 사라집니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/4.%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%9A%8C%EC%83%81%EC%9E%90%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/images/image2.png)

- 스낵바는 화면 아래쪽에서 올라오기 때문에 아래쪽의 화면 일부분을 가리지만 토스트와는 다른 방식으로 메시지를 보여줄 수 있다는 장점이 있습니다.

* * * 

## 알림 대화상자 보여주기

- 토스트와 함께 많이 사용되는 알림 대화상자는 사용자에게 확인을 받거나 선택하게 할 때 사용합니다. 보통 알림 대화상자는 사용자의 입력을 받기보다는 일방적으로 메시지를 전달하는 역할을 주로 하며 '예', '아니오'와 같은 전형적인 응답을 처리합니다.

- 새로운 SampleDialog 프로젝트를 만들고 activity_main.xml 파일에 텍스트뷰 하나와 버튼 하나를 추가합니다. 텍스트뷰에는 '버튼을 누르면 대화상자가 뜹니다.'라는 글자가 보이게 하고, 버튼에는 '띄우기'라는 글자가 보이도록 합니다. 텍스트뷰의 글자 크기는 25sp로 설정합니다. 그리고 버튼의 id는 button, 텍스트뷰의 id는 textView로 자동 부여되었는지 다시 한 번 확인합니다. 그다음 MainActivity. java 파일을 열어 다음 코드를 추가합니다. 이 코드는 버튼을 눌렀을 때 대화상자를 보여주는 코드입니다.

#### SampleDialog>/app/java/org.koreait.sampledialog/MainActivity.java

```java
package org.koreait.sampledialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showMessage();
            }
        });
    }

    private void showMessage() {
        // 대화상자를 만들기 위한 빌더 객체 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // 예 버튼 추가
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "예 버튼이 눌렸습니다.";
                textView.setText(message);
            }
        });

        // 취소 버튼 추가
        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "취소 버튼이 눌렸습니다.";
                textView.setText(message);
            }
        });

        // 아니오 버튼 추가 
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "아니오 버튼이 눌렸습니다.";
                textView.setText(message);
            }
        });
        
        // 대화상자 객체 생성 후 보여주기
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
```

- AlertDialog 클래스를 입력하면 글자가 빨간색으로 표시되면서 [Alt] + [Enter]를 입력하라는 작은 팝업 메시지가 표시됩니다. 이것은 AlertDialog 클래스를 찾을 수 없을 때나 AlertDialog 클래스가 여러 개 있을 때 표시됩니다. [Alt] + [Enter]를 누르면 두 개의 AlertDialog 클래스가 있으니 그중에 하나를 고르라는 팝업이 표시됩니다.

- AlertDialog는 기본 API에 포함된 것 외에 appcompat 패키지에 포함된 것도 있습니다. appcompat 패키지는 예전 버전의 OS에서도 사용할 수 있도록 제공되는 것이므로 여기에서는 androidx.appcompat.app 패키지 안에 있는 것으로 선택합니다.

- AlertDialog 클래스는 알림 대화상자를 보여주는 가장 단순한 방법을 제공합니다. 알림 대화상자의 타이틀은 setTitle 메서드로 설정하고 내용은 setMessage 메서드를 사용해 설정합니다. 아이콘은 setIcon 메서드로 설정할 수 있습니다. 만약 안드로이드 기본 API에 포함된 여러 개의 아이콘 중 하나를 사용하고 싶다면 android.R.drawable을 코드에 입력합니다. 그러면 그 안에 들어 있는 여러 아이콘 중 하나를 선택할 수 있습니다. '예', '아니오'와 같은 버튼의 설정은 setPositiveButton과 setNegativeButton 메서드를 사용합니다. 이 메서드에는 OnClickListener를 설정할 수 있으며 이 버튼이 눌릴 때 텍스트뷰에 어떤 버튼이 선택되었는지 표시합니다.

- 이 앱을 실행하고 버튼을 누르면 다음과 같은 대화상자가 표시되며, 각각의 버튼을 누르면 대화상자가 닫히면서 텍스트뷰에 결과를 표시합니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/4.%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%9A%8C%EC%83%81%EC%9E%90%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/images/image3.png)

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/4.%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%9A%8C%EC%83%81%EC%9E%90%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/images/image4.png)

- 이제 토스트나 스낵바를 사용해 간단한 메시지를 보여주거나 알림 대화상자를 사용해 사용자에게 택할 수 있는 기회를 주는 방법을 알아보았습니다. 앱을 개발하는 중간 중간 필요한 메시지를 보여주고 싶다면 이 뷰들을 사용하기 바랍니다. 앞으로 복잡한 위젯들을 사용할 때도 토스트 메시지나 알림 대화상자로 필요한 상태나 알림 정보를 표시할 필요가 자주 생기게 될 것입니다.

> 실제 앱에서는 대화상자의 모양을 바꿔서 보여주어야 할 때가 종종 있습니다. 예를 들어, 대화상자 안에 에디트텍스트가 들어가야 하는 경우, 프래그먼트를 사용해 원하는 형태의 대화상자 화면을 만들어야 합니다. 따라서 이 장의 뒷부분에서 설명하는 프래그먼트를 먼저 이해하는 것이 필요합니다.