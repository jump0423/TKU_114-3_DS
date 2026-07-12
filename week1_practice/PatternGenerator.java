import java.util.Scanner;

public class PatternGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("請輸入選項：");
            int option = sc.nextInt();

            if (option == 0) {
                break;
            }

            switch (option) {
                case 1:
                    printMultiplicationTable();
                    break;
                case 2: {
                    int height = readPositiveInt(sc, "請輸入高度：");
                    printTriangle(height);
                    break;
                }
                case 3: {
                    int height = readPositiveInt(sc, "請輸入高度：");
                    printReverseTriangle(height);
                    break;
                }
                case 4: {
                    int rows = readPositiveInt(sc, "請輸入列數：");
                    int cols = readPositiveInt(sc, "請輸入欄數：");
                    printNumberGrid(rows, cols);
                    break;
                }
                default:
                    System.out.println("選項不合法，請重新輸入。");
            }
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("=== Pattern Menu ===");
        System.out.println("1. Multiplication table");
        System.out.println("2. Triangle");
        System.out.println("3. Reverse triangle");
        System.out.println("4. Number grid");
        System.out.println("0. Exit");
    }

    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            value = sc.nextInt();
            if (value > 0) {
                break;
            }
            System.out.println("輸入不合法，請重新輸入。");
        }
        return value;
    }

    public static void printMultiplicationTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(i + "x" + j + "=" + (i * j) + "\t");
            }
            System.out.println();
        }
    }

    public static void printTriangle(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printReverseTriangle(int height) {
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printNumberGrid(int rows, int cols) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j);
                if (j < cols) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}