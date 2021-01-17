import java.util.ArrayList;

public class Rule {
    private StringKind LHS;
    private ArrayList<ArrayList<StringKind> > RHS;

    public Rule(){
    }

    public void setLHS(StringKind lhs){
        this.LHS = lhs;
    }

    public void setRHS(ArrayList<ArrayList<StringKind> > rhs){
        this.RHS = rhs;
    }

    public StringKind getLHS(){
        return this.LHS;
    }

    public ArrayList<ArrayList<StringKind> > getRHS(){
        return this.RHS;
    }
}
