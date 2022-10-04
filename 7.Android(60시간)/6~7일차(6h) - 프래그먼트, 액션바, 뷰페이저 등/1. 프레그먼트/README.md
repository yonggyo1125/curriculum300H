# 프레그먼트란?

- 여러 개의 화면을 구성할 때는 보통 각각의 화면을 액티비티로 만든 후 애티비티를 전환하는 방법을 시용합니다. 이 방법은 매우 중요하기 때문에 꼭 기억해야 할 내용입니다. 그런데 화면의 일부분을 다른 화면으로 구성하고 싶을 때는 어떻게 해야 할까요? 예를 들어, 화면의 아래쪽 일부분에 독립적인 레이아웃을 만들고 그 안에서 동영상을 재생하고 싶다면 어떻게 구성해야 할까요? 다른 예를 들면, 화면 전체를 차지하는 메뉴 화면이 있다고 생각해 보겠습니다. 화면이 비교적 작은 스마트폰을 사용할 때는 괜찮지만 화면이 큰 태블릿에서는 이런 메뉴 화면은 상당히 불편하겠죠. 이런 경우에는 태블릿의 큰 화면을 이용하여 화면 왼쪽에는 메뉴 화면, 오른쪽에는 선택한 메뉴의 상세 화면이 나타나면 훨씬 편리합니다. 즉, 전체 화면 안에 부분 화면을 만들어 넣으면 화면을 전환하지 않아도 되니 불편하지도 않고 넓은 화면을 잘 활용할 수 있어 좋습니다.

## 프래그먼트에 대해 이해하기

- 하나의 화면을 구성하는 XML 레이아웃을 만들 때는 리니어 레이아웃 안에 또 다른 리니어 레이아웃을 넣을 수 있습니다. 즉, 부분 화면은 전체 화면으로 만든 레이아웃 안에 들어있는 또 다른 레이아웃입니다. 예를 들어, 다음과 같은 레이아웃을 만들 수 있습니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image1.png)

- 이렇게 만든 레이아웃을 이용하면 리니어 레이아웃 안에 다른 레이아웃을 추가하기나 각각의 레이아옷 안에 필요한 뷰를 넣어 화면을 만들 수 있습니다. 하지만 이렇게 하면 코드가 많이 복잡해집니다. 하나만 예를 들어 볼까요? 화면의 위쪽에 표시된 글 리스트를 보다가 글을 쓰고 싶다면 글 리스트가 보이는 부분을 글쓰기 레이아웃으로 바뀌게 만들 수 있습니다. 이런 작업이 가능하려면 프레임 레이아웃 안에 여러 개의 레이아웃을 넣어 중첩시킨 후 가시성 속성으로 필요한 레이아웃만 보이게 만들면 됩니다. 이때 각각의 레이아웃이 동시에 보이는 상태인지 아닌지에 대한 정보도 알 수 있어야 합니다. 그리고 그 안에 들어 있는 객체들이 메모리에 만들어져 있는지 아닌지에 대한 정보도 관리해야 합니다. 특히 지금 보고 있는 액티비티가 아니라 다른 액티비티에서도 여기에서 사용하는 글쓰기 부분을 보여주고 싶다면 다른 액티비티에서도 동일한 레이아웃을 중복해서 만들어야 하는 문제가 생깁니다. 즉, 하나의 화면 안에 들어가는 부분 화면을 만들어 넣는 것은 자연스러운 일이지만 각각의 부분 화면을 다루는 것이 쉽지만은 않습니다.

- 그렇다면 하나의 액티비티 안에 여러 개의 액티비티를 부분 화면으로 올려서 보여주는 방법은 어떨까요? 트위터를 위한 액티비티와 동영상을 위한 액티비티를 만든 후에 하나의 레이아웃에 같이 보이도록 하는 것이 가능할까요? 구체적으로 Activity 클래스와 ActivityGroup 클래스를 사용하면 구현할 수 있습니다. 하지만 액티비티는 하나의 화면을 독립적으로 구성할 때 필요한 여러 가지 속성들을 사용하게 되며, 안드로이드 시스템에서 관리하는 앱 구성 요소이기 때문에 <b>액티비티 안에 다른 액티비티를 넣는 것은 단말의 리소스를 많이 사용하는 비효율적인 방법</b>입니다.

- 또 다른 예를 하나 더 들어보겠습니다. 다음 그림의 왼쪽 부분은 기존 스마트폰에서 전화번호부 화면이 전환되는 방식을 보여주는데 두 개의 액티비티를 만든 후 필요할 때마다 액티비티가 서로 전환됩니다. 이와 다르게 오른쪽 부분은 하나의 액티비티를 만든 후 그 안에 두 개의 화면인 전화번호부의 리스트와 전화번호부의 상세 정보를 하나의 액티비티 안에 넣어 두었습니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image2.png)

- 오른쪽 그림처럼 하나의 화면을 여러 부분으로 나눠서 보여주거나 각각의 부분 화면 단위로 바꿔서 보여주고 싶을 때 사용하는 것이 프래그먼트(Fragment)입니다. 프래그먼트는 태블릿처럼 큰 화면의 단말을 지원하려고 시작했는데 지금은 단말의 크기와 상관없이 화면 UI를 만들 때 많이 사용됩니다. <b>프래그먼트는 하나의 화면 안에 들어가는 부분 화면과 같아서 하나의 레이아웃처럼 보입니다. 하지만 액티비티처럼 독립적으로 동작하는 부분 화면을 만들 때 사용</b>됩니다.

- 프래그먼트를 알아보기 전에 가장 먼저 생각해야 할 것이 프래그먼트가 만들어진 목적입니다. 코드가 복잡해지는 문제를 해결하기 위해 각 부분 화면의 코드를 분리시킨 것이므로 프래그먼트를 사용하는 가장 큰 목적은 분할된 화면들을 독립적으로 구성하고 그 상태를 관리하는 데 있습니다. 

- 프래그먼트 사용 목적
	- 분할된 화면들을 독립적으로 구성하기 위해 사용함
	- 분할된 화면들의 상태를 관리하기 위해 사용함

> 프래그먼트가 화면 분할을 위한 것이라고 얘기할 때 사용하는 '화면' 이라는 말과 액티비티에서 사용하는 '화면' 이라는 말은 서로 다른 것을 가리킵니다. 똑같이 XML 레이아웃으로 만들지만 <b>액티비티에 사용되면 시스템에서 관리하는 화면</b>이고, <b>프래그먼트에서 사용되면 단순히 액티비티 위에 올라가는 화면의 일부, 즉 '부분 화면'</b> 이 됩니다.


