public class StringKind {
    private String KIND;
    private String NAME;

    public StringKind(String kind, String name){
        this.KIND = kind;
        this.NAME = name;
    }

    public void setKind(String kind){
        this.KIND = kind;
    }

    public void setName(String name){
        this.NAME = name;
    }

    public String getKind(){
        return this.KIND;
    }

    public String getName(){
        return this.NAME;
    }
}
