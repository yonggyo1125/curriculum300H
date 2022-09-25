# 애니메이션 사용하기

- 화면을 구성하는 각각의 뷰에 애니메이션을 적용해 보겠습니다. 안드로이드는 애니메이션을 간편하게 적용할 수 있는 여러 가지 방법을 제공합니다. 그중에서 <b>트윈 애니메이션(Tweened Animation)</b>이가장 간단하면서 일반적인 방법으로 사용됩니다. 이는 이동, 확대/축소, 회전과 같이 일정한 패턴으로움직이는 애니메이션을 구현할 때 사용됩니다. 다음은 자주 사용하는 애니메이션 구현 방식을 그림으로 표현한 것입니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/4.%20%EC%95%A0%EB%8B%88%EB%A9%94%EC%9D%B4%EC%85%98/images/image1.png)

- 애니메이션이 어떻게 동작할지 정의한 정보는 XML로 만듭니다. 이렇게 만든 XML 정보는 자바 소스에서 애니메이션(Animation) 객체로 로딩한 후 뷰 객체의 startAnimation 메서드를 사용해서 애니메이션을 동작하게 만들 수 있습니다. 즉, 첫 번째 단계로 애니메이션이 어떻게 동작하는지를 XML로 정의하고, 두 번째 단계로 XML을 로딩하며 애니메이션 객체로 만듭니다. 그런 다음 세 번째 단계로 뷰에 애니메이션을 적용하여 동작시킵니다. 이렇게 동작시킬 수 있는 트윈 애니메이션의 대상과 애니메이션효과는 다음 표에 정리한 것처럼 구분할 수 있습니다.

<table>
	<thead>
		<tr>
			<th>구분</th>
			<th>이름</th>
			<th>설명</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td rowspan='2'>대상</td>
			<td>뷰</td>
			<td>View는 위젯이나 레이아웃을 모두 포함합니다.<br>예를 들어, 텍스트뷰나 리니어 레이아웃에 애니메이션을 적용할 수 있습니다.</td>
		</tr>
		<tr>
			<td>그리기 객체</td>
			<td>
				다양한 드로어블(Drawable)에 애니메이션을 적용할 수 있습니다.<br>
				ShapeDrawable은 캔버스에 그릴 도형을 지정할 수 있으며, BitmapDrawable은 비트맵 이미지를 지정할 수 있습니다.
			</td>
		</tr>
		<tr>
			<td rowspan='4'>효과</td>
			<td>위치 이동</td>
			<td>Translate로 정의한 액션은 대상의 위치를 이동시키는 데 사용되는 효과입니다.</td>
		</tr>
		<tr>
			<td>확대/축소</td>
			<td>Scale로 정의한 액션은 대상의 크기를 키우거나 줄이는 데 사용되는 효과입니다.</td>
		</tr>
		<tr>
			<td>회전</td>
			<td>Rotate로 정의한 액션은 대상을 회전시키는 데 사용되는 효과입니다.</td>
		</tr>
		<tr>
			<td>투명도</td>
			<td>Alpha로 정의한 액션은 대상의 투명도를 조절하는 데 사용되는 효과입니다.</td>
		</tr>
	</tbody>
</table>

- 트윈 애니메이션의 액션(Action) 정보는 XML 리소스로 정의하거나 소스 코드에서 직접 객체로 만들 수 있습니다. 애니메이션을 위한 XML 파일은 /app/res/anim 폴더의 밑에 두고 확장자를 xml로 해야 합니다. 이렇게 리소스로 포함된 애니메이션 액션 정의는 다른 리소스와 마찬가지로 빌드할 때 컴파일되어 설치 파일에 포함됩니다.

- 애니메이션을 적용해보기 위해 새로운 SampleTweenAnimation 프로젝트를 만듭니다. 이때 패키지 이름은 org.koreait.anim으로 입력합니다. 먼저 애니메이션 액션 정보를 만들기 위해 /app/res 폴더 안에 anim 폴더를 생성합니다. 애니메이션 액션 정보는 /app/res 폴더 안에 있어야 인식됩니다. 새로 만든 anim 폴더 안에 scale.xml 파일을 만듭니다. 그리고 다음과 같이 대상을 두 배로 확대하는 스케일 액션을 XML로 정의합니다.

#### SampleTweenAnimation>/app/res/anim/scale.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <scale
        android:duration="2500"
        android:pivotX="50%"
        android:pivotY="50%"
        android:fromXScale="1.0"
        android:fromYScale="1.0"
        android:toXScale="2.0"
        android:toYScale="2.0"
        />
