import java.util.Scanner;

public class ProductArraySystem {

    static String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
    static int[] prices = {890, 490, 5200, 250, 1290};
    static int[] stocks = {12, 20, 5, 30, 8};

    static int purchaseCount = 0;
    static int restockCount = 0;

    public static void printMenu() {
        System.out.println("\n===== 商品陣列管理系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束並顯示操作摘要");
        System.out.print("請選擇功能：");
    }

    public static void printAllProducts() {
        System.out.println("編號\t名稱\t\t單價\t庫存");
        for (int i = 0; i < names.length; i++) {
            System.out.println((i + 1) + "\t" + names[i] + "\t\t" + prices[i] + "\t" + stocks[i]);
        }
    }

    public static int readProductNumber(Scanner sc) {
        int number;
        do {
            System.out.print("請輸入商品編號（1～" + names.length + "）：");
            number = sc.nextInt();
            if (number < 1 || number > names.length) {
                System.out.println("編號超出範圍，請重新輸入。");
            }
        } while (number < 1 || number > names.length);
        return number - 1;
    }

    public static void queryProduct(Scanner sc) {
        int index = readProductNumber(sc);
        System.out.println("名稱：" + names[index]);
        System.out.println("單價：" + prices[index]);
        System.out.println("庫存：" + stocks[index]);
    }

    public static void purchaseProduct(Scanner sc) {
        int index = readProductNumber(sc);
        int quantity;
        do {
            System.out.print("請輸入購買數量：");
            quantity = sc.nextInt();
            if (quantity <= 0) {
                System.out.println("數量必須大於 0，請重新輸入。");
            } else if (quantity > stocks[index]) {
                System.out.println("庫存不足，目前庫存為 " + stocks[index] + "，請重新輸入。");
            }
        } while (quantity <= 0 || quantity > stocks[index]);

        stocks[index] -= quantity;
        purchaseCount++;
        System.out.println("購買成功，" + names[index] + " 剩餘庫存：" + stocks[index]);
    }

    public static void restockProduct(Scanner sc) {
        int index = readProductNumber(sc);
        int quantity;
        do {
            System.out.print("請輸入補貨數量：");
            quantity = sc.nextInt();
            if (quantity <= 0) {
                System.out.println("數量必須大於 0，請重新輸入。");
            }
        } while (quantity <= 0);

        stocks[index] += quantity;
        restockCount++;
        System.out.println("補貨成功，" + names[index] + " 目前庫存：" + stocks[index]);
    }

    public static void printLowStock() {
        System.out.println("低庫存商品（庫存 < 10）：");
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.println(names[i] + "，庫存：" + stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    public static int calculateTotalValue() {
        int total = 0;
        for (int i = 0; i < names.length; i++) {
            total += prices[i] * stocks[i];
        }
        return total;
    }

    public static void printSummary() {
        System.out.println("===== 操作摘要 =====");
        System.out.println("購買次數：" + purchaseCount);
        System.out.println("補貨次數：" + restockCount);
        System.out.println("最終庫存總價值：" + calculateTotalValue());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    printAllProducts();
                    break;
                case 2:
                    queryProduct(sc);
                    break;
                case 3:
                    purchaseProduct(sc);
                    break;
                case 4:
                    restockProduct(sc);
                    break;
                case 5:
                    printLowStock();
                    break;
                case 6:
                    System.out.println("庫存總價值：" + calculateTotalValue());
                    break;
                case 7:
                    printSummary();
                    break;
                default:
                    System.out.println("選項錯誤，請重新輸入。");
            }
        } while (choice != 7);

        sc.close();
    }
}
