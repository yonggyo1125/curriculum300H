# 기본 위젯 

- SampleWidget이라는 이름으로 새로운 프로젝트를 만드세요. 프로젝트 창이 열리면 \[activity_main.xml\] 탭을 선택하세요. Component Tree의 최상위 레이아웃을 LinearLayout으로 변경하고 orientation 속성을vertical로 바꿉니다. 그리고 화면 가운데 있는 Hello World! 텍스트뷰는 삭제하세요. 이제 기본 위젯을 하나씩 추가하며 기본 위젯의 특징과 여러 속성을 실습해 보겠습니다.

## 텍스트뷰 자세히 살펴보기

- 텍스트뷰 화면을 구성할 때 가장 많이 사용되는 기본 위젯입니다. 텍스트뷰는 화면에서 글자를 보여주는 역할을 합니다. 텍스트뷰에는 여러 속성을 설정할 수 있는데 지금부터 각각의 속성에 대해 하나씩 알아보겠습니다.

### 텍스트뷰의 text 속성

- text 속성은 텍스트뷰의 문자열을 설정할 수 있습니다. 이때 text 속성은 반드시 지정해야 합니다. 텍스트뷰에 문자열이 없으면 텍스트뷰가 차지하는 영역도 알 수 없기 때문입니다. text 속성을 추가하는 방법은 text 속성 값으로 직접 문자열을 넣는 방법과 /app/res/values 폴더에서 strings.xml 파일에 작성한 문자열을 지정하는 방법이 있습니다.

- 지금까지는 디자인 화면에서 텍스트뷰를 선택한 후에 text 속성 값으로 문자열을 입력했습니다. 하지만 strings.xml 파일에 문자열을 미리 작성한 다음 이 값을 text 속성에 지정하는 방법을 더 권장합니다. 왜냐하면 XML 레이아웃 파일에서는 화면 모양을 정의하도록 하고 그 안에 들어가는 글자는 다른 파일에 저장하는 것이 더 좋기 때문입니다. 

- text 속성에 지정할 문자열을 strings.xml 파일로 분리하면 여러가지 장점이 생깁니다. 예를 들어, 다국어 지원이 필요할 때 한국어, 영어, 일본어, 중국어 버전의 XML 레이아웃 파일을 만드는 것보다 strings.xml 파일을 언어별로 만드는 것이 훨씬 효율적입니다. 그래서 글자들만 따로 strings.xml 파일로 분리한 후 XML 레이아웃 파일에서는 strings.xml 파일 안에 있는 글자 중 하나를 참조하도록 만들게 됩니다.


- 안드로이드 스튜디오의 왼쪽 프로젝트 창에서 /app/res/values 폴더 안에 들어 있는 strings.xml 파일을 여세요. 그런 다음 \<resources\> 태그 안에 \<string\> 태그를 이용하여 원하는 문자열을 넣으세요.

#### SampleWidget > /app/res/values/strings.xml 

```xml
<resources>
    <string name="app_name">SampleWidget</string>
    <string name="person_name">김안드</string>
</resources>
```

- 이제 화면 레이아웃을 만들면서 새로 추가한 \<string\> 태그 안의 문자열을 텍스트뷰에 표시되도록 만들어 보겠습니다. \[activity_main.xml\] 탭을 누른 후 디자인 화면에서 텍스트뷰를 하나 추가하세요. 오른쪽 텍스트뷰의 속성 창에서 text 속성을 찾아 @string/person_name이라고 입력하세요. 그러면strings.xml 파일에 입력한 \<string\> 태그의 문자열이 텍스트뷰에 나타납니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/1.%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF/images/image1.png)

> 안드로이드에서 다국어를 지원할 때는 리소스에 공통으로 적용되는 병렬 리소스 로딩(Parallel Resource Loading) 방식을 사용합니다. 예를 들어, 영어와 한국어를 지원하는 앱을 만들고 싶다면다음과 같이 /app/res 폴더 안에 두 개의 폴더를 만든 후 그 안에 strings.xml 파일을 넣어둡니다. 이때 안드로이드 스튜디오의 왼쪽 프로젝트 영역에서는 여러 개의 파일을 하나로 정리하여 보여주므로 폴더 구조가 제대로 표시되지 않을 수 있습니다. 파일 탐색기를 열어 보거나 또는 왼쪽 프로젝트 영역의 상단에서\[Android\]를 \[Project Files\]로 변경하면 원래의 폴더 구조를 그대로 볼 수 있습니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/1.%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF/images/image2.png)

