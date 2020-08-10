// CS/C0E 445 LAB #10 THE GRAPH CLASS
// GRAPHTESTER.JAVA FOR LAB#10
// TESTS ALL THE METHODS OF THE GRAPH CLASS
// THIS IS THE TESTER USED BY THE GRADING SCRIPT FOR PROJECT #9

import java.io.*;
import java.util.*;

class GraphTester {
    public static void main(String args[]) throws Exception {
        if (args.length < 1) {
            System.out.println("usage: must enter filename on command line.");
            System.exit(0);
        }

        Graph myGraph = new Graph(args[0]);
        System.out.print(myGraph); //
        System.out.println();
        System.out.println("maxOutDegree: " + myGraph.maxOutDegree());
        System.out.println("minOutDegree: " + myGraph.minOutDegree());
        System.out.println("maxInDegree: " + myGraph.maxInDegree());
        System.out.println("minInDegree: " + myGraph.minInDegree());
        System.out.println("maxDegree: " + myGraph.maxDegree());
        System.out.println("minDegree: " + myGraph.minDegree());

        System.out.println("\nremoving edge 3 -> 5");
        myGraph.removeEdge(3, 5); // SHOULD NOT THROW EXCEPTION
        System.out.println("Graph after removing edge 3 -> 5");
        System.out.print(myGraph);

        System.out.println("\nremoving edge 6 -> 1");
        myGraph.removeEdge(6, 1); // SHOULD NOT THROW EXCEPTION
        System.out.println("Graph after removing edge 6 -> 1");
        System.out.print(myGraph);

        System.out.println("\nremoving edge 17 -> 6");
        myGraph.removeEdge(17, 6);// THIS -DOES- THROW AN EXCEPTION

    } // END main

} // END GRAPHTESTER CLASS