- 또 한 가지 기억해야 할 내용은 프래그먼트는 항상 액티비티 위에 올라가 있어야 한다는 점입니다. 액티비티로 만든 화면을 분할한 뒤 각각의 부분 화면을 프래그먼트로 만들고 그 프래그먼트를 독립적으로 관리하는 것이 목표이기 때문에 프래그먼트는 액티비티 위에 올라가 있어야 프래그먼트로서의 역할을 할 수 있습니다. 따라서 <b>프래그먼트가 제대로 동작하는 시점은 프래그먼트가 메모리에 만들어진 시점이 아니라 액티비티에 올라가는 시점입니다.</b>

- 프래그먼트를 처음 기획한 개발자는 프래그먼트를 독립적으로 다룰 수 있는 가장 좋은 방법이 무엇인지 고민했습니다. 그리고 <b>프래그먼트가 동작하는 방식을 하나의 화면을 독립적으로 다루는 액티비티와 유사한 방식으로 만들기로 결정</b>했습니다. 따라서 프래그먼트는 다음과 같은 형태로 동작합니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image3.png)

- 왼쪽 그림은 액티비티가 동작하는 방식입니다. <b>액티비티는 앱 구성 요소이므로 안드로이드 시스템에서 관리</b>합니다. 좀 더 구체적으로는 액티비티 매니저가 액티비티의 동작 순서나 처리 방식을 결정합니다. 또한 액티비티가 시스템에서 관리되기 때문에 시스템이 이해하는 형식으로 명령이나 데이터를 만들어보내야 하는데, <b>인텐트가 그 역할</b>을 합니다. 다시 말해, <b>액티비티를 관리하는 시스템 객체는 액티비티 매니저</b>이며, 이 <b>액티비티 매니저에 의해 액티비티가 독립적으로 동작</b>할 수 있습니다.

- 오른쪽 그림을 보면 프래그먼트가 동작하는 방식이 왼쪽의 것과 상당히 유사합니다. 즉, <b>액티비티가 동작하는 왼쪽 방식을 그대로 본떠 만들었다는 것</b>을 알 수 있습니다. 왼쪽 그림에서 안드로이드 시스템이 하던 역할을 오른쪽 그림에서는 액티비티가 합니다. 왼쪽 그림에서 액티비티 매니저가 액티비티들을 관리했다면 오른쪽 그림에서는 프래그먼트 매니저라는 것을 만들어 프래그먼트들을 관리하도록 했습니다. 여기에서 보면 액티비티가 시스템 역할을 하게 되므로 액티비티 위에 올라가 있지 않은 프래그먼트는 정상적으로 동작할 수 없다는 것을 짐작할 수 있습니다.

- 그런데 왼쪽 그림에서 인텐트가 하던 역할은 오른쪽에서는 사용할 수 없습니다. 왜냐하면 인텐트는 시스템에서 이해하는 객체인데 그것을 프래그먼트와 액티비티 사이에서 전달하게 만드는 것은 바람직하지 않기 때문입니다. 이 때문에 <b>액티비티와 프래그먼트 간에 데이터를 전달할 때는 단순히 메서드를 만들고 메서드를 호출하는 방식을 사용</b>합니다.

- 그렇다면 프래그먼트는 액티비티 위에 항상 두 개 이상이 올라가 있어야 할까요? 그렇지 않습니다. 하나의 프래그먼트만 액티비티에 올려놓아도 상관없습니다. 그리고 그 프래그먼트가 화면 전체를 채우도록 할 수 있어 사용자는 프래그먼트가 하나의 전체 화면처럼 느끼게 됩니다. 이런 방식을 사용하면 원하는 시점에 하나의 프래그먼트를 올려놓았다가 다른 프래그먼트로 바꿔서 보여줄 수도 있으므로 액티비티를 전환하지 않아도 화면 전환 효과를 낼 수 있습니다.

- 액티비티는 시스템에서 관리하지만 프래그먼트는 액티비티 위에 올라가 있어 액티비티를 전환하지 않고도 훨씬 가볍게 화면 전환 효과를 만들 수 있게 됩니다. 특히탭 모양으로 화면을 구성할 때 각각의 [탭] 버튼을 클릭할 때마다 다른 화면이 보이는 효과를 내고 싶다면 액티비티가 아닌 프래그먼트를 사용하는 것이 좋습니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image4.png)

## 프래그먼트를 화면에 추가하는 방법 이해하기

- 앞서 프래그먼트 사용 목적은 부분 화면을 독립적으로 사용하기 위해서이고 액티비티를 본떠 만든 것이라고 했습니다. 이 때문에 프래그먼트를 만들 때도 액티비티를 만들 때의 과정과 비슷하게 진행합니다. 즉, 액티비티라는 것이 하나의 XML 레이아웃과 하나의 자바 소스 파일로 동작하는 것처럼 <b>프래그먼트도 하나의 XML 레이아웃과 하나의 자바 소스파일로 동작하게 만듭니다.</b>

- 다음 그림은 새로운 프래그먼트를 만들기 위해 하나의 자바 파일과 하나의 XML 레이아웃 파일을 만들어 화면에 보일 때까지의 과정을 간단하게 보여줍니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image5.png)

- 본격적인 실습을 진행하기 전에 위 과정에 대해 설명해 보겠습니다. 프래그먼트도 부분 화면이므로 화면에 뷰들을 배치할 때는 <b>XML 레이아웃</b>으로 만듭니다. 그다음에는 <b>프래그먼트를 위한 자바 소스</b>를 만듭니다. 프래그먼트를 위한 자바 스는 Fragment 클래스를 상속하여 만들 수 있습니다. 다음은 프래그먼트 클래스에 있는 주요 메서드들입니다.

- <b>Fragment </b>
	- <code>public final Activity getActivity()</code> : 이 프래그먼트를 포함하는 액티비티를 반환함.
	- <code>public final FragmentManager getFragmentManager()</code> : 이 프래그먼트를 포함하는 액티비티에서 프래그먼트 객체들과 의사소통하는 프레그먼트 매니저를 반환함.
	- <code>public final Fragment getParentFragment()</code> : 이 프레그먼트를 포함하는 부모가 프레그먼트일 경우 리턴함. 액티비티면 null을 반환함.
	- <code>public final int getId()</code> : 이 프래그먼트의 ID를 반환함.
	
