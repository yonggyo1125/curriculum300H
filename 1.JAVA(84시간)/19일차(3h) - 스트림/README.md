# 스트림(Stream)
## 스트림이란?
- 데이터 소스가 무엇이든 간에 같은 방식으로 다룰 수 있게 데이터를 추상화 하고 데이터를 다루는데 자주 사용되는 메서드들을 정의해 놓음
- 데이터 소스가 무엇이든 간에 같은 방식으로 다룰 수 있게 되면서 코드의 재사용성이 높아진다.
- 배열이나, 컬렉션뿐만 아니라 파일에 저장된 데이터도 모두 같은 방식으로 다룰 수 있다.

#### 스트림은 데이터 소스를 변경하지 않는다.
- 스트림은 데이터 소스르 부터 데이터를 읽기만할 뿐, 데이터 소스를 변경하지 않는다.

#### 스트림은 일회용이다.
- 스트림은 한번 사용하면 닫혀서 다시 사용할 수 없다.
- 다시 사용하려면 스트림을 다시 생성해야 한다.
```
strStream1.sorted().forEach(x -> System.out.println(x));
int numOfStr = strStream1.count(); // 스트림이 닫혔으므로 에러 발생 
```

#### 스트림은 작업을 내부 반복으로 처리한다.
- 내부 반복이라는 것은 반복문을 메서드 내부에 숨길 수 있다는 것을 의미
```
void forEach(Consumer<? super T> action) {
	Objects.requireNonNull(action); // 매개변수의 널 체크
	
	for(T t : src) {
		action.accept(T);
	}
}
```

### 스트림의 연산
#### 중간연산 
연산 결과가 스트림인 연산, 스트림에 연속해서 중간 연산할 수 있다.

#### 최종연산
연산 결과가 스트림이 아닌 연산. 스트림의 요소를 소모하므로 단 한번만 가능

```
stream.distinct().limit(5).sorted().forEach(x -> System.out.println(x));
      
distinct(), limit(5), sorted() - 중간연산
forEach - 최종연산
```

##### 스트림의 중간 연산 목록 
|중간 연산|설명|
|----------|-------|
|Stream<T> distinct()|중복을 제거|
|Stream<T> filter(Predicate<T> predicate)|조건에 안 맞는 요소 제외|
|Stream<T> limit(long maxSize)|스트림의 일부를 잘라낸다|
|Stream<T> skip(long n)|스트림의 일부를 건너뛴다|
|Stream<T> peek(Consumer<T> action)|스트림의 요소에 작업 수행|
|Stream<T> sorted()
<Stream<T> sorted(Comparator<T> comparator)|스트림의 요소를 정렬한다.|


##### 스트림의 최종 연산 목록


### 지연된연산
- 최종 연산이 수행되기 전까지는 중간 연산이 수행되지 않는다.
- 중간 연산을 호출하는 것은 단순히 어떤 작업이 수행되어야하는 지를 지정해주는 것이다.
- 최종 연산이 수행되어야 스트림의 요소들이 중간 연산을 거처 최종 연산에서 소모된다.


### 기본자료형을 다루는 스트림

