import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private City[] cities;

    public RealEstate()
    {
        City city1 = new City("Ashkelon", "South");
        City city2 = new City("Ashkeltown", "South");
        City city3 = new City("Ashdod", "South");
        City city4 = new City("Eilat", "Negev");
        City city5 = new City("Haifa", "North");
        City city6 = new City("Netaniya", "HaSharon");
        City city7 = new City("Tel-Aviv", "Center"); // 🤮
        City city8 = new City("Jerusalem", "Center");
        City city9 = new City("Metula", "North");
        City city10 = new City("Harish", "North");
        this.cities = new City[]{city1, city2, city3, city4, city5, city6, city7, city8, city9, city10};
    }

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

    // USER MENU LOGIC
    // ______________________________________________________________________________________________
    public boolean postNewProperty(User user)
    {
        // If User Has Reached Max Posts
        if (!isUserAllowedToPost(user))
        {
            return false;
        }

        // List all cities
        City[] citiesArray = this.cities;
        System.out.println("\nCities List:");
        for (int i = 0; i < citiesArray.length; i++)
        {
            System.out.println(citiesArray[i]);
        }

        // Get Valid City Name
        City chosenCity = getValidCityFromCityName();
        if (chosenCity == null)
        {
            System.out.println("Invalid City!");
            return false;
        }
        String chosenCityName = chosenCity.getName();

        // List All Streets of Chosen City
        String[] chosenCityStreets = chosenCity.getStreets();
        for (int i = 0; i < chosenCityStreets.length; i++)
        {
            System.out.println(chosenCityStreets[i]);
        }

        // Get Valid Street Name
        String chosenStreetName = getValidStreetName(chosenCityStreets);
        if (chosenStreetName == null)
        {
            System.out.println("Invalid Street!");
            return false;
        }

        // Get Property Type
        int chosenPropertyType = getPropertyType();
        if (chosenPropertyType == 0)
        {
            System.out.println("Invalid Property Type!");
            return false;
        }

        Scanner input = new Scanner(System.in);

        // Get Property Floor
        final int APARTMENT_TYPE_VAL = 1;
        int chosenFloor = 0;
        if (chosenPropertyType == APARTMENT_TYPE_VAL)
        {
            System.out.print("Enter Floor: ");
            chosenFloor = input.nextInt();
        }

        // Get Property Rooms Count
        System.out.print("Enter Property Rooms Count: ");
        int chosenPropertyRoomsCount = input.nextInt();

        // Get Property Number
        System.out.print("Enter Property Number: ");
        int chosenPropertyNumber = input.nextInt();

        // Get is Property For Rent;
        boolean chosenIsPropertyForRent = isPropertyForRent();

        // Get Propert Price
        System.out.print("Enter Property Price: ");
        int chosenPropertyPrice = input.nextInt();

        // Create New Property Object
        Property newProperty = new Property(chosenCityName,
                                            chosenStreetName,
                                            chosenPropertyRoomsCount,
                                            chosenPropertyPrice,
                                            chosenPropertyType ,
                                            chosenIsPropertyForRent,
                                            chosenPropertyNumber,
                                            chosenFloor);

        addNewPropertyToProperties(newProperty);

        return true;
    }

    private boolean isUserAllowedToPost(User user)
    {
        // Broker - Max 5
        // Regular - Max 2
        final int BROKER_MAX_POSTS = 5;
        final int REGULAR_MAX_POSTS = 2;

        int userMaxPropertyPostsAllowed = 0;
        if (user.isBroker())
        {
            userMaxPropertyPostsAllowed = BROKER_MAX_POSTS;
        }
        else
        {
            userMaxPropertyPostsAllowed = REGULAR_MAX_POSTS;
        }

        // Check if User can Post
        Property[] propertiesArray = this.properties;
        int userPostCount = 0;
        if (propertiesArray != null)
        {
            for (int i = 0; i < propertiesArray.length; i++)
            {
                if (propertiesArray[i].isUserEqualsToUserPoster(user))
                {
                    userPostCount++;
                }
            }
        }
        if (userPostCount >= userMaxPropertyPostsAllowed)
        {
            System.out.printf("Max Posts Reached! (%d)\n", userMaxPropertyPostsAllowed);
            return false;
        }

        return true;
    }

    private City getValidCityFromCityName()
    {
        City[] citiesArray = this.cities;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a City Name :");
        String inputtedCityName = input.nextLine();

        City foundCity = null;
        for (int i = 0; i < citiesArray.length; i++)
        {
            if (citiesArray[i].isCityNameEqual(inputtedCityName))
            {
                foundCity = citiesArray[i];
                break;
            }
        }
        return foundCity;
    }

    private String getValidStreetName(String[] streetNames)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a Street Name :");
        String inputtedStreetName = input.nextLine();

        for (int i = 0; i < streetNames.length; i++)
        {
            if (streetNames[i].equalsIgnoreCase(inputtedStreetName))
            {
                return  streetNames[i];
            }
        }
        return null;
    }

    private int getPropertyType()
    {
        Scanner input = new Scanner(System.in);

        final int APARTMENT_VAL = 1;
        final int PENTHOUSE_VAL = 2;
        final int HOUSE_VAL = 3;
        final int ERROR_VAL = 0;

        System.out.println("Enter a Property Type :");
        System.out.printf("\t%d.Apartment (Regular Apartment in a Building)\n", APARTMENT_VAL);
        System.out.printf("\t%d.Penthouse (Penthouse Apartment in a Building)\n", PENTHOUSE_VAL);
        System.out.printf("\t%d.House (Private House)\n", HOUSE_VAL);
        System.out.print("Enter a Property Type (1 - 3) : ");
        int inputtedPropertyType = input.nextInt();

        if (inputtedPropertyType < 1 || inputtedPropertyType > 3)
        {
            return  ERROR_VAL;
        }
        return inputtedPropertyType;
    }

    public boolean isPropertyForRent()
    {
        Scanner input = new Scanner(System.in);
        final int NEGATIVE_VAL = 0;
        final int POSITIVE_VAL = 1;

        System.out.println("Is The Property For Rent ? : ");
        int inputtedPropertyType = -1;
        do
        {
            System.out.printf("%d (No) , %d (Yes): ", NEGATIVE_VAL, POSITIVE_VAL);
            inputtedPropertyType = input.nextInt();
        }
        while (inputtedPropertyType != NEGATIVE_VAL && inputtedPropertyType != POSITIVE_VAL);

        if(inputtedPropertyType == NEGATIVE_VAL)
        {
            return false;
        }
        return true;
    }

    // Adds The New Property When User Posts a New Listing
    private void addNewPropertyToProperties(Property newProperty) // O(1) ?
    {
        int propertyArrayLength = 0;
        if (this.properties != null)
        {
            propertyArrayLength = this.properties.length;
        }
        Property[] newProperties = new Property[propertyArrayLength + 1]; // Creating a New Property Array
        for (int i = 0; i < propertyArrayLength; i++)
        {
            newProperties[i] = this.properties[i];
        }
        newProperties[propertyArrayLength] = newProperty;
        this.properties = newProperties; // This Property-Array Pointing to the New Property-Array
    }
    // ______________________________________________________________________________________________
}
