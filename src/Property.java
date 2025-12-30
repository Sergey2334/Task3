public class Property {
    private String city;
    private String street;
    private int roomCount;
    private int price;
    private int type;
    private boolean isForRent;
    private int propertyNumber;
    private int floor;
    private User userPoster;

    public Property(String city, String street, int roomCount, int price, int type, boolean isForRent, int propertyNumber, int floor)
    {
        this.city = city;
        this.street = street;
        this.roomCount = roomCount;
        this.price = price;
        this.type = type;
        this.isForRent = isForRent;
        this.propertyNumber = propertyNumber;
        this.floor = floor;
    }

    public boolean isUserEqualsToUserPoster(User user)
    {
        return this.userPoster == user;
    }
}
