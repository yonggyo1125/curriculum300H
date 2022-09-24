# 브로드캐스트 수신자 이해하기

- 안드로이드에서 브로드캐스팅(Broadcasting)이란 메시지를 여러 객체에 전달하는 것을 말합니다. 잘 이해되지 않으면 카카오톡에서 여러 사람에게 메시지를 전달할 때 그룹 채팅방을 만들어 메시지를 전달하는 것을 상상해 보세요. 마찬가지로 안드로이드도 여러 앱 구성 요소에 메시지를 전달할 때 브로드캐스팅을 사용합니다.

- 예를 들어, 다른 사람으로부터 문자를 받았을 때 이 문자를 SMS 수신 앱에 알려줘야 한다면 브로드캐스팅으로 전달하면 됩니다. 이런 메시지 전달 방식은 단말 전체에 적용될 수 있겠죠? 그래서 이런 메시지 전달 방식을 '<b>글로벌 이벤트(Global Event)</b>'라고 부릅니다. 글로벌 이벤트의 대표적인 예로는 '전화가 왔습니다.', '문자 메시지가 도착했습니다.'와 같은 사용자 알림을 들 수 있습니다.

- 여러분이 만든 앱에서 브로드캐스팅 메시지를 받고 싶다면 <b>브로드캐스트 수신자(Broadcast Receiver)</b>를 만들어 앱에 등록하면 됩니다. 다시 말해, 기기 안에서 동작하는 다른 앱 A로부터 특정 메시지를 받기 위해 여러분이 만든 앱에 브로드캐스트 수신자를 등록하면 A 앱의 메시지가 여러분이 만든 앱으로 전달됩니다. 이때 서비스와 마찬가지로 브로드캐스트 수신자도 앱 구성 요소입니다. 따라서 새로운 브로드캐스트 수신자를 만들면 새로 등록해야 시스템이 알 수 있습니다. 단, 브로드캐스트 수신자는 매니페스트 등록 방식이 아닌 소스 코드에서 <b>registerReceiver 메서드</b>를 사용해 시스템에 등록할 수 있습니다. 소스 코드를 이용하여 브로드캐스트 수신자를 등록하면 액티비티 안에서 브로드캐스트 메시지를 전달받아 바로 다른 작업을 수행하도록 만들 수 있는 장점이 있습니다.

## 브로드캐스트 수신자 등록하고 사용하기

- 브로드캐스트 수신자에는 <b>onReceive 메서드</b>를 정의해야 합니다. 이 메서드는 원하는 브로드캐스트 메시지가 도착하면 자동으로 호출됩니다. 하지만 시스템의 모든 메시지를 받을 수는 없습니다. 만약 원하는 메시지만 받으려면 어떻게 해야 할까요? 모든 메시지는 인텐트 안에 넣어 전달되므로 <b>원하는 메시지는 인텐트 필터를 사용해 시스템에 등록</b>하면 됩니다. 구체적인 내용은 실습을 통해 알아보겠습니다.

- 브로드캐스트 수신자를 만들어 SMS 문자를 받아볼 수 있게 새로운 프로젝트를 만들겠습니다. 프로젝트 이름은 SampleReceiver로 하고 패키지 이름은 org.koreait.receiver로 하여 새로운 프로젝트를 만듭니다. 새로운 프로젝트 창이 열리면 왼쪽 프로젝트 영역에서 app 폴더를 선택한 후 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [New → Other → Broadcast Receiver] 메뉴를 선택합니다. 그﻿러면 새로운 브로드캐스트 수신자를 만들 수 있는 대화상자가 표시됩니다. Class Name:에 SmsReceiver를 입력한 후 [Finish] 버튼을 누릅니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image1.png)

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image2.png)

- 새로운 브로드캐스트 수신자가 만들어지면 java 폴더 아래의 org.koreait.receiver 폴더에 SmsReceiver.java 파일이 생성됩니다. 그리고 AndroidManifest.xml 파일 안에는 \<receiver\> 태그가 추가니다. 이미 알고 있는 것처럼 AndroidManifest.xml 파일 안에는 앱 구성 요소를 등록해야 하며 새로 만들어진 브로드캐스트 수신자도 앱 구성 요소이므로 이 파일에 자동으로 등록됩니다. 그러면 먼저 AndroidManifest.xml 파일을 열고 다음 코드처럼 수정합니다.


