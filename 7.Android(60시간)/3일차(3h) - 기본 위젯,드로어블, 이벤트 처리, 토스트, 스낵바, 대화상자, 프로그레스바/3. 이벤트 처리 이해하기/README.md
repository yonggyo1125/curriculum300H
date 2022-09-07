# 이벤트 처리 이해하기

- 윈도우 화면에서 마우스로 왼쪽 버튼으로 폴더를 더블 클릭하면 폴더가 열리고 바탕화면에서 마우스 오른쪽 버튼을 누르면 메뉴가 펼쳐지는 등의 기능은 마우스 클릭에 대한 이벤트를 처리해야 구현할 수 있습니다. 안드로이드도 마찬가지입니다. 사용자의 화면 터치에 대한 이벤트를 처리해야 여러 기능을 구현할 수 있습니다.

## 이벤트 처리 방식

- 안드로이드의 이벤트는 윈도우의 이벤트와 조금 다릅니다. 윈도우는 주로 마우스, 키보드로 조작하지만 안드로이드는 손가락 터치 방식으로 조작하기 때문입니다. 그리고 손가락으로 화면을 터치하면 ‘터치 이벤트(Touch Event)'가 발생합니다. 이 밖의 안드로이드 폰의 주요 이벤트로는 실제 버튼이나 소프트 키패드를 누르면 발생하는 '키 이벤트(Key Event)'도 있습니다.

- 터치 이벤트는 가장 많이 사용되는 이벤트이기 때문에 어떻게 처리하는지 정확하게 이해하는 것이 중요합니다. 그런데 터치 이벤트는 생각보다 복잡합니다. 손가락으로 누를 때, 누른 상태에서 움직일 때, 누른 상태에서 뗄 때 모두 다르게 처리해야 하죠. 그래서 터치 이벤트를 쉽게 처리할 수 있도록 '클릭 이벤트(Click Event)'를 별도로 제공합니다.

- 예를 들어, 버튼 태그에 onClick 속성을 추가하면 버튼을 클릭했을 때 발생하는 이벤트를 처리할 수 있습니다. 이 속성 값에 소스 코드에서 정의할 메서드 이름을 넣으면 되죠. 이렇게 하면 버튼에 발생하는클릭 이벤트를 속성 값에 지정한 메서드로 전달할 수 있습니다. XML이 아니라 소스 코드에서 클릭 이벤트를 처리하도록 하려면 버튼의setOnClickListener 메서드를 이용해 리스너를 설정할 수 있습니다.

- 이와 같은 이벤트 처리 방식은 화면에서 발생하는 이벤트를 버튼과 같은 위젯 객체에 전달한 후 그 이후의 처리 과정을 버튼에 위임한다고 해서 '위임 모델(Delegation Model)'이라고 부릅니다. 코드를 만들 때 사용하는 이 패턴은 각각의 뷰마다 하나의 이벤트 처리 루틴을 할당해 줍니다. 이렇게 하면 이벤트를 이벤트 루프에서 받아 처리할 때처럼 코드가 복잡해지지 않고 이벤트를 위젯마다 개별적으로 처리하는 객체 지향 코드를 만들 수 있습니다. 위임 모델은 각각의 이벤트를 처리할 수 있는 리스너(Listener) 인터페이스를 등록할 수 있도록 합니다. OnClickListener는 이벤트가 발생하면 즉시 동작할 수 있도록 만들어주는 리스너 중 하나입니다. 다음은 OnClickListener 사용 방식을 다이어그램으로 표현한 것입니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image1.png)

- 이 패턴처럼 다른 이벤트도 리스너 객체를 하나 만들어 설정하면 손쉽게 처리할 수 있습니다. 다음은 대표적인 이벤트 처리 메서드입니다. 이벤트가 전달되면 이 메서드들이 자동으로 호출되므로 이 메서드의 파라미터로 전달되는 이벤트 객체를 이용하면 필요한 작업을 수행할 수 있습니다.

```java
boolean onTouchEvent (MotionEvent event)
boolean onKeyDown (int keyCode, KeyEvent event)
boolean onKeyUp (int keyCode, KeyEvent event)
```

- 사용자가 화면을 터치하거나 키패드의 키를 하나 누르면 앞서 살펴보았던 이벤트가 발생하고 파라미터로 필요한 정보들이 전달됩니다. 터치했을 때 발생하는 이벤트는 MotionEvent라 불리며 키를 입력했을 때는 KeyEvent가 전달됩니다. 따라서 위와 같은 이벤트 처리 메서드들을 정의하면 이벤트가 전달될 때마다 처리할 수 있습니다.

- 그런데 이 메서드들은 뷰를 상속하여 새로운 클레스를 정의할 때 재정의할 수 있습니다. 예를 들어, Button 클래스를 상속하여 MyButton과 같은 새로운 클래스를 만들면 이 메서드들을 재정의할 수 있습니다. 만약 새로운 뷰를 정의하지 않고 기존의 뷰 객체에서 이벤트를 처리하려면 리스너를 설정하는 메서드를 호출해야 합니다. 다음은 기존의 뷰 클래스를 그대로 사용하면서 그 객체에 설정하여 사용하는 메서드들을 정의한 것이며, 모두 리스너 인터페이스를 구현하도록 만들어져 있습니다.

