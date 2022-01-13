package JavaRestAPI.restAPI;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeekController {

    @GetMapping("/weather/week")
    public List<Week> week(@RequestParam(value = "loc", required = false) String city) {
        List<Week> weekList = new ArrayList<Week>();
        try {
            Document html = Jsoup.connect("https://www.google.com/search?q=" + city + "%20weather").get();
            Elements week = html.select(".wob_df");
            int i = -1;
            for (Element element: week) {
               i = i + 1;
               String icon = week.get(i).select("img[src]").attr("src");
               String condition = week.get(i).select("img[src]").attr("alt");
               String day = week.get(i).select(".Z1VzSb").text();
               String high = week.get(i).select("[style='display:inline']").first().text();
               String low = week.get(i).select("[style='display:inline']").last().text();
               weekList.add(new Week(day, high, low, icon, condition));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weekList;
    }
}
