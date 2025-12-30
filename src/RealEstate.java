import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private City[] cities;

    public void createUser() // O(1)
    {
        String userName = createValidUserName();
        String userPassword = createValidUserPassword();
        String userPhone =  createValidUserPhone();
        boolean isBroker = isUserABroker();

        User newUser = new User(userName, userPassword, userPhone, isBroker);
    }

    private String createValidUserName() // O(n) ?
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter username: ");
        String userName = input.nextLine();
        while (isUserNameTaken(userName))
        {
            System.out.println("Username already taken!");
            System.out.print("Enter username: ");
            userName = input.nextLine();
        }
        return userName;
    }

    // Checks if the Inputted Username is Taken
    private boolean isUserNameTaken(String userName) // O(1) ? or O(n) - Based on the Input its O(1) but we have User[] , does that count ?
    {
        if (users != null && users.length > 0) // Checks if there are any users
        {
            for (int i = 0; i < users.length; i++)
            {
                if (users[i].isUserNameEquals(userName))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private String createValidUserPassword() // O(n)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter password: ");
        String userPassword = input.nextLine();
        while (!isUserPasswordValid(userPassword))
        {
            System.out.println("Invalid password!");
            System.out.print("Enter password: ");
            userPassword = input.nextLine();

        }
        return userPassword;
    }

    private boolean isUserPasswordValid(String password) // O(1)
    {
        final int MIN_PASSWORD_LENGTH = 5;

        if (password.length() < MIN_PASSWORD_LENGTH)
        {
            return false;
        }

        // Regex : {.* - (anything)} {\\d - (digits 0-9)} {.* - (anything)}
        if (!password.matches(".*\\d.*")) // Checks if there is any Digit
        {
            return false;
        }

        // Regex : {.* - (anything)} {[$%_] - (Set of those Symbols))} {.* - (anything)}
        if (!password.matches(".*[$%_].*")) // Checks if there is any Special Symbol
        {
            return false;
        }

        return true;
    }

    private String createValidUserPhone() // O(n) ?
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter phone number: ");
        String userPhone = input.nextLine();
        while (!isUserPhoneValid(userPhone))
        {
            System.out.println("Invalid phone number!");
            System.out.print("Enter phone number: ");
            userPhone = input.nextLine();

        }
        return userPhone;
    }

    private boolean isUserPhoneValid(String phoneNumber) // O(1)
    {
        final int PHONE_NUMBER_LENGTH = 10;

        if (phoneNumber.length() != PHONE_NUMBER_LENGTH)
        {
            return false;
        }

        if (!phoneNumber.startsWith("05"))
        {
            return  false;
        }

        if (phoneNumber.matches(".*[^0-9].*")) // Checks if there is any Non-Numeric Symbol
        {
            return false;
        }

        return true;
    }

    private boolean isUserABroker() // O(n) ?
    {
        Scanner input = new Scanner(System.in);
        final int REGULAR_USER_VAL = 0;
        final int BROKER_USER_VAL = 1;

        System.out.println("Are a you a Broker ?: ");
        System.out.printf("%d (YES) , %d (N0): ", BROKER_USER_VAL, REGULAR_USER_VAL);
        int inputtedVal = input.nextInt();

        while (inputtedVal != REGULAR_USER_VAL && inputtedVal != BROKER_USER_VAL)
        {
            System.out.println("Invalid input!");
            System.out.printf("%d (YES) , %d (N0): ", BROKER_USER_VAL, REGULAR_USER_VAL);
            inputtedVal = input.nextInt();
        }

        return inputtedVal == BROKER_USER_VAL;
    }
}
