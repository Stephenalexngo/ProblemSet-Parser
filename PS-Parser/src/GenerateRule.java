import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class GenerateRule {

    private File filetoparse;

    public GenerateRule(File file){
        this.filetoparse = file;
    }

    public HashMap<String,Rule> generate() throws Exception {
        FileReader fr = new FileReader(this.filetoparse);
        BufferedReader br = new BufferedReader(fr);
        String line;
        HashMap<String,Rule> grammarRules = new HashMap<String,Rule>();

        while( (line = br.readLine()) != null){
            if(!line.equals("")){
                String[] arr = line.replace(";", "").split(":");
                boolean leftside = true;
                Rule newRule = new Rule();
                StringKind strkindLHS = new StringKind();
                    // STRING KIND FOR LHS

                for (String word : arr){
                    if(leftside){
                        strkindLHS.setName(word);
                        strkindLHS.setKind("nonepsilon");
                        newRule.setLHS(strkindLHS);
                        leftside = false;
                    }
                    else{
                        String[] arrRule = word.split("\\|");
                        ArrayList<ArrayList<StringKind> > arrRHS = new ArrayList<ArrayList<StringKind> >();

                        for(String eachRule : arrRule){
                            String[] insideRule = eachRule.split(" ");
                            ArrayList<StringKind> arrStringKind = new ArrayList<StringKind>();

                            if(eachRule.equals(" ")){
                                strkindLHS.setKind("epsilon");
                            }

                            for(String stringkind : insideRule){
                                if(!stringkind.equals("")){
                                    StringKind strkind = new StringKind();
                                    stringkind = stringkind.replace("\'", "");

                                    if(stringkind.length() > 1){
                                        strkind.setName(stringkind.replace('*', ' ')
                                                              .replace('+', ' ')
                                                              .replace('?', ' ')
                                                              );
                                    }
                                    else
                                        strkind.setName(stringkind);
                                    
                                    if(stringkind.length() == 1)
                                        strkind.setKind("normal");
                                    else if(stringkind.charAt(stringkind.length()-1) == '*')
                                        strkind.setKind("zerotomany");
                                    else if(stringkind.charAt(stringkind.length()-1) == '+')
                                        strkind.setKind("onetomany");
                                    else if(stringkind.charAt(stringkind.length()-1) == '?')
                                        strkind.setKind("zerotoone");
                                    else
                                        strkind.setKind("normal");

                                    arrStringKind.add(strkind);
                                }
                            }

                            arrRHS.add(arrStringKind);
                        }

                        newRule.setRHS(arrRHS);
                    }
                }

                grammarRules.put(newRule.getLHS().getName(), newRule);
            }
        }

        fr.close();
        System.out.println("Generated Rules Complete!");
        return grammarRules;
    }
}