</set>
```

- 자동으로 만들어진 \<set\> 태그 안에 \<scale\> 태그를 추가했습니다. 각각의 애니메이션 액션이 갖는 공통적인 속성으로는 여러 가지가 있지만 그중에서 시작 시간과 지속 시간이 대표적입니다. 시작 시간은 startOffset, 지속 시간은 duration으로 정의됩니다. startOffset은 애니메이션이 시작한지 얼마 후에 이 액션이 수행될 것인지를 알 수 있도록 합니다. startOffset을 지정하지 않으면 애니메이션은 바로 시작됩니다. duration은 애니메이션이 지속되는 시간으로 여기에서는 2.5초 동안 지속되도록 되어 있습니다.

- \<scale\> 태그는 대상을 확대하거나 축소할 때 사용되는데, 크기를 변경하려는 축의 정보는 X축과 Y축에 대해 각각 pivotX와 pivotY로 지정합니다. fromXScale과 fromYScale은 시작할 때의 확대/축소 비율이며, toXScale과 toYScale은 끝날 때의 확대/축소 비율입니다. 여기서는 1.0으로 시작하여 2.0으로 끝나므로 원래의 크기에서 시작해서 두 배의 크기로 확대되는 애니메이션이 수행됩니다.

- 이번에는 activity_main.xml 파일을 열고 텍스트뷰는 삭제하고 버튼을 하나 추가합니다. 최상위 레이아웃은 LinearLayout으로 변경하고 orientation 속성 값은 vertical로 합니다. 버튼을 하나 추가하고 layout_width 속성 값은 wrap_content로 설정합니다. layout_weight의 값은 삭제하고 가로 방향의 가운데에 올 수 있도록 layout_gravity 속성의 값을 center_horizontal로 지정합니다. 버튼에는 '확대'라는 글자가 표시되도록 text 속성을 설정합니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/4.%20%EC%95%A0%EB%8B%88%EB%A9%94%EC%9D%B4%EC%85%98/images/image2.png)

- 화면에 애니메이션의 대상이 되는 버튼을 하나 추가했으니 이 뷰에 스케일 애니메이션을 적용해 보겠습니다. MainActivity.java 파일을 열고 다음과 같이 애니메이션을 적용하는 코드를 입력합니다.

#### SampleTweenAnimation>/app/java/org.koreait.anim/MainActivity.java

```java
package org.koreait.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
                // 리소스에 정의한 애니메이션 액션 로딩
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                // 뷰의 애니메이션 시작
                view.startAnimation(anim);
            }
        });
    }
}
```

- 버튼의 id 속성 값이 button이므로 findViewById 메서드로 버튼을 찾아 참조한 후 리스너 객체를 설정합니다. onClick 메서드 안을 보면 애니메이션을 로딩하여 적용하는 것을 볼 수 있습니다. XML 리소스에 정의된 애니메이션 액션 정보를 로딩하기 위해 <b>AnimationUtils</b> 클래스의 <b>loadAnimation</b> 메서드를 사용합니다.

```java
public static Animation loadAnimation (Context context, int id)
```

- 첫 번째 파라미터는 컨텍스트 객체이므로 여기서는 getApplicationContext를 사용하였고, 두 번째 파라미터는 XML 리소스에 정의된 애니메이션 액션의 id 값이므로 애니메이션 리소스 이름인 R.anim.scale이 사용됩니다. onClick 메서드로 전달되는 뷰 객체는 사용자가 클릭한 버튼 객체이므로 버튼을 눌렀을 때 뷰 객체의 startAnimation 메서드를 호출하면 버튼에 애니메이션 효과를 주게 됩니다. 앱을 실행하고 버튼을 누르면 다음 그림과 같이 버튼이 두 배 커졌다가 다시 원상태로 돌아가는 것을 볼 수 있습니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/4.%20%EC%95%A0%EB%8B%88%EB%A9%94%EC%9D%B4%EC%85%98/images/image3.png)

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/4.%20%EC%95%A0%EB%8B%88%EB%A9%94%EC%9D%B4%EC%85%98/images/image4.png)

- 그런데 애니메이션이 끝난 후 버튼이 원상태로 갑자기 바뀌는 것이 조금 부자연스럽게 보일 수 있습니다. 이 버튼이 원래의 상태로 자연스럽게 되돌아가게 하려면 어떻게 해야 할까요? 버튼을 원상태로 자연스럽게 돌아가도록 만드는 가장 간단한 방법은 애니메이션이 끝난 후 똑같은 애니메이션을 거꾸로 적용하는 것입니다. 즉, 두 개의 애니메이션이 연속으로 수행되도록 하나로 묶어두는 방법입니다.

- XML로 저장하는 애니메이션 액션은 여러 개의 효과를 하나로 묶어 동시에 수행되도록 만들 수 있습니다. 이때 \<set\> 태그를 사용하여 여러 애니메이션 액션을 포함시킵니다. 애니메이션 집합(Animation Set)으로 표현되는 여러 애니메이션의 묶음은 동시에 수행될 수도 있고 시작 시간의 설정에 따라 연속적으로 수행될 수도 있습니다. 다음은 두 개의 스케일 효과를 하나의 애니메이션 액션으로 정의한 것입니다. 크기가 두 배로 커졌다가 다시 작아지는 애니메이션을 연속적으로 수행하도록 합니다. anim 폴더 안에 scale2.xml이라는 이름의 새로운 파일을 만들고 다음 코드를 입력합니다.  

> 안드로이드 스튜디오의 프로젝트 창에서 anim 폴더 안에 있는 scale.xml 파일을 복사, 붙여넣기를 해서 scale2.xml 파일명만 수정하면 좀 더 쉽게 파일을 만들 수 있습니다.

#### SampleTweenAnimation>/res/anim/scale2.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 확대 애니메이션 액션 정의 -->
    <scale
        android:duration="2500"
        android:pivotX="50%"
        android:pivotY="50%"
        android:fromXScale="1.0"
        android:fromYScale="1.0"
        android:toXScale="2.0"
        android:toYScale="2.0"
        />

    <!-- 2.5초 후에 시작할 축소 애니메이션 액션 정의 -->
    <scale
        android:startOffset="2500"
        android:duration="2500"
        android:pivotX="50%"
        android:pivotY="50%"
        android:fromXScale="1.0"
        android:fromYScale="1.0"
        android:toXScale="0.5"
        android:toYScale="0.5"
        />
</set>
```

