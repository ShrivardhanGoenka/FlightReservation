import java.util.Scanner;
import java.io.*;
class ResultPage
{
    static String dep;
    static String arr;
    static int pas;
    static String date;
    static Scanner sc= new Scanner(System.in);
    static BufferedReader brline = new BufferedReader(new InputStreamReader(System.in));
    public static void enter(String dep,String arr,int pas,String date)
    {
        ResultPage.dep = dep;
        ResultPage.arr = arr;
        ResultPage.pas = pas;
        ResultPage.date = date;
        flight[0]=0;
        flight[1]=0;
        page1();
    }
    static  int flight[] = new int[2];
    static int dir;
    private static void page1()
    {

        try
        {
            java.util.Map<String,String> map = getmapofairport.main();
            System.out.println("\fDeparture Airport: "+map.get(dep)+"\n"+
                "Arrival Airport: "+map.get(arr)+"\n"+
                "Date: "+date+"\n"+
                "Passengers: "+pas);
            BufferedReader br = new BufferedReader(new FileReader("flightlist1.txt"));
            int flag=0;
            String text;
            int i=1;
            int[] a = new int[3];
            while((text=br.readLine())!=null)
            {
                String text1 = text.substring(4,11);
                String text2 = dep.concat(" ".concat(arr));
                if(text1.equals((text2)))
                {
                    if(flag==0)
                    {
                        System.out.println("\nFlight No.    Departure Airport \t Arrival Airport \t Departure Time \t Arrival Time \t    Cost(In Rupees) \t Availability(No. of seats)");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        flag=1;
                    }
                    System.out.println("   DF"+text.substring(0,3)+"\t "+map.get(text.substring(4,7))+"\t\t    "+map.get(text.substring(8,11))+"\t\t     "+
                        text.substring(12,18)+"\t\t    "+text.substring(18,24)+"\t\t"+text.substring(24) +"\t\t\t"+checkseats(Integer.parseInt(text.substring(0,3)),date));
                    a[i-1] = Integer.parseInt(text.substring(0,3));
                    i++;
                }
            }
            if(flag==0)
            {
                System.out.println("\nThere are no direct flights between the 2 locations you have chosen.\n"+
                    "Please choose from one of the options below.");
                dir=1;
                flight = nodirect();

                System.out.println("\nYou have chosen flights DF"+ flight[0]+" and DF"+flight[1]+".");
                if(checkseats(flight[0],date)>=pas && checkseats(flight[1],date)>=pas)
                {
                    System.out.println("There are seats available.");
                }
                else
                {
                    System.out.println("Seats are no available.");
                    System.out.println("\nPlease choose one of the options:\n"+
                        "1. To make a choice again.\n"+
                        "2. To go back to the home page.");
                    choicenoseats();
                    return;
                }
            }
            if(i>1)
            {
                System.out.println("\nPlease enter the Flight Number from the above choices.\n");
                flight[0] = enterfn(a);
                dir=0;
                System.out.println("\nYou have chosen the flight DF"+flight[0]);
                if(checkseats(flight[0],date)>=pas)
                {
                    System.out.println("There are seats available.");
                }
                else
                {
                    System.out.println("Seats are no available.");
                    System.out.println("\nPlease choose one of the options:\n"+
                        "1. To make a choice again.\n"+
                        "2. To go back to the home page.");
                    choicenoseats();
                    return;
                }
            }
        }
        catch(Exception e){}

        System.out.println("\nPlease choose one of the options:\n"+
            "1. To continue with the above flight(s) and enter the passengers' details.\n"+
            "2. To make a choice again.\n"+
            "3. To go back to the home page.");
        choicemainpage();
    }
    static int c=0;