```java
View.OnTouchListener : boolean onTouch(View v, MotionEvent event)
View.OnKeyListener : boolean onKey (View v, int keyCode, KeyEvent event)
View.OnClickListener : void onClick (View v)
View.OnFocusChangeListener : void onFocusChange (View v, boolean hasFocus)
```

- OnClickListener의 경우에는 버튼과 같은 객체에 자주 사용되지만 OnTouchListener는 버튼뿐만 아니라 일반적인 뷰 객체에도 사용할 수 있습니다. 따라서 사용자와의 상호작용이 필요한 경우 훨씬 더 많이 사용됩니다.
- OnTouchListener에 정의된 onTouch 메서드는 사용자가 손가락으로 터치할 때마다 발생되는 이벤트를 받아 처리할 수 있으며, 
- OnKeyListener에 정의된 onKey 메서드는 키 입력이 발생할 때마다 발생되는 이벤트를 받아 처리합니다. 
- 뷰에 포커스가 주어지거나 없어질 경우에 발생하는 FocusChange 이벤트는 OnFocusChangeListener를 사용해 처리합니다. 대표적인 이벤트를 유형대로 정리하면 다음과 같습니다.

|속성|설명|
|-----|-------|
|터치 이벤트|화면을 손가락으로 누를 때 발생하는 이벤트|
|키 이벤트|키 패드나 하드웨어 버튼을 누를 때 발생하는 이벤트|
|제스쳐 이벤트|터치 이벤트 중에서 스크롤과 같이 일정 패턴으로 구분되는 이벤트|
|포커스|뷰마다 순서대로 주어지는 포커스|
|화면 방향 변경|화면 방향이 가로와 세로로 바뀜에 따라 발생하는 이벤트|

- 터치 이벤트는 사용자가 손가락으로 화면을 터치할 때마다 발생하는 이벤트인데 이 중에서 일정한 패턴, 즉 손가락으로 좌우로 스크롤할 때와 같은 패턴을 '제스처(Gesture)'라고 합니다. 이러한 제스처 이벤트는 터치 이벤트를 받은 후에 추가적인 확인을 거쳐 만들어지므로 일반적인 터치 이벤트보다 더 간단하게 처리할 수 있습니다. 다시 말해, 손으로 눌러 움직이는 스크롤도 하나의 이벤트로 만들어져 하나의 메서드에서 처리되므로 복잡한 이벤트를 좀 더 쉽게 처리할 수 있습니다. 
- 다음은 제스처 이벤트를 통해 처리할 수 있는 이벤트입니다.

|메서드|이벤트 유형|
|----|-----|
|onDown()|화면이 눌렸을 경우|
|onShowPress()|화면이 눌렸다 떼어지는 경우|
|onSingleTabUp()|화면이 한 손가락으로 눌렸다 떼어지는 경우|
|onDoubleTap()|화면이 두 손가락으로 눌려지는 경우|
|onDoubleTabEvent()|화면이 두 손가락으로 눌려진 상태에서 떼거나 이동하는 등 세부적인 액션을 취하는 경우|
|onScroll()|화면이 눌린 채 일정한 속도와 방향으로 움직였다가 떼는 경우|
|onFling()|화면이 눌린 채 가속도를 붙여 손가락을 움직였다 떼는 경우|
|onLongPress()|화면을 손가락으로 오래 누르는 경우|

- 제스처 이벤트로 처리할 수 있는 유형을 보면 단순히 터치 이벤트를 처리할 때보다 좀 더 복잡한 기능을 쉽게 처리할 수 있습니다.

## 터치 이벤트 처리하기

- 터치 이벤트가 어떻게 동작하는지 살펴보기 위해 새로운 SampleEvent 프로젝트를 만듭니다. 그리고 activity_main.xml 파일을 열고 디자인 화면에서 최상위 레이아웃을 LinearLayout으로 변경합니다. LinearLayout이 세로 방향으로 뷰를 쌓도록 orientation 속성 값을 vertical로 설정합니다.

- 화면 안에 있던 텍스트뷰는 삭제하고 팔레트의 widgets에서 두 개의 View를, containers에서 한 개의 ScrollView를 추가하세요. 세 개의 뷰가 순서대로 공간을 차지하도록 추가하면 됩니다. 그런 다음 ScrollView 안에 들어 있는 LinearLayout 안에 TextView를 넣어 글자가 보이게 만듭니다. 세 개의 뷰를 추가했다면 layout_height 속성 값을 모두 0dp로 설정하고 layout_weight 속성 값은 1로 설정합니다. 그러면 세 개의 뷰가 세로 방향으로 공간을 3분할하여 나눠 갖게 됩니다.

> 왼쪽 팔레트에서 ScrollView를 끌어다 놓았을 때 왼쪽 아래의 Component Tree에 ScrollView만 표시되고 그 안에 있는 LinearLayout은 표시되지 않는 경우가 있습니다. 이 경우에는 팔레트와 Component Tree 창 사이에 있는 구분선을 약간 끌어당기면 Component Tree가 새로 고침이 되면서 제대로 표시되기도 합니다.