> 이때 stringx.xml 파일이 저장될 폴더의 이름은 반드시 'values-'와 함게 'en', 'ko'와 같은 로케일 이름을 붙여 지어주어야 합니다.<br>이렂 구조로 만들었다면 단말의 설정(Settings) 언어(Language)가 한국어면 /app/res/values-ko/strings.xml 의 문자열이, 영어면 /app/res/values-en/strings.xml 의 문자열이 화면에 표시됩니다. 만약 단말에 설정된 언어에 해당하는 파일을 찾을 수 없다면 기본 폴더인 values 폴더 안에 들어 있는 strings.xml 파일이 사용됩니다.

><b>로케일 이름</b><br>여기에서의 로케일은 언어를 가리키는 것으로, 일반적인 /app/res/values 폴더가 아니라 각각의 언어에 맞는 이름의 폴더를 만든 후 그 안에 strings.xml을 정의합니다. 물론 각각의 strings.xml 파일 안에는 해당 언어로 된 글자를 넣을 수 있습니다. 예를 들어, /app/res/values 폴더가 한국어 로케일을 사용하는 것으로 하려면 /app/res/values-ko 폴더로 만들고 그 안에 들어 있는 strings.xml 파일 안에는 한국어로 된 글자를 입력합니다. 그리고 영문 로케일을 사용하는 것으로 하려면 /app/res/values-en 폴더로 만들고 그 안에 들어 있는 strings.xml 파일 안에는 영어로 된 글자를 입력합니다.

- /app/res/values/strings.xml 파일에 정의된 문자열은 text 속성에서 @string/.. 와 같은 형식으로 참조해야 합니다.

### 텍스트뷰의 textColor 속성

- textColor 속성은 텍스트뷰에서 표시하는 문자열의 색상을 설정합니다. 색상 설정은 일반적으로 '#AARRGGBB' 포맷을 사용하며 # 뒤에 나오는 4종류의 2자리 값은 각각 Alpha, Red, Green, Blue를 의미합니다. 이떄 투명도를 나타내는 Alpha 값은 투명하지 않음(FF), 투명함(00), 반투명(88)을 설정할 수 있습니다. 예를 들어, 투명하지 않은 빨간색은 textColor의 속성 값을 #FFFF0000으로 입력하면 됩니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/1.%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF/images/image3.png)

### textStyle

- textStyle 속성은 텍스트뷰에서 표시하는 문자열의 스타일 속성을 설정합니다. "normal", "bold", "italic" 등의 값을 지정할 수 있으며, \| 기호를 사용하면 여러 개의 속성 값을 함께 지정할 수 있습니다. 이때 \| 기호 앞뒤에 공백이 있어서는 안 됩니다. 예를 들어, 화면 레이아웃에 들어 있는 텍스트뷰의 스타일을 바꾸려면 textStyle 속성 값 중 bold를 선택하면 됩니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/1.%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF/images/image4.png)

### typeFace

- typeFace 속성은 텍스트뷰에서 표시하는 문자열의 폰트를 설정합니다. 기본적으로 제공되는 폰트는사용할 수 있는 개수가 많지는 않지만 그중 하나를 설정할 수 있습니다. 일반적으로는 "normal", "sans","serif", "monospace" 중에서 하나를 지정합니다. 다른 폰트가 필요하다면 폰트를 앱에 추가하고 그 폰트를 설정할 수도 있습니다. 'serif'를 선택하고 결과를 확인해 보세요.

![image5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/1.%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF/images/image5.png)

### maxLines

- maxLines 속성은 텍스트뷰에서 표시하는 문자열의 최대 줄 수를 설정합니다. 특히 한 줄로만 표시하고 싶을 때는 값을 "1"로 설정하면 됩니다. 이렇게 하면 한 줄의 영역을 넘어가는 부분은 표시되지 않습니다. 
- 텍스트뷰를 하나 더 추가한 후 text 속성 값으로 '여기에 사용자 이름을 입력하세요. 이름은 한 줄로 표시됩니다.'라는 글자를 입력합니다. textSize 속성 값은 40sp로 설정하고 textStyle 속성 값은 bold, maxLines 속성 값은 1로 설정합니다. 그러면 텍스트뷰에 입력한 글자가 모두 보이지 않는 것을 알 수 있습니다.

