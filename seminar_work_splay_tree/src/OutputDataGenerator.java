import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class OutputDataGenerator {
    public static void generate(int[] array) {
        Random random = new Random();
        File outputFile = new File("results/resultsInsert.txt");
        MySplayTree splayTree = new MySplayTree();
        long InsertTime = 0;
        long InsertOperations = 0;
        long totalInsertOperations = 0;
        long totalInsertTime = 0;
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            for (int num : array) {
                long startTime = System.nanoTime();
                splayTree.insert(num);
                long endTime = System.nanoTime();
                InsertTime = endTime - startTime;
                InsertOperations = splayTree.getOperations();
                totalInsertTime+=InsertTime;
                totalInsertOperations+=InsertOperations;
                writer.println(InsertTime + " " + InsertOperations);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Среднее время для вставки: "+(totalInsertTime/10000.0));
        System.out.println("Среднее количество операций для вставки: "+(totalInsertOperations/10000.0));
        outputFile = new File("results/resultsSearch.txt");
        long searchTime;
        long searchOperations;
        long totalSearchTime = 0;
        long totalSearchOperations = 0;
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            for (int i = 0; i < 100; i++) {
                long startTime = System.nanoTime();
                splayTree.search(array[random.nextInt(10000 - 1)]);
                long endTime = System.nanoTime();
                searchTime = endTime - startTime;
                totalSearchTime+=searchTime;
                searchOperations = splayTree.getOperations();
                totalSearchOperations+=searchOperations;
                writer.println(searchTime + " " + searchOperations);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Среднее время для поиска: "+(totalSearchTime/100.0));
        System.out.println("Среднее количество операций для поиска: "+(totalSearchOperations/100.0));
        outputFile = new File("results/resultsDelete.txt");
        long deleteTime = 0;
        long totalDeleteTime = 0;
        long deleteOperations = 0;
        long totalDeleteOperations = 0;
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            for (int i = 0; i < 1000; i++) {
                long startTime = System.nanoTime();
                splayTree.delete(array[random.nextInt(10000 - 1)]);
                long endTime = System.nanoTime();
                deleteTime = endTime - startTime;
                totalDeleteTime+=deleteTime;
                deleteOperations = splayTree.getOperations();
                totalDeleteOperations+=deleteOperations;
                writer.println(deleteTime + " " + deleteOperations);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Среднее количество операций для удаления: "+(totalDeleteTime/1000.0));
        System.out.println("Среднее количество операций для удаления: "+(totalDeleteOperations/1000.0));
    }
}
