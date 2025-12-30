package Task3Tests;

public class MainTests {
    public static void main(String[] args) {
        String phoneNumber1 = "0501234567"; // Valid
        String phoneNumber2 = "0S01234567"; // Not Valid
        if (phoneNumber2.matches(".*[^0-9].*"))
        {
            System.out.println("Match");
        }
        else
        {
            System.out.println("Not Match");
        }
    }
}
