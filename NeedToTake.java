package prereqchecker;

import java.util.*;

/**
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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE

//adjlist
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


//new
    StdIn.setFile(args[1]);
    StdOut.setFile(args[2]);

    String targetCourse = StdIn.readLine();
    String numD = StdIn.readLine();
    int numberOfInputsD = Integer.parseInt(numD);
    HashSet<String> allTaken = new HashSet<String>();
    HashMap<String, Boolean> map2 = new HashMap<String, Boolean>();

    for (int i = 0; i < numberOfInputsD; i++) {
        String course = StdIn.readLine();
        allTaken.add(course);
        for (String x: map.keySet()) {
            map2.put(  x, false);
        }
        DFS(course, allTaken , map, map2);
    }





    HashMap<String, Boolean> map3 = new HashMap<String, Boolean>();

    HashSet<String> allNeed = new HashSet<String>();
    for (int i = 0; i < map.get(targetCourse).size(); i++) {
        String course = map.get(targetCourse).get(i);//StdIn.readLine();
        allNeed.add(course);
        for (String x: map.keySet()) {
            map3.put(  x, false);
        }
        DFS(course, allNeed , map, map3);
    }



    for (String x: allNeed) {
        if (!allTaken.contains(x)) StdOut.println(x);
    }




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
}
