import java.sql.*;
import java.util.Scanner;
public class JDBC_HotalRejervationSystem {
    static JDBC_hotalregervationDatabaseOparetion reservation;
    public static void main(String[] args) {
        try {
            reservation=new JDBC_hotalregervationDatabaseOparetion();
        }
        catch (ClassNotFoundException e){
            System.out.println(e);
        }
        int number;
        do {
            number = takeInputForOparetion();
            performOparetion(number);
        }while (number!=0);
    }

    static int takeInputForOparetion(){
        Scanner sc =new Scanner(System.in);
        int number;
        System.out.println("1. New Reservation");
        System.out.println("2. Check Reservation");
        System.out.println("3. Check Room No.");
        System.out.println("4. Update reservation");
        System.out.println("5. Delete Reservation");
        System.out.println("0. Exit");
        System.out.println("Enter any number between 0-5");

        System.out.print("Enter Number : ");
        number =sc.nextInt();
        return number;
    }

    static void performOparetion(int number){
        switch (number){
            case 1:{
                reservation.newReservation();
                break;
            }
            case 2:{
                reservation.checkReservation();
                break;
            }
            case 3:{
                reservation.checkRoomNo();
                break;
            }
            case 4:{
                reservation.updateReservation();
                break;
            }
            case 5:{
                reservation.deletresarvation();
                break;
            }
            case 0:{
                reservation.exit();
                break;
            }
            default:{
                System.out.println("Enter valid Number!!!");
                break;
            }
        }
    }
}
