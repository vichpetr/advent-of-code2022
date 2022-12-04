package eu.petrvich.adventofcode2022.adventofcode2022java.day4;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
public class Day4Service {

    @PostConstruct
    public void cleaningService() throws IOException {
        log.info("=================================== Day 4 - part 1 ===========================");

        int countOfOverlapAll = 0;
        int countOfOverlapPartiallyOrAll = 0;

        final List<String> input = Files.readAllLines(Paths.get("input-d4.txt"));
        for (String row : input) {
            final String[] elements = row.split(",");

            final Pair firstPair = new Pair(elements[0]);
            final Pair secondPair = new Pair(elements[1]);

            final boolean firstOverlapSecondAtAll = firstPair.isOverlapAtAll(secondPair);
            final boolean firstOverlapSecondPartially = firstPair.isOverlapPartially(secondPair);
            final boolean secondOverlapFirstAtAll = secondPair.isOverlapAtAll(firstPair);
            final boolean secondOverlapFirstPartially = secondPair.isOverlapPartially(firstPair);

            if (firstOverlapSecondAtAll || secondOverlapFirstAtAll || firstOverlapSecondPartially || secondOverlapFirstPartially) {
                if (firstOverlapSecondAtAll || secondOverlapFirstAtAll) {
                    countOfOverlapAll++;
                }

                countOfOverlapPartiallyOrAll++;
            }

            log.info("row [{}], firstOverlapSecondAtAll: [{}], firstOverlapSecondPartially: [{}], secondOverlapFirstAtAll: [{}], secondOverlapFirstPartially: [{}]", row, firstOverlapSecondAtAll, firstOverlapSecondPartially, secondOverlapFirstAtAll, secondOverlapFirstPartially);

        }

        log.info("sum of countOfOverlapAll is {}", countOfOverlapAll);
        log.info("sum of countOfOverlapPartiallyOrAll is {}", countOfOverlapPartiallyOrAll);

        log.info("=================================== Day 4 End - part 1 ===========================");
    }
}
