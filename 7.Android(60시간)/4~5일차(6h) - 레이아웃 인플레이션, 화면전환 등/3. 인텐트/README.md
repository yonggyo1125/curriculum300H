# 인텐트 살펴보기

- 여러분이 지금까지 실습했던 내용을 다시 떠올려 보세요. <b>① 새로운 화면에 필요한 XML 레이아웃을 만들고</b> <b>② 소스 코드를 만든 후</b> <b>③ 그 화면을 띄우는 과정</b>을 실습했습니다. 바로 앞의 실습에서는 새로운 화면을 띄우며 인텐트를 만들고 인텐트의 파라미터로 액티비티 클래스의 객체를 전달해 액티비티를 화면에 띄웠습니다. 또한 앱을 만들 때 인텐트 안에 웹페이지 주소나 전화번호를 URI 객체로 만들어 넣어 웹브라우저나 전화 걸기 화면을 띄우기도 했습니다.


- 바로 앞 실습에서 인텐트는 다른 액티비티를 띄우거나 기능을 동작시키기 위한 수단으로 사용했습니다. 즉, 무언가 작업을 수행하기 위해 사용되는 일종의 명령 또는 데이터를 전달하는데 사용했습니다. 이 과정을 조금 더 자세히 설명하면 인텐트를 만든 후 <b>startActivity</b>나 <b>startActivityForResult</b> 메서드를 호출하면서 전달하는 방법으로 인텐트를 시스템에 전달했습니다. 그러면 시스템은 그 인텐트 안에 들어 있는 명령을 확인하고 여러분이 만든 액티비티 또는 이미 단말에 설치되어 있는 다른 앱들의 액티비티를 띄운 것이죠. 이렇게 인텐트는 여러분이 만든 앱 구성 요소가 해야 할 일을 지정합니다.

## 인텐트의 역할과 사용 방식

- 인텐트에 대해 조금 더 자세히 알아보겠습니다. android.content 패키지 안에 정의되어 있는 인텐트는 앱 구성 요소 간에 작업 수행을 위한 정보를 전달하는 역할을 합니다. 다른 앱 구성 요소에 인텐트를 전달할 수 있는 대표적인 메서드는 다음과 같습니다.

```java 
startActivity() 또는 startActivityResult()
startService() 또는 bindService()
broastcastIntent()
```

- startActivity 메서드는 액티비티를 화면에 띄울 때 사용되며, startService 메서드는 서비스를 시작할 때 그리고 broadcastIntent 메서드는 인텐트 객체를 브로드캐스팅 방식으로 전송할 때 사용됩니다. 이 메서드들을 호출할 때 인텐트가 파라미터로 전달되며 이렇게 전달된 파라미터는 앱 구성요소인 액티비티, 서비스, 브로드캐스트 수신자로 전달될 수 있습니다.

- 인텐트의 기본 구성 요소는 '<b>액션(Action)</b>'과 '<b>데이터(Data)</b>'입니다. 액션은 수행할 기능이고 데이터는 액션이 수행될 대상의 데이터를 의미합니다. 대표적인 액션으로는 ACTION_VIEW, ACTION_EDIT 등을 들 수 있습니다. 예를 들어, 인텐트 객체를 만들 때 ACTION_VIEW와 함께 웹페이지 주소를 전달하면 단말 안에 설치되어 있던 웹브라우저의 화면이 뜨면서 해당 웹페이지를 보여줍니다.

<br>
- 액션과 데이터를 사용하는 대표적인 예

|속성|설명|
|-----|-----|
|ACTION_DIAL tel:010778881234|주어진 전화번호를 이용해 전화걸기 화면을 보여줌.|
|ACTION_VIEW tel:01077881234|주어진 전화번호를 이용해 전화걸기 화면을 보여줌.|
|ACTION_EDIT content://contacts/people/2|전화번호부 데이터베이스에 있는 정보 중에서 ID 값이 2인 정보를 편집하기 위한 화면을 보여줌.|
|ACTION_VIEW content://contacts/people|전화번호부 데이터베이스의 내용을 보여줌.|

