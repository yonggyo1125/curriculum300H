## 문항
- 정수로 된 난수 n개 발생 시킵니다.
- 발생한 정수를 작은 순서대로 나열 한 후 중간값을 구하시오
- n개가 홀수라면 중간값은 1개, 짝수라면 중간값은 2개가 됩니다.

<!--
```

import java.util.Random;
import java.util.Arrays;

public class Median {
	
	public static void main(String[] args) {
		int n = 9;
		// 정수로 된 난수 n개 발생 시킵니다.
		int[] nums = generateNums(n);
		
		System.out.println("발생한 난수 : " + Arrays.toString(nums));
		
		// 발생한 난수를 작은 순서대로 정렬
		for (int i = 0; i < nums.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < nums.length; j++) {
				// 앞 인덱스와 뒷 인덱스 숫자를 비교해서 앞 인덱스 숫자가 크면 뒤의 숫자 인덱스로 교체
				if (nums[minIndex] > nums[j]) {
					minIndex = j;
				}
			}
			//비교 했을때 앞 인덱스 숫자가 더 작으면 건너뛰기
			if (minIndex == i) { 
				continue;
			}
			
			// 크기 비교하여 i번째 인덱스의 변경이 있다면 기존값을 임시로 담고 서로 변경
			int tmp = nums[minIndex];
			nums[minIndex] = nums[i];
			nums[i] = tmp;
		}
		
		System.out.println("정렬된 난수 : " + Arrays.toString(nums));
		
		
		// 중간값 구하기 - 홀수일땐 1개, 짝수일떈 2개
		if (n % 2 == 1) { // 홀수 
			int i = (int)Math.ceil(n / 2.0);
			System.out.println("중간값 : " + nums[i]);
		} else { // 짝수
			int i = n / 2;
			System.out.println("중간값 : " + nums[i - 1] + ", " + nums[i]);
		}
		
	}
	
	public static int[] generateNums(int n) {
		int[] nums = new int[n];
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			nums[i] = rand.nextInt();
		}
		
		return nums;
	}
}
```
-->
