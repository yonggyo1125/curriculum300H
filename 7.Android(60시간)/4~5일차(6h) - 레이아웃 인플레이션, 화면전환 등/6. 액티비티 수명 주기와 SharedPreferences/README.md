# 액티비티의 수명주기와 SharedPreferences 이해하기

- 안드로이드 시스템은 실행되는 앱의 상태를 직접 관리합니다. 이는 대부분의 모바일 OS에서 사용하는 방법인데 독립적인 앱이 시스템에 의해 관리되지 않으면, 실행된 앱이 메모리를 과도하게 점유하거나 화면을 보여주는 권한을 과도하게 갖기 때문에 전화기의 원래 기능인 전화 수신 또는 발신 기능을 사용하지 못할 수도 있습니다. 특히, 안드로이드가 지원하는 멀티태스킹처럼 음악을 들으면서도 웹사이트를 검색하는 등 여러 개의 앱이 동시에 실행되는 기능은 앱이 실행되는 환경을 시스템이 계속 모니터링해야 할 필요가 있게 만듭니다.

- 앞에서 이전에 실행한 액티비티는 액티비티 스택에 보관하다가 현재 화면에 보이는 액티비티가 사라지면 다시 이전 액티비티가 동작하는 과정을 거친다고 했습니다. 이 때문에 하나의 액티비티가 화면에보이거나 보이지 않게 되었을 때 다른 액티비티의 상태에 영향을 미칠 수 있습니다. 예를 들어, 여러분이 만든 앱이 실행되는 도중에 전화가 오면 단말의 통화 앱이 화면에 나타나기 때문에 여러분의 앱 화면은 다른 화면 뒤로 들어가 중지될 수 있습니다.

- 이처럼 액티비티는 처음 실행될 때 메모리에 만들어지는 과정부터 시작해서 실행과 중지, 그리고 메모리에서 해제되는 여러 과정의 상태 정보로 갖고 있으며, 이런 상태 정보는 시스템이 관리하면서 각각의 상태에 해당하는 메서드를 자동으로 호출하게 됩니다. 예를 들어, 액티비티에 기본으로 만들어져 있는 onCreate() 메서드는 액티비티가 만들어질 때 시스템이 자동으로 호출하는 메서드입니다. 이러한 상태는 여러 가지가 있는데 대표적인 상태 정보는 다음과 같습니다.

- 액티비티의 대표적인 상태 정보

|상태|설명|
|-----|-------|
|실행(Running)|화면상에 액티비티가 보이면서 실행되어 있는 상태, 액티비티 스택의 최상위에 있으며 포커스를 가지고 있음|
|일시 정지(Paused)|사용자에게 보이지만 다른 액티비티가 위에 있어 포커스를 받지 못하는 상태<br>대화상자가 위에 있어 일부가 가려진 경우에 해당함|
|중지(Stopped)|다른 액티비티에 의해 완전히 가려져 보이지 않는 상태|

- 이렇게 액티비티의 상태 정보가 변화하는 것을 액티비티의 '<b>수명주기(Life Cycle)</b>' 또는 생명주기라 하며 액티비티가 처음 만들어진 후 없어질 때까지 상태가 변화하면서 각각에 해당하는 메서드가 자동으로 호출됩니다. 다음은 액티비티의 수명주기를 다이어그램으로 보여줍니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/6.%20%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0%20%EC%88%98%EB%AA%85%20%EC%A3%BC%EA%B8%B0%EC%99%80%20SharedPreferences/images/image1.png)

