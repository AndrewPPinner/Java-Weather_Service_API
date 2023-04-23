package JavaRestAPI.restAPI.Logging;

import JavaRestAPI.restAPI.Logging.LogModels.LogModel;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class MemoryLogUtil {
    public static final HashMap<String, LogModel> ipMap = new HashMap<>();

    public static void Log(String ip){
        LogModel model = ipMap.getOrDefault(ip, new LogModel(LocalDate.now()));
        if (model.requestDate.equals(LocalDate.now())){
            model.requestCount += 1;
        } else {
            model.requestDate = LocalDate.now();
            model.requestCount = 1;
        }
        ipMap.put(ip, model);
    }

    public static boolean IsOverused(String ip){
        LogModel model = ipMap.get(ip);
        return model != null && model.requestCount > 50;
    }
}
