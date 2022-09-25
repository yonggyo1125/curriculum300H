# 위험 권한 부여하기

- 앞선 예제에서 브로드캐스트 수신자를 만들어 SMS 문자를 받으려면 RECEIVE_SMS 권한이 필요하다는것을 알게 되었습니다. 매니페스트에 넣어준 권한은 앱을 설치할 때 사용자가 허용하면 한꺼번에 권한이 부여되는데 마시멜로(API 23)부터는 중요한 권한들을 분류하여 설치 시점이 아니라 앱을 실행했을 때 사용자로부터 권한을 부여받도록 변경되었습니다. 그러면 중요한 권한들은 어떻게 해야 하는지 살펴보겠습니다.


## 일반 권한과 위험 권한의 차이점 알아보기

- 마시멜로 버전부터는 권한을 일반 권한(Normal Permission)과 위험 권한(Dangerous Permission)으로 나누었습니다. 앱을 설치하는 시점에 사용자에게 물어보는 기존의 방식은 사용자가 아무런 생각 없이 앱을 설치하는 경우가 많았으며 이에 따라 설치된 앱들이 단말의 주요 기능을 마음대로 사용할 수있었습니다. 이 때문에 위험 권한으로 분류된 권한들에 대해서는 앱을 설치할 때가 아니라 앱을 실행할때 권한을 부여하도록 변경된 것입니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/6.%20%EC%9C%84%ED%97%98%EA%B6%8C%ED%95%9C%20%EB%B7%B0%EC%97%AC%ED%95%98%EA%B8%B0/images/image1.png)

- 예를 들어, 인터넷을 사용할 때 부여하는 INTERNET 권한은 일반 권한입니다. 따라서 앱을 설치할 때사용자에게 권한이 부여되어야 함을 알려주고 설치할 것인지를 물어봅니다. 사용자가 부여할 권한들의 설명을 보고 수락하면 앱이 설치되고 앱에는 INTERNET 권한이 부여됩니다. 그러나 위험 권한으로 분류되는 RECEIVE_SMS의 경우에는 설치 시에 부여한 권한은 의미가 없으며 실행 시에 사용자에게 ﻿권한을 부여할 것인지 물어보게 됩니다. 만약 사용자가 권한을 부여하지 않으면 해당 기능은 동작하지 않습니다. 즉 앱을 설치했다고 하더라도 권한에 따라 실행할 수 있는 기능에 제약이 생기는 것입니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/6.%20%EC%9C%84%ED%97%98%EA%B6%8C%ED%95%9C%20%EB%B7%B0%EC%97%AC%ED%95%98%EA%B8%B0/images/image2.png)

- 위험 권한으로 분류된 주요 권한들을 보면 대부분 개인정보가 담겨있는 정보에 접근하거나 개인정보를 만들어낼 수 있는 단말의 주요 장치에 접근할 때 부여된다는 것을 알 수 있습니다. 위치, 카메라, 마이크, 연락처, 전화, 문자, 일정, 센서로 대표되는 위험 권한은 다음과 같은 세부 권한으로 나뉩니다.

|위험 권한 그룹의 분류(Permission Group)|세부 권한(Permission)|
|----|----|
|Location (위치)|ACCESS_FINE_LOCATION<br>ACCESS_COARSE_LOCATION|
|CAMERA|CAMERA|
|MICROPHONE|RECORD_AUDIO|
|CONTACTS|READ_CONTACTS<br>WRITE_CONTACTS<br>GET_ACCOUNTS|
|PHONE|READ_PHONE_STATE<br>CALL_PHONE<br>READ_CALL_LOG<br>WRITE_CALL_LOG<br>ADD_VOICEMAIL<br>USE_SIP<br>PROCCESS_OUTGOING_CALLS|
|SMS|SEND_SMS<br>RECEIVE_SMS<br>READ_SMS<br>RECEIVE_WAP_PUSH<br>RECEIVE_MMS|
|CALENDAR|READ_CALENDAR<br>WRITE_CALENDAR|
|SENSORS|BODY_SENSORS|
|STORAGE|READ_EXTERNAL_STORAGE<br>WRITE_EXTERNAL_STORAGE|

- ﻿권한 그룹(Permission Group)은 동일한 기능을 접근하는데 몇 가지 세부 권한을 하나로 묶어주는 역할을 합니다. 앞서 얘기했던 주요 위험 권한 외에 SD 카드에 접근할 때 사용하는 READ_EXTERNAL_STORAGE와 WRITE_EXTERNAL_STORAGE 권한도 위험 권한으로 분류되었다는 점에 주목할 필요가 있습니다. 왜냐하면 기존에 사용하던 많은 앱들이 SD 카드를 접근하고 있기 때문입니다. 여러분도 앞으로 SD 카드를 접근할 경우가 많을 것이므로 위협 권한을 부여하는 방법에 대해서는 잘 알아두는 것이 좋습니다.