- 다이어그램만으로도 액티비티의 상태가 변할 때 어떤 메서드들이 호출되는지 쉽게 알 수 있습니다. 만약 새로운 액티비티가 만들어진다면 <b>onCreate</b>, <b>onStart</b>, <b>onResume</b> 메서드가 차례대로 호출되며 그런 다음 화면에 보이게 됩니다. 이렇게 실행된 액티비티는 다른 액티비티가 그 위에 새로 실행되어 보이면 <b>onPause</b> 메서드가 호출되면서 일시 정지나 중지 상태로 변하게 됩니다. <b>onStop</b> 메서드는 중지 상태로 변경될 때 자동으로 호출되는 메서드입니다. 일시 정지나 중지 상태로 갈 때 호출되는 <b>onPause</b> 메서드처럼 액티비티가 다시 실행될 때는 <b>onResume</b> 메서드가 호출되게 됩니다. 만약 액티비티가 메모리에서 없어질 경우에는 <b>onDestroy</b> 메서드가 호출됩니다. 다음은 이 각각의 상태 메서드들에 대해 자세하게 설명한 표입니다.

|상태 메서드|설명|
|----|-------|
|onCreate|액티비티가 처음에 만들어졌을 때 호출됨<br>화면에 보이는 뷰들의 일반적인 상태를 설정하는 부분<br>이전 상태가 저장되어 있는 경우에는 번들 객체를 참조하여 이전 상태 복원 가능<br>이 메서드 다음에는 항상 onStart 메서드가 호출됨|
|onStart|액티비티가 화면에 보이기 바로 전에 호출됨<br>액티비티가 화면 상에 보이면 이 메서드 다음에 onResume 메서드가 호출됨<br>액티비티가 화면에서 가려지게 되면 이 메서드 다음에 onStop메서드가 호출됨|
|onResume|액티비티가 사용자 상호작용하기 바로 전에 호출됨|
|onRestart|액티비티가 중지된 이후에 호출되는 메서드로 다시 시작되기 바로 전에 호출됨<br>이 메서드 다음에는 항상 onStart 메서드가 호출됨|
|onPause|또 다른 액티비티를 시작하려고 할 때 호출됨<br>저장되지 않은 데이터를 저장소에 저장하거나 애니메이션 작업을 중지하는 등의 기능을 수행하는 메서드임<br>이 메서드가 리턴하기 전에는 다음 액티비티가 시작될 수 없으므로 이 작업은 매우 빨리 수행된 후 리턴되어야 함<br>액티비티가 이 상태에 들어가면 시스템은 액티비티를 강제 종료할 수 있음|
|onStop|액티비티가 사용자에게 더 이상 보이지 않을 때 호출됨<br>액티비티가 소멸되거나 또 다른 액티비티가 화면을 가릴 떄 호출됨<br>액티비티가 이 상태에 들어가면 시스템은 액티비티를 강제 종료할 수 있음|
|onDestroy|액티비티가 소멸되어 없어지기 전에 호출됨<br>이 메서드는 액티비티가 받는 마지막 호출이 됨<br>액티비티가 앱에 의해 종료되거나(finish  메서드 호출) 시스템이 강제로 종료시키는 경우에 호출될 수 있음|


- 게임과 같은 실제 앱을 구성할 때는 중간에 전화가 오거나 갑자기 전화기가 종료된 이후에도 게임 진행 중이던 상태로 다시 돌아갈 수 있어야 합니다. 예를 들어, 사용자가 게임의 2단계를 진행하고 있는 상태였다면 그 정보를 저장해 두었다가 앱이 다시 실행되었을 때 그 상태부터 다시 시작할 수 있도록 만들어주어야 합니다. 이런 경우에 사용되는 액티비티의 수명주기 메서드는 onPause와 onResume입니다. 이 두 가지 메서드는 앱이 멈추거나 없어질 때, 그리고 앱이 다시 보이거나 새로 실행될 때 호출되므로 이 두 가지 메서드를 구현하여 앱의 상태를 저장하거나 복원해야 합니다.

