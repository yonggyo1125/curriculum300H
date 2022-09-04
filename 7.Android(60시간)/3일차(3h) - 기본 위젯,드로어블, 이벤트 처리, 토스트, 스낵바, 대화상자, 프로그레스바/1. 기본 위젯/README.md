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

