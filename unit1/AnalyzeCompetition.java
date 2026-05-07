/*
There is a school hosting an online programming competition. Each problem is assigned a unique level of difficulty. Every time a student successfully solves a problem, their score is updated based on the problem's difficulty level. However, if a student makes an unsuccessful attempt, they incur a penalty. The competition logs every action of each student in a string.

Your task is to create a Java function named analyzeCompetition(). It will take a string of logs as input and output a list of arrays, representing the students' score, the number of successful attempts, and the total penalties. The arrays should be sorted by the decreasing order of scores of their respective students. It is guaranteed that there will be no students with the same positive score. Don't include students in the output who haven't solved any problem.

For example, if you have logs like this:
"1 solve 09:00 50, 2 solve 10:00 60, 1 fail 11:00, 3 solve 13:00 40, 2 fail 14:00, 3 solve 15:00 70",
your function should return: [[3, 110, 2, 0], [2, 60, 1, 1], [1, 50, 1, 1]].

All log entries are separated by a comma and a space. It is guaranteed that the log entries are sorted in chronological order.
 */

package unit1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnalyzeCompetition {
    public List<int[]> analyzeCompetition(String logs) {
        // TODO: implement the function
        HashMap<Integer, HashMap<String, Integer>> studentCatalog = new HashMap<>();
        
        String[] students = logs.split(", ");
        for (String student : students) {
            String[] parts = student.split(" ");
            int id = Integer.parseInt(parts[0]);
                        
            if (studentCatalog.containsKey(id)) {
                HashMap<String, Integer> details = studentCatalog.get(id);

                if (parts[1].equals("solve")) {
                    details.put("solves", details.get("solves") + 1);
                    details.put("score", details.get("score") + Integer.parseInt(parts[3]));
                } else {
                    details.put("fails", details.get("fails") + 1);
                }
                
                studentCatalog.put(id, details);
            } else {
                HashMap<String, Integer> details = new HashMap<>();

                if (parts[1].equals("solve")) {
                    details.put("solves", 1);
                    details.put("fails", 0);
                    details.put("score", Integer.parseInt(parts[3]));
                } else {
                    details.put("solves", 0);
                    details.put("fails", 1);
                    details.put("score", 0);
                }
                
                studentCatalog.put(id, details);
            }
        }
        
        List<int[]> result = new ArrayList<>();
        for (Integer id : studentCatalog.keySet()) {
            int[] student = new int[4];
            student[0] = id;
            student[1] = studentCatalog.get(id).get("score");
            student[2] = studentCatalog.get(id).get("solves");
            student[3] = studentCatalog.get(id).get("fails");
            result.add(student);
        }
        
        result.sort((arr1, arr2) -> {
            return Integer.compare(arr2[1], arr1[1]);
        });
        
        return result;
    }
}
