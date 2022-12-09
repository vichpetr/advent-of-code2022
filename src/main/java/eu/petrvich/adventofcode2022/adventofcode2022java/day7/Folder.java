package eu.petrvich.adventofcode2022.adventofcode2022java.day7;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class Folder extends Item {

    private final List<Item> items = new ArrayList<>();
    private final Folder parentFolder;

    public Folder(final String name, Folder parentFolder) {
        super(name, ItemType.FOLDER);
        this.parentFolder = parentFolder;
    }

    @Override
    public Integer size() {
        return items.stream().mapToInt(Item::size).sum();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public List<Item> childFolder() {
        return this.items.stream()
                .filter(item -> Objects.equals(ItemType.FOLDER, item.getType()))
                .collect(Collectors.toList());
    }

    public Item fildChildFolderByName(final String folderName) {
        return this.items.stream()
                .filter(item -> Objects.equals(ItemType.FOLDER, item.getType()))
                .filter(item -> Objects.equals(folderName, item.getName())).findFirst().orElse(null);
    }
}
