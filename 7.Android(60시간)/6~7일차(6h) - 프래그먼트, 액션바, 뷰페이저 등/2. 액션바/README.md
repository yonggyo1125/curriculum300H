# 액션바 사용하기

## 화면에 메뉴 기능 넣기

- 안드로이드와 아이폰의 차이점 중의 하나는 화면 아래쪽에 있는 [메뉴] 버튼과 [BACK] 버튼의 유무입니다. 아이폰의 아래쪽에는 버튼이 하나(홈 버튼)만 있거나 없지만 안드로이드의 화면 아래쪽에는 버튼이 세 개([홈] 버튼, [메뉴] 버튼, [BACK] 버튼) 있습니다. 그래서 안드로이드 단말은 [메뉴] 버튼을 눌렀을 때 숨어있던 메뉴가 보인다는 것을 자연스럽게 알 수 있습니다.

- 그런데 이렇게 시스템 [메뉴] 버튼을 눌렀을 때 숨어있던 메뉴가 보이도록 할 수도 있고 앱의 상단 타이틀 부분에 [메뉴] 버튼을 배치하고 그것을 눌렀을 때 메뉴가 보이도록 할 수도 있습니다. 이런 메뉴를 <b>옵션 메뉴(Option Menu)</b>라고 부릅니다. 그리고 옵션 메뉴와 다르게 입력상자를 길게 눌러 나타나는 '복사하기', '붙여넣기' 등의 팝업 형태의 메뉴는 <b>컨텍스트 메뉴(Context Menu)</b>라고 합니다. <b>옵션 메뉴는 각각의 화면마다 설정할 수 있으며 컨텍스트 메뉴는 각각의 뷰마다 설정할 수 있습니다.</b>

|속성|설명|
|----|--------|
|옵션 메뉴|시스템 [메뉴] 버튼을 눌렀을 때 나타나는 메뉴로 각 화면마다 설정할 수 있는 주요 메뉴입니다.|
|컨텍스트 메뉴|화면을 길게 누르면 나타나는 메뉴로 뷰에 설정하여 나타나게 할 수 있습니다. 텍스트뷰의 편집 상태를 바꾸거나 할때 사용합니다.|


- 옵션 메뉴는 액션바(Action Bar)에 포함되어 보이도록 만들어져 있습니다. 여기에서 액션바는 앱의 제목(Title)이 보이는 위쪽 부분을 말합니다. 옵션 메뉴와 컨텍스트 메뉴는 각각의 액티비티마다 설정할 수 있으므로 액티비티에 추가하고 싶은 경우에는 다음의 두 메서드를 다시 정의하여 메뉴 아이템을 추가합니다.

```java
public boolean onCreateOptionsMenu (Menu menu)
public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
```

- 즉, 두 메서드를 다시 정의하기만 하면 매우 쉽게 메뉴를 추가할 수 있습니다. 이 두 개의 메서드를 보면 Menu ContextMenu 객체가 전달되는 것을 알 수 있는데 이 객체의 add 메서드를 사용해서 메뉴 아이템을 추가하게 됩니다. 메뉴 아이템을 추가할 수 있는 대표적인 메서드들은 다음과 같습니다.

```java
MenuItem add (int groupId, int itemId, int order, CharSequence title)
MenuItem add (int groupId, int itemId, int order, int titleRes)
SubMenu addSubMenu (int titleRes)
```

- groupid 값은 아이템을 하나의 그룹으로 묶을 때 사용합니다. itemld는 아이템이 갖는 고유 ID 값으로, 아이템이 선택되었을 때 각각의 아이템을 구분할 때 사용할 수 있습니다. 아이템이 많아서 서브 메뉴로 추가하고 싶을 때는 addSubMenu 메서드를 사용합니다. 그런데 이렇게 코드에서 메뉴를 추가하는 것보다는 XML에서 메뉴의 속성을 정의한 후 객체로 로딩하여 참조하는 것이 더 간단합니다.

