public enum ItemCondition {
    NEW{
        @Override public String toString(){
            return "nowy";
        }
    },
    USED{
        @Override public String toString(){
            return "uzywany";
        }
    },
    DAMAGED{
        @Override public String toString(){
            return "uszkodzony";
        }
    };
}
