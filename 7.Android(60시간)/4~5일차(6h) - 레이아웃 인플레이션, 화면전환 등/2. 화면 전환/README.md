# 여러화면 만들고 화면 간 전환하기 

- 대부분의 앱은 여러 화면으로 구성되어 있고 화면을 전환하며 실행됩니다. 그리고 화면은 액티비티로 구현합니다. 즉, 화면을 필요에 따라 띄우거나 닫는 과정은 액티비티를 전환하는 것과 같습니다. 따라서 좋은 앱을 만들기 위해서는 액티비티를 꼭 공부해야 합니다.

- 그러면 액티비티만 알면 안드로이드 앱을 잘 구현할 수 있을까요? 아닙니다. 액티비티는 안드로이드 앱의 네 가지 기본 구성 요소 중 하나입니다. 그렇다면 안드로이드의 앱을 구성하는 네 가지 기본 구성 요소는 무엇이 있을까요? 다음은 안드로이드 앱의 기본 구성 요소를 나타낸 그림입니다.

- 안드로이드 앱을 구성하는 네 가지 구성 요소

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/2.%20%ED%99%94%EB%A9%B4%20%EC%A0%84%ED%99%98/images/image1.png)

- 안드로이드 앱의 네 가지 구성 요소로는 '<b>액티비티(Activity)</b>', '<b>서비스(Service)</b>', '<b>브로드캐스트 수신자(Broadcast Receiver)</b>', '<b>내용 제공자(Content Provider)</b>'가 있습니다. 왜 이것들이 중요할까요? 그 이유는 앱을 만들어 단말에 설치했을 때 안드로이드 시스템이 이 요소에 대한 정보를 요구하기 때문입니다. 그러면 이 정보들은 어디에 있을까요? 새로 프로젝트를 만들면 자동으로 만들어지는 AndroidManifest.xml 파일이 바로 그 정보들을 담고 있습니다. 이 파일 안에는 위의 네 가지 구성 요소 외의 앱에 대한 다양한 정보가 들어갑니다. 앞의 실습을 진행하며 \<activity\>라는 태그를 본적이 있을 것입니다. 이 태그는 우리가 만든 액티비티에 대한 정보를 포함하고 있으며, 만약 새로운 액티비티를 만들어 앱에 추가한다면 이 매니페스트 파일에 새 액티비티 정보를 추가해야 합니다. 그래야 시스템이 새 액티비티에 대한 정보를 알 수 있습니다. 나머지 구성 요소도 마찬가지입니다. 
- 새 액티비티에 대한 \<activity\> 태그를 매니페스트에 추가하지 않으면 새 액티비티를 화면에, 보여줄 수 없습니다.

- 액티비티를 소스 코드에서 띄울 때는 startActivity 메서드를 사용하면 됩니다. 이 메서드는 단순히 액티비티를 띄워 화면에 보이도록 만듭니다. 하지만 실제로 앱을 만들다 보면 메인 액티비티에서 띄워야 할 화면이 많아져 띄웠던 화면을 닫고 원래의 메인 화면으로 돌아올 때 데이터를 새로 적용해야 하는 경우가 자주 생깁니다. 즉, 단순히 액티비티를 띄워주는 것이 아니라 어떤 액티비티를 띄운 것인지 그리고 띄웠던 액티비티로부터 다시 원래의 액티비티로 돌아오면서 응답을 받아 처리하는 코드가 필요하게 됩니다. 이런 기능은 액티비티를 소스 코드에서 띄울 때 <b>startActivity</b> 메서드가 아닌 <b>startActivityForResult</b> 메서드를 사용해야 해결할 수 있습니다.

```java
startActivityForResult(Intent intent, int requestCode)
```

- <b>startActivityForResult</b> 메서드에 전달되는 파라미터는 인텐트(intent)와 정수로 된 코드 값(requestCode)인데 코드 값은 일반적으로 각각의 액티비티를 구별하기 위해 사용됩니다. 예를 들어 액티비티에서 새 액티비티를 띄우기만 하는 것은 startActivity 메서드로 구현해도 상관없습니다. 하지만 새 액티비티에서 원래의 액티비티로 돌아오면서 새 액티비티의 응답을 받아 처리해야 하는 경우에는 어떤 액티비티로부터 돌아온 응답인지 구분해야 이 응답을 처리할 수 있습니다. 이런 경우를 처리하기 위하여 새 액티비티를 startActivity 메서드로 만들지 않고 startActivityForResult 메서드로 만듭니다.