- 이러한 방법 이외에도 액티비티를 중지시키기 전에 호출되는 <b>onSaveInstanceState</b> 메서드를 이용해 데이터를 임시로 저장할 수도 있습니다. <b>onSavelnstanceState</b> 메서드의 파라미터로 전달되는 번들 객체를 이용해 데이터를 저장하면 <b>onCreate</b> 메서드나 <b>onRestoreInstanceState</b> 메서드로 저장했던 데이터가 전달됩니다. 이 방식을 사용하면 앱이 강제 종료되거나 비정상 종료된 이후에 앱이 재실행되었을 때도 그 상태 그대로 보일 수 있도록 만들어줍니다.

- 액티비티 수명주기를 확인하기 위해 액티비티에 들어있는 몇 가지 메서드에 토스트 메시지를 넣어 보겠습니다. 새로운 SampleLifeCycle 프로젝트를 만듭니다. 그리고 activity_main.xml 파일을 열어서 중앙에 있는 텍스트뷰는 삭제한 후 버튼 하나와 텍스트뷰 하나를 추가합니다. 버튼에는 '메뉴 화면 띄우기'라는 글자가 보이도록 합니다. 이전에 만들었던 프로젝트에서처럼 메뉴 액티비티를 새로 추가하고 메인 액티비티의 버튼을 눌렀을 때 메뉴 액티비티를 띄우도록 소스 코드를 직접 수정합니다. 메뉴 액티비티에는 '돌아가기'라는 글자가 보이는 버튼을 하나 추가하고 이 버튼을 눌렀을 때 이전화면으로 돌아가게 합니다. 


#### SampleLifeCycle>/app/res/layout/activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="메인 화면"
        android:textSize="30sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="48dp"
        android:text="메뉴화면 띄우기"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView" />

    <EditText
        android:id="@+id/nameInput"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="60dp"
        android:ems="10"
        android:inputType="textPersonName"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

#### SampleLifeCycle>/app/res/layout/activity_menu.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MenuActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="돌아가기"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

#### SampleLifeCycle>/app/src/org.koreait.samplelifecycle/MainActivity.java

```java
package org.koreait.samplelifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
```

#### SampleLifeCycle>/app/src/org.koreait.samplelifecycle/MenuActivity.java

```java
package org.koreait.samplelifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
```

- 버튼을 눌렀을 때 메뉴 액티비티를 띄우면 메인 액티비티는 액티비티 스택에 들어가므로 수명주기의 상태 변화가 일어납니다. 상태 변화를 확인하기 위해 MainActivity.java 파일을 열·고 MainActivity 클래스 안에 몇 개의 메서드를 재정의합니다. MainActivity 클래스에 마우스 커서를 둔 후 마우스 오른쪽 버튼을 눌러 팝업 메뉴가 보이도록 합니다. [Generate Override Methods...] 메뉴를 누르면 재정의하고 싶은 부모 클래스의 메서드를 선택할 수 있습니다. 

- [Ctrl] 을 누른 상태에서 메서드를 선택하면 여러 개를 한꺼번에 선택할 수 있습니다. 다음 메서드들을 선택합니다.

```
onStart, onStop, onResume, onPause, onDestroy
```

- [OK] 버튼을 누르면 MainActivity 클래스 안에 선택한 메서드의 코드가 자동으로 추가됩니다. 이 메서드들은 액티비티의 상태에 따라 호출됩니다. 메서드가 호출될 때 호출 여부를 알 수 있도록 토스트 메시지를 각각의 메서드 안에 추가합니다. onCreate 메서드 안에도 토스트 메시지가 보이도록 다음처럼 코드 한 줄을 추가합니다.

- 이 앱을 실행해 보면 화면이 나타난 후부터 차례대로 토스트 메시지가 보이게 됩니다. 그런데 토스트 메시지는 여러 번 실행될 경우 이전 메시지가 보이지 않을 수도 있습니다. 따라서 디버깅 목적으로 사용할 때는 Logcat 창에 메시지를 출력하는 것이 좋습니다. 다음과 같이 println이라는 이름의 함수를 정의한 후 각 수명주기 메서드 안에서 이 println() 메서드를 호출하도록 모두 수정합니다.