- 프래그먼트를 위한 클래스까지 만들었다면 XML 레이아웃 파일의 내용을 소스 파일과 매칭하는 과정이 필요합니다. 그런데 프래그먼트에는 setContentView 메서드가 없습니다. 그래서 인플레이션 객체인 <b>LayoutInflater</b>를 사용해 인플레이션을 진행해야 합니다. XML 레이아웃 파일의 내용을 인플레이션한 후 클래스에서 사용하도록 하는 코드는 <b>onCreateView</b> 메서드 안에 들어갑니다. <b>onCreateView</b> 메서드는 콜백 메서드로 인플레이션이 필요한 시점에 자동으로 호출됩니다. 따라서 이 메서드 안에서 인플레이션을 위한 <b>inflate</b> 메서드를 호출하면 되고 인플레이션 과정이 끝나면 프래그먼트가 하나의 뷰처럼 동작할 수 있는 상태가 됩니다.

- 프래그먼트는 버튼이나 레이아웃처럼 화면의 일정 공간을 할당받을 수 있으므로 새로 만든 프래그먼트를 메인 액티비티에 추가하는 방법은 뷰와 마찬가지로 XML 레이아웃에 추가하거나 또는 소스 코드에서 new 연산자로 객체를 만든 후 프래그먼트 매니저로 추가할 수 있습니다. 좀 더 구체적으로 말하면, 메인 화면을 위해 만든 <b>activity_main.xml 파일에 직접 \<fragment\> 태그를 사용해 프래그먼트를 추가</b>할 수도 있고, <b>새로 정의한 프래그먼트 클래스의 인스턴스 객체를 new 연산자로 만든 후 FragmentManager 객체의 add 메서드를 사용해 액티비티에 추가</b>할 수도 있습니다.

- 메인 액티비티의 레이아웃 파일인 activity_main.xml 파일에 프래그먼트를 추가하면 프래그먼트 화면이 액티비티에 추가됩니다. 만약 코드에서 프래그먼트를 추가하고 싶다면 프래그먼트 관리를 담당하는 프래그먼트 매니저를 사용해야 합니다. 프래그먼트 매니저(FragmentManager) 클래스에 들어 있는 주요 메서드들은 다음과 같습니다.


- <b>FragmentManager</b>
	- <code>public abstract FragmentTransaction beginTransaction()</code> : 프래그먼트를 변경하기 위한 트랜잭션을 시작함.
	- <code>public abstract Fragment findFragmentById(int id)</code> : ID를 이용해 프래그먼트 객체를 찾음
	- <code>public abstract Fragment findFragmentByTag(String tag)</code> : 태그 정보를 사용해 프래그먼트 객체를 찾음.
	- <code>public abstract boolean executePendingTransactions()</code> : 트랜잭션은 commit 메서드를 호출하면 실행되지만 비동기(asynchronous) 방식으로 실행되므로 즉시 실행하고 싶다면 이 메서드를 추가로 호출해야 함.
	
- FragmentManager 객체는 프래그먼트를 액티비티에 추가(add), 다른 프래그먼트로 바꾸거나(replace) 또는 삭제(remove)할 때 주로 사용할 수 있으며 <b>getFragmentManager</b> 메서드를 호출하면 참조할 수 있습니다.

><b>getSupportFragmentManager 메서드가 getFragmentManager 메서드와 같은 기능을 합니다.</b><br>프래그먼트 매니저 객체를 참조하려면 getFragmentManager 메서드를 호출할 수도 있고 getSupportFragmentManager 메서드를 호출할 수도 있습니다. 이것은 이전 버전의 단말에서도 동작할 수 있도록 appcompat 라이브러리(Android Compatibility Library)의 기능을 사용하기 때문입니다. 일반적인 경우에는 예전 버전까지 호환되도록 만드는 것이 좋으므로 <b>getSupportFragmentManager</b> 메서드를 사용하는 것을 권장합니다.

- 프래그먼트는 다음처럼 뷰와 액티비티의 특성을 모두 가지고 있으며, 큰 화면과 해상도를 가진 태블릿의 경우에 더욱 유용하게 사용될 수 있습니다

- 프레그먼트의 대표적인 특성

|특성|설명|
|----|-------|
|뷰 특성|뷰 그룹에 추가되거나 레이아웃의 일부가 될 수 있음<br>(뷰에서 상속받은 것은 아니며 뷰를 담고 있는 일종의 틀임)|
|액티비티 특성|액티비티처럼 수명주기(Lifecycle)를 거치고 있음<br>(컨텍스트 객체는 아니며 수명주기는 액티비티에 종속됨)|

- 프래그먼트 클래스는 보통 Fragment 클래스를 상속하도록 만들지만 프래그먼트 중에는 미리 정의된 몇 가지 프래그먼트 클래스들이 있어 그 클래스를 그대로 사용할 때도 있습니다. 그중에서 <b>DialogFragment</b>는 액티비티의 수명주기에 의해 관리되는 대화상자를 보여줄 수 있도록 합니다. 이 프래그먼트는 액티비티의 기본 대화상자 대신 사용할 수 있습니다.

## 프래그먼트 만들어 화면에 추가하기

- 이제 새로운 프로젝트를 만들고 프래그먼트를 액티비티 화면에 추가해 보겠습니다. 먼저 프래그먼트를 만들고 프래그먼트를 위한 XML 레이아웃 안에 텍스트뷰와 버튼을 추가할 것입니다. 안드로이드 스튜디오의 시작화면에서 [Create New Project]를 눌러서 Empty Activity 화면을 선택한 후 프로젝트 이름을 SampleFragment로 입력하고 패키지 이름을 org.koreait.fragment라고 입력하여 프로젝트를 만드세요. 그런 다음 왼쪽 프로젝트 영역에서 app을 선택한 후 마우스 오른쪽 버튼을 클릭하고 [New → Fragment → Fragment (Blank)]를 선택해서 새로운 프래그먼트를 추가합니다.

> 지금까지는 프로젝트 이름을 입력했을 때 자동으로 입력되는 패키지 이름을 사용했습니다. 하지만 이렇게 하면 패키지 이름이 너무 길어질 수 있습니다. 따라서 지금부터는 패키지 이름을 새로 입력하도록 하겠습니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image6.png)

