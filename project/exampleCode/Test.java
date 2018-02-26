/**
 *
 * Vibhu Patel
 * vxc5074
 * javac 1.8.0_121
 * OS: Ubuntu 16.04 LTS
 * The class to test the Programming Assignment 1
 */
//package pkg461project1;

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

	//Create a new Parser and pass it the SQL syntax to test and convert to XML
        Parser f = new Parser("SELECT J1 FROM T1 WHERE C1=5.23 AND C2=56");
        Parser t = new Parser("SELECT C1 FROM T1 WHERE C1=5.23 AND C2=56");
        Parser k = new Parser("WHERE C1,C2,C3,C4 FROM A1,A2,A3,A4 WHERE ID < 1.11 AND ID = 5");
        Parser p = new Parser("SELECT C1,C2 FROM T1 WHERE C1=5.23"); 
        Parser a = new Parser("SELECT C1,K1,J1,F4 FROM A1 WHERE ID > 6");        
        Parser c = new Parser ("SELECT C1,C2 FROM T1");        
        Parser d = new Parser ("SELECT C1 FROM T1");

        d.run();
        System.out.println();
        c.run();
        System.out.println();
        k.run();
        System.out.println();
        f.run();
        System.out.println();
        t.run();
        System.out.println();
        a.run();
        System.out.println();        
        p.run();
        System.out.println();
        
        
    }

}
