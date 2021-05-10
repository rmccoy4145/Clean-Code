package com.b.simple.design.business.text;

public class TextHelper {

	public String swapLastTwoCharacters(String str) {
            
            
            int finalIndex = str.length() - 1;
            
            if(finalIndex < 1) return str;
            
            char finalChar = str.charAt(finalIndex);
            char secondToLastChar = str.charAt(finalIndex - 1);            
            
            return str.substring(0, finalIndex - 1)
                    + finalChar
                    + secondToLastChar;
	}

	public String truncateAInFirst2Positions(String str) {
            int finalIndex = str.length() - 1;
            
            if(finalIndex < 1) {
                return str.replaceAll("A", "");
            }
            
            String firstTwoCharactersUpdated = str.substring(0, 2).replaceAll("A", "");
            String restOfString = str.substring(2);
            
            return firstTwoCharactersUpdated + restOfString;

	}
}
