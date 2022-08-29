# 테이블 레이아웃

- 테이블 레이아웃은 표나 엑셀 시트와 같은 형태로 화면을 구성하는 레이아웃입니다. 표나 엑셀 시트는 행과 열로 구성된 격자 형태로 되어있습니다. 테이블 레이아웃도 각각의 행과 그 안에 여러 개의 열을 넣어 레이아웃을 구성합니다.
- 구체적으로 테이블 레이아웃의 안에는 TableRow라는 태그가 여러 개 들어가는데 이 태그는 한 행을 의미합니다. TableRow 안에는 여러 개의 뷰가 들어가며 이 뷰들은 각각 하나의 열이 됩니다. 결과적으로 레이아웃에 추가된 TableRow의 개수가 행의 개수가 되고, 각 TableRow마다 추가된 뷰의 개수가 열의 개수가 됩니다.

- 테이블 레이아웃을 사용해보기 위해 새로운 SampleTableLayout 프로젝트를 만듭니다. 새로운 프로젝트 창이 열리면 activity_main.xml 파일을 열고 Component Tree 창에서 최상위 레이아웃으로 보이는 ContraintLayout을 LinearLayout으로 변경합니다.

- 최상위 레이아웃을 LinearLayout으로 변경한 이유는 [Convert view ... ] 메뉴를 눌렀을 때 보이는 대화상자에서 TableLayout을 선택할 수 없기 때문입니다. 이제 화면 안에 있는 TextView 태그는 삭제하고 팔레트 창에서 Layouts 폴더 안에 있는 TableLayout을 찾아 화면에 끌어다 놓습니다. 그리고 TableRow 뷰를 찾아 추가합니다. TableRow 뷰는 한 번에 다섯 개가 한꺼번에 추가되므로 두 개만 남기도 세 개는 삭제합니다.

- TableRow를 추가하면 layout_width와 layout_height 속성 값이 모두 match_parent로 설정됩니다. 그런데 layout_height 값이 match_parent로 설정되어 있어도 테이블 레이아웃의 아래쪽 여유 공간을 차지하지 않습니다. 결국 테이블 레이아웃 안에 포함된 TableRow의 높이 값은 내부적으로 항상 wrap_content로 설정되어 있어 화면을 꽉 채울 수 없으며, 반대로 폭은 내부적으로 항상 match_parent로 설정되어 있어 가로 공간을 꽉 채우게 됩니다. 이것은 하나의 행으로 표시될 수 있도록 만들기 위함입니다.

- 첫 번째 TableRow 안에 세 개의 버튼을 추가합니다. 이번에는 디자인 화면에 추가하는 것이 아니라 Component Tree를 이용하여 버튼을 추가합니다. 두 번째 TableRow 안에도 세 개의 버튼을 추가합니다. 만약 버튼을 추가했는데 layout_weight 속성 값이 자동으로 생겼으면 삭제합니다. 그러면 다음과 같이 격자 모양으로 버튼들이 정렬됩니다.

![image48](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image48.png)

- 그런데 테이블 모양으로 레이아웃을 만들 때, 세 개의 버튼을 추가한 후에 오른쪽에 남는 공간이 없도록 만들고 싶은 경우가 생깁니다. 이때는 TableLayout의 strechColumns 속성 값을 설정하면 됩니다. 
- 이 속성은 가로 방향으로 여유 공간이 있다면 그 여유 공간까지 모두 채워서 컬럼을 설정할 수 있습니다. 예를 들어, "0"이라고 지정하면 첫 번째 버튼이 나머지 여유 공간을 모두 차지하므로 세 개의 버튼이 가로 방향으로 꽉 채우게 됩니다.  
- "0"이라는 값은 컬럼의 인덱스를 가리키며 첫 번째 컬럼은 0부터 시작합니다. 만약 첫 번째와 두 번째 컬럼이 여유 공간을 추가로 차지하도록 만들고 싶다면 "0,1"을 값으로 지정합니다. 각각의 인덱스 값은 콤마(,)로 구분하면서 넣어줍니다. 여기에서는 세 개의 버튼이 모두 여유 공간을 나누어 가지므로 가로 공간을 채우도록 stretchColumns 속성 값을 "0,1,2"로 설정합니다.

- 이렇게 strechColumns 속성을 추가한 결과 화면은 다음과 같습니다.

![image49](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image49.png)

- 세 개의 버튼이 가로 방향으로 꽉 채워졌습니다. 이렇게 테이블 레이아웃에 설정할 수 있는 대표적인 속성으로 shrinkColumns와 strechColumns가 있는데 이 속성은 각각 특정 열을 자동으로 축소하거나 확장할 수 있도록 만들어줍니다. 자동 축소가 가능하도록 shrinkColumns 속성을 지정하면 부모 컨테이너의 폭에 맞추도록 각 열의 폭을 강제로 축소하며, 자동 확장이 가능하도록 strechColumns 속성을 지정하면 부모 컨테이너의 여유 공간을 모두 채우기 위해 각 열의 폭을 강제로 늘립니다.

- 각각의 컬럼으로 추가하는 뷰에는 layout_column이나 layout_span 속성을 지정할 수도 있습니다. \<TableRow\> 태그 안에 추가하는 뷰는 순서대로 0, 1, 2와 같은 컬럼 인덱스 값을 자동으로 부여받는데 layout_column 속성으로 컬럼 인덱스를 지정하면 그 순서를 설정할 수 있습니다. layout_span은 뷰가 여러 컬럼에 걸쳐 있도록 만들기 위한 속성이며 뷰가 몇 개의 컬럼을 차지하게 할 것인지 숫자로 지정합니다.

- 테이블 레이아웃 안에 TableRow를 하나 더 추가한 후 입력상자와 버튼을 추가합니다. 입력상자는 팔레트의 Text 폴더 안에 있는 두 번째 항목(Plain Text) 입니다. 두 개의 뷰가 추가되었으니 각각의 뷰가 갖는 컬럼 인덱스는 0과 1이 됩니다. 그런데 첫 번째 입력상자의 layout_span 속성 값을 2로 지정하면 이 입력상자가 두 개의 컬럼 영역을 차지하게 됩니다. 

![image50](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/1~2%EC%9D%BC%EC%B0%A8(6h)%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD%20%EC%84%A4%EC%A0%95%2C%20%EB%B7%B0%2C%20%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83/images/layouts/image50.png)

- 테이블 레이아웃은 자주 사용되지 않지만 격자 형태로 레이아웃을 만들 때는 유용하게 사용할 수 있습니다.