- 인텐트에 포함되어 있는 데이터는 그 포맷이 어떤 것인가를 시스템이 확인한 후 적절한 액티비티를 자동으로 찾아 띄워주기도 합니다. 만약 http처럼 특정 포맷을 사용하면 그 포맷은 등록된 MIME 타입으로 구분합니다. MIME 타입은 일반적으로 웹 서버에서 사용하는 MIME 타입과 같습니다. 예를 들어, "http://"로 시작하는 문자열의 경우에는 웹페이지 주소를 나타내는 URL이라고 인식하는 것과 같습니다. 결국, 인텐트 전달 메커니즘도 이렇게 MIME 타입을 구분한 후 설치된 앱들 중에 적절한 것을 찾아 액티비티를 띄워주는 것입니다.

- 다음은 인텐트의 생성자들입니다. 이들을 살펴보면 인텐트 객체는 액션과 데이터를 인수로 하여 만들 수도 있지만 다른 인텐트나 클래스 객체를 인수로 하여 만들기도 한다는 것을 알 수 있습니다.

```
Intent()
Intent(intent o)
Intent(String action, [,Uri uri])
Intent(Context packageContext, class<?> cls)
Intent(String action, Uri uri, Context packageContext, class<?> cls)
```

- 인텐트에 클래스 객체나 컴포넌트 이름을 지정하여 호출할 대상을 확실히 알 수 있는 경우에는 '<b>명시적 인텐트(Explicit Intent)</b>'라고 하며, 액션과 데이터를 지정하긴 했지만 호출할 대상이 달라질 수 있는 경우에는 '<b>암시적 인텐트(Implicit Intent)</b>'라고 부릅니다. 암시적 인텐트는 MIME 타입에 따라 시스템에서 적절한 7다른 앱의 액티비티를 찾은 후 띄우는 방식을 사용하게 됩니다. 즉, 설치된 앱 정보를 알고 있는 시스템이 인텐트를 이용해 요청한 정보를 처리할 수 있는 적절한 컴포넌트를 찾아본 다음 사용자에게 그 대상과 처리 결과를 보여주는 과정을 거치게 됩니다. 여기에서 <b>컴포넌트(Component)는 액티비티와 같은 독립적인 구성 요소</b>라고 생각하면 쉽습니다.

- 암시적 인텐트는 액션과 데이터로 구성되지만 그 외에도 여러 가지 속성을 가지고 있습니다. 대표적인 것으로 '범주(Category)', '타입(Type)', '컴포넌트(Component)' 그리고 '부가 데이터(Extras)'를 들 수 있으며 그 각각에 대한 설명은 다음과 같습니다.

## 범주(Category)

- 액션이 실행되는 데 필요한 추가적인 정보를 제공합니다. 예를 들어, CATEGORY_LAUNCHER는 최상위 앱으로 설치된 앱들의 목록을 보여주는 애플리케이션 런처(Launcher) 화면에 이 앱을 보여주어야 한다는 것을 의미합니다.

## 타입(Type)

- 인텐트에 들어가는 데이터의 MIME 타입을 명시적으로 지정합니다. 보통 MIME 타입은 데이터만으로도 구별이 가능하지만 명시적으로 지정할 필요가 있는 경우도 있습니다.

## 컴포넌트(Component)

- 인텐트에 사용될 컴포넌트 클래스 이름을 명시적으로 지정합니다. 보통 이 정보는 인텐트의 다른 정보를 통해 결정됩니다. 이 속성이 지정될 경우에는 지정된 컴포넌트가 실행되도록 합니다. 새로운 액티비티를 정의하고 그 액티비티의 클래스 객체를 인텐트에 전달하여 실행하는 방법도 컴포넌트를 지정하는 방식과 같습니다.

## 부가 데이터(Extra Data)

- 인텐트는 추가적인 정보를 넣을 수 있도록 번들(Bundle) 객체를 담고 있습니다. 이 객체를 통해 인텐트 안에 더 많은 정보를 넣어 다른 앱 구성 요소에 전달할 수 있습니다. 예를 들어, 이메일을 보내는 액션이 있다면 이메일에 들어갈 제목, 내용 등을 부가 데이터로 넣어 전달해야 이메일 앱이 그 데이터를 받아 처리할 수 있습니다.

- 그럼 인텐트를 이용하는 대표적인 두 가지 경우, 즉, 인텐트에 액션과 데이터를 넣어 다른 앱의 액티비티를 띄우는 경우와 컴포넌트 이름을 이용해 새로운 액티비티를 띄우는 경우를 다시 확인해 보겠습니다.

