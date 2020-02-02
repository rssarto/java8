package test.streams;

import org.junit.Test;
import test.common.Person;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionsTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> "ricardo")
                .thenAccept(s -> System.out.println(s));

        voidCompletableFuture.get();
    }

    @Test
    public void sumNumbersWithReduce(){
        final List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final Integer result = integers.stream().reduce(0, Math::addExact);
        System.out.println(result);
        assertThat(result).isEqualTo(45);
    }

    @Test
    public void sumNumbersWithIntStream(){
        final List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final int result = integers.stream().mapToInt(value -> value).sum();
        assertThat(result).isEqualTo(45);

        assertThat(45).isEqualTo(integers.stream().reduce(Math::addExact).get());
    }

    @Test
    public void collectionGroupBy(){
        final List<Person> people = List.of(new Person("ricardo", 37),
                new Person("ricardo", 31),
                new Person("viviane", 41),
                new Person("rafaela", 7));

        System.out.println(String.format("average age: %s", people.stream().collect(Collectors.averagingInt(Person::getAge))));

        //Grouping by name and results in a list
        final Map<String, List<Person>> collect = people.stream().collect(Collectors.groupingBy(Person::getName));
        System.out.println(collect);

        //Grouping by name and results in Set
        final Map<String, Set<Person>> groupToSet = people.stream().collect(Collectors.groupingBy(Person::getName, toSet()));
        System.out.println(groupToSet);

        final Map<String, IntSummaryStatistics> groupToSum = people.stream().collect(Collectors.groupingBy(Person::getName, Collectors.summarizingInt(Person::getAge)));
        assertThat(groupToSum.get("ricardo").getSum()).isEqualTo(68);
        assertThat(groupToSum.get("ricardo").getCount()).isEqualTo(2);

        Function<String, Predicate<Person>> nameIsEqualTo = (name) -> person -> name.equals(person.getName());
        Set<Person> collect2 = people.stream().collect(Collectors.filtering(nameIsEqualTo.apply("ricardo"), toSet()));

        List<Person> sortedList = people.stream()
                .filter(nameIsEqualTo.apply("ricardo").negate())
                .sorted()
                .collect(Collectors.toUnmodifiableList());

        final Long collect1 = people.stream().collect(Collectors.counting());
        assertThat(collect1).isEqualTo(4L);

        System.out.println(collect);

    }


}