- 이어서 뷰 각각의 배경색을 설정하기 위해 오른쪽 속성 창에서 background 속성을 찾은 후 속성의 왼쪽에 있는 버튼을 눌러 대화상자를 띄웁니다. 대화상자의 위쪽에 보이는 탭 중에서 [Custom] 탭을 누르면 색상을 선택할 수 있습니다. 연속된 색상이 보이는 위쪽에서는 색상을 지정할 수 있고 아래쪽에는자주 사용하는 색상을 표시합니다.

- 위쪽의 뷰에는 연한 파란색, 그 아래에 있는 뷰에는 연한 주황색, 그리고 마지막 뷰에는 흰색 배경색으로 설정합니다. 이렇게 세 개의 뷰에 모두 색상을 설정하면 다음과 같은 화면이 됩니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image2.png)

- TextView의 text 속성 값은 삭제하여 가장 아래쪽에 보이는 글자는 없애줍니다. 첫 번째 뷰의 id 속성값으로는 view, 두 번째 뷰의 id 속성 값으로는 view2가 설정되어 있는지 확인합니다. XML 레이아웃을 완성했다면 MainActivity.java 파일을 열고 다음 코드를 차근차근 입력합니다.

#### SampleEvent>/app/java/org.koreait.sampleevent/MainActivity.java

```java
package org.koreait.sampleevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        View view = findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    println("손가락 눌림 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    println("손가락 움직임 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    println("손가락 뗌 : " + curX  + ", " + curY);
                }

                return true;
            }
        });
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}
```

- 화면 위쪽에 배치한 뷰(view)를 findViewById 메서드로 찾아 참조한 후 setOnTouchListener 메서드를 호출하여 리스너를 등록합니다. 이 메서드를 호출할 때 파라미터로 리스너 객체를 전달하는데, new 연산자를 이용해 OnTouchListener 객체를 생성하면서 전달합니다. 그러면 뷰가 터치되었을 때 이 리스너 객체의 on Touch 메서드가 자동으로 호출됩니다.

- onTouch 메서드로는 MotionEvent 객체가 파라미터로 전달됩니다. 이 객체에는 액션 정보나 터치한 곳의 좌표 등이 들어 있습니다. 액션 정보는 getAction 메서드로 확인할 수 있으며 손가락이 눌렸는지눌린 상태로 움직이는지, 또는 손가락이 떼졌는지를 알 수 있도록 합니다. getAction 메서드를 호출하면 정수 자료형의 값이 반환되는데 그 값과 MotionEvent 클래스에 정의된 상수 값을 비교하면 손가락이 눌렸거나 눌린 상태로 움직이거나 또는 떼졌을 때의 상태를 알 수 있습니다.
	- <code>MotionEvent.ACTION_DOWN</code> : 손가락이 눌렸을 때
	- <code>MotionEvent.ACTION_MOVE</code> : 손가락이 눌린 상태로 움직일 때
	- <code>MotionEvent.ACTION_UP</code> : 손가락이 떼졌을 때
	
- 이 값은 if~else if~else 구문으로 비교할 수 있고 switch ~ case 구문으로 비교할 수도 있습니다. 여기서는 텍스트뷰에 한 줄씩 터치한 좌표를 출력하도록 하고 있습니다. 화면의 가장 아래쪽에 추가한 텍스트뷰에 글자를 추가하면서 보여주도록 하는 기능은 println이라는 이름의 함수로 만들어 두었습니다. 따라서 이 함수를 호출하기만 하면 파라미터로 전달된 글자를 텍스트뷰에 추가하여 보여줄 수 있습니다.

- 앱을 실행한 후 가장 위쪽에 있는 뷰를 터치한 채로 움직여보면 가장 아래쪽에 있는 텍스트뷰에 글자가 찍히는 것을 볼 수 있습니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image3.png)

- 이 예제를 통해 손가락의 상태를 구별할 수 있고 손가락이 어느 위치에 있는지를 확인할 수 있다는 것을 알았습니다. 이 예제를 사용하면 손가락이 움직일 때 사진이 따라서 움직이도록 만들 수도 있으며,손가락의 움직임에 따라 선을 그리도록 만들 수도 있습니다.

## 제스처 이벤트 처리하기

- 제스처 이벤트는 터치 이벤트 중에서 스크롤 등을 구별한 후 알려주는 이벤트입니다. 제스처 이벤트를처리해주는 클래스는 GestureDetector이며, 이 객체를 만들고 터치 이벤트를 전달하면 GestureDetector 객체에서 각 상황에 맞는 메서드를 호출합니다. 화면에 추가했던 두 번째 뷰를 터치했을 때 제스처 이벤트로 처리하도록 onCreate 메서드 안에 다음 코드를 추가합니다.


##### SampleEvent>/app/java/org.koreait.sampleevent/MainActivity.Java

```java

... 생략

public class MainActivity extends AppCompatActivity {

    TextView textView;
    GestureDetector detector;  // 제스터 디렉터 객체 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       
	   ... 생략

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onDown(MotionEvent motionEvent) {
                println("onDown() 호출됨.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) { 
                println("onShowPress() 호출됨.");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                println("onSingleTabUp() 호출됨.");

                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onScroll() 호출됨 : " + v + ", " + v1);

                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                println("onLongPress() 호출됨.");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onFling() 호출됨 : " + v + ", " + v1);
                
                return true;
            }
        });
		
		View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}
```

