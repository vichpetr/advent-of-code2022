package eu.petrvich.adventofcode2022.adventofcode2022java.day7;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class File extends Item {

    private final Integer size;
    public File(final String name, Integer size) {
        super(name, ItemType.FILE);
        this.size = size;
    }

    @Override
    public Integer size() {
        return size;
    }

    @Override
    public List<Item> childFolder() {
        return Collections.emptyList();
    }
}
