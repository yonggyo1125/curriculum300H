#  플래그와 부가 데이터 사용하기

- 지금까지 새 액티비티를 띄우는 방법을 알아보았습니다. 그런데 액티비티로 만든 화면이 한 번 메모리에 만들어졌는데도 계속 startActivity startActivityForResult 메서드를 여러 번 호출하면 동일한 액티비티가 메모리에 여러 개 만들어질 것
입니다. 왜냐하면 시스템이 인텐트별로 새 액티비티를 띄워주기 때문입니다. 만약 같은 액티비티에 대해 인텐트를 두 번 보내면 중복된 액티비티가 뜨게 됩니다. 그래서 시스템의 [BACK] 버튼을 누르면 아래에 있던 액티비티가 나타납니다. 중복된 액티비티를 띄우지 않으려면 어떻게 해야 할까요? 이런 문제는 플래그(Flag)를 사용하면 조정할 수 있습니다.

## 플래그

- 플래그를 이해하려면 액티비티가 처리되는 방식을 이해해야 합니다. 액티비티는 액티비티 매니저(ActivityManager)라는 객체에 의해 '액티비티 스택(Activity Stack)'이라는 것으로 관리됩니다. 그리고 이스택은 액티비티를 차곡차곡 쌓아두었다가 가장 상위에 있던 액티비티가 없어지면 이전의 액티비티가 다시 화면에 보이게 합니다. 다음 그림은 액티비티 스택의 처리 과정을 나타낸 것입니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/4.%20%ED%94%8C%EB%9E%98%EA%B7%B8%EC%99%80%20%EB%B6%80%EA%B0%80%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%82%AC%EC%9A%A9/images/image1.png)


- 새로운 액티비티를 만들어 매니페스트 파일에 등록하면 그 액티비티는 startActivity 또는 startActivityForResult 메서드를 사용해 실행됩니다. 이렇게 실행된 액티비티는 화면에 띄워지는데 새로운 액티비티가 화면에 띄워지면 이전에 있던 액티비티는 액티비티 스택에 저장되고 새로운 액티비티가 화면에 보이는 구조입니다. 화면에 보이던 액티비티가 없어지면 액티비티 스택의 가장 위에 있던 액티비티가 화면에 보이면서 화면 기능이 다시 동작합니다. 결국, 새로운 화면이 보이면 이전의 화면들은 그 화면의 뒤에 차곡차곡 가려져 있다고 생각할 수도 있습니다.

- 이렇게 일반적인 스택 구조를 이용해 액티비티가 관리되기는 하지만 만약 여러분이 동일한 액티비티를 여러 번 실행한다면 동일한 액티비티가 여러 개 스택에 들어가게 되고 동시에 데이터를 여러 번 접근하거나 리소스를 여러 번 사용하는 문제가 발생할 수 있습니다. 이러한 문제들을 해결할 수 있도록 도와주는 것이 바로 플래그인데 대표적인 플레그들은 다음과 같습니다.

```
FLAG_ACTIVITY_SINGLE_TOP
FLAG_ACTIVITY_NO_HISTORY
FLAG_ACTIVITY_CLEAR_TOP
```

- 첫 번째, <b>FLAG_ACTIVITY_SINGLE_TOP</b> 플래그는 <b>액티비티를 생성할 때 이미 생성된 액티비티가 있으면 그 액티비티를 그대로 사용하라는 플래그</b>입니다. 다음은 플래그를 사용하지 않을 때와 FLAG_ACTIVITY_SINGLE_TOP 플래그를 사용할 때를 비교한 것으로 두 번째 액티비티가 두 번 실행되었지만 액티비티는 한 개만 만들어져 있고 이전에 사용된 두 번째 액티비티가 그대로 사용된 것을 알 수 있습니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/4.%20%ED%94%8C%EB%9E%98%EA%B7%B8%EC%99%80%20%EB%B6%80%EA%B0%80%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%82%AC%EC%9A%A9/images/image2.png)

- 결국 FLAG_ACTIVITY_SINGLE_TOP 플래그를 사용하면 기존에 만든 동일한 액티비티를 그대로 사용하게 됩니다. 그런데 화면에 보이는 액티비티가 새로 만들어지지 않고 기존에 있는 것이 보인다면 시스템에서 전달하는 인텐트 객체는 어떻게 전달받을 수 있을까요?

