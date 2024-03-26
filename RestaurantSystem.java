package linkedlistdemo1;

import java.time.DateTimeException;
import java.util.*;
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
public class RestaurantSystem
{
   Helper myHelper;
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args)
   {
       RestaurantSystem aRestaurant = new RestaurantSystem();
       aRestaurant.myHelper = new Helper();
       int menuItem;   
       System.out.println("Welcome to the 3804 Restaurant Reservation System");
       do
       {
           menuItem = aRestaurant.displayMenu();
           switch (menuItem)
           {
               //cases 1 - 5 will cause an update to a linkedList in the Helper class
               case 1:
                   aRestaurant.addReservation();
                   break;
               case 2:
                   aRestaurant.changeReservation();
                   break;
               case 3:
                   aRestaurant.cancelReservation();
                   break;
               case 4:
                   aRestaurant.listAllReservations();
                   break;
               case 5:
                   aRestaurant.seatAReservation();
                   break;
               //cases 6 - 9 will cause an update to a quueue in the Helper class
               case 6:
                   aRestaurant.addAWalkIn();
                   break;
               case 7:
                   aRestaurant.listAllWalkIns();
                   break;
               case 8:
                   aRestaurant.seatAWalkIn();
                   break;
               case 9:
                   System.out.println("Bye!");
                   break;
               default:
                   System.out.println("Invalid menu option. Only 1 - 7 are valid.");

            }
        } while (menuItem !=9);

   }
   /**
    * 
    * @return - Integer representing users menu selection.
    */
   public int displayMenu()
   {
       int menuSelection = 0;
       try {
            System.out.println("1.  Make a Reservation");
            System.out.println("2.  Change a Reservation");
            System.out.println("3.  Cancel a Reservation");
            System.out.println("4.  List all of the Reservations");
            System.out.println("5.  Seat a Reservation");
            System.out.println("6.  Add a Walk-In");
            System.out.println("7.  List all of the Walk-Ins");
            System.out.println("8.  Seat a Walk-In");
            System.out.println("9.  Exit");
       
            System.out.println("What is your selection?");
            menuSelection = keyboard.nextInt();
       }
       catch (InputMismatchException e) {
           System.out.println("Invalid menu option. Numerical values only.");
       }
       keyboard.nextLine();
       return menuSelection;
   }
   /**
    * Adds reservation to LinkedList.
    */
    public void addReservation()
    {
        Reservation myReservation = createReservation();
        LocalDateTime t1 = null;
        int year, month, day, hour, minutes;
        boolean error;
        
        do {
            try {
                System.out.println("What year is your reservation for? (Numerical values only)");
                year = keyboard.nextInt();
                System.out.println("What month is your reservation for? (Numerical values only)");
                month = keyboard.nextInt();
                System.out.println("What day is your reservation for? (Numerical values only)");
                day = keyboard.nextInt();
                System.out.println("What hour is your reservation for? (Numerical values only)");
                hour = keyboard.nextInt();
                System.out.println("What minute is your reservation for? (Numerical values only)");
                minutes = keyboard.nextInt();
                keyboard.nextLine();
                try {
                    t1 = LocalDateTime.of(year, month, day, hour, minutes);
                    error = false;
                }
                catch (DateTimeException e) {
                    System.out.println("Invalid month, day, or time.");
                    System.out.println("Please enter date and time again.");
                    error = true;
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid entry. Numerical values only.");
                System.out.println("Please enter date and time again.");
                error = true;
                keyboard.nextLine();
            }
        } while(error == true);
        myReservation.setReservationDateTime(t1); //Updates myReservations date

        if (myHelper.addReservation(myReservation) == 0)
        {
            System.out.println("Your reservation was added. Thanks!");
        }
        else
        {
            System.out.println("Sorry, we are unable to add your reservation at this time. Please try another date.");
        }
    }
    /**
     * Changes reservation if found in LinkedList.
     */
    public void changeReservation()
    {
        String lastName, firstName;
        Reservation myReservation, myNewReservation;
        LocalDateTime resDate = null;
        int year, month, day, hour, minutes, sizeOfParty = 0;
        boolean error;
        
        System.out.println("What is the first name on your reservation?");
        firstName = keyboard.nextLine();
        System.out.println("What is the last name on your reservation?");
        lastName = keyboard.nextLine();
        
        do {
            try {
                System.out.println("What is the size of the party on your reservation? (Numerical values only)");
                sizeOfParty = keyboard.nextInt(); //Add try catch
                System.out.println("What year is your reservation for? (Numerical values only)");
                year = keyboard.nextInt();
                System.out.println("What month is your reservation for? (Numerical values only)");
                month = keyboard.nextInt();
                System.out.println("What day is your reservation for? (Numerical values only)");
                day = keyboard.nextInt();
                System.out.println("What hour is your reservation for? (Numerical values only)");
                hour = keyboard.nextInt();
                System.out.println("What minute is your reservation for? (Numerical values only)");
                minutes = keyboard.nextInt();
                keyboard.nextLine();
                try {
                    resDate = LocalDateTime.of(year, month, day, hour, minutes);
                    error = false;
                }
                catch (DateTimeException e) {
                    System.out.println("Invalid month, day, or time.");
                    System.out.println("Please enter size of party, date, and time again.");
                    error = true;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid entry. Numerical values only.");
                System.out.println("Please enter size of party, date, and time again.");
                error = true;
                keyboard.nextLine();
            }
        } while(error == true);
            myReservation = new Reservation(firstName, lastName, resDate, sizeOfParty);
        
        do {
            System.out.println("Please enter the new information for your reservation.");
            myNewReservation = createReservation();
            System.out.println("What year is your reservation for? (Numerical values only)");
            year = keyboard.nextInt();
            System.out.println("What month is your reservation for? (Numerical values only)");
            month = keyboard.nextInt();
            System.out.println("What day is your reservation for? (Numerical values only)");
            day = keyboard.nextInt();
            System.out.println("What hour is your reservation for? (Numerical values only)");
            hour = keyboard.nextInt();
            System.out.println("What minute is your reservation for? (Numerical values only)");
            minutes = keyboard.nextInt();
            keyboard.nextLine();
            try {
                resDate = LocalDateTime.of(year, month, day, hour, minutes);
                error = false;
            }
            catch (DateTimeException e) {
                System.out.println("Invalid month, day, or time.");
                System.out.println("Please enter size of party, date, and time again.");
                error = true;
                }
        } while(error == true);
        myNewReservation.setReservationDateTime(resDate);
        
        if (myHelper.changeReservation(myReservation, myNewReservation) == 0)
        {
            System.out.println("Your reservation was changed. Thanks!");
        }
        else
        {
            System.out.println("Sorry, we are unable to change your reservation at this time. Please try again.");
        }
    }
    /**
     * Cancels reservation if found in LinkedList.
     */
    public void cancelReservation()
    {
       String lastName, firstName;
       Reservation myReservation;
       LocalDateTime resDate = null;
       int year, month, day, hour, minutes, sizeOfParty = 0;
       boolean error;
        
       System.out.println("What is the first name on your reservation? ");
       firstName = keyboard.nextLine();
       System.out.println("What is the last name on your reservation? ");
       lastName = keyboard.nextLine();
        
       do {
           try {
               System.out.println("What is the size of the party on your reservation? (Numerical values only)");
               sizeOfParty = keyboard.nextInt(); //Add try catch
               System.out.println("What year is your reservation for? (Numerical values only)");
               year = keyboard.nextInt();
               System.out.println("What month is your reservation for? (Numerical values only)");
               month = keyboard.nextInt();
               System.out.println("What day is your reservation for? (Numerical values only)");
               day = keyboard.nextInt();
               System.out.println("What hour is your reservation for? (Numerical values only)");
               hour = keyboard.nextInt();
               System.out.println("What minute is your reservation for? (Numerical values only)");
               minutes = keyboard.nextInt();
               keyboard.nextLine();
               try {
                   resDate = LocalDateTime.of(year, month, day, hour, minutes);
                   error = false;
               }
               catch (DateTimeException e) {
                   System.out.println("Invalid month, day, or time.");
                   System.out.println("Please enter size of party, date, and time again.");
                   error = true;
               }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid entry. Numerical values only.");
                System.out.println("Please enter size of party, date and time again.");
                error = true;
                keyboard.nextLine();
            }
        } while(error == true);
            myReservation = new Reservation(firstName, lastName, resDate, sizeOfParty);
        
        if (myHelper.deleteReservation(myReservation) == 0)
        {
            System.out.println("Your reservation was cancelled. Thanks!");
        }
        else
        {
            System.out.println("Sorry, we are unable to cancel your reservation at this time. Please try again.");
        }
    }
    /**
     * Displays all reservations.
     */
    public void listAllReservations()
    {   
        System.out.println(myHelper.listAllReserv());
    }
    /**
     * Seats reservation if found in LinkedList.
     */   
    public void seatAReservation()
    {
       String lastName, firstName;
       Reservation myReservation;
       LocalDateTime resDate = null;
       int year, month, day, hour, minutes, sizeOfParty = 0;
       boolean error;
        
       System.out.println("What is the first name on your reservation? ");
       firstName = keyboard.nextLine();
       System.out.println("What is the last name on your reservation? ");
       lastName = keyboard.nextLine();
        
       do {
           try {
               System.out.println("What is the size of the party on your reservation? (Numerical values only)");
               sizeOfParty = keyboard.nextInt(); //Add try catch
               System.out.println("What year is your reservation for? (Numerical values only)");
               year = keyboard.nextInt();
               System.out.println("What month is your reservation for? (Numerical values only)");
               month = keyboard.nextInt();
               System.out.println("What day is your reservation for? (Numerical values only)");
               day = keyboard.nextInt();
               System.out.println("What hour is your reservation for? (Numerical values only)");
               hour = keyboard.nextInt();
               System.out.println("What minute is your reservation for? (Numerical values only)");
               minutes = keyboard.nextInt();
               keyboard.nextLine();
               try {
                   resDate = LocalDateTime.of(year, month, day, hour, minutes);
                   error = false;
               }
               catch (DateTimeException e) {
                   System.out.println("Invalid month, day, or time.");
                   System.out.println("Please enter size of party, date, and time again.");
                   error = true;
               }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid entry. Numerical values only.");
                System.out.println("Please enter size of party, date, and time again.");
                error = true;
                keyboard.nextLine();
            }
        } while(error == true);
            myReservation = new Reservation(firstName, lastName, resDate, sizeOfParty);
        
        if (myHelper.deleteReservation(myReservation) == 0)
        {
            System.out.println("Your reservation was found and you will be seated. Thanks!");
        }
        else
        {
            System.out.println("Sorry, we are unable to find your reservation and you cannot be seated. Please try again.");
        }
    }
    /**
     * 
     * @return - Reservation with name, last name, and total in party. 
     */
    public Reservation createReservation() 
    {
        String lastName, firstName;
        int sizeOfParty = 0;
        boolean error;
        
        System.out.println("What is your first name, please? ");
        firstName = keyboard.nextLine();
        System.out.println("What is your last name? ");
        lastName = keyboard.nextLine();
        do {
            try {
                System.out.println("How many are in your party? (Numerical values only)");
                sizeOfParty = keyboard.nextInt();
                error = false;
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid entry. Amount in party must be numerical.");
                error = true;
                keyboard.nextLine();
            }
        } while(error == true);
        
        LocalDateTime resDate = LocalDateTime.now();
        Reservation myReservation = new Reservation(firstName, lastName, resDate, sizeOfParty);
        return myReservation;
    }
    /**
     * Adds walk-in to walk in queue.
     */
    public void addAWalkIn()
    {
        Reservation myReservation = createReservation();
        int success = myHelper.addAWalkInQueue(myReservation);
        if (success == 0) {
            System.out.println("You have been added to our wait-list. Please wait your turn.");
        }
    }
    /**
     * Displays all walk-ins in walk-in queue.
     */
    public void listAllWalkIns()
    {
        System.out.println(myHelper.listAllWalkIns());
    }
    /**
     * Seats the first walk-in found in the walk-in queue.
     */
    public void seatAWalkIn()
    {
        Reservation aWalkIn;
        aWalkIn = myHelper.deleteAWalkIn();
        
        if (aWalkIn != null)
        {
            System.out.println("The following walk-in reservation has been found and will be seated. Thanks! " + aWalkIn);
        }
        else
        {
            System.out.println("Sorry, we are unable to find any walk-in reservations. Please try again.");
        }   
    }

}