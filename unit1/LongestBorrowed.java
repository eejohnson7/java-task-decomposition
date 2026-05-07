/*
You are provided with log data from a library's digital system, stored in string format. The log represents books' borrowing activities, including the book ID and the time a book is borrowed and returned. The structure of a log entry is as follows: <book_id> borrow <time>, <book_id> return <time>.

The time is given in the HH:MM 24-hour format, and the book ID is a positive integer between 1 and 500. The logs are separated by a comma, followed by a space (", ").

Your task is to create a Java function named solution(). This function will take as input a string of logs and output a list of strings representing the books with the longest borrowed duration. Each string contains the book ID and the book's borrowed duration, concatenated by a space. By 'borrowed duration,' we mean the period from when the book is borrowed until it is returned. If a book has been borrowed and returned multiple times, the borrowed duration is the total cumulative sum of those durations. If multiple books share the same longest borrowed duration, the function should return all such books in ascending order of their IDs.

For example, if we have a log string as follows: "1 borrow 09:00, 2 borrow 10:00, 1 return 12:00, 3 borrow 13:00, 2 return 15:00, 3 return 16:00",
the function should return the list ["2 05:00"].

Note: You can safely assume that all borrowing actions for a given book will have a corresponding return action in the log, and vice versa. Also, the logs are sorted by the time of the action.
 */

package unit1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestBorrowed {
        public List<String> solution(String logs) throws Exception {
        // TODO: your code goes here
        HashMap<String, String> borrowedTimes = new HashMap<>();
        HashMap<String, Integer> totalBorrowedTimes = new HashMap<>();
        
        List<String> log = Arrays.asList(logs.split(", "));
        for (String entry : log) {
            String[] parts = entry.split(" ");
            String bookId = parts[0];
            String action = parts[1];
            String time = parts[2];
            
            if (action.equals("borrow")) {
                borrowedTimes.put(bookId, time);
            } else if (action.equals("return")) {
                totalBorrowedTimes.put(bookId, totalBorrowedTimes.getOrDefault(bookId, 0) + getBorrowedTime(borrowedTimes, bookId, time));
                borrowedTimes.remove(bookId);
            } else {
                throw new Exception();
            } 
        }
        
        int maxBorrowedTime = Collections.max(totalBorrowedTimes.values());
        int resultHours = maxBorrowedTime / 60;
        int resultMins = maxBorrowedTime % 60;
        
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : totalBorrowedTimes.entrySet()) {
            if (entry.getValue() == maxBorrowedTime) {
                result.add(entry.getKey() + " " + String.format("%02d", resultHours) + ":" + String.format("%02d", resultMins));
            }
        }

        return result;
    }
    
    private int getBorrowedTime(HashMap<String, String> borrowedTimes, 
            String bookId, String returnTime) {
        String initialTime = borrowedTimes.get(bookId);
        int initialHour = Integer.parseInt(initialTime.substring(0, 2));
        int initialMin = Integer.parseInt(initialTime.substring(3, 5));
        int initialMins =  initialHour * 60 + initialMin;
        
        int returnHour = Integer.parseInt(returnTime.substring(0, 2));
        int returnMin = Integer.parseInt(returnTime.substring(3, 5));
        int returnMins = returnHour * 60 + returnMin;
        
        return returnMins - initialMins;
    }
}
