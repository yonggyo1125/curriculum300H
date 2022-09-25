package org.koreait.sliding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean isPageOpen = false;

    Animation translateLeftAnim;
    Animation translateRightAnim;

    LinearLayout page;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);

        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        // 애니메이션 리스너 설정하기
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPageOpen) {
                    page.startAnimation(translateRightAnim);
                } else {
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeftAnim);
                }
            }
        });
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        // 애니메이션이 끝났을 때 호출되는 메서드 안에 코드 넣기
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                page.setVisibility(View.INVISIBLE);

                button.setText("Open");
                isPageOpen = false;
            } else {
                button.setText("Close");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationStart(Animation animation) {}

        @Override
        public void onAnimationRepeat(Animation animation) {}
    }
}