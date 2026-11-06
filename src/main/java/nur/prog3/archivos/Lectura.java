package nur.prog3.archivos;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class Lectura {
    public static void main(String[] args) throws IOException {
        File f = new File("c:\\temp\\hello.txt");
        List<String> lines = Files.readAllLines(f.toPath(), Charset.defaultCharset());
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
