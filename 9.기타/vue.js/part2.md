# Vue.js – Part2

## 컴포넌트 심화 학습

### 부모 컴포넌트와 자식 컴포넌트
컴포넌트에서 다른 컴포넌트를 사용하는 방법은 사용할 컴포넌트를 import한 후 현재 컴포넌트의 템플릿에서 사용할 컴포넌트를 components에 등록하시면 됩니다.

#### src/components/PageTitle.vue
```html
<template>
    <h2>Page Title</h2>
</template>
```

#### src/views/NestedComponent.vue
```
<template>
    <div>
        <PageTitle />
    </div>
</template>
<script> 
    import PageTitle from '../components/PageTitle';
    export default {
        components : {PageTitle}
    }
</script>
```

### 부모 컴포넌트에서 자식 컴포넌트로 데이터 전달하기 (Props)
#### src/components/PageTitle.vue
```javascript
<template>
    <h2>{{ title }}</h2>
</template>
<script>
    export default {
        props : {
            title : {
                type : String,
                default : "페이지 제목 입니다"
            }
        }
    }
</script>
```
#### src/views/NestedComponent.vue
```javascript
<template>
    <div>
        <PageTitle title="부모 컴포넌트에서 자식 컴포넌트로 데이터 전달" />
    </div>
</template>
<script> 
    import PageTitle from '../components/PageTitle';
    export default {
        components : {PageTitle}
    }
</script>
```

## 정적/동적 Prop 전달
v-bind나 약어인 : 문자를 사용하여 prop에 동적인 값을 전달할 수 있습니다.
```javascript
<template>
    <div>
        <PageTitle :title="title" />
        <page-title :title="title2" />
    </div>
</template>
<script> 
    import PageTitle from '../components/PageTitle';
    export default {
        components : { PageTitle },
        data() {
            return {
                title : '동적 페이지 타이틀',
                title2 : '동적 페이지 타이틀2'
            };
        }
    }
</script>
```
### 숫자형(Number) 전달
v-bind를 사용하지 않은 경우는 전달한 값은 숫자가 아니라 문자입니다. 숫자로 전달하기 위해서는 v-bind를 사용하여야 합니다.

#### src/components/BlogPost.vue
```javascript
<template>
    <h2>{{ likes }}</h2>
</template>
<script>
export default {
    props : {
        likes : {
            type : Number,
            default : 10
        }
    }
}
</script>
```

#### src/views/NestedComponent2.vue
```javascript
<template>
    <div>
        <blog-post likes="42" />
        <blog-post :likes="41" />
        <blog-post :likes="likes" />
    </div>
</template>
<script>
   import BlogPost from "../components/BlogPost";
    export default {
        components : { BlogPost },
        data() {
            return {
                likes : 40
            };
        }
    }
</script>
```
### 논리 자료형(Boolean) 전달
논리 자료형 역시 v-bind를 사용하지 않으면 문자열로 전달되기 때문에, v-bind를 사용해야 합니다.

### 배열(Array) 전달
배열 역시 v-bind을 사용하지 않으면 문자열로 전달되기 때문에 v-bind를 사용해야 합니다.
```
<!-- 배열이 정적이지만, v-bind를 사용함으로써 전달되는 데이터가 자바스크립트 표현식이 됩니다. -->
<blog-post :comment-ids=“[234, 266, 273]” />

<!-- 변수 값에 동적으로 할당합니다. -->
<blog-post :comment-ids=“post.commentIds” />
 ```
 
### 객체(Object) 전달
객체(Object) 역시 v-bind를 사용하지 않으면 문자열로 전달되기 때문에, v-bind를 사용해야 합니다.

### 객체(Object)의 속성 전달
객체(Object)역시 v-bind을 사용하지 않으면 문자열로 전달되기 때문에, v-bind를 사용해야 합니다.<br><br>

다음 두개의 코드는 동일
```
<blog-post v-bind=“post” />
<blog-post :id=“post.id” :title=“post.title” />
```
```
data() {   
		return {       post : { id : 1, title : “제목” }   
	};
}
```