- 새로운 액티비티를 띄워주는 액티비티를 부모 액티비티라고 부른다면, 부모 액티비티로부터 전달하는 인텐트는 새로 만들어진 인텐트의 onCreate 메서드 안에서 getIntent 메서드로 참조할 수 있습니다.

- 그런데 액티비티가 새로 만들어지지 않고 재사용된다면 액티비티의 onCreate 메서드가 호출되지 않습니다. 이 경우에는 새로 띄워지는 액티비티에서 인텐트를 전달 받아 처리하는 방법이 따로 있어야 합니다. 그것이 바로 onNewIntent 메서드입니다. onNewIntent 메서드를 재정의하면 액티비티가 새로 만들어지지 않았을 때 인텐트 객체만 전달 받을 수 있습니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/4.%20%ED%94%8C%EB%9E%98%EA%B7%B8%EC%99%80%20%EB%B6%80%EA%B0%80%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%82%AC%EC%9A%A9/images/image3.png)

>  액티비티가 이미 메모리에 객체로 만들어져 있다면 액티비티를 다시 띄우더라도 onCreate 메서드가 호출되지 않습니다. 따라서 onNewIntent 메서드를 재정의하여 사용해야 합니다. onNewInten 메서드는 액티비티가 이미 객체로 만들어져 있을 때 시스템으로부터 자동으로 호출되며 파라미터로 인텐트 객체를 전달 받을 수 있습니다.

```java
public void onNewIntent(Intent intent)
```

- 새로운 액티비티를 띄울 때 FLAG_ACTIVITY_SINGLE_TOP 플래그를 어떻게 사용하는지는 브로드캐스트 수신자에서 액티비티를 띄우거나 또는 서비스에서 액티비티를 띄우는 코드를 보면 쉽게 이해할 수 있습니다. 이 내용은 서비스나 브로드캐스트 수신자 부분에서 설명할 것입니다.

- 두 번째로 플래그를 <b>FLAG_ACTIVITY_NO_HISTORY</b>로 설정하는 경우가 있습니다. 이 플래그로 설정하면 <b>처음 이후에 실행된 액티비티는 액티비티 스택에 추가되지 않습니다.</b> 즉, 플래그가 설정되지 않은 경우에는 이전에 실행되었던 액티비티가 스택에 추가되므로 시스템 [Back] 키를 누르면 이전의 액티비티가 보입니다. 하지만 이 플래그를 사용하면 항상 맨 처음에 실행되었던 액티비티가 바로 보이게 됩니다. 이 플래그는 알람 이벤트가 발생하여 사용자에게 한 번 알림 화면을 보여주고 싶을 때 유용하게 사용할 수 있습니다. 이 알림 화면은 한 번만 보여주면 되므로 여러 번 알람 이벤트가 발생하더라도 그 화면만 한 번 보여주는 형태로 만들 수 있습니다. 다음은 FLAG_ACTIVITY_NO_HISTORY로 플래그를 설정한 경우 화면에서 어떻게 처리되는지를 보여줍니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/4.%20%ED%94%8C%EB%9E%98%EA%B7%B8%EC%99%80%20%EB%B6%80%EA%B0%80%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%82%AC%EC%9A%A9/images/image4.png)

- 세 번째로 플래그를 <b>FLAG_ACTIVITY_CLEAR_TOP</b>으로 설정하면 <b>이 액티비티 위에 있는 다른 액티비티를 모두 종료</b>시키게 됩니다. 이 플래그는 홈 화면과 같이 다른 액티비티보다 항상 우선하는 액티비티를 만들 때 유용하게 사용할 수 있습니다. 만약 홈 화면이 여러 개 있는 것이 아니라 하나만 만들어지는 것으로 하고 싶을 때 FLAG_ACTIVITY_SINGLE_TOP 플래그와 함께 설정하면 항상 하나의 객체가 메모리에 존재하면서 그 상위의 액티비티를 모두 종료시킬 수 있습니다. 다음은 FLAG_ACTIVITY_CLEAR_TOP으로 설정했을 때 화면에서 액티비티가 어떻게 바뀌는지 보여줍니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/4.%20%ED%94%8C%EB%9E%98%EA%B7%B8%EC%99%80%20%EB%B6%80%EA%B0%80%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%82%AC%EC%9A%A9/images/image5.png)

- 플래그가 무엇인지 알 것 같나요? 이 외에도 여러 가지 플래그가 있지만 실제 앱을 만들 때 주로 사용하는 것은 위의 세 가지 정도입니다. 플래그를 사용하는 방법은 뒤에서 다룰 것입니다.

## 부가 데이터

