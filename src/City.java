public class City {
    private String name;
    private String district;
    private String[] streets;

    public City (String name, String district)
    {
        this.name = name;
        this.district = district;

        // Adding 3 Streets [A,B,C] to Test
        this.streets = new String[]{"A", "B", "C"};
    }

    public String toString()
    {
        return this.name + " , " + this.district;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean isCityNameEqual(String name)
    {
        return this.name.equalsIgnoreCase(name);
    }

    public String[] getStreets()
    {
        return this.streets;
    }
}
