# 새로운 뷰 만들기

- API에서 제공하는 위젯을 사용하면 거의 대부분의 화면을 만들 수 있습니다. 하지만 여러분이 원하는기능을 가진 위젯을 따로 구상해야 할 때는 새로운 뷰 위젯을 만들어야 합니다. 이번에는 API에서 제공하는 뷰를 사용해서 새로운 뷰를 정의해 보겠습니다.

- API에서 제공하는 뷰를 사용하려면 API의 뷰를 상속해야 합니다. 그리고 API의 뷰를 상속하여 새로운 뷰를 만들 때는 뷰가 그려지는 방법을 반드시 이해해야 합니다. 뷰의 영역과 크기는 그 뷰를 포함﻿하고 있는 레이아웃의 영향을 받아 정해집니다. 이때 개발자가 뷰의 상태에 따라 추가적인 코드를 넣을 수 있도록 콜백 메서드가 호출됩니다. 뷰가 스스로의 크기를 정할 때 자동으로 호출되는 메서드는 onMeasure이고 스스로를 레이아웃에 맞게 그릴 때는 onDraw 메서드가 자동으로 호출됩니다.

```java
public void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
public void onDraw(Canvas canvas)
```

- onMeasure 메서드의 파라미터로 전달되는 widthMeasurespec과 heightMeasureSpec은 이 뷰를 담고 있는 레이아웃에서 이 뷰에게 허용하는 여유 공간의 폭과 높이에 대한 정보입니다. 즉, 부모 컨테이너에서 여유 공간에 대한 정보를 전달하는데 이 값을 참조하여 뷰가 보일 적절한 크기를 반환하면 이 크기 값으로 뷰가 그려지게 됩니다. onMeasure 메서드 안에서 이 뷰를 담고 있는 레이아웃에게 이 뷰의 크기 값을 반환하고 싶다면 다음 메서드를 사용합니다.

```java
void setMeasuredDimension (int measuredWidth, int measuredHeight)
```

- 이 메서드의 두 파라미터는 뷰의 폭과 높이 값이 됩니다.

## onDraw 메서드와 invalidate 메서드 이해하기

- 뷰가 화면에 보일 때는 onDraw 메서드가 호출됩니다. 예를 들어, 버튼의 경우에 그림으로 된 아이콘이나 글자가 그 위에 표시되려면 먼저 그 버튼을 담고 있는 레이아웃에 따라 버튼의 위치와 크기가 정해져야 합니다. 그런 다음 버튼의 모양과 그 안의 아이콘 또는 글자를 화면상에 그려주는 과정을 거치게 됩니다. 이렇게 그려지는 과정에서 호출되는 onDraw 메서드를 다시 정의하면 여러분이 보여주려는 내용물을 버튼 위에 그릴 수 있습니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/1.%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image1.png)

- 결국, 새로운 뷰를 클래스로 정의하고 그 안에 onDraw 메서드를 다시 정의한 후 필요한 코드를 넣어 기능을 구현하면 다른 모양으로 보이는 뷰를 직접 만들 수 있는 것입니다. 뷰 위에 그래픽을 그리는 과정을 설명하는 그림에서 볼 수 있듯이, 새로 정의한 MyView 클래스의 onDraw 메서드는 새로 정의한 ﻿뷰가 화면에 보이기 전에 호출되므로 이 메서드 안에서 원하는 모양의 그래픽을 화면에 그리면 그 모양대로 화면에 표현할 수 있습니다. 또한 손가락으로 터치하여 화면에 그려진 뷰를 이동시키려고 할 때는 뷰가 이동한 후에 그 뷰의 그래픽을 다시 그려야 할 필요가 있는데 이때 invalidate 메서드를 호출하면됩니다. invalidate 메서드가 호출되면 자동으로 onDraw 메서드가 다시 호출되어 이동한 좌표에 뷰의 그래픽을 다시 그리도록 만들 수 있습니다.

- 버튼을 직접 만들어보기 위해 새로운 SampleView 프로젝트를 만듭니다. 프로젝트를 만들 때 패키지이름은 org.koreait.view로 입력합니다. 프로젝트가 만들어지면 /app/java/org.koreait.view 폴더 안에 새로운 MyButton 클래스를 만듭니다. 새로운 클래스를 만들 때는 해당 폴더를 선택한 상태에서 마우스 오른쪽 버튼을 누르고 [New → Java Class] 메뉴를 선택하면 됩니다. 입력란에 My Button을입력하고 Enter를 치면 새로운 클래스가 만들어집니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/1.%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image2.png)