- 새로운 SampleCallIntent 프로젝트를 만들고 activity_main.xml 파일에 들어있던 텍스트뷰를 삭제한 후 최상위  레이아웃을 LinearLayout으로 변경합니다. LinearLayout의 orientation 속성 값은 vertical로 설정하고 입력상자 하나와 버튼 하나를 추가합니다. 입력상자의 text 속성 값으로 'tel:010-10001000'을 입력하고 textSize 속성 값은 24sp로 설정합니다. 버튼의 text 속성 값은 '전화걸기'로 입력합니다.


#### SampleCallintent>/app/java/org.koreait.samplecallintent/MainActivity.java

```java
package org.koreait.samplecallintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 뷰 객체 참조

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 입력상자에 입력된 전화번호 확인
                String data = editText.getText().toString();

                // 전화걸기 화면을 보여줄 인텐트 객체 생성
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
                startActivity(intent); // 액티비티 띄우기
            }
        });
    }
}
```

- 앱을 실행하면 다음과 같은 화면을 볼 수 있으며 버튼을 누르면 전화걸기 화면으로 이동합니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/3.%20%EC%9D%B8%ED%85%90%ED%8A%B8/images/image1.png)

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/3.%20%EC%9D%B8%ED%85%90%ED%8A%B8/images/image2.png)

- 버튼을 누르면 입력상자에 입력된 값을 가져와 인텐트의 파라미터로 전달하므로 입력상자에 입력된 전화번호를 바꾸면 다른 번호로 전화를 걸도록 만들 수 있습니다.

- 이제 인텐트를 사용하여 액티비티를 띄워주는 두 번째 경우를 살펴보도록 하겠습니다. activity_main.xml 파일에 새로운 버튼을 하나 더 추가하고 '메뉴 화면 띄우기' 글자가 표시되도록 합니다. 메뉴 화면은 왼쪽 프로젝트 창에서 app 항목을 선택한 후 마우스 오른쪽 버튼을 눌러 보이는 메뉴에서 [New → Activity → Empty Activity]를 선택해서 만듭니다. 새로 추가하는 액티비티의 이름은 MenuActivity로 합니다. 다시 MainActivity.java 파일로 돌아와 [메뉴 화면 띄우기] 버튼을 누르면 MenuActivity가 나타나도록 코드를 입력하겠습니다.

#### SampleCalllIntent>/app/java/org.koreait.samplecallintent/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 뷰 객체 참조

		... 생략

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // 컴포넌트 이름을 지정할 수 있는 객체 생성
                ComponentName name = new ComponentName("org.koreait.samplecallintent", "org.koreait.samplecallintent.MenuActivity");
                intent.setComponent(name); // 인텐트 객체에 컴포넌트 지정
                startActivityForResult(intent, 101); // 액티비티 띄우기
            }
        });
    }
}
```

- 컴포넌트 이름은 ComponentName 객체를 만들어 인텐트에 설정하는데 두 개의 파라미터는 각각 패키지 이름과 클래스 이름이 됩니다. 앱을 실행해 보면 기존에 새로운 액티비티의 클래스 객체를 전달하여 액티비티를 띄울 때와 동일한 결과를 볼 수 있습니다. 이렇게 인텐트를 이용해 다른 화면을 띄워줄 수 있는데 이 화면은 여러분들이 직접 만든 화면이 될 수도 있고 다른 사람이 만든 앱의 화면이 될 수도 있습니다.

> ComponentName 객체를 만들어 대상 액티비티의 이름을 지정할 때도 패키지 이름까지 함께 사용합니다.

- 다음은 인텐트(Intent) 클래스에 정의된 다양한 액션 정보를 보여줍니
	- ACTION_MAIN
	- ACTION_VIEW
	- ACTION_ATTACH_DATA
	- ACTION_ANSWER
	- ACTION_EDIT
	- ACTION_INSERT
	- ACTION_PICK
	- ACTION_DELETE
	- ACTION_CHOOSER
	- ACTION_RUN
	- ACTION_GET_CONTENT
	- ACTION_SYNC
	- ACTION_DIAL
	- ACTION_PICK_ACTIVITY
	- ACTION_CALL
	- ACTION_SEARCH
	- ACTION_SEND 
	- ACTION_ACTION_WEB_SEARCH
	- ACTION_SENDTO
	- ACTION_FACTORY_TEST

- 액션들 중에서 ACTION_MAIN과 ACTION_EDIT가 가장 많이 사용되는 액션 중의 하나입니다.