- 새로운 프래그먼트를 만들 수 있는 대화상자가 보이면 Fragment Name:에 입력된 글자를 MainFragment로 변경한 후 [Finish] 버튼을 누릅니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image7.png)

- fragment_main.xml 파일과 MainFragment.java 파일이 새로 추가됩니다. 프래그먼트도 화면에 표시되는 구성 요소이기 때문에 화면 레이아웃을 구성하는 XML 파일과 기능을 담당하는 소스 파일이 한 쌍으로 만들어진 것입니다. fragment_main.xml 파일을 살펴보면 최상위 레이아웃이 FrameLayout으로 만들어진다는 것을 확인할 수 있습니다. 디자인 화면의 Component Tree에서 최상위 레이아웃인 FrameLayout을 LinearLayout으로 바꾸고 그 안에 있던 텍스트뷰는 삭제합니다. LinearLayout의 orientation 속성은 vertical로 설정하고 텍스트뷰와 버튼을 하나씩 추가합니다. 텍스트뷰의 text 속성값은 '메인 프래그먼트'로 바꾸고 textSize 속성 값은 30sp로 설정합니다. 버튼의 text 속성 값으로 '메뉴 화면으로'라는 글자를 입력합니다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image8.png)

- 프래그먼트를 구성할 XML 레이아웃 파일을 만들었으니 이제 소스 코드를 확인합니다. MainFragment.java 파일을 열어보면 아주 복잡하고 많은 코드가 들어가 있습니다. 우리는 아주 간단한 코드만 사용할 것이므로 클래스 안에서 onCreateView 메서드를 제외한 나머지 코드는 모두 삭제합니다.

#### SampleFragment>/app/java/org.koreait.fragment/MainFragment.java

```java
package org.koreait.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
```

- <b>onCreateView</b> 메서드의 파라미터로 <b>LayoutInflater</b> 객체가 전달되므로 이 객체의 inflate 메서드를 바로 호출할 수 있습니다. inflate 메서드로 전달되는 첫 번째 파라미터는 XML 레이아웃 파일이 되므로 R.layout.fragment_main이 입력되어 있습니다. 두 번째 파라미터는 이 XML 레이아웃이 설정될 뷰그룹 객체가 되는데 onCreateView 메서드로 전달되는 두 번째 파라미터가 이 프래그먼트의 가장 상위 레이아웃입니다. 따라서 container 객체를 전달하면 됩니다. inflate 메서드를 호출하면 인플레이션이 진행되고 그 결과로 ViewGroup 객체가 반환됩니다. 이 객체를 return 키워드를 사용하여 반환합니다.

- 이제 새로운 프래그먼트가 만들어졌습니다. 앞서 언급했지만 이 프래그먼트를 메인 액티비티에 추가하는 방법은 두 가지입니다. 하나는 메인 액티비티의 XML 레이아웃에 태그로 추가하는 방법이고, 다른 하나는 메인 액티비티의 소스 코드에서 추가하는 방법입니다. 여기서는 태그로 추가해 보겠습니다. activity_main.xml 파일을 열고 다음과 같이 코드를 수정합니다.

#### SampleFragment>/app/res/layout/activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <fragment
        android:id="@+id/mainFragment"
        android:name="org.koreait.fragment.MainFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</FrameLayout>
```

- 최상위 레이아웃을 FrameLayout로 변경했으며 id 속성을 추가했습니다. id 속성 값은 container로 하고 \<FrameLayout\> 태그 안에 \<fragment\> 태그를 추가합니다. 프래그먼트는 뷰와 달라서 뷰를 담고 있는 공간만 확보합니다. 따라서 태그 이름으로 프래그먼트의 이름을 사용할 수 없으며, name 속성에 새로 만든 MainFragment의 이름을 설정합니다. 프래그먼트의 이름을 설정할 때는 패키지 이름을 포함한 이름으로 설정하고 프래그먼트의 id 값은 mainFragment로 합니다.

```xml
<View 클래스 추가 시 태그 추가 방법 예시>

<org.koreait.ui.view.MyView 
	android:layout_width="match_parent"
	android:layout_height="match_parent" />
	
```

```xml
<Fragment 클래스 추가 시 태그 추가 방법 예시>

<fragment
	android:name="org.koreait.ui.fragment.MyFragment"
	android:layout_width="match_parent" 
	android:layout_height="match_parent" />
