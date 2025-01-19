package software.ulpgc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TsvFileTitleReader implements TitleReader {
    private final File file;

    public TsvFileTitleReader(File file) {
        this.file = file;
    }

    @Override
    public List<Title> read() {
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            read.readLine();
            return readWith(read);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Title> readWith(BufferedReader read) throws IOException {
        TsvTitleDeserializer deserializer = new TsvTitleDeserializer();
        List<Title> titles = new ArrayList<>();
        while (true) {
            String line = read.readLine();
            if (line == null) break;
            titles.add(deserializer.deserialize(line));
        }
        return titles;
    }
}
