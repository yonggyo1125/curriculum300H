# 스피너 사용하기

- 리스트나 격자 모양으로 여러 아이템을 보여주는 경우가 많지만 그 외에도 여러 아이템 중에서 하나를 선택하는 전형적인 위젯으로 스피너(Spinner)를 들 수 있습니다. 스피너는 일반적으로 윈도우에서 콤보박스로 불립니다. 윈도우에서 콤보박스를 누르면 그 아래쪽에 작은 창이 나타나는데 그 안에 들어있는 여러 데이터 중의 하나를 선택하도록 되어 있습니다. 하지만 아이폰이나 안드로이드 단말에서는 손가락으로 쉽게 터치할 수 있도록 별도의 창으로 선택할 수 있는 데이터 아이템들이 표현됩니다. 스피너는 \<Spinner\> 태그를 사용해 XML 레이아웃에 추가한 후 사용할 수 있습니다.

- 새로운 SampleSpinner 프로젝트를 만듭니다. 패키지 이름은 org.koreait.spinner로 입력하고 프로젝트 창이 만들어지면 activity_main.xml 파일을 엽니다. 디자인 화면에서 추가할 때는 최상위 레이아웃을 LinearLayout으로 변경하고 orientation 속성 값은 vertical로 설정합니다. 그다음 팔레트에서 텍스트뷰 하나를 끌어다 놓고 Container 폴더 안에 있는 Spinner를 끌어다 놓습니다. 스피너의 id 값은 spinner로 설정하고 텍스트뷰의 text 속성 값은 '선택한 아이템'으로 설정합니다. textSize 속성 값은 30sp로 설정합니다.

#### SampleSpinner>/app/java/org.koreait.spinner/MainActivity.java 

```java
package org.koreait.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    String[] items = { "mike", "angel", "crow", "john", "ginnie", "sally", "cohen", "rice" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter); // 스피너에 어댑터 설정하기

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // 스피너에 리스너 설정하기
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                textView.setText(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("");
            }
        });
    }
}
```

- onCreate 메서드 안을 보면 텍스트뷰와 스피너 객체를 findViewById 메서드로 찾아낸 후 클래스 안에 선언된 변수에 할당해 두었습니다. 이렇게 하면 MainActivity 클래스 안에 있는 어떤 메서드에서도 접근할 수 있습니다. 이 텍스트뷰는 사용자가 스피너의 한 아이템을 선택했을 때 선택한 값을 보여주기 위해 추가한 것으로 아이템이 하나 선택되면 onItemSelected 메서드가 자동으로 호출됩니다. 그리고 선택된 값은 setText 메서드를 사용해 텍스트로 표시합니다. 스피너 객체가 아이템 선택 이벤트를 처리할 수 있도록 사용하는 리스너는 OnItemSelectedListener입니다.

- 스피너 객체도 선택 위젯이므로 setAdapter 메서드의 파라미터로 어댑터 객체를 전달해야 합니다. 그런데 리싸이클러뷰를 만들 때는 어댑터를 직접 정의했었는데, 여기에서는 정의하지 않고 두 줄의 코드만 추가했습니다. 그 이유는 API에서 제공하는 기본 어댑터들이 있기 때문입니다. 여기에서 사용한 어댑터는 <b>ArrayAdapter</b>로 배열로 된 데이터를 아이템으로 추가할 때 사용합니다. ArrayAdapter 객체를 만들 때는 <b>simple_spinner_item</b>이라는 레이아웃을 지정합니다. 이 레이아웃도 스피너를 간단하게 사용할 수 있도록 API에서 제공하는 레이아웃입니다. 스피너는 항목을 선택하기 위한 창이 따로 있기 때문에 항목을 선택하는 창을 위한 레이아웃도 설정해주어야 합니다. 이때 사용하는 메서드가 <b>setDropDownViewResource</b> 메서드입니다. <b>ArrayAdapter</b>를 만들 때 사용한 생성자를 보면 다음과 같습니다.

```java
public ArrayAdapter (Context context, int textViewResourceId, T[] objects)
```

- 첫 번째 파라미터는 Context 객체이므로 액티비티인 this를 전달하면 됩니다. 두 번째 파라미터는 뷰를 초기화할 때 사용되는 XML 레이아웃의 리소스 ID 값으로 이 코드에서는 <b>android.R.layout.simple_spinner_item</b>을 전달하였습니다. 이 레이아웃은 문자열을 아이템으로 보여주는 단순 스피너 아이템의 레이아웃이라고 보면 됩니다. 이 레이아웃 안에는 텍스트뷰 객체가 들어 있습니다. 세 번째 파라미터는 아이템으로 보일 문자열 데이터들의 배열입니다. 여기에서는 변수로 정의한 items이 전달되었습니다. <b>setDropDownViewResource</b> 메서드는 스피너의 각 아이템들을 보여줄 뷰에 사용되는 레이아웃을 지정하는 데 사용되며 안드로이드에서 미리 정의한 리소스인 <b>android.R.layout.simple_spinner_dropdown_item</b> 값을 전달하면 가장 단순한 형태의 뷰가 보이게 됩니다. 다음은 이 앱을 실행한 화면입니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/3.%20%EC%8A%A4%ED%94%BC%EB%84%88/images/image1.png)

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/8~9%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%83%88%EB%A1%9C%EC%9A%B4%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EB%A7%8C%EB%93%A4%EA%B8%B0%2C%20%EC%B9%B4%EB%93%9C%20%EB%B7%B0%2C%20%EB%A6%AC%EC%8B%B8%EC%9D%B4%ED%81%B4%EB%9F%AC%20%EB%B7%B0%20%EB%93%B1/3.%20%EC%8A%A4%ED%94%BC%EB%84%88/images/image2.png)

- 메인 화면의 스피너에는 기본 값인 'mike'가 선택되어 있고 텍스트뷰에도 그 값이 들어 있는 것을 볼 수있습니다. 이 스피너를 터치하면 새로운 뷰가 화면에 보이게 되는데 코드에서 문자열로 된 배열에 넣어둔 값들이 리스트 형태로 나타납니다. 이중 하나의 값을 선택하면 그 값이 스피너와 텍스트뷰에 보이도록 되어 있습니다. 스피너를 터치했을 때 보이는 것은 앞에서 다뤄 보았던 리싸이클러뷰와 비슷합니다. 하지만 이 리스트 모양은 앞 단락에서 보았던 것보다 훨씬 단순한 문자열로만 구성되어 있습니다.
