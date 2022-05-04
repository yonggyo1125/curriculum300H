package day15;

public class FruitBoxEx1 {
	public static void main(String[] args) {
		Box<Fruit> fruitBox = new Box<Fruit>();
		Box<Apple> appleBox = new Box<Apple>();
		Box<Toy> toyBox = new Box<Toy>();
		// Box<Grape> grapeBox = new Box<Apple>(); //  에러. 타입 불일치
		
		fruitBox.add(new Fruit());
		fruitBox.add(new Apple()); // OK.  void add(Fruit item)
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
		// appleBox.add(new Toy()); // 에러. Box<Apple>에는 Apple 만 담을 수 있음.
		
		toyBox.add(new Toy());
		// toyBox.add(new Apple() // 에러, Box<Toy>에는 Apple을 담을 수 없다.
		
		System.out.println(fruitBox);
		System.out.println(appleBox);
		System.out.println(toyBox);
	}
}