#### SampleReceiver>/app/manifests/AndroidManifest.xml

```xml

... 생략 

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.SampleReceiver"
        tools:targetApi="31">
		
		
        <!-- <receiver> 태그 추가하고 name 속성에 수신자 이름 지정하기 -->
        <receiver
            android:name=".SmsReceiver"
            android:enabled="true"
            android:exported="true">

            <!-- <receiver> 태그 안에 <intent-filter>태그 추가하고 액션 정보 넣기 -->
            <intent-filter>
                <action android:name="android.provider.Telephony.SMS_RECEIVED" />
            </intent-filter>
        </receiver>

        ... 생략 
		
    </application>

... 생략

```
 
﻿- 브로드캐스트 수신자는 \<receiver\> 태그 안에 \<intent-filter\> 태그를 넣어 어떤 인텐트를 받을 것인지 지정합니다. 여기에서는 \<intent-filter> 태그 안에 <action> 태그를 추가하고 \<action\> 태그의 android:name 속성 값으로 android.provider.Telephony.SMS_RECEIVED를 넣었습니다. 이것은 SMS메시지가 들어간 인텐트를 구분하기 위한 액션 정보입니다. 즉, 단말에서 SMS를 수신했을 때 이 action정보가 들어간 인텐트를 전달하므로 이 값을 넣어주면 SMS를 받아볼 수 있습니다.

- 이제 SmsReceiver.java 파일을 살펴보면 BroadcastReceiver 클래스를 상속한 SmsReceiver 클래스가 정의되어 있고 그 안에 onReceive 메서드가 들어 있습니다. 이 onReceive 메서드 안에 다음 코드를 입력합니다.

#### SampleReceiver>/app/java/org.koreait.receiver/SmsReceiver.java

```java
package org.koreait.samplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive() 메서드 호출됨");

        // 인덴트에서 Bundle 객체 가져오기
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle); // parseSmsMessage() 메서드 호출하기

        if (messages != null && messages.length > 0) {
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG, "SMS sender : " + sender);

            String contents = messages[0].getMessageBody();
            Log.i(TAG, "SMS contents : " + contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.i(TAG, "SMS received date : " + receivedDate.toString());
        }
    }
}
```

- SMS를 받으면 onReceive 메서드가 자동으로 호출됩니다. 그리고 파라미터로 전달되는 Intent 객체안에 SMS 데이터가 들어 있습니다. 먼저 onReceive 메서드가 호출되었는지 알 수 있도록 onReceive메서드 안에 로그 메시지를 출력하는 한 줄을 추가합니다. 그리고 인텐트 객체 안에 들어 있는 Bundle객체를 getExtras 메서드로 참조합니다. 이 Bundle 객체 안에는 부가 데이터가 들어 있으며, parseSmsMessage 메서드를 호출하여 SMS 메시지 객체를 만들도록 합니다. parseSmsMessage 메서드는 직접 정의한 메서드로 SmsMessage라는 자료형으로 된 배열 객체를 반환하도록 되어 있습니다. 이 ﻿SmsMessage 객체에는 SMS 데이터를 확인할 수 있는 메서드들이 정의되어 있습니다. onReceive 메서드 아래쪽에 다음 메서드를 추가합니다.

```java
... 생략 

	private SmsMessage[] parseSmsMessage(Bundle bundle) {

        // Bundle 객체에 들어가 있는 부가 데이터 중에서 pdus 가져오기
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for (int i = 0; i < smsCount; i++) {
            // 단말 OS 버전에 따라 다른 방식으로 메서드 호출하기
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i[ = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
	
... 생략 

```

