package org.football_world_cap.model;

import org.football_world_cap.validator.Validator;

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
