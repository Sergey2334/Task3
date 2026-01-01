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

    // Adding ID Would Be Simpler :'( , If No I'm Comparing Memory Addresses
    // private int id;

    public Property(String city, String street, int roomCount, int price, int type, boolean isForRent, int propertyNumber, int floor,  User userPoster)
    {
        this.city = city;
        this.street = street;
        this.roomCount = roomCount;
        this.price = price;
        this.type = type;
        this.isForRent = isForRent;
        this.propertyNumber = propertyNumber;
        this.floor = floor;
        this.userPoster = userPoster;
    }

    // Copy Of A Property - Creates A Clone , Points To A Different Address (Need This To Pass By Value)
    public Property(Property originalProperty)
    {
        this.city = originalProperty.city;
        this.street = originalProperty.street;
        this.roomCount = originalProperty.roomCount;
        this.price = originalProperty.price;
        this.type = originalProperty.type;
        this.isForRent = originalProperty.isForRent;
        this.propertyNumber = originalProperty.propertyNumber;
        this.floor = originalProperty.floor;
        this.userPoster = originalProperty.userPoster;
    }

    public String toString()
    {
        // First Line Of Format
        String addressStr = "\t" + this.city + " - " + this.street + ".\n";

        // Second Line Of Format
        String typeStr = "\t";
        final int TYPE_1_VALUE = 1;
        final int TYPE_2_VALUE = 2;
        final int TYPE_3_VALUE = 3;
        final String TYPE_1_STR = "Apartment";
        final String TYPE_2_STR = "Penthouse";
        final String TYPE_3_STR = "House";
        if (this.type == TYPE_1_VALUE)
        {
            typeStr += TYPE_1_STR;
        }
        else if (this.type == TYPE_2_VALUE)
        {
            typeStr += TYPE_2_STR;
        }
        else if (this.type == TYPE_3_VALUE)
        {
            typeStr += TYPE_3_STR;
        }
        typeStr += " - ";
        final String IS_FOR_RENT_STR = "For Rent";
        final String IS_FOR_SALE_STR = "For Sale";
        if (this.isForRent)
        {
            typeStr += IS_FOR_RENT_STR;
        }
        else
        {
            typeStr += IS_FOR_SALE_STR;
        }
        typeStr += ": " + this.roomCount + " rooms";
        if (this.floor > 0)
        {
            typeStr += ", floor " + this.floor;
        }
        typeStr += ".\n";

        // Third Line Of Format
        String priceStr = "\tPrice: " + this.price + "$.\n";

        // Forth Line Of Format
        String contactStr = "\tContact info: " + this.userPoster.getUserName() + " " + this.userPoster.getUserPhoneNumber();
        final String IS_BROKER_STR = " (real estate broker)";
        if (this.userPoster.isBroker())
        {
            contactStr += IS_BROKER_STR;
        }
        contactStr += ".\n";

        return addressStr + typeStr + priceStr + contactStr;
    }

    public boolean isUserEqualsToUserPoster(User user)
    {
        return this.userPoster == user;
    }

    public boolean getIsForRent()
    {
        return this.isForRent;
    }

    private final int NO_FILTER_VAL = -999;
    public boolean isPropertyTypeEquals(int type)
    {
        if (this.type == NO_FILTER_VAL)
        {
            return true;
        }
        return this.type == type;
    }

    public boolean isRoomCountEquals(int roomCount)
    {
        if (this.roomCount == NO_FILTER_VAL)
        {
            return true;
        }
        return this.roomCount == roomCount;
    }

    public boolean isPriceInRange(int minPrice, int maxPrice)
    {
        if (this.price == NO_FILTER_VAL)
        {
            return true;
        }
        return this.price >= minPrice && this.price <= maxPrice;
    }
}
