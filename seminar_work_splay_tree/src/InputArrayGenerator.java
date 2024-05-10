import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class InputArrayGenerator {
    public static int[] generate() {
        Random random = new Random();
        int[] array = new int[10000];
        for (int i = 0; i < 10000; i++) {
            array[i] = random.nextInt(10000);
        }
        return array;
    }
}
