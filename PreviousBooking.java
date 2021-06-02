import java.util.Scanner;
import java.io.*;
class PreviousBooking
{
    static int n=0;
    public static void mainpage()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Please enter the Passenger Code: ");
        try
        {
            BufferedReader brline = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(brline.readLine());
            try
            {
                BufferedReader br = new BufferedReader(new FileReader("Passengers\\Pas"+n+".txt"));
                System.out.println("\fYour flight was succesfully found.");
                System.out.println("The details are: \n");
                String text;
                while((text=br.readLine())!=null)
                {
                    System.out.println(text);
                }
                br.close();
                System.out.println("\n\nPlease press enter and any character to return back to the home page.");
                sc.next();
                MainPage.mainpage();
            }
            catch(Exception e)
            {
                System.out.println("\nThe code is not valid. Please try again.");
                mainpage();
            }
        }
        catch(Exception e)
        {
            System.out.println("Please enter an integer. Please try again.");
            mainpage();
        }
    }
}