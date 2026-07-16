import java.util.Scanner;

/**
 * 課堂實作題二：商品名稱搜尋系統
 *
 * 測試案例：
 * 1. 完整搜尋 "keyboard" -> 索引 0
 * 2. 完整搜尋 " Mouse " -> 索引 1
 * 3. 完整搜尋 "Camera" -> -1（不存在）
 * 4. 部分搜尋 "o" -> Mouse, Monitor, Headset
 * 5. 部分搜尋 "" -> 全部商品
 * 6. 最長名稱商品 -> USB Cable
 */

public class ProductSearchSystem {

    // 0714 商品資料
    static String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
    static int[] prices = {890, 490, 5200, 250, 1290};
    static int[] stocks = {12, 20, 5, 30, 8};

    // 顯示全部商品
    public static void showAll() {
        System.out.println("商品名稱\t價格\t庫存");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + "\t" + prices[i] + "\t" + stocks[i]);
        }
    }

    // 完整名稱搜尋，忽略大小寫與前後空白
    public static int findExact(String keyword) {
        String normalized = keyword.trim();
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(normalized)) {
                return i;
            }
        }
        return -1;
    }

    // 部分名稱搜尋，顯示多筆結果
    public static void findPartial(String keyword) {
        String normalized = keyword.trim().toLowerCase();
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(normalized)) {
                System.out.println(names[i] + "\t" + prices[i] + "\t" + stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到相符商品。");
        }
    }

    // 找出名稱最長的商品索引
    public static int findLongestNameIndex() {
        int longestIndex = 0;
        for (int i = 1; i < names.length; i++) {
            if (names[i].length() > names[longestIndex].length()) {
                longestIndex = i;
            }
        }
        return longestIndex;
    }

    // 顯示商品名稱與關鍵字第一次出現的位置
    public static void showFirstIndexOf(String keyword) {
        String normalizedKeyword = keyword.trim().toLowerCase();
        for (String name : names) {
            int index = name.toLowerCase().indexOf(normalizedKeyword);
            System.out.println(name + " -> 第一次出現位置：" + index);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== 商品名稱搜尋系統 ===");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 完整名稱搜尋");
            System.out.println("3. 部分名稱搜尋");
            System.out.println("4. 顯示名稱最長的商品");
            System.out.println("5. 顯示商品名稱與關鍵字出現位置");
            System.out.println("6. 結束");
            System.out.print("請選擇功能：");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    showAll();
                    break;
                case "2":
                    System.out.print("請輸入完整商品名稱：");
                    String exactKeyword = sc.nextLine();
                    int exactIndex = findExact(exactKeyword);
                    if (exactIndex == -1) {
                        System.out.println("找不到商品。");
                    } else {
                        System.out.println("找到商品：" + names[exactIndex]
                                + "，價格：" + prices[exactIndex]
                                + "，庫存：" + stocks[exactIndex]);
                    }
                    break;
                case "3":
                    System.out.print("請輸入部分商品名稱：");
                    String partialKeyword = sc.nextLine();
                    findPartial(partialKeyword);
                    break;
                case "4":
                    int longestIndex = findLongestNameIndex();
                    System.out.println("名稱最長的商品：" + names[longestIndex]);
                    break;
                case "5":
                    System.out.print("請輸入搜尋關鍵字：");
                    String searchKeyword = sc.nextLine();
                    showFirstIndexOf(searchKeyword);
                    break;
                case "6":
                    running = false;
                    System.out.println("系統結束。");
                    break;
                default:
                    System.out.println("無效選項，請重新輸入。");
            }
        }

        sc.close();
    }
}
