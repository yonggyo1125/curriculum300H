# Vue.js - Part1

## 개발환경 구성 
- Visual Studio Code 설치<br>Nodepad++에서 vue 관련 플러그인이 제공되지 않아 개발상의 어려움이 있어 Visual Studio Code를 설치하여 진행 

- vue 개발을 위한 유용한 플러그인 설치
	-	Vetur : vue 문법에 맞는 Syntax Highlighting을 지원 

	- Debugger for Chrome : Chrome 브라우저 개발자 도구를 사용하는 것처럼 vs code 안에서 디버깅을 할 수 있도록   해준다	
	- Prettier – Code formatter : Vue 프로그램 구현 시 코드 포맷을 지정된 형태로 변환해 줍니다.
	
- Node.js 설치


## Vue 프로젝트 생성하기

-  Vuu CLI 설치
```
npm install –g @vue/cli
```
-g 옵션은 global로 전역 설치입니다.vue 명령어를 매번 설치하지 않고 1번만 설치해서 손쉽게 설치하기 위해서입니다.


- Vue 프로젝트 생성
```
vue create 프로젝트명
예) vue create vue-project
```
설치옵션은 Vue 3(최신버전)인 Default (Vue 3 ...)을 선택할 것

- Vue 프로젝트 실행
	- 생성한 프로젝트 폴더로 이동 
	- npm run serve 명령어를 통해 실행 
	- 프로젝트 기본포트는 8080번 포트이며 개발 환경 접속은 http://localhost:8080 입니다

	- 포트 번호 변경은 npm run serve -- --port 포트번호 형태로 실행할 것 


- Vue 프로젝트 매니저 실행	
	- vue ui 명령어로 실행하면 GUI 형태의 프로젝트 설치 웹이 실행 됩니다.


- Vue 프로젝트 파일 구조
	- node_modules – npm으로 설치된 패키지 파일들이 모여 있는 디렉토리
	- public – 정적 리소스가 모여 있는 디렉토리
	- src/assets – 이미지, css, 폰트 등을 관리하는 디렉토리 
	- src/components – Vue 컴포넌트 파일이 모여 있는 디렉토리
	- App.vue – 최상위(Root) 컴포넌트
	- main.js – 가장 먼저 실행되는 자바스크립트 파일로써, Vue 인스턴스를 생성하는 역할
	- .gitignore – 깃허브에 업로드 할 때 제외할 파일 설정
	- babel.config.js – 바벨(Babel) 설정 파일
	- package.json – 프로젝트에 필요한 package를 정의하고 관리하는 파일
	- README.md – 프로젝트 정보를 기록하는 파일

- Vue Router 설정
	- 사용저가 접속한 주소에 따라 페이지(컴포넌트)가 달라지는 것을 라우팅이라고 합니다.

- Vue-Router 설치
```
터미널에 다음 명령어 입력
vue add router 
```

- 설치가 완료 되면 src폴더에 router, views 폴더와 파일이 생성됩니다.

#### src/App.use
```html
<template>
  <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </div>
  <router-view/>
</template>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}#nav {
  padding: 30px;
}#nav a {
  font-weight: bold;
  color: #2c3e50;
}#nav a.router-link-exact-active {
  color: #42b983;
}</style>
```

#### src/router/index.js
```javascript
import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home 
    사용자가 path “/”에 접근하지 않더라도 이미 vue 파일을 import 한다
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    사용자가 path에 접근하기 전까지는 vue 파일에 대한 import가 일어나지 않는다
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
export default router
```

#### src/main.js
```javascript
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

createApp(App).use(router).mount('#app')
```
- import router from ‘./router’를 통해 router 폴더의 index.js가 import 된다.
- 마지막 줄을 보면 createApp(App) 최상위 컴포넌트인 App.vue로 app을 생성하고, use(router)코드를 추가하여 App.vue에서 router가 사용될 수 있도록 추가했습니다. 그리고 App.use를 public 폴더의 index.html에 정의되어 있는 id=“app”인 html element에 마운트 시키게 됩니다.

