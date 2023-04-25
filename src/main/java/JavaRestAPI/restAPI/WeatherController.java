package JavaRestAPI.restAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import JavaRestAPI.restAPI.Logging.LogModels.LogModel;
import JavaRestAPI.restAPI.Logging.MemoryLogUtil;
import JavaRestAPI.restAPI.Models.Icons;
import JavaRestAPI.restAPI.Models.Weather;
import JavaRestAPI.restAPI.Models.Week;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {
    private static String current;
    private static String high;
    private static String low;
    private static String icon;
    private static String condition;
    

    @GetMapping("/weather")
    public Weather weather(@RequestParam(value = "loc") String city){
        try {
            Document html = Jsoup.connect("https://www.google.com/search?q=" + city + "%20weather").get();
            Elements today = html.select("#wob_wc");
            city = html.select(".BBwThe").text().replace("Pressure:", "");
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

    @GetMapping("/weather/week")
    public List<Week> week(@RequestParam(value = "loc", required = false) String city) {
        List<Week> weekList = new ArrayList<Week>();
        try {
            Document html = Jsoup.connect("https://www.google.com/search?q=" + city + "%20weather").get();
            Elements week = html.select(".wob_df");
            int i = -1;
            for (Element element: week) {
                i = i + 1;
                String condition = week.get(i).select("img[src]").attr("alt");
                String icon = Icons.valueOf(condition.replace(" ", "")).label;
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

    @GetMapping("/weather/ip")
    public HashMap<String, LogModel> ip() {
        return MemoryLogUtil.ipMap;
    }

    @PostMapping("/weather/deploy")
    public String triggerDeploy(@RequestBody String code) throws InterruptedException, IOException {
        if (!code.equals(System.getenv("deploy_auth_code"))) {
            return "Incorrect auth code";
        }
        ProcessBuilder pb = new ProcessBuilder("/home/server/code/scripts/pull_and_deploy.sh");
        Process process = pb.start();
        return "Deploying Completed";
    }

}
