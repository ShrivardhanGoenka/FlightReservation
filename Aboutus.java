import java.util.Scanner;
class Aboutus
{
    public static void mainpage()
    {
        System.out.println("\f\t\t\t\t\t\t\t\tAbout us");
        System.out.print("Domestic AirServices is an Indian domestic aviation company which serves 10 of the major cities in India which are:\n"+
                           "Delhi, Mumbai, Kolkata, Chennai, Goa, Hyderabad, Bangalore, Cochin, Trivandrum, Amritsar.\n"+
                           "We serve 64 direct flights daily between the 10 cities and have connecting flights connecting all the 10 cities.\n"+
                           "We have a fleet of 40 A-320 aircrafts which serve the 126 flights everyday. All our seats our economy class as our longest \nflight is of 3 hours.\n"+
                           "Our A-320s have 6*30 seats per flight with costs ranging from Rs 4000 to Rs 13000.\n\n"+
                           "   Our bank details are:\n"+
                            "     Account Number: 3487210563\n"+
                            "     Account Name: Domestic AirServices Pvt. Ltd.\n"+
                            "     IFSC code: HSBC0400002\n"+
                            "     Account Type: Current Account.\n\n"+
                            "For any queries, please dont hesitate to contact us at:\n"+
                            "  Email: inquiry@domesticairservices.in\n"+
                            "  Phone: +91 9830376021\n\n"+
                           "Please enter any character and press enter to go to the main page: ");
        Scanner sc = new Scanner(System.in);
        sc.next();
        System.out.print("\f");
        MainPage.mainpage();
    }
}