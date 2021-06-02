import java.util.Scanner;
import java.io.*;
import java.util.Map;
class OurNetwork
{
    static int c=0;
    static BufferedReader brline = new BufferedReader(new InputStreamReader(System.in));
    public static void mainpage()
    {
        System.out.println("\f\t\t\t\t\t\tOur Network");
        System.out.println("Please choose one of the following:\n"+
            "1.To see the cities directly connected.\n"+
            "2.To see the entire daily schedule.\n"+
            "3.To go back to the main page.");
        choicemainpage();
    }

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
            System.out.println("Please enter an integer.");
            choicemainpage();
        }
        switch(c)
        {
            case 1:
            dirconnected();
            break;
            case 2:
            schedule();
            break;
            case 3:
            MainPage.mainpage();
            break;
            default:
            System.out.println("Wrong choice. Please enter again.");
            choicemainpage();
            break;
        }
    }

    private static void dirconnected()
    {
        try
        {
            Map <String,String> map = getmapofairport.main();
            BufferedReader br = new BufferedReader(new FileReader("DirectFlights.txt"));
            System.out.println("\nDeparture Airport \tArrival Airport \t No of flights per day");
            System.out.println("-----------------------------------------------------------------------");
            String text;
            while((text=br.readLine())!=null)
            {
                System.out.println("   "+map.get(text.substring(0,3))+ "\t\t   "+map.get(text.substring(4,7))+"\t\t\t"+text.charAt(8));
            }
            printstatement();
        }
        catch(IOException e)
        {

        }
    }
    private static void printstatement()
    {
        System.out.println("\n\nPlease choose one of the following: \n"+
                           "1. To go back to the main menu.\n"+
                           "2. To reset this page.");
        choicesecondpage();
    }
    private static void schedule()
    {
        try
        {
            Map <String,String> map = getmapofairport.main();
            BufferedReader br = new BufferedReader(new FileReader("flightlist1.txt"));
            System.out.println("\nFlight No.    Departure Airport \tArrival Airport \t Departure Time \t Arrival Time \t    Cost(In Rupees)");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            String text;
            while((text=br.readLine())!=null)
            {
                System.out.println("  DF"+text.substring(0,3)+"\t\t "+map.get(text.substring(4,7))+"\t\t    "+map.get(text.substring(8,11))+"\t\t     "+
                    text.substring(12,18)+"\t\t    "+text.substring(18,24)+"\t\t"+text.substring(24));
            }
            printstatement();
        }
        catch(IOException e)
        {
            schedule();
        }
    }
    private static void choicesecondpage()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your choice: ");
        try
        {
            c = Integer.parseInt(brline.readLine());
        }
        catch(Exception e)
        {
            System.out.println("Please enter an integer.");
            choicesecondpage();
        }
        switch(c)
        {
            case 1:
            System.out.print("\f");
            MainPage.mainpage();
            break;
            case 2:
            System.out.println("\f");
            mainpage();
            break;
            default:
            System.out.println("Wrong choice. Please enter again.");
            choicemainpage();
            break;
        }
    }
}