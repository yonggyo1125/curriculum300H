package day04.array;

public class TwoDimension {
	public static void main(String[] args) {
		int[][] arr = {{1,2,3}, {4,5,6}}; // 2차원 배열 선언과 동시에 초기화
		
		for(int i = 0; i < arr.length; i++) { // 행 
			for(int j = 0; j < arr[i].length; j++) { // 열
				System.out.println(arr[i][j]);
			}
			System.out.println();
		}
	}
}
