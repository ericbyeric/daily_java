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
public class Day003 {
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    public static void main(String[] args) throws InterruptedException {
        var day003 = new Day003();
        day003.printCurrentTimeEvery2Second();
        Thread.sleep(15_000);
        day003.stopPrinting();
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

## Day 4 - Removing items from a List.
- `equals()`
- lambda
```java
public class Day004 {
    public static void main(String[] args) {
        List<Person> beatles = new ArrayList<>();
        beatles.add(new Person("1", "John Lennon", LocalDate.of(1940, 10, 9)));
        beatles.add(new Person("2", "Paul McCartney", LocalDate.of(1942, 6, 18)));
        beatles.add(new Person("3", "George Harrison", LocalDate.of(1943, 2, 25)));
        beatles.add(new Person("4", "Ringo Starr", LocalDate.of(1940, 7, 7)));

        removeitemUsingEquals(beatles);

        removeItemUsingSpecificFilter(beatles);

        beatles.forEach(System.out::println);
    }

    private static void removeitemUsingEquals(List<Person> beatles) {
        beatles.removeIf(p -> "George Harrison".equals(p.getName()));
    }

    private static void removeItemUsingSpecificFilter(List<Person> beatles) {
        Person johnLennon = new Person("1", "John Lennon", LocalDate.of(1940, 10, 9));
        beatles.remove(johnLennon);
    }
    static class Person {
        private final String id;
        private final String name;

        private final LocalDate dateOfBirth;

        public Person(String id, String name, LocalDate dateOfBirth) {
            this.id = id;
            this.name = name;
            this.dateOfBirth = dateOfBirth;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj){
                return true;
            }
            if (obj == null || getClass() != obj.getClass()){
                return false;
            }
            var person = (Person) obj;
            return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(dateOfBirth, person.dateOfBirth);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, dateOfBirth);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
```

## Day 5 - Creating a list with filtered items from other lists
- What to do: Concatenate all the musicians and filter guitar players only
- `record`
- Stream
  - 중간 연산: `Stream.concat()`, `filter()`, `map()`
  - 최종 연산: `collect()`
```java
public class Day005 {
    private static final String GUITAR = "Guitar";
    private static final String DRUMS = "Drums";
    private static final String BASS = "Bass";
    private static final String VOCALS = "Vocals";
    private static final String KEYBOARDS = "Keyboards";
    public static void main(String[] args) {
        List<BandMember> pinkFloyd = List.of(
                new BandMember("David Gilmour", GUITAR),
                new BandMember("Roger Waters", BASS),
                new BandMember("Richard Wright", KEYBOARDS),
                new BandMember("Nick Mason", DRUMS)
        );

        List<BandMember> ironMaiden = List.of(
                new BandMember("Bruce Dickinson", VOCALS),
                new BandMember("Steve Harris", BASS),
                new BandMember("Adrian Smith", GUITAR),
                new BandMember("Dave Murray", GUITAR),
                new BandMember("Nicko McBrain", DRUMS)
        );

        List<BandMember> blackSabbath = List.of(
                new BandMember("Ozzy Osbourne", VOCALS),
                new BandMember("Geezer Butler", BASS),
                new BandMember("Toni Iommi", GUITAR),
                new BandMember("Bill Ward", DRUMS)
        );

        // All musicians
        Stream<BandMember> allMusicians = Stream.concat(Stream.concat(pinkFloyd.stream(), ironMaiden.stream()), blackSabbath.stream());

        // Guitar players
        List<String> guitarPlayers = allMusicians.filter(member -> GUITAR.equals(member.instrument))
                .map(BandMember::name)
                .collect(Collectors.toList());

        System.out.println(guitarPlayers);
    }
    static record BandMember(String name, String instrument){}
}

```
