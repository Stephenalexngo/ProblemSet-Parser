import java.util.ArrayList;

public class Rule {
    private String LHS;
    private ArrayList<String> RHS;

    public Rule(String lhs, ArrayList<String> rhs){
        this.LHS = lhs;
        this.RHS = rhs;
    }

    public String getLHS(){
        return this.LHS;
    }

    public ArrayList<String> getRHS(){
        return this.RHS;
    }
}
