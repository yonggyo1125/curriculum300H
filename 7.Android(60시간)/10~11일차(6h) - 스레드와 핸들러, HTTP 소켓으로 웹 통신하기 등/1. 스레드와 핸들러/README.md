# 핸들러 이해하기

- 새로운 프로젝트를 만들면 자동으로 생성되는 메인 액티비티는 앱이 실행될 때 하나의 프로세스에서 처리됩니다. 따라서 메인 액티비티 내에서 이벤트를 처리하거나 특정 메서드를 정의하여 기능을 구현할 때도 같은 프로세스 안에서 실행됩니다. 같은 프로세스 안에서 일련의 기능이 순서대로 실행될 때 대부분은 큰 문제가 없지만, 대기 시간이 길어지는 네트워크 요청 등의 기능을 수행할 때는 화면에 보이는 UI도 멈춤 상태로 있게 되는 문제가 생길 수 있습니다.

- 이런 문제를 해결하려면 하나의 프로세스 안에서 여러 개의 작업이 동시 수행되는 멀티 스레드 방식을 사용하게 됩니다. 스레드(Thread)는 동시 수행이 가능한 작업 단위이며, 현재 수행 중인 작업 이외의 기능을 동시에 처리할 때 새로운 스레드를 만들어 처리합니다. 이런 멀티 스레드 방식은 같은 프로세스 안에 들어 있으면서 메모리 리소스를 공유하므로 효율적인 처리가 가능합니다. 하지만 동시에 리소스에 접근할 때 데드락(DeadLock)이 발생하여 시스템이 비정상적으로 동작할 수도 있습니다.

- 여러 개의 스레드가 동시에 공통 메모리 리소스에 접근할 때 데드락이 발생합니다. 데드락이란 동시에 두 곳 이상에서 요청이 생겼을 때 어떤 것을 먼저 처리할지 판단할 수 없어 발생하는 시스템상의 문제입니다. 이런 경우는 런타임 시의 예외 상황이므로 디버깅하기 쉽지 않은 경우가 많습니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/1.%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC/images/image1.png)

- 지연 시간이 길어질 수 있는 앱이라면 오랜 시간 작업을 수행하는 코드를 별도로 분리한 다음 UI에 응답을 보내는 방식을 사용합니다. 이를 위해 안드로이드가 제공하는 두 가지 시나리오를 정리하면 다음과 같습니다.

|구분|시나리오|
|----|------|
|서비스 사용하기|백그라운드 작업은 서비스로 실행하고 사용자에게는 알림 서비스로 알려줍니다. 만약 메인 액티비티로 결과 값을 전달하고 이를 이용해서 다른 작업을 수행하고자 한다면 브로드캐스팅으로 결과 값을 전달할 수 있습니다.|
|스레드 사용하기|스레드는 같은 프로세스 안에 있기 때문에 작업 수행의 결과를 바로 처리할 수 있습니다. 그러나 UI 객체는 직접 접근할 수 없으므로 핸들러(Handler) 객체를 사용합니다.|

- ﻿안드로이드에서 UI 처리할 때 사용되는 기본 스레드를 '메인 스레드'라고 부릅니다. 메인 스레드에서이미 UI에 접근하고 있으므로 <b>새로 생성한 다른 스레드에서는 핸들러 (Handler) 객체를 사용해서 메시지를 전달</b>함으로써 <b>메인 스레드에서 처리</b>하도록 만들 수 있습니다.

> 동시 접근으로 발생하는 데드락 문제를 해결하는 가장 간단한 방법은 작업을 순서대로 처리하는 것입니다. 이 역할은 각 스레드 안에서 동작하는 핸들러가 담당합니다.

## 스레드 사용하기

- 안드로이드에서는 표준 자바의 스레드를 그대로 사용할 수 있습니다. 따라서 표준 자바처럼 스레드를 사용하는 가장 단순한 방법은 다음과 같습니다. 스레드는 new 연산자로 객체를 생성한 후 start 메서드를 호출하면 시작할 수 있습니다. Thread 클래스에 정의된 생성자는 크게 파라미터가 없는 경우와 Runnable 객체를 파라미터로 갖는 두 가지로 구분할 수 있습니다. 일반적으로 Thread 클래스를 상속한 새로운 클래스를 정의한 후 객체를 만들어 시작하는 방법을 사용합니다.