- 이제 위험 권한을 사용하는 앱은 앱이 실행될 때 권한을 부여해 달라는 대화상자를 사용자에게 띄운다는 것을 알았습니다. 그리고 위험 권한을 부여하려면 코드를 많이 입력해야 해서 외부 라이브러리를 사용하는 경우도 많습니다.

## 위험 권한 부여하는 방법 알아보기

- 이번에는 기본적인 방법으로 위험 권한을 부여하는 실습을 통해 위험 권한을 부여하는 동작 원리를 공부합니다. 그런 다음 외부 라이브러리를 사용하여 위험 권한을 부여하는 방법을 다시 살펴보겠습니다. SamplePermission이라는 새로운 프로젝트를 만들고 패키지 이름은 org.koreait.permission으로 수정합니다. SD 카드를 접근할 때 사용되는 두 가지 위험 권한을 부여하기 위해 먼저 AndroidManifest.xml 파일을 열고 다음과 같이 권한을 추가합니다.

#### SamplePermission>/app/manifests/AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.permission">

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android:permission.WRITE_EXTERNAL_STORAGE" />
	
	... 생략
	
</manifest>
```

- 기본 권한을 부여할 때는 \<uses-permission\> 태그를 사용합니다. 이렇게 부여한 기본 권한 중에서 SD카드를 접근하는 권한은 위험 권한이므로 코드를 추가로 입력해야 합니다. MainActivity.java 파일을 열고 다음 코드를 입력합니다.

#### SamplePermission>/app/java/org.koreait.permission/MainActivity.java

```java 
package org.koreait.permission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위험 권한을 부여할 권한 지정하기
        String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        checkPermissions(permissions);
    }

    public void checkPermissions(String[] permissions) {
        ArrayList<String> targetList = new ArrayList<String>();

        for(int i = 0; i < permissions.length; i++) {
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, curPermission + " 권한 있음.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, curPermission + " 권한 없음.", Toast.LENGTH_LONG).show();
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)) {
                    Toast.makeText(this, curPermission + " 권한 설명 필요함.", Toast.LENGTH_LONG).show();
                } else {
                    targetList.add(curPermission);
                }
            }
        }

        String[] targets = new String[targetList.size()];
        targetList.toArray(targets);

        // 위험 권한 부여 요청하기
        ActivityCompat.requestPermissions(this, targets, 101);
    }
}
```

- SD카드에 접근하기 위해서는 READ_EXTERNAL_STORAGE와 WRITE_EXTERNAL_STORAGE 두개의 권한이 필요합니다. 물론 SD 카드 읽기만 하고 싶다면 두 번째 권한은 사용하지 않아도 되지만 일반적으로는 쓰기 권한까지 사용하는 경우가 많습니다. 그런데 이 권한은 위험 권한으로 분류되었으므로 앱이 실행될 때 사용자에게 권한을 부여해 달라는 대화상자를 띄워야 합니다.

> 위험 권한을 부여하는 것은 앱이 실행 중이라면 언제든 가능합니다. 예를 들어, 액티비티가 메모리에 만들어지는 시점에 부여되도록 하려면 onCreate 메서드 안에서 권한 부여 요청을 하지만 버튼이 눌렸을 때 권한이 부여되게 할 수도 있습니다.

- onCreate 메서드 안에서 checkPermissions 메서드를 호출하도록 코드를 작성했습니다. checkPermissions 메서드는 지정한 권한들에 대해서 그 권한이 부여되어 있는지를 먼저 확인합니다. 그리고 권한이 부여되지 않았다면 ArrayList 안에 넣었다가 부여되지 않은 권한들만 권한 요청을 하게 됩니다. checkSelfPermission 메서드로 이미 권한이 부여되어 있는지 확인하도록 만들었습니다. 만약 권한이 부여되지 않았다면 requestPermissions 메서드를 호출하여 권한 부여 요청 대화상자를 띄워줍니다. 이 대화상자는 여러분이 직접 만드는 것이 아니라 requestPermissions 메서드를 호출했을 때 시스템에서 띄워주기 때문에 사용자가 수락했는지 아니면 거부했는지의 여부를 콜백 메서드로 받아 확인하는것이 필요합니다. 이렇게 권한을 요청하면 콜백 메서드로 그 결과를 받을 수 있습니다.

- MainActivity.java 파일의 아래쪽에 다음과 같이 <b>onRequestPermissionsResult</b> 메서드를 재정의하는코드를 추가합니다.

#### SamplePermission>/java/org.koreait.permission/MainActivity.java

```java

... 생략 

	@Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // 요청 코드가 맞는지 확인함
        switch (requestCode) {
            case 101:
                // 사용자가 권한을 수락했는지 여부를 확인함
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "첫 번째 권한을 사용자가 승인함.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "첫 번째 권한 거부됨.", Toast.LENGTH_LONG).show();
                }

                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
	
... 생략 