![image6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/1.%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF/images/image6.png)

## 버튼

- 버튼은 사용자가 클릭하면 클릭에 대한 반응을 하는 위젯입니다. 그런데 버튼은 텍스트뷰를 상속하여 정의되어 있습니다. 그래서 텍스트뷰의 속성도 그대로 가지고 있습니다. 예를 들어, text, textColor, textSize 등의 속성은 텍스트뷰의 속성과 동일합니다.

- 또 버튼의 유형이 다양해서 체크 박스, 라디오 버튼 등이 있으며 이 버튼들도 모두 버튼의 속성을 그대로 가집니다. 사용자가 설정한 상태 값을 저장하도록 정의되어 있다는 점만 다르죠. 어쨌든 버튼 위젯에 발생한 이벤트(사용자의 클릭 등)를 가장 간단하게 처리할 수 있는 방법은 OnClickListener를 정의하여 버튼에 설정하는 것입니다. 이번에는 기본 버튼, 체크 박스, 라디오 버튼을 모두 이용하여 실습해 보겠습니다.

- 체크 박스와 라디오 버튼은 단순히 클릭 이벤트만 처리하는 것이 아니라 상태 값을 저장하고 선택/해제 상태를 표시할 수 있습니다. 이런 작업이 가능하도록 CompoundButton 클래스가 정의되어 있는데 이 클래스는 다음과 같은 메서드를 포함하고 있습니다.

```java
public boolean isChecked()
public void setChecked(boolean checked)
public void toggle()
```

- 체크 박스, 라디오 버튼이 선택되어 있는지 확인하는 메서드는 isChecked이고 체크 상태를 지정하는메서드는 setChecked입니다. 만약 버튼의 상태가 바뀌는 것을 알고 싶다면 다음 메서드를 재정의하여 사용합니다.

```java
void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
```

- 라디오 버튼은 하나의 버튼이 선택되면 다른 버튼의 선택이 해제되어야 합니다. 이런 기능을 구현하기 위하여 RadioGroup을 이용해 라디오 버튼을 하나의 그룹으로 묶어줍니다.

- 기본 버튼과 라디오 버튼 그리고 체크 박스를 적용하기위해 /app/res/layout 폴더 안에 button.xml 파일을 만드세요. File name은 button.xml, Root element:는 LinearLayout으로 입력합니다.

- 파일을 새로 만들었으면 기본 버튼 아래에 RadioGroup을 추가하고 그 안에 두 개의 RadioButton을 추가하여 그룹화합니다. 그 아래에는 새 LinearLayout으로 텍스트뷰와 CheckBox를 추가합니다. 디자인 화면에서 추가할 수도 있는데 RadioGroup을 먼저 추가하고 그 안에 RadioButton을 넣는다는 점에주의해야 합니다. 글자나 글자 색상은 다음 화면을 보면서 똑같이 만들어보세요.

![image7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/1.%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF/images/image7.png)

- button.xml 앱의 첫 화면으로 등록하려면 MainActivity.java 파일을 열고 setContentView 메서드에 전달되는 파라미터의 값을 R.layout.button으로 변경하면 됩니다. 앱을 실행해 보세요. 그러면 button.xml 파일에서 만든 화면 레이아웃이 그대로 나타납니다.

- 이 화면에서 라디오 버튼이 어떻게 동작하는지 확인해 보세요. 라디오 버튼(2개)은 RadioGroup 태그 안에 들어있어 "남성"이나 "여성"을 선택하면 선택하지 않은 라디오 버튼은 자동으로 해제됩니다. '하루종일'이라는 체크 박스는 누를 때마다 체크 상태가 변합니다.

- 버튼에 아이콘을 넣고 싶나요? 그러면 아이콘을 설정하는 속성을 추가하면 됩니다. 그러나 텍스트가 없는 버튼을 사용하고 싶다면 ImageButton 태그를 적용할 수 있습니다. ImageButton은 이미지만을 보여주며 사용자의 클릭 이벤트를 버튼과 동일하게 처리할 수 있도록 정의되어 있습니다.