- 이제 스레드를 하나 만들어서 실습해 보겠습니다. SampleThread 프로젝트를 만들고 org.koreait.thread로 패키지 이름을 수정합니다. activity_main.xml 파일을 열어서 텍스트뷰는 삭제하고 버튼을하나 추가합니다. 버튼은 화면 가운데에 위치시키고 버튼 글자는 '스레드 시작'으로 수정합니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/1.%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC/images/image2.png)

- [스레드 시작] 버튼을 눌렀을 때 스레드를 하나 만들어 동작시킬 수 있도록 MainActivity.java 파일을열고 다음 코드를 입력합니다.

#### SampleThread>/app/java/org.koreait.thread/MainActivity.java

```java
package org.koreait.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 스레드 객체 생성하고 시작하기
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });
    }

    class BackgroundThread extends Thread {
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}

                value += 1;
                Log.d("Thread", "value : " + value);
            }
        }
    }
}
```

- 버튼을 누르면 스레드가 동작하고 value 변수의 값은 스레드에 의해 1초마다 1씩 증가합니다. 이러한 기능을 수행하는 스레드를 BackgroundThread라는 이름으로 정의했으며 이 스레드는 Thread 클래스를 상속받고 있습니다. 스레드 클래스를 정의했다면 그 클래스로부터 스레드 객체를 만들 수 있으며, start 메서드를 호출하면 스레드가 시작됩니다. 스레드를 시작시키면 그 안에 run 메서드가 실행됩니다. run 메서드 안에서는 반복문을 돌면서 1초마다 value의 값을 증가시킵니다.

-앱을 실행하고 버튼을 누르면 Logcat 창에 value 변수의 값이 반복적으로 출력되는 것을 확인할 수 있습니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/1.%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC/images/image3.png)

- 이 코드를 보면 안드로이드의 액티비티에서 표준 자바의 스레드를 그대로 사용할 수 있음을 확실하게 알 수 있을 것입니다. 이제 Logcat이 아니라 화면에 value 값을 출력하도록 수정해 보겠습니다.

- activity_main.xml 파일을 열고 버튼 위쪽에 텍스트뷰를 하나 추가합니다. 텍스트뷰의 text 속성 값은 'value 값'이라고 설정하고 textSize 속성 값은 30sp로 설정합니다.

- MainActivity.java 파일에는 다음 코드처럼 스레드의 run 메서드 안에서 텍스트뷰 객체의 setText 메서드를 호출하도록 코드를 수정합니다.


```java

... 생략 

public class MainActivity extends AppCompatActivity {
    int value = 0;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        ... 생략 
    }

    class BackgroundThread extends Thread {
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}

                value += 1;
                Log.d("Thread", "value : " + value);

                // 스레드 안에서 텍스트뷰의 setText 메서드 호출하기
                textView.setText("value 값 : " + value);
            }
        }
    }
}
```

- 소스 코드에는 에러 표시가 없습니다. 하지만 앱을 실행하면 앱이 정상적으로 실행되지 못하고 에리가 발생합니다. 앱이 비정상 종료되었을 때 Logcat 장에서 에러 메시지를 살펴보면 다음과 같습니다.



이 에러 로그는 여러분이 직접 만든 BackgroundThread 객체에서 UI 객체를 직접 접근했다는 것을 말하고 있습니다. 결국 메인 스레드에서 관리하는 UI 객체는 여러분이 직접 만든 스레드 객체에서는 접근할 수 없다는 의미 입니다. 이 문제는 핸들러를 사용해서 해결할 수 있습니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/1.%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC/images/image4.png)

## 핸들러로 메시지 전송하기

- 앱을 실행할 때 프로세스가 만들어지면 그 안에 메인 스레드가 함께 만들어집니다. 그리고 최상위에서 관리되는 앱 구성 요소인 액티비티, 브로드캐스트 수신자 등과 새로 만들어지는 윈도우를 관리하기 위한 메시지 큐(Message Queue)를 실행합니다. <b>메시지 큐를 사용하면 순차적으로 코드를 수행</b>할 수 있는데, 이렇게 <b>메시지 큐로 메인 스레드에서 처리할 메시지를 전달하는 역할</b>을 <b>핸들러 클래스</b>가 담당합니다. 결국 핸들러는 실행하려는 특정 기능이 있을 때 핸들러가 포함되어 있는 스레드에서 순차적으로 실행시킬 때 사용하게 됩니다. 핸들러를 이용하면 특정 메시지가 미래의 어떤 시점에 실행되도록 스케줄링 할 수도 있습니다.

