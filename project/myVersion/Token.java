/**
 * Wenliang Sun
 * wzs51
 * Java 1.8
 * OS: MAC 10.13.3
 * Store the value and category of any token.
 *
 */
public class Token {

    //Three type token in this project
    public enum TokenType {
        STRING, KEYWORD, INVALID, EOI
    }

    private TokenType category;
    private String value;
    
    //The constructor is used to initialize the token
    //And it includes the token category and token value
    Token(TokenType t, String v) {
        value = v;
        category = t;
    }

    public TokenType getTokenType() {
        return category;
    }

    public String getTokenValue() {
        return value;
    }

    //Print out the Category of the current token
    public void print() {
        String s = "";
        switch (category) {
            case KEYWORD:
            case STRING:
                s = value;
                break;
            case EOI:
                s = "";
                break;
            case INVALID:
                s = "invalid";
                break;
        }
        System.out.print(s);
    }

    //This method sets the string to the category of the
    // token and returns it as a static string
    public static String typeToString(TokenType tp) {
        String result = "";
        switch (tp) {
            case STRING:
                result = "STRING";
                break;
            case KEYWORD:
                result = "KEYWORD";
                break;
            case INVALID:
                result = "INVALID";
                break;
        }
        return result;
    }
}
