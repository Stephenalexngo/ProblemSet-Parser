import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class Parser {

    private File filetoparse;

    public Parser(File file){
        this.filetoparse = file;
    }

    public void parse() throws Exception {
        FileReader fr = new FileReader(this.filetoparse);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while( (line = br.readLine()) != null){
            System.out.println(line);
            
            // The terminal symbols are [0 – 9], [ +, *, (, ), [, ]]. NOTE: ‘[‘ and ‘]’ are included.
        }

        fr.close();
        System.out.println("Parsing Complete!");
    }
}
