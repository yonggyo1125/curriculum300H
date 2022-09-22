# 서비스

- 앱이 실행되어 있다고 해서 항상 화면이 보이는 것은 아닙니다. 예를 들어, 카카오톡은 앱이 실행되어있지 않거나 화면이 사용자에게 보이지 않는 상태에서도 다른 사람이 보낸 메시지를 받을 수 있습니다. 왜 그럴까요? 화면 없이 백그라운드에서 실행되는 서비스(Service)가 있기 때문입니다. 이때 화면 뒤의공간을 뒷단 또는 백그라운드라고 부릅니다. 지금부터는 백그라운드라는 말을 많이 사용하겠습니다.

- 서비스란 백그라운드에서 실행되는 앱의 구성 요소를 말합니다. 이미 만들어보았던 액티비티는 앱의구성 요소라고 불리는데 서비스도 동일하게 앱의 구성 요소 역할을 합니다. 서비스도 앱의 구성 요소이므로 시스템에서 관리합니다. 그래서 액티비티를 만들 때 매니페스트 파일에 등록했던 것처럼 새로 만든 서비스도 매니페스트 파일에 꼭 등록해야 합니다

## 서비스의 실행 원리와 역할

- 그러면 서비스는 어떻게 실행될까요? 그리고 앱에서 어떤 역할을 할까요? 서비스를 실행하려면 메인 액티비티에서 startService 메서드를 호출하면 됩니다. 서비스의 주요 역할 중 하나는 단말이 항상 실행되어 있는 상태로 다른 단말과 데이터를 주고받거나 필요한 기능을 백그라운드에서 실행하는 것입니다. 그래서 서비스는 실행된 상태를 계속 유지하기 위해 서비스가 비정상적으로 종료되더라도 시스템이 자동으로 재실행합니다. 다음 그림을 통해 이해해 보세요.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image1.png)

- 서비스를 시작시키기 위해 startService 메서드를 호출할 때는 인텐트 객체를 파라미터로 전달합니다.이 인텐트 객체는 어떤 서비스를 실행할 것인지에 대한 정보를 담고 있으며 시스템은 서비스를 시작시킨 후 인텐트 객체를 서비스에 전달합니다.

- 그런데 서비스가 실행 중이면 실행 이후에 startService 메서드를 여러 번 호출해도 서비스는 이미 메모리에 만들어진 상태로 유지됩니다. 따라서 startService 메서드는 서비스를 시작하는 목적 이외에 인﻿텐트를 전달하는 목적으로도 자주 사용됩니다. 예를 들어, 액티비티에서 서비스로 데이터를 전달하려면 인텐트 객체를 만들고 부가 데이터(Extra data)를 넣은 후 startService 메서드를 호출하면서 전달하면 됩니다. 그런데 앞에서 가정하고 있는 상태는 서비스가 startService 메서드에 의하여 메모리에 만들어져 있는 상태입니다. 이런 경우에는 시스템이 onCreate 메서드가 아니라 onStartCommand 메서드를 실행합니다. onStartCommand 메서드는 서비스로 전달된 인텐트 객체를 처리하는 메서드입니다. 일단 서비스의 실행 원리와 역할은 여기까지만 설명하겠습니다. 나머지는 실습을 진행하며 자세히 설명하겠습니다.

- 서비스가 동작하는 방식을 이해하기 위해 새로운 프로젝트를 만들고 그 안에 서비스 클래스를 정의하여 실습해 보겠습니다. 안드로이드 스튜디오에서 새로운 SampleService 프로젝트를 만듭니다. 프로젝트를 만들 때 패키지 이름은 org.koreait.service로 입력합니다. 프로젝트가 만들어지면 왼쪽 프로젝트 창에서 app 폴더를 선택하고 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [New → Service → Service] 메뉴를 선택합니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image2.png)

- 그러면 새로운 서비스를 만들 수 있는 대화상자가 표시됩니다. Class Name: 입력란에는 디폴트 값으로 MyService가 입력되어 있습니다. 입력된 값을 그대로 두고 아래쪽 [Finish] 버튼을 누릅니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image3.png)

﻿- 새로운 서비스가 만들어지면 MyService.java 파일이 만들어지는 것뿐만 아니라 AndroidManifest.xml 파일 안에 \<service\> 태그도 추가됩니다. 앞에서도 설명했지만 서비스는 시스템에서 관리하므로 매니페스트에 넣어 앱 설치 시에 시스템이 알 수 있게 해야 합니다. MyService.java 파일에는 자동으로 만들어진 생성자 메서드와 onBind 메서드만 있습니다. 하지만 서비스의 수명주기를 관리하기 위하여 onCreate, onDestroy 메서드와 인텐트 객체를 전달받기 위한 onStartCommand 메서드를 추가하겠습니다.

- MyService 클래스 안에 마우스 커서를 둔 상태로 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [Generate → Override Methods] 메뉴를 선택합니다. 부모 클래스의 메서드를 재정의할 수 있는 대화상자가 표시되면 [Ctrl] 을 누른 상태로 Create, onDestroy, onStartCommand 메서드를 선택하고  [OK] 버튼을 누릅니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image4.png)

