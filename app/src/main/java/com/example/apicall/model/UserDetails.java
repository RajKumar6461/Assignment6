package com.example.apicall.model;

import java.io.Serializable;

public class UserDetails  implements Serializable {


    private String id;
    private String name;

    /**
     * Constructor of student class
     *
     * @param name of String type
     * @param id- of String type
     */
    public UserDetails(String name, String id) {
        this.name = name;
        this.id = id;
    }


    public String getmName() {
        return name;
    }

    /**
     * used to get id
     * @return id of Sring type
     */
    public String getId() {
        return id;
    }

    /**
     * used to set name
     * @param Name
     */
    public void setmName(String Name) {
        this.name = Name;
    }

    /**
     * used to set rollNo
     * @param RollNo
     */
    public void setmRollNo(String RollNo) {
        this.id = id;
    }
}