package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {

    String rating;

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents 
    any digit between 1 and 4*/
    public boolean isValidRating() {
        final String currentRating = this.getRating();
        if(currentRating == null) return false;

        if (validBRating(currentRating)) return true;
        if (validARating(currentRating)) return true;
        
        return false;
    }

    private boolean validBRating(final String currentRating) throws NumberFormatException
    {
        List<String> validRatings = Arrays.asList("B1", "B2", "B3", "B4");
        
        if (validRatings.contains(currentRating.toUpperCase())) return true;

        return false;
    }

    private boolean validARating(final String currentRating)
    {
        String firstCharacter = currentRating.substring(0, 1);
        if (firstCharacter.equalsIgnoreCase("A")
                && currentRating.length() == 3
                && StringUtils.isNumeric(currentRating.substring(1, 3)))
        {
            return true;
        }
        return false;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
