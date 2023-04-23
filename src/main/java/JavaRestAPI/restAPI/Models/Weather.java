package JavaRestAPI.restAPI.Models;

public class Weather {
    public final String city;
    public final String current;
    public final String high;
    public final String low;
    public final String icon;
    public final String condition;
 
    public Weather(String city, String current, String high, String low, String icon, String condition) {
        this.city = city;
        this.current = current;
        this.high = high;
        this.low = low;
        this.icon = icon;
        this.condition = condition;

    }

    public String getCity() {
        return city;
    }

    public String getCurrent() {
        return current;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }
    public String getIcon() {
        return icon;
    }
    public String getCondition() {
        return condition;
    }


}
