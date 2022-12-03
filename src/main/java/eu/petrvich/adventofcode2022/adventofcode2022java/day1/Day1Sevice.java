package eu.petrvich.adventofcode2022.adventofcode2022java.day1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//@Service
public class Day1Sevice {

    //@PostConstruct
    public void calculateCalories() throws IOException {

        final List<String> input = Files.readAllLines(Paths.get( "input-d1.txt"));

        Map<List<String>, Integer> inputs = new HashMap<>();
        int tmpCalculation = 0;
        List<String> tmpList = new ArrayList<>();
        for (String row : input) {
            if("".equals(row)) {
                if(!tmpList.isEmpty()) {
                    inputs.put(tmpList, tmpCalculation);
                }
                tmpCalculation = 0;
                tmpList = new ArrayList<>();
                continue;
            }

            tmpCalculation += Integer.parseInt(row);
            tmpList.add(row);
        }

        final List<Integer> values = new ArrayList<>(inputs.values().stream().toList());
        Collections.sort(values);

        int max = values.get(values.size()-1);
        int secondMax = values.get(values.size()-2);
        int thirdMax = values.get(values.size()-3);

        int sum = max+secondMax+thirdMax;
        log.info("1. max value is {}", max);
        log.info("2. max value is {}", secondMax);
        log.info("3. max value is {}", thirdMax);
        log.info("sum value is {}", sum);
    }
}
