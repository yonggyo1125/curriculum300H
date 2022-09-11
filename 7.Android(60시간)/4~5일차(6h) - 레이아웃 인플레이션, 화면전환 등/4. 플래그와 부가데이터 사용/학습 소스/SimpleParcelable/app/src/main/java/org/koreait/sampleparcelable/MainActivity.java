package org.koreait.sampleparcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_MENU = 101;
    private static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                // SimpleData 객체 생성
                SimpleData data = new SimpleData(100, "Hello Android!");
                intent.putExtra(KEY_SIMPLE_DATA, data); // 인텐트에 부가 데이터로 넣기
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }
}