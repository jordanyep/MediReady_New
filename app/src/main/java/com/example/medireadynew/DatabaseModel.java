package com.example.medireadynew;

/**
 * Created by jordanyep on 2018-03-19.
 */

public class DatabaseModel {
    private String firstName, lastName, relationship, gender, age;
    private String medicalID,allergies,medication,conditions;
    private int img; //within drawable

    //TEST
    private byte[] image;
    //

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

    public String getMedicalID() {
        return medicalID;
    }
    public void setMedicalID(String medicalID) {
        this.medicalID= medicalID;
    }

    public String getAllergies() {
        return allergies;
    }
    public void setAllergies(String allergies) {
        this.allergies= allergies;
    }

    public String getMedication() {
        return medication;
    }
    public void setMedication(String medication) {
        this.medication= medication;
    }

    public String getConditions() {
        return conditions;
    }
    public void setConditions(String conditions) {
        this.conditions= conditions;
    }


    //TEST
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
}
