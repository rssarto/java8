package epam;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EpamTest {


    @Test
    public void shouldCountNames(){
        final List<String> names = Arrays.asList("Ricardo", "Krystian", "Ricardo", "Java");
        final Map<String, Long> stringLongMap = Epam.countWords(names);
        Assertions.assertThat(stringLongMap).isNotNull();
        Assertions.assertThat(stringLongMap).isNotEmpty();
        Assertions.assertThat(stringLongMap.size()).isEqualTo(3);

        Assertions.assertThat(stringLongMap.containsKey("Ricardo")).isTrue();
        Assertions.assertThat(stringLongMap.get("Ricardo")).isEqualTo(2L);

        Assertions.assertThat(stringLongMap.containsKey("Krystian")).isTrue();
        Assertions.assertThat(stringLongMap.get("Krystian")).isEqualTo(1L);

        Assertions.assertThat(stringLongMap.containsKey("Java")).isTrue();
        Assertions.assertThat(stringLongMap.get("Java")).isEqualTo(1L);
    }
}
