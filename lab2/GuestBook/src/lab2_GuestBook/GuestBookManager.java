package lab2_GuestBook;

import java.util.ArrayList;
import java.util.List;

public class GuestBookManager {

// List of GuestBookModel(Entries)
// interface (abstract concept)			vs. 	implementation (real implementation)
private List<GuestBookModel> guestBookEntries = new ArrayList<GuestBookModel>();
 
 public void clear() {
   guestBookEntries.clear(); //call is delegated
 }
 
 public void add(GuestBookModel model) {
    guestBookEntries.add(model); //call is delegated
 }
 
 public boolean isEmpty() {
    return guestBookEntries.isEmpty();
 }
 
 public List<GuestBookModel> getGuestBookEntries() {
    return guestBookEntries;
 }
 
 public int getSize() {
    return guestBookEntries.size();
 }
 
}