import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        File grammar_file = new File("src/grammarfile.txt");
        // File input_file = new File("inputfile.txt");
        
        FileWriter fw = new FileWriter("src/output.txt");
        
        GenerateRule generate_rule = new GenerateRule(grammar_file);
        HashMap<String,Rule> grammar_rules = generate_rule.generate();

        grammar_rules.entrySet().forEach(rule->{
            System.out.println(rule.getKey());
            
            for(int x=0; x<rule.getValue().getRHS().size(); x++){
                for(int y=0; y<rule.getValue().getRHS().get(x).size(); y++){
                    System.out.println(" Name: " + rule.getValue().getRHS().get(x).get(y).getName() + "  Kind: " + rule.getValue().getRHS().get(x).get(y).getKind());
                }
            }
        });
        
        fw.close();
    }
}
