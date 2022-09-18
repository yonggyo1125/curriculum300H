# 뷰페이저 만들기

- 뷰페이저는 손가락으로 좌우 스크롤하여 넘겨볼 수 있는 기능을 제공합니다. 만약 화면 전체를 뷰페이저로 채운다면 좌우 스크롤을 통해 화면을 넘겨볼 수 있게 되죠. 화면 일부분만 차지하고 있어도 그 부분에서만 좌우 스크롤이 동작합니다. 뷰페이저는 그 안에 프래그먼트를 넣을 수 있고 좌우 스크롤로 프래그먼트를 전환하게 됩니다. 뷰페이저는 내부에서 어댑터라는 것과 상호작용하게 되어 있는데 이것은 뷰페이저가 여러 개의 아이템 중에 하나를 보여주는 방식으로 동작하기 때문입니다. 어댑터에 대해서는 나중에 리싸이클러뷰를 다룰 때 자세하게 설명합니다. 일단 여기에서는 어댑터를 사용한다고 이해하고 그 안에 코드가 어떻게 들어가는지를 유심히 살펴보기 바랍니다.

- 뷰페이저의 동작 방식

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/3.%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80/images/image1.png)

- 뷰페이저를 만들어보기 위해 SamplePager라는 이름의 새로운 프로젝트를 만듭니다. 패키지 이름은 org.koreait.pager로 입력합니다. 새로운 프로젝트 창이 만들어지면 디자인 화면에서 최상위 레이아웃을 LinearLayout으로 변경하고 orientation 속성 값은 vertical로 설정합니다. 기존에 있던 텍스트뷰는 삭제한 후 좌측 상단 팔레트에서 버튼 하나를 화면에 끌어다 놓고 Containers 폴더 안에 들어 있는 ViewPager도 끌어다 놓습니다.

- 뷰페이저는 아래쪽 화면을 꽉 채우도록 layout_width와 layout_height 속성이 모두 match_parent로 설정되어 있습니다. 뷰페이저의 id 속성 값으로 pager를 입력합니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/3.%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80/images/image2.png)

- 이제 소스 파일에 뷰페이저를 만드는 코드를 입력합니다. 먼저 이전에 만들었던 SampleTab 예제에서 (fragment1.xml, fragment2.xml, fragment3.xml)과 세 개의 소스 파일(Fragment1, Fragment2, Fragment3)을 복사해서 SamplePager에 붙여 넣습니다. 
- 프래그먼트 세 개를 추가했다면 MainActivity.java 파일을 열고 onCreate 메서드 아래쪽에 어댑터 클래스를 추가합니다. Fragment나 FragmentManager 클래스는 모두 androidx.fragment.app 패키지의 것을 import하도록 합니다.


#### Sample Pager>/app/java/org.koreait.samplepager/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
```

- MyPagerAdapter 클래스는 내부 클래스로 만들어졌으며 FragmentStatePagerAdapter를 상속했습니다. 어댑터는 뷰페이저에 보여줄 각 프래그먼트를 관리하는 역할을 하며, 뷰페이저에 설정하면 서로 상호작용하면서 화면에 표시해주게 됩니다. MyPagerAdapter 안에는 프래그먼트들을 담아둘 ArrayList 객체를 만들었으며 그 안에는 프래그먼트 객체를 넣어둘 것입니다. ArrayList 객체 안에 프래그먼트를 추가할 수 있는 addItem 메서드나 프래그먼트를 가져갈 수 있는 getItem 메서드가 들어 있으며, getCount 메서드를 사용해서 프래그먼트의 개수를 확인할 수도 있습니다.

- 뷰페이저는 어댑터와 상호작용하면서 getCount 메서드로 몇 개의 프래그먼트가 들어 있는지 확인합니다. 그런 다음 화면의 상태에 따라 해당하는 프래그먼트를 꺼내와 보여주게 됩니다. 이 어댑터를 사용할 수 있도록 다음 코드를 onCreate 메서드에 추가합니다.

#### Sample Pager>/app/java/org.koreait.samplepager/MainActivity.java

```java

... 생략 

