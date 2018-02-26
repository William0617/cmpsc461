/**
 * Wenliang Sun
 * wzs51
 * Java 1.8
 * OS: MAC 10.13.3
 * Distinguish different tokens, get next token and
 * let the parser to know if a token is valid or invalid
 */
class Lexer {

    private final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String digits = "0123456789";
    private final String otherSign = "</>";

    String stmt;
    int index = 0;
    char ch;

    //Just a constructor.
    public Lexer(String s) {
        stmt = s + "$";
        index = 0;
        ch = nextChar();
    }

    //Get the next token from the string.
    public Token nextToken() {
        do {
            //If the char is letter or digit, use concat to judge if it is a string token.
            //If so, got the whole token and return it as a String type
            if (Character.isLetter(ch) || Character.isDigit(ch)) {
                String string = concat(letters + digits);
                return new Token(Token.TokenType.STRING, string);
            }
            //If it is not a string type, judge it whether a invalid one
            else if (isOtherSign(ch)) {
                String string = concatForKeyword(letters + digits + otherSign);
                if (string.equals("invalid"))
                    return new Token(Token.TokenType.INVALID, Character.toString(ch));
                return new Token(Token.TokenType.KEYWORD, string);
            }
            //Other signs
            else {
                switch (ch) {
                    case ' ':
                        ch = nextChar();
                        break;
                    case '$':
                        return new Token(Token.TokenType.EOI, "");
                    default:
                        ch = nextChar();
                        return new Token(Token.TokenType.INVALID, Character.toString(ch));
                }
            }
        } while (true);
    }

    //In this project, it includes "/", "<" and ">" signs.
    private boolean isOtherSign(char ch) {
        if (ch == '<' || ch == '/' || ch == '>')
            return true;
        return false;
    }

    //Get a next char from the string.
    private char nextChar() {
        char ch = stmt.charAt(index);
        index = index + 1;
        return ch;
    }


    //Concat several strings and match it with the next char.
    private String concat(String set) {
        StringBuffer r = new StringBuffer("");
        do {
            r.append(ch);
            ch = nextChar();
        } while (set.indexOf(ch) >= 0);//If the ch is existed in the digits+chars, >= 0; else -1;
        return r.toString();
    }

    //Concat several strings and match it with the next char,
    //return a token string or a invalid token
    private String concatForKeyword(String set) {
        StringBuffer r = new StringBuffer("");
        do {
            r.append(ch);
            ch = nextChar();
            if (isKeyword(r.toString())){
                return r.toString();
            }
        } while (set.indexOf(ch) >= 0);
        return "invalid";
    }

    //Judge the string whether a keyword?
    private boolean isKeyword(String string) {

        return string.equals("<body>") || string.equals("</body>") ||
                string.equals("<b>") || string.equals("</b>") ||
                string.equals("<i>") || string.equals("</i>") ||
                string.equals("<ul>") || string.equals("</ul>") ||
                string.equals("<li>") || string.equals("</li>");
    }

    private boolean check(char c) {
        ch = nextChar();
        if (ch == c) {
            ch = nextChar();
            return true;
        } else {
            return false;
        }
    }

    public void error(String msg) {
        System.err.println("\nError: location " + index + " " + msg);
        System.exit(1);
    }

}