## 부모 컴포넌트에서 자식 컴포넌트의 이벤트 직접 발생시키기
- HTML 태그에 ref=“id“를 지정하면 Vue 컴포넌트의 함수에서 this.$refs를 통해 접근이 가능
- ref 속성은 HTML 태그에서 사용되는 id와 비슷한 기능을 한다(ref는 유일한 키 값을 사용)

#### src/views/ChildComponent.vue
```javascript
<template>
    <button type="button" @click="childFunc" ref="btn">Click</button>
</template>
<script>
    export default {
        methods : {
            childFunc() {
                console.log("부모 컴포넌트에서 직접 발생시킨 이벤트");
            }
        }
    }
</script>
```

#### src/views/ParentComponent.vue
```javascript
<template>
    <child-component @send-message="sendMessage" ref="child_component" />
</template>
<script>
    import ChildComponent from './ChildComponent';
    export default {
        components : { ChildComponent },
        mounted() {
            this.$refs.child_component.$refs.btn.click();
        }
    }
</script>
```

## 부모 컴포넌트에서 자식 컴포넌트의 함수 직접 호출하기
#### src/views/ChildComponent2.vue
```javascript
<script>
export default {
    methods : {
        callFromParent() {
            console.log('부모 컴포넌트에서 직접 호출한 함수');
        }
    }
}
</script>
```

#### src/views/ParentComponent2.vue
```javascript
<template>
    <child-component @send-message="sendMessage" ref="child_component" />
</template>
<script>
import ChildComponent from './ChildComponent2';
export default {
    components: { ChildComponent },
    mounted() {
        this.$refs.child_component.callFromParent();
    }
}
</script>
```

## 부모 컴포넌트에서 자식 컴포넌트의 데이터 옵션값 직접 변경하기
#### src/views/ChildComponent3.vue
```javascript
<template>
    <h1>{{ msg }}</h1>
</template>
<script>
    export default {
        data() {
            return {
                msg : ''
            };
        }
    }
</script>
```

#### src/views/ParentComponent3.vue
```javascript
<template>
    <child-component @send-message="sendMessage" ref="child_component" />
    <button type="button" @click="changeChildData">Change Child Data</button>
</template>
<script>
import ChildComponent from './ChildComponent3';
export default {
    components : { ChildComponent },
    methods : {
        changeChildData() {
            this.$refs.child_component.msg = "부모 컴포넌트가 변경한 데이터";
        }
    }
}
</script>
```

## 자식 컴포넌트에서 부모 컴포넌트로 이벤트/데이터 전달하기(커스텀 이벤트)
자식 컴포넌트에서 부모 컴포넌트로 이벤트를 전달하기 위해서는 $emit를 사용합니다.

#### src/views/ChildComponent4.vue 
```javascript
<script>
    export default {
        data() {
            return {
                msg : '자식 컴포넌트로 부터 보내는 메세지'
            };
        },
        mounted() {
            this.$emit('send-message', this.msg);
        }
    }
</script>
```
#### src/views/ParentComponent4.vue
```javascript
<template>
    <child-component @send-message="sendMessage" />
</template>
<script>
import ChildComponent from './ChildComponent4';
export default {
    components : { ChildComponent },
    methods : {
        sendMessage(data) {
            console.log(data);
        }
    }
}
</script>
```

## Computed
부모 컴포넌트에서 자식 컴포넌트의 데이터 옵션 값 동기화하기 부모 컴포넌트에서 computed를 이용하면 자식 컴포넌트에 정의된 데이터 옵션 값의 변경사항을 항상 동기화시킬 수 있습니다.

#### src/views/ChildComponent5.vue
```
<template>
    <button type="button" @click="childFunc" ref="btn">자식 컴포넌트 데이터 변경</button>
</template>
<script>
export default {
    data() {
        return {
            msg : '메시지',
        };
    },
    methods: {
        childFunc() {
            this.msg = '변경된 메시지';
        }
    }
}
</script>
```

#### src/views/ParentComponent5.vue
```
<template>
    <button type="button" @click="checkChild">자식 컴포넌트 데이터 조회</button>
    <child-component ref="child_component" />
</template>
<script>
import ChildComponent from './ChildComponent5';
export default {
    components : {ChildComponent},
    computed: {
        msg() {
            return this.$refs.child_component.msg;
        }
    },
    methods : {
        checkChild() {
            alert(this.msg);
        }
    }
}
</script>
```