## 에디트텍스트

- 입력상자의 역할을 하는 에디트텍스트(EditText)는 사용자에게 값을 입력받을 때 사용합니다. 이 위젯은 글자를 입력하려고 커서를 옮기면 키패드가 화면에 나타납니다. 그리고 한글, 영문, 숫자 등 입력하는 문자의 유형도 다양합니다. 그래서 조심해서 사용해야 하는 위젯입니다.

> 에디트텍스트는 입력하는 문자의 유형을 지정할 수 있는 inputType 속성을 제공합니다. 예를 들어, 숫자만 입력되도록 하거나 이메일을 편리하게 입력할 수 있는 키패드를 띄우고 싶다면 inputType 속성 값을 설정합니다. 

- button.xml 파일을 만들었던 것과 동일한 방법으로 /app/res/layout 폴더 안에 edittext.xml 파일을 만드세요. Root element: 입력상자에는 LinearLayout을 입력하여 만듭니다. 그리고 디자인 화면에서 에디트텍스트를 추가합니다. 팔레트에 있는 [Text → Plain Text]를 Component Tree 창의 LinearLayout 아래로 끌어다 놓습니다. 입력상자의 속성 중에서 textSize 속성 값은 24sp, inputType 속성값은 text, hint 속성 값은 '이름을 입력하세요.'로 설정합니다. 그리고 text 속성 값으로 들어 있던 글자는 삭제합니다.

- hint 속성을 적용하니 에디트텍스트에 간단한 안내글이 표시됩니다. 안내글은 사용자의 입력이 진행되면 사라집니다. inputType 속성은 입력되는 글자의 유형을 정의할 수 있습니다. 즉, 글자를 입력할 때 보이는 키패드의 유형을 정할 수 있습니다. 여기에서는 inputType을 "text" 값으로 지정하여 글자 키패드가 보이도록 했습니다. 다음은 레이아웃을 만든 결과 화면입니다.
 
 ![image8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/1.%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF/images/image8.png)
 
 - 에디트텍스트에 들어 있는 글자를 복사하거나 붙여 넣으려면 안드로이드에서 지원하는 기본 기능(글자위를 길게 누르기)을 그대로 사용하면 됩니다.
 
 ## 이미지뷰와 이미지 버튼
 
 - 이미지뷰와 이미지 버튼은 이미지를 화면에 표시할 때사용하는 가장 간단한 위젯입니다. 두 위젯의 차이점은 버튼처럼 사용할 수 있다는 점 이외에는 없으므로 이미지뷰를 기준으로 설명하겠습니다. 이미지뷰에 이미지를 보여주려면 먼저 /app/res/drawable 폴더에 이미지 파일을 넣은 후 app:srcCompat 속성 값을 다음과 같은 방법으로 지정하면 됩니다. 
 - 만약 윈도우 파일 탐색기에서 이미지 파일을 복사해서넣으려면 /app/src/main/res/drawable 폴더에 넣으세요.
 
 ```
 @drawable/이미지 파일명
 ```
 
- 이때 이미지 파일명은 확장자를 제외하고 작성해야 합니다. 이렇게 이미지 파일을 지정하는 방식을 '이미지 리소스를 지정 방식'이라고 합니다. 왜냐하면 이미지 파일은 res 폴더 안에 들어 있는 리소스 중의하나이기 때문입니다. 이 방식 이외에도 이미지 파일을 소스 코드에서 직접 로딩하여 비트맵으로 만든후 설정하는 방법도 있습니다. 이미지뷰의 대표적인 속성은 다음과 같습니다.

```
android:src 또는 app:srcCompat
```

- android:src 속성이나 app:srcCompat 속성은 원본 이미지를 설정합니다. 텍스트뷰에서는 text 속성을 설정하지 않으면 뷰의 내용물이 설정되지 못해 뷰의 크기를 확인할 수 없습니다. 이와 마찬가지로 이미지뷰는 내용물(이미지)이 지정되지 않으면 이미지뷰의 크기를 확인할 수 없습니다. 따라서 이 속성은 반드시 설정해야 합니다. 그리고 이미지뷰에 추가하는 이미지의 확장자는 JPG 또는 PNG 확장자입니다(PNG 포맷을 권장).
- 물론 필요에 따라 여러 이미지 포맷을 선택해도 됩니다.

