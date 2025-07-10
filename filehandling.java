import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandlingUtility {

    public static void writeFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("File written successfully: " + fileName);
        }
    }

    public static String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }

    public static void modifyFile(String fileName, String target, String replacement) throws IOException {
        Path path = Paths.get(fileName);
        String content = Files.readString(path);

        content = content.replace(target, replacement);

        Files.writeString(path, content);
        System.out.println("File modified successfully: " + fileName);
    }

    public static void main(String[] args) {
        String fileName = "example.txt";
        String content = "Hello, this is a sample file.\nThis file will be modified.";

        try {
            // Write to file
            writeFile(fileName, content);

            // Read file content
            String readContent = readFile(fileName);
            System.out.println("Content of the file:");
            System.out.println(readContent);

            // Modify file content
            modifyFile(fileName, "sample", "modified sample");

            // Read modified content
            String modifiedContent = readFile(fileName);
            System.out.println("Modified content of the file:");
            System.out.println(modifiedContent);

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
