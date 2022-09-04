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