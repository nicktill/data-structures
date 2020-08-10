import java.io.*;

public class Knapsack
{	
    public static void main( String[] args ) throws Exception
	{
        if (args.length != 1)
            die( "FATAL ERROR. PROGRAM ABORTING. Must enter 1 cmd args, something like this:\n" +
            "$ java Knapsack input1.txt\n" + 
            "                inputFile");

        BufferedReader infile = new BufferedReader(new FileReader(args[0]));
        int [] set = new int[16];
        String setLine = infile.readLine();
        
        int cur = 0;
        for (String element : setLine.split(" ")){
            set[cur] = Integer.parseInt(element);
            cur++;
        }

        int target = Integer.parseInt(infile.readLine());
        infile.close();

        System.out.println(setLine);
        System.out.println(target);
		for (int i = 1; i < -1 >>> 16; i++)
		{
            int sum = 0;
            String setString = "";
			for (int j = 0; j <= 15  ; j++ )
			{
				/* shift i'th bit to the end position then AND it with 1 */
				if ( (i >>> j) % 2 == 1 ){ /* true iff i'th bit is a 1 */
                    sum += set[j];
                    setString += (set[j] + " ");
                }
            }
            if (sum == target){
                System.out.println(setString);
            }
        }
    } // END MAIN
    // to kill the program
    static void die( String errMsg )
	{	System.out.println( errMsg );
		System.exit( 0 );
    }
}