- 이제 메뉴 기능을 확인해보기 위해 새로운 SampleOptionMenu 프로젝트를 만듭니다. 이때 패키지 이름은 org.koreait.menu로 수정합니다. 새로운 프로젝트가 만들어지면 /app/res 폴더 안에 새로운 menu 폴더를 만들겠습니다. /app/res 폴더를 선택한 상태에서 마우스 오른쪽 버튼을 누르고 [New → Directory] 메뉴를 선택합니다. 새로운 폴더 이름을 입력하라는 대화상자가 보이면 menu를 입력하고 [OK] 버튼을 누릅니다. 새로 만들어진 /app/res/menu 폴더를 선택한 상태에서 마우스 오른쪽 버튼을 눌러 나타난 메뉴 중에서 [New → Menu resource file] 메뉴를 선택합니다. 이 폴더 안에는 메뉴를 정의하는 XML 파일이 만들어집니다. 대화상자가 보이면 File name에 menu_main.xml을 입력한 후 [OK] 버튼을 누릅니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/2.%20%EC%95%A1%EC%85%98%EB%B0%94/images/image1.png)

- 안드로이드 스튜디오는 /app/res/menu 폴더안에 메뉴를 위한 XML 파일이 만들어진다는 것을 미리 알고 있습니다. 따라서 메뉴를 위한 XML 파일은 반드시 menu 폴더 안에 들어 있어야 합니다. /app/res/drawable 폴더 안에는 학습 소스에 있는 세 개의 이미지 파일(menu_refresh.png, menu_search.png, menu_settings.png 파일)을 복사해서 넣어 놓습니다. 물론 여러분이 원하는 이미지를 넣어도 됩니다. 그리고 menu_main.xml 파일 안에는 다음과 같이 세 개의 \<item\> 태그를 추가합니다. 이때 \<menu\> 태그의 xmlns:app도 꼭 입력하세요. 그래야 \<item\> 태그의 showAsAction이 제대로 인식됩니다.


#### SampleOptionMenu>/app/res/menu/menu_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item android:id="@+id/menu_refresh"
        android:title="새로고침"
        android:icon="@drawable/menu_refresh"
        app:showAsAction="always" />

    <item android:id="@+id/menu_search"
        android:title="검색"
        android:icon="@drawable/menu_search"
        app:showAsAction="always" />

    <item android:id="@+id/menu_settings"
        android:title="설정"
        android:icon="@drawable/menu_settings"
        app:showAsAction="always" />
    
