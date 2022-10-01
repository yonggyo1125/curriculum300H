# Volley 사용하기

- 웹 서버에 요청하고 응답을 받을 때는 앞서 살펴본 HttpURLConnection 객체를 사용할 수 있지만 요청과 응답에 필요한 코드의 양이 많습니다. 그리고 스레드를 사용하면서 넣어야 하는 코드의 양도 많습니다. 핸들러에 대한 이해가 없다면 앱이 비정상 종료될 수도 있겠죠, 이런 문제를 해결하기 위해 여러가지 라이브러리들이 만들어졌습니다. 그중에서도 가장 많이 사용되는 것 중의 하나가 Volley입니다. Volley 라이브러리는 웹 요청과 응답을 단순화하기 위해 만들어진 라이브러리입니다.

- ﻿Volley를 사용하려면 먼저 요청(Request) 객체를 만들고 이 요청 객체를 요청 큐(RequestQueue)라는 곳에 넣어주기만 하면 됩니다. 그러면 요청 큐가 알아서 웹 서버에 요청하고 응답까지 받아줍니다. 여러분은 응답을 받을 수 있도록 지정된 메서드를 만들어두기만 하면 응답이 왔을 때 그 메서드가 자동으로 호출됩니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/3.%20Volley%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/images/image1.png)

- Volley 라이브러리의 가장 큰 장점은 스레드를 신경 쓰지 않아도 된다는 점입니다. 요청 큐가 내부에서 스레드를 만들어 웹 서버에 요청하고 응답받는 과정을 진행하는데, 응답을 처리할 수 있는 메서드를 호출할 때는 메인 스레드에서 처리할 수 있도록 만들기 때문입니다. 따라서 스레드를 사용할 필요도 없고 화면에 결과를 표시할 때 핸들러를 사용할 필요도 없습니다.

- Volley를 사용해서 웹 요청과 응답을 처리하는 과정을 살펴보겠습니다. 먼저 SampleRequest 프로젝트를 만들고 패키지 이름을 org.koreait.request로 수정합니다. 새로운 프로젝트 창이 열리면 Volley 라이브러리를 추가해야 합니다. Volley는 외부 라이브러리이므로 build.gradle 파일에 정보를 추가해야 사용할 수 있습니다. /app/Gradle Scripts 폴더 안에 있는 build.gradle(Module: SampleRequest.app) 파일을 열고 dependencies 중괄호 안에 라이브러리 정보를 추가합니다. 라이브러리 정보를 추가하고 [Sync Now] 링크를 클릭하면 자동으로 라이브러리가 추가됩니다.

```gradle

... 생략

dependencies {

	... 생략
	
    implementation 'com.android.volley:volley:1.2.1'
}
```

- 인터넷을 사용하므로 매니페스트 파일에 INTERNET 권한을 추가하는 것도 잊어서는 안 됩니다. 또 application 태그에는 usesCleartextTraffic 속성을 추가해야 합니다. /app/manifests 안에 있는 AndroidManifest.xml 파일을 열고 다음 내용을 추가합니다.

#### SampleRequest>/app/manifests/AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.request">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:usesCleartextTraffic="true"
		
		... 생략 
		>
		... 생략
		
	</application>
</manifest>
```

- 화면은 이전 단락에서 만든 것과 똑같이 상단에 입력상자와 버튼을 두고 그 아래에 스크롤뷰와 그 안에 포함된 텍스트뷰를 배치합니다. 레이아웃 화면을 구성했으면 버튼을 눌렀을 때 입력상자에 입력한 사이트 주소로 웹 요청을 할 것이므로 MainActivity.java 파일을 열고 다음 코드를 입력합니다. 
> [File - Open Recent - SampleHttp]를 새 창에서 열고 activity_main.xml 파일을 복사해서 /res/layout 폴더에 붙여 넣어 덮어써도 됩니다.

#### SampleRequest>/app/java/org.koreait.request/MainActivity.java

```java
package org.koreait.request;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });

        // RequestQueue 객체 생성하기
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void makeRequest() {
        String url = editText.getText().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청 보냄.");
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}
```

- 사용자가 버튼을 클릭했을 때 요청 객체를 만들고 요청 큐에 넣어줍니다. 요청 큐는 한 번만 만들어 계속 사용할 수 있기 때문에 static 키워드로 클래스 변수를 선언한 후 할당했습니다. 요청 큐를 이 액티비티에서만 사용하는 것이 아니라 앱 전체에서 사용하는 것이 일반적입니다. 따라서 실제 앱을 만들 때는 Application 클래스 안에 넣어두거나 별도의 클래스를 하나 만들어서 넣어둘 수 있습니다. 요청 큐를 만들 때는 Volley.newRequestQueue 메서드를 사용할 수 있습니다. 이 코드는 onCreate 메서드 안에 넣어줍니다.

- ﻿요청 객체는 StringRequest 클래스로 만들 수 있습니다. StringRequest는 문자열을 주고받기 위해 사용하는 요청 객체이며 Volley 라이브러리 안에는 이외에도 여러 가지 유형의 요청 객체가 들어 있습니다. 하지만 일반적으로는 StringRequest 객체만으로도 충분합니다.

- 요청 객체를 new 연산자로 만들 때는 네 개의 파라미터를 전달할 수 있습니다. 첫 번째 파라미터로는 GET 또는 POST 메서드를 전달합니다. 요청 방식을 지정하는 것이죠. 두 번째 파라미터로는 웹사이트 주소를 전달합니다. 세 번째 파라미터로는 응답받을 리스너 객체를 전달합니다. 이 리스너의 onResponse 메서드는 응답을 받았을 때 자동으로 호출됩니다. 네 번째 파라미터로는 에러가 발생했을 때 호출될 리스너 객체를 전달합니다. 여기에서는 GET 방식을 사용했지만 POST 방식을 사용하면서 요청 파라미터를 전달하고자 한다면 getParams 메서드에서 반환하는 HashMap 객체에 파라미터 값들을 넣어주면 됩니다. 여기서는 파라미터가 없기 때문에 비워두었습니다.

- 요청 객체를 만들었다면 이 객체는 요청 큐에 넣어줍니다. 요청 큐의 add 메서드로 요청 객체를 넣으면 요청 큐가 자동으로 요청과 응답 과정을 진행합니다. 요청 객체는 cache 메커니즘을 지원하는데 만약 이전 응답 결과를 사용하지 않겠다면 setShouldCache 메서드를 사용해서 cache를 사용하지 않도록 설정하면 됩니다.

- 앱을 실행하고 입력상자에 사이트 주소를 입력한 후 버튼을 누릅니다. 그러면 아래쪽 텍스트뷰에 응답 결과가 표시됩니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/3.%20Volley%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/images/image2.png)

- 스레드나 핸들러를 사용하지 않았는데도 웹 사이트 요청과 응답이 문제없이 진행되는 것을 확인할 수 있습니다.