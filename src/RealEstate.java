import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private City[] cities;

    public User[] getUsers()
    {
        if (this.users == null)
        {
            return new User[0];
        }
        return this.users;
    }

    // SIGN UP LOGIC
    // ______________________________________________________________________________________________
    public void createUser() // O(1)
    {
        String userName = createValidUserName();
        String userPassword = createValidUserPassword();
        String userPhoneNumber =  createValidUserPhone();
        boolean isBroker = isUserABroker();

        User newUser = new User(userName, userPassword, userPhoneNumber, isBroker);
        addNewUserToUsers(newUser);
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
        if (this.users != null && this.users.length > 0) // Checks if there are any users
        {
            for (int i = 0; i < this.users.length; i++)
            {
                if (this.users[i].isUserNameEquals(userName))
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

        System.out.print("Enter password (Min Length 5 , At Least 1 Digit and Has to Have one Of these Symbols [$,%,_]): ");
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

        System.out.print("Enter phone number (Valid Israel Phone Format 0501234567): ");
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

    // Adds The New User When Someone Signs Up
    private void addNewUserToUsers(User newUser) // O(1) ?
    {
        int usersArrayLength = 0;
        if (this.users != null)
        {
            usersArrayLength = this.users.length;
        }
        User[] newUsers = new User[usersArrayLength + 1]; // Creating a New Users Array
        for (int i = 0; i < usersArrayLength; i++)
        {
            newUsers[i] = this.users[i];
        }
        newUsers[usersArrayLength] = newUser;
        this.users = newUsers; // This Users-Array Pointing to the New Users-Array
    }
    // ______________________________________________________________________________________________


    // LOGIN LOGIC
    // ______________________________________________________________________________________________
    public User login()
    {
        User[] usersArray = this.users;
        if (usersArray == null || usersArray.length == 0) // There are no Users in the System
        {
            System.out.println("No Users Found!");
            return null;
        }

        Scanner input = new Scanner(System.in);

        System.out.print("Enter username: ");
        String inputtedUserName = input.nextLine();

        System.out.print("Enter password: ");
        String inputtedUserPassword = input.nextLine();

        User targetUser = null;
        for (int i = 0; i < usersArray.length; i++)
        {
            if (usersArray[i].isUserNameEquals(inputtedUserName))
            {
                targetUser = usersArray[i];
                break;
            }
        }

        if (targetUser == null)
        {
            System.out.println("Invalid User!");
            return null;
        }


        if (!targetUser.isPasswordEquals(inputtedUserPassword))
        {
            System.out.println("Wrong Password!");
            return null;
        }

        return targetUser;
    }


    // ______________________________________________________________________________________________

}
