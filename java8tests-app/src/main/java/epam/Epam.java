package epam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Epam {

    public static void main(String[] args){
        Map<String, Long> stringLongMap = countWords(Arrays.asList("Ricardo", "Krystian", "Ricardo", "Java"));
        System.out.println(stringLongMap);
    }

    public static Map<String, Long> countWords(List<String> input){
        Map<String, Long> countNamesMap = input.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return countNamesMap;
    }
}