- 한 액티비티에서 다른 액티비티를 띄울 때 데이터를 전달해야 하는 경우도 있습니다. 예를 들어, 로그인 화면에서 로그인 버튼을 눌러 로그인을 성공하면 메뉴 화면으로 아이디(또는 이름)를 전달하여 표시해야 할 수도 있습니다. 어떻게 하면 로그인 화면에서 메뉴 화면으로 아이디를 전달할 수 있을까요? 가장 간단한 방법은 별도의 클래스를 만든 다음 그 안에 클래스 변수(static 키워드를 이용해 선언한 변수)를 만들어 두 개의 화면에서 모두 그 변수를 참조하게 하는 방법입니다. 즉, 로그인 화면에서 값을 설정하고 메뉴 화면에서 로그인 화면의 변수 값을 참조하면 됩니다.

- 하지만 안드로이드는 다른 앱에서 여러분이 만든 화면을 띄울 수도 있기 때문에 변수를 공유하는 방식으로 데이터를 전달하는 것이 불가능할 수도 있습니다. 따라서 기본적으로는 액티비티를 띄울 때 전달되는 인텐트 안에 부가 데이터(Extra data)를 넣어 전달하는 방법을 권장합니다.

- 인텐트 안에는 번들(Bundle) 객체가 들어 있는데, 번들 객체는 해시테이블과 유사해서 putExtra와 getOOOExtra 메서드로 데이터를 넣거나 빼낼 수 있습니다(여기에서 ○○○은 기본 자료형의 이름). 예를 들어, 문자열을 넣고 싶다면 putExtra 메서드를 호출하고 문자열을 다시 빼내고 싶다면 getStringExtra 메서드를 사용하면 됩니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/4.%20%ED%94%8C%EB%9E%98%EA%B7%B8%EC%99%80%20%EB%B6%80%EA%B0%80%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%82%AC%EC%9A%A9/images/image6.png)

- 기본적으로 기본 자료형(Primitive Data Type)을 넣었다 뺄 수 있지만 바이트 배열이나 Serializable 객체도 넣었다 뺄 수 있습니다.

- 이렇게 번들 객체 안에 넣은 데이터를 부가 데이터라고 하며 시스템에서는 건드리지 않고 다른 앱 구성 요소로 전달합니다. 번들 안에 문자열이나 정수와 같은 부가 데이터를 넣을 때는 키(Key)와 값(Value)을 쌍으로 만들어 넣습니다. 문자열과 정수 그리고 이진 값을 넣거나 뺄 때 사용하는 대표적인 메서드는 다음과 같습니다.

```java
Intent putExtra(String name, String value)
Intent putExtra(String name, int value)
intent putExtra(String name, boolean value)

String getStringExtra(String name)
int getIntExtra(String name, int defaultValue)
boolean getBooleanExtra(String name, boolean defaultValue)
```

- getOOO() 형태를 가진 메서드는 데이터 값이 없으면 디폴트로 설정한 defaultValue 값이 반환됩니다. 또, 전달하고 싶은 데이터가 기본 자료형이 아니라 객체(Object) 자료형인 경우에는 객체 자체를 전달할 수 없습니다. 객체 데이터는 바이트 배열로 변환하거나 Serializable 인터페이스를 구현하는 객체를 만들어 직렬화한 다음 전달해야 합니다. 
- 직렬화 방법은 표준 자바를 접해 보았다면 쉽게 이해할 수 있을 것입니다.

- 그러나 안드로이드는 객체를 전달할 때 Serializable 인터페이스와 유사한 Parcelable 인터페이스를 권장합니다. Parcelable 인터페이스는 Serializable과 유사하지만 직렬화했을 때 크기가 작아 안드로이드 내부의 데이터 전달에 자주 사용됩니다. 이 인터페이스를 사용하면 객체를 직접 번들에 추가하여 데이터를 전송할 때 사용할 수 있습니다. 단, 다음의 두 가지 메서드를 모두 구현해야 합니다.

```java
public abstract int descibeContents();
public abstract void wroteToParcel(Parcel dest, int flags)
```

