package eu.petrvich.adventofcode2022.adventofcode2022java.day4;

import lombok.Data;

@Data
public class Pair {

    private int start;
    private int end;

    public Pair(String element) {
        final String[] parts = element.split("-");
        this.start = Integer.parseInt(parts[0]);
        this.end = Integer.parseInt(parts[1]);
    }

    public boolean isOverlapAtAll(Pair otherPair) {
        return this.start <= otherPair.start && this.end >= otherPair.end;
    }

    public boolean isOverlapPartially(Pair otherPair) {
        return this.start <= otherPair.start && this.end >= otherPair.start && this.end <= otherPair.end;
    }
}
