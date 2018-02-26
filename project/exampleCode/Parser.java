/**
 *
 * Vibhu Patel
 * vxc5074
 * javac 1.8.0_121
 * OS: Ubuntu 16.04 LTS
 * Outputs ID and value from Lexer class
 * 
 */


//package pkg461project1;


class Parser {

    Lexer lexer;
    Token token;

    //The parser constructor takes in an sql syntax 
    public Parser(String s) {
        lexer = new Lexer(s + "$");
        token = lexer.nextToken();
    }   
    /*Checks to see if the string passed starts w the keyword SELECT and if it does calls the Keyword and IDList methords and checks weather the next keyword is FROM and then follows the same steps and 
      then checks to see if the next keyword after FROM is WHERE       
    */
    public void run() {
        System.out.println("<Query>");
        if (!token.getTokenValue().equals("SELECT")) {
            System.out.println("Syntax error: expecting expected-token SELECT saw token " + token.getTokenValue());
        } else {
            Keyword();
            IdList();
            if (!token.getTokenValue().equals("FROM")) {
                System.out.println("Syntax error: expecting expected-token FROM saw token " + token.getTokenValue());
            } else {
                Keyword();
                IdList();
                if (!token.getTokenValue().equals("WHERE")) {
                    System.out.println("Syntax error: expecting expected-token WHERE saw token " + token.getTokenValue());
                } else {
                    Keyword();
                    CondList();
                    match(Token.TokenType.EOI);
                    System.out.println("</Query>");
                    token = lexer.nextToken();
                }
            }
        }

    }
    //Prints out the keyword in the XML indenting
    public void Keyword() {
        System.out.print("\t<KEYWORD>");
        System.out.print(token.getTokenValue());
        System.out.println("</KEYWORD>");
        token = lexer.nextToken();
    }
    
    //Checks to see proper IDList token has been passed and prints it in XML indenetation and exits the system if incorrect syntax is passed
    public void IdList() {
        System.out.println("\t<IDList>");
        if (!token.getTokenType().equals(Token.TokenType.ID)) {
            System.out.println("Error: " + token.getTokenValue() + " value was not a valid ID");
            System.exit(1);

        } else {
            System.out.println("\t\t<ID>" + token.getTokenValue() + "</ID>");
            token = lexer.nextToken();
            while (token.getTokenType() == Token.TokenType.COMMA) {
                System.out.println("\t\t<COMMA>,</COMMA>");
                token = lexer.nextToken();
                if (!token.getTokenType().equals(Token.TokenType.ID)) {
                    System.out.println("Error: " + token.getTokenValue() + " value was not a valid ID");
                    System.exit(1);
                }
                System.out.println("\t\t<ID>" + token.getTokenValue() + "</ID>");
                token = lexer.nextToken();
            }
            System.out.println("\t</IDList>");

        }
    }
    //Prints out the CondList in the XML indenting and calls the Cond methord
    public void CondList() {
        System.out.println("\t<CondList>");
        Cond();
        System.out.println("\t</CondList>");
        token = lexer.nextToken();
    }

    //Checks to see proper Cond token has been passed and prints it in XML indenetation and exits the system if incorrect syntax is passed
    public void Cond() {
        System.out.println("\t\t<Cond>");
        if (!token.getTokenType().equals(Token.TokenType.ID)) {
            System.out.println("Error: " + token.getTokenValue() + " value was not a valid ID");
            System.exit(1);

        } else {
            System.out.println("\t\t\t<ID>" + token.getTokenValue() + "</ID>");
            token = lexer.nextToken();
            if (!token.getTokenType().equals(Token.TokenType.OPERATOR)) {
                System.out.println("Error: " + token.getTokenValue() + " value was not a valid ID");
                System.exit(1);

            } else {
                System.out.println("\t\t\t<OPERATOR>" + token.getTokenValue() + "</OPERATOR>");
                token = lexer.nextToken();
                System.out.println("\t\t\t<Term>");
                Term();
                System.out.println("\t\t\t</Term>");
                if (token.getTokenValue().equals("AND")) {
                    System.out.println("\t\t</Cond>");
                    System.out.print("\t");                    
                    Keyword();                                       
                    System.out.println("\t\t<Cond>");
                    System.out.println("\t\t\t<ID>" + token.getTokenValue() + "</ID>");
                    token = lexer.nextToken();
                    System.out.println("\t\t\t<OPERATOR>" + token.getTokenValue() + "</OPERATOR>");
                    token = lexer.nextToken();
                    System.out.println("\t\t\t<Term>");
                    Term();
                    System.out.println("\t\t\t</Term>");                    
                }
            }
            System.out.println("\t\t</Cond>");

            token = lexer.nextToken();

        }
    }

    //Differenciates the two different kinds of terms or sysrem exits when incorrect syntax passed
    public void Term() {
        if (token.getTokenType() == Token.TokenType.ID) {
            System.out.println("\t\t\t\t<Identifier>" + token.getTokenValue() + "</Identifier>");
        } else if (token.getTokenType() == Token.TokenType.INT) {
            System.out.println("\t\t\t\t<Int>" + token.getTokenValue() + "</Int>");

        } else if (token.getTokenType() == Token.TokenType.FLOAT) {
            System.out.println("\t\t\t\t<Float>" + token.getTokenValue() + "</Float>");

        } else {
            System.err.println("Syntax error: expecting an ID, an int, or a float" + "; saw:" + Token.typeToString(token.getTokenType()));
            System.exit(1);
        }
        token = lexer.nextToken();
    }
    
    //Sets value, also gets the next token if the token category matches that of the current token else returns error
    private String match(Token.TokenType tp) {
        String value = token.getTokenValue();
        if (token.getTokenType() == tp) {
            token = lexer.nextToken();
        } else {
            error(tp);
        }
        return value;
    }
   //Just a print methord for the errors
    private void error(Token.TokenType tp) {
        System.err.println("Syntax error: expecting: " + Token.typeToString(tp) + "; saw: " + Token.typeToString(token.getTokenType()));
        System.exit(1);
    }

}
