package linkedlistdemo1;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
/**
 *
 * @author 6174638
 * Title: RestaurantLinkedListQueue
 * Semester: COP3804 Summer 2022
 * Lecturer: Prof. Charters
 * Description: This program allows the user to make restaurant reservations by
 * utilizing a LinkedList and allows the user to create walk-in reservations by
 * using a Queue. This program can also edit a reservation, seat reservations and walk-ins,
 * cancel reservations and walk-ins, and list all reservations and walk-ins.
 */
public class Helper {
    
    LinkedList<Reservation> reservationList = new LinkedList<>();
    Queue<Reservation> walkInQueue = new LinkedList<>();
  /**
   * 
   * @param aReservation
   * @return - Integer representing a successful or unsuccessful search in LinkedList.
   */  
  public int searchReservation(Reservation aReservation)
  {
      int returnCode = 0;
      boolean found = false;
      Reservation nextReservation;
      ListIterator<Reservation> myIt = reservationList.listIterator();
      
      while (myIt.hasNext() && !found) {
          nextReservation = myIt.next();
          if (nextReservation.getReservationDateTime().equals(aReservation.getReservationDateTime()) && 
              nextReservation.getLastName().equals(aReservation.getLastName()) && 
              nextReservation.getFirstName().equals(aReservation.getFirstName()) && 
              nextReservation.getSizeOfParty() == aReservation.getSizeOfParty()) {
              found = true;
          }
      }
      if (found == false) {
          returnCode = -1;
      }
      return returnCode;
  }
  /**
   * 
   * @param aReservation
   * @return - Integer representing a successful or unsuccessful add in LinkedList. 
   */  
  public int addReservation(Reservation aReservation)
  {
      int returnCode=0;
      boolean found = false;
      Reservation nextReservation;
      ListIterator<Reservation> myIt = reservationList.listIterator();
      
      while (myIt.hasNext() && !found) {
          nextReservation = myIt.next();
          if (nextReservation.getReservationDateTime().compareTo(aReservation.getReservationDateTime()) > 0) {
              myIt.previous();
              myIt.add(aReservation);
              found = true;
          }
      }
      if (!found) {
          myIt.add(aReservation);
      }
      return returnCode;
  }
    /**
     * 
     * @param oldRes
     * @param newRes
     * @return - Integer representing a successful or unsuccessful change in LinkedList.
     */
    public int changeReservation(Reservation oldRes, Reservation newRes)
    {
        int returnCode=0;
        int deleteSuccess, addSuccess;
        
        if (searchReservation(oldRes) == -1) {
            returnCode = -1;
            return returnCode;
        }
        else if (searchReservation(oldRes) == 0) {
            deleteSuccess = deleteReservation(oldRes);
            if (deleteSuccess != 0) {
                returnCode = -1;
                return returnCode;
            }
        }
        addSuccess = addReservation(newRes);
        if (addSuccess != 0) {
            returnCode = -1;
            return returnCode;
        }
        return returnCode;
    }
    /**
     * 
     * @param aReservation
     * @return - Integer representing a successful or unsuccessful delete from LinkedList.
     */
    public int deleteReservation(Reservation aReservation) //Return not 0 if reservation not found
    {
        int returnCode=0;
        boolean found = false;
        Reservation nextReservation;
        ListIterator<Reservation> myIt = reservationList.listIterator();
        
        while (myIt.hasNext() && !found) {
            nextReservation = myIt.next();
            if (nextReservation.getReservationDateTime().equals(aReservation.getReservationDateTime()) && 
                nextReservation.getLastName().equals(aReservation.getLastName()) && 
                nextReservation.getFirstName().equals(aReservation.getFirstName()) && 
                nextReservation.getSizeOfParty() == aReservation.getSizeOfParty()) {
                
                myIt.previous();
                myIt.remove();
                found = true;
            }
        }
        if (found == false) {
            returnCode = -1;
        }     
        return returnCode;        
    }
    /**
     * 
     * @return - String representation of all reservations in LinkedList.
     */
    public String listAllReserv()
    {
        String output = "";
        Reservation nextReservation;
        ListIterator<Reservation> myIt = reservationList.listIterator();
        
        while (myIt.hasNext()) {
            nextReservation = myIt.next();
            output = output + nextReservation + "\n";
        }
        if(output.equals("")) {
            output = "Sorry, we are unable to find any reservations. Please try again.";
        }
        return output;
    }   
    /**
     * 
     * @param aReservation
     * @return - Integer representing a successful or unsuccessful add to queue. 
     */    
    public int addAWalkInQueue(Reservation aReservation)
    {
        int returnCode = 0;
        walkInQueue.add(aReservation);
        return returnCode;
    }
   /**
    * 
    * @return - String representation of all reservations in queue.
    */
    public String listAllWalkIns()
    {
        String output = "";
        Queue<Reservation> walkInQueueClone = new LinkedList<>();
        Reservation myWalkIn;
        
        while (!walkInQueue.isEmpty()) {
            myWalkIn = walkInQueue.remove(); 
            walkInQueueClone.add(myWalkIn);
        }
        while (!walkInQueueClone.isEmpty()) {
            myWalkIn = walkInQueueClone.remove();
            output = output + myWalkIn + "\n";
            walkInQueue.add(myWalkIn);
        }
        if(output.equals("")) {
            output = "Sorry, we are unable to find any walk-in reservations. Please try again.";
        }
        return output;
    }
    /**
     * 
     * @return - Null if queue is empty and the Reservation object if one is present.
     */
    public Reservation deleteAWalkIn()
    {
        Reservation aWalkIn;
        
        if (walkInQueue.peek() == null) {
            aWalkIn = null;
        }
        else {
           aWalkIn = walkInQueue.remove();
        }
        return aWalkIn;
    }

}