- \<set\> 태그로 묶여진 두 개의 스케일 애니메이션은 첫 번째 것이 2.5초 동안 버튼을 두 배로 확대하고 그 후에 2.5초 동안 반으로 축소하는 효과를 보여줍니다. activity_main.xml을 열고 XML 레이아웃에 버튼을 하나 더 추가합니다. 새로 추가한 버튼에는 '확대/축소' 글자가 표시되도록 하고 layout_width 속성 값은 wrap_content로 설정하고 layout_weight는 삭제합니다. 그리고 layout_gravity 속성 값은 center_horizontal로 설정합니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/4.%20%EC%95%A0%EB%8B%88%EB%A9%94%EC%9D%B4%EC%85%98/images/image5.png)

- 추가한 [확대/축소] 버튼에 새로 정의한 scale2.xml 파일의 애니메이션을 적용하기 위해 MainActivity.java 파일에 다음 코드를 추가합니다.

#### SampleTweenAnimation>/app/java/org.koreait.sampletweenanimation/MainActivity.java 

```java

... 생략

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	
        ... 생략

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 애니메이션 정의한 것 로딩하기
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale2);

                // 애니메이션 시작하기
                view.startAnimation(anim);
            }
        });
    }
}
```

- 앱을 실행한 후 하단의 버튼을 클릭하면 버튼이 커졌다가 다시 자연스럽게 작아지는 것을 확인할 수 있습니다. 이렇게 간단한 확대/축소 효과를 보여주는 트윈 애니메이션은 위치 이동, 회전, 투명도에도 적용할 수 있습니다. 

## 트윈 애니메이션으로 위치 이동 액션 효과 주기

- 위치 이동은 대상의 위치를 변경하는 것으로 한 곳에서 다른 곳으로 부드럽게 움직이는 효과를 낼 수있습니다. 위치 이동 액션은 \<translate\> 태그를 사용하여 정의하는데 시작 위치는 fromXDelta와 fromYDelta, 종료 위치는 toXDelta와 toYDelta라는 이름을 가진 속성으로 지정할 수 있습니다. 다음은 대상의 크기만큼 왼쪽으로 이동시키는 액션을 XML로 정의한 것입니다.