- Home, About을 클릭할 때마다 연결되어 있는 vue 파일이 호출이 되고, 해당 파일의 코드가 실행되어 화면에 나타나게 됩니다. 이렇게 .vue로 작성된 파일을 컴포넌트라고 부릅니다.


#### public/index.html
```html
<!DOCTYPE html>
<html lang="">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="icon" href="<%= BASE_URL %>favicon.ico">
    <title><%= htmlWebpackPlugin.options.title %></title>
  </head>
  <body>
    <noscript>
      <strong>We're sorry but <%= htmlWebpackPlugin.options.title %> doesn't work properly without JavaScript enabled. Please enable it to continue.</strong>
    </noscript>
    <div id="app"></div>
    <!-- built files will be auto injected -->
  </body>
</html>
```







## 컴포넌트 Basic

### 컴포넌트란?
- 컴포넌트는 View, Data, Code의 셋트라고 생각하시면 됩니다. 컴포넌트 안에는 HTML 코드가 있고, 이 HTML 코드를 실행하기 위한 자바스크립트 코드, 그리고 데이터가 존재합니다. 컴포넌트의 가장 큰 특징은 재사용이 가능하다는 점입니다.(다른 컴포넌트에서 import 해서 사용할 수 있다)

- src/views  - 페이지라고 부르는 화면 하나하나에 해당하는 vue 컴포넌트
- src/components – vue 파일에서 호출해서 공통으로 사용할 수 있는 vue 컴포넌트


### 컴포넌트 기본 구조
```javascript
<template>
	<div></div>
</template>
<script>
export default {
	name : '', // 컴포넌트 이름
	components : {}, // 다른 컴포넌트 사용시
	data() { // html과 자바스크립트 코드에서 사용할 데이터 변수 선언 
		return {
			데이터속성명 : '값', .. 
		}
	},
	setup() {} , // 컴포지션 API
	created() {}, // 컴포넌트가 생성되면 실행
	mounted() {}, // template에 정의된 html 코드가 렌더링된 후 실행
	unmounted() {}, // unmount가 완료된 후 실행
	methods : {} // 컴포넌트 내에서 사용할 메서드 정의
}
</script>
```

- \<template\> : view에 해당하는 html 코드를 작성하는 영역입니다.
- name : 컴포넌트 이름을 등록합니다. 컴포넌트 이름은 등록하지 않아도 사용하는 것에는 지장이 없음.
- components : 외부 컴포넌트를 사용하게 되면 해당 컴포넌트를 import한 후 객체 속성으로 등록
- data : 데이터 속성은 html 코드와 자바스크릡트 코드에서 전역변수로 사용하기 위해 선언하는 데이터입니다. 데이터 바인딩을 통해 화면에 해당하는 HTML과 코드에 해당하는 자바스크립트 간의 양방향 통신이 가능하도록 합니다. 데이터 속성에 정의된 변수는 this를 통해서 접근해야 합니다.
- setup : 컴포지션 API를 구현하는 메서드입니다.
- created : 컴포넌트가 생성되면 실행됩니다.
- mounted : 템플릿에 작성한 HTML 코드가 렌더링 된 후 실행됩니다.
- unmounted : 컴포넌트를 빠져나갈 때 실행됩니다.
- methods : 컴포넌트 내에서 사용할 메서드를 정의하는 곳입니다. 이곳에 작성된 메서드는 this를 통해서 접근해야 합니다.

### 데이터 바인딩
Vue는 양방향 데이터 바인딩(Two-way data binding)을 지원합니다. 양방향 데이터 바인딩이라는 것은 모델(Model)에서 데이터를 정의한 후 뷰(View)와 연결하면 모델과 뷰 중 어느 한쪽에 변경이 일어났을 때 다른 한쪽에 자동으로 반영되는 것을 의미합니다.

#### 문자열 데이터 바인딩