- 두 번째 뷰(view2)에는 OnTouchListener 객체를 설정하고 있습니다. 따라서 두 번째 뷰 객체를 터치했을 때는 자동으로 on Touch 메서드가 호출됩니다. 그리고 on Touch 메서드 안에서는 GestureDetector 객체의 onTouchEvent 메서드를 호출하면서 MotionEvent 객체를 전달합니다. 이렇게 하면 GestureDetector 객체가 터치 이벤트를 처리한 후 GestureDetector 객체에 정의된 메서드를 호출합니다. 제스처 이벤트 중에서 대표적인 것이 스크롤(Scroll)과 플링(Fling)인데 <b>스크롤</b>은 <b>손가락으로 드래그하는 경우</b>를 말하고 <b>플링</b>은 <b>빠른 속도로 스크롤을 하는 것</b>을 말합니다. 따라서 스크롤은 이동한 거리 값이 중요하게 처리되며, 플링은 이동한 속도 값이 중요하게 처리됩니다.


- 여러분이 마우스 포인터의 위치 변화를 일일이 계산하기는 쉽지 않습니다. 그래서 <b>GestureDetector 객체는 이런 이벤트를 간단하게 처리할 수 있도록 거리나 속도의 값을 파라미터로 전달</b>합니다. 그 외에도 오랫동안 손가락으로 누르고 있을 때 호출되는 onLongPress를 확인할 수 있습니다. 이 앱을 실행한 후 두 번째 뷰 위에서 빠른 속도로 드래그하거나 한 부분을 오랫동안 누르고 있다가 떼면 다음과 같은 화면을 볼 수 있습니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image4.png)

## 키 이벤트 처리하기

- 키 입력은 onKeyDown 메서드를 재정의하여 처리할 수 있습니다. onKeyDown 메서드로 전달되는 파라미터는 두 개이며, KeyCode는 어떤 키가 사용되는지 구별할 때 사용되고 KeyEvent는 키 입력 이벤트에 대한 정보를 알고 싶을 때 사용됩니다. 이러한 키 입력 이벤트는 하드웨어 키보드나 소프트 키패드에 상관없이 동일한 이벤트로 전달되며 시스템 버튼 중의 하나인 [BACK] 버튼도 이 이벤트로 처리할 수 있습니다. 시스템 버튼은 단말 아래쪽에 보이는 버튼으로 앱과 관계없이 단말에 의해 동작하며 그중에서 이전화면으로 돌아가거나 작업을 취소하는 목적으로 쓰이는 버튼이 [BACK] 버튼입니다. 나머지 두 개의 버튼은 [HOME] 버튼과 [Recent Apps] 버튼으로, 하나는 홈 화면으로 이동하는 데 사용되고 다른 하나는 최근에 실행된 앱 목록을 보여주는 데 사용됩니다. 이 두 개의 시스템 버튼은 앱에서 직접 제어는 안 되며 키가 입력되었는지 정보만 전달받을 수 있습니다.

```java
boolean onKeyDown (int keyCode, KeyEvent event)
boolean onKey (View v, int keyCode, KeyEvent event)
```

- 두 번째 onKey 메서드는 뷰의 OnKeyListener 인터페이스를 구현할 때 사용됩니다. 다음은 keyCode정수 값으로 구분할 수 있는 대표적인 키 값을 설명하고 있습니다.

|키코드|설명|
|----|-----|
|KEYCODE_DPAD_LEFT|왼쪽 화살표|
|KEYCODE_DPAD_RIGHT|오른쪽 화살표|
|KEYCODE_DPAD_UP|위쪽 화살표|
|KEYCODE_DPAD_DOWN|아래쪽 화살표|
|KEYCODE_DPAD_CENTER|[중앙] 버튼|
|KEYCODE_CALL|[통화] 버튼|
|KEYCODE_ENDCALL|[통화 종료] 버튼|
|KEYCODE_BACK|[뒤로 가기] 버튼|
|KEYCODE_VOLUMN_UP|[소리 크기 증가] 버튼|
|KEYCODE_VOLUMN_DOWN|[소리 크기 감소] 버튼|
|KEYCODE_0 \~ KEYCODE_9|숫자 0부터 9까지의 키의 값|
|KEYCODE_A \~ KEYCODE_Z|알파벳 A부터 Z까지의 키 값|



- 실제 앱을 구성하면서 사용하는 키 입력의 대표적인 예는 카메라 미리보기를 하면서 사용하는 [카메라] 버튼과 시스템 [BACK] 버튼이 있습니다. 이 버튼들은 각각 KEYCODE_CAMERA와 KEYCODE_BACK으로 구분되는 코드 값을 가지고 있습니다. 시스템 [BACK] 버튼 이벤트는 onKeyDown 메서드를 사용하면 간단히 처리할 수 있습니다.