- 이제 activity_main.xml 파일을 열고 기존에 있던 텍스트뷰를 삭제한 후 버튼과 입력상자 하나를 화면 가운데 추가합니다. 그리고 버튼은 '서비스로 보내기' 글자가 표시되도록 수정합니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image5.png)

﻿- [서비스로 보내기] 버튼을 누르면 입력상자에 입력한 글자를 서비스에 전달하도록 만들 것입니다. 서비스에 데이터를 전달할 때는 startService 메서드를 사용하며 인텐트 안에 부가 데이터를 추가하여 전달하면 됩니다. MainActivity.java 파일을 열고 다음 코드를 입력합니다.

####  SampleService>/app/java/org.koreait.service/MainActivity.java
 
```java 
package org.koreait.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();

                // 인텐트 객체 만들고 부가 데이터 넣기
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", name);

                startService(intent); // 서비스 시작하기
            }
        });
    }
}
```

- 인텐트 안에는 두 개의 부가 데이터를 넣었습니다. 하나는 command라는 키(Key)를 부여하였으며 또다른 하나는 name이라는 키를 부여했습니다. command는 서비스 쪽으로 전달한 인텐트 객체의 데이터가 어떤 목적으로 사용되는지를 구별하기 위해 넣은 것입니다. name은 입력상자에서 가져온 문자열을 전달하기 위한 것입니다.

- startService 메서드에 담은 인텐트 객체는 MyService 클래스의 onStartCommand 메서드로 전달됩니다. MyService.java 파일을 열고 다음 코드를 수정 및 추가 입력합니다.

#### SampleService>/app/java/org.koreait.service/MyService.java

```java
package org.koreait.service;

import static android.content.ContentValues.TAG;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() 호출됨.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() 호출됨.");

        // 인텐트 객체가 널이 아니면 processCommand() 메서드 호출하기
        if (intent == null) {
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        // 인텐트에서 부가 데이터 가져오기
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "command : " + command + ", name : " + name);

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            Log.d(TAG, "Waiting " + i + " seconds.");
        }
    }
	
	... 생략 
}
```

- ﻿Service 클래스를 상속하는 Myservice 클래스 안에는 onCreate, onDestroy, onStartCommand 메서드가 있습니다. 이번에는 startservice 메서드에 전달한 인텐트의 부가 데이터를 출력하기 위하여 토스트 메시지가 아니라 Log.d 메서드를 사용해서 로그를 유력합니다. 새로운 방법이라고 당황한 필요는없습니다. 토스트 메시지는 화면에 메시지를 띄우는 것이고 로그는 Logeat 창에서 확인할 수 있는 메시지입니다. 심부에서는 로그를 더 많이 사용하니 알아두는 것이 좋습니다.

## 로그 사용하여 인텐트의 부가 데이터 출력하기

- 로그 출력을 위해서는 첫 번째 파라미터로 로그를 구분할 수 있는 문자열을 전달해야 합니다. 이것을 보통 태그(Tag)라고 부릅니다. 여기서는 "MyService"라는 문자열을 상수로 정의한 후 사용했습니다. 서비스에 추가한 세 개의 메서드 중 onStartCommand 메서드가 인텐트 객체를 전달받습니다. 이때 onStartCommand 메서드는 서비스 내에서 아주 중요한 역할을 합니다. 서비스는 시스템에 의해 자동으로 다시 시작될 수 있기 때문에 onStartCommand 메서드로 전달되는 인텐트 객체가 null인 경우도 검사합니다. 만약 인텐트 객체가 null이면 onStartCommand 메서드는 <b>Service.START_STICKY을 반환</b>합니다. 그리고 이 값을 반환하면 <b>서비스가 비정상 종료</b>되었다는 의미이므로 <b>시스템이 자동으로 재시작</b>합니다. 만약 자동으로 재시작하지 않도록 만들고 싶다면 다른 상수를 사용할 수 있습니다.

- 여기서는 onStartCommand 메서드에 코드를 너무 많이 넣으면 복잡해 보일 수 있어 새로운 processCommand 메서드를 정의하여 호출합니다. processCommand 메서드는 for문을 사용해 5초 동안 1초에 한 번씩 로그를 출력합니다.

- <b>서비스가 서버 역할을 하면서 액티비티와 연결될 수 있도록 만드는 것을 바인딩(Binding)</b>이라고 합니다. <b>이를 위해서는 onBind 메서드를 재정의</b>해야 합니다. 하지만 여기서는 바인딩 기능을 사용하지 않으므로 메서드가 정의된 상태로 두고 다음으로 진행하세요.

- 앱을 실행하고 화면에 보이는 버튼을 클릭하면 서비스가 실행됩니다. 서비스는 화면에 보이지 않으므로 안드로이드 스튜디오 창 하단에 보이는 [Logcat] 탭에서 어떤 로그가 출력되는지 확인합니다. 로그를 볼 때는 Logcat 창의 우측 상단에 있는 콤보박스에서 Edit Filter Configuration을 선택하고 Tag:란에 MyService를 입력합니다. 이렇게 하면 여러분의 앱에서 출력하는 로그만 선택적으로 볼 수 있습니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image6.png)