#### src/views/DataBinding.vue
```javascript
<template>
    <h1>hello, {{ title }}</h1>
</template>
<script>
    export default {
        data() {
            return {
                title : 'World'
            };
        }
    }
</script>
```

#### src/router/index.js
```javascript
import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import DataBinding from '../views/DataBinding.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/databinding',
    name: 'Databinding',
    component : DataBinding
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
```

#### src/App.vue
```html
<template>
  <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/databinding">Data Binding</router-link>
  </div>
  <router-view/>
</template>
```

### HTML 데이터 바인딩
- 이중 중괄호({{ }})를 이용해서 바인딩하면 html 태그가 아니라 문자열, 즉 텍스르로 인식하게 됩니다.
- 실제 HTML로 출력되기 위해서는 v-html 디렉티브를 사용해야 합니다.

#### src/views/DataBindingHtml.vue
```javascript
<template>
    <div>
        <div>{{ htmlString }}</div>
        <div v-html="htmlString"></div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                htmlString : '<p style="color:red">HTML으로 작성한 문장</p>'
            };
        }
    }
</script>
```

#### Form 입력 데이터 바인딩

- 웹페이지에서 사용자로부터 데이터를 입력받을 수 있는 필드를 Form Element라고 합니다. v-model 디렉티브를 사용하여 양방향 데이터 바인딩을 생성할 수 있습니다. 
> 주의할점) v-model은 내부적으로 서로 다른 속성을 사용하고 서로 다른 입력 요소에 대해 서로 다른 이벤트를 전송


- Input type=text
	- v-model은 내부적으로 value 속성을 사용하며 v-model에 넣어주면 모델인 data와 뷰인 input=text의 value 속성이 서로 양방향으로 데이터 바인딩 설정 됩니다.

#### src/views/DataBindingInputText.vue
```javascript
<template>
    <div>
        <input type="text" v-model="valueModel" />
    </div>
</template>
<script> 
    export default {
        data() {
            return {
                valueModel : 'South Korea'
            };
        }
    }
</script>
```

- Input type=number
	- 사용자의 입력 값이 문자가 아닌 숫자로 바로 처리할 수 있도록 v-model.number 디렉티브를 사용할 수 있습니다.

#### src/views/DataBindingInputNumber.vue
```javascript
<template>
    <div>
        <input type="number" v-model.number="numberModel" />
    </div>
</template>
<script>
    export default {
        data() {
            return {
                numberModel : 3
            };
        }
    }
</script>
```


- Textarea
	- \<textarea v-model=“변수명”\>\</textarea\>과 같이 사용해야 합니다.
	
#### src/views/DataBindingTextarea.vue
```javascript
<template>
    <div>
        <textarea v-model="message"></textarea>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                message : "여러 줄을 입력할 수 있는 textarea 입니다."
            };
        }
    }
</script>
```

- Select
	- v-model은 내부적으로 select의 value 속성을 사용해서 양방향 데이터 바인딩을 합니다.

#### src/views/DataBindingSelect.vue
```javascript
<template>
    <div>
        <select v-model="city">
            <option value="02">서울</option>
            <option value="21">부산</option>
            <option value="064">제주</option>
        </select>
    </div>
</template>
<script>
export default {
    data() {
        return {
            city : '064'
        };
    }
}
</script>
```

- 체크박스(input type=checkbox)
	- 체크박스에서 v-model은 내부적으로 체크박스의 checked 속성을 사용합니다. 
	- 만약 value 속성에 바인딩을 하려면 v-model이 아닌 v-bind:value를 사용해야 합니다.

#### src/view/DataBindingCheckbox.vue
```javascript
<template>
    <div>
        <label><input type="checkbox" v-model="checked"> {{ checked }}</label>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                checked : true
            };
        }
    }
</script>
```

