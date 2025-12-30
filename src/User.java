public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean isBroker;

    public User(String userName, String password, String phoneNumber, boolean isBroker) // O(1)
    {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isBroker = isBroker;
    }

    public String toString() // O(1)
    {
        String userNameString = "Username: " + this.userName;
        String phoneNumberString = "Phone Number: " + this.phoneNumber;
        String userStr = userNameString + " , " + phoneNumberString;
        if (this.isBroker)
        {
            userStr += " (Broker)";
        }

        return createOutlineForUserStr(userStr);
    }

    public String getUserName() // O(1)
    {
        return this.userName;
    }

    public boolean isUserNameEquals(String userName) // O(1)
    {
        return this.userName.equals(userName);
    }

    public boolean isPasswordEquals(String password)
    {
        return this.password.equals(password);
    }

    private String createOutlineForUserStr(String userStr)
    {
        // Initialize outputStr
        String outputStr = "";

        // Top Outline
        outputStr += "+";
        for (int i = 0; i < userStr.length(); i++)
        {
            outputStr += "-";
        }
        outputStr += "+\n";

        // User Details and Edges
        outputStr += "|";
        outputStr += userStr;
        outputStr += "|\n";

        // Bottom Outline
        outputStr += "+";
        for (int i = 0; i < userStr.length(); i++)
        {
            outputStr += "-";
        }
        outputStr += "+\n";

        // Output
        return outputStr;
    }
}