- 다음은 핸들러의 메시지 처리 방법을 그림으로 표현한 것입니다. 메인스레드에 접근하기 위해 핸들러를 사용할 때 필요한 세 가지 단계를 보여줍니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/1.%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC/images/image5.png)

﻿- 새로 만든 스레드(스레드 #1)가 수행하려는 정보를 메인 스레드로 전달하기 위해서는 먼저 캔들러가 관리하는 메시지 큐에서 처리할 수 있는 메시지 객체 하나를 참조해야 합니다. 이 첫 번째 과정에서는 obtainMessage 메서드를 이용할 수 있으며 호흡의 결과로 메시지 객체를 반환받게 됩니다. 이 메시지 객체에 필요한 정보를 넣은 후 sendMessage 메서드를 이용해 메시지 큐에 넣을 수 있습니다. 메시지 큐에 들어간 메시지는 순서대로 핸들러가 처리하게 되며 이때 handleMessage 메서드에 정의된 기능이 수행됩니다. 이때 handleMessage에 들어 있는 코드가 수행되는 위치는 새로 만든 스레드가 아닌 메인 스레드가 됩니다.앞에서 만들었던 프로젝트에 핸들러를 적용해보기 위해 SampleThread 프로젝트를 복사하여 Samplethread2 프로젝트를 만듭니다. 이때 애플리케이션의 실행이 원활할 수 있도록 app 폴더의 build 폴더를 삭제하는 것을 잊지 마세요. 새로 복사한 프로젝트를 시작화면에서 [Open an existing Android Studio project] 메뉴를 눌러 열어줍니다. 프로젝트 창에서 /app/java/org.techtown.thread/MainActivity. java 파일을 더블클릭하여 수정합니다. 

> 파일 탐색기에서 C:\Users\사용자계정\Android StudioProjects 폴더에 있는 SampleThread 폴더를 복사해서 붙여 넣은 후 폴더명을 SampleThread2로 변경하면 됩니다.

#### SampleThread2>/app/java/org.koreait.thread/MainActivity.java

```java
package org.koreait.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MainHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 스레드 객체 생성하고 시작하기
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });

        handler = new MainHandler();
    }

    class BackgroundThread extends Thread {
        int value = 0;

        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}

                value += 1;
                Log.d("Thread", "value : " + value);

                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);

                handler.sendMessage(message); // 핸들러로 메시지 객체 보내기

            }
        }
    }

    class MainHandler extends Handler {
        // 핸들러 안에서 전달 받은 메시지 객체 처리하기
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("value 값: " + value);
        }
    }
}
```

- Handler 클래스를 상속한 MainHandler 클래스가 새로 정의되었습니다. Handler와 Message는 android.os 패키지에 들어 있는 클래스는 사용합니다. Handler 클래스에는 handleMessage 메서드가 들어 있어 이 메서드를 다시 정의하면 메시지가 메인 스레드에서 수행될 때 필요한 기능을 넣어둘 수 있습니다. 이렇게 정의한 핸들러는 onCreate 메서드에서 액티비티가 초기화될 때 new 연산자를 이용해 객체로 만들어집니다.

- 새로 만든 스레드 객체에서 수행한 작업의 결과가 나왔을 때는 핸들러 객체의 obtainMessage로 메시지 객체 하나를 참조한 후 sendMessage 메서드를 이용해 메시지 큐에 넣게 됩니다. 그런데 데이터를 전달하고자 할 때는 어떻게 할까요? 텍스트뷰 객체의 setText 메서드를 호출하는 코드가 핸들러 클래﻿스의 handleMessage 메서드 안으로 이동해야 하므로 이 handleMessage 메서드로 value 값을 전달해야 하는 문제가 생깁니다. 따라서 이 value 값을 Message 객체에 넣어서 보내는 것이 필요합니다. Message 객체에는 Bundle 객체가 들어 있어 putOOO 메서드로 데이터를 넣었다가 getOOO 메서드로 데이터를 가져올 수 있게 되어 있습니다(여기에서 ○○○은 자료형에 따라 달라질 수 있음).

- 앱을 실행하고 버튼을 누르면 화면에 value 값이 표시되고 계속 변하는 것을 확인할 수 있습니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/1.%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC/images/image6.png)

