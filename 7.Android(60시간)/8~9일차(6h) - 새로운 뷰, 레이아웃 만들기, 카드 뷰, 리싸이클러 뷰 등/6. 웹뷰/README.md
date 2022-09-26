# 앱 화면에 웹브라우저 넣기

- 앱에서 웹페이지를 띄우는 방법은 아주 간단합니다. 인텐트 객체를 만들어서 웹사이트 주소를 넣은 후 시스템으로 요청하기만 하면 되기 때문이죠. 그런데 앱 화면에 웹브라우저를 넣어서 볼 수도 있을까요? 인텐트에 웹사이트 주소만 넣어 띄우는 방식은 단말기에 있는 웹브라우저 앱 화면이 뜨는 것이지만 여러분이 만든 앱 화면 안에 웹브라우저를 넣으면 여러분의 앱 안에서 웹사이트가 보이게 됩니다. 사용자는 단말에서 보고 있는 컨텐츠가 웹페이지인지 아니면 앱에서 구성한 것인지에 큰 관심이 없지만 여러분의 앱 안에 웹페이지가 들어 있을 때 훨씬 더 일관성 있는 화면으로 인식합니다. 예를 들어, 서버에 있는 웹페이지로 앱의 설명서를 보여주는 경우에 가이드의 1장, 2장, 3장과 같이 목차를 앱으로 구성하고 그 각각의 항목을 선택하면 웹페이지가 보이면서 앱 설명서가 표시되는 경우를 들 수 있습니다. 이렇게 만들면 사용자는 앱으로 느끼게 되고 별도의 화면으로 갑자기 나타나는 웹브라우저 화면보다 더 자연스러운 화면을 보여줄 수 있습니다.

- 웹브라우저를 앱 안에 넣을 때는 웹뷰(WebView)를 사용하면 되는데 XML 레이아웃에서는 \<WebView\> 태그로 정의합니다. 웹뷰를 정의하여 사용할 때는 인터넷에 접속하게 됩니다. 따라서 항상 매니페스트에 인터넷 접속 권한이 등록되어 있어야 합니다. 다음은 매니페스트에 등록해야 할 인터넷 권한입니다.

```xml
<uses-permissions android:name="android.permission.INTERNET" />
```
- XML 레이아웃에 뷰를 추가하면 소스 코드에서 웹뷰 객체를 찾아 참조할 수 있습니다. 앱 화면 안에 웹뷰를 넣어보는 실습을 위해 새로운 SampleWeb 프로젝트를 만들고 패키지 이름은 org.koreait.web으로 수정합니다. 그리고 activity_main.xml 파일을 열고 최상위 레이아웃을 리니어 레이아웃으로 변경합니다. 화면에 들어 있던 텍스트뷰는 삭제하고 orientation 속성 값은 vertical로 설정합니다. 가로 방향으로 추가할 수 있는 리니어 레이아웃을 하나 추가하고 layout_height 속성 값을 wrap_content로 설정한 후 URL을 입력할 수 있는 입력상자와 버튼 하나를 추가합니다. 그리고 리니어 레이아웃 아래에는 웹뷰를 추가합니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/6.%20%EC%9B%B9%EB%B7%B0/images/image1.png)

- 웹뷰의 id는 webView로 설정합니다. 그러면 소스 코드에서는 이 뷰를 findViewById 메서드로 찾아 참조할 수 있습니다. MainActivity.java 파일을 열고 다음 코드를 입력합니다. 이 코드에서는 웹뷰를 사용해 웹페이지를 로딩합니다.

#### SampleWeb>/app/java/org.koreait.web/MainActivity.java 

```java
package org.koreait.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.webView);

        // 웹뷰의 설정 수정하기
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new ViewClient());

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭시 사이트 로딩하기
                webView.loadUrl(editText.getText().toString());
            }
        });
    }

    private class ViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            view.loadUrl(url);

            return true;
        }
    }
}
```

- findViewById 메서드를 사용해서 참조한 WebView 객체의 getSettings 메서드를 사용해 WebSettings 객체를 참조합니다. 이 객체에 설정한 설정 정보 중에서 setJavaScriptEnabled 코드가 있으므로그 값이 true로 설정되면 자바스크립트가 동작할 수 있는 환경이 됩니다. 대부분의 웹사이트가 자바스크립트를 사용하므로 이 값은 항상 true로 설정하는 것이 좋습니다.

- 웹페이지를 로딩하여 화면에 보여주기 위해서는 loadUrl 메서드를 사용합니다. 이 메서드를 사용하면 원격지의 웹페이지를 열거나 로컬에 저장된 HTML 파일을 열 수 있습니다. 이렇게 나타난 웹페이지는 확대/축소 기능이 설정되어 있으면 화면 상에서 확대/축소가 가능하며 웹뷰 객체의 goForward나 goBack 메서드를 이용하면 앞 페이지 또는 뒤 페이지로도 이동할 수 있습니다. 화면에 추가된 WebView 객체에 웹페이지를 보여주기 위해서는 WebViewClient를 상속한 객체를 만들어 WebView에설정해야 합니다.

- 앱을 실행하려면 권한을 설정해야 하므로 /app/manifests 폴더 안에 있는 AndroidManifest.xml 파일을 열고 다음 코드를 추가합니다.

#### SampleWeb>/app/manifests/AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.web">

    <uses-permission android:name="android.permission.INTERNET" />
	
	<application
         android:usesCleartextTraffic="true"
	... 생략 
	
</manifest>
```

- INTERNET 권한을 추가했으며 \<application\> 태그 안에는 usesCleartextTraffic이라는 속성을 추가했습니다. 이 권한과 속성이 추가되어야 웹뷰 안에 웹사이트가 표시될 수 있습니다. 앱을 실행한 후 입력상자에 웹사이트 주소(http://m.naver.com)를 입력하고 [열기] 버튼을 누릅니다. 버튼 아래쪽의 웹 뷰에 지정한 웹사이트가 열리는 것을 확인할 수 있습니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/6.%20%EC%9B%B9%EB%B7%B0/images/image2.png)

- 웹뷰를 앱의 구성 요소로 사용하면 컨텐츠가 자주 변하는 경우에도 서버에서 이 컨텐츠를 관리할 수 있다는 장점이 있습니다. 또한 앱 가이드와 같이 매뉴얼을 작성해 넣거나 텍스트로 된 메시지에 중간 중간 색상이나 링크를 넣고 싶은 경우에도 유용하게 사용할 수 있습니다. 필요한 경우에는 웹페이지와 앱사이에 데이터를 주고받을 수도 있으니 일단 웹뷰를 활용해서 앱 화면 안에서 웹사이트를 보여주는 방법을 잘 알아두면 좋습니다.