> <b>시스템 [BACK] 키가 눌렸을 때 간단히 처리하는 방법</b><br>시스템 [BACK] 키를 누르는 경우는 자주 사용되므로 onBackPressed 메서드만 다시 정의하면 간단하게 이벤트를 처리할 수도 있습니다.<br><code>void onBackPressed()</code>

- MainActivity.java 파일을 연 상태에서 MainActivity 클래스를 클릭한 후 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [Generate... -> Override Methods...] 메뉴를 선택합니다. 그리고 메서드를 재정의하는 대화상자가 보이면 onKeyDown 메서드를 선택한 후 [OK]를 누릅니다. onKeyDown 메서드가 MainActivity 클래스 안에 추가되면 다음과 같이 코드를 입력합니다.

#### SampleEvent>/app/java/org.techtown.sampleevent/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
	
	... 생략 
		
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "시스템 [BACK] 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
        }
        return false;
    }

	... 생략
	
}
```

- onKeyDown 메서드로 KEYCODE_BACK 이벤트를 확인하면 토스트 메시지가 표시되게 합니다. 앱을 실행하고 시스템 [BACK] 버튼을 누르면 토스트 메시지를 볼 수 있습니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image5.png)

## 단말 방향을 전환했을 때 이벤트 처리하기

- 앱을 만들면서 자주 마주하는 문제 중 하나가 단말을 세로/가로 방향으로 바꿨을 때 발생하는 방향(Orientation) 전환 문제입니다. 단말의 방향이 바뀌었을 때는 가로와 세로 화면의 비율에 따라 화면이다시 보이게 됩니다. 다시 말하면 XML 레이아웃이 다르게 보여야 하는 거죠. 이 때문에 액티비티는 메모리에서 없어졌다가 다시 만들어집니다.

- 보통 사용자는 세로 방향으로 보던 내용을 가로 방향으로 바꾸면 내용을 좀 더 크게 보거나 또는 왼쪽과 오른쪽에 좀 더 다양한 내용이 나타나길 바랍니다. 따라서 단말의 방향이 바뀌었을 때 세로 방향의 XML 레이아웃과 가로 방향의 XML 레이아웃을 따로 만들어 둘 필요가 있습니다. 어차피 방향 전환을할 때 액티비티가 없어졌다가 다시 보이게 되니까 말이죠.

- 단말 방향을 바꿨을 때 서로 다른 XML 레이아웃을 보여주는 방법을 알아보기 위해 SampleOrientation이라는 이름의 새로운 프로젝트를 만듭니다. 그리고 왼쪽 프로젝트 창에서 res 폴더 아래에 새로운폴더를 만듭니다. 새로운 폴더를 만들려면 res 폴더를 선택한 후 마우스 오른쪽 버튼을 누르고 [New→ Android Resource Directory] 메뉴를 선택합니다. Directory name: layout-land를 입력하고 Resource type:은 layout을 선택합니다. [OK] 버튼을 누르면 새로운 리소스 폴더가 만들어집니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image6.png)

- 그런데 새로 만든 폴더는 프로젝트 창에는 보이지 않습니다.그 이유는 왼쪽 프로젝트 창은 실제 폴더나 파일을 보여주는것이 아니라 필요한 정보만 정리해서 보여주기 때문입니다. 왼쪽 프로젝트 창 상단에서 [Project] 탭을 선택하면 프로젝트 안의 모든 파일을 원래대로 보여주기 때문에 다음과 같이새로 만든 폴더를 확인할 수 있습니다. SampleOrientation/app/src/main/res 경로로 들어가면 Layout-land 리소스 폴더가 추가되어 있습니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image7.png)

- layout-land 폴더는 layout 폴더와 같은 역할을 하지만 단말이 가로 방향으로 보일 때는 layout-land 폴더 안에 들어 있는 XML 레이아웃 파일이 사용됩니다. 즉, layout 폴더의 activity_main.xml 파일은 단말이 세로 방향일 때 사용되고, layout-land 폴더의 activity_main.xml 파일은 단말이 가로 방향일 때 사용됩니다. 지금까지는 layout-land 폴더가 없었기 때문에 단말이 가로 방향이든 세로 방향이든 상관없이 layout 폴더의 XML 레이아웃 파일이 디폴트로 사용되었던 것입니다. layout-land 폴더의 이름은 미리 지정된 것입니다. 이름으로 된 폴더는 앱을 실행했을 때 단말에 의해 자동으로 확인된 후 단말을 가로 방향으로 돌리면 이 폴더 안에 들어 있는 레이아웃 파일을 우선적으로 적용합니다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image8.png)

- layout 폴더 안에 있는 activity_main.xml 파일을 열고 가운데 들어 있는 TextView의 글자를 '세로 방향’으로 변경합니다. 글자 크기도 더 크게 바꾸기 위해 textSize 속성 값을 50sp로 설정합니다. 그리고 activity_main.xml 파일을 복사하여 layout-land 폴더에 넣은 후 글자는 '가로 방향’으로 변경합니다. 파일을 복사할 때는 프로젝트 창에서 activity_main.xml 파일을 선택한 후 [Ctrl]+ [C]버튼을 누릅니다. 그리고 layout 폴더가 선택된 상태에서 [Ctrl] + [V] 버튼을 누릅니다. 그러면 layout 또는 layout-land 폴더 중에서 선택하여 저장할 수 있습니다.

- 그런데 단말의 방향이 바뀔 때 액티비티를 메모리에서 없앴다가 다시 만든다고 했으니 그것도 함께 확인해 보겠습니다. MainActivity.java 파일을 열고 MainActivity 클래스 안에 커서를 둔 상태에서 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [Generate... →Override Methods...] 메뉴를 눌러 메서드를 재정의하는 대화상자를 띄웁니다. 재정의할 메서드로 <b>onStart, onStop, onDestroy</b>를 모두 선택한 후 [OK] 버튼을 누릅니다. 이 메서드들이 추가되면 그 메서드가 호출되었을 때 토스트 메시지가 보이게 다음 소스 코드처럼 show Toast 메서드를 각각 추가합니다. 이렇게 여러분이 직접 호출하는 것이 아니라 화면의 상태에 따라 시스템이 자동으로 호출해주는 메서드를 '수명 주기(Life Cycle)' 또는 '생명 주기 메서드'라고 부릅니다. 이 내용은 나중에 좀 더 자세하게 알아볼 것이므로 여기에서는 화면 상태에 따라 자동으로 호출된다고만 알아두면 됩니다.

#### SampleOrientation>/app/java/org.koreait.sampleorientation/MainActivity.java

```java
package org.koreait.sampleorientation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast("onCreate 호출됨.");
    }

    @Override
    protected void onStart() {
        super.onStart();

        showToast("onStart 호출됨.");
    }

    @Override
    protected void onStop() {
        super.onStop();

        showToast("onStop 호출됨.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        showToast("onDestroy 호출됨.");
    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}
```

- 토스트 메시지를 띄우는 코드가 반복해서 사용되므로 showToast 메서드를 새로 만들었습니다. 그리고 onCreate, onStart, onStop, onDestroy 메서드 안에서 showToast 메서드를 호출하여 토스트 메시지가 표시되도록 했습니다. 
- 앱을 실행한 후 단말의 방향을 바꾸면 XML 레이아웃이 달라집니다. 그리고 액티비티도 없어졌다가 새로 만들어지는 것을 토스트 메시지로 확인할 수 있습니다. 
- 액티비티는 화면에 보이기 전에 메모리에 만들어져야 하는데 그 시점에 <b>onCreate</b> 메서드가 호출됩니다. 그리고 화면에 보이기 전에 <b>onStart</b> 메서드가 호출됩니다. 화면이 보이다가 없어지면 <b>onStop</b>이 호출될 수 있으며, 메모리에서 없어지는 경우에는 <b>onDestroy</b> 메서드가 호출됩니다.

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image9.png)

- 단말의 방향을 바꾸려면 에뮬레이터의 오른쪽에 보이는 아이콘 중에서 [Rotate left]나 [Rotateright] 아이콘을 사용합니다. 아이콘을 눌렀는데도 단말 방향이 바뀌지 않는다면 단말의 시스템 버튼들이 있는 곳을 자세히 보기 바랍니다. 시스템 버튼들 옆에 작은 회전 모양 아이콘이 보일 것입니다. 그 버튼까지 눌러주어야 단말 방향이 회전됩니다.

- 단말의 방향을 바꿨을 때 다른 화면이 보이게 하는 방법을 알아보았습니다. 그런데 한 가지 문제가 있습니다. 단말의 방향이 바뀔 때 액티비티가 메모리에서 없어졌다가 새로 만들어진다는 점입니다. 
- 이 경우에 액티비티 안에 선언해 두었던 변수 값이 사라지므로 변수의 값을 저장했다가 다시 복원하는 방법이 있어야 합니다. 
- 이런 문제를 해결할 수 있도록 <b>onSaveInstanceState</b> 콜백 메서드가 제공됩니다. 이메서드는 액티비티가 종료되기 전의 상태를 저장합니다. 그리고 이때 저장한 상태는 <b>onCreate</b> 메서드가 호출될 때 전달되는 번들 객체로 복원할 수 있습니다.

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image10.png)

- 상태를 저장했다가 복원하는 기능을 확인하기 위해 XML 레이아웃 파일에 에디트텍스트와 버튼을 추가합니다. 이 에디트텍스트에 글자를 넣고 버튼을 누르면 MainActivity 클래스 안에 정의한 변수에 해당 글자를 할당할 것입니다. 그리고 단말의 방향을 돌리면 액티비티가 없어졌다가 새로 생성되므로 이때 변수의 값을 저장했다가 복원하게 합니다. XML 레이아- 파일은 layout 폴더와 layout-land 폴더 안에 각각 들어 있으므로 두 폴더 안에 들어 있는 activity_main.xml 파일을 열고 에디트텍스트와 버튼을 각각 추가합니다. 

> 에디트텍스트는 팔레트의 Text 메뉴에서 Plain Text를이용하여 만들면 됩니다.

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image11.png)

- 에디트텍스트에 들어 있는 'Name'이라는 글자는 text 속성을 찾아 삭제합니다. 그리고 버튼에는 '확인'이라는 글자가 보이도록 text 속성 값으로 '확인'을 입력합니다. layout 폴더 안에 있는 activity_main.xml 파일의 가운데 텍스트뷰 글자는 '세로 방향'으로 설정하고 layout-land 폴더 안에 있는 activity_main.xml 파일의 가운데 텍스트뷰 글자는 '가로 방향'으로 설정합니다. 
- layout 폴더와 layout-land폴더 안에 있는 activity_main.xml 파일을 모두 수정했다면 MainActivity.java 파일을 열고 onSaveInstanceState 메서드를 재정의합니다. 커서를 MainActivity 클래스 안에 둔 상태에서 마우스 오른쪽 버튼을 누르고 팝업 메뉴가 보이면 [Generate... → Override Methods...] 메뉴를 선택합니다. 대화상자가 보이면 onSaveInstanceState 메서드를 선택한 후 [OK] 버튼을 누릅니다. 이제 액티비티가 메모리에서 없어지기 전에 onSaveInstanceState 메서드 안에서 변수의 값을 저장하도록 만들 수 있습니다.다음 소스 코드를 참조해서 수정합니다.


#### SampleOrientation>/app/java/org.koreait.sampleorientation/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
    String name;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast("onCreate 호출됨.");

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 버튼을 클릭했을 때 사용자가 입력한 값을 name 변수에 할당
                name = editText.getText().toString();
                showToast("입력된 값을 변수에 저장했습니다 : " + name);
            }
        });

        if (savedInstanceState != null) {
            name = savedInstanceState.getString("name");
            showToast("값을 복원했습니다 : " + name);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        
        // name 변수의 값 저장
        outState.putString("name", name);
    }

    ... 생략
}
```

- MainActivity 클래스 안에 name 변수를 선언했습니다. 사용자가 에디트텍스트에 글자를 입력하고 [확인] 버튼을 누르면 에디트텍스트에 입력한 글자를 가져와 이 변수에 할당합니다. <b>setOnClickListener</b>메서드는 버튼에 리스너 객체를 설정할 때 사용되는데 파라미터로 전달되는 객체는 new 연산자를 이용해 생성됩니다. 이렇게 OnClickListener 객체를 설정하면 버튼을 클릭했을 때 그 안에 있는 <b>onClick</b>메서드가 호출됩니다.

- 단말 방향을 바꾸어 액티비티가 소멸되었다가 다시 만들어질 때는 onSaveInstanceState 메서드 안에서 name 변수의 값을 파라미터로 전달받은 Bundle 객체에 넣어줍니다. 이 Bundle 객체에 데이터를 넣으면 그 데이터는 단말에 저장되고 onCreate 메서드가 호출될 때 파라미터로 전달됩니다. onCreate메서드의 파라미터는 savedInstanceState라는 이름으로 되어있으며, 이 객체에서 데이터를 가져와 name 변수에 다시 할당하면 데이터를 복구하게 됩니다.

- 앱을 실행하고 에디트텍스트에 사람 이름을 입력한 후 버튼을 누릅니다. 그러면 입력된 값을 변수에 저장했다는 토스트 메시지가 출력됩니다. 그다음 단말 방향을 바꾸면 액티비티가 메모리에 다시 만들어지면서 값을 복원했다는 토스트 메시지가 출력됩니다. 다만 에디트텍스트에 표시되는 값은 여러분이직접 복원하여 설정하지 않아도 그대로 유지된다는 점에 주의하세요.

![image12](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image12.png)

![image13](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image13.png)

- 토스트 메시지를 여러 개 보이도록 한 경우에는 이전 토스트 메시지 때문에 그다음 토스트 메시지가 보이지 않을 수 있습니다. 따라서 onStart 메서드 안에서 토스트 메시지를 띄우는 코드는 주석으로 막아놓고 실행해보면 가로 방향으로 단말을 돌렸을 때 토스트 메시지가 보이도록 만들 수 있습니다.

- 지금까지 단말 방향이 바뀌어 액티비티가 새로 만들어질 때 변수의 값을 저장했다가 복원하는 방법을알아보았습니다. 그런데 이렇게 시스템이 액티비티를 없앴다가 다시 만들어주는 이유는 가로 방향일 때의 액티비티와 세로 방향일 때의 액티비티가 서로 다를 수 있기 때문입니다. 하지만 액티비티는 바뀌지 않고 단순히 화면에 보이는 레이아웃만 바꾸고 싶다면 액티비티를 굳이 없앴다가 다시 만들 필요가 없습니다. 이 때문에 액티비티를 유지할 수 있는 방법을 따로 제공합니다. 기본적으로 단말의 방향 전환은 내부 센서에 의해 방향이 바뀌는 시점을 알 수 있습니다. 따라서 방향이 바뀌는 이벤트를 앱에 전달한 다음 추가적인 기능이 동작하도록 만들 수 있습니다. 단말의 방향이 바뀌는 것을 앱에서 이벤트로 전달받도록 하고 액티비티는 그대로 유지하는 방법을 사용하려면 먼저 매니페스트에 액티비티를 등록할 때 <b>configChanges</b> 속성을 설정해야 합니다.

- 단말 방향이 바뀔 때 액티비티를 유지하는 방법을 사용해 보기 위해 새로운 SampleOrientation2 프로젝트를 만듭니다. activity_main.xml 파일 안에 들어 있는 텍스트뷰의 글자는 '단말의 방향을 바꾸어보세요.'로 변경하고 글자 크기는 30sp로 설정합니다. 그리고 왼쪽 프로젝트 창에서 /app/manifests 폴더 안에 들어 있는 AndroidManifest.xml 파일을 엽니다. 그 안에는 여러 개의 태그가 들어 있습니다. 그 중에서 \<activity\> 태그가 액티비티를 등록할 때 사용하는 태그인데, MainActivity를 위해 등록된 \<activity\> 태그에 <b>configChanges</b> 속성을 추가합니다.

#### SampleOrientation2>/app/manifests/AndroidManifest.xml

```xml

... 생략 

<activity
	android:name=".MainActivity"
	android:configChanges="orientation|screenSize|keyboardHidden" 
	android:exported="true">
	<intent-filter>
		<action android:name="android.intent.action.MAIN" />

		<category android:name="android.intent.category.LAUNCHER" />
	</intent-filter>
</activity>

... 생략

```

- <b>configChanges 속성 값이 설정되면 시스템은 액티비티의 상태 변화를 액티비티 쪽으로 알려주기만 합니다.</b> 따라서 개발자가 직접 각 상태 변화에 따른 대응 코드를 넣어야 합니다. configChanges 속성 값으로 <b>orientation screenSizelkeyboardHidden</b>을 설정하면 단말의 방향이 바뀔 때마다 액티비티에서 인식할 수 있으며, 단말의 방향이 바뀌는 시점에 configurationChanged 메서드가 자동으로 호출됩니다. 여기에서 keyboardHidden 값은 단말의 방향 전환과는 관련이 없지만 자주 사용되는 값 중의 하나입니다. 이 값을 함께 설정하면 액티비티가 보일 때 키패드가 자동으로 나타나지 않도록 하고 키패드가 보여야 할 시점을 액티비티 쪽에 알려주기만 합니다. MainActivity.java 파일을 열고 <b>onConfigurationChanged</b> 메서드를 재정의합니다.

#### SampleOrientation2>/app/java/org.koreait.sampleorientation2/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {

    ... 생략
	
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showToast("방향 : ORIENTATION_LANDSCAPE");

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            showToast("방향 : ORIENTATION_PORTRAIT");
        }
    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}