- 생성자를 추가할 수 있는 대화상자에는 세 개의 메서드가 표시되는데 그중에서 두 개가 필수 생성자입니다. 첫 번째 생성자는 Context 객체만 파라미터로 전달받으며 두 번째 생성자는 Context 객체와 ﻿AttributeSet 객체를 파라미터로 전달받습니다. 안드로이드는 UI 객체를 만들 때 Context 객체를 전달받도록 되어 있으므로 생성자에는 항상 Context 객체가 전달되어야 합니다. AttributeSet 객체는 XML레이아웃에서 태그에 추가하는 속성을 전달받기 위한 것으로 이 뷰를 XML 레이아웃에 추가하는 경우이 두 번째 생성자가 사용됩니다. 첫 번째 생성자는 이 뷰를 소스 코드에서 new 연산자로 생성하는 경우에 사용됩니다. 두 개의 생성자를 선택하고 [OK] 버튼을 누르면 두 개의 생성자가 소스 파일에 추가됩니다.

```java
public MyButton(Context context)
public MyButton(Context context, AttributeSet attrs)
```

- 생성자가 두 개이므로 이 뷰가 초기화될 때 필요한 코드는 init 메서드를 만들어 그 안에 정의합니다. 이렇게 하면 두 개의 생성자에서 모두 init 메서드를 호출하도록 할 수 있어 어떤 생성자가 호출되는상관없이 동일한 초기화 작업이 진행되도록 만들 수 있습니다. 다음을 입력하세요. 소스를 완성하면 text_size에만 오류가 표시될 것입니다. 이 오류는 바로 수정할 것이니 걱정하지 않아도 됩니다.

#### SampleView>/java/org.koreait.view/MyButton.java

```java
package org.koreait.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

// AppCompatButton 클래스 상속하여 새로운 클래스 정의하기
public class MyButton extends AppCompatButton {
    public MyButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    // 초기화를 위한 메서드 정의하기
    private void init(Context context) {
        setBackgroundColor(Color.CYAN);
        setTextColor(Color.BLACK);

        // 초기화를 위한 메서드 정의하기
        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);

    }
}
```

- init 메서드에는 Context 객체를 전달하도록 했으며, init 메서드 안에서는 뷰의 배경색과 글자색을 설정하도록 했습니다. 배경색을 설정할 때는 setBackgroundColor 메서드를 호출하면 되고 글자색을 설정할 때는 setTextColor 메서드를 호출하면 됩니다. setTextSize 메서드를 이용하면 글자 크기도 설﻿정할 수 있습니다. 하지만 이 방법은 픽셀 단위 설정만 할 수 있습니다. 그래서 이 방법은 잘 사용하지않습니다. 글자 크기는 화면 크기별로 다르게 표현되는 sp 단위를 사용하는 것을 권합니다. 그런데 <b>sp단위 설정으로 글자 크기를 조절하려면 소스 코드가 아니라 XML 파일을 사용</b>해야 합니다. /app/res/values 폴더 안에 dimens.xml 파일을 하나 만듭니다. 그리고 그 안에 다음과 같이 크기를 정의합니다.

#### SampleView>/app/res/values/dimens.xml

```xml
<?xml version="1.0" encoding="utf-8" ?>
<resources>
    <dimen name="text_size">16sp</dimen>
</resources>
```

- dimens.xml 파일은 크기 값 등을 정의할 수 있는 파일입니다. 이 파일의 \<resources\> 태그 안에 \<dimen\> 태그를 추가하고 dp, sp 또는 다른 단위의 크기 값을 정의하면 소스 코드에서 그 값을 참조할 수있습니다. 소스 코드에서 참조할 때는 Resources 객체의 getDimension 메서드를 사용합니다. 이 메서드에서 반환하는 값은 픽셀 값으로 자동 변환된 값입니다.

