package eu.petrvich.adventofcode2022.adventofcode2022java.day7;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public abstract class Item {

    String name;
    ItemType type;

    public abstract Integer size();

    public abstract List<Item> childFolder();
}