### maxWidth, maxHeight

- 두 속성은 이미지가 표시되는 최대 폭, 높이를 설정합니다. 이 속성을 설정하지 않으면 원본 이미지가 그대로 나타납니다. 이미지의 원본이 너무 크면 이 속성으로 최대 크기를 제한할 수 있습니다.

### tint

- tint 속성은 이미지뷰에 보이는 이미지의 색상을 설정할 수 있습니다. 색상은 "#AARRGGBB" 포맷으로 적용하면 됩니다. 예를 들어, 반투명으로 이미지의 색상 값을 적용하면 원본 이미지의 느낌과 다른 느낌을 연출할 수 있습니다.

### scaleType

- scaleType 속성은 이미지뷰의 크기에 맞게 원본 이미지의 크기를 자동으로 늘리거나 줄여서 보여줄 때 사용합니다. 이때 원본 이미지를 무조건 늘리거나 줄이는 것이 아니라 원하는 형태로 확대하거나 축소할 수 있습니다. scaleType 속성에는 fitXY, centerCrop, centerInside 등의 이미지 변환 알고리즘이 적용된 미리 정의된 값을 사용할 수 있으며, 필요에 따라 적절하게 적용하면 됩니다. 이미지뷰에 추가될 이미지는 보통 JPG나 PNG 확장자를 가진 이미지가 사용됩니다. 일반적으로는 PNG 포맷을 권장하지만 필요에 따라 여러 가지 이미지 포맷 중에서 선택해서 사용할 수 있습니다.

> 안드로이드는 화면의 해상도에 따라 서로 다른 크기의 모양의 이미지를 로딩할 수 있습니다. 이 방법도 strings.xml 파일을 나라별로 사용했던 것과 같은 병렬 리소스 로딩 방식을 사용합니다. 즉, /app/res/drawable 폴더에 이미지를 그냥 넣으면 일반적으로 사용되는 이미지로 인식합니다. 하지만 /app/res/drawable-hdpi 에 넣은 이미지는 고해상도 화면, /app/res/drawable-mdpi 에 넣은 이미지는 중간 해상도 화면으로 자동 적용됩니다. 물론 각각의 폴더는 여러분이 직접 만들어야 합니다. 프로젝트를 처음 만들면 /app/res/drawable 폴더만 있습니다. 다음은 해상도에 맞는 폴더 이름입니다.

|해상도|폴더 이름|
|-----|-------|
|초고해상도|/app/res/drawable-xhdpi,<br>/app/res/drawable-xxhdpi,<br>/app/res/drawable-xxxhdpi|
|고해상도|/app/res/drawable-hdpi|
|중간 해상도|/app/res/drawable-mdpi|
|저해상도|/app/res/drawable-ldpi|

- /app/res/layout 폴더 안에 image.xml 파일을 만듭니다. 파일을 만들기 위한 대화상자에서 Root element: 항목에 LinearLayout을 입력합니다. 파일이 만들어지면 먼저 사용할 이미지 파일을 /app/res/drawable 폴더에 넣어줍니다. 학습소스의 이미지 파일을 복사하여 사용해도 됩니다. 팔레트에서 ImageButton과 ImageView를 하나씩 끌어다 놓습니다. 이미지 버튼과 이미지뷰를 화면에 추가할 때는 대화상자가 보이면서 이미지를 선택하도록 합니다. 이때 선택한 이미지는 src 속성이나 srcCompat 속성 값으로 설정됩니다.

- 화면에 추가한 이미지 버튼과 이미지뷰의 layout_margin 값을 적당히 설정하여 간격을 띄웁니다. 다음은 디자이너 화면에서 볼 수 있는 결과 화면입니다.

 ![image9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/3%EC%9D%BC%EC%B0%A8(3h)%20-%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF%2C%EB%93%9C%EB%A1%9C%EC%96%B4%EB%B8%94%2C%20%EC%9D%B4%EB%B2%A4%ED%8A%B8%20%EC%B2%98%EB%A6%AC%2C%20%ED%86%A0%EC%8A%A4%ED%8A%B8%2C%20%EC%8A%A4%EB%82%B5%EB%B0%94%2C%20%EB%8C%80%ED%99%94%EC%83%81%EC%9E%90%2C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%A0%88%EC%8A%A4%EB%B0%94/1.%20%EA%B8%B0%EB%B3%B8%20%EC%9C%84%EC%A0%AF/images/image9.png)
 
