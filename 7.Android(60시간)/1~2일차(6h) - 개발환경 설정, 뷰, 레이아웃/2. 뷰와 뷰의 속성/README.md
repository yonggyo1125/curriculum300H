# 뷰와 뷰의 크기 속성 이해하기

## 뷰의 속성 

- 뷰(View)는 화면에 들어가는 각각의 요소를 말하며 일반적으로 컨트롤이나 위젯이라고 불립니다.
- 즉, 사용자의 눈에 보이는 화면의 구성 요소들이 바로 뷰입니다. 이러한 뷰를 여러개 포함하고 있는 것을 뷰 그룹(ViewGroup)이라고 하며, 이 뷰 그룹 안에서 뷰의 위치를 지정할 수 있습니다. 
- 뷰 그룹은 여러개의 뷰를 담고 있는 그릇과 같아서 뷰와는 약간 다르지만 뷰 그룹 안에는 뷰 뿐만 아니라 또 다른 뷰 그룹도 넣을 수 있습니다. 
- 이것은 뷰그룹이 뷰를 상속하여 만들어졌기 때문입니다. 
- 여러개의 뷰를 담고 있는 뷰그룹 역시 하나의 뷰라고 했을 때 또 다른 뷰그룹이 이 뷰그룹을 뷰처럼 다룰 수 있습니다. 이러한 뷰와 뷰그룹의 관계는 여러 가지 디자인 패턴 중에 컴포지트 패턴(Composite Pattern)을 사용하여 만들어진 것입니다. 
- 컴포지트 패턴으로 만들어진 뷰 그룹은 뛰어난 확장성을 가집니다. 다음은 컴포지트 패턴으로 정의된 뷰와 뷰그룹의 관계를 표현하고 있습니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image1.png)

- 뷰가 뷰그룹을 상속하게 되면 뷰그룹도 뷰가 되므로 뷰그룹 안에 또 다른 뷰그룹을 가지고 있을 수 있습니다. 왜냐하면 뷰그룹은 뷰를 담을 수 있는데 담고 있는 뷰 중 하나가 뷰그룹이면 그 안에 다른 뷰들이 또 들어갈 수 있기 때문입니다. 
- 이런 뷰와 뷰 그룹의 관계는 안드로이드의 UI를 매우 자연스럽게 구성할 수 있게 합니다.
- 이외에도 뷰는 다른 뷰의 속성을 상속하여 상속받은 뷰의 특성을 그대로 가질 수도 있습니다. 예를 들어, 버튼은 텍스트뷰를 상속해서 만들었기 때문에 텍스트뷰의 속성을 그대로 갖고 있습니다.

## 위젯과 레이아웃으로 구별되는 뷰

- 안드로이드는 화면을 구성하는 것들을 크게 뷰와 뷰그룹으로 나눌 수 있다고 했는데, 각각의 역할을 구분하기 위해 뷰의 종류에 따라 다른 이름으로 부르기도 합니다. 
- 뷰 중에서 화면에 보이면서 일반적인 컨트롤의 역할을 하는 것을 <b>위젯(Widget)</b>이라고 부르며, 
- 뷰그룹 중에서 내부에 뷰들을 포함하고 있으면서 그것들을 배치하는 역할을 하는 것을 <b>레이아웃(Layout)</b>이라고 부릅니다.

> 화면 구성 요소는 뷰, 사용자 눈에 보이는 컨트롤 역할을 하는 뷰를 위젯이라고 생각하면 됩니다. 뷰그룹과 레이아웃도 비슷한 방법으로 구분합니다. 뷰를 담고 있는 그릇은 뷰그룹, 뷰그룹 안의 뷰를 배치하는 것을 레이아웃이라고 생각하면 됩니다. 

- 뷰가 화면을 구성하는 기본 요소이므로 뷰로 정의된 클래스의 수는 굉장히 많습니다. 또한 뷰로 정의된 각각의 클래스에 정의된 속성과 메서드도 아주 많습니다. 그래서 클래스를 사용하기 위해 클래스에 정의된 속성과 메서드를 다 외우기 보다는 뷰로 정의된 클래스의 기초 속성이나 메서드를 기억했다가 그것들을 중심으로 활용하는 것이 좋습니다. 예를 들어, 가장 기초적인 클래스인 텍스트뷰(TextView)와 버튼(Button)의 속성을 먼저 살펴보는 것이 좋습니다. 이들의 속성을 알고 나면 다른 뷰나 위젯의 속성도 쉽게 이해할 수 있습니다.

