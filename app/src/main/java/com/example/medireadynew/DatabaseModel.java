package com.example.medireadynew;

/**
 * Created by jordanyep on 2018-03-19.
 */

public class DatabaseModel {
    private String firstName, lastName, relationship, gender, age;
    private int img; //within drawable

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName= firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName= lastName;
    }

    public String getRelationship() {
        return relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship= relationship;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender= gender;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age= age;
    }

    public int getImg() {
        return img;
    }
}
