package pw2021.backend.Flatly.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DataConverter {
    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
