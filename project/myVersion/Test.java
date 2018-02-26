/**
 * Wenliang Sun
 * wzs51
 * Java 1.8
 * OS: MAC 10.13.3
 * This class includes many cases to test the Lexer.java and Parser.java
 */

public class Test {

    public static void main(String args[]) {
        testLexer();
        testParser();
    }

    private static void testParser() {
        //case 1
        System.out.println("-------------------------Parser case 1------------------------------");
        Parser p1 = new Parser("<body> google </body>");
        p1.run();
        //case 2
        System.out.println("-------------------------Parser case 2------------------------------");
        Parser p2 = new Parser("<body> google <b> <i> <b> yahoo </b> </i> </b> </body>");
        p2.run();
        //case 3
        System.out.println("-------------------------Parser case 3------------------------------");
        Parser p3 = new Parser("<body> a <b> b </b> <i> yahoo </i> </body>");
        p3.run();
        //case 4
        System.out.println("-------------------------Parser case 4------------------------------");
        Parser p4 = new Parser("<body>a<b>b</b><i>yahoo</i><ul><li>haha</li></ul></body>");
        p4.run();

        //case 5
        System.out.println("-------------------------Parser case 5------------------------------");
        Parser p5 = new Parser("aaaaaa <body> a <b> b </b> <i> yahoo </i> </body>aaaaaa");
        p5.run();
    }

    private static void testLexer() {
        //case 1
        System.out.println("-------------------------Lexer case 1------------------------------");
        Lexer lex = new Lexer("<body>google</body>");
        Token tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 2
        System.out.println("-------------------------Lexer case 2------------------------------");
        lex = new Lexer("<body> goo  gle   <b> <i> <b> yah oo  </b>  </i> </b>  </body>  ");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 3
        System.out.println("-------------------------Lexer case 3------------------------------");
        lex = new Lexer("<body> goo &*% gle   <b> <i> <b> yah oo  </b>  </i> </b>  </body>  ");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 4
        System.out.println("-------------------------Lexer case 4------------------------------");
        lex = new Lexer("&%^( <body> *&(");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 5
        System.out.println("-------------------------Lexer case 5------------------------------");
        lex = new Lexer("<>");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 6
        System.out.println("-------------------------Lexer case 6------------------------------");
        lex = new Lexer("<body");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 7
        System.out.println("-------------------------Lexer case 7------------------------------");
        lex = new Lexer("<body>");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 8
        System.out.println("-------------------------Lexer case 8------------------------------");
        lex = new Lexer("body");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 9
        System.out.println("-------------------------Lexer case 9------------------------------");
        lex = new Lexer("<body>a<b>b</b><i>yahoo </i> </body>");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 10
        System.out.println("-------------------------Lexer case 10------------------------------");
        lex = new Lexer("<bodyyyyyy>google</body>");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 11
        System.out.println("-------------------------Lexer case 11------------------------------");
        lex = new Lexer("<body>google</body></body>");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");

        //case 12
        System.out.println("-------------------------Lexer case 12------------------------------");
        lex = new Lexer(" <body>a<b>b</b><i>yahoo</i><ul><li>haha</li></ul></body>");
        tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            tk.print();
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");
    }
}
