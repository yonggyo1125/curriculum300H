# 네트워킹이란?

- 네트워킹은 인터넷에 연결되어 있는 원격지의 서버 또는 원격지의 단말과 통신해서 데이터를 주고받는 동작들을 포함합니다. 이런 네트워킹은 여러분이 가지고 있는 단말의 데이터만 사용하는 것이 아니라 멀리 떨어져 있는 서버나 다른 사람의 단말의 데이터를 조회할 수 있습니다. 그리고 서버에 데이터를 저장할 때는 먼저 인터넷을 통해 데이터 통신이 가능한지를 알아본 후 데이터를 주고받는 과정도 진행합니다. 데이터를 주고받는 과정은 상당히 복잡합니다. 그래도 네트워킹을 사용하는 이유는 인터넷에 연결되어 있는 여러 단말을 동시에 사용할 수 있어서 다양한 데이터 자원을 효율적으로 사용할 수있기 때문입니다. 그러면 본격적으로 네트워크 연결 방식에 대해 알아보겠습니다.

## 네트워크 연결 방식 이해하기

- 원격지의 서버를 연결하는 가장 단순한 방식은 클라이언트와 서버가 일대일로 연결하는 '2-tier C/S(Client/Server)' 방식입니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image1.png)

- '2-tier C/S' 연결 방식은 가장 많이 사용하는 네트워킹 방식이며 대부분 클라이언트가 서버에 연결되어 데이터를 요청하고 응답받는 단순한 개념으로 이해할 수 있습니다. 웹페이지를 볼 때 사용하는 HTTP 프로토콜, 파일전송을 위한 FTP 프로토콜 그리고 메일을 주고받는 POP3 프로토콜 등의 연결방식은 모두 위와 같은 방법으로 서버로 간편하게 접속하여 처리하는 것입니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image2.png)

- 3-tier 연결 방식을 사용하면 서버를 좀 더 유연하게 구성할 수 있습니다. 또 응용 서버와 데이터 서버로 서버를 구성하면 데이터베이스를 분리할 수 있어 중간에 비즈니스 로직(Business Logic)을 처리하는응용 서버가 좀 더 다양한 역할을 할 수 있다는 장점이 생깁니다. 이것보다 좀 더 많은 단계들을 추가한 N-tier 연결 방식이 있지만 일반적으로는 3-tier까지만 이해해도 앱을 만드는 데 큰 무리가 없습니다.

- 단말 간의 통신이 일반화되며 클라이언트와 서버의 관계는 피어-투-피어(Peer-to-Peer) 통신으로 불리는 P2P 모델로도 변형되어 사용되기도 합니다. P2P 모델은 서버를 두지 않고 단말끼리 서버와 클라이언트 역할을 하죠. 이런 특징을 가지고 있는 P2P 모델은 정보 검색이나 파일 송수신으로 정보를 공유하는 데 많이 사용됩니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image3.png)

- 우리가 흔히 사용하는 메신저 서비스나 인터넷 전화에 사용되는 SIP 프로토콜 기반의 서비스들은 서버가 있긴 하지만 P2P 모델의 특성을 가지고 있습니다. 그러므로 단말끼리의 통신 방식에 대한 기본적인이해도 필요합니다. 간단하게 네트워킹이 무엇인지 알아보았으니 가장 기초적인 네트워킹 방법인 소켓을 먼저 알아보겠습니다.

* * * 
# 소켓 사용하기

- 네트워킹을 이해하려면 먼저 TCP/IP 수준의 통신 방식을 제공하는 소켓이 무엇인지 알아야 합니다. IP주소로 목적지 호스트를 찾아내고 포트로 통신 접속점을 찾아내는 소켓 연결은 TCP와 UDP 방식으로나눌 수 있습니다. 하지만 일반적인 프로그래밍에서는 대부분 TCP 연결을 사용합니다. 따라서 앞으로 실습하는 내용은 모두 TCP 연결을 사용할 것입니다.


> 인터넷 전화에 많이 사용되는 SIP 프로토콜이나 멀티미디어 데이터 스트림을 처리하는 RTP 프로토콜은 기본적으로 UDP를 많이 사용합니다. 여기에서 SIP는 세션 개시 프로토콜(Session Initiation Protocol)이라고 하는데 IETF에서 정의한 시그널링 프로토콜입니다. 음성과 화상통화 같은 멀티미디어 세션을 제어하기 위해 널리 사용되고 있습니다.

## HTTP 프로토콜과 소켓

