import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    static Stack<StringKind> grammar_stack = new Stack<StringKind>();
    static Stack<ArrayList<StringKind> > history_stack = new Stack<ArrayList<StringKind> >();
    static Stack<Character> input_stack = new Stack<Character>();
    static StringKind history_parent = new StringKind();
    static int currholder = 0;
    static boolean isMultiple = false; 
    static boolean isRequired = false;
    static boolean isAccepted = true;
    
    static HashMap<String,Rule> grammar_rules = new HashMap<String,Rule>();

    static void startup(String input_line){
        // start node
        grammar_stack.push(new StringKind("start","normal"));

        // put all input in stack
        for(int x=input_line.length()-1; x > -1; x--){
            input_stack.push(input_line.charAt(x));
        }
    }

    static void expand(String lefthandside){
        StringKind curLHS = grammar_rules.get(lefthandside).getLHS();
        ArrayList<ArrayList<StringKind> > curRHS = grammar_rules.get(lefthandside).getRHS();

        int cursize = curRHS.size();

        if(curLHS.getKind().equals("epsilon")){
            cursize--;
        }

        // putting in history stack
        if(cursize > 1){
            history_parent = curLHS;
            for(int x=cursize-1; x>0; x--){
                history_stack.push(curRHS.get(x));
            }
        }

        // grammar expansion
        // if(grammar_stack.peek().getKind().equals("normal")){
        //     grammar_stack.pop();
        // }
        // else{
            // one to many implementation
        // }
        grammar_stack.pop();

        for(int y=curRHS.get(0).size()-1; y>-1; y--){
            grammar_stack.push(curRHS.get(0).get(y));

            if(cursize > 1)
                currholder++;
        }
    }

    static void performBacktrack(){
        for(int i=0; i<currholder; i++){
            grammar_stack.pop();
        }

        currholder = 0;

        if(history_stack.size() > 1){
            for(int x=history_stack.peek().size()-1; x>-1; x--){
                grammar_stack.push(history_stack.peek().get(x));
                currholder++;
            }
        }
        else{
            if(history_parent.getKind().equals("nonepsilon")){
                isAccepted = false;
            }
        }

        history_stack.pop();
    }

    static void parse(){
        boolean isAccepted = true;

        while(!input_stack.isEmpty() && isAccepted){
            if(!grammar_stack.isEmpty()){
                if( Character.isLowerCase(grammar_stack.peek().getName().charAt(0)) ){
                    expand(grammar_stack.peek().getName());
                }
                else if( Character.isUpperCase(grammar_stack.peek().getName().charAt(0)) ){
                    if( grammar_rules.get(grammar_stack.peek().getName()).getRHS().get(0).get(0).getName().equals(input_stack.peek().toString()) ){
                        input_stack.pop();
                        grammar_stack.pop();
                        history_stack.clear();
                        currholder = 0;
                    }
                    else{
                        performBacktrack();
                    }
                }
            }
            else{
                isAccepted = false;
                break;
            }
        }

        if(!isAccepted)
            System.out.println("REJECT");
        else{

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

        // System.out.println("HELLO = "+grammar_rules.keySet().contains("expression"));
        // grammar_rules.entrySet().forEach(rule->{
        //     System.out.println(rule.getValue().getLHS().getName() + "  KIND = " + rule.getValue().getLHS().getKind());
        //     System.out.println("LHS SIZE = " + rule.getValue().getLHS().getName().length());
            
        //     for(int x=0; x<rule.getValue().getRHS().size(); x++){
        //         for(int y=0; y<rule.getValue().getRHS().get(x).size(); y++){
        //             System.out.println(" Name: " + rule.getValue().getRHS().get(x).get(y).getName() + "  Kind: " + rule.getValue().getRHS().get(x).get(y).getKind());
        //         }
        //     }
        // });
        
        fw.close();
    }
}
