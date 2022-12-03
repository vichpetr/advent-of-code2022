package eu.petrvich.adventofcode2022.adventofcode2022java.day3;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class Day3Service {

    private static <T> List<T> findCommonElements(List<T> first, List<T> second) {
        Set<T> common = new HashSet<>(first);
        common.retainAll(second);
        return common.stream().toList();
    }

    @PostConstruct
    public void countPriorityPart1() throws IOException {
        log.info("=================================== Day 3 - part 1 ===========================");

        final List<String> input = Files.readAllLines(Paths.get("input-d3.txt"));

        int totalPriority = 0;
        for (String row : input) {
            final String part1 = row.substring(0, (row.length() / 2));
            final String part2 = row.substring(row.length() / 2);

            assert part1.length() == part2.length();

            final List<String> list1 = new java.util.ArrayList<>(List.of(part1.split("")));
            final List<String> list2 = List.of(part2.split(""));

            final List<String> commonElements = findCommonElements(list1, list2);
            log.debug("input [{}], part1 [{}] with size {}, part2 [{}] with size {}, same elem [{}]", row, part1, part1.length(), part2, part2.length(), commonElements);

            final int priority;
            final String commonElem = commonElements.get(0);
            final char charElem = commonElem.charAt(0);
            if (Character.isLowerCase(charElem)) {
                priority = charElem - 96;
            } else {
                priority = charElem - 38;
            }
            totalPriority += priority;
            log.info("priority of [{}] is {}", commonElem, priority);
        }

        log.info("total priority {}", totalPriority);
        log.info("=================================== Day 3 end - part 1 ===========================");
    }

    @PostConstruct
    public void countPriorityPart2() throws IOException {
        log.info("=================================== Day 3 - part 2 ===========================");

        final List<String> input = Files.readAllLines(Paths.get("input-d3.txt"));

        int totalPriority = 0;

        for (int i = 0; i < input.size(); i += 3) {
            String line1 = input.get(i);
            String line2 = input.get(i + 1);
            String line3 = input.get(i + 2);


            log.info("line1 [{}], line2 [{}], line3 [{}]", line1, line2, line3);

            final int priority;

            final char charElem = commonCharInString(line1, line2, line3);
            if (Character.isLowerCase(charElem)) {
                priority = charElem - 96;
            } else {
                priority = charElem - 38;
            }
            totalPriority += priority;
            log.info("priority of [{}] is {}", charElem, priority);
        }

        log.info("total priority {}", totalPriority);
        log.info("=================================== Day 3 end - part 2 ===========================");
    }

    private char commonCharInString(String line1, String line2, String line3) {
        final List<String> chars1 = List.of(line1.split(""));
        final List<String> chars2 = List.of(line2.split(""));
        final List<String> chars3 = List.of(line3.split(""));

        final List<String> commonElements = findCommonElements(chars1, chars2);
        final List<String> finalMatch = findCommonElements(commonElements, chars3);

        return finalMatch.get(0).charAt(0);
    }
}
