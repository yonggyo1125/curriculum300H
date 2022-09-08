package org.koreait.sampledialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showMessage();
            }
        });
    }

    private void showMessage() {
        // 대화상자를 만들기 위한 빌더 객체 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // 예 버튼 추가
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "예 버튼이 눌렸습니다.";
                textView.setText(message);
            }
        });

        // 취소 버튼 추가
        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "취소 버튼이 눌렸습니다.";
                textView.setText(message);
            }
        });

        // 아니오 버튼 추가
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "아니오 버튼이 눌렸습니다.";
                textView.setText(message);
            }
        });
        
        // 대화상자 객체 생성 후 보여주기
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}