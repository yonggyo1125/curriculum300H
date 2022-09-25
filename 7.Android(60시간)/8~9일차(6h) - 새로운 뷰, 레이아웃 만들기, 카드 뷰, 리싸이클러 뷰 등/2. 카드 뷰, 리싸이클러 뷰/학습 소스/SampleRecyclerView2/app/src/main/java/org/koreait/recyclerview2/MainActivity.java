package org.koreait.recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰에 GridLayoutManager를 레이아웃 매니저로 설정하기
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PersonAdapter();

        adapter.addItem(new Person("김민수", "010-1000-1000"));
        adapter.addItem(new Person("김하늘", "010-2000-2000"));
        adapter.addItem(new Person("홍길동", "010-3000-3000"));

        // 리싸이클러뷰에 어댑터 설정하기
        recyclerView.setAdapter(adapter);
        
        // 어댑터에 리스너 설정하기
        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                // 아이템 클릭 시 어댑터에서 해당 아이템의 Person 객체 가져오기
                Person item = adapter.getItem(position);
                Toast.makeText(getApplication(), "아이템 선택됨: " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}