- describeContents 메서드는 직렬화하려는 객체의 유형을 구분할 때 사용합니다. 여기에서는 단순히 0을 반환하도록 합니다. writeToParcel 메서드는 객체가 가지고 있는 데이터를 Parcel 객체로 만들어 주는 역할을 합니다. 이 Parce 객체는 Bundle 객체처럼 readOOO()와 write○○○() 형태를 가진 메서드를 제공하므로 기본 데이터 타입을 넣고 확인할 수 있도록 합니다. 위의 두 가지 메서드를 모두 구현한 다음에는 CREATOR라는 상수를 만들어야 하는데 이 상수는 Parcel 객체로부터 데이터를 읽어 들여 객체를 생성하는 역할을 합니다. 이 객체는 상수로 정의되고 반드시 static final로 선언되어야 합니다.

- 이제 Parcelable 인터페이스를 구현하는 새로운 객체를 정의하고 그 객체를 인텐트에 넣어 전달해 보겠습니다. 새로운 SampleParcelable 프로젝트를 만들고 activity_main.xml 파일을 연 후 기존에 있던 텍스트뷰는 삭제하고 버튼을 하나 추가합니다. 버튼에 보이는 글자는 '메뉴 화면 띄우기'로 설정합니다. 이 버튼을 클릭하면 Parcelable 자료형으로 된 객체를 인텐트에 넣어 메뉴 화면에 전달하는 코드를 넣을 예정입니다.

- 새 액티비티를 하나 더 추가하겠습니다. 왼쪽 프로젝트 창에서 app을 선택한 상태에서 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [New → Activity → Empty Activity]를 눌러 새로운 액티비티를 추가할 수 있는 대화상자를 띄웁니다. 새 액티비티의 이름을 MenuActivity로 수정하고 [Finish]를 누르면 MenuActivity.java 파일과 activity_menu.xml 파일이 프로젝트에 추가됩니다.

- 메인 화면의 [메뉴 화면 띄우기] 버튼을 클릭하여 Parcelable 자료형으로 된 객체를 메뉴 화면에 전달하려면 먼저 클래스를 정의해야 합니다. 왼쪽 프로젝트 창의 app 폴더 안에 있는 [java → org.koreait.sampleparcelable] 폴더를 선택한 후 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [New Java Class]를 눌러 새로운 클래스를 만들 수 있는 [New Java Class] 대화상자를 띄웁니다. 입력상자에

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/4.%20%ED%94%8C%EB%9E%98%EA%B7%B8%EC%99%80%20%EB%B6%80%EA%B0%80%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%82%AC%EC%9A%A9/images/image7.png)

#### SampleParcelable>/app/src/org.koreait.sampleparcelable/SimpleData.java

```java
package org.koreait.sampleparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {
    int number;
    String message;

    public SimpleData(int number, String message) {
        this.number = number;
        this.message = message;
    }

    // Parcel 객체에서 읽기
    public SimpleData(Parcel src) {
        number = src.readInt();
        message = src.readString();
    }

    // CREATOR 상수 정의
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        // SimpleData 생성자 호출해 Parcel 객체에서 읽기
        public SimpleData createFromParcel(Parcel in) {
            return new SimpleData(in);
        }

        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    // Parcel 객체로 쓰기
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
    }
}
```

- SimpleData 클래스는 Parcelable 인터페이스를 구현하므로 implements Parcelable 코드가 추가되어 있습니다. 클래스 안에 정의된 인스턴스 변수는 두 개이며, 하나는 문자열이고 하나는 정수입니다. SimpleData 객체는 이 두 개의 변수로 구성된 객체입니다. writeToParcel 메서드는 이 SimpleData 객체 안에 들어 있는 데이터를 Parcel 객체로 만드는 역할을 합니다. 그래서 이 메서드 안에는 writeInt와 writeString 메서드가 있습니다. 또한, SimpleData 클래스의 생성자를 보면 Parcel 객체를 파라미터로 받게 되는데 이 경우에는 readInt와 readString 메서드를 이용해 데이터를 읽어 들입니다.

- CREATOR 객체는 상수로 정의되어 있으며 새로운 객체가 만들어지는 코드가 들어가므로 new SimpleData()와 같이 SimpleData 객체를 만드는 부분을 볼 수 있습니다. 결과적으로 SimpleData 클래스안에 Parcel 객체의 데이터를 읽는 부분과 Parcel 객체로 쓰는 부분을 정의하게 됩니다.

- 이제 메인 액티비티의 소스 코드를 수정합니다. MainActivity.java 파일을 열고 버튼을 클릭했을 때 메뉴 액티비티를 띄우는 코드를 추가합니다. 메뉴 액티비티를 띄우기 위해 만드는 인텐트 객체에는SimpleData 객체를 부가 데이터로 넣어 전달할 것입니다. MainActivity 클래스 안에 다음 코드를 입력합니다.