```

><b>디자인 화면에서 FragmentContainerView를 사용해도 됩니다.</b><br>디자인 화면의 팔레트를 보면 common 그룹 안에 FragmentContainerView가 있습니다. 이것을 화면에 추가하면 fragment 태그를 직접 입력했던 것처럼 디자인 화면에서 프래그먼트를 추가할 수 있습니다. 화면에 끌어다 놓으면 어떤 프래그 먼트를 지정할 것인지 묻는 대화상자가 보입니다. 여기서 MainFragment를 선택하면 됩니다. 

- 이제 앱을 실행하면 다음 그림처럼 프래그먼트가 액티비티 화면 위에 보이게 됩니다.

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image9.png)

- 다음 그림을 보면 액티비티 위에 프래그먼트가 어떻게 올라가 있는지 쉽게 이해될 것입니다.

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image10.png)

- 액티비티를 위한 XML 레이아웃 위에 프래그먼트를 위한 XML 레이아웃이 올라가 있기 때문에 앱을 실행했을 때 여러분은 fragment_main.xml 파일 안에 만들어진 내용만 화면에서 볼 수 있습니다. 

## 버튼 클릭했을 때 코드에서 프래그먼트 추가하기

- 지금까지 새로운 프래그먼트를 만들어 액티비티 화면 위에 올리는 과정을 함께 진행해 보았습니다. 프래그먼트를 만들어 사용하는 과정을 간단하게 정리하면 다음과 같습니다.
	- (1) 프래그먼트를 위한 XML 레이아웃 만들기
	- (2) 프레그먼트 클래스 만들기
	- (3) 프래그먼트를 액티비티의 XML 레이아웃에 추가하기
	
- 그런데 화면은 XML과 소스 코드가 분리되어 있습니다. 따라서 화면에 뷰를 추가하는 방법도 XML 레이아웃에 추가하는 방법과 소스 코드를 사용해 추가하는 방법으로 나뉩니다.
- 화면에 뷰를 추가하는 방법 2가지
	- (1) XML 레이아웃에 추가하는 방법
	- (2) 자바 소스 코드로 추가하는 방법
	

- 프래그먼트도 새로 만든 프래그먼트를 액티비티의 XML 레이아웃에 넣어 화면에 추가하는 것뿐만 아니라 코드에서 직접 추가하는 것도 가능합니다. 코드에서 프래그먼트를 추가할 때는 프래그먼트 매니저에게 요청해야 한다는 것은 이미 앞에서 설명했습니다. 그러면 이번에는 MenuFragment라는 이름의 새로운 프래그먼트를 하나 더 만든 후 MainFragment 안에 있는 버튼을 클릭했을 때 이 MenuFragment 화면으로 바뀌도록 해 보겠습니다.

- 이번에는 app에서 마우스 오른쪽 버튼을 눌러 프래그먼트를 만들어주는 메뉴를 사용하지 않고 XML 레이아웃과 소스 파일을 각각 만드는 방법으로 프래그먼트를 추가해 보겠습니다. 먼저 /app/res/layout 폴더 안에 있는 fragment_main.xml 파일을 복사하여 fragment_menu.xml 파일로 만든 후 화면에 있는 텍스트뷰의 글자를 '메뉴 프래그먼트'로 바꿉니다. 버튼의 글자도 '메인 화면으로’로 변경하고 배경색도 디자인 화면에서 주황색 계열로 설정합니다.

- 파일을 복사하기 위해서는 파일(fragment_main.xml)을 선택한 다음 [Ctrl] + [C] 를 누르고 [Ctrl] + [V ]를 누릅니다. 파일을 복사하면 동일한 이름의 파일이 있으니 파일 이름을 수정하라는 대화상자가 나타나는데 파일 이름(fragment_menu)을 입력하고 [OK] 버튼을 누르면 됩니다.

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image11.png)

- 이제 /app/java/org.koreait.fragment 폴더 안에 들어 있는 MainFragment.java 파일을 복사하여 MenuFragment.java 파일로 만든 후 코드를 다음과 같이 변경합니다.

#### SampleFragment>/app/java/org.koreait.fragment/MenuFragment.java

```java
package org.koreait.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
```

- inflate 메서드로 전달되는 첫 번째 파라미터의 값이 R.layout.fragment_menu 로 바뀌었으므로 이 MenuFragment 클래스에는 fragment_menu.xml 파일의 내용이 인플레이션되어 설정됩니다. 메뉴 화면을 위한 프래그먼트를 만들었으므로 이제 메인 프래그먼트에 안에 들어 있는 버튼을 클릭했을 때 메뉴 프래그먼트로 전환되도록 만들어야 합니다.

- 다음을 참고하여 메인 프래그먼트의 onCreateView 메서드를 수정하세요.

#### SampleFragment>/app/java/org.koreait.fragment/MainFragment.java

```java

... 생략

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(0);
            }
        });
        return rootView;
    }
}
```

#### SampleFragment>/app/java/org.koreait.fragment/MenuFragment.java

```java

... 생략

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);

       Button button = rootView.findViewById(R.id.button);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               MainActivity activity = (MainActivity) getActivity();
               activity.onFragmentChanged(1);
           }
       });
       return rootView;
    }
}
```

- 코드를 작성하면 getActivity 메서드와 onFragmentChanged 메서드가 빨간색으로 표시될 것입니다. 이 메서드들은 추가로 작성할 것이므로 걱정하지 않아도 됩니다. 일단 지금까지 입력한 코드에 대해 먼저 설명하겠습니다.

- 메인 프래그먼트 안에 표시되는 최상위 레이아웃은 인플레이션을 통해 참조한 rootView 객체입니다. 이 관계를 조금 쉽게 설명하면 최상위 레이아웃(rootView)은 메인 프래그먼트 안에 들어 있는 것이고 메인 프래그먼트는 이 레이아웃을 화면에 보여주기 위한 틀이라고 생각할 수 있습니다. 그래서 rootView의 findViewById 메서드를 사용하여 레이아웃에 들어 있는 버튼 객체를 찾아낼 수 있죠. 그리고 이 객체의 setOnClickListener 메서드를 호출하여 리스너를 등록하면 버튼이 클릭되었을 때 이벤트를 처리할 수 있습니다. onClick 메서드 안에서는 MainActivity 객체를 참조한 후 onFragmentChanged 메서드를 호출하도록 합니다. onFragmentChanged 메서드는 메인 액티비티에 새로 추가할 메서드로 프래그먼트 매니저를 이용해 프래그먼트를 전환하는 메서드입니다. 이렇게 코드를 입력한 이유는 프래그먼트가 액티비티를 본떠 만들었고 액티비티 관리를 시스템에서 하는 것처럼 프래그먼트 관리를 액티비티가 하기 때문에 액티비티에서 프래그먼트를 전환하도록 만들어야 하기 때문입니다. 다시 말해, 하나의 프래그먼트에서 다른 프래그먼트를 직접 띄우는 것이 아니라 액티비티를 통해 띄워야 합니다.

- 프래그먼트에서는 getActivity 메서드를 호출하면 프래그먼트가 올라가 있는 액티비티가 어떤 것인지 확인할 수 있습니다. 이제 액티비티에 onFragmentChanged 메서드를 포함한 나머지 필요한 코드를 MainActivity.java 파일에 추가합니다.


#### SampleFragment>/app/java/org.koreait.fragment/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment = new MenuFragment();
    }

    public void onFragmentChanged(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();

        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
        }
    }
}
```

- 메인 프래그먼트는 액티비티를 위한 activity_main.xml 파일에 추가되어 있으므로 id를 사용해서 찾아야 합니다. 그런데 프래그먼트는 뷰가 아니라서 Activity 클래스에 있는 findViewById 메서드로 찾을수 없습니다. 대신 프래그먼트를 관리하는 <b>FragmentManager</b> 객체의 <b>findFragmentById</b> 메서드를 사용해서 찾을 수 있습니다. 메인 프래그먼트는 <b>findFragmentById</b> 메서드를 사용해 찾은 후 변수에 할당하고 메뉴 프래그먼트는 new 연산자를 사용해 새로운 객체로 만들어 변수에 할당합니다.

