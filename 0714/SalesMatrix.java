import java.util.Scanner;

public class SalesMatrix {

    static final int PRODUCT_COUNT = 3;
    static final int DAY_COUNT = 4;

    public static void inputSales(Scanner sc, int[][] sales) {
        for (int p = 0; p < sales.length; p++) {
            for (int d = 0; d < sales[p].length; d++) {
                int value;
                do {
                    System.out.print("商品 " + (p + 1) + " 第 " + (d + 1) + " 天銷售量：");
                    value = sc.nextInt();
                    if (value < 0) {
                        System.out.println("銷售量不得小於 0，請重新輸入。");
                    }
                } while (value < 0);
                sales[p][d] = value;
            }
        }
    }

    public static void printTable(int[][] sales) {
        System.out.println("銷售量表格：");
        for (int p = 0; p < sales.length; p++) {
            for (int d = 0; d < sales[p].length; d++) {
                System.out.printf("%5d", sales[p][d]);
            }
            System.out.println();
        }
    }

    public static int calculateProductTotal(int[][] sales, int productIndex) {
        int total = 0;
        for (int day = 0; day < sales[productIndex].length; day++) {
            total += sales[productIndex][day];
        }
        return total;
    }

    public static int calculateDayTotal(int[][] sales, int dayIndex) {
        int total = 0;
        for (int product = 0; product < sales.length; product++) {
            total += sales[product][dayIndex];
        }
        return total;
    }

    public static int findMaxProduct(int[][] sales) {
        int maxIndex = 0;
        int maxTotal = calculateProductTotal(sales, 0);

        for (int p = 1; p < sales.length; p++) {
            int total = calculateProductTotal(sales, p);
            if (total > maxTotal) {
                maxTotal = total;
                maxIndex = p;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[PRODUCT_COUNT][DAY_COUNT];

        inputSales(sc, sales);
        printTable(sales);

        System.out.println("各商品總銷售量：");
        for (int p = 0; p < sales.length; p++) {
            System.out.println("商品 " + (p + 1) + "：" + calculateProductTotal(sales, p));
        }

        System.out.println("各天總銷售量：");
        for (int d = 0; d < DAY_COUNT; d++) {
            System.out.println("第 " + (d + 1) + " 天：" + calculateDayTotal(sales, d));
        }

        int maxProduct = findMaxProduct(sales);
        System.out.println("總銷售量最高的商品是：商品 " + (maxProduct + 1));

        sc.close();
    }
}