#### SampleParcelable>/app/src/org.koreait.simpleparcelable/MainActivity.java

```java

... 생략

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
```

- 앞에서 정의한 SimpleData 클래스로 객체를 만드는 부분을 보면 new SimpleData(100, "Hello Android!")로 되어 있습니다. 따라서 정수는 100, 문자열은 "Hello Android!"인 데이터가 Parcel 객체로만들어집니다. 인텐트 객체의 putExtra 메서드를 사용해 SimpleData 객체를 부가 데이터로 추가했다면이 SimpleData 객체는 메뉴 액티비티에서 꺼내어 사용할 수 있습니다.

- 마지막으로 메뉴 화면을 수정할 차례입니다. 메뉴 액티비티의 XML 레이아웃 파일인 activity_menu.xml 파일을 열고 텍스트뷰 하나와 버튼 하나를 추가합니다. 텍스트뷰에는 '전달 받은 데이터'라는 글자가 보이도록 text 속성을 설정합니다. 텍스트뷰에는 메인 액티비티로부터 전달 받은 데이터를 보여줄것입니다. 버튼에는 '돌아가기'라는 글자가 표시되도록 text 속성을 설정합니다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/4.%20%ED%94%8C%EB%9E%98%EA%B7%B8%EC%99%80%20%EB%B6%80%EA%B0%80%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%82%AC%EC%9A%A9/images/image8.png)

- XML 레이아웃 파일을 완성했다면 MenuActivity.java 파일을 열고 전달 받은 인텐트를 처리하는 코드를 입력합니다.

#### SampleParcelable>/app/src/org.koreait.sampleparcelable/MenuActivity.java

```java 
package org.koreait.sampleparcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    TextView textView;

    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike");
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            SimpleData data = bundle.getParcelable(KEY_SIMPLE_DATA);
            textView.setText("전달 받은 데이터\nNumber : " + data.number + "\nMessage : "  + data.message);
        }
    }
}
```

- 메인 액티비티로부터 전달 받은 인텐트 객체를 참조하기 위해 onCreate 메서드 안에서 getIntent 메서드를 호출했습니다. getIntent 메서드를 호출하면 인텐트 객체가 반환되므로 이 객체 안의 번들 객체를 참조할 수 있습니다. getExtras 메서드를 호출하면 Bundle 자료형의 객체가 반환됩니다. 이렇게 번들 객체를 참조한 후 getOOO() 형태의 메서드를 사용해도 되고, 번들 객체를 참조하지 않고 인텐트 객체에 정의되어 있는 getOOOExtra() 형태의 메서드를 사용해도 됩니다. 번들 객체 안에 SimpleData 객체가 들어 있으므로 getParcelable 메서드로 객체를 참조한 후 화면의 텍스트뷰에 전달 받은 데이터를 보여줍니다. 번들 객체에 데이터를 저장하기 위한 키(Key)는 메인 액티비티와 또 다른 액티비티 모두 KEY_SIMPLE_DATA라는 같은 이름의 상수로 정의되어 있습니다.

이 앱을 실행하면 다음과 같은 화면을 볼 수 있습니다.

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/4.%20%ED%94%8C%EB%9E%98%EA%B7%B8%EC%99%80%20%EB%B6%80%EA%B0%80%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%82%AC%EC%9A%A9/images/image9.png)

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/4~5%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83%20%EC%9D%B8%ED%94%8C%EB%A0%88%EC%9D%B4%EC%85%98%2C%20%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98%20%EB%93%B1/4.%20%ED%94%8C%EB%9E%98%EA%B7%B8%EC%99%80%20%EB%B6%80%EA%B0%80%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%82%AC%EC%9A%A9/images/image10.png)


- 메인 액티비티의 [메뉴화면 띄우기] 버튼을 누르면 새로 추가한 메뉴 화면이 뜹니다. 그리고 화면에는 인텐트 안의 번들 객체에 넣어 전달한 SimpleData 객체의 정보가 나타납니다. Parcelable 인터페이스를 구현하는 방법이 처음에는 약간 어려워 보일 수 있지만 한두 번 사용해보면 그리 복잡하지는 않습니다. Parcelable 인터페이스를 사용하면 객체를 정의해 데이터를 전달할 수 있으므로 코드가 좀 더 단순해지고 재사용성이 높아지는 장점이 생기게 됩니다. 하지만 데이터를 담아둘 새로운 객체를 일일이 정의하는 것이 번거롭다는 단점도 있습니다.

