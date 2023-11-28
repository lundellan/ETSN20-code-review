import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        if (args.length < 3 || !args[0].equals("search")) {
            System.out.println("Usage: search [-i] <pattern> <file>");
            return;
        }

        boolean caseInsensitive = false;
        int startIndex = 1;

        if (args.length > 3 && args[1].equals("-i")) {
            caseInsensitive = true;
            startIndex = 2;
        }

        String pattern = args[startIndex];
        String fileName = args[startIndex + 1];

        try {
            searchFile(pattern, fileName, caseInsensitive);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static void searchFile(String pattern, String fileName, boolean caseInsensitive) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;

            while ((line = br.readLine()) != null) {
                if (caseInsensitive) {
                    if (line.toLowerCase().contains(pattern.toLowerCase())) {
                        System.out.println("Line " + lineNumber + ": " + line);
                    }
                } else {
                    if (line.contains(pattern)) {
                        System.out.println("Line " + lineNumber + ": " + line);
                    }
                }
                lineNumber++;
            }
        }
    }
}
