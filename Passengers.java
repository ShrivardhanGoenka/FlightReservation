import java.io.*;
class Passengers
{
    static BufferedReader brline = new BufferedReader(new InputStreamReader(System.in));
    //String mobile;
    public static String[][] enterdetails(int pas)
    {
        System.out.println();
        String details[][] = new String[pas][5];
        for(int i=1;i<=pas;i++)
        {
            System.out.println("Passenger#"+i);
            details[i-1][0] = name();
            details[i-1][1] = ""+getage();
            details[i-1][3] = mobno();
            details[i-1][4] = gender();
            System.out.println();
        }
        return details;
    }

    public static String name() 
    {
        try{
            System.out.print("Enter Name:");
            String FName=brline.readLine().trim();
            FName=" "+FName;
            int flag=0;
            String str="";
            for(int i=0;i<FName.length();i++)
            {
                if(FName.charAt(i)==' ')
            {
                flag=1;
                str+=Character.toUpperCase(FName.charAt(i));
            }
            else if(flag==1)
            {
                str+=FName.charAt(i);
                flag=0;
            }
            else
            {
                str+=Character.toLowerCase(FName.charAt(i));
            }
            }
            return str.trim();
        }
        catch(Exception e){}
        return name();
    }

    public static String gender()
    {
        char gen=' ';
        System.out.println("Enter Gender:");
        System.out.println("Enter M for Male");
        System.out.println("Enter F for Female");
        System.out.print("Your Gender: ");
        try{
            gen=brline.readLine().toUpperCase().charAt(0);
        }
        catch(Exception e){}
        if(gen!='M' && gen!='F')
        {
            System.out.println("Please enter valid gender");
            return gender();
        }
        String gen1="";
        if (gen=='M')
        {
            gen1="Male";
        }
        else if(gen=='F')
        {
            gen1="Female";
        }
        return gen1;
    }

    static int age;
    public static int getage()
    {
        System.out.print("Please enter your age: ");
        try
        {
            age = Integer.parseInt(brline.readLine());
            return age;
        }
        catch(Exception e)
        {
            System.out.println("Please enter an integer.");
            return getage();
        }
    }

    static String mobileno;
    public static String mobno()
    {
        System.out.print("Enter Mobile Number without country code or spaces:");

        try
        {
            mobileno = brline.readLine();
        }
        catch(Exception e)
        {
            return mobno();
        }
        int l= mobileno.length();
        if(l!=10)
        {
            System.out.println("Please Enter Valid Mobile Number");
            return mobno();
        }
        char c;
        for(int i=0;i<l;i++)
        {
            c=mobileno.charAt(i);
            if(Character.isLetter(c))
            {
                System.out.println("Please enter valid phone number");
                return mobno();
            }

        }
        return mobileno;
    }
}