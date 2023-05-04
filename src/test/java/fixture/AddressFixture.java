package fixture;

import com.github.fabriciolfj.javaexamples.data.Address;
import com.github.fabriciolfj.javaexamples.data.Person;

import java.util.Random;

public class AddressFixture {

    private static final Random RANDOM = new Random();
    
    private AddressFixture() {
        
    }

    public static final Address getValid(final Person person) {
        return Address
                .builder()
                .state("SP")
                .city("Serrana")
                .number(RANDOM.nextInt(100))
                .street("Rua ainda nao sei")
                .person(person)
                .build();
    }
}
