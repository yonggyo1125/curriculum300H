# 레이아웃 인플레이션 이해하기

- 지금까지는 하나의 화면에 대하여 화면을 어떻게 보여줄지 결정하는 XML 레이아웃을 정의했습니다. 그런데 XML 레이아웃은 단순히 XML로 정의된 파일입니다. 즉, 화면을 어떻게 배치하고 구성하는지만 정의할 뿐입니다. 따라서 XML 레이아웃만 만들었다고 화면을 띄우고 앱을 실행할 수는 없습니다. 화면의 기능을 담당하는 소스 코드 파일이 필요하죠. 그래서 지금까지 XML 파일과 소스 코드 파일을 모두 작성하여 실행했던 것입니다.

- 안드로이드 앱은 화면 배치를 알려주는 XML 레이아웃 파일과 화면의 기능을 담당하는 소스 코드 파일로 분리하여 개발해야 합니다. 화면 배치를 담당하는 XML 파일을 별도의 파일로 분리하여 화면의 모양을 따로 만들고 화면의 기능은 소스 코드 파일로 작성하면 관리가 수월해지기 때문입니다. 지금까지는 본문에서 지시하는 대로 XML 파일과 소스 코드 파일을 작성하여 안드로이드 앱을 실행했습니다. 이제는 안드로이드 앱을 실행할 때 XML 레이아웃 파일과 소스 코드 파일이 모두 필요하다는 사실을 꼭 알아두길 바랍니다.

- 그런데 두 개의 XML 레이아웃 파일과 하나의 소스 코드 파일만 있다면 어떤 XML 레이아웃 파일이 소스 파일과 연결되는 것인지 어떻게 알 수 있을까요? 다시 말해, 새로 만든 XML 레이아웃 파일을 화면 기능을 담당하는 소스 코드에 어떻게 설정하는지 궁금해집니다. 다음은 새 프로젝트를 만들 때 만들어지는 소스 파일(MainActivity.java)에 자동으로 입력된 소스입니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/1.%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98/images/image1.png)

- onCreate 메서드 안에 있는 코드를 살펴보세요. super.onCreate 메서드는 부모 클래스의 동일한 메서드를 호출하는 것이니 사실상 setContentView 메서드가 소스 코드의 전부인 셈입니다. 즉, setContentView 메서드가 XML 레이아웃 파일을 연결한다고 추측할 수 있습니다.

- 화면의 기능을 담당하는 소스 파일에는 AppCompatActivity를 상속하는 MainActivity 클래스가 자동으로 만들어집니다. 그런데 MainActivity 클래스가 상속하는 AppCompatActivity에는 화면에 필요한 기능(메서드)들이 들어있습니다. 그 기능 중 하나인 setContentView 메서드에 XML 레이아웃 파일 이름을 파라미터로 전달하여 XML 레이아웃과 소스 코드를 연결한 것이죠. 이런 방식으로 앱을 실행했을 때 화면이 나타나게 되는 것입니다. 이때 setContentView 메서드에 전달하는 XML 레이아웃 파일의 이름은 R.layout.activity_main과 같은 방법으로 확장자 없이 지정해야 합니다.

```
R.layout.레이아웃파일명
```

- 여기서 대문자 R은 프로젝트 창에 보이는 res 폴더를 의미합니다. 그리고 layout은 res 폴더의 layout 폴더를 의미합니다. 따라서 /app/res/layout 폴더 안에 들어 있는 activity_main.xml 파일은 R.layout.activity_main로 표현할 수 있습니다.

- 실제로 앱을 실행하면 XML 레이아웃(화면 배치 정보)을 소스 코드에서 사용합니다. 즉, 앱이 실행될 때 XML 레이아웃의 내용이 메모리에 객체화되고 객체화된 XML 레이아웃을 소스 파일에서 사용합니다. 이렇게 <b>XML 레이아웃의 내용이 메모리에 객체화되는 과정</b>을 '<b>인플레이션(Inflation)</b>'이라고 합니다. 

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/1.%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98/images/image2.png)

- <b>XML 레이아웃은 앱이 실행되는 시점에 메모리에 객체화</b>됩니다. 즉, XML 레이아웃 파일에 \<Button\>태그를 정의해도 앱은 자신이 실행되기 전까지 버튼이 있는지 모릅니다. 이 내용을 확인해 보기 위해 소스 파일에서 setContentView 메서드가 호출되기 전에 XML 레이아웃에 정의된 버튼을 찾아 참조해 보겠습니다.