- 다음의 코드를 통해 체크/해제 되었을 때의 기본 값을 변경할 수 있다
```
<label><input type=“checkbox” v-model=“checked” true-value=“yes” false-value=“no”> {{ checked }}</label>
```
- 여러개의 체크박스를 사용할 때는 배열을 이용해서 데이터를 바인딩해서 한번에 처리할 수 있습니다.


#### src/views/DataBindingCheckbox2.vue
```javascript
<template>
<div>
   <label><input type="checkbox" value="서울" v-model="checked"> 서울</label>
   <label><input type="checkbox" value="부산" v-model="checked"> 부산</label>
   <label><input type="checkbox" value="제주" v-model="checked"> 제주</label>
   <br>
   <span>체크한 지역: {{ checked }}</span>
</div>
</template>
<script>
export default {
 data() {
   return {
     checked: []
   };
 }
}
</script>
```

- 라디오(input type=radio)
	- 라디오에서 v-model은 내부적으로 checked 속성과 바인딩으로 이루어집니다. 라디오에서는 v-model이 라디오의 value 속성이 아닌 checked속성을 사용하기 때문에 value 속성에 데이터 바인딩을 하려면 v-bind:value를 사용해야 합니다.

#### src/views/DataBindingRadio.vue
```javascript
<template>
<div>
  <label><input type="radio" v-bind:value="radioValue1" v-model="picked"> 서울</label>
  <label><input type="radio" v-bind:value="radioValue2" v-model="picked"> 부산</label>
  <label><input type="radio" v-bind:value="radioValue3" v-model="picked"> 제주</label>
  <br>
  <span>선택한 지역: {{ picked }}</span>
</div>
</template>
<script>
export default {
data() {
  return {
    picked: '',
    radioValue1: '서울',
    radioValue2: '부산',
    radioValue3: '제주',
  };
}
}
</script>
```

### 속성(Attribute)
value를 제외한 HTML 객체의 속성(attribute)에 데이터를 바인딩 하기 위해서 v-bind: 디렉티브를 사용합니다. v-bind: 디렉티브는 v-bind를 생략하고 :(콜론)으로 사용할 수도 있습니다.

- img 객체의 src
#### src/views/DataBindingAttribute.vue
```javascript
<template>
    <div>
        <img v-bind:src="imgSrc" />
    </div>
</template>
<script>
export default {
    data() {
        return {
            imgSrc : "https://kr.vuejs.org/images/logo.png"
        };
    }
}
</script>
```

- button 객체의 disabled
#### src/views/DataBindingButton.vue
```javascript
<template>
    <div>
        <input type="text" v-model="textValue" />
        <button type="button" v-bind:disabled="textValue==''">Click</button>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                textValue: ""
            };
        }
    }
</script>
```

- 클래스 바인딩
	- 조건에 따라 바인딩할 클래스의 경우는 v-bind:class를 이용해서 추가적으로 정의해서 사용
#### src/views/DataBindingClass.vue
```javascript
<template>
    <div class="container" v-bind:class="{'active' : isActive, 'text-red' : hasError}">Class Binding</div>
</template>
<script>
export default {
    data() {
        return {
            isActive : true,
            hasError : false
        };
    }
}
</script>
<style scoped>
.container {
 width: 100%;
 height: 200px;
}
 
.active {
 background-color: yellow;
 font-weight: bold;
}
 
.text-red {
 color: red;
}
</style>
```

- 다음과 같이 배열 형태로 클래스를 바인딩할 수도 있습니다.
#### src/views/DataBindingClass2.vue
```javascript
<template>
    <div class="container" v-bind:class="[activeClass, errorClass]">Class Binding</div>
</template>
<script>
export default {
    data() {
        return {
            activeClass: 'active',
            errorClass : 'text-red'
        };
    }
}
</script>
<style scoped>
.container {
 width: 100%;
 height: 200px;
}
 
.active {
 background-color: yellow;
 font-weight: bold;
}
 
.text-red {
 color: red;
}
</style>
```

