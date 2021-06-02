import java.util.Scanner;
import java.io.*;
class MainPage
{
    static BufferedReader brline = new BufferedReader(new InputStreamReader(System.in));
    
    public static void mainpage()
    {
        System.out.println("\f\t\t\t\tWelcome To Domestic AirServices");
        System.out.println("Please choose one of the following: ");
        System.out.println("1. To book a new flight");
        System.out.println("2. To see our network of flights");
        System.out.println("3. To check a previous booking");
        System.out.println("4. To read our 'About us' page");
        System.out.println("5. To quit");
        choicemainpage();
    }
    static int c;
    private static void choicemainpage()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your choice: ");
        try
        {
            c = Integer.parseInt(brline.readLine());
        }
        catch(Exception e)
        {
            System.out.println("Wrong choice. Please enter an integer.");
            choicemainpage();
        }
        switch(c)
        {
            case 1:
            FlightBooking.mainpage();
            break;
            case 2:
            OurNetwork.mainpage();
            break;
            case 3:
            System.out.print("\f");
            PreviousBooking.mainpage();
            break;
            case 4:
            Aboutus.mainpage();
            mainpage();
            break;
            case 5:
            quit();
            break;
            default:
            System.out.println("Wrong choice. Please enter again.");
            choicemainpage();
            break;
        }
    }

    private static void quit()
    {
        System.out.println("\f\t\t\t\t\t\t\t Thank You for using our Services!!");
        System.out.println("\t\t\t\t\t Use our official services to book tickets at unbelievable prices!!");
        System.out.println("\t\t\t\t\t\t\t We wish you a great day ahead!!");
        System.exit(0);
    }
}