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

    public String toString()
    {
        String userNameString = "Username: " + this.userName;
        String phoneNumberString = "Phone Number: " + this.phoneNumber;
        String outputStr = userNameString + "\n" + phoneNumberString;
        if (this.isBroker)
        {
            return outputStr += "\nBroker";
        }
        return outputStr;
    }

    public String getUserName() // O(1)
    {
        return this.userName;
    }

    public boolean isUserNameEquals(String userName) // O(1)
    {
        return this.userName.equals(userName);
    }
}
