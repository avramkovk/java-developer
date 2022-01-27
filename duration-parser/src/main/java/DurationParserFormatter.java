import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurationParserFormatter implements DurationParser, DurationFormatter {
    private static final int MILLISECOND = 1;
    private static final int MILLISECOND_IN_SECOND = 1000;
    private static final int MILLISECOND_IN_MINUTE = 60000;
    private static final int MILLISECOND_IN_HOUR = 3600000;
    private static final int MILLISECOND_IN_DAY = 86400000;
    private static final int MILLISECOND_IN_WEEK = 604800000;

    @Override
    public Duration parse(String durationStr) {

        Pattern validExpression = Pattern.compile("^([\\d]+w)?([\\d]+d)?([\\d]+h)?([\\d]+m)?([\\d]+s)?([\\d]+ms)?$");
        Matcher matcher = validExpression.matcher(durationStr);
        String correctExpression = "";
        if (durationStr.isEmpty()) {
            return Duration.ofMillis(0);
        }
        if (matcher.find()) {
            correctExpression = matcher.group();
        } else {
            throw new IllegalArgumentException("incorrect expression");
        }

        long milliSecond = 0;
        long second = 0;
        long minute = 0;
        long hour = 0;
        long day = 0;
        long week = 0;
        Pattern patternMilliSecond = Pattern.compile("[\\d]+ms");
        Pattern patternSecond = Pattern.compile("[\\d]+s");
        Pattern patternMinute = Pattern.compile("[\\d]+m[^s][\\d]*|[\\d]+[m]$");
        Pattern patternHour = Pattern.compile("[\\d]+h");
        Pattern patternDay = Pattern.compile("[\\d]+d");
        Pattern patternWeek = Pattern.compile("[\\d]+w");

        Matcher matcherMilliSecond = patternMilliSecond.matcher(correctExpression);
        Matcher matcherSecond = patternSecond.matcher(correctExpression);
        Matcher matcherMinute = patternMinute.matcher(correctExpression);
        Matcher matcherHour = patternHour.matcher(correctExpression);
        Matcher matcherDay = patternDay.matcher(correctExpression);
        Matcher matcherWeek = patternWeek.matcher(correctExpression);

        if (matcherMilliSecond.find()) {
            String result = matcherMilliSecond.group();
            milliSecond = Integer.parseInt(result.split("\\D+")[0]);
        }
        if (matcherSecond.find()) {
            String result = matcherSecond.group();
            second = Integer.parseInt(result.split("\\D+")[0]);
        }
        if (matcherMinute.find()) {
            String result = matcherMinute.group();
            minute = Integer.parseInt(result.split("\\D+")[0]);
        }
        if (matcherHour.find()) {
            String result = matcherHour.group();
            hour = Integer.parseInt(result.split("\\D+")[0]);
        }
        if (matcherDay.find()) {
            String result = matcherDay.group();
            day = Integer.parseInt(result.split("\\D+")[0]);
        }
        if (matcherWeek.find()) {
            String result = matcherWeek.group();
            week = Integer.parseInt(result.split("\\D+")[0]);
        }

        long totalMilliSeconds = milliSecond * MILLISECOND
                + second * MILLISECOND_IN_SECOND
                + minute * MILLISECOND_IN_MINUTE
                + hour * MILLISECOND_IN_HOUR
                + day * MILLISECOND_IN_DAY
                + week * MILLISECOND_IN_WEEK;
        return Duration.ofMillis(totalMilliSeconds);
    }

    @Override
    public String format(Duration duration) {
        String[] units = {"w", "d", "h", "m", "s", "ms"};
        long[] unitsData = {MILLISECOND_IN_WEEK, MILLISECOND_IN_DAY, MILLISECOND_IN_HOUR, MILLISECOND_IN_MINUTE, MILLISECOND_IN_SECOND, MILLISECOND};

        StringBuilder result = new StringBuilder();
        long milliSeconds = duration.toMillis();

        if (milliSeconds == 0) {
            return "0ms";
        }

        for (int i = 0; i < unitsData.length; i++) {
            long quantityUnits = milliSeconds / unitsData[i];
            for (int j = 0; j < units.length; j++) {
                if (i == j && milliSeconds > 0) {
                    if (quantityUnits > 0) {
                        result.append(quantityUnits).append(units[i]);
                        milliSeconds = milliSeconds - quantityUnits * unitsData[i];
                    }
                }
            }
        }
        return result.toString();
    }
}