- parseSmsMessage 메서드는 한 번 입력해 놓으면 다른 앱을 만들 때도 재사용할 수 있습니다. 왜냐하면 SMS 데이터를 확인할 수 있도록 안드로이드 API에 정해둔 코드를 사용하므로 수정될 일이 거의 없기 때문입니다. 인텐트 객체 안에 부가 데이터로 들어 있는 SMS 데이터를 확인하려면 <b>SmsMessage클래스</b>의 <b>createFromPdu</b> 메서드를 사용하여 SmsMessage 객체로 변환하면 SMS 데이터를 확인할 ﻿수 있습니다. 이때 <b>Build.VERSION.SDK_INT</b>는 단말의 OS 버전을 확인할 때 사용합니다. 안드로이드 OS는 계속 업데이트되면서 새로운 기능이 추가되어왔으므로 단말의 OS 버전에 따라 코드가 약간씩 달라져야 할 때가 있습니다. 다음과 같은 코드가 버전에 따라 다른 코드를 넣을 때 사용하는 전형적인 코드 중 일부입니다.

```java
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) ... 
```

- Build.VERSION_CODES에는 안드로이드 OS 버전별로 상수가 정의되어 있습니다. 앞서 살펴본 코드는 OS가 마시멜로(첫 글자 M) 버전과 같거나 그 이후 버전일 때 중괄호 안의 코드를 실행하겠다는 뜻입니다.

- 이제 다시 onReceive 메서드로 돌아오겠습니다. onReceive 메서드의 코드를 살펴보면 SmsMessage객체에서 SMS 데이터를 확인하기 위한 메서드가 들어 있습니다. <b>발신자 번호를 확인하려면 getOriginatingAddress 메서드를 호출</b>합니다. 그리고 문자 내용을 확인하려면 <b>getMessageBody().toString()</b>코드를 사용합니다. SMS를 받은 시각도 확인할 수 있습니다. 일단 이렇게 확인한 데이터를 로그로 출력하도록 합니다.

- 그런데 이 앱에서 SMS를 수신하려면 <b>RECEIVE_SMS라는 권한</b>이 있어야 합니다. <b>AndroidManifest.xml</b> 파일을 열어 다음과 같이 권한을 추가하세요.

#### SampleReceiver>/app/manifests/AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.samplereceiver">

    <uses-permission android:name="android.permission.RECEIVE_SMS" />
	
	... 생략
	
</manifest>
```

- \<application\> 태그 위쪽에 \<uses-permission\> 태그를 추가하고 RECEIVE_SMS 권한을 추가했습니다. 그런데 이 권한은 위험 권한입니다. 위험 권한에 대해서는 다음 단락에서 자세하게 설명할 것입니다. 일단 여기에서는 위험 권한의 경우에는 소스 파일에서 앱 실행 후에 사용자가 권한을 부여할 수 있도록 별도의 코드가 추가되어야 한다는 정도만 알아둡니다. 그리고 외부 라이브러리를 하나 추가하고그 라이브러리를 사용해서 간단하게 위험 권한을 추가하는 코드를 넣어줍니다. 먼저 build.gradle(Module: SampleReceiver.app) 파일을 열고 다음과 같이 추가합니다.

#### SampleReceiver>Gradle Scripts>build.gradle(Module: SampleReceiver.app)

```
... 생략 

dependencies {

	.. 생략 
	
    implementation 'com.yanzhenjie:permission:2.0.2'
}
```

- build.gradle 파일을 수정하면 상단에 Sync Now라는 파란색 링크가 나타납니다. 이 링크를 누르거나 또는 상단에 있는 아이콘 중 오른쪽에서 여섯 번째 쯤에 있는 [Sync Project with Gradle Files] 아이콘을 클릭합니다.

- 외부 라이브러리를 사용할 수 있는 준비가 되었으니 MainActivity.java 파일을 열고 다음 코드를 추가합니다.

#### SampleReceiver>/app/java/org.koreait.receiver/MainActivity.java 

```java
package org.koreait.samplereceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 모든 위험 권한을 자동 부여하도록 하는 메서드 호출하기
        AndPermission.with(this)
                .runtime()
                .permission(Permission.RECEIVE_SMS)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showToast("허용된 권한 개수: " + permissions.size());
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showToast("거부된 권한 개수: " + permissions.size());
                    }
                })
                .start();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
