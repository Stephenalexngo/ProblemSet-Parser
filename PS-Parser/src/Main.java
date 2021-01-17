import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    static Stack<String> grammar_stack = new Stack<String>();
    static Stack<Character> input_stack = new Stack<Character>();
    static HashMap<String,Rule> grammar_rules;

    static void startup(String input_line){
        grammar_stack.push("start");

        for(int x=input_line.length()-1; x > -1; x--){
            input_stack.push(input_line.charAt(x));
        }
    }

    static void expand(){
        // System.out.println(grammar_rules.get("start").getRHS().get(0).get(0).getName());
    }

    static void performBacktrack(){

    }

    static void parse(){
        while(!input_stack.isEmpty()){
            if(!grammar_stack.isEmpty()){
                
            }
            else
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        File grammar_file = new File("src/grammarfile.txt");
        File input_file = new File("src/inputfile.txt");
        FileReader fr = new FileReader(input_file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        FileWriter fw = new FileWriter("src/output.txt");
        
        GenerateRule generate_rule = new GenerateRule(grammar_file);
        grammar_rules = generate_rule.generate();

        while( (line = br.readLine()) != null){
            line = line.replace(" ", "");
            startup(line);
            parse();
        }

        // grammar_rules.entrySet().forEach(rule->{
        //     System.out.println(rule.getValue().getLHS().getName() + "  KIND = " + rule.getValue().getLHS().getKind());
            
        //     for(int x=0; x<rule.getValue().getRHS().size(); x++){
        //         for(int y=0; y<rule.getValue().getRHS().get(x).size(); y++){
        //             System.out.println(" Name: " + rule.getValue().getRHS().get(x).get(y).getName() + "  Kind: " + rule.getValue().getRHS().get(x).get(y).getKind());
        //         }
        //     }
        // });
        
        fw.close();
    }
}
