
/**
 *
 * Vibhu Patel
 * vxc5074
 * javac 1.8.0_121
 * OS: Ubuntu 16.04 LTS
 * Stores the value and the category of any token object
 */

//package pkg461project1;


import java.util.regex.*;
import java.lang.String;

public class Token {

    public enum TokenType {
        INT, FLOAT, ID, COMMA, EOI, INVALID, OPERATOR, KEYWORD
    }

    private TokenType category;
    private String value;
    
    //The constructor to initilize the token which takes in the value and the category of the token to be created
    Token(TokenType t, String v) {
        category = t;
        value = v;
    }
    //Returns the token Category
    TokenType getTokenType() {
        return category;
    }

    //Returns the token value
    String getTokenValue() {
        return value;
    }

    //Pritns out the Categorty of the current token
    void print() {
        String s = "";
        switch (category) {
            case INT:
            case FLOAT:
            case KEYWORD:
            case OPERATOR:
            case ID:
                s = value;
                break;
            case COMMA:
                s = ";";
                break;

            case EOI:
                s = "";
            case INVALID:
                s = "invalid";
                break;
        }
        System.out.print(s);
    }

    //This methord sets the string to the category of the token and returns it as a static string
    public static String typeToString(TokenType tp) {
        String s = "";
        switch (tp) {
            case INT:
                s = "Int";
                break;
            case FLOAT:
                s = "Float";
                break;
            case ID:
                s = "ID";
                break;
            case COMMA:
                s = "Semicolon";
                break;
            case KEYWORD:
                s = "KEYWORD";
                break;
            case OPERATOR:
                s = "OPERATOR";
                break;
            case INVALID:
                s = "Invalid";
                break;
        }
        return s;
    }

}
