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
|Stream<T> sorted()<br>Stream<T> sorted(Comparator<T> comparator)|스트림의 요소를 정렬한다.|
|Stream<R> map(Function<T,R> mapper)<br>DoubleStream mapToDouble(ToDoubleFunction<T> mapper)<br>IntStream mapToInt(ToIntFunction<T> mapper)<br><br>Stream<R> flatMap(Function<T, Stream<R>> mapper)<br>DoubleStream flatMapToDouble(Function<T, DoubleStream> m)<br>IntStream flatMapToInt(Function<T, IntStream> m)<br>LongStream flatMapToLong(Function<T, LongStream> m)|스트림의 요소를 변환한다.|

##### 스트림의 최종 연산 목록
|최종 연산|설명|
|----------|-------|
|void forEach(Consumer<? super T> action)|void forEachOrdered(Consumer<? super T> action)|각 요소에 지정된 작업 수행|
|long count())|스트림의 요소의 개수 반환|
|Optional<T> max(Comparator<? super T> comparator)<br>Optional<T> min(Comparator<? super T> comparator)|스트림의 최대값/최소값을 반환|
|Optional<T> findAny() // 아무거나 하나<br>Optional<T> findFirst() // 첫 번째 요소|스트림의 요소를 하나 반환|
|boolean allMatch(Predicate<T> p) // 모두 만족하는지<br>boolean anyMatch(Predicate<T> p) // 하나라도 만족하는지<br>boolean noneMatch(Predicate<> p) // 모두 만족하지 않는지|주어진 조건을 모든 요소가 만족시키는지, 만족시키지 않는지 확인|
|Object[] toArray()<br>A[] toArray(IntFunction<A[]> generator)|스트림의 모든 요소를 배열로 변환|
|Optional<T> reduce(BinaryOperator<T> accumulator)<br>T reduce(T identity, BinaryOperator<T> accumulator)|스트림 요소를 하나씩 줄여가면서(리듀싱) 계산한다.|
|R collect(Collector<T,A,B> collector)|스트림의 요소를 수집한다.<br>주로 요소를 그룹화하거나 분할한 결과를 컬렉션에 담아 반환하는데 사용한다.|

### 지연된연산
- 최종 연산이 수행되기 전까지는 중간 연산이 수행되지 않는다.
- 중간 연산을 호출하는 것은 단순히 어떤 작업이 수행되어야하는 지를 지정해주는 것이다.
- 최종 연산이 수행되어야 스트림의 요소들이 중간 연산을 거처 최종 연산에서 소모된다.


### 기본자료형을 다루는 스트림


## 스트림만들기

### 컬렉션

### 배열

### 특정 범위의 정수

### 임의의 수

### 람다식 - iterate(), generate()

### 빈 스트림

### 두 스트림의 연결

## 스트림의 중간 연산
### 스트림 자르기 - skip(), limit()

### 스트림 요소 걸러내기 - filter(), distinct()

### 정렬 - sorted()

### 변환 - map()

### 조회 - peek()

### mapToInt(), mapToLong(), mapToDouble()

### flatMap() - Stream<T[]>를 Stream<T>로 변환

## Optional<T>와 OptionalInt

### Optional 객체 생성하기

### Optional객체의 값 가져오기

### OptionalInt, OptionalLong, OptionalDouble


## 스트림의 최종 연산

### forEach()

### 조건 검사 - allMatch(), anyMatch(), noneMatch(), findFirst(), findAny()

### 통계 - count(), sum(), average(), max(), min()

### 리듀싱 - reduce()


## collect()

### 스트림 컬렉션과 배열로 변환 - toList(), toSet(), toMap(), toCollection(), toArray()

### 통계 - counting(), summingInt(), averagingInt(), maxBy(), minBy()

### 리듀싱 - reducing()

### 문자열 결합 - joining()

### 그룹화와 분할 - groupingBy(), partitioningBy()

### partitioningBy()에 의한 분류

