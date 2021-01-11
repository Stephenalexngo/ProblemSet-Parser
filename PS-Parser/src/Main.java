import java.io.File;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        File grammar_file = new File("grammarfile.txt");
        // File input_file = new File("inputfile.txt");
        
        FileWriter fw = new FileWriter("output.txt");
        
        Parser parser = new Parser(grammar_file);
        
        parser.parse();
        
        fw.close();
    }
}