#### SampleLifeCycle>/app/src/org.koreait.samplelifecycle/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		
		... 생략

       println("onCreate 호출됨");
    }

    @Override
    protected void onStart() {
        super.onStart();
        println("onStart 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        println("onStop 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        println("onDestroy 호출됨");
    }

    @Override
    protected void onPause() {
        super.onPause();
        println("onPause 호출됨");
    }

    @Override
    protected void onResume() {
        super.onResume();
        println("onResume 호출됨");
    }

    public void println(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        Log.d("Main", data);
    }
}
```
- 앱을 실행한 후  Logcat 창을 보면 메서드가 순서대로 실행됩니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/6.%20%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0%20%EC%88%98%EB%AA%85%20%EC%A3%BC%EA%B8%B0%EC%99%80%20SharedPreferences/images/image2.png)

- 만약 너무 많은 메시지가 출력된다면 코드에서 입력한 "Main"이라는 태그로 검색할 수 있습니다. Logcat 창의 오른쪽 위에 있는 콤보박스를 눌러보면 Edit Filter Configuration 항목을 선택할 수 있습니다. 그러면 로그에 필터를 걸어 검색할 수 있는 창이 보입니다.

- Log Tag:에 Main이라는 글자를 입력하면 소스 코드에서 Log.d 메서드를 호출할 때 첫 번째 파라미터로 넣었던 Main 글자로 검색할 수 있습니다. 이렇게 필터에 사용되는 글자를 태그(Tag)라고 부릅니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/6.%20%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0%20%EC%88%98%EB%AA%85%20%EC%A3%BC%EA%B8%B0%EC%99%80%20SharedPreferences/images/image3.png)

- 이렇게 출력되는 메시지들은 앞에서 설명한 다이어그램처럼 액티비티 생성부터 실행까지 자동으로 메서드가 호출되기 때문에 보인 것입니다. 이렇게 시스템에서 자동으로 호출하는 메서드를 '<b>콜백 메서드(Callback Method)</b>'라고 합니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/6.%20%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0%20%EC%88%98%EB%AA%85%20%EC%A3%BC%EA%B8%B0%EC%99%80%20SharedPreferences/images/image4.png)

- 앱을 실행하면 화면처럼 가운데에 버튼이 나타납니다. 그리고 Logcat 창에 onCreate..., onStart...,onResume...처럼 수명주기 메서드가 호출될 때 표시되는 메시지가 출력됩니다. 몇 번 반복하면서 앱을 실행시켜보면 액티비티의 상태가 어떻게 변하는지 쉽게 알 수 있을 것입니다. 그 순서를 정리하면다음과 같습니다.

```
onCreate -> onStart -> onResume -> onPause -> onStop -> onDestroy
```

- 화면이 보일 때는 <b>onCreate, onStart, onResume</b> 순서로 호출되고, 시스템 [BACK] 버튼을 눌러 화면을 없앨 때는 <b>onPause, onStop, onDestroy</b> 순서로 호출됩니다. MenuActivity 클래스 안에도 수명주기 메서드를 넣으면 메인 액티비티에서 메뉴 액티비티로 화면이 전환될 때 어떻게 호출되는지 알 수있습니다.

- 화면이 전환될 때는 메인 액티비티의 onDestroy 메서드가 호출되지 않습니다. 즉, 메뉴 액티비티가 화면에 보이는 시점에 메인 액티비티는 화면 뒤쪽에 숨어있는 것과 같은 상태가 되고(실제로는 액티비티 스택으로 들어감) 앞에 있던 메뉴 액티비티가 사라지면 다시 onResume 메서드가 호출되면서 화면에 보이게 됩니다.

- 이렇게 상태에 따라서 호출되는 콜백 메서드가 바로 수명주기 메서드입니다. 그런데 화면이 보일 때와 화면이 보이지 않을 때 항상 호출되는 메서드가 있습니다. 바로 <b>onResume</b>과 <b>onPause</b> 메서드입니다. 이 두 개의 메서드는 아주 중요합니다. 왜냐하면 앱이 갑자기 중지되거나 또는 다시 화면에 나타날 때 앱 데이터의 저장과 복원이 필요하기 때문입니다. 예를 들어, 게임을 할 때 사용자의 점수가 갑자기 사라지지 않도록 하려면 <b>onPause 메서드 안에서 데이터를 저장하고 onResume 메서드 안에서 복원</b>해야 합니다.

- 앱 안에서 간단한 데이터를 저장하거나 복원할 때는 <b>SharedPreferences</b>를 사용할 수 있습니다. 이것은 앱 내부에 파일을 하나 만드는데 이 파일 안에서 데이터를 저장하거나 읽어올 수 있게 합니다. 개발자는실제로 파일을 만들 필요 없이 SharedPreferences의 저장, 복원 메서드를 호출하면 됩니다.

- activity_main.xml 파일을 열고 버튼 아래쪽에 입력상자를 하나 추가합니다. 그리고 입력상자에 입력되어 있는 글자를 삭제한 후 입력상자의 id를 nameInput으로 설정합니다. 앱을 실행했을 때 이 입력상자에는 사람 이름을 입력할 것입니다. 그리고 앱을 종료한 후 다시 실행했을 때 사람 이름이 그대로 보이도록 만들 것입니다. MainActivity.java 파일을 열고 다음의 코드를 추가합니다. onPause 메서드 안에서는 데이터를 저장하고, onResume 메서드 안에서 복원하는 코드입니다.

#### SampleLifeCycle>/app/src/org.koreait.samplelifecycle/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
    EditText nameInput;
	
	... 생략 

    @Override
    protected void onPause() {
        super.onPause();
        println("onPause 호출됨");
        saveState(); // 현재 입력상자에 입력된 데이터를 저장
    }

    @Override
    protected void onResume() {
        super.onResume();
        println("onResume 호출됨");
        restoreState(); // 설정 정보에 저장된 데이터를 복원
    }

	... 생략

    protected void restoreState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if ((pref != null) && (pref.contains("name"))) {
            String name = pref.getString("name", "");
            nameInput.setText(name);
        }
    }

    protected void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", nameInput.getText().toString());
        editor.commit();
    }

    protected void clearState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
```

