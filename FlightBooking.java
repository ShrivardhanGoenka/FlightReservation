import java.util.Scanner;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
class FlightBooking
{
    static String dep;
    static String arr;
    static int pas;
    static String date;
    static BufferedReader brline = new BufferedReader(new InputStreamReader(System.in));
    public static void mainpage()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\fThe list of the cities and their IATA code connected by our network: \n"+
            "Delhi - DEL\n"+
            "Mumbai - BOM\n"+
            "Kolkata - CCU\n"+
            "Chennai - MAA\n"+
            "Cochin - COK\n"+
            "Goa - GOI\n"+
            "Bangalore - BLR\n"+
            "Hyderabad - HYD\n"+
            "Amritsar - ATQ\n"+
            "Trivandrum - TRV");
        System.out.println("\nPlease enter the Departure Airport and Arrival Airport's IATA Code: ");
        dep = enterdeparr("Departure Airport");
        dep = dep.toUpperCase();
        arr = enterdeparr("Arrival Airport");
        arr = arr.toUpperCase();
        if(dep.equalsIgnoreCase(arr))
        {
            for(;;)
            {
                System.out.println("Departure and Arrival Airport cannot be the same");
                arr= enterdeparr("Arrival Airport");
                if(arr.equalsIgnoreCase(dep)==false)
                break;
            }
        }
        System.out.println();
        pas=passengers();
        System.out.println();
        System.out.println("Please enter the date(Please do not enter a date after 3 months)");
        date = enterdate();
        
        System.out.println("\nPlease choose one of the following: ");
        System.out.println("1. To continue with the above details.\n"+
                           "2. To re-enter the data\n"+
                           "3. To go back to the Home Page.");
        checkcontinue();
    }

    private static boolean checkIATAcode(String str)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("airportscode.txt"));
            String text;
            while((text=br.readLine())!=null)
            {
                if(str.equalsIgnoreCase(text))
                    return true;
            }
            return false;
        }
        catch(IOException e)
        {

        }
        return false;
    }

    private static String enterdeparr(String str)
    {
        Scanner sc = new Scanner(System.in);        

        System.out.print(str+": ");
        String dep = sc.next();
        if(checkIATAcode(dep))
            return dep;
        else
        {
            System.out.println("Wrong code. Please try again.");
            return enterdeparr(str);
        }
    }
    static int d,m,y;
     static String enterdate()
    {
        Scanner sc = new Scanner(System.in);
        String str;
        try
        {
            System.out.print("Please enter the day: ");
            d = Integer.parseInt(brline.readLine());
            System.out.print("Please enter the month: ");
            m = Integer.parseInt(brline.readLine());
            System.out.print("Please enter the year: ");
            y = Integer.parseInt(brline.readLine());
        }
        catch(Exception e)
        {
            System.out.println("\nPlease enter integers only.");
            enterdate();
        }
        if(m>12 ||d<1|| y<2020||y>2022||((m==1||m==3||m==5||m==7||m==8||m==10||m==12)&&d>31)||((m==2)&&d>28)||((m==4||m==6||m==9||m==11)&&d>30))
        {
            System.out.println("Wrong date entered. Please try again.\n");
            return enterdate();
        }
        else if(checkdate(d,m,y)==false)
        {
            System.out.println("Please enter a date from tomorrow to 3 months from now. Please try again.\n");
            return enterdate();
        }
        str = d+"-"+m+"-"+y;
        return str;
    }
    private static boolean checkdate(int d,int m,int y)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        String date = dtf.format(now);
        int dc = Integer.parseInt(date.substring(0,2));
        int mc = Integer.parseInt(date.substring(3,5));
        int yc = Integer.parseInt(date.substring(6));
        int ym=yc;
        int mm;
        mm=mc+3;
        if(mm>=13)
        {
            mm-=12;
            ym++;
        }
        if(y>ym||y<yc||(y==yc&&m==mc&&d<=dc)||(y==ym&&m==mm&&d>dc)||(y==yc&&m<mc)||(y==ym&&m>mm))
        return false;
        return true;
    }
    static int c=0;
    private static void checkcontinue()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Your choice: ");
        try
        {
            c = Integer.parseInt(brline.readLine());
        }
        catch(Exception e)
        {
            System.out.println("\nPlease enter an integer.");
            checkcontinue();
        }
        switch(c)
        {
            case 1:
            ResultPage.enter(dep,arr,pas,date);
            break;
            case 2:
            mainpage();
            break;
            case 3:
            MainPage.mainpage();
            break;
            default:
            System.out.println("Wrong choice entered. Please try again.");
            checkcontinue();
        }
    }
    static int p=0;
    private static int passengers()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the number of passengers(not more than 10): ");
        try
        {
            p = Integer.parseInt(brline.readLine());
        }
        catch(Exception e)
        {
            System.out.println("\nPlease enter an integer only.");
            passengers();
        }
        if(p>0&&p<=10)
        return p;
        System.out.println("The number of passengers is incorrect. Please try again.");
        return passengers();        
    }
}