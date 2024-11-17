package model.expressions;

public enum ArithmeticalOperator {
    PLUS,MINUS,MULTIPLY,DIVIDE;

    public String toString(){
        switch(this){
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case MULTIPLY:
                return "*";
            case DIVIDE:
                return "/";
            default:
                return "";
        }
    }
}
