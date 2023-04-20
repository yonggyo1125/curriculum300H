## 문항
- A 자동차와 B자동차는 서로 같은 도착 지점을 향해 가고 있습니다.
- A 자동차는 최초  60km/h속도로 이동하다가 한시간마다 10km/h씩 속력을 높이고 있고
- B 자동차는 최초 40km의 속도로 이동하다가 한시간마다 15km/h씩 속력을 높이고 있습니다. 
- A 자동차와 B 자동차가 최초로 만나는 지점(km)를 구하시오.

<!--
## 답안
```
public class CommonMultiple {

	public static void main(String[] args) {
		int aCarDistance = 60;
		int bCarDistance = 40;
		
		int commonDistance = 0;
		int hour = 1;
		while(true) {
			aCarDistance += 10; // A 자동차 이동 
			bCarDistance += 15; // B 자동차 이동 
			hour++;
			if (aCarDistance == bCarDistance) {
				commonDistance = aCarDistance;
				break;
			}
		}
		
		System.out.printf("A와 B자동차가 만나는 지점 : %d시간 뒤  %d km 지점에서 만남",  hour, commonDistance);
	}
}
```
-->