- onFragmentChanged 메서드는 프래그먼트에서 호출할 수 있도록 정의한 것으로 파라미터로 전달된정수의 값이 0이면 메인 프래그먼트가 보이게 하고, 1이면 메뉴 프래그먼트가 보이게 할 수 있습니다. 이 메서드 안에서는 FragmentManager 객체의 replace 메서드를 사용해 프래그먼트를 바꾸도록 입력합니다. replace 메서드로 전달되는 첫 번째 파라미터는 프래그먼트를 담고 있는 레이아웃의 id가 되어야 하므로 R.id.container를 전달합니다.

- 그런데 이 코드에서 볼 수 있는 것처럼 <b>프래그먼트 매니저 객체를 사용할 때는 트랜잭션이 사용</b>됩니다. 다음 그림은 액티비티와 프래그먼트가 의사소통하는 방식을 보여줍니다.

![image12](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image12.png)

- 메인 액티비티에서 프래그먼트를 다루기 위해서는 먼저 getSupportFragmentManager 메서드를 호출하여 매니저 객체를 참조합니다.

```java
public FragmentManager getSupportFragmentManager()
```

- 프래그먼트 매니저는 프래그먼트를 다루는 작업을 해 주는 객체로 프래그먼트 추가, 삭제 또는 교체 등의 작업을 할 수 있게 합니다. 그런데 <b>이런 작업들은 프래그먼트를 변경할 때 오류가 생기면 다시 원래상태로 돌릴 수 있어야 하므로 트랜잭션 객체를 만들어 실행</b>합니다. 트랜잭션 객체는 <b>beginTransaction</b> 메서드를 호출하면 시작되고 <b>commit</b> 메서드를 호출하면 실행됩니다.

- 이제 앱을 실행하고 처음 보이는 메인 프래그먼트 화면에서 버튼을 누르면 메뉴 프래그먼트 화면으로전환되는 것을 확인할 수 있습니다.

![image13](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image13.png)

- 프래그먼트를 두 개 만들고 하나의 프래그먼트에서 다른 프래그먼트로 전환하는 과정을 알아보았습니다. 코드가 조금 더 복잡해지긴 했지만 프래그먼트가 어떤 방식으로 처리되는지 이해한다면 그리 어렵지 않을 것입니다.


## 프래그먼트의 수명주기

- 프래그먼트 간에 전환하는 기능까지 살펴보았으니 프래그먼트가 무엇이고 어떻게 사용하는지 어느 정도 이해했을 것입니다. 그런데 프래그먼트 클래스를 정의할 때 XML 레이아웃의 내용을 인플레이션하는 코드가 들어 있는 메서드의 이름이 <b>onCreateView</b>라고 했습니다. 이 이름은 액티비티의 onCreate와 비슷한 이름입니다. 짐작했겠지만 이 메서드는 <b>onCreate 메서드처럼 어떤 상태가 되면 자동으로 호출되는 메서드</b>입니다.

- 프래그먼트는 액티비티를 본떠 만들면서 액티비티처럼 독립적으로 동작하도록 수명주기(Life Cycle)메서드를 추가했습니다. 따라서 상태에 따라 API에서 미리 정해둔 콜백 함 호출되므로 그 안에 필요한 기능을 넣을 수 있습니다. 프래그먼트를 사용하는 목적 중의 하나가 분할된 화면들의 상태를 관리하는 것인데 이것을 가능하게 하는 것이 수명주기 메서드들입니다. 즉, 액티비티 안에 들어 있는 프래그먼트도 필요할 때 화면에 보이거나 보이지 않게 되므로 액티비티처럼 각각의 상태가 관리되는 것이 필요합니다.

- 프래그먼트는 액티비티 위에 올라가는 것이므로 프래그먼트의 수명주기도 <b>액티비티의 수명주기에 종속적이지만 프래그먼트만 가질 수 있는 독립적인 상태 정보들이 더 추가</b>되었습니다. 특히 프래그먼트가 화면에 보이기 전이나 중지 상태가 되었을 때 액티비티처럼 onResume 메서드와 onPause 메서드가 호출되는데 프래그먼트는 액티비티에 종속되어 있으므로 이 상태 메서드 이외에도 세분화된 상태메서드들이 더 있습니다.

- 프래그먼트가 화면에 보이기 전까지 호출될 수 있는 수명주기 메서드들은 다음과 같습니다.

- 화면에 보이기 전에 호출되는 상태 메서드

|메서드|설명|
|----|------|
|onAttach(Activity)|프래그먼트가 액티비티와 연결될 때 호출됨.|
|onCreate(Bundle)|프래그먼트가 초기화될 때 호출됨<br>(new 연산자를 이용해 새로운 프래그먼트 객체를 만드는 시점이 아니라는 점에 주의해야 함)|
|onCreateView(LayoutInflater, ViewGroup, Bundle)|프레그먼트와 관련되는 뷰 계층을 만들어서 리턴함.|
|onActivityCreated(Bundle)|프레그먼트와 연결된 액티비티가 onCreate 메서드의 작업을 완료했을 때 호출됨.|
|onStart()|프래그먼트와 연결된 액티비티가 onStart되어 사용자에게 프래그먼트가 보일 때 호출됨.|
|onResume()|프레그먼트와 연결된 액티비티가 onResume 되어 사용자와 상호작용할 수 있을 때 호출됨.|

- 표에서 확인한 메서드들은 프래그먼트가 처음 만들어지고 화면에 나타나기 전에 호출되는 메서드들입니다. 액티비티가 메모리에 처음 만들어질 때는 onCreate 메서드가 호출된다는 것은 잘 알고 있습니다. 이와 마찬가지로 프래그먼트도 초기화될 때 onCreate 메서드가 호출됩니다.

- 그런데 한가지 주의할 점이 있습니다. 프래그먼트는 액티비티 안에 추가되어 사용되면서 동시에 액티비티에 종속되어 있어 프래그먼트와 액티비티가 연결되어야 초기화될 수 있습니다. 즉, <b>프래그먼트는 액티비티 위에 올라가야 제대로 동작합니다.</b> 이 때문에 new 연산자를 사용해 프래그먼트 객체를 만드는 시점과 onCreate 메서드가 호출되는 시점이 달라집니다. 이 과정을 순서대로 보면 먼저 onAttach 메서드가 호출되며 액티비티에 프래그먼트가 추가되고 그 다음에 onCreate 메서드가 호출됩니다. 다시 말해 onAttach 메서드가 호출될 때 파라미터로 전달되는 액티비티 객체 위에 프래그먼트가 올라가 있게 됩니다. 그러므로 액티비티를 위해 설정해야 하는 정보들은 이 onAttatch 메서드에서 처리해야 합니다.