- 새로운 SampleLayoutInflater 프로젝트를 만들고 activity_main.xml 파일에 들어 있는 텍스트뷰를 삭제한 후 버튼 하나를 새로 추가합니다. 그런 다음 MainActivity.java에서 setContentView 메서드를 호출하는 코드 위에 버튼을 찾아 변수에 할당하고 setOnClickListener 메서드를 호출하는 코드를 입력합니다.

#### SamploLayoutlnflator>/app/java/org.koreait.samplolayoutinflater/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
            }
        });

        setContentView(R.layout.activity_main);
    }
}
```

- 앱을 실행해 볼까요? 그러면 다음과 같이 앱이 중지되며 오류 메시지가 보입니다. 이 오류 메시지는 심각한 오류로 앱 자체를 멈추게 만드는데 그 이유가 바로 메모리에 객체화되지 않은 버튼 객체를 참조하려고 했기 때문입니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/1.%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98/images/image3.png)


- 이렇게 오류가 발생하면 안드로이드 스튜디오의 Logcat 창에 빨간색 오류 로그가 출력됩니다. 만약 앱을 실행했을 때 오류가 발생하면 오류 로그를 먼저 확인해야 합니다. 오류 로그가 많이 나타났지만 지금은 다음과 같은 오류 로그에만 집중하면 됩니다. 이 오류 로그는 소스 코드에서 레이아웃을 객체화하기 전에 레이아웃의 버튼을 참조했기 때문에 발생한 문제(널 포인터 예외)입니다.

```
Caused by: java.lang.NullPointerException: Attempt to invoke virtual method
```

- 이 결과를 통해 액티비티에서 사용하는 <b>setContentView</b> 메서드가 매우 중요하다는 것을 알 수 있습니다. 앞에서도 말했듯이 이 메서드는 <b>화면에 표시할 XML 레이아웃을 지정하거나 화면에 표시할 뷰 객체를 지정하는 역할</b>을 합니다. 즉, 이 메서드로 전달할 수 있는 파라미터는 뷰 객체 또는 XML 레이아웃이 정의된 리소스가 될 수 있습니다. 예를 들어, 자동으로 만들어진 <b>setContentView(R.layout.activity_main)</b> 코드의 경우에도 <b>activity_main.xml 파일을 파라미터로 전달하여 그 XML 레이아웃에 들어 있는 뷰들이 메모리에 객체화될 수 있도록 합니다.</b> 지금은 setContentView 메서드를 activity_main.xml파일을 객체화하기 위해 사용했지만 사실 <b>setContentView 메서드는 화면에 나타낼 뷰를 지정하거나 레이아웃 내용을 메모리에 객체화하는 두 가지 역할</b>을 수행합니다. 다음은 setContentView 메서드가 정의된 형태를 보여줍니다.

```java
public void setContentView(int layoutResID)
public void setContentView (View view [, ViewGroup.LayoutParams params])
```

- 그렇다면 화면 전체에 보여줄 XML 레이아웃이 아니라 별도의 XML 레이아웃 파일로 만든 부분 레이아웃을 소스 파일에 로딩하여 보여줄 수는 없을까요? 화면의 일부분을 차지하는 것을 부분 화면이라고 부를 수 있는데 전체 화면이 아닌 부분 화면도 별도의 XML 레이아웃 파일에 정의한 후 불러와 보여줄 수 있습니다.

- 하지만 setContentView 메서드는 액티비티의 화면 전체(메인 레이아웃)를 설정하는 역할만을 수행합니다. 즉, setContentView 메서드로는 부분 화면(부분 레이아웃)을 메모리에 객체화할 수는 없습니다. 부분 화면을 메모리에 객체화하려면 인플레이터를 사용해야 합니다. 안드로이드는 이를 위해 시스템 서비스로 LayoutInflater라는 클래스를 제공합니다. 그런데 LayoutInflater 클래스는 시스템 서비스로 제공하는 클래스이므로 다음 getSystemService 메서드를 이용하여 LayoutInflater 객체를 참조한 후 사용해야 합니다. 
> 시스템 서비스는 단말이 시작되면서 항상 실행되는 서비스입니다. 

```java 
getSystemService(Context.LAYOUT_INFLATER_SERVICE)
```

- 다음은 일부 화면을 XML 레이아웃으로 정의한 후 인플레이터를 이용하여 메인 레이아웃에 추가하는 과정을 정리한 그림입니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/1.%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98/images/image4.png)

- 메인 레이아웃에 부분 레이아웃이 포함되어 있다면 메인 레이아웃(activity_main.xml)은 소스 코드에서 setContentView(R.layout.activity_main)와 같은 방법으로 객체화하여 화면에 나타냅니다. 그중 일부 화면을 분리한 부분 화면(button.xml)은 LayoutInflater객체를 사용해 뷰그룹 객체로 객체화(인플레이션)한 후 메인 레이아웃에 추가해야 합니다.

- 그러면 앞에서 설명한 내용을 실습을 통해 알아보겠습니다. SampleLayoutInflater 프로젝트 안에 새로운 화면을 하나 더 추가해 보겠습니다. 왼쪽 프로젝트 창에서 app 폴더를 선택한 후 마우스 오른쪽버튼을 누르고 [New → Activity – Empty Activity]를 선택합니다. 새로운 화면을 만들 수 있는 대화상자가 나타나면 Activity Name:에 MenuActivity를 입력하고 [Finish] 버튼을 누릅니다. 그러면 res/layout 폴더에 새로운 XML 레이아웃 파일 하나와 app/java 폴더에 새로운 자바 소스 코드 파일 하나가 만들어집니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/1.%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98/images/image5.png)

- 하나의 화면을 띄우려면 XML 레이아웃 파일 하나와 소스 코드 파일 하나가 쌍으로 만들어져야 한다고 했던 내용이 이제 이해되었을 것입니다. 새로 추가한 activity_menu.xml 파일을 엽니다.

- 최상위 레이아웃을 LinearLayout으로 바꾸고 orientation 속성을 vertical로 설정합니다. 그 안에는 텍스트뷰 하나와 버튼 하나를 추가하고 그 아래에 리니어 레이아웃을 추가합니다. 가장 아래쪽에 추가한 리니어 레이아웃은 orientation 속성이 vertical이 되도록 설정하고 id 속성의 값은 container로 설정합니다. 리니어 레이아웃의 layout width와 layout_height 속성 값은 match_parent로 설정하여 아래쪽 화면을 꽉 채우도록 합니다. 코드를 입력한 다음에 디자인 화면을 확인해 보면 다음과 같은 결과화면을 볼 수 있습니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/1.%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98/images/image6.png)

- 어떤 방식으로 만들었든 글자와 버튼 하나 그리고 리니어 레이아웃이 추가된 화면 레이아웃을 만들면됩니다. 여기에서 텍스트뷰와 버튼의 id 값이 어떻게 설정되었는지 확인해보기 바랍니다. 자동으로 부여되기 때문에 버튼의 id가 button이 아니라 button2가 될 수도 있다는 점에 주의하세요.

- 리니어 레이아웃 안에 추가한 리니어 레이아웃은 부분 화면이 들어갈 공간을 확보하기 위해 넣은 것입니다. 이제 버튼을 클릭했을 때 새로운 XML 레이아웃을 메모리에 객체화하여 안쪽의 리니어 레이아웃에 나타나도록 만들어 보겠습니다.

- 부분 화면으로 추가할 XML 레이아웃도 만들어 보겠습니다. 왼쪽 프로젝트 창에서 /app/res/layout 폴더를 선택한 후 마우스 오른쪽 버튼을 눌러 보이는 팝업 메뉴 중에서 [New → Layout resource file]를선택합니다. 새로운 XML 레이아웃 파일을 만들 수 있는 [New Resource File] 대화상자가 보이면 Filename에 sub1.xml을 입력하고 Root element:에는 LinearLayout을 입력합니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/1.%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98/images/image7.png)

- [OK] 버튼을 클릭하면 sub1.xml이라는 새로운 파일이 만들어집니다. 이 파일 안에 다음 코드를 입력합니다.

#### SampleLayoutInflater>/app/res/layout/sub1.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="부분 화면 1"
        android:textSize="30sp" />

    <CheckBox
        android:id="@+id/checkBox"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="동의합니다" />
    
</LinearLayout>
```

