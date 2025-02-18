import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatternGenerator {
    static Random random = new Random();
    private static int matrixSize = 10;
    private static int patternAmout = 20;
    private static int[][] matrix = new int[matrixSize][matrixSize];
    private static String filepath = "files\\";
    private static String x = "1;2;3;4;5;6;7;8;9;10;11;12;13;14;15;16;17;18;19;20;21;22;23;24;25;26;27;28;29;30;31;32;33;34;35;36;37;38;39;40;41;42;43;44;45;46;47;48;49;50;51;52;53;54;55;56;57;58;59;60;61;62;63;64;65;66;67;68;69;70;71;72;73;74;75;76;77;78;79;80;81;82;83;84;85;86;87;88;89;90;91;92;93;94;95;96;97;98;99;100";

    public static void main(String[] args) {
        filepath = generateNewFile();
        printToFile(x + "\n");
        for (int i = 0; i < patternAmout; i++) {
            initializeMatrix();
            generateSquare();
            printMatrix();
            printToFile(tooString() + "\n");
        }
    }

    private static void initializeMatrix() {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    private static void generateSquare() {
        int startX = random.nextInt(matrixSize - 2);
        int startY = random.nextInt(matrixSize - 2);

        int endX = random.nextInt(matrixSize - (startX + 1)) + startX + 1;
        int endY = random.nextInt(matrixSize - (startY + 1)) + startY + 1;

        for (int x = startX; x <= endX; x++) {
            matrix[x][startY] = 1;
            matrix[x][endY] = 1;
        }

        for (int y = startY; y <= endY; y++) {
            matrix[startX][y] = 1;
            matrix[endX][y] = 1;
        }
    }

    private static String tooString() {
        String output = "";
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                output += (matrix[i][j] + ";");
            }
        }
        if (output.length() > 0) {
            output = output.substring(0, output.length() - 1);
        }
        return output;
    }

    private static void printMatrix() {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printToFile(String text) {
        try {
            FileWriter writer = new FileWriter(filepath, true);
            writer.write(text);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static String generateNewFile() {
        String name = "rawinput";
        String extension = ".csv";
        int count = 1;

        try {
            File directory = new File(filepath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            while (new File(filepath + name + count + extension).exists()) {
                count++;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while checking file existence.");
            e.printStackTrace();
        }

        String filename = filepath + name + count + extension;
        return filename;
    }
}
