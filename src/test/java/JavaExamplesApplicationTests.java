import com.github.fabriciolfj.javaexamples.JavaExamplesApplication;
import com.github.fabriciolfj.javaexamples.configuration.PostgresSqlContainerDevMode;
import org.springframework.boot.SpringApplication;

//@SpringBootTest
class JavaExamplesApplicationTests {

	public static void main(String[] args) {
		SpringApplication.from(JavaExamplesApplication::main)
				.with(PostgresSqlContainerDevMode.class)
				.run(args);
	}

}
