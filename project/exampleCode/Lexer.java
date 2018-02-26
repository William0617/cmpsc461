/**
 *
 * Vibhu Patel
 * vxc5074
 * javac 1.8.0_121
 * OS: Ubuntu 16.04 LTS
 * Differentiates between different tokens and allows the parser to tell if a token is valid or invalid
 */
//package pkg461project1;


class Lexer {

    private final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String digits = "0123456789";

    String stmt;
    int index = 0;
    char ch;

    public Lexer(String s) {
        stmt = s;
        index = 0;
        ch = nextChar();
    }

    public Token nextToken() {
        do {
            if (stmt.equals("SELECT") || stmt.equals("FROM") || stmt.equals("WHERE") || stmt.equals("AND")) {
                return new Token(Token.TokenType.KEYWORD, stmt);
            }
            if (Character.isLetter(ch)) {
                String id = concat(letters + digits);
                return new Token(Token.TokenType.ID, id);
            } else if (Character.isDigit(ch)) {
                String num = concat(digits);
                if (ch != '.') {
                    return new Token(Token.TokenType.INT, num);
                }
                num += ch;
                ch = nextChar();
                if (Character.isDigit(ch)) {
                    num += concat(digits);
                    return new Token(Token.TokenType.FLOAT, num);
                } else {
                    return new Token(Token.TokenType.INVALID, num);
                }
            } else {
                switch (ch) {
                    case ' ':
                        ch = nextChar();
                        break;
                    case ',':
                        ch = nextChar();
                        return new Token(Token.TokenType.COMMA, "");
                    case '=':
                        ch = nextChar();
                        return new Token(Token.TokenType.OPERATOR, "=");
                    case '<':
                        ch = nextChar();
                        return new Token(Token.TokenType.OPERATOR, "<");
                    case '>':
                        ch = nextChar();
                        return new Token(Token.TokenType.OPERATOR, ">");
                    case '$':
                        return new Token(Token.TokenType.EOI, "");
                    default:
                        ch = nextChar();
                        return new Token(Token.TokenType.INVALID,
                                Character.toString(ch));
                }
            }

        } while (true);
    }

    private char nextChar() {
        char ch = stmt.charAt(index);
        index = index + 1;
        return ch;
    }

    private String concat(String set) {
        StringBuffer r = new StringBuffer("");
        do {
            r.append(ch);
            ch = nextChar();
        } while (set.indexOf(ch) >= 0);
        return r.toString();
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