```

- 이 코드는 위험 권한을 자동으로 부여하는 코드입니다. 이 코드는 다음 단락에서 자세히 설명하므로일단 여기에서는 onCreate 메서드 안에서 자동으로 권한을 부여하도록 요청한다고 이해하면 됩니다.

- 이제 앱을 실행했을 때 메인 액티비티가 화면에 보이 권한을 요청하는 대화상자가 표시됩니다. [ALLOW] 버튼을 누르면 권한이 승인되고 SMS를 받을 준비가 됩니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image3.png)

- 그런데 SMS는 이동통신사에 연결되어 있어야 다른 단말로부터 수신할 수 있습니다. 따라서 에뮬레이터에서는 실제 SMS를 받을 수 없습니다. 이 때문에 에뮬레이터에는 가상으로 SMS를 전송할 수 있는 기능이 들어 있습니다. 앱을 에뮬레이터로 실행한 후 에뮬레이터의 옆에 보이는 아이콘 중에서 가장 아래﻿쪽에 있는 [...] 아이콘을 클릭합니다. 그러면 [Extended controls] 대화상자가 표시됩니다. 왼쪽에 보이는 메뉴 중에서 [Phone] 메뉴를 선택합니다. SMS message 입력란에 'Hello!'라고 입력하고 [SEND MESSAGE] 버튼을 누릅니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image4.png)

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image5.png)

- 에뮬레이터로 SMS 문자가 전달되면 상단에 알림 메시지가 표시됩니다. 그리고 여러분이 만든 앱도 SMS를 받은 후 로그를 출력합니다. 로그는 [Logcat] 탭에서 볼 수 있습니다. 만약 메시지가 잘 보이지않으면 [Logcat] 검색창에서 SmsReceiver라는 단어를 검색해 보세요. 로그를 보면 SmsReceiver 클래스의 onReceive 메서드가 호출되었다는 것을 알 수 있습니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image6.png)

## SMS 내용 액티비티에 나타내기

- 그런데 발신자와 SMS 내용, 수신 시각 등을 Logcat 창이 아니라 사용자가 보는 화면에 나타내고 싶다면 어떻게 할까요? 브로드캐스트 수신자는 화면이 없으므로 보여주려는 화면은 액티비티로 만든 후 그 화면을 띄워야 합니다. 따라서 브로드캐스트 수신자에서 인텐트 객체를 만들고 startActivity 메서드를 사용해 액티비티 쪽으로 인텐트 객체를 전달해야 합니다.

- 수신한 SMS 문자 내용을 화면에 보여주려면 먼저 액티비티를 만듭니다. 왼쪽 프로젝트 창의 app 폴더를 선택한 상태에서 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [New → Activity → Empty ﻿Activity] 메뉴를 선택합니다. 새로운 액티비티를 만드는 대화상자가 보이면 Activity Name란에 SmsActivity를 입력합니다. [Finish] 버튼을 누르면 새로운 액티비티에 필요한 XML 레이아웃 파일 하나와소스 파일 하나가 생성됩니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image7.png)

- 그리고 AndroidManifest.xml 파일에는 \<activity\> 태그가 추가됩니다. 이 SmsActivity 화면에 수신한 SMS 내용을 보여줄 것이므로 먼저 activity_sms.xml 파일을 열고 화면 레이아웃을 구성합니다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image8.png)

- 디자인 화면에서 입력상자 세 개, 버튼 하나를 끌어다 놓습니다. 두 번째 입력상자는 크기를 늘려서 크게 만들고 버튼은 세 번째 입력상자의 아래쪽 가운데에 배치합니다. 입력상자의 hint 속성은 설명글을 보여주므로 첫 번째 입력상자에는 '발신번호', 두 번째는 '내용', 세 번째는 '수신 시각'을 hint 값으로 설정합니다. 버튼의 텍스트는 '확인'으로 설정하세요. 그리고 두 번째 입력상자에 보이는 글자가 좌측 위쪽에 보이도록 gravity 속성 값으로 left와 top을 설정합니다. 수신한 SMS 내용을 보여줄 화면의 레이아웃을 만들었으니 이제 소스 파일인 SmsActivity.java 파일을 열고 다음 코드를 입력합니다.

#### ﻿SampleReceiver>/app/java/org.koreait.receiver/SmsActivity.java

```java
package org.koreait.samplereceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 전달받은 인텐트 처리하도록 processIntent 메서드 호출하기
        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {

        //  인텐트가 널이 아니면 그 안에 들어있는 부가 데이터를 화면에 보여주기
        if (intent != null) {
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            String receivedDate = intent.getStringExtra("receivedDate");

            editText.setText(sender);
            editText2.setText(contents);
            editText3.setText(receivedDate);
        }
    }
}
```

- ﻿브로드캐스트 수신자로부터 인텐트를 전달받을 것이므로 onCreate 메서드 안에서 getIntent 메서드를 호출하여 processIntent 메서드를 호출하도록 합니다. 그리고 onNewIntent 메서드를 재정의하여이 액티비티가 이미 만들어져 있는 상태에서 전달받은 인텐트도 처리하도록 합니다. processIntent 메서드 안에서는 인텐트 객체 안에 들어 있는 부가 데이터를 꺼내서 입력상자에 설정합니다. 화면에 있는[확인] 버튼을 눌렀을 때는 finish 메서드를 호출하여 이 화면을 닫아줍니다.

- 수신한 SMS를 보여줄 화면까지 만들었으므로 SmsReceiver.java 파일을 열고 SmsActivity로 인텐트를 전달하는 코드를 추가합니다.


#### SampleReceiver>/app/java/org.koreait.receiver/SmsReceiver.java

```java