﻿- 액티비티에서 인텐트에 넣어 전달한 데이터는 서비스에서 그대로 출력됩니다. 그런데 이렇게 액티비티에서 서비스로 데이터를 전달할 수 있는 것처럼 서비스에서 액티비티로도 데이터를 전달할 수 있어야 합니다. 서비스에서 액티비티로 전달하고 싶다면 서비스에서 startActivity 메서드를 사용합니다. startActivity 메서드를 호출하면서 인텐트 객체를 전달하면 액티비티에서는 그 안에 들어 있는 부가 데이터를 받아볼 수 있습니다.

그럼 processCommand 메서드의 마지막 부분에서 액티비티 쪽으로 인텐트를 전달해 보겠습니다. 메인 액티비티에서는 이 인텐트를 전달 받아 화면에 보여줄 수 있을 것입니다. 다음은 processCommand 메서드에 추가한 코드입니다.

```java

... 생략 

	private void processCommand(Intent intent) {
		... 생략 

        // 액티비티를 띄우기 위한 인텐트 객체 만들기
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);

        // 인텐트에 플래그 추가하기
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + " from service.");
        startActivity(showIntent);
    }
```
- 인텐트 객체를 new 연산자로 생성할 때 첫 번째 파라미터로는 getApplicationContext 메서드를 호출하여 Context 객체가 전달되도록 했습니다. 그리고 두 번째 파라미터로는 MainActivity.class 객체가 전달되도록 했습니다. 이 인텐트 객체를 startActivity 메서드로 호출하면서 전달하면 메인 액티비티 쪽으로 인텐트 객체가 전달됩니다. 이 인텐트 객체에는 부가 데이터를 두 개 추가했으며 하나는 command, 다른 하나는 name 키를 갖고 있습니다. <b>이렇게 서비스에서 startActivity 메서드를 호출할 때는 새로운 태스크(Task)를 생성하도록 FLAG_ACTIVITY_NEW_TASK 플래그를 인텐트에 추가</b>해야 합니다. <b>서비스는 화면이 없기 때문에 화면이 없는 서비스에서 화면이 있는 액티비티를 띄우려면 새로운 태스크를 만들어야 하기 때문</b>입니다. 그리고 <b>MainActivity 객체가 이미 메모리에 만들어져 있을 때 재사용하도록 FLAG_ACTIVITY_SINGLE_TOP과 FLAG_ACTIVITY_CLEAR_TOP 플래그도 인텐트에 추가</b>합니다.

- ﻿서비스에서 5초 후에 메인 액티비티에 전달한 인텐트는 메인 액티비티에서 받아 처리할 수 있습니다. MainActivity.java 파일을 열고 다음 코드를 입력합니다.

#### SampleService>/app/java/org.koreait.service/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	
        ... 생략 

        // 액티비티가 새로 만들어질 떄 전달된 인텐트 처리하기
        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {

        // 액티비티가 이미 만들어져 있을 때 전달된 인텐트 처리하기
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "command : " + command + ", name : " + name, Toast.LENGTH_LONG).show();
        }
    }
}
```

- MainActivity가 메모리에 만들어져 있지 않은 상태에서 처음 만들어진다면 onCreate 메서드 안에서 getIntent 메서드를 호출하여 인텐트 객체를 참조합니다. 하지만 MainActivity가 이미 메모리에 만들어져 있다면 onCreate 메서드는 호출되지 않고 onNewIntent 메서드가 호출됩니다. 그리고 인텐트는 이 메서드의 파라미터로 전달됩니다. 여기서는 processIntent 메서드를 만들고 onNewIntent 메서드﻿가 호출되었을 때 processIntent 메서드를 호출하도록 했습니다. 인텐트로 전달받은 데이터는 토스트메시지로 보이도록 했으므로 앱을 실행하고 버튼을 누른 후 5초를 기다리면 다음과 같은 토스트 메시지를 확인할 수 있습니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image7.png)

- 요즘 앱들은 서비스를 자주 사용합니다. 따라서 액티비티에서 서비스로 데이터를 전달하거나 서비스에서 액티비티로 데이터를 전달하는 방법을 잘 알아두어야 합니다. 코드에서 이미 살펴본 것처럼 서비스를 시작하려면 startService를 호출하는 것만으로 충분합니다. 그리고 이렇게 실행된 서비스를 종료하고 싶다면 stopService 메서드를 호출하면 됩니다.

- Service 외에도 IntentService라는 클래스가 있습니다. 인텐트 서비스는 지금까지 살펴본 서비스와 달리 필요한 함수가 수행되고 나면 종료됩니다. 즉, 백그라운드에서 실행되는 것은 같지만 길게 지속되는서비스라기보다는 한 번 실행되고 끝나는 작업을 수행할 때 사용합니다. 인텐트 서비스에는 onHandleIntent라는 이름의 메서드가 있으며 이 함수는 onStartCommand 메서드로 전달된 인텐트를 전달 받으면서 실행됩니다. 그리고 이 함수의 실행이 끝나면 서비스는 자동 종료됩니다.
