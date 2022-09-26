# 키패드 제어하기

- EditText로 만든 입력상자에 포커스를 주면 화면 하단에 소프트 키패드가 생겨 입력할 수 있는 상태가 됩니다. 이때 열리는 소프트 키패드는 자동으로 열리고 닫히므로 별도의 코딩 없이 사용할 수 있지만 필요하다면 코드를 통해 직접 키패드를 열거나 닫을 수도 있습니다. 예를 들어, 로그인 화면에서 [로그인]버튼을 눌러 성공적으로 로그인되었을 때 열려 있던 소프트 키패드를 닫히게 만들어야 하지만 이 경우에도 코드에서 키패드를 닫아야 할 필요가 있습니다. 키패드와 관련된 기능은 InputMethodManager 객체로 사용할 수 있는데 이 객체는 시스템 서비스이므로 getSystemService 메서드로 참조한 후 다음과 같은 메서드를 사용해 키패드를 열거나 닫을 수 있습니다.


```java
boolean showSoftInput(View view, int flags)
boolean hideSoftInputFromWindow(IBinder windowToken, int flags [, ResultReceiver resultReceiver])
```

- 키패드를 열고 닫는 기능을 사용할 때 키 입력 관련 문제를 자주 접하게 되는데, 가장 일반적인 사례가 입력상자에 입력될 문자열의 종류를 지정하는 것입니다. 예를 들어, 입력상자에 숫자만 입력해야 할 때 키패드가 숫자용 키패드로 나타나게 입력상자의 inputType 속성을 설정합니다. 다음 표는 대표적인 inputType 속성을 정리한 것입니다. 각각의 속성을 바꿔가면서 설정해보면 사용법을 쉽게 이해할 수 있습니다.

|inputType 속성 값|설명|
|----|-----|
|number|숫자|
|numberSigned|0보다 큰 숫자|
|numberDecimal|정수|
|text|텍스트|
|textPassword|패스워드로 표시|
|textEmailAddress|이메일로 표시|
|phone|전화번호로 표시|
|date|날짜|

﻿- 소프트 키패드의 형태는 단말 제조사별로 다를 수 있습니다. 그러나 inputType 속성 값에 따라 보이는 키패드는 입력 정보의 종류를 지정하는 것이라서 자판 배열은 비슷합니다. 실제로 앱을 구성할 때 inputType 속성을 지정할 때가 많습니다. 이때 inputType 속성만 지정하면 간단하게 키패드의 유형을 바꿀 수 있으니 속성 값을 잘 기억해 두는 것이 좋습니다.

- 지금부터 입력상자를 추가해서 키패드가 어떻게 뜨는지, 그리고 버튼을 눌렀을 때 키패드가 사라지게 할 수 있는지 알아보겠습니다. 새로운 SampleKeypad 프로젝트를 만들고 패키지 이름은 org.koreait.keypad로 설정합니다. 프로젝트 창에서 activity_main.xml 파일을 열고 텍스트뷰를 삭제합니다. 팔레트에서 입력상자를 하나 끌어다 화면 가운데에 놓은 다음 입력상자 위쪽에 버튼도 하나 끌어다 놓습니다. 입력상자에 입력되어 있던 글자는 삭제하고 버튼의 글자는 '키패드 닫기'라고 수정합니다.


#### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/editText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:inputType="number"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="52dp"
        android:text="키패드 닫기"
        app:layout_constraintBottom_toTopOf="@+id/editText"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/8.%20%ED%82%A4%ED%8C%A8%EB%93%9C/images/image1.png)

- 이 상태로 앱을 실행하면 화면이 보이면서 동시에 키패드가 아래쪽에서 올라오는 것을 확인할 수 있습니다. 화면에 있는 입력상자가 포커스를 받으면 키패드는 자동으로 올라오기 때문에 굉장히 자연스러운 기능입니다. 하지만 때로는 화면이 떴을 때 키패드가 올라오지 않게 만드는 것이 필요할 때도 있습니다.


키패드가 올라오지 않도록 하려면 매니페스트에 속성을 추가해야 합니다. /app/manifests 폴더에 있는 AndroidManifest.xml 파일을 열고 \<activity\> 태그에 다음 속성을 추가합니다.

```
android:windowSoftInputMode="stateHidden" 
```

#### SampleKeypad>/app/manifests/AndroidManifest.xml
```xml
	
	... 생략
	
       <activity
            android:name=".MainActivity"
            android:windowSoftInputMode="stateHidden" 
            android:exported="true">
			
		... 생략
		
		</activity>
		
	... 생략
```

- 다시 앱을 실행하면 키패드가 나타나지 않습니다. 이제 입력상자의 inputType 속성 값을 number로 변경해서 버튼을 눌렀을 때 키패드가 닫히게 만들겠습니다. 먼저 activity_main.xml 파일을 열고 입력상자의 inputType 속성 값을 number로 변경합니다. 그리고 MainActivity.java 파일을 열고 onCreate 메서드 안에 버튼을 눌렀을 때 키패드가 닫히도록 만드는 코드를 입력합니다.

#### SampleKeypad>/app/java/org.koreait.keypad/MainActivity.java

```java
package org.koreait.keypad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
                if (getCurrentFocus() != null) {
                    // InputMethodManager 객체 잠조하기
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    // 키패드 감추기
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        });
    }
}
```

- 이제 앱을 다시 실행하고 입력상자를 클릭하면 숫자 키패드가 보입니다. 그리고 [키패드 닫기] 버튼을 누르면 키패드가 사라집니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/8.%20%ED%82%A4%ED%8C%A8%EB%93%9C/images/image2.png)

