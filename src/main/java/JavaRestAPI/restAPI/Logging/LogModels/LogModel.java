package JavaRestAPI.restAPI.Logging.LogModels;

import java.time.LocalDate;
import java.util.Date;

public class LogModel {
    public LocalDate requestDate;
    public Integer requestCount = 0;

    public LogModel(LocalDate date){
        requestDate = date;
    }
}
