## 문항
- 정수로 된 난수 n개 발생 시킵니다.
- 정수를 내림차순(큰수에서 작은수로)으로 정렬하여 출력하시오

<!--
```

import java.util.Random;
import java.util.Arrays;

public class SortDecending {

	public static void main(String[] args) {
		// 정수로 된 난수 n개 발생 시킵니다.
		int n = 10;
		int[] nums = generateNums(n);
		
		System.out.println("발생한 난수 : " + Arrays.toString(nums));
		
		// 정수를 내림차순(큰수에서 작은수로)으로 정렬
		for (int i = 0; i < nums.length - 1; i++) {
			int maxIndex = i;
			for (int j = i + 1; j < nums.length; j++  ) {
				// 앞 인덱스와 뒷 인덱스 숫자를 비교해서 앞 인덱스 숫자가 작으면 뒤의 숫자 인덱스로 교체
				if (nums[maxIndex] < nums[j]) {
					maxIndex = j;
				}
			}
			//비교 했을때 앞 인덱스 숫자가 더 크면 건너뛰기
			if (maxIndex == i) {
				continue;
			}
			
			// 크기 비교하여 i번째 인덱스의 변경이 있다면 기존값을 임시로 담고 서로 변경
			int tmp = nums[maxIndex];
			nums[maxIndex] = nums[i];
			nums[i] = tmp;
		} 
		
		System.out.println("정렬된 난수 : " + Arrays.toString(nums));
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

- 실행 결과
```
발생한 난수 : [711543063, -908709980, 1516589453, -1341997225, -1790515098, 631636891, -518346466, 2099626329, 1152334687, -740685879]
정렬된 난수 : [2099626329, 1516589453, 1152334687, 711543063, 631636891, -518346466, -740685879, -908709980, -1341997225, -1790515098]
```
-->
