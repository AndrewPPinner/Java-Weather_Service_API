package JavaRestAPI.restAPI.Models;

public class Week {
    public final String day;
    public final String high;
    public final String low;
    public final String icon;
    public final String condition;
 
    public Week(String day, String high, String low, String icon, String condition) {
        this.day = day;
        this.high = high;
        this.low = low;
        this.icon = icon;
        this.condition = condition;

    }

    public String getDay() {
        return day;
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