... 생략

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";

    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {
		
		... 생략

        if (messages != null && messages.length > 0) {
			
			... 생략

            // 새로운 화면을 띄우기 위한 메서드 호출하기
            sendToActivity(context, sender, contents, receivedDate);
        }
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate) {
        Intent myIntent = new Intent(context, SmsActivity.class);
        //  인텐트에 플래그 추가하기
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        myIntent.putExtra("sender", sender);
        myIntent.putExtra("contents", contents);
        myIntent.putExtra("receivedDate", format.format(receivedDate));

        context.startActivity(myIntent);
    }

    ... 생략 
	
}
```

- sendToActivity 메서드는 SmsActivity로 인텐트를 보내려고 만든 것입니다. Intent 객체를 만들 때 두번째 파라미터로 SmsActivity 객체(SmsActivity.class)를 전달했으므로 startActivity 메서드를 사용해이 인텐트를 시스템으로 전달하면 시스템이 그 인텐트를 SmsActivity 쪽으로 전달합니다. 브로드캐스트 수신자는 화면이 없으므로 인텐트의 플래그로 <b>FLAG_ACTIVITY_NEW_TASK</b>를 추가해야 한다는점을 잊지 말아야 합니다. 그리고 이미 메모리에 만든 SmsActivity가 있을 때 액티비티를 중복 생성하지 않도록 <b>FLAG_ACTIVITY_SINGLE_TOP</b> 플래그도 추가합니다. 이렇게 정의한 sendToActivity 메서드는 onReceive 안에서 호출합니다. 수신 시각의 경우 사용자가 알아보기 좋은 날짜 형태로 만들기위해 SimpleDateFormat 클래스를 사용할 수 있습니다. 이 클래스는 java.text 패키지 안에 있는 것을 import 하여 사용하며 날짜와 시간을 원하는 형태의 문자열로 만들 때 사용합니다.

- 이제 앱을 다시 실행한 후 가상으로 SMS 문자를 보내면 다음과 같은 화면이 뜨면서 수신한 SMS 내용이 표시됩니다.

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image9.png)

﻿- 레이아웃의 모양은 여러분이 원하는 형태대로 바꿀 수 있으니 예쁜 모양으로 다시 만들어보기 바랍니다.

- 여러분이 만든 브로드캐스트 수신자는 매니페스트 파일 안에 \<receiver\> 태그로 추가되어 있지만 매니페스트에 등록하지 않고 소스 파일에서 registerReceiver 메서드를 사용해 등록할 수도 있습니다. 이렇게 소스 파일에서 등록하면 화면이 사용자에게 보일 때만 브로드캐스트 수신자에서 메시지를 받도록 만들 수 있습니다. 따라서 필요에 따라 매니페스트 파일에 등록하거나 또는 소스 파일에서 등록하여 사용합니다.


> 만약 다른 앱에 메시지를 보내고 싶다면 여러분이 만든 앱에서 sendBroadcast 메서드를 사용할 수 있습니다. 물론 다른 앱에 브로드캐스트 수신자를 정의해야 하고 sendBroadcast 메서드로 메시지를 보낼 때는 인텐트 안에 넣어 보내야합니다.

> SMS 수신 시 문자는 단말의 기본 메시지 앱이 먼저 받아 처리한 후 다른 앱으로 넘겨주게 됩니다. 따라서 여러분이 만든앱을 사용자가 단말의 기본 메시지 앱으로 지정하면 단말의 기본 SMS 앱으로 여러분이 직접 만든 SMS 수신 앱을 띄울수 있습니다. 또한 다른 앱들이 문자를 처리하지 못하도록 할 수도 있습니다. 그러나 사용자가 직접 설정에 들어가 지정해주어야 한다는 번거로움이 있습니다.

## 브로드캐스트 수신자 동작 방식 정리하기

- 다음은 여러분이 만든 브로드캐스트 수신자가 동작하는 방식을 보여줍니다.

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image10.png)

- ﻿단말에서는 다른 사람으로부터 SMS 문자를 받았을 때 텔레포니(Telephony) 모듈이 처리하도록 합니다. 이렇게 처리된 정보는 인텐트에 담겨 브로드캐스팅 방식으로 다른 앱에 전달됩니다. 여러분이 직접 만든 앱도 그중의 하나가 되어 인텐트를 전달받으며 인텐트를 받았을 때 onReceive 메서드가 자동 호출됩니다. 여러분이 만든 브로드캐스트 수신자는 매니페스트 파일에 등록되었기 때문에 시스템이 이미 알고 있습니다. 따라서 시스템이 여러분이 만든 앱으로 인텐트를 전달할 수 있습니다. SmsReceiver 객체에서는 인텐트 안에 들어 있는 데이터를 확인한 후 SmsActivity로 인텐트를 전달합니다.

- 브로드캐스트 수신자를 사용하면서 주의할 점은 앱 A가 실행되어 있지 않아도 앱 A가 원하는 브로드캐스트 메시지가 도착하면 다른 앱 B를 실행하고 있는 도중에도 앱 A가 실행될 수 있다는 점입니다. 이 때문에 동일한 SMS 수신 앱을 여러 개 수정하여 만들어 설치하면 오류가 발생했을 때 어느 앱에서 생긴 오류인지 찾아내기 힘든 경우가 많습니다. 이 때문에 구 개발 버전의 앱을 한 번 설치한 후 앱의 패키지 이름을 수정하는 등의 방법으로 새 개발 버전의 앱을 만들었을 경우에는 구 개발 버전의 앱을 삭제하는 것이 좋습니다.

- 앱을 삭제하기 위해서는 에뮬레이터의 하단 가운데 부분에 있는 버튼을 누른 후 설정 메뉴를 눌러 설정화면을 띄우고 그 안에 있는 앱 항목을 찾아 실행합니다. 원하는 앱을 찾아 클릭하면 삭제할 수 있는 상세 화면을 볼 수 있습니다. 

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/5.%20%EB%B8%8C%EB%A1%9C%EB%93%9C%EC%BA%90%EC%8A%A4%ED%8A%B8%20%EC%88%98%EC%8B%A0%EC%9E%90/images/image11.png)

- 지금까지 브로드캐스트 수신자 사용 방법을 알아보았습니다. 앱을 실행하지 않은 상태에서도 인텐트 안에 들어 있는 메시지를 받아볼 수 있다는 점은 브로드캐스트 수신자가 갖고 있는 가장 중요한 특징입니다. 왜냐하면 어떤 특정한 상황에서 필요한 작업을 할 수 있도록 앱을 구성할 수 있기 때문입니다.

- 브로드캐스트 수신자를 포함하고 있는 앱의 메인 액티비티가 적어도 한 번 실행되어야 브로드캐스트 수신자가 메시지를 받을 수 있습니다.