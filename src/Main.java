
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Main {
    /** THE RULES
     * match on first and second column
     * also match on sixth column if the third column is the same as the other third columns within first/second column groups
     * concatenate values within fourth with fourth and fifth with fifth column values.
     */
    public static Random r = new Random();

    public static void main(String[] args) throws IOException {

        List<String> firstCol = List.of("a", "b", "c");
        List<String> secondCol = List.of("a", "b", "c");
        List<String> thirdCol = List.of("1", "2", "3", "4");
        List<String> fourthCol = List.of("a", "b", "c");
        List<String> fifthCol = List.of("a", "b", "c");
        List<String> sixthCol = List.of("1", "2", "3", "4");

        int numberOfRow = Math.abs((Math.abs(r.nextInt()) % 10) + 10);
        FileWriter f = new FileWriter("test.csv");

        for (int i = 0; i < numberOfRow; i++) {
            String sb = firstCol.get(Math.abs(r.nextInt()) % firstCol.size()) + "," + secondCol.get(Math.abs(r.nextInt()) % secondCol.size()) + "," + thirdCol.get(Math.abs(r.nextInt()) % thirdCol.size()) + "," + fourthCol.get(Math.abs(r.nextInt()) % fourthCol.size()) + "," + fifthCol.get(Math.abs(r.nextInt()) % fifthCol.size()) + "," + sixthCol.get(Math.abs(r.nextInt()) % sixthCol.size()) + "\r\n";

            f.write(sb);
            System.out.print(sb);

        }
        f.close();

        // beginning of solution

        System.out.println("___________");

        FileWriter solution = new FileWriter("Solution.csv");  //Opens a new file to be written to, I didn't want to edit the original

        solution.write("Input:\n");    //Writes the top line

        FileReader r = new FileReader("test.csv");  //Change to "test2.csv" to run example in the email

        // nDeclares a new ArrayList and scanner, the scanner stops at every ","
        ArrayList<String> dataInput = new ArrayList<String>();
        Scanner scanny = new Scanner(r);
        scanny.useDelimiter(",");

        // Reads through the whole file, writing the input to our new file, and adding the lines to an ArrayList
        while (scanny.hasNextLine()) {
            String arrayLTemp = scanny.nextLine();
            solution.write(arrayLTemp + "\n");
            dataInput.add(arrayLTemp);
        }

        scanny.close();

        // Seperates the input from the output in the file
        solution.write("\nOutput:\n");

        // Time to run the algorithm to check and concatenate
        for (int i = 0; i < dataInput.size(); i++) {
            String fileOutTemp = dataInput.get(i); //

            for (int j = i+1; j < dataInput.size(); j++) { // nStarts j after i so it doesn't check itself

                if ((fileOutTemp.charAt(0) == dataInput.get(j).charAt(0)) &&    //Compares first columns
                        (fileOutTemp.charAt(2) == dataInput.get(j).charAt(2)) &&     //Compares second columns
                        (fileOutTemp.charAt(4) == dataInput.get(j).charAt(4)) &&     //Compares third columns
                        fileOutTemp.charAt(10) == dataInput.get(j).charAt(10)        //Compares sixth columns
                ) {
                    fileOutTemp = fileOutTemp.substring(0, 7) + dataInput.get(j).charAt(6) + fileOutTemp.substring(7, 9) + dataInput.get(j).charAt(8) + fileOutTemp.substring(9); //concatenates the string
                    dataInput.remove(j); //Removes the object that got concatenated
                }
            }
            // Writes and prints out the solution
            solution.write(fileOutTemp + "\n");
            System.out.println(fileOutTemp);
        }

        // Closes the file
        solution.close();
    }
}
