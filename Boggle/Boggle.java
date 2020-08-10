import java.io.*;
import java.util.*;
 
// just generates all the strings & prints them as they are generated
 
public class Boggle
{   
    static String[][] board;
    static final short SMALLEST_WORD_LENGTH = 3;
 
    static TreeSet<String> hits = new TreeSet<String>();
    static TreeSet<String> dictionary = new TreeSet<String>();
    static HashSet<Integer> wordLengths = new HashSet<Integer>();

    public static void main( String args[] ) throws Exception
    {   
        board = loadBoard( args[1] );
         
        BufferedReader dictFile = new BufferedReader(new FileReader(args[0]));
        while (dictFile.ready()){
            String line = dictFile.readLine();
            // Doesn't add the word if it is smaller than 3 chars, or larger than the max possible 
            // length (depending on board dimensions, which is just NxN).
            if (line.length() >= SMALLEST_WORD_LENGTH && line.length() <= Math.pow(board.length, 2)){
                dictionary.add(line);
                wordLengths.add(line.length());
            }
            // wordLengths keeps track of all valid lengths of word inside the dictionary.
            // This is useful for pruning later, as it simply skips all words whose length is not in wordsLength.
        }
        dictFile.close();

        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[row].length; col++){
                dfs(row, col, "");
            }
        // FOR EACH [R][C] THE WORD STARTS EMPTY

        // Print out hits.
        for (String word : hits)
            System.out.println(word);
    } // END MAIN ----------------------------------------------------------------------------
 
    static void dfs( int r, int c, String word)
    {   
        // Append new character to current word
        word += board[r][c];
        // Search dictionary for the word. 
        if (searchDict(word))
            hits.add(word);
        else
            if (!dictionary.higher(word).startsWith(word))
                return;
        // If the dictionary doesn't have the word, then check the dictionary for the next highest word
        // (lexographically greater). If the next highest word doesn't have current word as a prefix, STOP.

        // N
        if (((r - 1 >= 0 && r - 1 < board.length) && (c >= 0)) && board[r - 1][c] != null){
            String unMarked = board[r][c]; //save to restore board after recursion
            board[r][c] = null; // mark
            dfs (r - 1, c, word); // move, add String to NORTH to word.
            board[r][c] = unMarked; // unmark, for next iteration.
        }
        // NE
        if (((r - 1 >= 0 && r - 1 < board.length) && (c + 1 >= 0 && c + 1 < board[r - 1].length)) && board[r - 1][c + 1] != null){
            String unMarked = board[r][c]; //save to restore board after recursion
            board[r][c] = null; // mark
            dfs (r - 1, c + 1, word); // move, add String to NORTHEAST to word.
            board[r][c] = unMarked; // unmark, for next iteration.
        }
        // E
        if (((r >= 0) && (c + 1 >= 0 && c + 1 < board[r].length)) && board[r][c + 1] != null){
            String unMarked = board[r][c]; //save to restore board after recursion
            board[r][c] = null; // mark
            dfs (r, c + 1, word); // move, add String to EAST to word.
            board[r][c] = unMarked; // unmark, for next iteration.
        }
        // SE
        if (((r + 1 >= 0 && r + 1 < board.length) && (c + 1 >= 0 && c + 1 < board[r].length)) && board[r + 1][c + 1] != null){
            String unMarked = board[r][c]; //save to restore board after recursion
            board[r][c] = null; // mark
            dfs (r + 1, c + 1, word); // move, add String to SOUTHEAST to word.
            board[r][c] = unMarked; // unmark, for next iteration.
        }
        // S
        if (((r + 1 >= 0 && r + 1 < board.length) && (c >= 0)) && board[r + 1][c] != null){
            String unMarked = board[r][c]; //save to restore board after recursion
            board[r][c] = null; // mark
            dfs (r + 1, c, word); // move, add String to SOUTH to word.
            board[r][c] = unMarked; // unmark, for next iteration.
        }
        // SW
        if (((r + 1 >= 0 && r + 1 < board.length) && (c - 1 >= 0 && c - 1 < board[r + 1].length)) && board[r + 1][c - 1] != null){
            String unMarked = board[r][c]; //save to restore board after recursion
            board[r][c] = null; // mark
            dfs (r + 1, c - 1, word); // move, add String to SOUTHWEST to word.
            board[r][c] = unMarked; // unmark, for next iteration.
        }
        // W
        if (((r >= 0) && (c - 1 >= 0 && c - 1 < board[r].length)) && board[r][c - 1] != null){
            String unMarked = board[r][c]; //save to restore board after recursion
            board[r][c] = null; // mark
            dfs (r, c - 1, word); // move, add String to WEST to word.
            board[r][c] = unMarked; // unmark, for next iteration.
        }
        // NW
        if (((r - 1 >= 0 && r - 1 < board.length) && (c - 1 >= 0 && c - 1 < board[r - 1].length)) && board[r - 1][c - 1] != null){
            String unMarked = board[r][c]; //save to restore board after recursion
            board[r][c] = null; // mark
            dfs (r - 1, c - 1, word); // move, add String to NORTHWEST to word.
            board[r][c] = unMarked; // unmark, for next iteration.
        }                
    } // END DFS ----------------------------------------------------------------------------
 
    //=======================================================================================
    static String[][] loadBoard( String fileName ) throws Exception
    {   Scanner infile = new Scanner( new File(fileName) );
        int rows = infile.nextInt();
        int cols = rows;
        String[][] board = new String[rows][cols];
        for (int r=0; r<rows; r++)
            for (int c=0; c<cols; c++)
                board[r][c] = infile.next();
        infile.close();
        return board;
    } //END LOADBOARD 
     
    static boolean searchDict(String word){
        // Checks if word is big enough, and then if the word is of a valid length.
        if (word.length() >= SMALLEST_WORD_LENGTH && wordLengths.contains(word.length()))
            if (dictionary.contains(word)) // If the dictionary contains it, return true.
                return true;
        return false;
 
    }
 
} // END BOGGLE CLASS
