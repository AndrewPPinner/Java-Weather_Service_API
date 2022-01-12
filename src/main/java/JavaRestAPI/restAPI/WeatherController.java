package JavaRestAPI.restAPI;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    private static String current;
    private static String high;
    private static String low;
    private static String icon;
    private static String condition;
    

    @GetMapping("/weather")
    public Weather weather(@RequestParam(value = "city") String city){
        try {
            Document html = Jsoup.connect("https://www.google.com/search?q=" + city + "weather").get();
            Elements today = html.select("#wob_wc");
            city = today.select("#wob_loc").text();
            current = today.select("#wob_tm").text();
            high = today.select("[style='display:inline']").get(2).text();
            low = today.select("[style='display:inline']").get(3).text();
            icon = today.select(".wob_tci").attr("src");
            condition =  today.select(".wob_tci").attr("alt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        


        return new Weather(city, current, high, low, icon, condition);
    }

}