- 이제 onDraw 메서드와 onTouchEvent 메서드를 재정의해 봅니다. MyButton.java 파일 안에 커서를 두고 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [Generate -> Override Methods...] 메뉴를 선택하여 부모 클래스에 있는 메서드를 재정의하기 위한 대화상자를 띄웁니다. onDraw 메서드와 onTouchEvent 메서드를 선택하고 [OK] 버튼을 누르면 두 개의 메서드가 추가됩니다.


#### SampleView>/app/java/org.koreait.view/MyButton.java

```java

... 생략

// AppCompatButton 클래스 상속하여 새로운 클래스 정의하기
public class MyButton extends AppCompatButton {
	
	... 생략
	
    @Override  // 뷰가 그려질 때 호출되는 함수에 기능 추가하기
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("MyButton", "onDraw 호출됨.");
    }

    @Override // 뷰가 터치될 때 호출되는 함수에 기능 추가하기
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyButton", "onTouchEvent 호출됨");

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(Color.BLUE);
                setTextColor(Color.RED);

                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.CYAN);
                setTextColor(Color.BLACK);

                break;
        }

        invalidate();
        
        return true;
    }
}
```

- ﻿처음에는 MyButton 뷰의 배경색이 밝은 파랑으로 글자색은 검은색으로 만들어지지만 버튼을 누르면 배경색은 파랑으로 글자색은 빨간색으로 변경됩니다. onTouchEvent 메서드가 호출되면서 전달되는 MouseEvent 객체에는 getAction 메서드가 있어서 손가락이 눌렸는지, 눌린 상태로 드래그되는지 또는 손가락이 떼어졌는지를 알 수 있습니다. getAction 메서드는 정수형(int) 값으로 이 상태를 반환하죠. 반환 값은 각각 MotionEvent 객체의 ACTION_DOWN, ACTION_MOVE, ACTION_UP 상수로 정의되어 있습니다. 이 값을 이용하여 손가락이 눌린 상태를 검사할 수 있습니다. 손가락이 눌렸을 때 배경색과 글자색을 바꾸었다면 invalidate 메서드를 호출하여 뷰를 다시 그립니다. 뷰가 다시 그려진다면 onDraw 메서드가 동작합니다. 그래서 onDraw 메서드에 Log.d 메서드를 작성해 두었습니다.

- 이렇게 정의한 새로운 버튼은 XML 레이아웃에 추가하거나 또는 소스 코드에서 new 연산자를 사용해 새로운 객체로 만든 후 레이아웃 객체의 addView 메서드로 추가할 수 있습니다. activity_main.xml 파일을 열고 최상위 레이아웃을 RelativeLayout으로 바꿉니다. 기존에 있던 텍스트뷰는 삭제하고 새로 만든 MyButton을 화면 가운데 추가합니다. MyButton의 가로 크기는 200dp, 세로 크기는 80dp로 설정하고 text 속성 값은 '시작하기'로 설정합니다. 디자인 화면의 우측 상단에 있는 [Code] 아이콘을 눌러 원본 XML 코드를 열어 보면 다음과 같이 보입니다.

####  SampleView>/app/res/layout/activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!-- 새로 만든 MyButton 클래스를 태그로 추가하기 -->
    <org.koreait.view.MyButton
        android:id="@+id/button"
        android:layout_width="200dp"
        android:layout_height="80dp"
        android:layout_centerInParent="true"
        android:text="시작하기"
        />

</RelativeLayout>
```

- MyButton은 직접 정의한 위젯이므로 XML 레이아웃에 추가할 때 패키지 이름까지 함께 넣어야 합니다.

```xml
<org.koreait.view.MyButton ...  />
```

- 상대 레이아웃 안에 추가했으므로 layout_centerInParent 속성 값을 true로 설정하여 화면 가운데에보이도록 합니다. 디자인 화면에서 XML 레이아웃을 직접 만들 경우 다음과 같은 화면이 됩니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/1.%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image3.png)

- 이제 앱을 실행하면 여러분이 직접 만든 버튼이 화면 가운데에 보이게 됩니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/1.%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image4.png)

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/1.%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0/images/image5.png)

- 가운데 있는 버튼을 눌렀을 때 눌린 상태에서는 배경색이나 글자색이 바뀌는 것을 확인할 수 있습니다. 또한 onDraw와 on TouchEvent 메서드가 호출되면서 로그가 출력되는 것도 확인할 수 있습니다.

