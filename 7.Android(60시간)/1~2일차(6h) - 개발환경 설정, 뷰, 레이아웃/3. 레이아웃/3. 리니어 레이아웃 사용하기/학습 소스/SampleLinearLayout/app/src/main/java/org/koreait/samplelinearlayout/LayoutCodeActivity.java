package org.koreait.samplelinearlayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class LayoutCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // new 연산자로 리니어 레이아웃을 만들고 방향을 설정
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        // new 연산자로 레이아웃 안에 추가될 뷰들에 설정할 파라미터 생성
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // 버튼에 파라미터 설정하고 레이아웃에 추가
        Button button1 = new Button(this);
        button1.setText("Button1");
        button1.setLayoutParams(params);
        mainLayout.addView(button1);

        // 새로 만든 레이아웃을 화면에 설정
        setContentView(mainLayout);
    }
}