- HTTP 프로토콜은 소켓으로 웹 서버에 연결한 후에 요청을 전송하고 응답을 받은 다음 연결을 끊습니다. 이런 특성을 '비연결성(Stateless)'이라고 하는데 이런 특성 때문에 실시간으로 데이터를 처리하는 ﻿앱은 응답 속도를 높이기 위해 연결성이 있는 소켓 연결을 선호했습니다. 하지만 지금은 인터넷의 속도가 빨라져 HTTP 프로토콜을 사용하는 웹이 일반적이 되었고 결국 속도가 그렇게 느리지 않으면서도 국제 표준을 따를 수 있다는 장점을 가진 웹 서버로 많은 서버가 만들어지게 되었습니다.

- 안드로이드는 표준 자바에서 사용하던 java.net 패키지의 클래스들을 그대로 사용할 수 있습니다. 이 때문에 네트워킹의 기본이 되는 소켓 연결은 아주 쉽게 구현할 수 있습니다. 즉, 화면 구성을 위한 액티비티를 구성하고 나면 소켓 연결에 필요한 코드는 기존에 사용하던 자바 코드를 그대로 사용할 수 있습니다.

- 네트워킹을 실습하기 위한 프로젝트를 만들어보기 전에 먼저 알아두어야 할 것이 있습니다. 안드로이드는 소켓 연결 등을 시도하거나 응답을 받아 처리할 때 스레드를 사용해야 한다는 것입니다. 이전에는 권장사항이었으나 현재 플랫폼 버전에서는 강제사항이 되었으므로 스레드를 사용하지 않으면 네트워킹 기능 자체가 동작하지 않습니다.

> 원격지에 데이터를 요청하고 응답을 기다리는 네트워킹 기능은 네트워크의 상태에 따라 응답 시간이 길어질 수 있을뿐더러 최근 플랫폼에서는 스레드 사용을 강제하고 있기 때문에 이런 경우에는 UI 업데이트를 위해 핸들러를 사용합니다.

- 네트워킹 실습을 위해 먼저 클라이언트와 서버 소켓을 만들어 보겠습니다. SampleSocket 프로젝트를 만들고 패키지 이름은 org.koreait.socket으로 수정합니다. activity_main.xml 파일을 열어서 최상위 레이아웃은 LinearLayout으로 변경하고 orientation 속성 값은 vertical로 설정합니다. 메인 화면은 위쪽과 아래쪽을 분할하여 위쪽은 클라이언트, 아래쪽은 서버 쪽 영역으로 사용하려고 합니다. 리니어레이아웃을 두 개 추가하고 위쪽과 아래쪽 공간을 나눠 가질 수 있도록 layout_height 속성 값은 0dp, layout_weight 속성 값은 1dp로 각각 설정합니다. 그리고 위쪽 레이아웃에는 입력상자와 버튼을 하나씩 추가하고 스크롤뷰에 포함된 텍스트뷰를 배치합니다. 아래쪽 레이아웃에는 버튼 하나와 스크롤뷰에 포함된 텍스트뷰를 배치합니다. 위쪽 레이아웃의 버튼은 '전송'이라는 글자가 표시되도록 하고 아래쪽 레이아웃의 버튼에는 '서버 시작'이라는 글자를 넣습니다. 위쪽과 아래쪽 영역을 구분할 수 있도록 위쪽에 있는 리니어 레이아웃에는 배경색을 밝은 파랑으로 설정하고 아래쪽에 있는 리니어 레이아웃은 오렌지색으로 배경을 설정합니다. 각각의 레이아웃에 넣은 텍스트뷰의 textSize 속성 값은 모두20sp로 설정합니다.

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

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:background="@android:color/holo_blue_bright"
        android:orientation="vertical">

        <EditText
            android:id="@+id/editText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:ems="10"
            android:inputType="textPersonName" />

        <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="전송" />

        <ScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">

                <TextView
                    android:id="@+id/textView"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:textSize="20sp" />
            </LinearLayout>
        </ScrollView>
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:background="@android:color/holo_orange_light"
        android:orientation="vertical">

        <Button
            android:id="@+id/button2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="서버 시작" />

        <ScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">

                <TextView
                    android:id="@+id/textView2"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:textSize="20sp" />

            </LinearLayout>

        </ScrollView>
    </LinearLayout>

