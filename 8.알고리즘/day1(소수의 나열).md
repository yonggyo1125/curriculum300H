## 소수의 나열 
- 소수는 자신과 1이외의 정수로 나누어 떨어지지 않는 정수 입니다. 예를 들어 소수인 13은 , 2,3, ..., 12 가운데 어떤 정수로도 나누어 떨어지지 않습니다.
- 그러므로 어떤 정수 n에 대하여 아래의 조건을 만족하면 소수임을 알 수 있습니다.
```
2부터 n - 1까지의 어떤 정수로도 나누어 떨어지지 않습니다.
```
- 만약 나누어 떨어지는 정수가 하나이상 존재하면 그 수는 합성수(composite number)입니다. 

<!-- ### 문항 : 1000 이하의 소수를 나열하는 프로그램을 작성하시오
```
class PrimeNumber {
	public static void main(String[] args) {
		int counter = 0; // 나눗셈의 횟수
		
		for(int i = 2; i <= 1000; i++) {
			int j;
			for (j = 2; j < i; j++) {
				counter++;
				if (i % j == 0) { // 나누어 떨어지면 소스가 아니므로 
					break;  // 반복 중단
				}				
			}
			if (j == i)  { // 마지막 까지 나누어 떨어지지 않으면 출력 
				System.out.println(j)
			}
		}
		
		System.out.println("나눗셈을 구행한 횟수 : " + counter);
	}
}
```
-->