```

> 메서드를 재정의하는 방법을 잊지는 않았겠죠? MainActivity를 마우스 오른쪽 버튼으로 클릭한 뒤 Generate를 누릅니다. 그런 다음 Override Methods를 눌러 onConfigurationChanged 메서드를 찾아서 선택하면 됩니다. 단축키를 사용해도 [Select Methods to Override/Implement] 대화상자를 불러올 수 있습니다. MainActivity를 클릭해서 선택한 상태로 [Ctrl] + [O]를 누르면 됩니다.

- onConfigurationChanged 메서드가 호출될 때 전달되는 Configuration 객체에는 orientation 속성이 들어 있어 단말의 방향이 가로로 바뀌었는지 아니면 세로로 바뀌었는지 알 수 있습니다. 이 값을 if 문에서 비교한 후 토스트 메시지를 띄우도록 합니다. onCreate 메서드 외에 onStart, onStop, onDestroy 메서드도 재정의한 후 각각의 메서드가 호출될 토스트 메시지가 보이게 합니다. 이렇게 하면 단말방향이 바뀔 때 액티비티가 다시 만들어지는지 확인할 수 있습니다.

- 앱을 실행한 후 단말의 방향을 바꾸면 다음과 같은 결과를 볼 수 있습니다.

![image14](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/3.%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/images/image14.png)

> 에뮬레이터는 방향이 바뀌는 것을 알 수 있는 센서가 정상적으로 동작하지 않을 수 있습니다. 따라서 단말의 방향이 바뀌는 것을 확인할 때는 되도록 실제 단말로 테스트하는 것이 좋습니다.

- 방향을 세로 또는 가로로 고정시키고 싶다면 매니페스트 파일에서 액티비티의 screenOrientation 속성 값을 지정하면 됩니다. 예를 들어, 사진을 보는 뷰어를 만들 때 액티비티에 사진이 잘 보이도록 화면을 항상 가로 방향으로 고정해 두겠다면 매니페스트에 다음처럼 설정할 수 있습니다. 매니페스트를 설정한 다음 앱을 다시 실행해 보세요.

```xml
<activity
	android:name=".MainActivity"
	android:screenOrientation="landscape"
	android:configChanges="orientation|screenSize|keyboardHidden"
	android:exported="true">
	<intent-filter>
		<action android:name="android.intent.action.MAIN" />

		<category android:name="android.intent.category.LAUNCHER" />
	</intent-filter>
</activity>
```

> [환경설정 - 디스플레이 - 자동방향전환] 설정이 체크되어 있지 않다면 단말기의 방향을 돌리더라도 방향이 자동 전환되지 않으므로 방향 전환 이벤트를 받을 수 없습니다. 만약 앱을 실행하고 단말의 방향을 돌려도 토스트 메시지가 보이지않는다면 환경설정을 확인하세요.