####  SampleTweenAnimation>/app/res/anim/translate.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXDelta="0%p"
    android:toXDelta="-100%p"
    android:duration="20000"
    android:repeatCount="-1"
    android:fillAfter="true"
    />
```

- <b>fromXDelta</b> 속성이 0% 이므로 시작 위치의 X 좌표는 원래 위치의 X 좌표가 됩니다. <b>toXDelta</b> 속성이-100%이므로 대상의 크기만큼 왼쪽으로 이동하게 됩니다. 지속 시간은 duration의 값이 20000이므로 20초가 되며 repeatCount 속성이 -1이므로 무한반복됩니다. 애니메이션이 끝난 후에 대상이 원래의 위치로 돌아오는 것을 막기 위해서는 <b>fillAfter</b> 속성을 true로 설정하면 됩니다.

## 트윈 애니메이션으로 위치 회전 액션 효과 주기

- 회전은 한 점을 중심으로 대상을 회전시키는 효과를 만드는 액션으로 시작 각도와 종료 각도를 지정할 수 있습니다. 예를 들어, 한 바퀴를 회전시키려고 한다면 fromDegrees 속성 값을 0으로 하고 toDegrees 속성 값을 360으로 하면 됩니다. 시계 반대 방향으로 회전시키고 싶을 경우에는 toDegrees 속성 값을 -360으로 설정합니다. 회전의 중심이 되는 점은 디폴트 값이 (0, 0)이므로 대상의 왼쪽 상단끝 지점이 됩니다. 만약 대상의 중앙 부분을 회전의 중심으로 만들고 싶다면 pivotX와 pivotY 속성의값을 지정하면 됩니다. 값의 단위는 좌표 값 또는 백분율(%)을 사용할 수 있습니다. 다음은 대상의 중심을 회전축으로 하여 10초 동안 시계 방향으로 한 바퀴 회전시키는 액션을 XML로 정의한 것입니다.

#### SampleTweenAnimation>/app/res/anim/rotate.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromDegrees="0"
    android:toDegrees="360"
    android:pivotX="50%"
    android:pivotY="50%"
    android:duration="10000"
    />
```

- duration 속성 값이 10000으로 설정되어 있으므로 10초 동안 애니메이션이 진행된 후 원래대로 돌아오게 됩니다. 애니메이션의 실행 결과를 확인하려면 앞에서 작성한 MainActivity.java의 loadAnimation 메서드에 전달하는 값만 R.anim.rotate로 수정하면 됩니다. 이후 액션도 모두 마찬가지의 방법으로 실습하세요.

## 트윈 애니메이션으로 스케일 액션 효과 주기

- 스케일은 대상을 크게 하거나 작게 할 수 있는 액션으로 확대/축소의 정도는 대상이 갖는 원래 크기에 대한 비율로 결정됩니다. 예를 들어 1.0이라는 값은 원래 크기와 같다는 의미이며, 2.0은 원래 크기의 두 배로 크게 만든다는 의미입니다. X축으로 늘리거나 줄이고 싶으면 fromXScale과 toXScale 속성을 설정하고 Y축으로 늘리거나 줄이고 싶으면 fromYScale과 toYScale 속성 값을 설정합니다. 확대/축소의 경우에도 증심이 되는 점을 지정할 수 있는데 앞에서와 마찬가지로 pivotX와 pivotY 속성 값을 이응하면 됩니다. 이 액션은 앞에서 사용해 보았으므로 XML 코드는 scale.xml 파일을 참조하면 됩니다.

## 트윈 애니메이션으로 투명도 액션 효과 주기

- 투명도를 결정하는 알파 값도 뷰나 그리기 객체의 투명도를 점차적으로 바꿀 수 있는 애니메이션 액션으로 정의할 수 있습니다. 알파 값을 이용한 투명도 변환은 대상을 천천히 보이게 하거나 보이지 않게 하고 싶을 때 또는 하나의 뷰 위에 다른 뷰를 겹쳐 보이게 할 때 사용합니다. 알파 값의 범위는 0.0부터 1.0까지이며 0.0은 알파 값이 0일 때와 마찬가지이므로 완전히 투명한 상태(뷰나 그리기 객체가 보이지 않음)이며 1.0은 알파 값이 1일 때와 마찬가지이므로 완전히 보이는 상태(투명 효과가 적용되지 않음)입니다. 다음은 10초 동안 대상을 천천히 보이게 만드는 액션을 XML로 정의한 것입니다.

