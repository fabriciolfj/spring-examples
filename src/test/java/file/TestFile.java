package file;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestFile {

    //@Test
    public void whenReadingAFile_thenReadsCharByChar() {
        StringBuilder result = new StringBuilder();

        try (FileReader fr = new FileReader("src/test/resources/sampleText2.txt")) {
            int i = fr.read();

            while(i != -1) {
                result.append((char)i);

                i = fr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals("qwerty", result.toString());
    }

    @Test
    public void whenReadingAFile_thenReadsLineByLine() {
        final StringBuilder result = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader("src/test/resources/sampleText2.txt"))) {
            String line;

            while((line = br.readLine()) != null) {
                result.append(line);
                result.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals("first line\nsecond line\nthird line\n", result.toString());
    }
}
