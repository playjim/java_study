package sample;

public class Familly {
    String name;
    int age;
    String relationship;
    public void Human(String relationship, String name, int age){
        this.relationship = relationship;
        this.name = name;
        this.age = age;
    }
    public String toString(){
        return relationship+","+name+","+age;
    }
}