</LinearLayout>
```

- 완성된 레이아웃은 다음과 같은 모양이 됩니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image4.png)

- 화면 레이아웃을 만들었다면 MainActivity.java 파일을 열고 다음 코드를 입력합니다. 텍스트뷰와 입력상자는 클래스 안에 변수를 선언하고 findViewById로 찾아 변수에 할당합니다. 첫 번째 버튼을 눌렀을 때는 새로 만들 send 메서드를 호출하도록 하고 두 번째 버튼을 눌렀을 때는 startServer 메서드를 호출하도록 합니다. 그런데 이 두 개의 메서드는 모두 네트워킹 기능을 사용할 것이므로 스레드로 만들어야 합니다. 따라서 버튼을 눌렀을 때 스레드 안에서 동작하게 만드는 게 중요합니다.

#### SampleSocket>/app/java/org.koreait.socket/MainActivity.java

```java
package org.koreait.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    TextView textView;
    TextView textView2;

    Handler handler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data = editText.getText().toString();
                // 스레드 안에서 send 메서드 호출하기
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 스레드 안에서 startServer 메서드 호출하기
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });
    }
}
```

- 두 개의 텍스트뷰는 결과를 화면에 출력하기 위한 것입니다. printClientLog 메서드는 화면 상단에 있는 텍스트뷰에 글자를 출력하도록 하고 printServerLog 메서드는 화면 하단에 있는 텍스트뷰에 글자를 출력하도록 합니다. 새로 만들어진 스레드에서 이 메서드들을 호출할 것이므로 핸들러 객체를 이용합니다.

#### SampleSocket>/app/java/org.koreait.socket/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {

	... 생략 

    public void printClientLog(final String data) {
        Log.d("MainActivity", data);

        // 클라이언트 쪽 로그를 화면에 있는 텍스트뷰에 출력하기 위해 핸들러 사용하기
       handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n");
            }
        });
    }

    public void printServerLog(final String data) {
        Log.d("MainActivity", data);

        // 서버 쪽 로그를 화면에 있는 텍스트뷰에 출려하기 위해 핸들러 사용하기
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView2.append(data + "\n");
            }
        });
    }
}
```

- printClientLog printServerLog 메서드 안에서는 핸들러 객체를 사용하고 있으며 Runnable 객체의 run 메서드 안에서 텍스트뷰를 접근하고 있습니다. 텍스트뷰의 append 메서드로 전달될 파라미터는 printClientLog와 printServerLog 메서드로 전달되는 파라미터가 그대로 전달되어야 하므로 final로 정의했습니다.

- 이제 클라이언트에서 데이터를 전송하는 send 메서드를 정의합니다. 여기에서는 서버와 클라이언트가 5001번 포트를 사용하도록 합니다.

