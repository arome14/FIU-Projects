package linkedlistdemo1;

import java.time.LocalDateTime;
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
public class Reservation implements Comparable<Reservation> {
    private String firstName;
    private String lastName;
    private LocalDateTime reservationDateTime; //Date(int year, int month, int date, int hrs, int min, int sec)
    private int sizeOfParty;
    
    public Reservation()
    {
        firstName = "";
        lastName = "";
        reservationDateTime = LocalDateTime.now();
        sizeOfParty = 0;
    }
    
    public Reservation(String aFirstName, String aLastName, LocalDateTime aReservationDateTime, int aPartySize)
    {
        firstName = aFirstName;
        lastName = aLastName;
        reservationDateTime = aReservationDateTime;
        sizeOfParty = aPartySize;
    }    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public int getSizeOfParty() {
        return sizeOfParty;
    }

    public void setSizeOfParty(int sizeOfParty) {
        this.sizeOfParty = sizeOfParty;
    }
    /**
     * 
     * @return - String representation of the reservation information. 
     */
    @Override
    public String toString()
    {
        return firstName + " " + lastName + "'s reservation: " + 
                reservationDateTime + ". Party of " + sizeOfParty + ".";
    }
    /**
     * 
     * @param anotherRes
     * @return - Integer 0, 1, or -1 depending on the result of compareTo(). 
     */
    @Override
    public int compareTo(Reservation anotherRes)
    {
        if (this.reservationDateTime.compareTo(anotherRes.getReservationDateTime()) > 0)
        {
            return 1;            
        }
        else if (this.reservationDateTime.compareTo(anotherRes.getReservationDateTime()) < 0)
        {
            return -1;
        }
        else if (this.lastName.compareTo(anotherRes.getLastName()) == 0 &&
                    this.firstName.compareTo(anotherRes.getFirstName()) == 0 &&
                      this.sizeOfParty == anotherRes.getSizeOfParty())
        {
            return 0;
        }
        else
        {
            return -1;
        }      
    }
}
