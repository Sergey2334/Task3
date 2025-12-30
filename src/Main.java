import java.io.Reader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RealEstate myRealEstate1 = new RealEstate();

        mainLogic(myRealEstate1);
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
        final int MIN_OPTIONS = 1;
        final int MAX_OPTIONS = 3;

        System.out.println("Main Menu:");
        System.out.println("‾‾‾‾‾‾‾‾‾‾");
        System.out.println("\t1. Sign Up");
        System.out.println("\t2. Login");
        System.out.println("\t3. Exit");
        System.out.printf("Enter your choice (%d - %d): ", MIN_OPTIONS, MAX_OPTIONS);

        int userChoice = input.nextInt();
        while (userChoice < MIN_OPTIONS || userChoice > MAX_OPTIONS)
        {
            System.out.println("\t* Invalid choice *");
            System.out.printf("Enter your choice (%d - %d): ", MIN_OPTIONS, MAX_OPTIONS);
            userChoice = input.nextInt();
        }
        /*
        // Prints main menu over and over
        if (userChoice < 1 ||  userChoice > 3)
        {
            System.out.println("\t* Invalid choice *");
            mainMenu();
        }
        */
        return userChoice;
    }

    public static int userMenu()
    {
        Scanner input = new Scanner(System.in);
        final int MIN_OPTIONS = 1;
        final int MAX_OPTIONS = 6;

        System.out.println("User Menu:");
        System.out.println("‾‾‾‾‾‾‾‾‾‾");
        System.out.println("\t1. Post New Property");
        System.out.println("\t2. Remove Post");
        System.out.println("\t3. List All Property Posts");
        System.out.println("\t4. List My Property Posts");
        System.out.println("\t5. Search Property Posts With Parameters");
        System.out.println("\t6. Logout and Return to Main Menu");
        System.out.printf("Enter your choice (%d - %d): ",  MIN_OPTIONS, MAX_OPTIONS);

        int userChoice = input.nextInt();
        while (userChoice < MIN_OPTIONS || userChoice > MAX_OPTIONS)
        {
            System.out.println("\t* Invalid choice *");
            System.out.printf("Enter your choice (%d - %d): ",  MIN_OPTIONS, MAX_OPTIONS);
            userChoice = input.nextInt();
        }
        return userChoice;
    }

    public static void mainLogic(RealEstate inputtedRealEstate)
    {
        welcomeMessage();

        final int SIGN_UP_CHOICE_VAL = 1;
        final int LOGIN_CHOICE_VAL = 2;
        final int EXIT_CHOICE_VAL = 3;


        boolean active = true;
        while(active)
        {
            switch(mainMenu())
            {
                // Sign Up
                case SIGN_UP_CHOICE_VAL:
                    System.out.println("\t┌-------┐");
                    System.out.println("\t│Sign Up│");
                    System.out.println("\t└-------┘");

                    inputtedRealEstate.createUser();
                    break;

                // Login
                case LOGIN_CHOICE_VAL:
                    System.out.println("\t┌-----┐");
                    System.out.println("\t│Login│");
                    System.out.println("\t└-----┘");

                    User inputtedUser = inputtedRealEstate.login();

                    // Login Success -> User Menu
                    if(inputtedUser != null)
                    {
                        System.out.println("Login Successful! :D");

                        // User Menu
                        userLogic();
                    }

                    // Login Failed -> Main Menu
                    System.out.println("Login Failed! :(");
                    break;


                // Exit
                case EXIT_CHOICE_VAL:
                    System.out.println("\t┌----┐");
                    System.out.println("\t│Exit│");
                    System.out.println("\t└----┘");
                    active = false;
                    break;
            }
        }

    }

    public static void userLogic(RealEstate inputtedRealEstate)
    {
        final int POST_NEW_PROPERTY_VAL = 1;
        final int REMOVE_POST_PROPERTY_VAL = 2;
        final int LIST_ALL_PROPERTY_VAL = 3;
        final int LIST_MY_PROPERTY_VAL = 4;
        final int SEARCH_PROPERTY_WITH_PARAMETERS_VAL = 5;
        final int LOGOUT_VAL = 6;

        boolean active = true;
        while(active)
        {
            switch(userMenu())
            {
                case POST_NEW_PROPERTY_VAL:
                    break;

                case REMOVE_POST_PROPERTY_VAL:
                    break;

                case LIST_ALL_PROPERTY_VAL:
                    break;

                case LIST_MY_PROPERTY_VAL:
                    break;

                case SEARCH_PROPERTY_WITH_PARAMETERS_VAL:
                    break;

                case LOGOUT_VAL:
                    active = false;
                    System.out.println("Login Successful! :D");
                    break;
            }
        }


    }

}
