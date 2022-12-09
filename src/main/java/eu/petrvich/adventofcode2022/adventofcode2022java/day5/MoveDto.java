package eu.petrvich.adventofcode2022.adventofcode2022java.day5;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MoveDto {

    private int move;
    private int from;
    private int to;

}