- 뷰그룹도 마찬가지입니다. 뷰그룹 중에서 뷰들의 배치에 사용되는 대표적인 레이아웃들을 살펴보면 화면 배치에 필요한 내용들은 대부분 쉽게 사용할 수 있습니다.
- 다음은 버튼과 리니어 레이아웃의 계층도를 보여주고 있습니다. 버튼은 위젯 중에서 자주 사용되며 리니어 레이아웃은 레이아웃 중에서 자주 사용되는 것입니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image2.png)

- 계층도를 보면 뷰를 배치하는 레이아웃도 뷰를 상속받은 것입니다. 즉, 뷰가 가지는 속성은 레이아웃에도 있습니다. 따라서 레이아웃(#1)안에 다른 레이아웃(#1-1, #1-2)이 들어갈 수 있습니다. 

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image3.png)

- 레이아웃은 레이아웃이 담고 있는 위젯이 어디에 배치되어야 할지 정해줍니다. 따라서 레이아웃 안에 레이아웃을 넣으면 각각의 레이아웃 안에서 버튼과 같은 위젯의 위치를 잡을 수 있습니다.

## 뷰의 크기 속성

- 화면에 추가할 수 있는 모든 것을 뷰라고 한다면 뷰는 다시 위젯과 레이아웃으로 구분할 수 있습니다. 뷰는 화면의 일정 영역을 차지하기 때문에 모든 뷰는 반드시 크기 속성을 가지고 있어야 합니다. 만약 뷰의 가로, 세로 크기 속성이 없으면 안드로이드는 XML 레이아웃이 잘못되었다고 판단하고 오류를 출력합니다. 

- 디자이너 도구 아래쪽의 [Text] 탭을 눌러 XML 원본을 살펴보면 태그의 속성으로 구성되어 있습니다. 태그는 꺽쇠 모양의 기호 안에 글자가 들어간 형태이며, 시작 태그와 끝 태그로 구성됩니다. 끝 태그는 꺽쇠 안에 / 기호가 추가가 붙어 있습니다. 시작 태그 안에는 공백을 구분으로 하는 속성이 들어 있을 수 있습니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image4.png)

- 텍스트뷰는 \<TextView\>라는 태그를 사용합니다. 그리고 \<TextView까지 입력해서 시작 태그임을 알려주며 그 아래에 여러 개의 속성을 넣은 후 /\>기호를 사용해 끝났음을 알려줍니다. 시작 태그와 끝 태그는 분리해서 사용할 수 있지만 이렇게 /\> 기호만 붙여서 하나의 태그로 정의할 수도 있습니다. 

- 하지만 가장 바깥에 있는 \<ContraintLayout\> 태그를 보면 조금 다릅니다. 이 태그는 다른 뷰를 담고 있는 레이아웃인데 그 안에 뷰를 위한 태그들이 들어가야 하므로 시작 태그와 끝 태그를 분리해서 사용할 수 밖에 없습니다.

- 태그 안에 들어간 속성들은 모두 공백이나 줄 바꿈으로 구별됩니다. 그리고 대부분 android이라는 단어가 앞에 붙습니다. 
- android:은 안드로이드 기본 API에서 정의한 속성이라는 의미입니다. 만약 직접 정의했거나 외부 라이브러리라(Third-party Library)를 사용했을 때 그 안에 정의된 속성이라면 android:가 아닌 다른 단어(예 : app:)가 속성 앞에 붙을 수도 있습니다.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image5.png)

- 따라서 속성을 구분할 때는 android: 처럼 속성 이름 앞에 붙어 있는 접두어(Prefix)는 생략하고 생각해도 됩니다. 그런데 이 속성들 중에서 공통된 속성이 보입니다. 바로 다음과 같은 두 가지 속성입니다.

```
layout_width와 layout_height -> 뷰의 폭과 높이를 설정합니다.
```

- 이 속성은 뷰가 가질 수 있는 내부 속성 중에서 필수 속성입니다. 뷰가 화면 안에 들어있으려면 먼저 뷰의 크기와 위치가 결정되어야 하므로 필수 속성입니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image6.png)

