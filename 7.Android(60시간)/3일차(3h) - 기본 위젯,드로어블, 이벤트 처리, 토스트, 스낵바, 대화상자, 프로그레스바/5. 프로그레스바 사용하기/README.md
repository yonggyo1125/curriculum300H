# 프로그레스바 사용하기

- 어떤 일의 진행 상태를 중간 중간 보여줄 수 있는 가장 좋은 방법 중 하나가 프로그레스바입니다. 프로그레스바는 작업의 진행 정도를 표시하거나 작업이 진행 중임을 사용자에게 알려줍니다. 다음은 대표적인 두 가지 형태의 프로그레스바를 표로 정리한 것입니다.

|속성|설명|
|----|-------|
|막대모양|작업의 진행 정도를 알려줄 수 있도록 막대모양으로 표시합니다.<br>style 속성 값을 '?android-attr/progressBarStyleHorizontal'로 설정합니다.|
|원 모양|작업이 진행 중임을 알려줍니다.<br>원 모양으로 된 프로그레스바가 반복적으로 표시됩니다.|

- XML 레이아웃에 프로그레스바를 추가할 때는 \<ProgressBar\> 태그가 사용되는데, 프로그레스바가 갖는 값의 최대 범위는 max 속성으로 설정하고 현재 값은 progress 속성으로 설정합니다. 예를 들어, 값의 최대 범위가 100이면 max 값을 100으로 설정하고 현재 진행률이 50%라면 max 값이 100이므로 progress 값을 50으로 설정하면 됩니다. 진행률이 변경되면 progress 속성으로 설정되었던 값을 바꾸면 됩니다. 자바 코드에서 프로그레스바의 현재 값을 바꿀 때 사용하는 대표적인 메서드들은 다음과 같습니다.

```java 
void setProgress (int progress)
void incrementProgressBy (int diff)
```

- setProgress 메서드는 정수 값을 받아 프로그레스바의 현재 값으로 설정합니다. incrementProgressBy 메서드는 현재 설정되어 있는 값을 기준으로 값을 더하거나 뺄 때 사용합니다. 프로그레스바는 항상 보일 필요가 없으므로 화면에서 차지하는 공간을 줄일 수 있도록 타이틀바에 프로그레스바를 표시할 수도 있습니다. 이 기능은 윈도우 속성으로 정의되어 있으므로 다음과 같은 메서드를 사용해야 합니다.

```java
requestWindowFeature(Window.FEATURE_PROGRESS);
```

- 타이틀 부분에 표시되는 프로그레스바는 범위를 따로 지정할 수 없으며 디폴트 값으로는 0부터 10000 사이의 값을 가질 수 있습니다. 따라서 진행률이 50%인 경우에는 프로그레스바의 현재 값을 5000으로 설정해야 합니다. 타이틀바에 프로그레스바를 보여주는 방식은 화면의 공간을 절약하는 매우 직관적인 방식이긴 하지만 앱을 만들 때는 타이틀 부분을 보이지 않게 설정하는 경우가 많으므로 사용할 수 없는 경우도 생긴다는 점에 주의해야 합니다.


- 프로그레스바를 사용해 보기 위해 새로운 SampleProgress 프로젝트를 만듭니다. 그리고 activity_main.xml 파일을 연 후 디자인 화면의 Component Tree에서 최상위 레이아웃을 LinearLayout으로 변경합니다. LinearLayout의 orientation 속성은 vertical로 변경하고 텍스트뷰는 삭제합니다.

- 좌측 상단의 팔레트에서 Widgets 폴더 안에 들어 있는 ProgressBar (Horizontal)를 찾아 화면에 끌어다 놓습니다. 그리고 max 속성의 값은 100으로 설정합니다. 그런 다음 프로그레스바 아래에 두 개의 버튼을 나란히 추가하기 위해서 LinearLayout(horizontal)을 추가한 후 버튼은 각각 '보여주기'와 '닫기' 글자가 보이도록 text 속성을 설정합니다. 이렇게 하면 다음과 같은 XML 레이아웃이 만들어집니다.

- 버튼을 나란히 추가하기 위해서 LinearLayout(horizontal)을 추가한 후 버튼은 각각 '보여주기'와 '닫기' 글자가 보이도록 text 속성을 설정합니다. 이렇게 하면 다음과 같은 XML 레이아웃이 만들어집니다

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/5.%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/images/image1.png)

- 첫 번째 버튼은 프로그레스바를 대화상자로 보여주고 두 번째 버튼은 그 대화상자를 없애주는 역할을 하도록 만들 것입니다. 프로그레스바를 XML 레이아웃에 추가하려면 단순히 태그를 \<ProgressBar\>로 만들어 주면 됩니다. XML 레이아웃의 [Code] 아이콘을 눌러서 \<ProgressBar\> 태그에 사용된 style 속성을 보면 막대 모양의 프로그레스바로 설정하고 있습니다. 그 값이 ?android:attr/progressBarStyleHorizontal로 되어 있어 조금 복잡하게 보이지만 팔레트에서 끌어다 놓을 때는 자동으로 생성됩니다. max값은 프로그레스바의 최댓값을 설정하는 데 사용됩니다. 이 XML 레이아웃을 사용하는 메인 액티비티의 코드는 MainActivity.java 파일을 열고 다음과 같이 입력합니다. 


#### SampleProgress>/app/java/org.koreait.sampleprogress/MainActivity.java

```java
package org.koreait.sampleprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        // 프로그레스바 객체 참조하여 설정하기
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(80);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 프로그레스 대화상자 객체 만들고 설정하기
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("데이터를 확인하는 중입니다.");

                dialog.show();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 프로그레스 대화상자 없애기
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
    }
}
```

- 이 코드에서는 XML 레이아웃에 들어 있는 프로그레스바를 findViewById 메서드로 찾은 후 그 값을 80으로 설정합니다. 버튼을 클릭했을 때는 프로그레스바대화상자가 표시되도록 합니다. 멈추지않는 프로그레스바를 대화상자 안에서 보여주려면 ProgressDialog 객체를 하나 만들고 그 스타일을 ProgressDialog.STYLE_SPINNER로 설정합니다. 이렇게 만든 ProgressDialog 객체는 show메서드를 호출하면 화면에 표시됩니다. ProgressDialog 객체를 생성할 때는 Context 객체가 파라미터로 전달되어야 하는데 액티비티인 MainActivity 객체를 전달하기 위해 파라미터를 MainActivity.this로 지정했습니다. 프로그레스 대화상자가 보이는 영역 밖을 터치하면 프로그레스바는 없어집니다. 
- 그러나 어떤 이벤트가 발생했을 때 대화상자를 보이지 않게 하고 싶다면 dismiss 메서드를 호출하면 됩니다. [닫기] 버튼은 화면에 표시된 ProgressDialog를 닫는 dismiss 메서드를 호출합니다. 다음은 이 앱을 실행한 화면을 보여주고 있습니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/5.%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/images/image2.png)

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/5.%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0/images/image3.png)

- 표시되면 [닫기] 버튼을 누를 수 없으므로 대화상자 이외의 화면 영역 또는 시스템 [BACK] 버튼을 눌러야 이전 화면으로 돌아갈 수 있습니다.
