package fixture;

import com.github.fabriciolfj.javaexamples.data.Person;

import java.util.ArrayList;

public class PersonFixture {

    private PersonFixture() {

    }

    public static final Person toValid() {
        return Person.builder()
                .name("teste")
                .lastName("silva")
                .addresses(new ArrayList<>())
                .build();
    }
}