```

﻿- onRequestPermissionsResult 메서드에는 요청 코드와 함께 사용자가 권한을 수락했는지 여부가 파라미터로 전달됩니다. 여러 권한을 한 번에 요청할 수도 있으니 grantResults 배열 변수 안에 수락 여부를 넣어 전달합니다. 앞에서 위험 권한에 대한 수락을 요청할 때 두 개의 권한을 요청했으므로 grantResults 배열의 길이는 2이며 사용자가 권한을 수락했다면 PackageManager.PERMISSION_GRANTED 상수가 결과 값으로 확인됩니다. 여기에서는 그 중에 첫 번째 권한에 대해 수락되었는지 여부를 확인한 후 토스트 메시지를 띄우도록 했습니다.

- 앱을 실행하면 다음과 같이 권한을 요청하는 대화상자가 뜨게 됩니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/6.%20%EC%9C%84%ED%97%98%EA%B6%8C%ED%95%9C%20%EB%B7%B0%EC%97%AC%ED%95%98%EA%B8%B0/images/image3.png)

- [ALLOW] 버튼을 클릭하면 권한이 부여됩니다. 이렇게 실행했을 때 부여되는 위험 권한이라고 하더라도 사용자가 한 번 수락하면 앱에 부여된 권한 정보를 단말에서 알고 있기 때문에 앱을 다시 실행해도 대화상자는 더 이상 나타나지 않습니다. 기본 방법으로 위험 권한을 설정하는 방법에 대해 알아보았습니다.

- 이번에는 외부 라이브러리를 이용한 위험 권한 자동 부여 방법에 대해 알아보겠습니다. SamplePermission2라는 이름의 프로젝트를 새로 만듭니다. 프로젝트를 만들 때 패키지 이름은 org.koreait.permission2로 입력합니다. 그리고 build.gradle(Module: SamplePermission2.app) 파일을 열고 다음과 같이 추가합니다. 이 과정은 앞서 익혔던 내용과 같습니다. 다시 한 번 진행하면서 확실히 익혀두길 바랍니다.


####  SamplePermission2>Gradle Scripts>build.gradle(Module: SamplePermission2.app)

```

... 생략

dependencies {
	
	... 생략 
	
    implementation 'com.yanzhenjie:permission:2.0.2'
}
```

﻿- build.gradle 파일을 변경했으면 오른쪽 상단에 Sync Now라는 노란색 링크가 나타납니다. Sync Now 링크를 누르거나 [Sync Project with Gradle Files.] 아이콘을 클릭합니다.

- 외부 라이브러리를 사용할 수 있는 준비가 되었으니 /app/manifests 폴더 안에 있는 AndroidManifest.xml파일을 열고 \<application\> 태그 위에  READ_EXTERNAL_STORAGE와 WRITE_EXTERNAL_STORAGE 권한을 추가합니다.

#### SamplePermission2>/app/manifests/Android Manifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.permission2">

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
	
	... 생략 
	
</manifest>
```

- 그다음 MainActivity.java 파일을 열고 다음 코드를 추가합니다.

#### SamplePermission2>/app/java/org.koreait.permission2/MainActivity.java

```java
package org.koreait.permission2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndPermission.with(this)
                .runtime()
                .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showToast("허용된 권한 개수 : " + permissions.size());
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

- 이 코드는 위험 권한을 자동으로 부여하는 코드입니다. AndroidManifest.xml 파일 안에 넣은 권한 중에서 위험 권한을 자동으로 체크한 후 권한 부여를 요청하는 방식이죠. onCreate 메서드 안에서는 자동으로 권한을 부여하도록 요청합니다. 그러면 권한 부여 요청 결과가 넘어오게 되는데 그 결과는 onGranted의 소괄호 안에 전달된 객체의 onAction 메서드로 전달 받습니다. 권한 부여 결과는 승인 또는 거부로 나뉘는데 이 때문에 onGranted 외에도 onDenied 메서드의 onAction 메서드가 호출될 수 있습니다. 예를 들어, 권한이 여러 개인 경우에는 어떤 권한은 onGranted 또는 어떤 권한은 onDenied로 나뉘어 호출됩니다.

- 이제 앱을 실행합니다. 앱이 실행되고 메인 액티비티가 화면에 보이면 권한을 요청하는 대화상자가 표시됩니다. [ALLOW] 버튼을 누르면 권한이 승인됩니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/6.%20%EC%9C%84%ED%97%98%EA%B6%8C%ED%95%9C%20%EB%B7%B0%EC%97%AC%ED%95%98%EA%B8%B0/images/image4.png)

- 위험 권한을 부여하는 두 가지 방식을 알아보았습니다. 매니페스트 파일에 들어 있는 권한 중에서 자동으로 위험 권한 요청 대화상자를 띄워주는 방식이 좀 더 간단하다는 것을 알게 되었습니다. 이 두 가지 방식 중에서 어떤 것을 사용해도 상관없지만 위험 권한을 부여하는 경우가 많기 때문에 어떻게 부여해야 하는지 잘 이해해고 있어야 합니다.