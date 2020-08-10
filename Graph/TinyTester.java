// TinyTester.java
//
// Tests your Graph class constructor, which calls your loadGraph, and
// tests your toString() method.

import java.io.*;
class TinyTester
{
	public static void main( String args[] ) throws Exception
    {
         Graph graph1 = new Graph( "graphdata.txt" );
         System.out.print( graph1 );
    }
}