    public static void choicenoseats()
    {
        System.out.print("Your choice: ");
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
            System.out.print("\f");
            page1();
            break;
            case 2:
            System.out.print("\f");
            MainPage.mainpage();
            break;
            default:
            System.out.println("Invalid choice. Please enter again.");
            choicemainpage();
        }
    }

    public static void choicemainpage()
    {
        System.out.print("Your choice: ");
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
            page2();
            break;
            case 2:
            System.out.print("\f");
            page1();
            break;
            case 3:
            System.out.print("\f");
            MainPage.mainpage();
            break;
            default:
            System.out.println("Invalid choice. Please enter again.");
            choicemainpage();
        }
    }
    static String details[][];
    public static void page2()
    {
        System.out.println("\fPlease enter the details of all the passengers: ");
        details = Passengers.enterdetails(pas);
        System.out.println("Are you sure you want to continue with the above details? Once entered, these details cannot be changed!\n"+
            "Please choose one of the following: \n"+
            "1. To continue with the above details.\n"+
            "2. To reset this page.\n"+
            "3. To go back to the previous page.\n"+
            "4. To go to the home page.");
        choicepage2();
    }

    public static void choicepage2()
    {
        try
        {
            System.out.print("Your choice: ");
            c = Integer.parseInt(brline.readLine());
        }
        catch(Exception e){
            System.out.println("Please enter an integer.");
            choicepage2();
            return;
        }
        switch(c)
        {
            case 1:
            page3();
            break;
            case 2:
            page2();
            break;
            case 3:
            page1();
            break;
            case 4:
            MainPage.mainpage();
            break;
            default:
            System.out.println("Invalid Choice. Please try again.");
            choicepage2();
            break;
        }
    }

    public static void page3()
    {
        if(flight[1]==0)
        {
            System.out.println("\fPlease choose the seats for the passenger(s):\n"+
                "The seats which have a specific number on them are the ones which are available.\n"+
                "The seats which are marked with with 'XXX' have already been booked.\n"+
                "Please choose the seats which you want for each passenger.");
            try
            {
                int arr[][] = arrayofseats(flight[0]);
                for(int i=0;i<details.length;i++)
                {
                    System.out.println("Passenger: "+details[i][0]);
                    arraydisplay(flight[0]);
                    System.out.println();
                    String seat = seatnumber(flight[0]);
                    arr = updatearray(seat,arr);
                    details[i][2] = seat;
                    System.out.println();
                }
                System.out.println("Please choose of the following: \n"+
                    "1. To continue with the data given by you.\n"+
                    "2. To reset this page.\n"+
                    "3. To go back to the main menu.");
                choicepage3(arr);
            }
            catch(Exception e)
            {
                System.out.println("An exception occured: "+ e);
            }
        }
        else
        {
            System.out.println("\fPlease choose the seats for the passenger(s):\n"+
                "The seats which have a specific number on them are the ones which are available.\n"+
                "The seats which are marked with with 'XXX' have already been booked.\n"+
                "Please choose the seats which you want for each passenger.");
            try
            {
                System.out.println("\nFor Flight DF"+flight[0]);
                int arr[][] = arrayofseats(flight[0]);
                for(int i=0;i<details.length;i++)
                {
                    System.out.println("Passenger: "+details[i][0]);
                    arraydisplay(flight[0]);
                    System.out.println();
                    String seat = seatnumber(flight[0]);
                    arr = updatearray(seat,arr);
                    details[i][2] = seat;
                    System.out.println();
                }
                System.out.println("\nFor Flight DF"+flight[1]);
                int[][] arr1 = arrayofseats(flight[1]);
                for(int i=0;i<details.length;i++)
                {
                    System.out.println("Passenger: "+details[i][0]);
                    arraydisplay(flight[1]);
                    System.out.println();
                    String seat = seatnumber(flight[1]);
                    arr1 = updatearray(seat,arr1);
                    details[i][2]+=" "+seat;
                    System.out.println();
                }
                System.out.println("Please choose of the following: \n"+
                    "1. To continue with the data given by you.\n"+
                    "2. To reset this page.\n"+
                    "3. To go back to the main menu.");
                choicepage3(arr,arr1);
            }
            catch(Exception e)
            {

            }

        }
    }

    public static void choicepage3(int[][] arr)
    {
        try
        {
            System.out.print("Your choice: ");
            c = Integer.parseInt(brline.readLine());
        }
        catch(Exception e){
            System.out.println("Please enter an integer.");
            choicepage3(arr);
            return;
        }
        switch(c)
        {
            case 1:
            writearray(arr,flight[0]);
            page4();
            break;
            case 2:
            page3();
            break;
            case 3:
            MainPage.mainpage();
            break;
            default:
            System.out.println("Invalid Choice. Please try again.");
            choicepage3(arr);
            break;
        }
    }

    public static void choicepage3(int[][] arr,int[][] arr1)
    {
        try
        {
            System.out.print("Your choice: ");
            c = Integer.parseInt(brline.readLine());
        }
        catch(Exception e){
            System.out.println("Please enter an integer.");
            choicepage3(arr);
            return;
        }
        switch(c)
        {
            case 1:
            writearray(arr,flight[0]);
            writearray(arr1,flight[1]);
            page4();
            break;
            case 2:
            page3();
            break;
            case 3:
            MainPage.mainpage();
            break;
            default:
            System.out.println("Invalid Choice. Please try again.");
            choicepage3(arr);
            break;
        }
    }

    public static void page4()
    {
        System.out.println("\fConfirmation Page\n"+
            "Are you sure you want to confirm the booking?\n"+
            "The details of the booking are:\n");
        if(flight[1]==0)
        {
            java.util.Map<String,String> map = new java.util.HashMap<String,String>();
            try
            {
                map = getmapofairport.main();
            }
            catch(Exception e){}            
            System.out.println("Date: "+date);
            System.out.println("Source City: "+map.get(dep));
            System.out.println("Arrival City: "+map.get(arr));
            System.out.println("Is direct: True");
            System.out.println("Flight Number: DF"+flight[0]);
            String deptime="";
            String arrtime="";
            int cost=0;
            try
            {
                BufferedReader br = new BufferedReader(new FileReader("flightlist1.txt"));
                String text;
                while((text = br.readLine())!=null)
                {
                    if(Integer.parseInt(text.substring(0,3))==flight[0])
                    {
                        deptime = text.substring(12,17);
                        arrtime = text.substring(18,23);
                        cost = Integer.parseInt(text.substring(24));
                        break;
                    }
                }
            }
            catch(Exception e)
            {}
            System.out.println("\nDeparture Time: "+deptime);
            System.out.println("Arrival Time: "+arrtime);
            System.out.println("Total Cost: Rs"+(cost*details.length));
            System.out.println("Number of passengers: "+details.length);
            System.out.println("\nDetails of Passengers:");
            System.out.println("\tName\t\tAge\tSeat No.\tMobile Number\tGender");
            for(int i=0;i<details.length;i++)
            {
                if(details[i][0].length()>14)
                    System.out.println(details[i][0]+"\t"+details[i][1]+"\t"+details[i][2]+"\t\t"+details[i][3]+"\t"+details[i][4]);
                else
                    System.out.println(details[i][0]+"\t\t"+details[i][1]+"\t"+details[i][2]+"\t\t"+details[i][3]+"\t"+details[i][4]);               
            }
        }
        else
        {
            java.util.Map<String,String> map = new java.util.HashMap<String,String>();
            try
            {
                map = getmapofairport.main();
            }
            catch(Exception e){}
            System.out.println("Date: "+date);
            System.out.println("Source City: "+map.get(dep));
            System.out.println("Arrival City: "+map.get(arr));
            System.out.println("Is Direct: False");
            System.out.println("\nFlight 1: DF"+flight[0]);
            String dep1="";
            String arr1="";
            String deptime="";
            String arrtime="";
            int cost=0;
            try
            {
                BufferedReader br = new BufferedReader(new FileReader("flightlist1.txt"));
                String text;
                while((text = br.readLine())!=null)
                {
                    if(Integer.parseInt(text.substring(0,3))==flight[0])
                    {
                        dep1 = map.get(text.substring(4,7));
                        arr1 = map.get(text.substring(8,11));
                        deptime = text.substring(12,17);
                        arrtime = text.substring(18,23);
                        cost = Integer.parseInt(text.substring(24));
                        break;
                    }
                }
            }
            catch(Exception e)
            {}
            System.out.println("Departure City: "+dep1);
            System.out.println("Arrival City: "+arr1);
            System.out.println("Departure Time: "+deptime);
            System.out.println("Arrival Time: "+arrtime);
            System.out.println();
            System.out.println("Flight 2: DF"+flight[1]);
            String dep2="";
            String arr2="";
            String arr1time="";
            String dep1time="";
            try
            {
                BufferedReader br = new BufferedReader(new FileReader("flightlist1.txt"));
                String text;
                while((text = br.readLine())!=null)
                {
                    if(Integer.parseInt(text.substring(0,3))==flight[1])
                    {
                        dep2 = map.get(text.substring(4,7));
                        arr2 = map.get(text.substring(8,11));
                        dep1time = text.substring(12,17);
                        arr1time = text.substring(18,23);
                        cost += Integer.parseInt(text.substring(24));
                        break;
                    }
                }
            }
            catch(Exception e){}
            System.out.println("Departure City: "+dep2);
            System.out.println("Arrival City: "+arr2);
            System.out.println("Departure Time: "+dep1time);
            System.out.println("Arrival Time: "+arr1time);
            System.out.println();
            System.out.println("Layover:");
            System.out.println("City: "+dep2);
            System.out.println("Time: "+ (Integer.parseInt(dep1time.substring(0,2))-Integer.parseInt(arrtime.substring(0,2)))+" hours.");
            System.out.println();
            System.out.println("Total Cost: Rs"+(cost*details.length));
            System.out.println();
            System.out.println("Number of passengers: "+details.length);
            System.out.println("\nDetails of Passengers:");
            System.out.println("\tName\t\tAge\tSeat Nos.\tMobile Number\tGender");
            for(int i=0;i<details.length;i++)
            {
                if(details[i][0].length()>14)
                    System.out.println(details[i][0]+"\t"+details[i][1]+"\t"+details[i][2]+"\t\t"+details[i][3]+"\t"+details[i][4]);
                else
                    System.out.println(details[i][0]+"\t\t"+details[i][1]+"\t"+details[i][2]+"\t\t"+details[i][3]+"\t"+details[i][4]);               
            }

        }
        System.out.println("\nPlease choose one of the following:\n"+
            "1.To confirm the booking.\n"+
            "2.To go to the Main Page.");
        choicepage4();
    }

    public static void choicepage4()
    {
        try
        {
            System.out.print("Your choice: ");
            c = Integer.parseInt(brline.readLine());
        }
        catch(Exception e){
            System.out.println("Please enter an integer.");
            choicepage2();
            return;
        }
        switch(c)
        {
            case 1:
            int code=writepassenger();
            page5(code);
            break;
            case 2:
            MainPage.mainpage();
            break;
            default:
            System.out.println("Invalid Choice. Please try again.");
            choicepage2();
            break;
        }
    }

    public static int writepassenger()
    {
        int code=0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("Passengers\\Code.txt"));
            code = Integer.parseInt(br.readLine());
            PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("Passengers\\Code.txt")));
            pw.println(code+1);
            pw.close();
            br.close();
        }
        catch(Exception e){}
        try
        {
            if(flight[1]==0)
            {
                java.util.Map<String,String> map = new java.util.HashMap<String,String>();
                map = getmapofairport.main();
                PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("Passengers\\Pas"+code+".txt")));
                pw.println("Date: "+date);
                pw.println("Source City: "+map.get(dep));
                pw.println("Arrival City: "+map.get(arr));
                pw.println("Is direct: True");
                pw.println("Flight Number: DF"+flight[0]);
                String deptime="";
                String arrtime="";
                int cost=0;
                try
                {
                    BufferedReader br = new BufferedReader(new FileReader("flightlist1.txt"));
                    String text;
                    while((text = br.readLine())!=null)
                    {
                        if(Integer.parseInt(text.substring(0,3))==flight[0])
                        {
                            deptime = text.substring(12,17);
                            arrtime = text.substring(18,23);
                            cost = Integer.parseInt(text.substring(24));
                            break;
                        }
                    }
                }
                catch(Exception e)
                {}
                pw.println("\nDeparture Time: "+deptime);
                pw.println("Arrival Time: "+arrtime);
                pw.println("Total Cost: Rs"+(cost*details.length));
                pw.println("Number of passengers: "+details.length);
                pw.println("\nDetails of Passengers:");
                pw.println("\tName\t\tAge\tSeat No.\tMobile Number\tGender");
                for(int i=0;i<details.length;i++)
                {
                    if(details[i][0].length()>14)
                        pw.println(details[i][0]+"\t"+details[i][1]+"\t"+details[i][2]+"\t\t"+details[i][3]+"\t"+details[i][4]);
                    else
                        pw.println(details[i][0]+"\t\t"+details[i][1]+"\t"+details[i][2]+"\t\t"+details[i][3]+"\t"+details[i][4]);               
                }
                pw.close();
            }
            else 
            {
                PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("Passengers\\Pas"+code+".txt")));
                java.util.Map<String,String> map = new java.util.HashMap<String,String>();
                try
                {
                    map = getmapofairport.main();
                }
                catch(Exception e){}
                pw.println("Date: "+date);
                pw.println("Source City: "+map.get(dep));
                pw.println("Arrival City: "+map.get(arr));
                pw.println("Is Direct: False");
                pw.println("\nFlight 1: DF"+flight[0]);
                String dep1="";
                String arr1="";
                String deptime="";
                String arrtime="";
                int cost=0;
                try
                {
                    BufferedReader br = new BufferedReader(new FileReader("flightlist1.txt"));
                    String text;
                    while((text = br.readLine())!=null)
                    {
                        if(Integer.parseInt(text.substring(0,3))==flight[0])
                        {
                            dep1 = map.get(text.substring(4,7));
                            arr1 = map.get(text.substring(8,11));
                            deptime = text.substring(12,17);
                            arrtime = text.substring(18,23);
                            cost = Integer.parseInt(text.substring(24));
                            break;
                        }
                    }
                }
                catch(Exception e)
                {}
                pw.println("Departure City: "+dep1);
                pw.println("Arrival City: "+arr1);
                pw.println("Departure Time: "+deptime);
                pw.println("Arrival Time: "+arrtime);
                pw.println();
                pw.println("Flight 2: DF"+flight[1]);
                String dep2="";
                String arr2="";
                String arr1time="";
                String dep1time="";
                try
                {
                    BufferedReader br = new BufferedReader(new FileReader("flightlist1.txt"));
                    String text;
                    while((text = br.readLine())!=null)
                    {
                        if(Integer.parseInt(text.substring(0,3))==flight[1])
                        {
                            dep2 = map.get(text.substring(4,7));
                            arr2 = map.get(text.substring(8,11));
                            dep1time = text.substring(12,17);
                            arr1time = text.substring(18,23);
                            cost += Integer.parseInt(text.substring(24));
                            break;
                        }
                    }
                }
                catch(Exception e){}
                pw.println("Departure City: "+dep2);
                pw.println("Arrival City: "+arr2);
                pw.println("Departure Time: "+dep1time);
                pw.println("Arrival Time: "+arr1time);
                pw.println();
                pw.println("Layover:");
                pw.println("City: "+dep2);
                pw.println("Time: "+ (Integer.parseInt(dep1time.substring(0,2))-Integer.parseInt(arrtime.substring(0,2)))+" hours.");
                pw.println();
                pw.println("Total Cost: Rs"+(cost*details.length));
                pw.println();
                pw.println("Number of passengers: "+details.length);
                pw.println("\nDetails of Passengers:");
                pw.println("\tName\t\tAge\tSeat Nos.\tMobile Number\tGender");
                for(int i=0;i<details.length;i++)
                {
                    if(details[i][0].length()>14)
                        pw.println(details[i][0]+"\t"+details[i][1]+"\t"+details[i][2]+"\t\t"+details[i][3]+"\t"+details[i][4]);
                    else
                        pw.println(details[i][0]+"\t\t"+details[i][1]+"\t"+details[i][2]+"\t\t"+details[i][3]+"\t"+details[i][4]);               
                }
                pw.close();
            }
        }
        catch(Exception e)
        {}

        return code;
    }
    
    public static void page5(int code)
    {
        try
        {
        System.out.println("\fCongratulations.\n"+
                            "Your booking has been confirmed!\n"+
                            "Your passenger code is "+code+"\n"+
                            "Please remember the passenger code. It contains all details of the flight.\n"+
                            "You can access the details from the 'Previous Booking' using the code.\n\n"+
                            "Payment details : \n"+
                            "Please send the money specified before in one of the 2 following ways: \n"+
                            "1. Please send the money to one of the airports which we serve.\n"+
                            "   The money can be deposited in our counter and along with your passenger number.\n"+
                            "2. Please transfer the money to our bank account.\n"+
                            "   Our bank details are:\n"+
                            "     Account Number: 3487210563\n"+
                            "     Account Name: Domestic AirServices Pvt. Ltd.\n"+
                            "     IFSC code: HSBC0400002\n"+
                            "     Account Type: Current Account.\n"+
                            "   Please transfer the money to this account with your passenger code in the comments section.\n\n"+
                            "Faliure to send the money 3 days from now will result in the termination of the booking.\n"+
                            "For any queries, please dont hesitate to contact us at:\n"+
                            "  Email: inquiry@domesticairservices.in\n"+
                            "  Phone: +91 9830376021\n\n"+
                            "Please press any character and enter to go back to main menu.");
        brline.readLine();
        MainPage.mainpage();
        }
        catch(Exception e){}
    }

    public static int[][] updatearray(String seat, int[][] arr)
    {
        int num=0;
        int pos=0;
        if(seat.length()==2)
        {
            num = Integer.parseInt(seat.charAt(0)+"");
            char c= seat.charAt(1);
            if(c=='A')
            {
                pos=1;
            }
            else if(c=='B')
            {
                pos=2;
            }
            else if(c=='C')
            {
                pos= 3;
            }
            else if(c=='D')
            {
                pos= 4;
            }
            else if(c=='E')
            {
                pos= 5;
            }
            else if(c=='F')
            {
                pos= 6;
            }
        }
        else
        {
            num = (Integer.parseInt(seat.charAt(0)+"")*10)+Integer.parseInt(seat.charAt(1)+"");
            char c= seat.charAt(2);
            if(c=='A')
            {
                pos=1;
            }
            else if(c=='B')
            {
                pos=2;
            }
            else if(c=='C')
            {
                pos= 3;
            }
            else if(c=='D')
            {
                pos= 4;
            }
            else if(c=='E')
            {
                pos= 5;
            }
            else if(c=='F')
            {
                pos= 6;
            }
        }
        arr[pos-1][num-1]=1;
        return arr;
    }

    public static String seatnumber(int f)
    {
        try
        {
            System.out.print("Please enter the seat number for this passenger: ");
            String str = brline.readLine();
            str = str.trim();
            str = str.toUpperCase();
            String arr[] = arrayofavailableseats(f);
            for(int i=0;i<arr.length;i++)
            {
                if(arr[i]!=null&&str.equalsIgnoreCase(arr[i]))
                    return str;
            }
            System.out.println("Invalid seat number. Please enter again.");
            return seatnumber(f);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static int[][] arrayofseats(int f) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("Data\\seats"+date+".txt"));
        String text;
        int i=1;
        int j=0;
        int arr[][] = new int[6][30];
        while((text=br.readLine())!=null)
        {
            if(i == (f-101)*8 +3)
            {
                for(int k=1;k<=6;k++)
                {
                    for(int l=0;l<30;l++)
                    {
                        arr[j][l] = Integer.parseInt(text.charAt(l*2)+"");
                    }
                    j++;
                    text = br.readLine();
                }
                break;
            }
            i++;
        }
        return arr;
    }

    public static void arraydisplay(int f)
    {
        String str[] = {"A","B","C","D","E","F"};
        int arr[][] = new int[6][30];

        try{
            arr= arrayofseats(f);
        }
        catch(Exception e)
        {}
        for(int i=1;i<=119;i++)
        {
            System.out.print("-");
        }
        System.out.println();
        for(int i=0;i<6;i++)
        {
            for(int j=29;j>=0;j--)
            {
                if(arr[i][j]==0)
                {
                    if((j+1)<10)
                        System.out.print("0"+(j+1)+str[i]+" ");
                    else
                        System.out.print((j+1)+str[i]+" ");
                }
                else
                    System.out.print("XXX ");
            }
            if(i<=2)
            {
                for(int j =0;j<i;j++)
                {
                    System.out.print(" ");
                }
                System.out.print("\\\\");
            }
            else
            {
                for(int j =5;j>i;j--)
                {
                    System.out.print(" ");
                }
                System.out.print("//");
            }
            System.out.println();
            if(i==2)
            {

                System.out.println();
            }
        }
        for(int i=1;i<=119;i++)
        {
            System.out.print("-");
        }
    }

    public static String[] arrayofavailableseats(int f)
    {
        String[] arr = new String[234];
        int a[][] = new int[6][30];
        try{
            a= arrayofseats(f);
        }
        catch(Exception e)
        {}
        String str[] = {"A","B","C","D","E","F"};
        int c=0;
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<30;j++)
            {
                if(a[i][j]==0)
                {
                    if((j+1)<10)
                    {
                        arr[c]="0"+ (j+1) + str[i];
                        c++;
                        arr[c] = (j+1)+str[i];
                    }
                    else
                        arr[c]=(j+1) + str[i];
                    c++;
                }
            }
        }
        return arr;
    }

    public static int[] nodirect()
    {
        int count=0;
        int counted[][] = new int[10][2];
        String arr[] = {"DEL","BOM","BLR","HYD","MAA"};
        System.out.println("Option \t Flight No.    Departure Airport \t Arrival Airport \t Departure Time \t Arrival Time \t    Cost(In Rupees) \t Availability(No. of seats)");
        for(int i=0;i<5;i++)
        {
            try
            {
                java.util.Map<String,String> map = getmapofairport.main();
                BufferedReader br = new BufferedReader(new FileReader("flightlist1.txt"));
                String text;

                while((text=br.readLine())!=null)
                {
                    String text1 = text.substring(4,11);
                    String text2 = dep.concat(" ".concat(arr[i]));
                    if(text1.equalsIgnoreCase(text2))
                    {
                        int arrtime=Integer.parseInt(text.substring(18,20));
                        BufferedReader br1 = new BufferedReader(new FileReader("flightlist1.txt"));
                        String text3;
                        while((text3=br1.readLine())!=null)
                        {
                            text1 = text3.substring(4,11);
                            text2 = arr[i].concat(" ".concat(ResultPage.arr));
                            if(text1.equalsIgnoreCase(text2))
                            {
                                int arrtime1 = Integer.parseInt(text3.substring(12,14));
                                if(arrtime1-arrtime>0)
                                {
                                    counted[count][0] = Integer.parseInt(text.substring(0,3));
                                    counted[count][1] = Integer.parseInt(text3.substring(0,3));
                                    count = count+1;
                                    System.out.println("  "+ count + "        DF"+text.substring(0,3)+"\t "+map.get(text.substring(4,7))+"\t\t    "+map.get(text.substring(8,11))+"\t\t     "+
                                        text.substring(12,18)+"\t\t    "+text.substring(18,24)+"\t\t"+text.substring(24) +"\t\t\t"+checkseats(Integer.parseInt(text.substring(0,3)),date));
                                    System.out.println("           DF"+text3.substring(0,3)+"\t "+map.get(text3.substring(4,7))+"\t\t    "+map.get(text3.substring(8,11))+"\t\t     "+
                                        text3.substring(12,18)+"\t\t    "+text3.substring(18,24)+"\t\t"+text3.substring(24) +"\t\t\t"+checkseats(Integer.parseInt(text3.substring(0,3)),date));
                                    System.out.println();
                                }
                            }
                        }
                    }
                }
            }
            catch(Exception e)
            {}
        }
        System.out.println("Please choose the option of the sequence of flights which you want to take: ");
        return nodirectchoice(counted, count);
    }

    public static int[] nodirectchoice(int[][] arr, int i)
    {
        try
        {
            System.out.print("Your choice: ");
            c = Integer.parseInt(brline.readLine());
        }
        catch(Exception e)
        {
            System.out.println("Please enter an integer.");
            nodirectchoice(arr,i);
        }
        if(c<1 || c>i)
        {
            System.out.println("Please enter a valid choice.");
            return nodirectchoice(arr,i);
        }
        return arr[c-1];
    }

    public static int enterfn(int[] arr)
    {
        System.out.print("The preferred flight No. is: DF");
        int i = -1;
        try
        {
            i = sc.nextInt();
        }
        catch(Exception e)
        {
            System.out.println("Wrong choice. Please enter an Integer.");
            return enterfn(arr);
        }

        for(int j=0;j<arr.length;j++)
        {
            if(arr[j]==i)
                return i;
        }
        System.out.println("\nWrong choice. Please try again. ");
        return enterfn(arr);
    }

    public static int checkseats(int f, String date) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("Data\\seats"+date+".txt"));
        String text;
        int i=1;
        while((text=br.readLine())!=null)
        {
            if(i == (f-101)*8 +2)
            {
                break;
            }
            i++;
        }
        return Integer.parseInt(text);
    }

    public static void writearray(int[][] arr, int f)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("Data\\seats"+date+".txt"));
            PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("temp.txt")));
            int i=1;String text;
            while((text=br.readLine())!=null)
            {
                if(i == (f-101)*8 +2)
                {
                    int s=Integer.parseInt(text);
                    for(int j=1;j<=pas;j++)
                    s--;
                    pw.println(s);
                    text = br.readLine();
                    for(int k=0;k<5;k++)
                    {
                        String str="";
                        for(int l=0;l<29;l++)
                        {
                            str+=arr[k][l]+" ";
                        }
                        str+=arr[k][29];
                        pw.println(str);
                        text = br.readLine();
                    }
                    {
                        String str="";
                        for(int l=0;l<29;l++)
                        {
                            str+=arr[5][l]+" ";
                        }
                        str+=arr[5][29];
                        pw.println(str);
                    }
                    i++;
                }
                else
                {
                    i++;
                    pw.println(text);
                }
            }
            br.close();
            pw.close();
            BufferedReader br1 = new BufferedReader(new FileReader("temp.txt"));
            PrintWriter pw1=new PrintWriter(new BufferedWriter(new FileWriter("Data\\seats"+date+".txt")));
            while((text=br1.readLine())!=null)
            {
                pw1.println(text);
            }
            br1.close();
            pw1.close();
        }
        catch(Exception e)
        {}
    }

    public static void writetofile()
    {
        int arr[][] = new int[6][30];
        arr[2][15] = 1;
        arr[3][12] = 1;
        writearray(arr,101);
    }
}