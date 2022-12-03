package eu.petrvich.adventofcode2022.adventofcode2022java.day2;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TurnTypeEnum implements Comparable<TurnTypeEnum> {

    ROCK(1, "A", "X"),
    PAPER(2, "B", "Y"),
    SCISSORS(3, "C", "Z");

    final int value;
    final String opponentTurn;
    final String myTurn;

    TurnTypeEnum(final int value, final String opponentTurn, final String myTurn) {
        this.value = value;
        this.opponentTurn = opponentTurn;
        this.myTurn = myTurn;
    }

    public static TurnTypeEnum getValue(String value) {
        return Arrays.stream(TurnTypeEnum.values())
                .filter(item -> item.getOpponentTurn().equals(value) || item.getMyTurn().equals(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Type not found by the letter [" + value + "]"));
    }

}
