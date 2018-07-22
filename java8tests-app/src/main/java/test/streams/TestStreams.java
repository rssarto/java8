package test.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import test.common.Person;

public class TestStreams {
	
	private static final int PEOPLE_QUANTITY = 5_000_000;
	private static final List<Person> personList = new ArrayList<>();
	
	private static final int initRange = 25;
	private static final int finalRange = 80;

	
	static {
		populatePeopleCollection();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		filterPersonByAge();
		filterPersonStreamByAge();
		filterPersonParallelStreamByAge();
		*/
		//delta_encode(new int[] {8,25626,25757,24367,24267,16,100,2,7277});
	}
	
	private static void filterPersonByAge() {
		final long initTime = System.currentTimeMillis();
		List<Person> filteredList = new ArrayList<>();
		for( Person person : personList ) {
			if( person.getAge() >= initRange && person.getAge() <= finalRange ) {
				filteredList.add(person);
			}
		}
		print("filterPersonByAge", initTime, "size: " + filteredList.size());
	}
	
	private static void filterPersonStreamByAge() {
		long initTime = System.currentTimeMillis();
		List<Person> filteredList = personList.stream().filter(strPerson -> { return strPerson.getAge() >= initRange && strPerson.getAge() <= finalRange;}).collect(Collectors.toList());
		print("filterPersonStreamByAge", initTime, "size: " + filteredList.size());
	}
	
	private static void filterPersonParallelStreamByAge() {
		long initTime = System.currentTimeMillis();
		List<Person> filteredList = personList.parallelStream().filter(strPerson -> { return strPerson.getAge() >= initRange && strPerson.getAge() <= finalRange;}).collect(Collectors.toList());
		print("filterPersonParallelStreamByAge", initTime, "size: " + filteredList.size());
	}
	
	
	private static void populatePeopleCollection() {
		Stream.iterate(1, count -> count + 1)
			.limit(PEOPLE_QUANTITY)
			.forEach((count) -> {
				personList.add(new Person("person_" + count  , ThreadLocalRandom.current().nextInt(1, 100))); 
			});
	}
	
	private static void printPeopleList(List<Person> people) {
		if( people != null ) {
			people.stream().forEach(System.out::println);
		}
	}
	
	private static void print(String methodId, long initTime, String additionalText) {
		long duration = System.currentTimeMillis() - initTime;
		System.out.format("%1s Time: %2d ms - %3s\n", methodId, duration, additionalText);
		//System.out.printf(format, args)
		System.out.println((int)Byte.MAX_VALUE);
	}
}
