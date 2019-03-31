package android.day6.lab2.showcountries.model;

public class Country {
    private String rank;
    private String country;
    private String population;
    private String flag;

    public Country() {

    }

    public Country(String rank, String country, String population, String flag) {
        this.rank = rank;
        this.country = country;
        this.population = population;
        this.flag = flag;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