## Slot 
slot은 컴포넌트 내에서 다른 컴포넌트를 사용할 때 쓰는 컴포넌트의 마크업을 재정의하거나 확장하는 기능입니다. 컴포넌트의 재활용성을 높여주는 기능입니다.

#### src/views/SlotModalLayout.vue
```javascript
<template>
    <div class="modal-container">
        <header>
            <slot name="header"></slot>
        </header>
        <main>
            <slot></slot>
        </main>
        <footer>
            <slot name="footer"></slot>
        </footer>
    </div>
</template>
<style scoped>
.modal-container {
  border: 1px solid #ddd;
}
</style>
```

#### src/views/SlotUseModalLayout.vue
```javascript
<template>
    <modal-layout>
        <template v-slot:header>
            <h1>팝업 타이틀</h1>
        </template>
        <template v-slot:default>
            <p>팝업 컨텐츠 1</p>
            <p>팝업 컨텐츠 2</p>
        </template>
        <template v-slot:footer>
            <button type="button">닫기</button>
        </template>
    </modal-layout>
</template>
<script>
import ModalLayout from './SlotModalLayout';
export default {
    components: {ModalLayout}
}
</script>
```
v-slot: 대신에 단축어로 #을 사용할 수 있습니다.컴포넌트 내에 여러 영역에 slot을 적용할 때는 name을 이용해서 적용하고, 하나의 영역에만 적용할 때는 굳이 slot에 name을 사용하지 않아도 됩니다.

#### src/components/PageTitle.vue
```html
<template>
    <h1><slot></slot></h1>
</template>
```

#### src/views/ParentComponent.vue
```
<template>
    <PageTitle>컴포넌트 사용 예제 페이지</PageTitle>
</template>
<script>
import PageTitle from '../components/PageTitle';
export default {
    components : { PageTitle }
}
</script>
```

## Provide/Inject
- 부모 컴포넌트에서 자식 컴포넌트로 데이터를 전달해야 하는 경우 props를 사용하면 됩니다. 그런데 만약 컴포넌트 계층 구조가 복잡하게 얽혀 있어서 부모 컴포넌트로부터 자식 컴포넌트, 그리고 그 자식 컴포넌트의 자식 컴포넌트로 전달하는 경우가 발생하면 props를 통해 데이터를 전달하는 것은 굉장히 복잡한 코드를 양산하게 됩니다. 

- 이러한 경우에 사용할 수 있는 것이 Provide/Inject입니다. 컴포넌트의 계층 구조가 아무리 복잡하더라도 부모 컴포넌트에서는 provide 옵션을, 자식 컴포넌트에서는 inject 옵션을 통해 데이터를 쉽게 전달할 수 있습니다.

#### src/views/ProvideInject.vue
```javascript
<template>
    <ProvideInjectChild />
</template>
<script>
    import ProvideInjectChild from './ProvideInjectChild';
    export default {
        components: { ProvideInjectChild },
        data() {
            return {
                items: ['A', 'B']
            };
        },
        provide() {
            return {
                itemLength: this.items.length
            };
        }
    }
</script>
```

#### src/views/ProvideInjectChild.vue 
```javascript
<script>
    export default {
        inject: ['itemLength'],
        mounted() {
            console.log(this.itemLength);
        }
    }
</script>
```

## Composition API 
컴포지션 API는 컴포넌트 내에서 사용하는 특정 기능을 갖는 코드를 유연하게 구성하여 사용할 수 있도록 Vue3버전에서 추가된 함수 기반의 API입니다.<br><br>

그동안 Vue는 ‘프로젝트 규모가 커질수록 관리하기 힘들다’는 단점이 있었습니다.<br>
data, computed, watch, methods 등 프로젝트 규모가 커질수록, 컴포넌트의 계층구조가 복잡할수록 코드에 대한 추적 및 관리가 어려웠습니다. 하지만 컴포지션 API를 이용하면 setup이라는 메서드 안에서 한 덩어리로 코드를 구현할 수 있어서 코드에 대한 관리가 훨씬 쉬워지게 됩니다. 즉, 컴포지션 API는 그동안 Vue가 가지고 있던 단점을 보완하기 위해서 추가된 Vue 3버전의 핵심 기능입니다.<br><br>