## Runnable 객체 실행하기

- 지금까지 핸들러를 사용해서 메시지를 전송하고 순서대로 이를 실행하는 방법을 살펴보았습니다. 이 방법은 가장 일반적이지만 개발자 입장에서는 코드가 복잡하게 보이는 단점이 있습니다. 좀 더 간단한 방법으로 메인 스레드에서 실행시킬 수 있습니다. 핸들러 클래스는 메시지 전송 방법 이외에 Runnable 객체를 실행시킬 수 있는 방법을 제공합니다. 즉, 새로 만든 Runnable 객체를 핸들러의 post 메서드로 전달해주면 이 객체에 정의된 run 메서드 안의 코드들은 메인 스레드에서 실행됩니다.

- 앞 단락에서 만든 코드를 메시지 전송 방식에서 Runnable 객체 실행 방식으로 바꿔보겠습니다. 파일 탐색기에서 SampleThread2 프로젝트를 복사해 SampleThread3 프로젝트를 만듭니다. 이번에도 app 폴더의 build 폴더를 삭제하세요. 안드로이드 스튜디오에서 새로 복사한 SampleThread3 프로젝트를 엽니다. 프로젝트 창이 열리면 MainActivity.java 파일을 열고 소스 코드를 수정합니다.

#### ﻿Sample Thread3>/app/java/org.koreait.thread/MainActivity.java

```java
package org.koreait.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    Handler handler = new Handler(); // API의 기본 핸들러 객체 생성하기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 스레드 객체 생성하고 시작하기
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });
    }

    class BackgroundThread extends Thread {
        int value = 0;

        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}

                value += 1;
                Log.d("Thread", "value : " + value);

               // 핸들러의 post 메서드 호출하기
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("value 값: " + value);
                    }
                });
            }
        }
    }
}
```

- ﻿코드가 전체적으로 변경되었습니다. 메시지 처리를 위해 새로 정의했던 MainHandler 클래스는 이제 더 이상 필요가 없으므로 일반적으로 사용하는 Handler 클래스로 객체를 생성합니다. Handler 객체를 만들어 변수에 할당해두면 이 객체의 post 메서드를 호출할 수 있습니다. 스레드 안에서 결과를 텍스트뷰에 표시하려면 post 메서드를 호출하면서 Runnable 객체를 만들어줍니다. 그리고 그 안에 텍스트뷰를 접근하는 코드를 넣어줍니다. 이렇게 하면 결과를 텍스트뷰에 보여주는 코드가 스레드 안에 있을 수 있으므로 좀 더 코드를 이해하기 쉽습니다.

- 이 코드에서 보는 것처럼 post 메서드로 전달되는 Runnable 객체는 스레드의 작업 결과물로 만들어지는 데이터를 처리해야 합니다. 따라서 결과물을 화면에 보여주어야 하는 부분이 있을 경우 new 연산자로 Runnable 인터페이스를 구현하는 새로운 객체를 만들어 사용하는 것이 일반적입니다.

- 앱을 실행하면 SampleThread2 앱과 동일한 결과를 볼 수 있습니다. 이렇게 post 메서드를 호출하는 방법이 훨씬 간단해 보이므로 실제 앱을 만들 때 더 많이 사용하게 됩니다.

> <b>runOnUiThread</b>는 핸들러 객체를 만들지 않고도 메인 스레드에서 동작하게 만드는 간단한 방법입니다. 따라서 다음 코드처럼 run 메서드 안에 뷰를 접근하는 코드를 넣으면 메인 스레드에서 동작하게 할 수 있습니다.

```java
runOnUiThread(new Runnable() {
	@Override
	public void run() {
	
	}
});
```

* * * 
# 일정 시간 후에 실행하기