- 프래그먼트가 새로 만들어질 때 그 안에 뷰들을 포함하게 되면 뷰그룹처럼 뷰들의 레이아웃을 결정해주어야 합니다. <b>onCreateView</b> 메서드는 <b>프래그먼트와 관련되는 뷰들의 계층도를 구성하는 과정에서 호출</b>됩니다.

- 액티비티가 메모리에 처음 만들어질 때는 액티비티의 onCreate 메서드가 호출되지만 프래그먼트의 경우에는 onActivityCreated 메서드가 호출됩니다. 이 메서드는 프래그먼트에서 액티비티가 만들어지는 상태를 알 수 있도록 해주는데 프래그먼트에서 다시 정의해 둔 onCreate 메서드와 구별해야 합니다.

- 다음은 프래그먼트가 화면에서 보이지 않게 되면서 호출되는 상태 메서드들입니다.
- 중지되면서 호출되는 상태 메서드

|메서드|설명|
|----|------|
|onPause()|프래그먼트와 연결된 액티비티가 onPause()되어 사용자와 상호작용을 중지할 때 호출됨.|
|onStop()|프래그먼트와 연결된 액티비티가 onStop되어 화면에서 더 이상 보이지 않을 때나 프래그먼트의 기능이 중지되었을 때 호출됨.|
|onDestroyView()|프레그먼트와 관련된 뷰 리소스를 해제할 수 있도록 호출됨|
|onDestroy()|프레그먼트의 상태를 마지막으로 정리할 수 있도록 호출됨.|
|onDetach()|프레그먼트가 액티비티와 연결을 끊기 바로 전에 호출됨.|

- onPause와 onStop 메서드는 액티비티의 onPause onStop 메서드가 호출될 때와 같은 상태 메서드입니다. onDestroyView 메서드는 프래그먼트 안에 들어 있는 뷰들의 리소스를 해제할 때 재정의하여 사용하며, onDetach 메서드는 onAttach와 반대로 프래그먼트가 액티비티와 연결을 끊기 바로 전에 호출됩니다.

![image14](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image14.png)

- 프래그먼트의 수명주기 메서드 중에서 눈에 띄는 것이 <b>onAttach</b>와 <b>onDetach</b>입니다. 이 메서드는 프래그먼트가 액티비티 위에 올라갈 때와 액티비티에서 떨어져 나올 때 호출됩니다. <b>이 메서드가 수명주기의 시작과 끝이 되는 이유는 프래그먼트는 액티비티 위에 올라가지 않고서는 프래그먼트로서 동작하지 않기 때문이라는 점</b>을 다시 한 번 기억하세요. 그리고 <b>프래그먼트 객체가 new 연산자로 만들어졌더라도 액티비티 위에 올라가기 전까지는 프래그먼트로 동작하지 않는다는 점</b>도 기억해야 합니다.

```java
MyFragment fragment = new MyFragment();
-> 프래그먼트 객체는 만들어졌지만 프래그먼트로 동작하지는 않음.

getSupportFragmentManager().beginTransaction().add(fragment).commit();
-> 액티비티에 추가된 후 프래그먼트로 동작함.
```

- 프래그먼트의 수명주기 메서드가 호출되는 것을 확인해 보고 싶다면 프래그먼트 안에서 각각의 메서드를 재정의한 후 그 안에서 로그를 출력하도록 하거나 또는 토스트 메시지를 띄우게 하면 됩니다.

* * * 
# 프래그먼트로 화면 만들기

- 이번에는 한 화면에 두 개의 프래그먼트가 들어가도록 만들어 보겠습니다. 화면의 위쪽과 아래쪽을 나눈 후 위쪽에는 이미지 선택이 가능한 리스트가 보이는 프래그먼트를 만들어 넣고 아래쪽에는 선택된이미지가 보이는 프래그먼트를 만들어 넣을 것입니다. 그러면 위쪽 프래그먼트에서 선택한 이미지가 어떤 것인지를 액티비티에 알려준 후 액티비티에서 아래쪽 프래그먼트에 해당 이미지가 보이게 만들어야 합니다.

- 새로운 SampleFragment2 프로젝트를 만듭니다. 프로젝트를 만들 때 패키지 이름은 org.koreait.fragment로 지정합니다. 그리고 새로운 프로젝트 창이 만들어지면 학습 소스(SampleFragment2)에서 제공하는 세 개의 이미지(dream01.png, dream02.png, dream03.png)를 /app/res/drawable 폴더 안에 복사합니다.

![image15](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image15.png)

- 먼저 /app/res/layout 폴더 안에 첫 번째 프래그먼트를 위한 XML 레이아웃을 fragment_list.xml라는이름으로 만듭니다. 최상위 레이아웃은 LinearLayout으로 설정하면 orientation 속성 값은 vertical로 설정되어 만들어집니다. 그 안에 세 개의 버튼을 추가합니다. 각 버튼의 text 속성에는 '첫 번째 이미지', '두 번째 이미지', '세 번째 이미지'로 글을 수정합니다.

![image16](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image16.png)

- 그런 다음 MainActivity 파일을 복사해서 소스 파일인 ListFragment.java을 추가합니다. 추가한 파일은 다음과 같이 Fragment 클래스를 상속하도록 한 후 onCreateView 메서드 안에서 fragment_list.xml 파일을 인플레이션합니다.

#### SampleFragment2>/app/java/org.koreait.fragment/ListFragment.java

```java
package org.koreait.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {

    public static interface ImageSelectionCallback {
        public void onImageSelected(int position);
    }

    public ImageSelectionCallback callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof ImageSelectionCallback) {
            callback = (ImageSelectionCallback) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);
        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onImageSelected(0);
                }
            }
        });

        Button button2 = rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onImageSelected(1);
                }
            }
        });

        Button button3 = rootView.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onImageSelected(2);
                }
            }
        });
        return rootView;
    }
}
```

- 각각의 버튼을 클릭했을 때는 callback 객체의 onImageSelected 메서드를 호출합니다. onAttach 메서드는 프래그먼트가 액티비티 위에 올라오는 시점에 호출됩니다. 그래서 프래그먼트에서 해당 액티비티를 참조하고 싶다면 onAttach 메서드로 전달되는 파라미터를 참조하거나 getActivity 메서드를 호출하여 반환되는 객체를 참조할 수 있습니다. 그리고 그 객체를 변수에 할당하면 프래그먼트 클래스 안에서 자유롭게 액티비티 객체를 참조할 수 있게 됩니다.