- 인라인 스타일 바인딩
#### src/views/DataBindingStyle.vue
```javascript
<template>
    <div v-bind:style="styleObject">인라인 스타일 바인딩</div>
</template>
<script>    
    export default {
        data() {
            return {
                styleObject : {
                    color: 'red',
                    fontSize: '13px'
                }
            };
        }
    }
</script>
```

#### src/views/DataBindingStyle2.vue
```javascript
<template>
    <div v-bind:style="[baseStyle, addStyle]">인라인 스타일 바인딩</div>
</template>
<script>
    export default {
        data() {
            return {
                baseStyle: 'background-color:yellow;width:100%;height:200px;',
                addStyle: 'color:red;font-weight:bold;'
            };
        }
    }
</script>
```

### 리스트 렌더링(v-for)
- 배열 데이터는 v-for 디렉티브를 이용해서 바인딩 할 수 있습니다. 반복적으로 렌더링 할 html 태그에 v-for 디렉티브를 사용하면 배열에 있는 데이터 수 만큼 html 태그를 반복적으로 렌더링 하게 됩니다.

- v-for=“(item, index) in items” 형식으로 사용
- items는 데이터 배열, v-for를 통해 배열을 하나씩 읽어와서 배열의 각 아이템을 item으로 배열의 현재 index를 index로 반환해 줍니다.

#### src/views/DataBindingList.vue
```javascript
<template>
<div>
  <table>
    <thead>
      <tr>
        <th>제품명</th>
        <th>가격</th>
        <th>카테고리</th>
        <th>배송료</th>
      </tr>
    </thead>
    <tbody>
     <tr :key='i' v-for="(product, i) in productList">
       <td>{{ product.product_name }}</td>
       <td>{{ product.price }}</td>
       <td>{{ product.category }}</td>
       <td>{{ product.delivery_price }}</td>
     </tr>
    </tbody>
  </table>
</div>
</template>
<script>
    export default {
        data() {
            return {
             productList: [
                {"product_name":"기계식키보드","price":25000,"category":"노트북/태블릿","delivery_price":5000},
                {"product_name":"무선마우스","price":12000,"category":"노트북/태블릿","delivery_price":5000},
                {"product_name":"아이패드","price":725000,"category":"노트북/태블릿","delivery_price":5000},
                {"product_name":"태블릿거치대","price":32000,"category":"노트북/태블릿","delivery_price":5000},
                {"product_name":"무선충전기","price":42000,"category":"노트북/태블릿","delivery_price":5000}
             ]
          };
        }
    }
</script>
<style scoped>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
</style>
```

### 렌더링 문법(v-if, v-show)
Vue 컴포넌트에서 조건에 따라 렌더링을 하는 방법은 v-if 디렉티브와 v-show 디렉티브를 사용하는 방법이 있습니다.

#### src/views/RenderingVIf.vue
```javascript
<template>
    <div>
        <h1 v-if="type=='A'">A</h1>
        <h1 v-else-if="type=='B'">B</h1>
        <h1 v-else>C</h1>
        <h1 v-show="bShow">bShow가 true면, 현재 블록 화면이 보이게 됩니다.</h1>
        <h1 v-show="bShow2">bShow2가 true면, 현재 블록 화면이 보이게 됩니다.</h1>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                type : 'A',
                bShow : true,
                bShow2 : false,
            };
        }
    }
</script>
```

#### v-if와 v-show의 차이점
- v-if의 경우 조건을 만족하면 그 순간에 html 블록이 생성되고, 조건에 만족하지 않으면 html 블록은 삭제가 됩니다.
- v-show의 경우는 조건 만족 여부에 상관없이 무조건 html 블록이 생성되며, 조건을 만족하면 css의 display를 이용해서 화면에 보이게되고 조건을 만족하지 않으면 회면에서 숨기도록 처리가 됩니다,

### 이벤트 처리(v-on)
Vue 컴포넌트에서 이벤트를 처리할 때는 v-on 디렉티브를 사용합니다. v-on 디렉티브는 심볼 @로 사용도 가능합니다.

