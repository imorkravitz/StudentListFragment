package com.example.class3demo2.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey
    @NonNull
    // must declare global members
    String name = "";
    String id = "";
    boolean flag = false;

    public Student(){} // must have for empty student
    public Student(String name, String id, boolean flag) {
        this.name = name;
        this.id = id;
        this.flag = flag;
    }
    // must have
    public void setId(String id) {
        this.id = id;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isFlag() {
        return flag;
    }
}