#### SampleSocket>/app/java/org.koreait.socket/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
	
	... 생략

    public void send(String data) {
        try {
            int portNumber = 5001;
            // 소켓 객체 만들기
            Socket sock = new Socket("localhost", portNumber);
            printClientLog("소켓 연결함.");

            //  소켓 객체로 데이터 보내기
            ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
            outstream.writeObject(data);
            outstream.flush();
            printClientLog("데이터 전송함.");

            ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
            printClientLog("서버로부터 받음: " + instream.readObject());
            sock.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
```

- 코드는 표준 자바의 소켓 클라이언트 코드와 거의 같습니다. 로그를 화면에 출력하기 위해 사용한 printClientLog 메서드만 다릅니다. 접속할 IP 주소는 "localhost", 포트는 5001번을 사용하고 있습니다. new 연산자로 만드는 소켓은 이 IP 주소와 포트 번호를 파라미터로 전달받으며, 새로 만들어진 소켓을 통해 데이터를 보내거나 받고 싶을 때는 getOutputStream과 getInputStream 메서드로 입출력 스트림 객체를 참조합니다. 여기서는 문자열을 객체 그대로 보내기 위해 ObjectOutputStream과 ObjectInputStream 클래스를 사용하였습니다.

- 이 클라이언트가 접속할 서버는 startServer 메서드 안에 다음과 같이 구성합니다.

#### SampleSocket>/app/java/org.koreait.socket/MainActivity.Java

```java

... 생략 

public class MainActivity extends AppCompatActivity {
	
	... 생략

    public void startServer() {
        try {
            int portNumber = 5001;

            // 소켓 서버 객체 만들기
            ServerSocket server = new ServerSocket(portNumber);
            printServerLog("서버 시작함: " + portNumber);

            while(true) {
                // 클라이언트가 접속했을 때 만들어지는 소켓 객체 참조하기
                Socket sock = server.accept();
                InetAddress clientHost = sock.getLocalAddress();
                int clientPort = sock.getPort();
                printServerLog("클라이언트 연결됨: " + clientHost + " : " + clientPort);

                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                Object obj = instream.readObject();
                printServerLog("데이터 받음: " + obj);

                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject(obj + " from Server.");
                outstream.flush();
                printServerLog("데이터 보냄.");

                sock.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
```

- 소켓 서버는 ServerSocket 클래스로 만든 후, 클라이언트로부터의 요청을 처리할 수 있는데 포트 번호는 클라이언트에서 접속할 5001번을 그대로 사용합니다. while 구문을 사용해서 클라이언트의 접속을 기다리다가 클라이언트의 접속 요청이 왔을 때 accept 메서드를 통해 소켓 객체가 반환되므로 클라이언트 소켓의 연결 정보를 확인할 수 있습니다. 여기서는 클라이언트에서 접속한 포트 번호를 확인한 후 보내온 문자열에 " from Server."라는 문자열을 붙여서 클라이언트로 다시 보내게 됩니다.

- 이제 마지막으로 /app/manifests 폴더 안에 있는 AndroidManifest.xml 파일을 열고 INTERNET 권한을 추가합니다.

#### SampleSocket>/app/manifests/AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.socket">

    <uses-permission android:name="android.permission.INTERNET" />
    
   ... 생략

</manifest>
```

- ﻿앱을 실행하고 화면 아래쪽의 [서버 시작] 버튼을 누르면 서버가 시작되었다는 로그가 화면 하단에 출력됩니다. 화면 상단에 있는 입력상자에 글자를 입력하고 [전송] 버튼을 누르면 그 글자가 서버로 전송되었다가 다시 클라이언트 쪽으로 전달되었다는 것을 알 수 있습니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image5.png)

> 실제 앱에서 네트워킹 관련 코드를 만들 때는 ObjectInputStream과 ObjectOutputStream은 잘 사용하지 않습니다. 이 두 클래스는 자바의 객체(Object) 정보를 편리하게 주고받을 수 있도록 만들어진 것이지만 자바가 아닌 다른 언어로 만들어진 서버와 통신할 경우에는 데이터 송수신이 정상적으로 이루어지지 않을 수 있습니다. 따라서 일반적으로는 DatalnputStream과 DataOutputStream을 많이 사용합니다.

* * * 
# 웹으로 요청하기

- 비연결성(Stateless)인 HTTP 프로토콜은 페이지 정보를 요청할 때마다 소켓을 새로 연결하고 응답을 받은 다음에는 소켓의 연결을 끊는 것이 일반적입니다. 그리고 그 소켓 연결 위에서 HTTP 프로토콜에 맞는 요청을 보내고 응답을 받아 처리합니다.

- HTTP로 웹 서버에 접속하는 것도 소켓의 경우와 마찬가지로 표준 자바의 방식을 그대로 사용할 수 있습니다. 자바에서 HTTP 클라이언트를 만드는 가장 간단한 방법은 URL 객체를 만들고 openConnection 메서드를 호출하여 HttpURLConnection 객체를 만드는 것입니다.

```java
public URLConnection openConnection()
```

- URL 객체에 들어 있는 문자열이 "http://"를 포함하면 HTTP 연결을 위한 객체를 만들게 되므로 openConnection 메서드가 반환하는 URLConnection 객체를 HttpURLConnection으로 형변환하여 사용할 수 있습니다. HttpURLConnection 객체로 연결할 경우에는 GET이나 POST와 같은 요청 방식과 함께 요청을 위한 파라미터들을 설정할 수 있습니다.

```java
public void setRequestMethod(String method)
public void setRequestProperty(String field, String newValue)
```

- 요청 방식을 지정하는 메서드는 setRequestMethod로 GET이나 POST 문자열을 파라미터로 전달합니다. setRequestProperty 메서드는 요청할 때 헤더에 들어가는 필드 값을 지정할 수 있도록 합니다. 웹페이지를 가져오는 기능은 간단하게 만들 수 있는데 이번에는 GET 방식을 사용하여 웹페이지 주소를 입력하면 해당 페이지의 내용을 가져오는 앱을 만들어 보겠습니다.

- 새로운 SampleHttp 프로젝트를 만들고 패키지 이름은 org.koreait.http로 수정합니다. activity_main.xml 파일을 열고 디자인 화면에서 최상위 레이아웃을 리니어 레이아웃을 리니어 레이아웃으로 변경합니다. 리니어 레이아웃의 orientation 속성 값은 vertical로 설정하고 그 안에 있던 텍스트뷰는 삭제합니다. 팔레트에서 입력상자와 버튼을 하나씩 넣고 스트롤뷰를 추가한 후 그 안에 텍스트뷰를 하나를 끌어다 추가합니다. 입력상자에는 사이트 주소를 입력할 것이며 버튼을 누르면 그 사이트로﻿부터 응답 데이터를 가져와 아래쪽의 텍스트뷰에 보여줄 것입니다. 버튼에는 '요청하기' 글자가 보이게 하고, 입력상자에는 '사이트 주소 입력'이라는 글자가 안내 글로 나타나도록 hint 속성을 설정합니다.

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

    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:ems="10"
        android:hint="사이트 주소 입력"
        android:inputType="textPersonName" />

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="요청하기" />

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <TextView
                android:id="@+id/textView"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:textSize="20sp" />
        </LinearLayout>
    </ScrollView>
	
</LinearLayout>
```

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image6.png)

- 이제 MainActivity.java 파일을 열고 버튼을 클릭했을 때 웹으로 요청하는 코드를 추가합니다.
 
####  SampleHttp>/app/java/org.koreait.http/MainActivity.java

```java
package org.koreait.http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    Handler handler = new Handler();

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
                final String urlStr = editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 스레드 안에서 request 메서드 호출하기
                        request(urlStr);
                    }
                }).start();
            }
        });
    }
}
```

- 버튼을 누르면 사용자가 입력한 사이트 주소를 이용해 request 메서드를 호출합니다. request 메서드 안에서는 인터넷을 사용할 것이므로 스레드 안에서 동작하도록 스레드 객체를 하나 생성하고 그 안에서 request 메서드를 호출하도록 합니다. 스레드에서 처리한 결과물을 화면에 표시할 때 사용하도록 핸들러 객체도 만들어 변수에 할당해 둡니다. request 메서드의 코드는 다음과 같습니다.

####  SampleHttp>/app/java/org.koreait.http/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {

	... 생략 

    public void request(String urlStr) {
        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(urlStr);

            // HttpURLConnection 객체 만들기
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                int resCode = conn.getResponseCode();
                // 입력 데이터를 받기 위한 Reader 객체 생성하기
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while(true) {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }

                    output.append(line + "\n");
                }
                reader.close();
                conn.disconnect();
            }
        } catch (Exception ex) {
            println("예외 발생함: " + ex.toString());
        }

        println("응답 -> " + output.toString());
    }

    public void println(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n");
            }
        });
    }
}
```

