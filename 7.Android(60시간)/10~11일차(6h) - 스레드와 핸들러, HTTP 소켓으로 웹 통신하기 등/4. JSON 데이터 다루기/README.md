# JSON 데이터 다루기

- JSON의 약자는 JavaScript Object Notation으로 자바스크립트 객체 포맷을 데이터를 주고받을 때 사용할 수 있도록 문자열로 표현한 것을 말합니다. 따라서 자바스크립트 객체 포맷과 거의 동일합니다. 약간의 차이가 있는데 속성의 이름과 문자열에 큰따옴표를 사용한다는 정도의 차이가 있습니다.

- 앞선 실습에서 응답으로 받은 데이터가 JSON 포맷으로 된 데이터입니다. JSON은 자바스크립트 객체 포맷이므로 중괄호를 사용해서 객체를 만들 수 있습니다. 앞선 실습에서 얻은 결과 값을 자세히 보면 조금 복잡하기는 해도 중괄호 안에 들어있는 여러 개의 속성을 확인할 수 있을 것입니다. 각각의 속성은 콤마(,)로 구분되며 각각의 속성 자체는 속성 이름과 속성 값이 콜론(:) 기호로 구분되면서 한 쌍을 이룹니다. 즉, 콜론 뒤에 값이 들어갈 수 있는데 문자열이나 숫자와 같은 기본 자료형이 올 수도 있고 다시 중괄호로 싸인 객체가 올 수도 있습니다. 문자열 값에는 큰따옴표를 붙이고 숫자에는 붙이지 않습니다. 만약 배열을 사용하고 싶다면 대괄호를 사용하며 그 안에 쉼표로 구분된 배열 원소들이 들어갑니다.

- 간단하게 JSON이 어떤 포맷인지 알게 되었으니 앞 단락에서 웹 응답으로 받은 JSON 결과물을 처리하는 방법을 알아보도록 하겠습니다. Gson은 자바스크립트에서처럼 JSON 문자열을 객체로 변환할 수 있도록 해 줍니다. 즉, JSON 문자열이 자바 객체로 만들어질 수 있습니다. Volley를 사용해서 웹 서버로부터 JSON 응답을 받았다면 Gson을 이용해서 자바 객체로 바꾸고 그 객체 안에 들어있는 데이터를 접근하여 사용할 수 있습니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/4.%20JSON%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EB%8B%A4%EB%A3%A8%EA%B8%B0/images/image1.png)

- Gson도 Volley처럼 외부 라이브러리이기 때문에 먼저 라이브러리를 추가해야 사용할 수 있습니다. 
-  Gson으로 응답 데이터를 처리해보기 위해 파일 탐색기를 열고 이전 단락에서 만들었던 SampleRequest 프로젝트를 복사하여 SampleRequest2 프로젝트를 만듭니다. 이때 build 폴더의 안드로이드 스튜디오에서 SampleRequest2 프로젝트를 열고 프로젝트 창이 보이면 먼저 Gson 라이브러리를 추가﻿합니다. /Gradle Scripts 폴더 안에 있는 build.gradle(Module: SampleRequest.app) 파일을 열고 라이브러리를 추가한 후 Sync Now 링크를 눌러 변경사항을 반영합니다.

#### SampleRequest2>/Gradle Scripts/build.gradle(Module: SampleRequest.app)

```

... 생략 

dependencies {
	
	... 생략
	
    implementation 'com.android.volley:volley:1.2.1'
    implementation 'com.google.code.gson:gson:2.8.6'
}
```

- Gson은 JSON 문자열을 자바 객체로 바꿔주는데 자바는 객체를 만들 때 클래스를 먼저 정의하는 과정을 거치므로 JSON 문자열을 자바 객체로 바꿀 때도 클래스를 먼저 정의해야 합니다.

 JSON 응답의 포맷에 맞추어 새로운 자바 클래스를 정의합니다. 왼쪽 프로젝트 창에서 /app/java/org.koreait.request 폴더를 선택한 상태에서 마우스 오른쪽 버튼을 눌러 새로운 자바 클래스를 만듭니다. 클래스의 이름은 TodoList 로 합니다. 
 
 - 응답 데이터 가장 바깥이 중괄호이므로 이 객체를 반환할 클래스로 Todo 라는 이름의 클래스를 정의했습니다.  여기에서 변수의 이름은 JSON 문자열에서 속성의 이름과 같아야 합니다. 그리고 변수의 자료형은 JSON 문자열에서 속성 값의 자료형과 같아야 합니다. 
 
#### SampleRequest2>/app/java/org.koreait.request/Todo.java

```java
 package org.koreait.request;

public class Todo {
    Long userId;
    Long id;
    String title;
    boolean completed;
	
	@Override
    public String toString() {
        return "Todo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
```
- 클래스들을 정의했다면 Gson을 이용해 JSON을 변환할 수 있습니다.

#### SampleRequest2>/app/java/org.koreait.request/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
	
	... 생략

    public void makeRequest() {
        String url = editText.getText().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);

                        processResponse(response);
                    }

                    public void processResponse(String response) {
                        // JSON 문자열을 객체로 변환하기
                        Gson gson = new Gson();
                        List<String> list = gson.fromJson(response, ArrayList.class);
                        System.out.println("--------------- 변환된 데이터 ----------------------");
                        for (String s : list) {
                            Todo todo = gson.fromJson(s, Todo.class);
                            System.out.println(todo);
                        }

                        System.out.println("--------------- 변환된 데이터 ----------------------");
                        println("할일의 개수 : " + list.size());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청 보냄.");
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}
```