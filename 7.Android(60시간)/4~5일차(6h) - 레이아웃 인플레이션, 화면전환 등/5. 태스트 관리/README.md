# 태스크 관리 이해하기

- 여러분이 만든 앱을 실행하면 그 앱은 하나의 프로세스 위에서 동작합니다. 다시 말해, 프로세스가 하나 실행되고 그 위에는 VM(Virtual Machine, 가상 머신)이 만들어지며, 또다시 가상 머신(VM) 위에서 앱이 실행됩니다. 그런데 여러분이 만든 앱에서 시스템으로 인텐트를 보내는 방법으로 안드로이드의 기본 앱 중 하나인 전화 앱을 띄울 수 있습니다. 이렇게 하면 전화 앱은 별도의 프로세스로 동작하게 됩니다.

- 그런데 전화 앱의 화면에서 시스템 [BACK] 키를 누르면 자연스럽게 여러분의 앱 화면으로 돌아올 수있어야 합니다. 그런데 프로세스는 독립적인 상자와 같아서 프로세스 간의 정보 공유는 어렵습니다. 그래서 앱에는 태스크(Task)라는 것이 만들어집니다. 태스크는 앱이 어떻게 동작할지 결정하는 데 사용됩니다. 즉, 태스크를 이용하면 프로세스처럼 독립적인 실행 단위와 상관없이 어떤 화면들이 같이 동작해야 하는지 흐름을 관리할 수 있습니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/5.%20%ED%83%9C%EC%8A%A4%ED%8A%B8%20%EA%B4%80%EB%A6%AC/images/image1.png)

## 프로세스와 태스크

- 앞에서 프로세스는 독립적인 하나의 상자와 같아서 다른 프로세스와 어떤 정보를 공유할 수 없다고 설명했습니다. 따라서 하나의 프로세스에서 다른 프로세스의 화면을 띄우려면 시스템의 도움이 필요합니다. 시스템에서 이런 액티비티의 각종 정보를 저장해두기 위해 태스크라는 것을 만들게 됩니다. 만약여러분의 앱에서 전화 앱의 화면을 띄우지 않고 전화 앱을 따로 실행시키면 전화 앱의 태스크는 여러분앱의 태스크와 별도로 만들어지게 됩니다.

- 시스템은 알아서 태스크를 관리하지만 여러분이 직접 제어해야 하는 경우도 생깁니다. 이를 위해 매니페스트 파일(AndroidManifest.xml)에 액티비티를 등록할 때 태스크도 함께 설정할 수 있습니다.

- 태스크를 설정해보기 위해 새로운 SampleTask 프로젝트를 만듭니다. activity_main.xml 파일 안에 있는 텍스트뷰의 글자는 '첫 번째 화면'으로 바꾸고 글자 크기는 30sp로 설정합니다. 텍스트뷰의 아래쪽에 버튼을 하나 추가하고 '나 자신 띄우기'라는 글자가 보이게 수정합니다. MainActivity.java 파일에는 버튼을 눌렀을 때 인텐트를 사용해 MainActivity 화면을 띄울 수 있도록 다음 코드를 입력합니다.


#### SampleTask>/app/src/org.koreait.sampletask/MainActivity.java

```java
package org.koreait.sampletask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
```

- 버튼을 클릭했을 때 onClick 메서드가 호출된다는 점과 화면을 띄울 때는 Intent 객체와 startActivity 메서드를 사용한다는 점을 잘 알고 있으므로 코드는 크게 어렵지 않습니다. 앱을 실행하고 버튼을 누를 때마다 첫 화면이 반복해서 뜨게 됩니다. 그다음 시스템 [BACK] 버튼을 누르면 동일한 화면이 여러 개 중첩되어 떠 있다는 것을 확인할 수 있습니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/5.%20%ED%83%9C%EC%8A%A4%ED%8A%B8%20%EA%B4%80%EB%A6%AC/images/image2.png)

- 이것은 AndroidManifest.xml 파일에서 MainActivity를 등록하는 \<activity\> 태그에 launchMode 속성을 추가하고 그 값을 standard로 한 것과 같습니다. 다시 말해, 태스크는 앞에서 설명한 것처럼 새로 뜨는 화면을 차례대로 스택에 넣어서 관리합니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/5.%20%ED%83%9C%EC%8A%A4%ED%8A%B8%20%EA%B4%80%EB%A6%AC/images/image3.png)

- 이번에는 /app/manifests 폴더 안에 있는 AndroidManifest.xml 파일을 열고 \<activity\> 태그 안에 <b>launchMode</b> 속성을 추가합니다. 그 값을 <b>singleTop</b>으로 설정하면 태스크의 가장 위쪽에 있는 액티비티는 더 이상 새로 만들지 않게 됩니다. 앞에서 인텐트의 플래그를 설정할 때 <b>FLAG_ACTIVITY_SINGLE_TOP</b>으로 설정했던 것과 같은 효과입니다.

#### SampleTask>/app/manifests/AndroidManifest.xml

```xml

... 생략 

<activity
	android:name=".MainActivity"
	android:launchMode="singleTop"
	android:exported="true">
	<intent-filter>
		<action android:name="android.intent.action.MAIN" />
		<category android:name="android.intent.category.LAUNCHER" />
	</intent-filter>
</activity>

... 생략 

```

- 앱을 실행하고 버튼을 여러 번 누른 후 시스템 [BACK] 키를 누릅니다. 그러면 시스템 [BACK] 키를 한번만 눌러도 앱의 화면이 사라지는 것을 볼 수 있습니다. 결국 MainActivity 화면은 한 번만 생성됩니다. 앞에서도 설명했던 것처럼 이 경우 MainActivity 쪽으로 전달되는 인텐트는 <b>onNewIntent</b> 메서드로 전달받아야 합니다.

- AndroidManifest.xml 파일에서 \<activity\> 태그에 launchMode 속성 값을 <b>singleTask</b>로 설정하면 이 액티비티가 실행되는 시점에 새로운 태스크를 만들게 되고 <b>singleInstance</b>로 설정하면 이 액티비티가실행되는 시점에 새로운 태스크를 만들면서 그 이후에 실행되는 액티비티들은 이 태스크를 공유하지않도록 합니다. 태스크에 대해 어느 정도 이해가 되었다면 경우에 따라 액티비티를 띄우면서 태스크를새로 만들도록 설정해야 한다는 점을 기억하면 됩니다. 이에 대해서는 나중에 서비스 관련 내용을 다룰 때 다시 확인할 수 있습니다.
