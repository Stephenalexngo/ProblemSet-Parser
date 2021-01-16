import java.util.ArrayList;

public class Rule {
    private String LHS;
    private ArrayList<ArrayList<StringKind> > RHS;

    public Rule(){
    }

    public void setLHS(String lhs){
        this.LHS = lhs;
    }

    public void setRHS(ArrayList<ArrayList<StringKind> > rhs){
        this.RHS = rhs;
    }

    public String getLHS(){
        return this.LHS;
    }

    public ArrayList<ArrayList<StringKind> > getRHS(){
        return this.RHS;
    }
}
