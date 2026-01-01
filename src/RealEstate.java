import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private City[] cities;

    public RealEstate() {
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

        // Test Users, Its Annoying To Sign Up And Login Everytime :'(
        User user1 = new User("John", "1" , "0501234567", true);
        User user2 = new User("Bob", "1" , "0521234569", false);
        User user3 = new User("Leo", "1" , "0554563216", false);
        this.users = new User[]{user1, user2, user3};
    }

    public User[] getUsers() {
        if (this.users == null) {
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
        String userPhoneNumber = createValidUserPhone();
        boolean isBroker = isUserABroker();

        User newUser = new User(userName, userPassword, userPhoneNumber, isBroker);
        addNewUserToUsers(newUser);
    }

    private String createValidUserName() // O(n) ?
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter username: ");
        String userName = input.nextLine();
        while (isUserNameTaken(userName) || userName.isEmpty())
        {
            if (userName.isEmpty())
            {
                System.out.println("Invalid Username!");
            }
            else
            {
                System.out.println("Username already taken!");
            }
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
            for (int i = 0; i < this.users.length; i++) {
                if (this.users[i].isUserNameEquals(userName)) {
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
        while (!isUserPasswordValid(userPassword)) {
            System.out.println("Invalid password!");
            System.out.print("Enter password: ");
            userPassword = input.nextLine();

        }
        return userPassword;
    }

    private boolean isUserPasswordValid(String password) // O(1)
    {
        final int MIN_PASSWORD_LENGTH = 5;

        if (password.length() < MIN_PASSWORD_LENGTH) {
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
        while (!isUserPhoneValid(userPhone)) {
            System.out.println("Invalid phone number!");
            System.out.print("Enter phone number: ");
            userPhone = input.nextLine();

        }
        return userPhone;
    }

    private boolean isUserPhoneValid(String phoneNumber) // O(1)
    {
        final int PHONE_NUMBER_LENGTH = 10;

        if (phoneNumber.length() != PHONE_NUMBER_LENGTH) {
            return false;
        }

        if (!phoneNumber.startsWith("05")) {
            return false;
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

        while (inputtedVal != REGULAR_USER_VAL && inputtedVal != BROKER_USER_VAL) {
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
        if (this.users != null) {
            usersArrayLength = this.users.length;
        }
        User[] newUsers = new User[usersArrayLength + 1]; // Creating a New Users Array
        for (int i = 0; i < usersArrayLength; i++) {
            newUsers[i] = this.users[i];
        }
        newUsers[usersArrayLength] = newUser;
        this.users = newUsers; // This Users-Array Pointing to the New Users-Array
    }
    // ______________________________________________________________________________________________


    // LOGIN LOGIC
    // ______________________________________________________________________________________________
    public User login() {
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
        for (int i = 0; i < usersArray.length; i++) {
            if (usersArray[i].isUserNameEquals(inputtedUserName)) {
                targetUser = usersArray[i];
                break;
            }
        }

        if (targetUser == null) {
            System.out.println("Invalid User!");
            return null;
        }


        if (!targetUser.isPasswordEquals(inputtedUserPassword)) {
            System.out.println("Wrong Password!");
            return null;
        }

        return targetUser;
    }
    // ______________________________________________________________________________________________

    // USER MENU LOGIC
    // ______________________________________________________________________________________________
    // Post New Property Logic
    // ________________________________________________
    public boolean postNewProperty(User user) {
        // If User Has Reached Max Posts
        if (!isUserAllowedToPost(user)) {
            return false;
        }

        // List all cities
        City[] citiesArray = this.cities;
        System.out.println("\nCities List:");
        for (int i = 0; i < citiesArray.length; i++) {
            System.out.println(citiesArray[i]);
        }

        // Get Valid City Name
        City chosenCity = getValidCityFromCityName();
        if (chosenCity == null) {
            System.out.println("Invalid City!");
            return false;
        }
        String chosenCityName = chosenCity.getName();

        // List All Streets of Chosen City
        String[] chosenCityStreets = chosenCity.getStreets();
        for (int i = 0; i < chosenCityStreets.length; i++) {
            System.out.println(chosenCityStreets[i]);
        }

        // Get Valid Street Name
        String chosenStreetName = getValidStreetName(chosenCityStreets);
        if (chosenStreetName == null) {
            System.out.println("Invalid Street!");
            return false;
        }

        // Get Property Type
        final int ERROR_VAL = -999;
        int chosenPropertyType = getPropertyTypeVal();
        if (chosenPropertyType == ERROR_VAL) {
            System.out.println("Invalid Property Type!");
            return false;
        }

        Scanner input = new Scanner(System.in);

        // Get Property Floor
        final int APARTMENT_TYPE_VAL = 1;
        int chosenFloor = 0;
        if (chosenPropertyType == APARTMENT_TYPE_VAL) {
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
        int chosenIsPropertyForRentVal = isPropertyForRentVal();
        final int POSITIVE_IS_FOR_RENT_VAL = 1;
        boolean chosenIsPropertyForRent = false;
        if (chosenIsPropertyForRentVal == POSITIVE_IS_FOR_RENT_VAL)
        {
            chosenIsPropertyForRent = true;
        }

        // Get Property Price
        System.out.print("Enter Property Price: ");
        int chosenPropertyPrice = input.nextInt();

        // Create New Property Object
        Property newProperty = new Property(chosenCityName,
                chosenStreetName,
                chosenPropertyRoomsCount,
                chosenPropertyPrice,
                chosenPropertyType,
                chosenIsPropertyForRent,
                chosenPropertyNumber,
                chosenFloor,
                user);

        // Add New Property Object To Property Array
        addNewPropertyToProperties(newProperty);

        // New Property Listing Was Successful
        return true;
    }

    private boolean isUserAllowedToPost(User user) {
        // Broker - Max 5
        // Regular - Max 2
        final int BROKER_MAX_POSTS = 5;
        final int REGULAR_MAX_POSTS = 2;

        int userMaxPropertyPostsAllowed = 0;
        if (user.isBroker()) {
            userMaxPropertyPostsAllowed = BROKER_MAX_POSTS;
        } else {
            userMaxPropertyPostsAllowed = REGULAR_MAX_POSTS;
        }

        // Check if User can Post
        Property[] propertiesArray = this.properties;
        int userPostCount = 0;
        if (propertiesArray != null) {
            for (int i = 0; i < propertiesArray.length; i++) {
                if (propertiesArray[i].isUserEqualsToUserPoster(user)) {
                    userPostCount++;
                }
            }
        }
        if (userPostCount >= userMaxPropertyPostsAllowed) {
            System.out.printf("Max Posts Reached! (%d)\n", userMaxPropertyPostsAllowed);
            return false;
        }

        return true;
    }

    private City getValidCityFromCityName() {
        City[] citiesArray = this.cities;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a City Name :");
        String inputtedCityName = input.nextLine();

        City foundCity = null;
        for (int i = 0; i < citiesArray.length; i++) {
            if (citiesArray[i].isCityNameEqual(inputtedCityName)) {
                foundCity = citiesArray[i];
                break;
            }
        }
        return foundCity;
    }

    private String getValidStreetName(String[] streetNames) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a Street Name :");
        String inputtedStreetName = input.nextLine();

        for (int i = 0; i < streetNames.length; i++) {
            if (streetNames[i].equalsIgnoreCase(inputtedStreetName)) {
                return streetNames[i];
            }
        }
        return null;
    }

    private int getPropertyTypeVal() {
        Scanner input = new Scanner(System.in);

        final int APARTMENT_VAL = 1;
        final int PENTHOUSE_VAL = 2;
        final int HOUSE_VAL = 3;
        final int ERROR_VAL = -999;

        System.out.println("Enter a Property Type :");
        System.out.printf("\t%d.Apartment (Regular Apartment in a Building)\n", APARTMENT_VAL);
        System.out.printf("\t%d.Penthouse (Penthouse Apartment in a Building)\n", PENTHOUSE_VAL);
        System.out.printf("\t%d.House (Private House)\n", HOUSE_VAL);
        System.out.print("Enter a Property Type (1 - 3) : ");
        int inputtedPropertyType = input.nextInt();

        if (inputtedPropertyType < 1 || inputtedPropertyType > 3) {
            return ERROR_VAL;
        }
        return inputtedPropertyType;
    }

    public int isPropertyForRentVal()
    {
        Scanner input = new Scanner(System.in);
        final int NEGATIVE_VAL = 0;
        final int POSITIVE_VAL = 1;

        final int ERROR_VAL = -999;

        System.out.println("Is The Property For Rent ? : ");
        int inputtedPropertyType = -1;
        do {
            System.out.printf("%d (No) , %d (Yes): ", NEGATIVE_VAL, POSITIVE_VAL);
            inputtedPropertyType = input.nextInt();
        }
        while ((inputtedPropertyType != NEGATIVE_VAL && inputtedPropertyType != POSITIVE_VAL) && inputtedPropertyType != ERROR_VAL);

        if (inputtedPropertyType == NEGATIVE_VAL)
        {
            return NEGATIVE_VAL;
        }
        else if (inputtedPropertyType == POSITIVE_VAL)
        {
            return POSITIVE_VAL;
        }
        return ERROR_VAL;
    }

    // Adds The New Property When User Posts a New Listing
    private void addNewPropertyToProperties(Property newProperty) // O(1) ?
    {
        int propertyArrayLength = 0;
        if (this.properties != null) {
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
    // ________________________________________________

    // Remove Property Logic
    // ________________________________________________
    public void removeProperty(User user)
    {
        // Get User Posted Properties
        Property[] userPostedProperties = getUserPostedProperties(user);

        // Check If There Are Any Property Posts
        if (userPostedProperties == null || userPostedProperties.length == 0)
        {
            System.out.println("Nothing to remove");
            return;
        }

        // Print User Posted Properties
        printProperties(user);

        // Get Index Property Post To Remove
        Scanner input = new Scanner(System.in);
        final int FIRST_PROPERTY_INDEX_VAL = 1;
        final int PROPERTY_POST_COUNT_MAX_INDEX_VAL = userPostedProperties.length;
        int indexToRemove = 0;
        boolean errorValueFound = false;
        do
        {
            errorValueFound = false;

            System.out.printf("Enter a Property Number To Remove (%d-%d): ", FIRST_PROPERTY_INDEX_VAL, PROPERTY_POST_COUNT_MAX_INDEX_VAL);
            indexToRemove = input.nextInt();
            if (indexToRemove < FIRST_PROPERTY_INDEX_VAL || indexToRemove > PROPERTY_POST_COUNT_MAX_INDEX_VAL)
            {
                System.out.println("Invalid Input");
                errorValueFound = true;
            }
        }
        while (errorValueFound);
        indexToRemove = indexToRemove - 1; // We Printed The User 1 Based Print

        // Get Target Property To Remove
        Property targetPropertyToRemove = userPostedProperties[indexToRemove];

        // Removing The Target Property From Properties Array, By Skipping It
        Property[] newProperties = new Property[this.properties.length - 1];
        for (int i = 0, newIndex = 0; i < this.properties.length; i++, newIndex++)
        {
            if (properties[i] == targetPropertyToRemove)
            {
                newIndex--;
                continue;
            }
            newProperties[newIndex] = this.properties[i];
        }

        // Pointing The New Properties Array
        this.properties = newProperties;
        System.out.println("Property Removed Successfully");
    }
    // ________________________________________________

    // Print All Property Posts Logic
    // ________________________________________________
    public void printAllProperties()
    {
        Property[] propertiesArray = this.properties;
        if (propertiesArray == null || propertiesArray.length == 0)
        {
            System.out.println("No Property Posts Listed !");
            return;
        }

        for (int i  = 0; i < propertiesArray.length; i++)
        {
            System.out.printf("%d. %s\n", i + 1,  propertiesArray[i].toString());
        }
    }

    // Print User Property Posts Logic
    // ________________________________________________
    public void printProperties(User user)
    {
        // Get Properties Posted By User
        Property[] propertiesArrayPostedByUser = getUserPostedProperties(user);

        // Check If There Are Any Properties Posted By User
        if (propertiesArrayPostedByUser == null || propertiesArrayPostedByUser.length == 0)
        {
            System.out.println("No Property Posts Listed !");
            return;
        }

        // Print The Properties Posted By User Array
        for (int i = 0; i < propertiesArrayPostedByUser.length; i++)
        {
            System.out.printf("%d. %s\n", i + 1,  propertiesArrayPostedByUser[i].toString());
        }
    }

    private Property[] getUserPostedProperties(User user)
    {
        // Get Property Posts Count By User
        int postsCountByUser = 0;
        Property[] propertiesArray = this.properties;

        // Check If There Are Any Properties Posted
        if (propertiesArray == null || propertiesArray.length == 0)
        {
            return null;
        }

        for (int i = 0;  i < propertiesArray.length; i++)
        {
            if (propertiesArray[i].isUserEqualsToUserPoster(user))
            {
                postsCountByUser++;
            }
        }

        // Check If There Are Any Property Posts By User
        if (postsCountByUser == 0)
        {
            return null;
        }

        // Create New Properties Array Only For User Posted Properties
        Property[] propertiesArrayPostedByUser = new Property[postsCountByUser];

        // Insert All Users Properties To New Properties Array
        for (int i = 0, newArrayIndex = 0; i < propertiesArray.length; i++)
        {
            if (propertiesArray[i].isUserEqualsToUserPoster(user))
            {
                propertiesArrayPostedByUser[newArrayIndex] = this.properties[i];
                newArrayIndex++;
            }
        }

        // Return The New Array Of User Posted Properties
        return propertiesArrayPostedByUser;
    }
    // ________________________________________________

    // Search Properties With Parameters Logic
    // ________________________________________________
    public Property[] search()
    {
        // Check If There Are Any Property Posts
        if (this.properties == null || this.properties.length == 0)
        {
            System.out.println("No Property Posts Listed");
            return null;
        }

        Scanner input = new Scanner(System.in);
        final int NO_FILTER_VAL = -999;

        // Get Property Is For Rent
        final int NEGATIVE_IS_FOR_RENT_VAL = 0;
        final int POSITIVE_IS_FOR_RENT_VAL = 1;
        int chosenIsForRentVal = isPropertyForRentVal();
        boolean isForRent = false;
        if (chosenIsForRentVal == POSITIVE_IS_FOR_RENT_VAL)
        {
            isForRent = true;
        }

        // Get Property Type
        final int APARTMENT_VAL = 1;
        final int PENTHOUSE_VAL = 2;
        final int HOUSE_VAL = 3;
        int chosenPropertyType = getPropertyTypeVal();

        // Get Property Rooms Count
        System.out.print("Enter Property Rooms Count: ");
        int chosenRoomCount = input.nextInt();

        // Get Property Price
        int chosenMinPriceRange = 0;
        int chosenMaxPriceRange = 0;
        boolean errorValueFound = false;
        do
        {
            errorValueFound = false;

            System.out.print("Enter Min Property Price: ");
            chosenMinPriceRange = input.nextInt();
            System.out.print("Enter Max Property Price: ");
            chosenMaxPriceRange = input.nextInt();
            if (chosenMaxPriceRange < chosenMinPriceRange && (chosenMinPriceRange != NO_FILTER_VAL &&  chosenMaxPriceRange != NO_FILTER_VAL))
            {
                System.out.println("Invalid Range");
                errorValueFound = true;
            }
        }
        while (errorValueFound);

        // Initialize New Cloned Properties Array
        final Property[] allProperties = this.properties;
        Property[] clonedPropertiesArray = new Property[allProperties.length];
        for (int i = 0 ; i < allProperties.length; i++) // Creates A Clone Of All Properties (To Pass By Value)
        {
            clonedPropertiesArray[i] = new Property(allProperties[i]);
        }

        // Parameters Filtering
        int filteredPropertiesCount = 0;
        for (int i = 0; i < clonedPropertiesArray.length; i++)
        {
            // Check If Exists
            if (clonedPropertiesArray[i] == null)
            {
                continue;
            }

            // Is For Rent Filter
            if (clonedPropertiesArray[i].getIsForRent() != isForRent && chosenIsForRentVal != NO_FILTER_VAL)
            {
                clonedPropertiesArray[i] = null;
                filteredPropertiesCount++;
            }
            // Property Type Filter
            else if (!clonedPropertiesArray[i].isPropertyTypeEquals(chosenPropertyType) && chosenPropertyType != NO_FILTER_VAL)
            {
                clonedPropertiesArray[i] = null;
                filteredPropertiesCount++;
            }
            // Room Count Filter
            else if (!clonedPropertiesArray[i].isRoomCountEquals(chosenRoomCount) && chosenRoomCount != NO_FILTER_VAL)
            {
                clonedPropertiesArray[i] = null;
                filteredPropertiesCount++;
            }
            // Price Filter
            else if (!clonedPropertiesArray[i].isPriceInRange(chosenMinPriceRange, chosenMaxPriceRange)
                                                    && (chosenMinPriceRange != NO_FILTER_VAL || chosenMaxPriceRange != NO_FILTER_VAL))
            {
                clonedPropertiesArray[i] = null;
                filteredPropertiesCount++;
            }
        }

        // Initialize Filtered Property Array
        Property[] filteredPropertiesArray = new Property[clonedPropertiesArray.length - filteredPropertiesCount];

        // Copying Cloned Array To Filtered Array , But Skipping Nulls
        for (int i = 0, newIndex = 0; i < clonedPropertiesArray.length; i++, newIndex++)
        {
            if (clonedPropertiesArray[i] == null)
            {
                newIndex--;
                if (newIndex < 0)
                {
                    newIndex = 0;
                }
                continue;
            }
            filteredPropertiesArray[newIndex] = clonedPropertiesArray[i];
        }

        return filteredPropertiesArray;
    }
    // ________________________________________________
    // ______________________________________________________________________________________________
}