- 그러면 실습으로 이 과정을 확인해 보겠습니다. 새로운 프로젝트를 만들 때 자동으로 만들어지는 액티비티 외에 또 다른 액티비티를 추가하고 두 개의 액티비티 간에 전환하는 기능을 만들어 보겠습니다.

- SampleIntent라는 이름의 새로운 프로젝트를 만듭니다. 그런 다음 왼쪽 프로젝트 창에서 [app]를 선택한 후 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [New Activity. Empty Activity] 메뉴를 눌러 새로운 액티비티를 추가하기 위한 대화상자를 띄웁니다. Activity name에 입력되어 있는 글자를 MenuActivity로 바꾼 후 [Finish] 버튼을 누르면 새로운 MenuActivity 액티비티가 추가됩니다.

- 왼쪽의 프로젝트 창에서 /app/manifests 폴더 안에 있는 AndroidManifest.xml 파일을 열어볼까요? 그러면 새로운 \<activity\> 태그가 추가되어 있고 그 태그의 android:name 속성 값으로 MenuActivity가 설정된 것을 알 수 있습니다. 이것은 안드로이드 스튜디오가 자동으로 추가한 것입니다. 다음 화면을 참고하여 이 \<activity\> 태그에 android:label과 android:theme 속성을 더 추가하세요

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/2.%20%ED%99%94%EB%A9%B4%20%EC%A0%84%ED%99%98/images/image2.png)

- android:label 속성은 화면의 제목을 설정할 때 사용하고 android:theme 속성은 테마를 설정할 때 사용합니다. android:theme 속성 값을 @style/Theme.AppCompat.Dialog로 설정하면 액티비티가 대화상자 형태로 나타납니다.

- 새 액티비티를 추가했으니 activity_menu.xml 파일을 열어 버튼 하나를 화면 가운데에 추가하고 text 속성을 '돌아가기'라고 수정하세요. 이 화면은 새 액티비티의 화면인 것을 잊지 마세요. 이 화면으로 새 액티비티를 띄운 다음 [돌아가기] 버튼을 클릭하면 원래의 메인 액티비티 화면으로 돌아가도록 앱을 만들 것입니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/2.%20%ED%99%94%EB%A9%B4%20%EC%A0%84%ED%99%98/images/image3.png)

- 이제 MenuActivity.java 파일을 열어 버튼을 클릭했을 때 원래 액티비티로 돌아가도록 코드를 입력하겠습니다.

#### SampleIntent>/app/java/org.koreait.sampleintent/MenuActivity.java

```java 
package org.koreait.sampleintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // 버튼 객체 참조
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 인텐트 객체 생성하고 name의 값을 부가 데이터로 넣기
                Intent intent = new Intent();
                intent.putExtra("name", "mike");

                setResult(RESULT_OK, intent);  // 응답 보내기
                finish(); // 현재 액티비티 없애기
            }
        });
    }
}
```

- 버튼을 클릭했을 때 호출되는 onClick 메서드 안에서는 Intent 클래스를 사용해 객체를 하나 만든 후 setResult 메서드를 호출하고 있습니다. 바로 이 setResult 메서드를 호출할 때 인텐트 객체가 파라미터로 전달됩니다. setResult 메서드는 새로 띄운 액티비티에서 이전 액티비티로 인텐트를 전달하고 싶을 때 사용하는 메서드로 호출할 때의 형식은 다음과 같습니다.

```java
setResult(응답 코드, 인텐트)

```

- 첫 번째 파라미터로는 응답 코드를 전달할 수 있습니다. 이 응답 코드와 인텐트는 새 액티비티를 띄운 원래 액티비티에 전달됩니다. finish 메서드는 액티비티를 화면에서 없애고 싶을 때 사용합니다. 메뉴 액티비티를 만들었으니 이제 메인 액티비티에서 이 액티비티를 띄울 차례입니다. activity_main.xml파일을 열어 텍스트뷰를 삭제하고 버튼을 추가합니다. text 속성은 '메뉴 화면 띄우기'로 설정하세요. 버튼의 id는 button으로 설정하세요. 자동으로 id가 button2와 같은 값으로 바뀔 수 있습니다. 버튼의 id가 button인지 꼭 확인하세요.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/2.%20%ED%99%94%EB%A9%B4%20%EC%A0%84%ED%99%98/images/image4.png)