#### SampleTweenAnimation>/app/res/anim/alpha.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromAlpha="0.0"
    android:toAlpha="1.0"
    android:duration="10000"
    />
```

## 트윈 애니메이션으로 속도 조절하기

- 애니메이션 효과가 지속되는 동안 빠르거나 느리게 효과가 진행되도록 만드는 방법은 인터폴레이터(Interpolator)를 사용하면 됩니다. 인터폴레이터는 R.anim에 미리 정의된 정보를 사용해서 설정할 수있는데 다음과 같은 대표적인 인터폴레이터를 사용할 수 있습니다.

|속성|설명|
|----|-----|
|accelerate_interpolator|애니메이션 효과를 점점 빠르게 나타나도록 만듭니다.|
|decelerate_interpolator|애니메이션 효과를 점점 느리게 나타나도록 만듭니다.|
|accelerate_decelerate_interpolator|애니메이션 혀과를 점점 빠르다가 느리게 나타나도록 만듭니다.|
|anticipate_interpolator|애니메이션 효과를 시작 위치에서 조금 뒤로 당겼다가 시작하도록 만듭니다.|
|overshoot_interpolator|애니메이션 혀과를 종료 위치에서 조금 지나쳤다가 종료되도록 만듭니다.|
|aniticipate_overshoot_interpolator|애니메이션 효과를 시작 위치에서 조금 뒤로 당겼다가 시작한 후 종료 위치에서 조금 지나쳤다가 종료되도록 만듭니다.|
|bounce_interpolator|애니메이션 효과를 종료 위치에서 튀도록 만듭니다.|

- 이런 정보들은 각각의 액션에 설정할 수도 있고 애니메이션 집합에 설정할 수도 있습니다. 만약 각각의 액션에 다른 인터폴레이터를 설정하는 경우에는 shareInterpolator 속성을 false로 할 수 있습니다.

- 이렇게 리소스로 정의된 애니메이션 정보들은 자바 코드에서 new 연산자로 직접 만드는 것도 가능합니다. 그리고 각각의 액션별로 만들 수 있는 객체들은 다음과 같습니다.

<table>
	<tr>
		<th>위치 이동</th>
		<td>translate → TranslateAnimation</td>
	</tr>
	<tr>
		<th>회전</th>
		<td>rotate →  RotateAnimation</td>
	</tr>
	<tr>
		<th>확대/축소</th>
		<td>scale →  ScaleAnimation</td>
	</tr>
	<tr>
		<th>투명도</th>
		<td>alpha →  AlphaAnimation</td>
	</tr>
	<tr>
		<th>애니메이션 집합</th>
		<td>set →  AnimationSet</td>
	</tr>
</table>

- 애니메이션은 버튼이 눌려졌을 때 시작해야 하는 경우도 있지만 화면이 사용자에게 보이는 시점에 시작해야 하는 경우도 있습니다. 사용자에게 화면이 표시되는 시점에 애니메이션이 시작하도록 만들고 싶다면 애니메이션의 시작점은 <b>onWindowFocusChanged</b> 메서드가 호출되는 시점 즉, 윈도우가 포커스를 받는 시점이 되어야 합니다. 따라서 <b>onWindowFocusChanged</b> 메서드 내에서 파라미터로 전달되는 <b>hasFocus</b> 변수의 값이 true일 경우에 각각의 애니메이션 객체에 대해 start 메서드를 호출함으로써 애니메이션이 시작되도록 하면 됩니다. 윈도우가 다른 윈도우에 의해 가려지거나 할 때는 hasFocus 변수의 값이 false가 되므로 이때는 애니메이션 객체의 reset 메서드를 호출하여 초기 상태로 되돌릴 수 있습니다.

- 애니메이션이 언제 시작했는지 또는 끝났는지에 대한 정보는 AnimationListener 객체를 설정하면 알 수 있습니다. 애니메이션 객체에 리스너를 설정하면 애니메이션이 진행되는 상태에 따라 다음과 같은 메서드가 자동으로 호출됩니다.


|메서드|설명|
|-----|-----|
|public void onAnimationStart(Animation animation)|애니메이션이 시작되기 전에 호출됩니다.|
|public void onAnimationEnd(Animation animation)|애니메이션이 끝났을 때 호출됩니다.|
|public void onAnimationRepeat(Animation animation)|애니메이션이 반복될 때 호출됩니다.|

- 애니메이션은 위젯에 적용할 수도 있고 레이아웃에 적용할 수도 있습니다. 