일반적으로 우리가 지금까지 사용했던 API는 API를 호출함으로써 API에 구현된 기능을 그대로 사용할 수 있었습니다. 즉, API는 특정 기능을 가지고 있고, 재사용을 위해 만들어진 것입니다.<br><br>

컴포지션  API 역시 API라는 이름이 붙어 있는 것처럼, 특정 기능을 갖는 함수를 정의하고 API처럼 사용할 수 있게 해주는 것입니다. 결국 궁극적인 목적인 **코드에 대한 재활용성을 높이고, 코드의 가독성을 높이기 위해 추가된 기능**입니다.

### setup
- setup 은 컴포지션 API를 구현하는 곳입니다

- 기존방식
#### src/views/Calculator.vue 
```javascript
<template>
    <div>
        <h2>Calculator</h2>
        <div>
            <input type="text" v-model="num1" @keyup="plusNumbers" />
            <span> + </span>
            <input type="text" v-model="num2" @keyup="plusNumbers" />
            <span> = </span>
            <span>{{ result }}</span>
        </div>
    </div>
</template>
<script>
export default {
    name : 'Calculator',
    data() {
        return {
            num1: 0,
            num2: 0,
            result: 0
        };
    },
    methods : {
        plusNumbers() {
            this.result = parseInt(this.num1) + parseInt(this.num2);
        }
    }
}
</script>
```

- 컴포지션 API 방식
#### src/views/CompositionAPI.vue
```javascript
<template>
    <div>
        <h2>Calculator</h2>
        <div>
            <input type="text" v-model="state.num1" @keyup="plusNumbers" />
            <span> + </span>
            <input type="text" v-model="state.num2" @keyup="plusNumbers" />
            <span> = </span>
            <span>{{ state.result }}</span>
        </div>
    </div>
</template>
<script>
    import { reactive } from 'vue';
    export default {
        name : 'calculator',
        setup() {
            /** 
                reactive를 이용해서 num1, num2, result를 
                실시간 변경사항에 대한 반응형 적용
            */
            let state = reactive({ 
                num1: 0,
                num2: 0,
                result: 0,
            });
            function plusNumbers() {
                state.result = parseInt(state.num1) + parseInt(state.num2);
            }
            /**
             * reactive로 선언된 state와 plusNumbers 함수를 반환함으로써
             * 기존 data, methods 옵션처럼 사용이 가능해짐
             */
            return {
                state,
                plusNumbers
            }
        }
    }
</script>
```

reactive와 computed를 사용하면 input type=text에 바인딩했던 keyup 이벤트를 없앨 수 있고, 코드가 훨씬 더 간결해 집니다.

#### src/views/CompositionAPI2.vue
```javascript
<template>
    <div>
        <h2>Calculator</h2>
        <div>
            <input type="text" v-model="state.num1" />
            <span> + </span>
            <input type="text" v-model="state.num2" />
            <span> = </span>
            <span>{{ state.result }}</span>
        </div>
    </div>
</template>
<script>
import { reactive, computed } from 'vue'; // computed 추가
export default {
    name: 'calculator',
    setup() {
        let state = reactive({
            num1: 0,
            num2: 0,
            result: computed(() => parseInt(state.num1) + parseInt(state.num2)) 
            // computed를 이용해서 num1, num2가 변경이 일어나면 즉시 result로 더한 값을 반환
        });

        return {
            state
        }
    }
}
</script>
```
<br>
작성한 코드를 여러 컴포넌트에서 재사용할 수 있도록 함수를 분리하는 하는 것이 좋습니다.<br>
반응형으로 선언된 외부 함수(function)에서 정상적으로 동작하기 위해서는 toRefs를 사용해야 합니다.<br>

