package eu.petrvich.adventofcode2022.adventofcode2022java.day6;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

@Slf4j
@Service
public class Day6Service {

    @PostConstruct
    public void execute() throws IOException {
        log.info("=================================== Day 6 - part 1 ===========================");
        this.turn1("input-d6-part1.txt",4);
        this.turn1("input-d6-part2.txt",4);
        this.turn1("input-d6-part3.txt",4);
        this.turn1("input-d6-part4.txt",4);
        this.turn1("input-d6.txt",4);
        this.turn1("input-d6.txt",14);
        log.info("=================================== Day 6 - END part 1 ===========================");
    }


    public void turn1(String fileName, int numberOfUnique) throws IOException {


        final String input = Files.readString(Paths.get(fileName));
        Queue<Character> characterStack = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            final char character = input.charAt(i);
            characterStack.add(character);
            if(i >= (numberOfUnique-1)) {
                final HashSet<Character> characters = new HashSet<>(characterStack);
                if(characters.size() == numberOfUnique) {
                    log.info("[{}] first marker after [{}] character {}", fileName, numberOfUnique, i+1);
                    break;
                }
                characterStack.poll();
            }
        }
    }
}
