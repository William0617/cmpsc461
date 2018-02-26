/**
 * Wenliang Sun
 * wzs51
 * Java 1.8
 * OS: MAC 10.13.3
 * This class just use the E-BNF grammar to parse the tokens.
 */
class Parser {

    Lexer lexer;
    Token token;
    Token firstToken;
    //When a parser method is invoked inside another parser method,
    //the indentation level should be increased by one.
    int countText = 0;
    int countListItem = 0;

    //Constructor
    public Parser(String s) {
        lexer = new Lexer(s);
        token = lexer.nextToken();
        firstToken = token;
    }

    // Entrance of the parser
    public void run() {
        webpage();
    }

    //For the first rule: WEBPAGE -> <body> { TEXT } </body>
    public void webpage() {
        //If the first token is <body>??? If not, exit.
        if (!firstToken.getTokenValue().equals("<body>")) {
            System.err.println("Syntax error: expecting a <body>"
                    + "; saw:"
                    + firstToken.getTokenValue()+". The program will be exited.");
            System.exit(1);
        }

        System.out.println(token.getTokenValue());
        token = lexer.nextToken();
        text();
        while (token.getTokenType() != Token.TokenType.EOI) {
            //Is there other token after </body>?
            if (token.getTokenValue().equals("</body>")
                    && lexer.nextToken().getTokenType() != Token.TokenType.EOI){
                System.out.println("</body>");
                System.err.println("There are some other tokens after </body>." +
                        " The program will be exited.");
                System.exit(1);
            }
            countText = 0;
            text();
        }
        System.out.println("</body>");
        token = lexer.nextToken();
    }

    ////For the second rule:
    // TEXT -> STRING | <b> TEXT </b> | <i> TEXT </i> | <ul> { LISTITEM } </ul>
    private void text() {
        //String is not a tag, so countText should be minus 1 after used.
        if (token.getTokenType() == Token.TokenType.STRING) {
            countText++;
            for (int i = 0; i < countText; ++i) {
                System.out.print("  ");
            }
            System.out.println(token.getTokenValue());
            token = lexer.nextToken();
            countText--;
        }
        //For keywords
        else {
            //For <b> tag
            if (token.getTokenType() == Token.TokenType.KEYWORD) {
                if (token.getTokenValue().equals("<b>")) {
                    countText++;
                    for (int i = 0; i < countText; ++i) {
                        System.out.print("  ");
                    }
                    int innerCount1 = countText;
                    System.out.println("<b>");
                    token = lexer.nextToken();
                    while (!token.getTokenValue().equals("</b>")) {
                        text();
                    }
                    for (int i = 0; i < innerCount1; ++i) {
                        System.out.print("  ");
                    }
                    System.out.println("</b>");
                    token = lexer.nextToken();
                }
                //For <i> tag
                else if (token.getTokenValue().equals("<i>")) {
                    countText++;
                    for (int i = 0; i < countText; ++i) {
                        System.out.print("  ");
                    }
                    int innerCount2 = countText;
                    System.out.println("<i>");
                    token = lexer.nextToken();
                    while (!token.getTokenValue().equals("</i>")) {
                        text();
                    }
                    for (int i = 0; i < innerCount2; ++i) {
                        System.out.print("  ");
                    }
                    System.out.println("</i>");
                    token = lexer.nextToken();
                }
                //For <ul> tag
                else if (token.getTokenValue().equals("<ul>")) {
                    countText++;
                    for (int i = 0; i < countText; ++i) {
                        System.out.print("  ");
                    }
                    int innerCount3 = countText;
                    System.out.println("<ul>");
                    token = lexer.nextToken();
                    countText += innerCount3 ;
                    while (!token.getTokenValue().equals("</ul>")) {
                        listItem(innerCount3);
                    }
                    for (int i = 0; i < innerCount3; ++i) {
                        System.out.print("  ");
                    }
                    System.out.println("</ul>");
                    token = lexer.nextToken();
                }

                else if (token.getTokenValue().equals("</body>")) {
                    token = lexer.nextToken();
                }

                else {
                    System.err.println("Syntax error. The program is exited. ");
                    System.exit(1);
                }
            }
            countText++;
        }
    }
    //For the third rule: LISTITEM -> <li> TEXT </li>
    private void listItem(int indentation) {
        countListItem += indentation ;
        for (int i = 0; i < countListItem; ++i) {
            System.out.print("  ");
        }
        int innerCount4 = countListItem;
        System.out.println("<li>");
        token = lexer.nextToken();
        countText = countListItem;
        while (!token.getTokenValue().equals("</li>")) {
            text();
        }
        for (int i = 0; i < innerCount4; ++i) {
            System.out.print("  ");
        }
        System.out.println("</li>");
        token = lexer.nextToken();
    }
}