- 여기서는 onAttach 메서드가 호출될 때 callback 변수에 객체가 할당되었는데 그 자료형이 Activity가 아니라 ImageSelectionCallback 입니다. 왜 callback 변수의 자료형을 ImageSelectionCallback으로 선언한 것일까요?

- 화면에서 선택된 버튼에 따라 다른 프래그먼트의 이미지를 바꿔주려면 액티비티 쪽으로 데이터를 전달해야 하므로 액티비티에 onImageSelected 메서드를 정의한 후 그 메서드를 호출하도록 합니다. 그런데 매번 액티비티마다 다른 이름의 메서드를 만들면 프래그먼트가 올라간 액티비티가 다른 액티비티로 변경되었을 때 해당 액티비티가 무엇인지 매번 확인해야 하는 번거로움이 있습니다 이 때문에 인터페이스를 하나 정의한 후 액티비티가 이 인터페이스를 구현하도록 하는 것이 좋습니다. 여기에서는 ImageSelectionCallback 인터페이스를 정의했으며 onImageSelected 메서드는 이 인터페이스 안에 정의했습니다

- 만약 MainActivity가 이 인터페이스를 구현하도록 했다면 이 프래그먼트에서는 액티비티 객체를 참조할 때 인터페이스 타입으로 참조한 후 onImageSelected 메서드를 호출할 수 있습니다. onAttach 메서드 안에서는 MainActivity 객체를 참조한 후 ImageSelectionCallback 타입으로 된 callback 변수에 할당합니다. 그러면 버튼이 클릭되었을 때 callback 변수에 할당된 액티비티 객체의 onImageSelected 메서드를 호출할 수 있습니다.

- 이제 이미지를 보여줄 뷰어 프래그먼트를 만듭니다. XML 레이아웃 파일의 이름은 fragment_viewer.xml로 하여 /app/res/layout 폴더 안에 추가합니다. 최상위 레이아웃은 LinearLayout으로 설정하면 orientation 속성 값은 vertical로 설정한 후 그 파일 안에 ImageView를 추가합니다. 팔레트에서 ImageView를 화면에 끌어다 넣으면 [Resources] 대화상자가 나타납니다. Drawable 폴더에 추가했던 dream01 이미지를 Project 항목에서 찾아서 넣으면 됩니다. ImageView 태그는 이미지를 화면에 보여주는 역할을 하며, layout_width 속성은 match_parent로 설정하고 layout_height 속성은 wrap_content로 설정하면 이미지가 화면 상단을 차지하게 됩니다. ImageView 객체의 src 속성에는 @drawable/dream01을 설정하여 첫 번째 이미지가 기본으로 보이도록 합니다.

![image17](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image17.png)

- 자바 소스 파일의 이름을 ViewerFragment.java로 작성하여 /app/java/org.koreait.fragment 폴더 안에 만들고 다음과 같이 코드를 입력합니다.

#### SampleFragment2>/app/java/org.koreait.fragment/ViewerFragment.java

```java
package org.koreait.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewerFragment extends Fragment {
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container, false);

        imageView = rootView.findViewById(R.id.imageView);
        return imageView;
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }
}
```

- ViewerFragment.java 파일의 코드는 그리 복잡하지 않습니다. onCreateView 메서드 안에서 인플레이션을 진행하고 나면 이미지뷰 객체를 찾아 imageView라는 이름의 변수에 할당하도록 합니다. 그리고 setImage 메서드를 만들어 액티비티에서 이 프래그먼트에 있는 이미지뷰에 이미지를 설정할 수 있도록 합니다.

- 이제 두 개의 프래그먼트를 만들었습니다. 프래그먼트가 두 개이니 새로 만들어진 XML 레이아웃 파일도 두 개이고 소스 파일도 두 개라는 것을 이해했을 것입니다. 그러면 이 프래그먼트들을 메인 액티비티의 XML 레이아웃에 추가합니다. activity_main.xml 파일을 열고 두 개의 프래그먼트를 추가합니다.

#### SampleFragment2>/app/res/layout/activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <fragment
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:name="org.koreait.fragment.ListFragment"
        android:id="@+id/listFragment"
        />

    <fragment
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:name="org.koreait.fragment.ViewerFragment"
        android:id="@+id/viewerFragment"
        />
    
</LinearLayout>
```

- 리니어 레이아웃 안에 두 개의 프래그먼트를 추가한 후 layout_weight 속성으로 화면을 절반씩 나눠갖도록 만들었습니다. 이제 마지막으로 MainActivity.java 파일의 내용을 수정합니다.

#### SampleFragment2>/app/java/org.koreait.fragment/MainActivity.java

```java
package org.koreait.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback {

    ListFragment listFragment;
    ViewerFragment viewerFragment;

    int[] images = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
        viewerFragment = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);
     }

    @Override
    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }
}
```

- onCreate 메서드 안에서 FragmentManager 객체의 findFragmentById 메서드를 사용해 두 개의 프래그먼트를 찾아 변수에 할당합니다. 이 MainActivity ImageSelectionCallback 인터페이스를 구현하도록 만들고 이 인터페이스에 정의된 onImageSelected 메서드도 구현합니다. 이미 잘 알고 있는 것처럼 onImageSelected 메서드는 리스트 프래그먼트에서 호출하게 되며 onImageSelected 메서드가 호출되면 뷰어 프래그먼트의 setImage 메서드를 호출하여 이미지가 바뀌도록 합니다.

- 이제 앱을 실행하고 상단에 있는 리스트 프래그먼트 안의 아이템을 클릭하면 아래쪽의 이미지가 바뀌는 것을 확인할 수 있습니다.

![image18](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/1.%20%ED%94%84%EB%A0%88%EA%B7%B8%EB%A8%BC%ED%8A%B8/images/image18.png)

- 리스트 프래그먼트에는 버튼들이 들어 있지만 선택해야 하는 항목이 늘어난다면 리싸이클러뷰를 사용해도 됩니다. 리싸이클러뷰는 나중에 다시 알아보도록 하고 지금까지는 프래그먼트에 대해서만 알아보았습니다.  프래그먼트는 UI를 만드는 데 필수 요소이니 잘 알아두는 것이 좋습니다.