- 웹 서버와 같은 원격 서버에 접속한 후 웹페이지를 요청할 때 응답이 늦어지거나 응답이 없으면 앱이 대기하고 있는 상황이 지속되는 문제가 생깁니다. 이런 경우에는 기본적으로 별도의 스레드를 만들어 처리하게 됩니다. 하지만 버튼을 클릭해서 간단하게 접속 처리하는 경우에는 메인 스레드 내에서 지연시간을 주는 것만으로도 UI의 멈춤 현상을 방지할 수 있습니다. 단순히 Thread.sleep 메서드를 사용해서 잠깐 대기 상태로 있다가 다시 실행할 수도 있습니다. 하지만 핸들러로 지연 시간을 주었을 때 핸들러로 실행되는 코드는 메시지 큐를 통과하면서 순차적으로 실행되기 때문에 UI 객체들에 영향을 주지 않으면서 지연 시간을 두고 실행됩니다.

- 일정 시간 후에 실행되는 예제를 실습하기 위해서 새로운 SampleDelayed 프로젝트를 만들고 패키지 이름은 org.techtown.delayed로 수정합니다. 그런 다음 activity_main.xml 파일 안에 글자를 보여줄 텍스트뷰 하나와 버튼 하나를 추가합니다. 버튼의 글자를 요청하기'로 수정하고 텍스트뷰에는 '결과'라는 글자로 수정합니다. 이때 텍스트뷰의 글자 크기는 30sp로 설정합니다. XML 레이아웃을 완성했다면 MainActivity.java 파일을 열고 다음 코드를 입력합니다.

#### SampleDelayed>/app/java/org.koreait.delayed/MainActivity.java

```java
package org.koreait.delayed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
    }

    private void request() {
        String title = "원격 요청";
        String message = "데이터를 요청하시겠습니까?";
        String titleButtonYes = "예";
        String titleButtonNo = "아니오";
        AlertDialog dialog = makeRequestDialog(title, message, titleButtonYes, titleButtonNo);
        dialog.show();

        textView.setText("대화상자 표시중...");
    }

    private AlertDialog makeRequestDialog(CharSequence title, CharSequence message, CharSequence titleButtonYes, CharSequence titleButtonNo) {
        AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);
        requestDialog.setTitle(title);
        requestDialog.setMessage(message);
        requestDialog.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textView.setText("5초 후에 결과 표시됨.");

                // 핸들러의 postDelayed 메서드 호출하기
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("요청 완료됨.");
                    }
                }, 5000);
            }
        });

        requestDialog.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return requestDialog.create();
    }
}
```

- 코드를 입력할 때 Handler는 android.os 패키지의 것을 선택하고 AlertDialog는 androidx.appcompat.app 패키지의 것을 선택합니다. 화면에 추가한 [요청하기] 버튼을 누르면 새로 정의한 request 메서드가 호출되는데 이 메서드는 AlertDialog를 이용하여 대화상치를 보여줍니다. 대회 상치의 [예] 버﻿튼을 누르면 핸들러 객체의 postDelayed 메서드를 사용해서 약간의 시간이 지난 후 코드가 실행되게 만듭니다.

- 핸들러는 메시지 큐를 사용하므로 메시지들을 순서대로 처리하지만 메시지를 넣을 때 시간을 지정하면 원하는 시간에 메시지를 처리하게 만들 수 있습니다. 따라서 일정 시간 후에 실행시킬 때 유용하게 사용됩니다. 시간을 지정할 때는 핸들러의 sendMessage 메서드와 유사한 이름을 가진 다음과 같은 두가지 메서드를 사용할 수 있습니다.


```java
public boolean sendMessageAtTime(Message msg, long uptimeMillis)
public boolean sendMessageDelayed(Message msg, long delayMillis)
```

- 첫 번째 메서드는 메시지를 보낼 때 시간을 지정할 수 있으며, 두 번째 메서드는 메시지가 일정 시간이 지난 후 실행되도록 설정할 수 있습니다. Runnable 객체를 실행하는 post 메서드도 postAtTime과 postDelayed 메서드가 있어 같은 기능을 수행합니다.

- 앱을 실행하고 [요청하기] 버튼을 누르면 대화상자가 표시됩니다. 대화상자의 [예] 버튼을 누르면 5초 뒤에 텍스트뷰에 다른 글자를 표시합니다. 이렇게 일정 시간이 지난 후에 특정 코드를 실행시킬 때 핸들러 객체의 postDelayed 메서드를 호출할 수 있습니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/1.%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC/images/image7.png)

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/1.%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC/images/image8.png)

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/1.%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC/images/image9.png)