- 이미지를 화면에 보여주기 위하여 이미지뷰와 이미지 버튼을 사용했습니다. 이미지 버튼은 이미지를 버튼처럼 만들고 싶을 때 사용합니다. 하지만 이미지 버튼은 일반적인 버튼과는 다르게 눌린 상태와 눌리지 않은 상태가 표시되지 않습니다. 이 문제를 해결하려면 셀렉터(Selector)를 만들어 사용해야 합니다. 셀렉터는 뒷부분에서 자세하게 설명합니다.

## 텍스트뷰와 에디트텍스트의 다른 기능들

- 텍스트뷰, 에디트텍스트 등은 모두 뷰를 상속받아 만들어진 것이므로 뷰의 속성들을 그대로 가지고 있습니다. 뷰가 갖고 있는 속성은 상당히 많은데 그중 몇 가지 속성들을 좀 더 자세히 살펴보겠습니다.

### 커서 관련 속성

- 에디트텍스트에는 문자나 숫자를 입력하는 역할을 하며 커서가 깜박이는 동작이 있습니다. 그리고 에디트텍스트의 커서 위치는 입력된 문자열의 마지막 지점으로 이동하도록 되어 있습니다. 그런데 사용자가 에디트텍스트를 선택할 때마다 전체 내용을 수정할 수 있도록 편의를 제공할 수도 있습니다. 예를 들어 에디트텍스트의 selectAllOnFocus 속성을 true로 설정하면 포커스를 받을 때 문자열 전체가 선택됩니다. 이렇게 문자열 전체가 선택되면 새로운 문자를 입력했을 때 입력되어 있던 문자열을 한꺼번에 대체하므로 손쉽게 글자를 바꿀 수 있습니다. 이렇게 문자열 전체가 선택되어 있는 상태에서 문자열을 대체하지 않고 추가하고 싶다면 왼쪽이나 오른쪽 방향키를 한 번 누르고 입력할 수도 있습니다. 또 커서를 보이지 않게 설정하려면 cursorVisible 속성을 "false" 값으로 설정하면 됩니다.

- 이렇게 커서에도 다양한 설정을 할 수 있으니 꼭 알아두는 것을 권합니다. 다음은 커서를 이용하여 사용자가 문자열을 선택하거나 복사, 잘라내기 등의 기능을 만들 때 사용하는 메서드입니다. 보통 에디트텍스트를 길게 눌러 복사, 잘라내기 등의 작업을 할 수 있는데 바로 그 기능을 만들 때 다음 메서드를 사용합니다.

```java
public int getSelectionStart()
public int getSelectionEnd()
public void setSelection(int start, int stop)
public void setSelection(int index)
public void selectAll();
public void extendSelection(int index)
```

- getSelectionStart 메서드는 선택된 영역의 시작 위치를 알려줍니다. 그리고 getSelectionEnd 메서드는 끝 위치를 알려주는데 두 메서드는 모두 선택 영역이 없으면 커서가 있는 현재 위치를 알려줍니다. setSelection 메서드는 선택 영역을 지정하는 데 사용하고 extendSelection은 선택 영역을 확장하는데 사용합니다. selectAll 메서드를 호출하면 전체 문자열이 선택됩니다.

### 자동 링크 관련 속성

-  autoLink 속성을 true로 설정하면 문서에 포함된 웹페이지 주소나 이메일 주소를 링크 색상으로 표시하고 링크를 누르면 웹페이지에 바로 접속하거나 메일 편집기를 띄워주는 기능을 간단하게 넣을 수 있습니다.

### 줄 간격 조정 관련 속성

