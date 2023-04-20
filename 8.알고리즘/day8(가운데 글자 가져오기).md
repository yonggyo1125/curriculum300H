# 문항
- 단어 s의 가운데 글자를 반환하는 함수를 만들어 보세요. 단어의 길이가 짝수라면 가운데 두글자를 반환하면 됩니다.
- s는 길이가 1 이상, 100이하인 스트링입니다.

<!--
# 답안
```java
public class CenterChar {
	public static void main(String[] args) {	
		System.out.println(getCenterChar("abcde"));
		System.out.println(getCenterChar("abcdef"));
	}
	
	static String getCenterChar(String str) {
		String c = "";
		if(str.length()%2==0){
			c = str.charAt((str.length()/2)-1)+""+str.charAt(str.length()/2);
		} else{
			c = str.charAt(str.length()/2)+"";
		}
		
		return c;
	}
}
```
-->
