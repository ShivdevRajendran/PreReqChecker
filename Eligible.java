package prereqchecker;

import java.util.*;

/**
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
//creates adjist
    StdIn.setFile(args[0]);
    HashMap< String, ArrayList<String> > map = new HashMap< String, ArrayList<String> >();
    String numA = StdIn.readLine();
    int numberOfInputsA = Integer.parseInt(numA);
    for (int i = 0; i < numberOfInputsA; i++) {
        String courseID = StdIn.readLine();
        ArrayList<String> emptyList = new ArrayList<String>();
        map.put(  courseID, emptyList  );
    }
    String numB = StdIn.readLine();
    int numberOfInputsB = Integer.parseInt(numB);  
    for (int i = 0; i < numberOfInputsB; i++) {
        String course = StdIn.readString();
        String preReq = StdIn.readString();
        map.get(course).add(preReq);
    }

/*STEP 2
* Step 2:
* EligibleInputFile name is passed through the command line as args[1]
* Read from EligibleInputFile with the format:
* 1. c (int): Number of course
* 2. c lines, each with 1 course ID
*/

    StdIn.setFile(args[1]);
    StdOut.setFile(args[2]);
    String numC = StdIn.readLine();
    int numberOfInputsC = Integer.parseInt(numC);
    HashSet<String> allTaken = new HashSet<String>();
    //HashSet<String> tempSet = new HashSet<String>();
    HashMap<String, Boolean> map2 = new HashMap<String, Boolean>();

    for (int i = 0; i < numberOfInputsC; i++) {
        String course = StdIn.readLine();
        allTaken.add(course);

        for (String x: map.keySet()) {
            map2.put(  x, false);
        }
        DFS(course, allTaken , map, map2);
    }

    for (String x: map.keySet()) {
        for (String i: map.get(x)) {
                if (!allTaken.contains(i) )  break;
                if (allTaken.contains(i) && map.get(x).indexOf(i) == map.get(x).size() -1 && !allTaken.contains(x)) StdOut.println(x);
        }
}


    


//STEP 3
    //StdOut.setFile(args[2]);




    }

    public static void DFS(String course, HashSet<String> allPreReqs, HashMap<String, ArrayList<String> > map, HashMap<String, Boolean> map2 ) {


        
        if (map.get(course).size() == 0) return;

        for (String x: map.get(course)) {
            if (map2.get(x) == false) {
                allPreReqs.add(x);

                for (String i: map.keySet()) {
                    map2.put(  i, false);
                }
                DFS(x, allPreReqs, map, map2);
            }
        }
        map2.replace(course, true);
    }
/*
    public static HashSet<String> getAllPreReqs(HashMap<String, ArrayList<String>> hash, String courseID) {
        HashSet<String> set = new HashSet<>();
        HashMap <String, Boolean> map = new HashMap<String, Boolean>();
        for (String x: hash.keySet()) {
            map.put(x, false);
    }
        for (int i = 0; i< hash.size() ; i++ ) {
            DFS(courseID   , map, set);
            //set.add(   hash.get(courseID).get(i)   );
        }
        return set;
    }
*/
}