### groupingBy()에 의한 분류

## 스트림의 변환
1. 스트림 -> 기본형 스트림 

|from|to|변환 메서드|
|-------|-------|------------|
|Stream\<T\>|IntStream<br>LongStream<br>DoubleStream|mapToInt(ToIntFunction\<T\> mapper)<br>mapToLong(ToLongFunction\<T\> mapper)<br>mapToDouble(ToDoubleFunction\<T\> mapper)|


2. 기본형 스트림 -> 스트림

|from|to|변환 메서드|
|-------|-------|------------|
|IntStream<br>LongStream<br>DoubleStream|Stream\<Integer\><br>Stream\<Long\><br>Stream\<Double\><br>Stream\<U\>|boxed()<br>boxed()<br>boxed()<br>mapToObj(DoubleFunction\<U\> mapper)|

3. 기본형 스트림 -> 기본형 스트림

|from|to|변환 메서드|
|-------|-------|------------|
|Stream\<T\>|IntStream<br>LongStream<br>DoubleStream|LongStream<br>DoubleStream|asLongStream()<br>asDoubleStream()|


4. 스트림 -> 부분 스트림

|from|to|변환 메서드|
|-------|-------|------------|
|Stream\<T\><br>IntStream|Stream\<T\><br>IntStream|skip(long n)<br>limit(long maxSize)|


5. 두 개의 스트림 -> 스트림

|from|to|변환 메서드|
|-------|-------|------------|
|Stream\<T\>, Stream\<T\>|Stream\<T\>|concat(Stream\<T\> a, Stream\<T\>b)|
|IntStream, IntStream|IntStream|concat(IntStream a, IntStream b)|
|LongStream, LongStream|LongStream|concat(LongStream a, LongStream b)|
|DoubleStream, DoubleStream|DoubleStream|concat(DoubleStream a, DoubleStream b)|


6. 스트림의 스트림 -> 스트림

|from|to|변환 메서드|
|-------|-------|------------|
|Stream\<\Stream\<T\>\>|Stream\<T\>|flatMap(Function mapper)|
|Stream\<IntStream\>|IntStream|flatMapToInt(Function mapper)|
|Stream\<LongStream\>|LongStream|flatMapToLong(Function mapper)|
|Stream\<DoubleStream\>|DoubleStream|flatMapToDouble(Function mapper)|


7. 스트림 -> 병렬 스트림

|from|to|변환 메서드|
|-------|-------|------------|
|Stream\<T\><br>IntStream<br>LongStream<br>DoubleStream|Stream\<T\><br>IntStream<br>LongStream<br>DoubleStream|parallel() // 스트림 -> 병렬 스트림<br>sequential() //  병렬 스트림 -> 스트림|


8. 스트림 -> 컬렉션

|from|to|변환 메서드|
|-------|-------|------------|
|Stream\<T\><br>IntStream<br>LongStream<br>DoubleStream|Collection\<T\><br>List\<T\><br>Set\<T\>|collect(Collectors.toCollection(Supplier supplier))<br>collect(Collectors.toList())<br>Collectors.toSet())|


9. 컬렉션 -> 스트림

|from|to|변환 메서드|
|-------|-------|------------|
|Collection\<T\>, List\<T\>, Set\<T\>|Stream\<T\>|stream()|


10. 스트림 -> Map

|from|to|변환 메서드|
|-------|-------|------------|
|Stream\<T\><br>IntStream<br>LongStream<br>DoubleStream|Map<K,V>|collect(Collectors.toMap(Function Key, Function value))<br>collect(Collectors.toMap(Function k, Function v, BinaryOperator merge))|


11. 스트림 -> 배열

|from|to|변환 메서드|
|-------|-------|------------|
|Stream\<T\>|Object[]<br>T[]|toArray()<br>toArray(intFunction\<A[]\> generator)|
|IntStream<br>LongStream<br>DoubleStream|int[]<br>long[]<br>double[]|toArray()|