</menu>
```

- \<item\> 태그는 하나의 메뉴에 대한 정보를 담고 있습니다. <b>id</b> 속성은 각각의 메뉴를 구분하기 위해 사용되며 <b>title</b> 속성에 넣은 값은 메뉴에 표시되는 글자입니다. 아이콘으로 표시하고 싶을 때는 <b>icon</b> 속성에 이미지를 넣을 수 있습니다. <b>showAsAction</b> 속성은 이 메뉴를 항상 보이게 할 것인지 아니면 숨겨둘 것인지를 지정할 수 있습니다. 여기서는 always 값을 설정했으므로 메뉴 아이콘이 항상 보이게 됩니다. android:로 시작하는 속성은 기본 API에 포함된 속성이고 app로 시작하는 속성은 여러분의 프로젝트에 들어 있는 속성이라는 것을 다시 한 번 상기하면 좋습니다. 그리고 여러분의 프로젝트에 외부 라이브러리가 추가되어 있다면 그 외부 라이브러리에서 제공되는 속성도 app로 참조하면 추가할수 있습니다.

- 다음은 showAsAction 속성에 설정할 수 있는 값을 정리한 것입니다.

|showAsAction속성 값|설명|
|always|항상 액션바에 아이템을 추가하여 표시합니다.|
|never|액션바에 아이템을 추가하여 표시하지 않습니다(디폴트 값).|
|ifRoom|액션바에 여유 공간이 있을 때만 아이템을 표시합니다.|
|withText|title 속성으로 설정된 제목을 같이 표시합니다.|
|collapseActionView|아이템에 설정한 뷰(actionViewLayout 으로 설정한 뷰)의 아이콘만 표시합니다.|

- 그럼 이렇게 정의한 메뉴들은 언제 화면에 추가되는 것일까요?

- MainActivity.java에 재정의된 onCreateOptionsMenu 메서드는 액티비티가 만들어질 때 미리 자동호출되어 화면에 메뉴 기능을 추가할 수 있도록 합니다. 앞에서 만든 메뉴 XML 파일은 XML 레이아웃 파일처럼 소스 코드에서 인플레이션한 후 메뉴에 설정할 수 있습니다. 이때 메뉴를 위한 XML 정보를 메모리에 로딩하기 위해 메뉴 인플레이터 객체를 사용합니다. MainActivity.java 파일을 열어서MainActivity 클래스를 마우스 오른쪽 버튼으로 누릅니다. 팝업 메뉴가 보이면 [Generate... Override Methods...] 메뉴를 선택합니다. 재정의할 수 있는 대화상자가 보이면 <b>onCreateOptionsMenu</b>와 <b>onOptionsItemSelected</b> 메서드를 모두 선택한 후 [OK]를 눌러서 추가합니다. 추가한 코드는 다음과 같이 수정합니다.

#### SampleOptionMenu>/app/java/org.koreait.menu/MainActivity.java

```java
package org.koreait.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_search :
                Toast.makeText(this, "검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_settings :
                Toast.makeText(this, "설정 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
```

- 화면이 처음 만들어질 때 메뉴를 정해놓은 것이 아니라 화면이 띄워진 후에 메뉴를 바꾸고 싶다면 <b>onPrepareOptionsMenu</b> 메서드를 재정의하여 사용하면 됩니다. 이 메서드는 메뉴가 새로 보일 때마다 호출되므로 메뉴 항목을 추가하거나 뺄 수 있어 메뉴 아이템들을 변경할 수 있습니다. 특히 메뉴의 속성을 바꿀 수 있으므로 메뉴를 활성화하거나 비활성화하여 사용자에게 앱의 상태에 따라 메뉴를 사용하거나 사용하지 못하도록 만들 수도 있습니다.

- 메뉴를 선택했을 때 처리하는 방법도 아주 간단합니다. 사용자가 하나의 메뉴 아이템을 선택했을 때 자동 호출되는 <b>onOptionsItemSelected</b> 메서드를 다시 정의한 후 그 안에서 현재 메뉴 아이템의 id 값이무엇인지 확인하여 그에 맞는 기능을 하게 만들면 됩니다.

```java
boolean onOptionsItemSelected (MenuItem item)
```

- SampleOptionMenu 프로젝트 파일을 실행한 후 액션바에 표시된 메뉴를 선택하면 다음과 같이 토스트 메시지가 나타나는 것을 볼 수 있습니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/2.%20%EC%95%A1%EC%85%98%EB%B0%94/images/image2.png)

- 메뉴를 사용하는 화면은 다양하게 있을 수 있지만 앱의 설정이나 도움말 등의 항목을 메뉴로 만들어 추가하는 경우가 많습니다. 컨텍스트 메뉴는 어떤 뷰에 필요한 기능만을 모아 정의해 놓은 것으로 손가락으로 길게 눌렀을 때 보이게 됩니다. 옵션 메뉴를 액티비티에 등록하고 사용자가 옵션 메뉴를 선택했을 때 처리하기 위해 두 개의 메서드를 다시 정의한 것처럼 컨텍스트 메뉴도 두 개의 메서드를 다시 정의하면 사용할 수 있습니다. 컨텍스트 메뉴를 특정 뷰에 등록하고 싶을 때는 registerForContextMenu메서드를 사용합니다.

```java
void Activity.registerForContextMenu (View view)
```

- 이 메서드로 컨텍스트 메뉴를 등록하면 각각의 메뉴 아이템을 선택했을 때 onContextItemSelected 메서드가 호출되므로 이 메서드의 파라미터로 전달되는 MenuItem 객체를 사용해 선택된 메뉴 아이템의 정보를 확인한 후 처리할 수 있습니다. 사용 방법이 옵션 메뉴와 거의 유사하므로 필요할 때 뷰에 기능을 붙여 사용하면 됩니다.

## 액션바 좀 더 살펴보기

- 액티비티의 위쪽에 보이는 타이틀 부분과 옵션 메뉴는 액션바로 합쳐져 보이게 됩니다. 그러면 이제 액션바에 대해 좀 더 자세하게 알아보겠습니다.

- 먼저 액션바는 기본적으로 제목을 보여주는 타이틀의 기능을 하므로 앱의 제목을 보여줄 수 있으며 화면에 보이거나 보이지 않도록 만들 수도 있습니다. 소스 코드에서 액션바를 보이게 만들고 싶다면 다음 코드처럼 show 메서드를 호출하고 감추고 싶다면 hide 메서드를 호출합니다.

```java
ActionBar abar = getActionBar();
abar.show();
abar.hide();
```

- setSubtitle 메서드를 사용하면 타이틀의 부제목을 달아줄 수도 있습니다. 부제목은 화면에 대한 구체적인 설명을 같이 보여주고 싶을 때 유용하게 사용할 수 있습니다. 앱을 디폴트 설정 그대로 실행하면 액션바의 왼쪽에는 아무것도 보이지 않지만 설정을 바꾸면 아이콘이 보이게 만들 수도 있습니다.

- 액션바를 다뤄보기 위해 새로운 SampleActionBar1 프로젝트를 만듭니다. 프로젝트를 만들 때 패키지 이름은 org.koreait.actionbar로 입력합니다. 새로운 프로젝트 창이 열리면 activity_main.xml파일에 버튼 하나를 추가합니다. 버튼에는 '액션바 아이콘 바꾸기'라는 글자가 보이게 합니다. 텍스트뷰의 글자인 Hello World!의 크기는 30sp로 설정합니다. 이 텍스트뷰에는 메뉴를 선택했을 때 어떤 메뉴를 선택했는지를 보여줄 것입니다. /app/res 폴더 안에 menu 폴더를 만들고 앞에서 만들었던 SampleOptionMenu 프로젝트의 메뉴 XML 파일을 복사합니다. /app/res/drawable 폴더의 이미지와 이 학습 소스에서 제공하는 이미지(home.png)도 추가로 복사하여 가져다 놓습니다. 그다음 MainActivity. java 파일도 SampleOptionMenu 프로젝트에서 만든 코드를 복사한 후 onCreate 메서드에 다음 코드를 추가합니다.

#### SampleActionBar1>/app/java/org.koreait.actionbar/MainActivity.java

```java
package org.koreait.actionbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActionBar abar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abar = getSupportActionBar();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                abar.setLogo(R.drawable.home);
                abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch(curId) {
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_search :
                Toast.makeText(this, "검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_settings :
                Toast.makeText(this, "설정 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            default :
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
```

- ActionBar는 androidx.appcompat.app 패키지 안에 들어 있는 클래스를 import하도록 합니다. 클래스 안에는 ActionBar 자료형의 변수가 선언되었으며, onCreate 메서드 안에서 getSupportActionBar 메서드를 이용해 XML 레이아웃에 들어 있는 ActionBar 객체를 참조합니다. ActionBar 객체는 직접 XML 레이아웃에 추가할 수도 있고 액티비티에 적용한 테마에 따라 자동으로 부여될 수도 있습니다. 버튼을 클릭했을 때 액션바가 보이는 모양을 바꾸도록 setDisplayOptions 메서드를 사용합니다.setDisplayOptions 메서드에는 미리 정의된 상수가 파라미터로 전달될 수 있으며 여기에서 사용된 상수들의 의미는 다음과 같습니다.

|디스플레이 옵션 상수|설명|
|-----|-------|
|DISPLAY_USE_LOGO|홈 아이콘 부분에 로고 아이콘을 사용합니다.|
|DISPLAY_SHOW_HOME|홈 아이콘을 표시하도록 합니다.|
|DISPLAY_HOME_AS_UP|홈 아이콘에 뒤로 가기 모양의 \< 아이콘을 같이 표시합니다.|
|DISPLAY_SHOW_TITLE|타이틀을 표시하도록 합니다.|

- 여기에서 로고 아이콘은 매니페스트에 등록된 액티비티의 속성으로 지정할 수도 있습니다. 앱을 실행하면 화면 위쪽에 있는 액션바에 두 개의 메뉴 아이콘이 표시된 것을 볼 수 있습니다. 가운데 있는 버튼을 누르면 타이틀 부분에 집 모양의 로고 아이콘이 표시됩니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/2.%20%EC%95%A1%EC%85%98%EB%B0%94/images/image3.png)

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/2.%20%EC%95%A1%EC%85%98%EB%B0%94/images/image4.png)

- 앞에서 복사해 만들었던 메뉴 XML 파일(/app/res/menu/menu_main.xml)을 다시 한 번 살펴보면 추가된 메뉴 아이템의 몇 가지 속성이 눈에 띕니다. 그 속성들 외에 세 개의 \<item\> 태그에 showAsAction 속성을 추가하고 그 값을 각각 'always', 'always withText', 'never'로 설정합니다. 이 중에서 'never'로 값을 설정하면 액션바에 메뉴가 보이지 않게 됩니다. 그리고 orderInCategory 속성도 추가합니다. 이 속성은 메뉴가 보이는 순서를 결정하며 101, 102, 103처럼 작은 숫자부터 순서대로 지정합니다.

- 이 코드의 수정은 메뉴 XML 파일(/app/res/menu/menu_main.xml)을 열어서 확인한 후 직접 수정해보기 바랍니다. 이렇게 액션바의 기능은 필요에 따라 약간씩 조정할 수 있는데 단순히 메뉴 아이콘을 표시하는 것이 아니라 입력상자와 같은 다른 형태의 뷰를 직접 보여줄 수 있다는 것을 알면 용도가 훨씬 더 다양하다는 것을 알 수 있을 것입니다.

- 이번에는 액션바에 검색어를 입력할 수 있는 입력상자를 넣어보겠습니다. 새로운 SampleActionBar2 프로젝트를 만듭니다. 프로젝트의 패키지명은 org.koreait.actionbar로 합니다. 책에서 제공된 이미지들은 /app/res/drawable 폴더로 복사합니다. 다음은 액션바 안에 입력상자를 넣으려고 만든 XML레이아웃으로 텍스트뷰 하나와 입력상자 하나로만 구성된 간단한 레이아웃입니다. 이 레이아웃은 입력상자에 검색어를 입력한 후 키패드에서 '완료' 키를 누르면 검색 기능을 수행할 수 있도록 하려고 만든 것입니다. /app/res/layout 폴더 안에 search_layout.xml 파일을 새로 만듭니다. 파일이 열리면 디자인 화면의 우측 상단에 있는 [Code] 아이콘을 눌러 XML 원본 코드를 띄웁니다. 그리고 다음 코드를 입력합니다.

#### SampleActionBar2>/app/res/layout/search_layout.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <!-- 문자열 표시를 위한 텍스트 뷰 -->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="검색 :"
        android:textSize="16sp"
        android:textColor="#ffad8745" />

    <!-- 검색어를 입력하기 위한 입력상자 -->
    <EditText
        android:id="@+id/editText"
        android:layout_width="100dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="4dp"
        android:inputType="text"
        android:imeActionId="1337"
        android:imeOptions="actionDone" />

</LinearLayout>
```

- 이렇게 만든 XML 레이아웃을 액션바에 넣어서 보여주려면 액션바에 추가된 메뉴 아이템 중 하나가 화면에 보일 때 이 레이아웃이 보이게 설정해야 합니다. 우선 파일 탐색기를 열고 SampleActionBar1 프로젝트에서 만든 /app/res/menu 폴더를 복사해서 SampleActionBar2 프로젝트의 /app/res 폴더로복사합니다. 그런 다음 그 폴더 안에 있는 menu_main.xml 파일을 열고 코드 가장 아래쪽에 다음 코드를 추가하세요. 메뉴가 정의된 XML 파일인 menu_main.xml 파일 안에 있는 메뉴 아이템 중에서 id 값이 'menu_search'인 아이템의 속성으로 메뉴가 화면에 보여질 때의 레이아웃을 설정합니다. 이때 사용되는 속성은 app:actionLayout입니다.


#### SampleActionBar2>/app/res/menu/menu_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item
        android:id="@+id/menu_refresh"
        android:icon="@drawable/menu_refresh"
        android:title="새로고침"
        app:showAsAction="always" />

    <item
        android:id="@+id/menu_settings"
        android:icon="@drawable/menu_settings"
        android:title="설정"
        app:showAsAction="always" />

    <item
        android:id="@+id/menu_search"
        android:orderInCategory="102"
        android:title="검색"
        app:actionLayout="@layout/search_layout"
        app:showAsAction="always|withText" />
    
</menu>
```

- 그다음에는 MainActivity.java 파일을 열어서 레이아웃에 들어 있는 EditText 객체에 사용자가 검색어를 입력하고 '완료' 키를 눌렀을 때 원하는 기능이 수행될 수 있도록 코드를 수정합니다. 이때 MainActivity 클래스를 마우스 오른쪽 버튼으로 눌러 [Generate... → Override Methods...] 메뉴를 선택하거나 단축키 [Ctrl] + [O] 를 누릅니다. 재정의할 수 있는 [Select Methods th Override/Implement] 대화상자가 보이면 onCreateOptionsMenu 메서드를 선택한 후 [OK]를 눌러서 추가합니다. onCreateOptionsMenu 메서드 안에 EditText 객체를 참조하고 리스너를 설정하는 코드를 추가합니다.

#### SampleActionBar2>/app/java/org.koreait.actionbar/MainActivity.java

```java
package org.koreait.actionbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        View v = menu.findItem(R.id.menu_search).getActionView();

        if (v != null) {
            EditText editText = v.findViewById(R.id.editText);

            if (editText != null) {
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        Toast.makeText(getApplicationContext(), "입력됨.", Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
            }
        }

        return true;
    }
}
```

- EditText 객체에 설정된 리스너에서는 토스트 메시지를 보여줍니다. 이 부분은 필요에 따라 사전 또는 인터넷 검색 기능을 만들어 호출하도록 변경할 수도 있을 것입니다.

- 다음은 앱을 실행했을 때의 화면입니다. 액션바에 검색할 수 있는 입력상자가 보이며, 검색어를 입력한 후 키패드에 있는 '완료' 키를 누르면 '입력됨'이라는 토스트 메시지가 표시됩니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/2.%20%EC%95%A1%EC%85%98%EB%B0%94/images/image5.png)

- 지금까지 액션바에 메뉴를 넣어 보여주는 방법을 자세히 살펴보았습니다. 액션바는 앱의 상단에 타이틀 뿐만 아니라 버튼이나 입력상자 등을 배치할 수 있는 공간이므로 앱을 만들 때 유용하게 사용할 수 있습니다.

* * * 
# 상단 탭과 하단 탭 만들기

## 상단 탭 보여주기

- 모바일 단말은 일반적으로 화면의 크기가 작기 때문에 하나의 화면에 너무 많은 구성 요소를 넣으면 성능이나 사용성(Usability) 면에서 좋지 않습니다. 안드로이드의 경우에도 하나의 화면을 나타내는 액티비티를 최대한 많이 분리시켜서 하나의 화면에 보이는 뷰의 개수를 줄여주는 것이 좋습니다.

- 그러나 때로는 하나의 화면에 여러 가지 구성 요소를 넣어두고 필요할 때 전환하여 보여주는 게 좋을때도 있습니다. 대표적인 것이 서브 화면들입니다. 예를 들어, 상단에 버튼이 두 개 있고 그 두 개의 버튼을 누를 때마다 아래에 다른 화면을 보여주는 방식으로 만든다면 고객의 일반 정보와 신용도를 구분하여 보여줄 수 있습니다. 이처럼 한 명의 고객과 관련된 서로 다른 두 가지 정보를 한 화면에서 전환하여 보여줄 수 있으므로 더 직관적인 화면을 구성할 수 있습니다.

- 이렇게 몇 개의 버튼을 두고 그중 하나의 버튼을 눌러 서브 화면을 전환하는 방식처럼 하나의 뷰에서여러 개의 정보를 볼 때 일반적으로 사용하는 뷰로 탭(Tab)을 들 수 있습니다. 탭은 안드로이드의 전화번호부를 비롯한 몇 개의 기본 앱에서 볼 수 있는데 상단에 있는 탭을 누를 때마다 내용이 보이는 화면 영역이 전환되어 나타납니다. 탭은 내비게이션(Navigation) 위젯이라고 불리기도 하며 상단 탭과 하단 탭(Bottom Navigation)으로 구분할 수 있습니다. 최근에는 하단 탭을 더 많이 사용합니다. 상단탭의 경우에는 액션바에 탭 기능을 넣어 보여주는 방법으로 제공되며 하단 탭은 별도의 위젯으로 제공됩니다.

- 그럼 상단 탭을 어떻게 간단하게 만들 수 있는지 살펴보겠습니다. 새로운 SampleTab 프로젝트 파일을 만들고 프로젝트의 패키지명은 org.koreait.tab으로 합니다. activity_main.xml 파일을 열고 다음과 같이 입력합니다.

#### SampleTab>/app/res/layout/activity_main.xml

```xml

````


