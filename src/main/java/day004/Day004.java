package day004;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
