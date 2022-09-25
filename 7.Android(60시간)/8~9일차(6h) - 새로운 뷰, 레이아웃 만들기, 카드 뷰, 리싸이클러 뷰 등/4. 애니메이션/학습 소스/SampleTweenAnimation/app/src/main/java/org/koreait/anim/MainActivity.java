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