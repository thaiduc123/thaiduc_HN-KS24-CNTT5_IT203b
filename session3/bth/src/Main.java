import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> nameOfStudents = new ArrayList<>();
        nameOfStudents.add("THD");
        nameOfStudents.add("THD2");
        nameOfStudents.add("THD3");
        nameOfStudents.add("THD4");
        nameOfStudents.add("THD5");
        System.out.println(nameOfStudents);
        Predicate<String> getName = (name) -> name.startsWith("Nguyễn");
        Function<String,String> convertName = (name) -> name + "bruh bruh lmao";
        nameOfStudents.stream()
                .filter(getName)
                .map((name)->{
                    String[] fullName = name.split(" ");
                    return fullName[fullName.length - 1];
                })
              //  .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toSet());
        System.out.println();
    }
}