import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User myUser1 = new User("Bob", "123", "0501234567", false);
        User myUser2 = new User("John", "123", "0501234567", true);
        System.out.println(myUser1);
        System.out.println(myUser2);
    }

    public static void welcomeMessage() // O(1)
    {
        System.out.println("Hello World, and Welcome to our Real Estate Agency");
        System.out.println("+----------------------------------------------------+");
        System.out.println("|                Home Sweet Loan                     |");
        System.out.println("+----------------------------------------------------+");
    }

    public static int mainMenu() // O(n)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Main Menu:");
        System.out.println("‾‾‾‾‾‾‾‾‾‾");
        System.out.println("\t1. Sign Up");
        System.out.println("\t2. Login");
        System.out.println("\t3. Exit");
        System.out.print("Enter your choice (1 - 3): ");

        int userChoice = input.nextInt();

        while (userChoice < 1 || userChoice > 3)
        {
            System.out.println("\t* Invalid choice *");
            System.out.print("Enter your choice (1 - 3): ");
            userChoice = input.nextInt();
        }

        // Prints main menu over and over
//        if (userChoice < 1 ||  userChoice > 3)
//        {
//            System.out.println("\t* Invalid choice *");
//            mainMenu();
//        }

        return userChoice;
    }

    public static void mainLogic()
    {
        welcomeMessage();

        final int signUpChoice = 1;
        final int loginChoice = 2;
        final int exitChoice = 3;

        switch(mainMenu())
        {
            case signUpChoice:
                // Sign Up
                System.out.println("Sign Up");


                break;

            case loginChoice:
                // Login
                System.out.println("Login");


                break;

            default:
                // Exit
                System.out.println("Exit");


                break;
        }
    }

}