#### src/views/CompositionAPI3.vue
```javascript
<template>
    <div>
        <h2>Calculator</h2>
        <div>
            <input type="text" v-model="num1" />
            <span> + </span>
            <input type="text" v-model="num2" />
            <span> = </span>
            <span>{{ result }}</span>
        </div>
    </div>
</template>
<script>
import { reactive, computed, toRefs } from 'vue'; // toRefs 추가
function plusCalculator() {
    let state = reactive({
        num1: 0,
        num2: 0,
        result: computed(() => parseInt(state.num1) + parseInt(state.num2))
    });
    /** 
     * 반응형으로 선언된 num1, num2, result가 외부 함수(function)
     * 에서 정상적으로 동작하기 위해서는 toRefs를 사용해야 함
     */
    return toRefs(state);
}
export default {
    name : 'calculator',
    setup() {
        let {num1, num2, result} = plusCalculator(); // 외부 함수
        return {
            num1, num2, result
        }
    }
}
</script>
```

컴포넌트 내에 정의된 코드를 다른 컴포넌트에서도 사용할 수 있도록 컴포넌트 밖으로 분리
#### src/common.js
```javascript
import { reactive, computed, toRefs } from 'vue';
const plusCalculator = () => {
    let state = reactive({
        num1: 0,
        num2: 0,
        result: computed(() => parseInt(state.num1) + parseInt(state.num2))
    });
    return toRefs(state);
};
export {
    plusCalculator
}
```

#### src/views/CompositionAPI4.vue
```
<template>
    <div>
        <h2>Calculator</h2>
        <div>
            <input type="text" v-model="num1" />
            <span> + </span>
            <input type="text" v-model="num2" />
            <span> = </span>
            <span>{{ result }}</span>
        </div>
    </div>
</template>
<script>
import { plusCalculator } from '../common.js';
export default {
    name: 'calculator',
    setup() {
        let { num1, num2, result } = plusCalculator();
        return {
            num1, num2, result
        }
    }
}
</script>
```

## Lifecycle Hooks
컴포지션 API내에서 사용할 수 있는 컴포넌트 라이프사이클 훅은 다음 표와 같습니다.

|Options API|Hook inside setup()|
|-----|-----|
|beforeCreate||
|created||
|beforeMount|onBeforeMount|
|mounted|onMounted|
|beforeUpdate|onBeforeUpdate|
|updated|onUpdated|
|beforeUnmount|onBeforeUnmount|
|unmounted|onUnmounted|
|errorCaptured|onErrorCaptured|
|renderTracked|onRenderTracked|
|renderTriggered|onRenderTriggered|

컴포지션 API에서 setup()은 컴포넌트 라이프사이클의 beforeCreate와 created 훅 사이에서 실행되기 때문에 onBeforeCreate, onCreated 훅은 필요가 없고, setup()안에서 코드를 작성하면 됩니다.

#### src/views/SetupMounted.vue
```javascript
<template>
    <h1>Mounted</h1>
</template>
<script>
import { onMounted } from 'vue';
export default {
    setup() {
        onMounted(() => {
            console.log('Component is mounted!');
        });
    }
}
</script>
```

### Provide/Inject
컴포지션 API에서 Provide/Inject 사용하려면 provide와 inject를 별도로 import해야 사용할 수 있습니다. 부모 컴포넌트에서는 provide 함수를 통해서 전달할 값에 대한 키(key), 값(value)을 설정합니다.

#### src/views/CompositionAPIProvide.vue
```javascript
<template>
    <CompositionAPIInject />
</template>

<script>
import { provide } from 'vue'; 
import CompositionAPIInject from './CompositionAPIInject';
export default {
    components: {
        CompositionAPIInject
    },
    setup() {
        provide('title', 'Vue.js 프로젝트');
        // provide 함수를 통해서 전달할 키(key), 값(value) 설정
    }
}
</script>
```

#### src/views/CompositionAPIInject.vue 
```
<template>
    <h1>{{ title }}</h1>
</template>
<script>
import { inject } from 'vue';
export default {
    setup() {
        const title = inject('title');
        // inject를 사용해서 provide에서 정의한 키(key)로 데이터를 전달
        return { title };
    }
}
</script>
```

## 믹스인(mixins)
일반적인 프로그래밍 언어를 이용해서 애플리케이션을 개발할 때 공통 모듈이라고 부르는 파일을 만들게 됩니다. 이 파일에는 자주 사용되는 기능을 메서드로 만들어서 등록해 놓고 공통모듈 파일을 import하고 그 기능을 사용합니다.<br><br>

