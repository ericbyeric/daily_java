# Daily Java Content

100 uninterrupted days practicing content about Java

## Day 1 - Generating a random number within a specific range.
- `SecureRandom`
```java
import java.security.SecureRandom;

public final class Day001 {

    public static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static void main(String[] args) {
        System.out.println("Generating a number between 50 and 100...");
        System.out.println(randomNumberBetween(50, 100));
    }

    private static int randomNumberBetween(int minimum, int maximum) {
        return SECURE_RANDOM.nextInt(maximum - minimum) + minimum;
    }

}
```


## Day 2 - Formatting a `LocalDatetime` object.
```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Day002 {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args){
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formatted = currentDateTime.format(FORMATTER);
        System.out.println("formatted = " + formatted);
    }
}
```

## Day 3 - Scheduling a task to run every 2 seconds.
- `ScheduledExecutorService`, `Executors.newSingleThreadScheduledExecutor()`
- `Thread`
```java
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTask2Sec {

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    public static void main(String[] args) throws InterruptedException {
        var ScheduleTask2Sec = new ScheduleTask2Sec();
        ScheduleTask2Sec.printCurrentTimeEvery2Second();
        Thread.sleep(15_000);
        ScheduleTask2Sec.stopPrinting();
    }

    private void printCurrentTimeEvery2Second() {
        Runnable task = () -> System.out.println(LocalTime.now());
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
    }
    private void stopPrinting() {
        scheduledExecutorService.shutdown();
    }
}
```