- 클릭 이벤트(v-on:click)
	- 클릭 이벤트는 v-on:click=“메소스명” 혹은 @click=“메소드명”을 사용해서 추가할 수 있습니다.

#### src/views/EventClick.vue
```javascript
<template>
    <div>
        <button type="button" @click="increaseCounter">Add 1</button>
        <button type="button" @click="setCount(7)">Set 7</button>
        <button type="button" @click="one(), two()">Click</button>
        <p>The Counter is : {{ counter }}</p>
    </div>
</template>
<script> 
    export default {
        data() {
            return {
                counter : 0
            };
        },
        methods : {
            increaseCounter() {
                this.counter = this.counter + 1;
            },
            setCount(counter) {
                this.counter = counter;
            },
            one() {
                alert('One');
            },
            two() {
                alert('Two');
            }
        }
    }
</script>
```


- Change 이벤트
	- v-on:change=“메서드명” 또는 @change=“메서드명”으로 사용합니다.
```javascript
<template>
<select v-model="selectedValue" @change="changeSelect">
   <option value="서울">서울</option>
   <option value="부산">부산</option>
   <option value="제주">제주</option>
 </select>
</template>
<script>
export default {
    data() {
        return {
            selectedValue: ''
        };
    },
    methods : {
        changeSelect() {
            alert(this.selectedValue);
        }
    }
}
</script>
```

- key이벤트
	- v-on:keyup=“메서드명” @keyup=“메서드명”

```
특수키가 입력되는지 체크하는 경우
예) 사용자로부터 엔터키가 입력되는지 체크 
<input @keyup.enter=“submit” />
```

- Vue에서 제공되는 자주사용되는 key 이벤트
```
.enter 
.tab
.delete(키보드에서 Del키, Backspace키)
.esc
.space
.up
.down
.left
.right
```

- control. shift, Alt키와 같이 다른 키와 같이 사용되는 특수 키
```html
<!-- Alt + Enter -->
<input @keyup.alt.enter=“clear”>
```

```html
<!-- Ctrl + Click -->
<div @click.ctrl=“doSomething”>do something</div>
```


### computed와 watch
computed와 watch는 둘 다 Vue 인스턴스 내의 정의된 데이터 값에 변경이 일어나는 지를 감시하고, 변경될 때마다 정의된 함수가 실행됩니다.

- computed
#### src/views/Computed.vue
```javascript
<template>
    <h1>Full Name : {{ fullName }}</h1>
</template>
<script>
    export default {
        data() {
            return {
                firstName : "Yonggyo",
                lastName : 'Lee'
            };
        },
        computed : {
            fullName() {
                return this.firstName + ' ' + this.lastName;
            }
        }
    }
</script>
```
- 함수명인 fullName은 함수이자 동시에 Vue 인스턴스의 데이터 키 값입니다.
- firstName 혹은 lastName 값 중 하나라도 변경이 일어나면 fullName 함수가 자동으로 실행되고 fullName 값이 갱신됩니다. 즉, 정의된 fullName은 함수이자 동시에 Vue인스턴스의 데이터입니다.


### watch
- watch는 watch에 정의된 데이터 값 하나만 감시하기 위한 용도로 사용됩니다.
- watch의 경우는 computed와 다르게 실제 데이터 변경이 일어나기 전까지는 실행되지 않습니다.

#### src/views/Watch.vue
```javascript
<template>
    <div>
        <h1>Full Name : {{ fullName }}</h1>
        <button type="button" @click="changeName">변경</button>
    </div>
</template>
<script>
export default {
    data() {
        return {
            firstName : 'Yonggyo',
            lastName : 'Lee',
            fullName : ''
        };
    },
    watch : {
        firstName() {
            this.fullName = this.firstName + ' ' + this.lastName;
        },
        lastName() {
            this.fullName = this.firstName + ' ' + this.lastName;
        }
    },
    methods : {
        changeName() {
            this.firstName = "Yonggyo2";
        }
    }
}
</script>
```