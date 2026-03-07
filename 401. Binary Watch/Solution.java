import java.util.ArrayList;
import java.util.List;

public class Solution {
    int[] hours = { 1, 2, 4, 8 };
    int[] minutes = { 1, 2, 4, 8, 16, 32 };

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        readBinaryWatchBacktracking(result, turnedOn, 0, 0, 0, 0);
        return result;
    }

    private void readBinaryWatchBacktracking(List<String> result, int turnedOn, int startHour, int startMinute,
            int currentHour, int currentMinute) {

        if (currentHour > 11 || currentMinute > 59) {
            return;
        }

        if (turnedOn == 0) {
            String hour = String.valueOf(currentHour);
            String minute = String.valueOf(currentMinute);
            if (currentMinute < 10) {
                minute = "0" + minute;
            }
            result.add(hour + ":" + minute);
            return;
        }

        for (int i = startMinute; i < minutes.length; i++) {
            readBinaryWatchBacktracking(result, turnedOn - 1, startHour, i + 1, currentHour,
                    currentMinute + minutes[i]);

        }

        for (int i = startHour; i < hours.length; i++) {
            readBinaryWatchBacktracking(result, turnedOn - 1, i + 1, minutes.length,
                    currentHour + hours[i], currentMinute);
        }
    }
}