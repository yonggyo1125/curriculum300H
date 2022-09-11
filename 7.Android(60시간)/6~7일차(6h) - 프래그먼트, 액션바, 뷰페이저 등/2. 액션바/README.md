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

#### SampleActionBar1>/app/java/org.techtown.actionbar/MainActivity.java

```java

```


