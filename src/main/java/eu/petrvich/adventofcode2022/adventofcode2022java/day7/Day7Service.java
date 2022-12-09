package eu.petrvich.adventofcode2022.adventofcode2022java.day7;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Slf4j
@Service
public class Day7Service {

    Map<String, Integer> folderSize = new HashMap<>();

    @PostConstruct
    public void execute() throws IOException {
        log.info("=================================== Day 7 - part 1 ===========================");
        //this.turn1("input-d7-part.txt");
        this.turn1("input-d7.txt");
        log.info("=================================== Day 7 - END part 1 ===========================");
    }


    public void turn1(String fileName) throws IOException {

        final List<String> lines = Files.readAllLines(Paths.get(fileName));
        Folder currentFolder = null;
        Folder rootFolder = null;
        for (String row : lines) {
            if (row.startsWith("$")) {
                if (row.equals("$ ls")) {
                } else if (row.equals("$ cd ..")) {
                    if (currentFolder != null) {
                        currentFolder = currentFolder.getParentFolder();
                    }
                } else {
                    final String[] items = row.split(" ");
                    String folderName = items[items.length - 1];
                    if (currentFolder != null) {
                        currentFolder = (Folder) currentFolder.fildChildFolderByName(folderName);
                    } else {
                        rootFolder = new Folder(folderName, null);
                        currentFolder = rootFolder;
                    }
                }
            } else {
                final String[] items = row.split(" ");
                if ("dir".equals(items[0])) {
                    String folderName = items[items.length - 1];
                    final Folder newFolder = new Folder(folderName, currentFolder);
                    if (currentFolder != null) {
                        currentFolder.addItem(newFolder);
                    }
                } else {
                    final File file = new File(items[1], Integer.parseInt(items[0]));
                    currentFolder.addItem(file);
                }
            }
        }

        log.info("smaller folder [{}] size is {}", fileName, calculateSize(rootFolder));
        log.info("root folder [{}] size is {}", fileName, calculateSize(rootFolder));
        Integer sizeToDelete = rootFolder.size() - 40_000_000;
        log.info("we must delete [{}] size", sizeToDelete);
        Integer maxSizeToDelete = Integer.MAX_VALUE;
        Integer minSizeToDelete = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : this.folderSize.entrySet()) {
            if(entry.getValue() > sizeToDelete && entry.getValue() < maxSizeToDelete) {
                maxSizeToDelete = entry.getValue();
                log.info("Folder to delete is {} with size [{}] - max", entry.getKey(), entry.getValue());
            }

            if(entry.getValue() < sizeToDelete && entry.getValue() > minSizeToDelete) {
                minSizeToDelete = entry.getValue();
                log.info("Folder to delete is {} with size [{}] - min", entry.getKey(), entry.getValue());
            }
        }
    }

    int calculateSize(Folder root) {
        if (root == null) {
            return 0;
        }
        int size = 0;
        folderSize.put(root.getName(), root.size());
        if (root.size() < 100000) {
            size = root.size();
        }
        for (Item item : root.childFolder()) {
            Folder folder = (Folder) item;
            size += calculateSize(folder);
        }
        return size;
    }
}