- 부분 레이아웃의 결과 화면디자인 화면에서 살펴보면 텍스트뷰, 체크박스가 각각 1씩 들어있다는 것을 알 수습니다. 들어간 레이아웃로 이 XML 레이아웃도 디자인 화면에서 손쉽게 만들 있습니다. 디자인 화면의 쪽 팔레트에서 텍스트뷰와 체크박스를 끌어다 놓습니다. 텍스트뷰의 text 속성에는 '부분 화면 1'이라는 글자를 입력하고 textSize 속성에는 30sp를 입력합니다. 체크박스의 text 속성에는 "동의합니다"라는 글자를 입력합니다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/1.%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98/images/image8.png)

- 이렇게 만든 부분 레이아웃(sub1.xml)은 전체 레이아웃(activity_menu.xml)의 [추가하기] 버튼을 클릭하여 안쪽 리니어 레이아웃에 추가하기 위해 만든 것입니다. 이제 소스 코드를 입력하여 전체 레이아웃에 부분 레이아웃을 추가해 보겠습니다. activity_menu.xml 파일의 짝인 MenuActivity.java 파일을 수정하겠습니다. 소스 코드를 입력하기 전에 전체 레이아웃, 부분 레이아옷 요소들의 id 값을 반드시 확인하세요. 

