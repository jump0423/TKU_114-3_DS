import java.util.Scanner;

public class ProductManagementSystem {

    private static Product[] products = new Product[10];
    private static int count = 0;
    private static int searchCount = 0;
    private static int addCount = 0;
    private static int sellCount = 0;
    private static int restockCount = 0;
    private static int priceChangeCount = 0;

    private static void printAllProducts() {
        if (count == 0) {
            System.out.println("目前沒有任何商品");
            return;
        }
        System.out.println("=== 全部商品（共 " + count + " 項） ===");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + products[i]);
        }
    }

    private static Product findProductByName(String name) {
        if (name == null) {
            return null;
        }
        String target = name.trim();
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(target)) {
                return products[i];
            }
        }
        return null;
    }

    private static boolean addProduct(String name, int price, int stock) {
        if (count >= products.length) {
            System.out.println("新增失敗：商品欄位已滿");
            return false;
        }
        if (findProductByName(name) != null) {
            System.out.println("新增失敗：商品名稱重複");
            return false;
        }
        products[count] = new Product(name, price, stock);
        count++;
        addCount++;
        return true;
    }

    private static boolean sellProduct(String name, int quantity) {
        Product target = findProductByName(name);
        if (target == null) {
            System.out.println("出售失敗：找不到商品");
            return false;
        }
        boolean result = target.sell(quantity);
        if (result) {
            sellCount++;
        } else {
            System.out.println("出售失敗：數量錯誤或庫存不足");
        }
        return result;
    }

    private static boolean restockProduct(String name, int quantity) {
        Product target = findProductByName(name);
        if (target == null) {
            System.out.println("補貨失敗：找不到商品");
            return false;
        }
        boolean result = target.restock(quantity);
        if (result) {
            restockCount++;
        } else {
            System.out.println("補貨失敗：數量必須大於 0");
        }
        return result;
    }

    private static boolean changeProductPrice(String name, int newPrice) {
        Product target = findProductByName(name);
        if (target == null) {
            System.out.println("修改失敗：找不到商品");
            return false;
        }
        boolean result = target.setPrice(newPrice);
        if (result) {
            priceChangeCount++;
        } else {
            System.out.println("修改失敗：價格必須大於 0");
        }
        return result;
    }

    private static void printLowStockProducts() {
        System.out.println("=== 低庫存商品（庫存 < 10） ===");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (products[i].isLowStock()) {
                System.out.println(products[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有低庫存商品");
        }
    }

    
    private static long calculateTotalValue() {
        long total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getInventoryValue();
        }
        return total;
    }

    private static void printSummary() {
        System.out.println("=== 操作摘要 ===");
        System.out.println("目前商品數量：" + count);
        System.out.println("新增次數：" + addCount);
        System.out.println("搜尋次數：" + searchCount);
        System.out.println("出售次數：" + sellCount);
        System.out.println("補貨次數：" + restockCount);
        System.out.println("修改價格次數：" + priceChangeCount);
        System.out.println("庫存總價值：" + calculateTotalValue());
    }

    private static void initProducts() {
        addProduct("Keyboard", 890, 12);
        addProduct("Mouse", 490, 20);
        addProduct("Monitor", 5200, 5);
        addProduct("Headset", 1200, 8);
        addProduct("Webcam", 1500, 15);
        addCount = 0;
    }

    private static void runAutomatedTests() {
        System.out.println("=== 自動化測試開始 ===");

        System.out.println("測試1 新增商品 Tablet：" + addProduct("Tablet", 8900, 6));
        System.out.println("測試2 新增重複名稱 tablet（忽略大小寫）：" + addProduct("tablet", 100, 1));
        System.out.println("測試3 搜尋 KEYBOARD（忽略大小寫）：" + (findProductByName("KEYBOARD") != null));
        System.out.println("測試4 搜尋前後有空白 \" mouse \"：" + (findProductByName(" mouse ") != null));
        System.out.println("測試5 出售 Monitor 3 件：" + sellProduct("Monitor", 3));
        System.out.println("測試6 出售 Monitor 超過庫存：" + sellProduct("Monitor", 999));
        System.out.println("測試7 補貨 Headset 5 件：" + restockProduct("Headset", 5));
        System.out.println("測試8 補貨數量為 0（應失敗）：" + restockProduct("Headset", 0));
        System.out.println("測試9 修改 Webcam 價格為 1600：" + changeProductPrice("Webcam", 1600));
        System.out.println("測試10 修改價格為負數（應失敗）：" + changeProductPrice("Webcam", -100));
        System.out.println("測試11 搜尋不存在的商品：" + (findProductByName("NotExist") == null));
        System.out.println("測試12 新增至陣列滿（連續新增至第 10 筆）：");
        while (count < products.length) {
            addProduct("Extra" + count, 100, 1);
        }
        System.out.println("目前商品數：" + count);
        System.out.println("測試13 陣列已滿時再新增（應失敗）：" + addProduct("Overflow", 100, 1));

        System.out.println("=== 自動化測試結束 ===\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initProducts();
        runAutomatedTests();

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=== 商品管理系統 ===");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 依完整名稱搜尋");
            System.out.println("3. 新增商品");
            System.out.println("4. 出售商品");
            System.out.println("5. 補充庫存");
            System.out.println("6. 修改商品價格");
            System.out.println("7. 顯示低庫存商品");
            System.out.println("8. 顯示全部庫存總價值");
            System.out.println("0. 結束並顯示操作摘要");
            System.out.print("請選擇：");

            if (!scanner.hasNextInt()) {
                System.out.println("請輸入數字選項");
                scanner.next();
                continue;
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printAllProducts();
                    break;
                case 2: {
                    System.out.print("請輸入商品名稱：");
                    String name = scanner.next();
                    searchCount++;
                    Product found = findProductByName(name);
                    if (found != null) {
                        System.out.println("搜尋結果：" + found);
                    } else {
                        System.out.println("找不到商品");
                    }
                    break;
                }
                case 3: {
                    System.out.print("請輸入商品名稱：");
                    String name = scanner.next();
                    System.out.print("請輸入價格：");
                    int price = scanner.nextInt();
                    System.out.print("請輸入庫存：");
                    int stock = scanner.nextInt();
                    if (addProduct(name, price, stock)) {
                        System.out.println("新增成功");
                    }
                    break;
                }
                case 4: {
                    System.out.print("請輸入商品名稱：");
                    String name = scanner.next();
                    System.out.print("請輸入出售數量：");
                    int quantity = scanner.nextInt();
                    if (sellProduct(name, quantity)) {
                        System.out.println("出售成功");
                    }
                    break;
                }
                case 5: {
                    System.out.print("請輸入商品名稱：");
                    String name = scanner.next();
                    System.out.print("請輸入補貨數量：");
                    int quantity = scanner.nextInt();
                    if (restockProduct(name, quantity)) {
                        System.out.println("補貨成功");
                    }
                    break;
                }
                case 6: {
                    System.out.print("請輸入商品名稱：");
                    String name = scanner.next();
                    System.out.print("請輸入新價格：");
                    int price = scanner.nextInt();
                    if (changeProductPrice(name, price)) {
                        System.out.println("修改成功");
                    }
                    break;
                }
                case 7:
                    printLowStockProducts();
                    break;
                case 8:
                    System.out.println("庫存總價值：" + calculateTotalValue());
                    break;
                case 0:
                    printSummary();
                    System.out.println("系統結束");
                    break;
                default:
                    System.out.println("無效選項，請重新輸入");
            }
        }

        scanner.close();
    }
}