- ﻿request 메서드에서는 응답 결과물을 모아 화면에 출력합니다. 화면에 출력할 때 사용하는 println메서드는 핸들러를 사용하면서 화면에 들어있는 텍스트뷰의 append 메서드를 호출하도록 합니다. request 메서드 안에 정의된 웹페이지 요청 부분을 보면 가장 먼저 URL 객체를 만들고 있습니다. 파라미터로 전달된 URL 문자열을 이용해 만들어진 객체의 openConnection 메서드를 호출하면 HttpURLConnection 객체가 반환됩니다.

- 이 객체에 GET 방식으로 요청한다는 내용을 setRequestMethod로 설정하고 getResponseCode 메서드를 호출하면 이 시점에 내부적으로 웹 서버에 페이지를 요청하는 과정을 수행하게 됩니다. setConnectionTimeout 메서드는 연결 대기 시간을 설정하는 것으로 10초 동안 연결되기를 기다린다는 의미이며, setDoInput 메서드는 이 객체의 입력이 가능하도록 만들어 줍니다. 응답 코드가 HTTP_OK인 경우에는 정상적으로 응답이 온 경우이므로 응답으로 들어온 스트림을 문자열로 변환하여 반환합니다. 만약 요청한 주소의 페이지가 없는 경우에는 HTTP_NOT_FOUND 코드가 반환되며, 이외에도 다양한 응답 코드가 정의되어 있습니다. 스트림에서 한 줄씩 읽어 들이는 메서드인 readLine은 BufferedReader 클래스에 정의되어 있으므로 HttpURLConnection 객체의 스트림을 이 클래스의 객체로 만든 후에 처리합니다.

- 이 앱이 인터넷 권한을 사용하므로 매니페스트 파일을 열고 다음 권한을 추가합니다. 그리고 <application> 태그에 속성을 하나 더 추가합니다.

#### SampleHttp>/app/manifests/AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.http">

    <uses-permission android:name="android.permission.INTERNET" />

	... 생략

</manifest>

```

앱을 실행하고 입력창에 다음 주소를 입력한 후 버튼을 누르면 다음과 같은 결과 화면을 볼 수 있습니다. 

```
https://jsonplaceholder.typicode.com/todos
```

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image7.png)

