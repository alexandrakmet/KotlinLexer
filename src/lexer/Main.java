package lexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println(Lexer.tokenize(getInputFromFile("src/resources/input.txt")));
    }

    private static String getInputFromFile(String path) {
        StringBuilder input = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String t;
            while ((t = reader.readLine()) != null)
                input.append(t).append('\n');
            reader.close();
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        return input.toString();
    }
}
