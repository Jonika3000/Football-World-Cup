package org.example.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Team {
    private String name;

    public Team(String name) {
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("I am a string");
        boolean isSpecialChar = matcher.find();

        if (isSpecialChar)
            throw new RuntimeException("Team name cannot contain special character");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