Vue에서도 이렇게 공통모듈에 해당하는 파일을 만들어서 사용할 수 있는데, 그 중 하나의 방법이 믹스인(mixin)입니다.<br><br>

믹스인(mixin)은 기능을 따로 구현하고, 필요할 때마다 믹스인 파일을 컴포넌트에 결합해서 사용하는 방법입니다.

### 믹스인(mixins) 파일 생성
#### src/views/api.js
```javascript
import axios from 'axios';
export default {
    methods: {
        async $callAPI(url, method, data) {
            return (await axios({
                method,
                url,
                data
            }).catch(e => {
                console.log(e);
            })).data;
        }
    }
}
```
함수 이름은 $callAPI 라고 작성되었습니다. 함수 이름에 $라는 prefix를 사용하는 이유는 믹스인 파일을 사용하는 컴포넌트 내에 동일한 메서드명이 있어서 오버라이딩 되는 것을 방지하기 위해서입니다.<br><br>

일반적으로 컴포넌트에 정의되는 메서드 명에서는 $와 같은 prefix를 사용하지 않기 때문에 믹스인 파일의 메서드명을 이렇게 작성하면 컴포넌트의 메서드명과 구분할 수 있습니다.

### 컴포넌트에서 믹스인(mixins) 사용
다음과 같이 mixins 프로퍼티에서 사용할 믹스인 파일을 정의해서 사용

#### src/views/Mixins.vue
```javascript
<script>
import ApiMixin from '../api.js';
export default {
    /** 사용할 믹스인 파일을 배열로 등록 */
    mixins: [ApiMixin],
    data() {
        return {
            productList: []
        };
    },
    async mounted() {
        this.productList = await this.$callAPI("http://localhost:3000", "get");
        console.log(this.productList);
    }
}
</script>
```

### 믹스인(mixins)에서 라이프사이클 훅 이용하기
#### src/mixins.js
```javascript
export default {
    mounted() {
        console.log("믹스인 mounted");
    },
    unmounted() {
        console.log("믹스인 unmounted");
    }
}
```

#### src/views/component.vue
```javascript
<script>
import mixin from '../mixins.js';
export default {
    mixins: [mixin],
    mounted() {
        console.log('컴포넌트 mounted');
        // 믹스인 mounted
        // 컴포넌트 mounted
    },
    unmounted() {
        console.log('컴포넌트 unmounted');
        // 믹스인 unmounted
        // 컴포넌트 unmounted
    }
}
</script>
```

### 믹스인 파일을 전역으로 등록하기:main.js에 등록
#### src/main.js
```javascript
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import mixins from './mixins'

const app = createApp(App);
app.use(router);
app.mixin(mixins);
app.mount('#app');
```

## Custom Directives
Vue에서는 v-model, v-show 디렉티브 같은 기본 디렉티브 외에도 사용자가 직접 디렉티브를 정의해서 사용할 수 있습니다.<br><br>

커스텀 디렉티브를 전역에서 사용할 수 있도록 등록이 가능하고, 특정 컴포넌트 안에서만 사용하도록 등록도 가능합니다.

### 전역에 등록하여 사용
#### src/main.js 
```javascript
const app = createApp(App);

app.directive('focus', {
    mounted(el) {
        el.focus();
    }
});
```

컴포넌트에서는 다음과 같이 사용
```
<input type“text” v-focus />
```

### 컴포넌트 내에 등록하여 사용 
#### src/views/CustomDirective.vue
```javascript
<template>
    <div>
        <input type="text" v-focus />
    </div>
    <div style="height:1000px;">
        <p v-pin="position">페이지 고정 영역</p>
    </div>
</template>
<script>
export default {
    directives: {
        focus: {
            mounted(el) {
                el.focus()
            }
        },
        pin : {
            mounted(el, binding) {
                el.style.position = 'fixed';
                el.style.top = binding.value.top + 'px';
                el.style.left = binding.value.left + 'px';            }
        }
    },
    data() {
        return {
            position : { top : 50, left: 100 }
        };
    }
}
</script>
```