- <b>saveState</b> 메서드는 현재 입력상자에 입력된 데이터를 저장합니다. 데이터를 저장할 때는 <b>SharedPreferences</b>를 사용하며 pref 문자열을 저장소의 이름으로 사용합니다. SharedPreferences 객체를 사용하려면 <b>getSharedPreferences</b> 메서드로 참조합니다. SharedPreferences.Editor 객체는 데이터를 저장할 수 있도록 <b>edit 메서드</b>를 제공하는데 edit 메서드를 호출한 후 putOOO 메서드로 저장하려는 데이터를 설정할 수 있습니다. 데이터를 저장한 후에는 commit 메서드를 호출해야 실제로 저장됩니다.

- <b>restoreState</b> 메서드는 설정 정보에 저장된 데이터를 가져와서 토스트 메시지로 보여줍니다. 이렇게 상태 정보를 담고 있는 데이터를 저장하고 다시 복원하기 위해 만든 saveState와 restoreState 메서드는 onPause와 onResume 메서드에 들어가야 합니다. 그래야 액티비티가 화면에서 사라지거나 또는 다시 화면이 복원될 때 그 상태 그대로 사용자에게 보여줄 수 있습니다.

- 앱을 실행하고 화면이 없어졌다가 다시 보일 때 어떻게 보이는지 확인해보기 바랍니다. <b>onSaveInstanceState</b> 메서드와 <b>onRestoreInstanceState</b> 메서드도 액티비티의 상태와 관련하여 호출되므로 앞에서 살펴본 코드 대신 사용할 수 있습니다.
