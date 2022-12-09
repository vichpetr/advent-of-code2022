package eu.petrvich.adventofcode2022.adventofcode2022java.day5;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Slf4j
@Service
public class Day5Service {

    @PostConstruct
    public void turn1() throws IOException {
        log.info("=================================== Day 5 - part 1 ===========================");

        final List<String> input = Files.readAllLines(Paths.get("input-d5.txt"));
        Map<Integer, Stack<String>> stackMap = new HashMap<>();
        List<String> tmpStackRows = new ArrayList<>();
        List<MoveDto> moveDtoList = new ArrayList<>();
        boolean isCoordinates = false;
        for (String row : input) {
            if (!isCoordinates) {
                if("".equals(row)) {
                    isCoordinates = true;
                    continue;
                }
                tmpStackRows.add(row);
            } else {
                final String[] parts = row.split(" ");
                final MoveDto dto = MoveDto.builder()
                        .move(Integer.parseInt(parts[1]))
                        .from(Integer.parseInt(parts[3]))
                        .to(Integer.parseInt(parts[5]))
                        .build();
                moveDtoList.add(dto);
            }
        }

        String rowWithNumber = tmpStackRows.get(tmpStackRows.size() - 1);
        for (String item: rowWithNumber.split(" ")) {
            if("".equals(item)) {
                continue;
            }
            stackMap.put(Integer.parseInt(item), new Stack<>());
        }
        for (int i = tmpStackRows.size() - 2; i >= 0; i--) {
            String item = tmpStackRows.get(i);
            int pointer = 1;
            for (Map.Entry<Integer,Stack<String>> entry : stackMap.entrySet()) {
                if (item.length() < pointer) {
                    continue;
                }
                final char result = item.charAt(pointer);
                final String value = String.valueOf(result);
                if(!" ".equals(value)) {
                    entry.getValue().add(value);
                }
                pointer += 4;
            }
        }
        log.info("rows = {}", stackMap);
        for (MoveDto move : moveDtoList) {
            final Stack<String> fromStack = stackMap.get(move.getFrom());
            final Stack<String> toStack = stackMap.get(move.getTo());
            List<String> tmpList = new ArrayList<>();
            for (int i = 0; i < move.getMove(); i++) {
                final String pop = fromStack.pop();
                tmpList.add(pop);
            }
            Collections.reverse(tmpList);
            toStack.addAll(tmpList);
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer,Stack<String>> entry : stackMap.entrySet()) {
            result.append(entry.getValue().pop());
        }
        log.info("stack result is {}", result.toString());
        log.info("=================================== Day 5 - part 1 END ===========================");
    }
}