#### SampleLayoutinflater>/app/java/org.koreait.samplelayoutinflater/MenuActivity.java

```java

... 생략

public class MenuActivity extends AppCompatActivity {
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        container = findViewById(R.id.container);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub1, container, true);
                CheckBox checkBox = container.findViewById(R.id.checkBox);
                checkBox.setText("로딩되었어요.");
            }
        });
    }
}
```

- inflate 메서드의 파라미터로 R.layout.sub1, container 객체를 전달합니다. 바로 이것이 container를 id로 갖는 리니어 레이아웃 객체에 sub1.xml 파일의 레이아웃을 설정하는 것입니다. 이 과정을 통해 부분 레이아웃(sub1.xml)에 정의된 뷰들이 메모리에 로딩되며 객체화 과정을 거치게 됩니다.

- 이제 부분 레이아웃 파일(sub1.xml)이 객체화되었으므로 부분 레이아웃에 들어있던 텍스트뷰와 체크박스를 findViewById 메서드로 참조할 수 있습니다. 단, 부분 레이아웃은 container 객체에 설정되었으므로 container 객체의 findViewById 메서드를 사용해야 합니다. 즉, 부분 레이아웃의 체크박스는 container.findViewById(...)와 같은 방법으로 참조해야 합니다.

- 마지막으로 앱이 실행되었을 때 MainActivity 화면이 아니라 프로젝트에 새로 추가한 MenuActivity 화면이 나타나도록 매니페스트 파일을 수정해야 합니다. 왼쪽 프로젝트 창에서 /app/manifests 폴더 안에 있는 AndroidManifest.xml 파일을 열고 다음과 같이 \<activity\> 태그 안에 들어 있는 android:name 속성 값을 모두 수정합니다.

```xml

... 생략 

		<activity android:name=".MenuActivity"
				android:label="메뉴 액티비티"
				android:theme="@style/Theme.AppCompat.Dialog">
		</activity>
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```

- 이제 앱을 실행해 보세요. 그러면 다음과 같은 화면을 볼 수 있습니다. 

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/1.%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98/images/image9.png)

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/1.%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98/images/image10.png)

- 왼쪽의 화면은 부분 레이아웃(sub1.xml)을 로딩하기 전이고 오른쪽 화면은 인플레이션을 통해 부분 레이아웃(sub1.xml)을 로딩한 이후의 화면입니다. 이렇게 XML 레이아웃으로 화면 형태를 만드는 것이 일반적입니다. 하지만 XML 레이아웃을 만들지 않고 코드에서 직접 new Button(this)과 같은 코드를 부모 컨테이너에 추가할 수도 있습니다. 그러나 코드에서 직접 추가하면 소스 코드의 양이 많아지고 복잡해지는 문제가 있으므로 되도록 XML 레이아웃을 사용하는 것이 좋습니다.

- 다시 예제로 돌아와 Layoutlnflater 객체의 inflate 메서드에 대해 다시 한 번 살펴보겠습니다.

```java
View inflate (int resource, ViewGroup root)
```

- inflate 메서드는 첫 번째 파라미터로 XML 레이아웃 리소스를, 두 번째 파라미터로 부모 컨테이너를 지정합니다.
- LayoutInflater 객체는 시스템 서비스로 제공되므로 getSystemService 메서드를 호출하는 방법을 사용하거나 LayoutInflater 클래스의 from 메서드를 호출하여 참조할 수도 있습니다.

```java
static LayoutInflater LayoutInflater.from (Context context)
```

- 지금까지 레이아웃을 객체화하는 방법에 대해 알아보았습니다. <b>레이아웃 객체화 과정은 앱이 실행될 때(런타임) 레이아웃 XML 파일에 정의된 내용들이 메모리에 객체화되기 때문에 매우 중요</b>합니다. 그리고 이 과정을 <b>인플레이션</b>이라고 부릅니다.