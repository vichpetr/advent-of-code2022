package eu.petrvich.adventofcode2022.adventofcode2022java.day8;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
public class Day8Service {

    private static int[][] parseArray(final String fileName) throws IOException {
        final List<String> lines = Files.readAllLines(Paths.get(fileName));
        final int width = lines.get(0).length();
        final int height = lines.size();

        int[][] array = new int[width][height];
        for (int i = 0; i < lines.size(); i++) {
            String row = lines.get(i);
            for (int j = 0; j < row.length(); j++) {
                array[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
            }
        }
        return array;
    }

    @PostConstruct
    public void execute() throws IOException {
        log.info("=================================== Day 8 - part 1 ===========================");
        //this.turn1("input-d8.txt");
        this.turn2("input-d8.txt");
        log.info("=================================== Day 8 - END part 1 ===========================");
    }

    public void turn1(String fileName) throws IOException {
        int[][] array = parseArray(fileName);

        boolean[][] visibleFromTop = new boolean[array.length][array[0].length];
        boolean[][] visibleFromLeft = new boolean[array.length][array[0].length];
        boolean[][] visibleFromBottom = new boolean[array.length][array[0].length];
        boolean[][] visibleFromRight = new boolean[array.length][array[0].length];
        boolean[][] finalResult = new boolean[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            int maxTreeSize = Integer.MIN_VALUE;
            for (int j = 0; j < array[i].length; j++) {
                final boolean comparisonResult = maxTreeSize < array[j][i];
                if (comparisonResult) {
                    visibleFromTop[j][i] = comparisonResult;
                    maxTreeSize = array[j][i];
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            int maxTreeSize = Integer.MIN_VALUE;
            for (int j = 0; j < array[i].length; j++) {
                final boolean comparisonResult = maxTreeSize < array[i][j];
                if (comparisonResult) {
                    visibleFromLeft[i][j] = comparisonResult;
                    maxTreeSize = array[i][j];
                }
            }
        }

        for (int i = array.length - 1; i > 0; i--) {
            int maxTreeSize = Integer.MIN_VALUE;
            for (int j = array[i].length - 1; j > 0; j--) {
                final boolean comparisonResult = maxTreeSize < array[j][i];
                if (comparisonResult) {
                    visibleFromRight[j][i] = comparisonResult;
                    maxTreeSize = array[j][i];
                }
            }
        }

        for (int i = array.length - 1; i > 0; i--) {
            int maxTreeSize = Integer.MIN_VALUE;
            for (int j = array[i].length - 1; j > 0; j--) {
                final boolean comparisonResult = maxTreeSize < array[i][j];
                if (comparisonResult) {
                    visibleFromBottom[i][j] = comparisonResult;
                    maxTreeSize = array[i][j];
                }
            }
        }

        int topCounter = 0;
        int leftCounter = 0;
        int rightCounter = 0;
        int bottomCounter = 0;
        int finalCount = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                finalResult[i][j] = visibleFromTop[i][j]
                        || visibleFromBottom[i][j]
                        || visibleFromRight[i][j]
                        || visibleFromLeft[i][j];
                if (finalResult[i][j]) {
                    finalCount++;
                }
                if (visibleFromTop[i][j]) {
                    topCounter++;
                }
                if (visibleFromBottom[i][j]) {
                    bottomCounter++;
                }
                if (visibleFromRight[i][j]) {
                    rightCounter++;
                }
                if (visibleFromLeft[i][j]) {
                    leftCounter++;
                }
            }
        }


        log.info("topCounter {}", topCounter);
        log.info("leftCounter {}", leftCounter);
        log.info("rightCounter {}", rightCounter);
        log.info("bottomCounter {}", bottomCounter);
        log.info("finalCount {}", finalCount);
    }

    public void turn2(String fileName) throws IOException {
        int[][] array = parseArray(fileName);

        int maxScenicView = 0;
        for (int i = 1; i < array.length - 1; i++) {
            for (int j = 1; j < array[i].length - 1; j++) {
                final int scenicTopView = calculateScenicTopView(array, j, i);
                final int scenicLeftView = calculateScenicLeftView(array, j, i);
                final int scenicRightView = calculateScenicRightView(array, j, i);
                final int scenicBottomView = calculateScenicBottomView(array, j, i);

                int totalScenicView = scenicTopView * scenicLeftView * scenicRightView * scenicBottomView;
                if(maxScenicView < totalScenicView) {
                    maxScenicView = totalScenicView;
                }
            }
        }

        log.info("total scenic view is {}", maxScenicView);
    }

    private int calculateScenicLeftView(int[][] array, int rowIndex, int columnIndex) {
        int scenicView = 0;
        int currentValue = array[columnIndex][rowIndex];
        for (int i = rowIndex + 1; i < array[columnIndex].length; i++) {
            final int newValue = array[columnIndex][i];
            if (newValue >= currentValue) {
                scenicView++;
                break;
            }
            scenicView++;
        }
        return scenicView;
    }

    private int calculateScenicRightView(int[][] array, int rowIndex, int columnIndex) {
        int scenicView = 0;
        int currentValue = array[columnIndex][rowIndex];
        for (int i = rowIndex - 1; i >= 0; i--) {
            final int newValue = array[columnIndex][i];
            if (newValue >= currentValue) {
                scenicView++;
                break;
            }
            scenicView++;
        }
        return scenicView;
    }

    private int calculateScenicTopView(int[][] array, int rowIndex, int columnIndex) {
        int scenicView = 0;
        int currentValue = array[columnIndex][rowIndex];
        for (int i = columnIndex + 1; i < array.length; i++) {
            final int newValue = array[i][rowIndex];
            if (newValue >= currentValue) {
                scenicView++;
                break;
            }
            scenicView++;
        }
        return scenicView;
    }

    private int calculateScenicBottomView(int[][] array, int rowIndex, int columnIndex) {
        int scenicView = 0;
        int currentValue = array[columnIndex][rowIndex];
        for (int i = columnIndex - 1; i >= 0; i--) {
            final int newValue = array[i][rowIndex];
            if (newValue >= currentValue) {
                scenicView++;
                break;
            }
            scenicView++;
        }
        return scenicView;
    }
}
