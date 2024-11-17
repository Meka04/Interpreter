package model.expressions;

public enum LogicalOperator {
    AND, OR;

    public String toString(){
        switch(this){
            case AND:
                return "&&";
            case OR:
                return "||";
            default:
                return "";
        }
    }
}