## CORS(Cross-origin resource sharing) 해결 방법(교차 출처 리소스 공유)
클라이언트 Vue 애플리케이션과 서버 애플리케이션이 각각 별도의 주소(도메인 혹은 포트가 다른 경)로 운영이 되는 경우 CORS 문제가 발생합니다.<br><br>

이를 해결 하기 위해서는 서버의 Response 헤더에 다음을 등록하여 접근을 허용해 주면 됩니다. 
```
Access-Control-Allow-Origin: *
Access-Control-Allow-Header: X-Requested-With
```
- Node.js Express 서버 예시
```javascript
app.use((req, res, next) => {
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "X-Requested-With");
	next();
});
```

#### PHP 서버 예시
```php
header(“Access-Control-Allow-Origin: *”);
header(“Access-Control-Allow-Headers: X-Requested-With”);
```

## Vuex(v4.x)
애플리케이션이 복잡해지고 컴포넌트 수가 많아지면 컴포넌트 간의 데이터 전달 및 관리가 점점 어려워집니다. Vuex는 Vue에서 모든 컴포넌트가 접근 가능한 중앙 집중식 저장소를 두고 데이터 관리 상태를 할 수 있도록 해주는 상태관리 패턴 + 라이브러리입니다.<br><br>

데이터를 store에 저장하고, 프로젝트 전체에서 사용할 수 있도록 해주는 것이 Vuex입니다.

- Vuex 설치
```
npm install vuex@nex --save
```
모든 Vue 애플리케이션의 중심에는 store가 있습니다. 저장소(store)는 애플리케이션의 상태를 저장하고 있는 컨테이너입니다.<br><br>

Vuex 저장소가 일반 전역 객체와 두 가지 다른 점이 있습니다. Vuex store는 반응형입니다. Vue 컴포넌트는 저장소의 상태(state)를 검색할 때 저장소의 상태에 정의된 변수 값의 변경 여부를 바로 알 수 있습니다.

#### src/store.js 
```javascript
import { createStore } from 'vuex'  
const store = createStore({
    state() {
        return {
            count: 0
        }
    },
    mutations : {
        increment (state) {
            state.count++;
        }
    }
});
export default store;
```

#### src/main.js
```javascript
mport { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store';

const app = createApp(App);
app.use(router);
app.use(store);
app.mount('#app');
```

#### src/views/StoreAccess.vue
```
<template>
    <p>Count : {{ count }}</p>
    <button type="button" @click="increment">Increment</button>
</template>
<script>
export default {
    computed: {
        count() {
            return this.$store.state.count;
        }
    },
    methods : {
        increment() {
            this.$store.commit('increment');
        }
    }
}
</script>
```
- <b>State</b><br>state는 프로젝트 전체에서 공통으로 사용할 변수를 정의하는 곳입니다. state에 변수를 정의함으로써, 모든 컴포넌트에서 사용이 가능합니다. State관리를 통해 모든 컴포넌트에서 동일한 값을 사용할 수 있습니다.
- <b>Mutations</b><br>Vuex는 state에 정의된 변수를 직접 변경하는 것을 허용하지 않습니다. 즉, mutations은 state를 변경시키는 역할을 합니다,<br>mutations에 정의된 함수를 commit를 통해서 호출하는 것으로 저장소의 state에 정의된 변수의 값을 변경할 수 있습니다.

## 프로젝트 배포하기
개발된 Vue 프로젝트를 운영환경으로 배포하기 위한 배포파일을 생성합니다.

### 프로젝트 빌드 하기
터미널에서 다음 명령어를 실행해서 배포파일을 생성합니다.
```
npm run build 
```

명령어를 실행하면 다음과 같이 프로젝트 배포를 위한 파일이 프로젝트 루트 디렉토리의 dist 폴더에 생성됩니다.<br><br>

생성된 dist 폴더를 보면 css, img, js 폴더와 index.html이 생성된 것을 확인할 수 있습니다.<br><br>

js폴더를 열어보면 우리가 작성한 모든 js파일과 vue 컴포넌트 파일에 해당하는 js파일이 생성된 것을 확인할 수 있습니다.<br><br>

서비스를 운영하고자 하는 호스팅 서버에 dist 폴더의 파일을 업로드해서 서비스를 구동하면 됩니다. index.html이 Vue 프로젝트 실행 파일이 됩니다.