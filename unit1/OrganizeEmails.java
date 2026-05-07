/*
Imagine you have a large mailbox that receives emails from various sources and you need to organize these emails. Your task involves implementing a Java method named organizeInbox. This method will accept a string of emails as input and output a list of strings. Each string contains two elements: the sender's email address and the total count of emails received from this sender, separated by a space.

Each email is represented by various metadata separated by commas, such as "Sender Email Address, Subject, Timestamp". The total string comprises these entries, separated by semicolons. Emails originate from distinct senders and can occur at any timestamp in the "HH:MM" format within a 24-hour range.

Here is the format of the string: "Sender Email Address1, Subject1, 09:00; Sender Email Address2, Subject2, 10:00; Sender Email Address1, Subject3, 12:00"

The method should return: ["Sender Email Address1 2", "Sender Email Address2 1"].

For each input entry, the sender's email is a string containing up to 
20
20 characters. The timestamp follows the "HH:MM" format. The total number of email entries varies from 
1
1 to 
500
500, inclusive.

Your method must:

Extract the sender's email address.
Count the number of emails received from each sender.
Output a list of strings, where each string contains the sender's email address followed by the count of emails received from them, separated by a space.
Sort the list in descending order of the counts. If two senders have sent the same number of emails, list them in ascending order based on the senders' email addresses.
The sender's email address is always followed by a comma, a space, and the start of the subject line. The subject line is always followed by a comma, a space, and the timestamp. All emails are unique, meaning there will be no emails with the same subject and timestamp from the same sender.
 */

package unit1;

import java.util.*;

public class OrganizeEmails {
    public List<String> organizeInbox(String inboxString) {
        // TODO: Implement this method
        HashMap<String, Integer> senderCount = new HashMap<>();
        
        String[] emails = inboxString.split(";");
        for(String email : emails) {
            String[] parts = email.split(",");
            senderCount.put(parts[0].trim(), senderCount.getOrDefault(parts[0].trim(), 0) + 1);
        }
                
        List<String> result = new ArrayList<>();
        for (String sender : senderCount.keySet()) {
            result.add(sender + " " + senderCount.get(sender));
        }   
        
        result.sort((s1, s2) -> {
            String[] p1 = s1.split(" ");
            String[] p2 = s2.split(" ");
            
            int countCompare = Integer.compare(
                Integer.parseInt(p2[1]), Integer.parseInt(p1[1])
            );
            
            if (countCompare != 0) return countCompare;
            return p1[0].compareTo(p2[0]);
        });
        
        return result;
    }
}