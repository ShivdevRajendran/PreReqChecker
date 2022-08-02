package prereqchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
	// WRITE YOUR CODE HERE

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
        //System.out.println(preReq);

        map.get(course).add(preReq);
        //map.put(  course,  map.get(course) );

    }
/*
    for (String x: map.keySet()) {
        String tempString = x;
        //StdOut.println(x + " "); // + map.get(x));
        for (int i = 0; i < map.get(x).size(); i++) {
            tempString = tempString + " " + map.get(x).get(i);
        }
        StdOut.println(tempString);
    }
*/
///NEW STUFF
    StdIn.setFile(args[1]);
    StdOut.setFile(args[2]);

    String course1 = StdIn.readLine();
    String course2 = StdIn.readLine();


    HashSet<String> allPreReqs = new HashSet<String>();

    HashMap<String, Boolean> map2 = new HashMap<String, Boolean>();
    for (String x: map.keySet()) {
        map2.put(  x, false);
    }
    DFS(course2, allPreReqs, map, map2);

    if (allPreReqs.contains(course1)) {
        StdOut.println("NO");
    }
    else {
        StdOut.println("YES");
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
/*
    public static HashSet<String> getAllPreReqs(HashMap<String, ArrayList<String>> hash, String courseID) {
        HashSet<String> set = new HashSet<>();
        HashMap <String, Boolean> map = new HashMap<String, Boolean>();
        for (String x: hash.keySet()) {
            map.put(x, false);
    }
        //for (int i = 0; i< hash.size() ; i++ ) {
            DFS(courseID   , map, set, hash);
            //set.add(   hash.get(courseID).get(i)   );
        //}
        return set;
    }

    public static void DhFS(String courseID, HashMap <String, Boolean> map, HashSet<String> set, HashMap<String, ArrayList<String>> hash) {//HashSet<String> set) { 
        // = new HashMap<String, Boolean>();
        /*
        for (String x: map) {
                map.put(x, false);
        }
        
        if (map.get(courseID) == true && map != null) return;
        else {
            map.put(courseID, false);
            set.add(courseID);
            DFS(courseID, map, set, hash);
        }
            /*
                if ( map.get(x).equals(false) ) {
                    set.add(x);
                    DFS(x, map, set);
                }
            
        
        map.put(courseID, true);
        return;
    }
*/
}












/*
    public HashMap<String, Boolean> create(HashMap< String, ArrayList<String> > map) {

        HashMap<String, Boolean> boolMap = new HashMap<String, Boolean>();

        for (int i = 0; i < map.size(); i++) {
            ArrayList<Boolean> emptyList = new ArrayList<Boolean>();
            boolMap.put(  map(i), emptyList  );
        }


        for (String key : map.keySet() ) {
            for (int i = 0; i < map.get(key).size(); i++) {
                boolMap.get(key).get(i);
                Boolean tf = false;
            }
        }
        return map;
    }
    */


/*
    String isValid(String courseID, String possiblePreReq) {
        HashSet set = new HashSet<>();
        DFS(courseID, set);

        if ( set.contains(possiblePreReq) == true ) {
            return "YES";
        }
        else return "NO";
    }


    public void DFS(String courseID, HashSet set) {
        HashMap map = new HashMap<String, Boolean>();
        for (String x: map.keySet()) {
            String tempString = x;
            //StdOut.println(x + " "); // + map.get(x));
            for (int i = 0; i < map.get(x).size(); i++) {
                tempString = tempString + " " + map.get(x).get(i)  ;
            }
            StdOut.println(tempString);
        }

        Iterator mapIterator = map.entrySet().iterator();
    }
*/

 /*
    public class aug {
        private HashMap< String, ArrayList<String> > hashONE = new HashMap<>();
        private HashMap hashTWO = new HashMap<>();
    }
    */

