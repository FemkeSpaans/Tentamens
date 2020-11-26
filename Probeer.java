package Tentamen;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Probeer {
    public static void main(String[] args) throws FileNotFoundException {
        String file = ("Upregulated proteins mpk6 0-24 h after DEX treatment.txt");
        Scanner scan = new Scanner(new FileReader(file));
        // initialises the scanner to read the file file

        String[][] entries = new String[1][1];
        // creates a 2d array with 1 row and 1 column.

        int i = 0;
        while(scan.hasNextLine()){
            entries[i] = scan.nextLine().split("\t");
            i++;
        }
        //loops through the file and splits on a tab

        for (int row = 0; row < entries.length; row++) {
            for (int col = 0; col < entries[0].length; col++) {
                if(entries[row][col] != null){
                    //System.out.print(entries[row][col] + " " );
                }
            }
            if(entries[row][0] != null){
                System.out.print("\n");
            }
        }
        //prints the contents of the array that are not "null"
    }
    }
//            public static void main(String[] args) throws IOException {
//                BufferedReader reader1 = new BufferedReader(new FileReader("C:\\Users\\FSpaa\\IdeaProjects\\PractiseRoundOne\\src\\Tentamen\\Upregulated proteins mpk3 0-24 h after DEX treatment.txt"));
//                BufferedReader reader2 = new BufferedReader(new FileReader("C:\\Users\\FSpaa\\IdeaProjects\\PractiseRoundOne\\src\\Tentamen\\Upregulated proteins mpk6 0-24 h after DEX treatment.txt"));
//                String line1 = reader1.readLine();
//                String line2 = reader2.readLine();
//                boolean areEqual = true;
//                int lineNum = 1;
//                int counter = 0;
//                while (line1 != null || line2 != null)
//                {
//                    if(! line1.equalsIgnoreCase(line2)) {
//                        break;
//                    }
//                    if(line1.equalsIgnoreCase(line2))
//                    {
//                        counter++;
//                    } else {
//                    }
//                    line1 = reader1.readLine();
//                    line2 = reader2.readLine();
//                    lineNum++;
//                }
//                System.out.println(counter);
//                reader1.close();
//                reader2.close();
//            }



