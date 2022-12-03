package eu.petrvich.adventofcode2022.adventofcode2022java.day2;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static eu.petrvich.adventofcode2022.adventofcode2022java.day2.TurnTypeEnum.PAPER;
import static eu.petrvich.adventofcode2022.adventofcode2022java.day2.TurnTypeEnum.ROCK;
import static eu.petrvich.adventofcode2022.adventofcode2022java.day2.TurnTypeEnum.SCISSORS;

@Slf4j
@Service
public class Day2Service {


    public void rockPaperScissorsPartOne() throws IOException {

        log.info("=================================== Day 2 - part 1 ===========================");

        final List<String> input = Files.readAllLines(Paths.get("input-d2.txt"));

        int myTotalResult = 0;
        int opponentTotalResult = 0;
        log.info("number of rows are {}", input.size());
        for (String row : input) {
            final String[] elements = row.split(" ");

            final TurnTypeEnum opponentTurn = TurnTypeEnum.getValue(elements[0]);
            final TurnTypeEnum myTurn = TurnTypeEnum.getValue(elements[1]);

            final int comparison = compareTo(opponentTurn, myTurn);

            int myTurnResult = myTurn.getValue();
            int opponentTurnResult = opponentTurn.getValue();
            if (comparison == 0) {
                myTurnResult += 3;
                opponentTurnResult += 3;
            } else if (comparison == 1) { // opponent won
                opponentTurnResult += 6;
            } else { // I won
                myTurnResult += 6;
            }

            myTotalResult += myTurnResult;
            opponentTotalResult += opponentTurnResult;
            log.info("row is [{}], opponent [{}], my [{}], comparison [{}], myResult [{},{}], opponent result [{},{}]", row, opponentTurn, myTurn, comparison, myTurnResult, myTotalResult, opponentTurnResult, opponentTotalResult);
        }

        log.info("my result = {}", myTotalResult);
        log.info("opponent result = {}", opponentTotalResult);

        log.info("=================================== Day 2 - part 1 END ===========================");
    }

    @PostConstruct
    public void rockPaperScissorsPartTwo() throws IOException {

        log.info("=================================== Day 2 - part 2 ===========================");

        final List<String> input = Files.readAllLines(Paths.get("input-d2.txt"));

        int myTotalResult = 0;
        int opponentTotalResult = 0;
        log.info("number of rows are {}", input.size());
        for (String row : input) {
            final String[] elements = row.split(" ");

            final TurnTypeEnum opponentTurn = TurnTypeEnum.getValue(elements[0]);
            final TurnResult turnResult = TurnResult.getValue(elements[1]);

            final TurnTypeEnum myTurn = getTurnByResult(opponentTurn, turnResult);

            final int comparison = compareTo(opponentTurn, myTurn);

            int myTurnResult = myTurn.getValue();
            int opponentTurnResult = opponentTurn.getValue();
            if (comparison == 0) {
                myTurnResult += 3;
                opponentTurnResult += 3;
            } else if (comparison == 1) { // opponent won
                opponentTurnResult += 6;
            } else { // I won
                myTurnResult += 6;
            }

            myTotalResult += myTurnResult;
            opponentTotalResult += opponentTurnResult;
            log.info("row is [{}], opponent [{}], my [{}], comparison [{}], myResult [{},{}], opponent result [{},{}]", row, opponentTurn, myTurn, comparison, myTurnResult, myTotalResult, opponentTurnResult, opponentTotalResult);
        }

        log.info("my result = {}", myTotalResult);
        log.info("opponent result = {}", opponentTotalResult);
    }


    private int compareTo(TurnTypeEnum opponent, TurnTypeEnum my) {
        if (opponent.equals(my)) {
            return 0;
        } else if (ROCK.equals(opponent) && SCISSORS.equals(my)) {
            return 1;
        } else if (SCISSORS.equals(opponent) && PAPER.equals(my)) {
            return 1;
        } else if (PAPER.equals(opponent) && ROCK.equals(my)) {
            return 1;
        } else if (ROCK.equals(my) && SCISSORS.equals(opponent)) {
            return -1;
        } else if (SCISSORS.equals(my) && PAPER.equals(opponent)) {
            return -1;
        } else if (PAPER.equals(my) && ROCK.equals(opponent)) {
            return -1;
        }

        throw new UnsupportedOperationException("invalid comparison variant");
    }

    private TurnTypeEnum getTurnByResult(TurnTypeEnum opponentTurn, TurnResult result) {
        switch (result) {
            case DRAW -> {
                return opponentTurn;
            }
            case LOSE -> {
                return calculateLose(opponentTurn);
            }
            case WIN -> {
                return calculateWin(opponentTurn);
            }
        }

        throw new UnsupportedOperationException("invalid comparison variant");
    }

    private TurnTypeEnum calculateLose(TurnTypeEnum opponentTurn) {
        if (ROCK.equals(opponentTurn)) {
            return SCISSORS;
        } else if (SCISSORS.equals(opponentTurn)) {
            return PAPER;
        } else if (PAPER.equals(opponentTurn)) {
            return ROCK;
        }
        throw new UnsupportedOperationException("invalid comparison variant");
    }

    private TurnTypeEnum calculateWin(TurnTypeEnum opponentTurn) {
        if (ROCK.equals(opponentTurn)) {
            return PAPER;
        } else if (SCISSORS.equals(opponentTurn)) {
            return ROCK;
        } else if (PAPER.equals(opponentTurn)) {
            return SCISSORS;
        }
        throw new UnsupportedOperationException("invalid comparison variant");
    }
}