- 줄 간격 조정 관련 속성을 이용하면 텍스트뷰의 줄 간격을 조절하여 가독성을 높일 수도 있습니다. 줄 간격은 lineSpacingMultiplier와 lineSpacingExtra로 설정할 수 있습니다. lineSpacing Multiplier는 줄 간격을 기본 줄 간격의 배수로 설정할 때 사용하고 lineSpacingExtra는 여유 값으로 설정할 때 사용합니다. 배수로 설정할 때는 기본 값을 1.0로 놓고 값을 조절하면 됩니다. 예를 들어 1.0보다 작은 값으로 설정하면 기본 줄 간격보다 더 좁게 보입니다.

> 실제로 텍스트가 많은 화면은 글자가 너무 빼곡해서 사용자가 읽기 어렵습니다. 이럴 때 줄 간격을 조정하는 속성을 사용할 수 있습니다.

### 대소문자 표시 관련 속성

- capitalize 속성을 이용하면 글자, 단어, 문장 단위로 대소문자를 조절할 수 있습니다. 속성 값으로는 "characters", "words", "sentences" 등을 지정할 수 있는데 각각의 값으로 설정하면 글자, 단어, 문장 단위로 맨 앞 글자를 대문자로 표시할 수 있습니다.


### 줄임 표시 관련 속성

- 텍스트뷰는 한 줄로 되어 있어 텍스트뷰에 많은 문자를 입력하면 입력한 내용의 뒷부분은 잘리고 말줄임표(...)로 표시됩니다. 이때 ellipsize 속성을 사용하면 입력한 내용의 생략 부분을 설정할 수 있습니다. 디폴트 값인 "none" 은 뒷부분을 자르고 "start", "middle", "end" 값들은 각각 앞부분, 중간부분, 뒷부분을 잘라서 보여줍니다. 텍스트뷰를 한 줄로 표시할 때는 maxLines 속성을 사용합니다.

#### 힌트 표시 관련 속성

- 에디트텍스트에 어떤 내용을 입력하라고 안내문으로 알려주고 싶을 때는 hint 속성을 사용하면 됩니다. 만약 hint 속성으로 나타낸 글자의 색상을 바꾸고 싶다면 textColorHint 속성에서 색상을 지정하면 됩니다.

#### 편집 가능 관련 속성

- 에디트텍스트에 입력되어 있는 문자열을 편집하지 못하게 하고 싶다면 editable 속성 값을 "false"로 설정하면 됩니다. 기본 값은 문자열을 편집할 수 있는 "true"입니다.

#### 문자열 변경 처리 관련 속성

- 에디트텍스트에 입력된 문자를 확인하거나 입력된 문자가 필요한 포맷과 맞는지 확인할 때 getText 메서드를 사용합니다. 이 메서드가 리턴하는 것은 Editable 객체인데 이 객체의 toString 메서드를 이용하면 일반 String 타입의 문자열을 확인할 수 있습니다.

- 만약 문자열이 사용자의 입력에 의해 바뀔 때마다 확인하는 기능을 넣고 싶다면 TextChangedListener를 사용할 수 있습니다.

```java
public void addTextChangedListener(TextWatcher watcher)
```

- addTextChangedListener 메서드를 사용하면 TextWatcher 객체를 설정할 수 있습니다. 이 객체는 텍스트가 변경될 때마다 발생하는 이벤트를 처리합니다. TextWatcher 인터페이스에는 다음과 같은 메서드들이 정의되어 있습니다.

```java
public void beforeTextChanged(CharSequence s, int start, int count, int after)
public void afterTextChanged(Editable s)
public void onTextChanged (CharSequence s, int start, int before, int count)
```

- 문자열이 편집되기 전과 후, 그리고 편집된 정보를 확인할 수 있도록 하므로 이 안에 필요한 기능을 추가합니다. 만약 입력된 문자열의 길이 값을 확인할 때는 setFilters 메서드를 사용해서 InputFilter 객체를 파라미터로 전달하고 이 객체의 LengthFilter 메서드를 호출하면 입력될 문자열의 길이 값을 설정할 수 있습니다.

- 이 TextWatcher 인터페이스를 활용하는 전형적인 예로 문자를 SMS로 전송하는 경우를 들 수 있습니다. SMS는 80바이트까지만 전송할 수 있으므로 사용자가 몇 글자를 입력했는지 바로바로 표시하고 싶을 때 이 클래스를 사용합니다.