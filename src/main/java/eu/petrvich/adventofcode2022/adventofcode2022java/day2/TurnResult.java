package eu.petrvich.adventofcode2022.adventofcode2022java.day2;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TurnResult {

    LOSE("X"),DRAW("Y"),WIN("Z");

    final String result;

    TurnResult(final String result) {
        this.result = result;
    }

    public static TurnResult getValue(String value) {
        return Arrays.stream(TurnResult.values())
                .filter(item -> item.getResult().equals(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Type not found by the letter [" + value + "]"));
    }
}
