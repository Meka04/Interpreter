package model.expressions;

public enum RelationalOperator {
        EQUAL, GEQUAL, LEQUAL, GREATER, LESS, NOTEQUAL;
        public String toString(){
            switch (this){
                case EQUAL:
                    return "==";
                case GEQUAL:
                    return ">=";
                case GREATER:
                    return ">";
                case LESS:
                    return "<";
                case LEQUAL:
                    return "<=";
                case NOTEQUAL:
                    return "!=";
                default:
                    return "";
            }
        }
    }

