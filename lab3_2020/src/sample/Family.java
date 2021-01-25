package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Family {
    private SimpleStringProperty name;
    private SimpleIntegerProperty age;
    private SimpleStringProperty relationship;

    Family(String relationship, String name, int age) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.relationship = new SimpleStringProperty(relationship);
    }

    public String toString() {
        return this.relationship.get() + " " + this.name.get() + " " + this.age.get();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public String getRelationship() {
        return relationship.get();
    }

    public void setRelationship(String value) {
        relationship.set(value);
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int value) {
        age.set(value);
    }
}