- MainActivity.java 파일에는 버튼을 클릭했을 때 메뉴 화면을 띄우는 코드를 입력합니다.

#### SampleIntent>/app/src/org.koreait.sampleintent/MainActivity.java

```java
package org.koreait.sampleintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }
}
```

- 새로운 액티비티를 띄울 때 startActivityForResult 메서드를 호출한 점에 주목하세요. 이 메서드는 startActivity 메서드처럼 새 액티비티를 띄우지만 새 액티비티로부터 응답을 받을 수 있습니다.

- 액티비티에 선언된 상수인 REQUEST_CODE_MENU는 새 액티비티를 띄울 때 보낼 요청 코드입니다. 코드의 값(101)은 여러분 마음대로 지정해도 됩니다. 하지만 앱에 들어갈 액티비티가 여러 개라면 중복되지 않는 값으로 정해야 합니다. 이 값은 나중에 새 액티비티로부터 응답을 받을 때 다시 전달받을 값입니다. 이런 방식으로 어떤 액티비티로부터 온 응답인지 구분할 수 있는 것이죠.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/2.%20%ED%99%94%EB%A9%B4%20%EC%A0%84%ED%99%98/images/image5.png)

- 버튼이 눌렸을 때 호출되는 onClick 메서드 안에서 인텐트 객체를 하나 만들고 startActivityForResult 메서드를 호출했습니다. 인텐트 객체는 액티비티를 띄울 목적으로 사용되며 액티비티 간에 데이터를 전달하는 데에도 사용될 수 있습니다. 인텐트 객체를 만들 때 두 번째 파라미터로는 메뉴 액티비티의 클래스 인스턴스인 MenuActivity.class를 전달합니다. 첫 번째 파라미터로는 컨텍스트(Context) 객체가 전달되는데, 액티비티 객체는 컨텍스트가 될 수 있기 때문에 일반적으로 this 변수를 사용할 수도 있습니다. 다만 여기서는 이벤트 처리 메서드 안에서 this 변수로 MainActivity 객체를 참조할 수 없으므로 getApplicationContext 메서드를 사용해 이 앱의 Context 객체를 참조한 후 전달합니다.

- 이번에는 새로 띄운 MenuActivity로부터 받은 응답을 처리하는 메서드를 추가합니다. MainActivity 클래스 안에 커서를 둔 상태에서 마우스 오른쪽 버튼을 누르면 팝업 메뉴가 보입니다. 그중에 [Generate - Override Methods] 메뉴를 누릅니다. 또는 단축키 [Ctrl] + [O]를 누릅니다. 부모 클래스인 AppCompatActivity 또는 그 외의 상속받은 클래스들이 가지고 있는 메서드들이 보입니다. 스크롤을 내려 onActivityResult 메서드를 찾아 선택한 후 [OK] 버튼을 누릅니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/2.%20%ED%99%94%EB%A9%B4%20%EC%A0%84%ED%99%98/images/image6.png)

- 그러면 onActivityResult 메서드가 자동으로 추가됩니다. 이 메서드는 부모 클래스에 정의된 onActivityResult 메서드를 재정의한 것이 됩니다. 메서드 안에는 다음 코드를 추가합니다

```java

... 생략 

public class MainActivity extends AppCompatActivity {

	... 생략 

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MENU) {
            Toast.makeText(getApplicationContext(),
                    "onActivityResult 메서드 호출됨. 요청 코드 : " + requestCode +
                    ", 결과 코드 : " + resultCode, Toast.LENGTH_LONG).show();

            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                Toast.makeText(getApplicationContext(), "응답으로 전달된 name : " + name,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
```

- onActivityResult 메서드는 새로 띄웠던 메뉴 액티비티가 응답을 보내오면 그 응답을 처리하는 역할을 합니다.

```java
protected void onActivityResult(int requestCode, int resultCode, Intent intent)
```

