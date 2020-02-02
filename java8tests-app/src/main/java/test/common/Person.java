package test.common;

public class Person implements Comparable<Person> {
	
	private String name;
	private int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	@Override
	public String toString() {
		return this.getName() + " - " + this.getAge();
	}

	@Override
	public int compareTo(Person o) {
		return this.getName().compareTo(o.getName());
	}
}
