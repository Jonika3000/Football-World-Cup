package org.example.models;

import org.example.helpers.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Team {
    private String name;

    public Team(String name) throws Exception {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if(!Validator.validName(name))
            throw new IllegalArgumentException("Name cannot contain special characters");
        this.name = name;
    }
}
