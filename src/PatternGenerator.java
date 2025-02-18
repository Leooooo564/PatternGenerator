import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatternGenerator {
    static Random random = new Random();
    private static int matrixSize = 20;
    private static int patternAmout = 100;
    private static int[][] matrix = new int[matrixSize][matrixSize];
    private static String inputFilepath = "";
    private static String outputFilepath = "";
    private static String inputNeurons = inputNeurons();
    private static String outputNeurons = "521;522;523";

    public static void main(String[] args) {

        inputFilepath = generateNewFile(true);
        outputFilepath = generateNewFile(false);
        printToFile(true, inputNeurons + "\n");
        printToFile(false, outputNeurons + "\n");

        for (int i = 0; i < patternAmout; i++) {
            initializeMatrix();
            int rand = random.nextInt(4);
            String outputPattern = "";

            switch (rand) {
                case 0:
                    generateSquare();
                    outputPattern = "1;0;0";
                    break;
                case 1:
                    generateTriangle();
                    outputPattern = "0;1;0";
                    break;
                case 2:
                    generateCircle();
                    outputPattern = "0;0;1";
                    break;
                case 3:
                    generateRandom();
                    outputPattern = "0;0;0";
                    break;
            }
            // printMatrix();
            printToFile(true, tooString() + "\n");
            printToFile(false, outputPattern + "\n");
        }
    }

    private static void initializeMatrix() {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    private static String inputNeurons() {
        String tmp = "";
        for (int i = 1; i <= matrixSize * matrixSize; i++) {
            tmp += i + ";";
        }
        if (tmp.length() > 0) {
            tmp = tmp.substring(0, tmp.length() - 1);
        }
        return tmp;
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

    private static void generateRandom() {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = random.nextInt(2);
            }
        }
    }

    private static void generateTriangle() {
        // int numTriangles = random.nextInt(6) + 3;
        // for (int t = 0; t < numTriangles; t++) {
        int baseSize = random.nextInt(9) + 2; // Basis zwischen 2 und 4
        int row = random.nextInt(matrixSize - baseSize + 1);
        int col = random.nextInt(matrixSize - baseSize + 1);

        for (int i = 0; i < baseSize; i++) {
            for (int j = 0; j <= i; j++) {
                if (row + i < matrixSize && col + j < matrixSize) {
                    matrix[row + i][col + j] = 1;
                }
            }
        }
        // }
    }

    private static void generateCircle() {
        int radius = random.nextInt(4) + 1;
        int centerX = random.nextInt(matrixSize - 2 * radius) + radius;
        int centerY = random.nextInt(matrixSize - 2 * radius) + radius;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (Math.pow(i - centerX, 2) + Math.pow(j - centerY, 2) <= Math.pow(radius, 2)) {
                    matrix[i][j] = 1;
                }
            }
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

    private static void printToFile(Boolean isInput, String text) {
        String filepath = isInput ? inputFilepath : outputFilepath;
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

    private static String generateNewFile(Boolean isInput) {
        String inputName = "rawinput";
        String outputName = "rawoutput";
        String extension = ".csv";
        int count = 1;
        String name = isInput ? inputName : outputName;
        String path = "files\\";

        try {
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            while (new File(path + name + count + extension).exists()) {
                count++;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while checking file existence.");
            e.printStackTrace();
        }

        String filename = path + name + count + extension;
        return filename;
    }
}