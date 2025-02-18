package org.example.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validScore (int score) {
        return score < 0;
    }

    public static boolean validName (String name) {
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("I am a string");

        return !matcher.find();
    }
}