public class MainActivity extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        Fragment1 fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        Fragment2 fragment2 = new Fragment2();
        adapter.addItem(fragment2);

        Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);
    }

   ... 생략
   
}
```

- XML 레이아웃에 들어 있는 ViewPager 객체를 findViewById 메서드로 찾아 참조한 후 클래스 안에 선언한 변수에 할당했습니다. 그리고 setOffscreenPageLimit 메서드로 미리 로딩해 놓을 아이템의 개수를 세 개로 늘렸습니다. 뷰페이저는 어댑터가 가지고 있는 아이템 중에서 몇 개를 미리 로딩해 두었다가 좌우 스크롤할 때 빠르게 보여줄 수 있습니다. 이 값이 처음에는 3보다 적게 설정되어 있기 때문에 여기서는 3으로 설정했습니다.

- 앞에서 복사한 세 개의 프래그먼트는 객체로 만든 후 어댑터 객체에 추가했습니다. addItem 메서드를 호출하면서 프래그먼트 객체를 파라미터로 넘겨주면 어댑터 안에 들어 있는 ArrayList에 추가된다.는 점을 이해해야 합니다. 마지막 부분을 보면 ViewPager 객체에 어댑터 객체를 설정하고 있습니다. setAdapter 메서드를 호출하면 어댑터 객체가 설정되고 이때부터 뷰페이저는 어댑터에 있는 프래그먼트들을 화면에 보여줄 수 있게 됩니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/3.%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80/images/image3.png)

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/3.%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80/images/image4.png)

- 만약 사용자가 손가락으로 화면을 전환하지 않고 코드에서 전환시키고 싶다면 뷰페이저 객체의 setCurrentItem 메서드를 사용하면 됩니다. activity_main.xml에 추가했던 뷰페이저 위의 버튼 글자를 '두 번째 화면 보여주기'로 변경하고 MainActivity.java 파일의 onCreate 메서드 안에 버튼 이벤트를 처리하는 코드를 추가합니다.


#### Sample Pager>/app/java/org.koreait.samplepager/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(1);
            }
        });

        ... 생략 
		
    }

	... 생략 
}
```

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/3.%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80/images/image5.png)

﻿- 이렇게 뷰페이지를 만들어 보았습니다. 그런데 지금 보고 있는 화면이 전체 프래그먼트들 중에서 몇 번째 것인지 알기는 어렵습니다. 그래서 위나 아래쪽에 전체 아이템의 개수와 현재 보고 있는 아이템이 어떤 것인지를 보여줄 필요가 있습니다. 이런 역할을 하는 것이 타이틀스트립(TitleStrip)입니다. 타이틀스트립 외에 탭스트립을 사용할 수도 있는데 이 경우에는 탭 모양으로 아이템을 구분하여 보여줍니다.

- 타이틀스트립을 추가해 보겠습니다. activity_main.xml 파일을 열고 \<ViewPager\> 태그 안에 \<PagerTitleStrip\> 태그를 추가합니다. PagerTitleStrip 객체도 support 패키지 안에 들어 있어 패키지 이름까지 모두 넣어주어야 합니다. 그리고 ViewPager 태그는 끝 태그가 없었는데 그 안에 PagerTitleStrip 태그가 들어가면서 시작 태그와 끝 태그가 분리된다는 점도 잘 살펴봐야 합니다.

#### SamplePager>/app/res/layout/activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"

	... 생략

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <androidx.viewpager.widget.PagerTitleStrip
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="top"
            android:background="#55cedf"
            android:textColor="#FFFFFF"
            android:paddingTop="5dp"
            android:paddingBottom="5dp">
        </androidx.viewpager.widget.PagerTitleStrip>

    </androidx.viewpager.widget.ViewPager>
</LinearLayout>
```

- layout_gravity 속성의 값이 top으로 되어 있어 타이틀스트립이 뷰페이저의 위쪽에 보이게 됩니다. XML 레이아웃에 PagerTitleStrip을 추가했으니 이제 MainActivity.java 파일을 열고 MyPagerAdapter 클래스 마지막에 getPageTitle 메서드를 추가합니다.

```java

... 생략

public class MainActivity extends AppCompatActivity {

	... 생략

    class MyPagerAdapter extends FragmentStatePagerAdapter {
	
		... 생략

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "페이지" + position;
        }
    }
}
```

- 앱을 실행하면 뷰페이저의 위쪽에 아이템으로 보이는 화면들을 구분할 수 있는 표시가 보이게 됩니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/3.%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80/images/image6.png)