- 이 메서드의 첫 번째 파라미터는 액티비티를 띄울 때 전달했던 요청 코드와 같습니다. 이 값으로 어떤 액티비티로부터 응답을 받은 것인지 구분할 수 있습니다. 두 번째 파라미터는 새 액티비티로부터 전달된 응답 코드입니다. 응답 코드는 새 액티비티에서 처리한 결과가 정상인지 아닌지를 구분하는 데 사용됩니다. 보통 Activity.RESULT_OK 상수를 전달하는 방법으로 정상 처리임을 알립니다. 물론 여러분이 임의로 만든 코드를 전달할 수도 있습니다. 예를 들어, 성공인 경우 200, 실패인 경우 400 코드를 전달하도록 만들 수도 있습니다. 세 번째 파라미터는 새 액티비티로부터 전달 받은 인텐트입니다. 이 인텐트 안에 새 액티비티의 데이터를 전달할 수 있습니다. 인텐트 객체는 주로 새 액티비티로부터 원래의 액티비티로 데이터를 전달할 때 사용합니다. 그러면 인텐트 객체에 데이터를 넣는 방법은 무엇일까요? 가장 간단한 방법은 <b>putExtra</b> 메서드를 사용하는 것입니다. 이 메서드를 이용할 때는 키(Key)와 데이터 값(Value)을 쌍으로 넣어야 합니다. 물론 이 값을 다시 확인할 경우에는 키(Key)를 사용해 데이터 값을 가져올 수 있습니다.

- 앱을 실행해 보세요. 여러분이 작성한 실습 코드는 원래 액티비티에서 요청한 코드와 새 액티비티에서 응답한 코드를 비교하여 응답 결과를 토스트로 표시하도록 구현했습니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/2.%20%ED%99%94%EB%A9%B4%20%EC%A0%84%ED%99%98/images/image7.png)

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/2.%20%ED%99%94%EB%A9%B4%20%EC%A0%84%ED%99%98/images/image8.png)

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/2.%20%ED%99%94%EB%A9%B4%20%EC%A0%84%ED%99%98/images/image9.png)

- 메인 액티비티 안에 들어 있는 버튼을 누르면 새 메뉴 액티비티가 뜨고 그 화면의 버튼을 누르면 다시 메인 액티비티로 돌아갑니다. 그리고 그 각각의 상태는 토스트 메시지로 보여줍니다. 토스트 메시지를 보면 어떤 값이 액티비티 간에 전달되었는지도 알 수 있습니다.

- 지금까지 새로운 액티비티를 만들어 추가하고 서로 간에 상태 코드나 데이터를 주고받는 방법에 대해 살펴보았습니다. 이 과정을 정리하면 다음과 같습니다.

- (1) 새로운 액티비티 만들기
	- 새로운 액티비티를 추가하면 XML 레이아웃 파일 하나와 자바 소스 파일 하나가 만들어지고 매니페스트 파일에 액티비티 태그가 추가됩니다.
	
- (2) 새로운 액티비티의 XML 레이아웃 정의하기
	- 새로 만들어진 XML 레이아웃을 수정하여 새로운 액티비티의 화면이 어떻게 배치될지를 작성합니다.
	
- (3) 메인 액티비티에서 새로운 액티비티 띄우기
	- 메인 액티비티의 버튼을 클릭하면 startActivityForResult 메소드로 새로운 액티비티를 띄웁니다.

- (4) 새로운 액티비티에서 응답 보내기
	- 새로운 액티비티가 보이고 그 안에 들어 있는 버튼을 클릭하면 setResult 메서드로 응답을 보냅니다.
	
- (5) 응답 처리하기
	- 메인 액티비티에서 onActivityResult 메서드를 재정의하여 새로 띄웠던 액티비티에서 보내오는 응답을 처리합니다.

<br>
- 액티비티 추가와 요청 그리고 응답 과정 

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/2.%20%ED%99%94%EB%A9%B4%20%EC%A0%84%ED%99%98/images/image10.png)

- 이 과정은 앱에 새로운 액티비티를 추가하기 위해 필요한 것이므로 실제 앱을 만들 때도 잘 이해하고 있어야 합니다. 특히, 실제 앱을 구성할 때에는 여러 개의 화면이 사용되고 복잡한 앱의 경우에는 수십개의 화면이 사용되기도 하므로 액티비티 간의 요청과 응답 구조에 대해 명확하게 알고 있어야 코드를 쉽게 이해할 수 있습니다.