- XML 레이아웃에 추가된 뷰의 속성 중에 위 두가지 속성(layout_width와 layout_height)이 들어있지 않았을 때 오류가 발생합니다.

- 가로와 세로 크기의 값으로는 다음과 같은 세 가지 값 중에 하나가 들어갈 수 있습니다.

|구분|설명|
|----|----------|
|wrap_content|뷰에 들어 있는 내용물의 크기에 자동으로 맞춥니다.|
|match_parent|뷰를 담고 있는 뷰그룹의 여유 공간을 꽉 채웁니다.|
|숫자로 크기 지정|숫자를 사용해 크기를 지정합니다. 크기는 dp나 px와 같은 단위가 꼭 있어야 합니다.|

## 버튼의 크기 속성 바꿔보기

- 화면에 추가한 버튼의 layout_width, layout_height 속성을 변경해 봅니다. [Design] 아이콘을 눌러 디자인 화면으로 전환한 후 화면에 버튼 한개를 추가한 후 선택합니다. 
- 오른쪽 속성 창에서 layout_width와 layout_height 속성 값을 확인합니다. 두 개의 속성 값 모두 wrap_content로 되어 있는데, 이 값으로 설정하면 버튼의 내용물에 맞게 크기가 자동으로 결정됩니다.
-  버튼의 내용물은 글자, 즉 텍스트(Text)이므로 TextView에 있는 text 속성을 찾아 '안녕하세요, 반갑습니다!'로 변경합니다. 그러면 버튼의 가로 크기가 자동으로 늘어나는 것을 확인할 수 있습니다.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image7.png)

- 왼쪽 아래의 Component Tree 창을 보면 눈에 보이지는 않지만 이 화면에 어떤 뷰들이 계층구조로 만들어져 있는지 알 수 있습니다. 
- 버튼이 ContraintLayout 안에 들어 있으니 보이지 않는 레이아웃이 화면 전체를 차지하고 있다는 것도 알수 있습니다.
- layout_width 속성 값에 '240dp'를 입력해서 바꿔봅니다. 버튼의 크기가 입력된 값으로 변경되는 것을 확인할 수 있습니다.

![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image8.png)

- 버튼의 layout_height 속성 값을 match_contraints로 변경하면 약간의 변화는 있지만 크기는 그대로 입니다. 이는 버튼을 담고 있는 레이아웃이 제약 레이아웃(ContraintLayout)이기 때문입니다. 
- 만약 이 레이아웃을 리니어 레이아웃(LinearLayout)이나 상대 레이아웃(RelativeLayout)으로 변경하면 다음 그림처럼 wrap_content와 match_parent라는 값을 설정했을 때의 차이를 분명하게 이해할 수 있습니다.

![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image9.png)

- 레이아웃을 리니어 레이아웃으로 변경해 보겠습니다.
- 왼쪽 아래의 Component Tree 창에서 가장 위에 있는 ConstraintLayout 항목을 선택한 후 마우스 오른쪽 버튼을 누릅니다. 나타난 메뉴에서 [Convert view ...] 메뉴를 선택합니다.

![image10](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image10.png)

- 작은 대화상자가 나타나면 LinearLayout을 선택하고 [Apply] 버튼을 누릅니다. 그러면 화면 전체를 차지하고 있는 레이아웃 ConstraintLayout에서 LinearLayout으로 바뀝니다.

![image11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image11.png)

- 리니어 레이아웃이 화면 전체를 차지하게 되었으나 리니어 레이아웃 안에 버튼이 들어간 모양이 되고 버튼은 화면의 왼쪽 상단에 위치하게 됩니다.

![image12](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image12.png)

- 버튼을 다시 선택한 다음 layout_height 속성과 layout_width 속성 값을 모두 match_parent로 바꿉니다. 그러면 버튼이 화면을 꽉 채우게 됩니다.

![image13](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/views/image13.png)

- 동일한 속성이라도 뷰(버튼)를 담고 있는 레이아웃은 어떤 것인지에 따라서 뷰에 적용되는 방식이 달라집니다. 또한 각각의 레이아웃마다 필수 속성도 약간씩 다를 수 있습니다. 
- 예를 들어, 리니어 레이아웃은 orientation 속성이 필수 속성이지만 상대 레이아웃은 orientation 속성을 해석하지 않습니다. 

