import java.util.ArrayList;
import java.util.Scanner;
/**
 * 課後作業：商品文字資料管理器
 *
 * 測試案例：
 * 1. "Keyboard,890,12" -> 解析成功
 * 2. 完整搜尋 "mouse" -> 找到 Mouse
 * 3. 完整搜尋 "Camera" -> 找不到
 * 4. 部分搜尋 "e" -> Keyboard, Mouse, Headset
 * 5. 低庫存門檻 10 -> Monitor(5), Headset(8)
 * 6. 庫存總價值 -> 各項 價格*庫存 加總
 * 7. 新增 "Webcam,1500,15" -> 成功
 * 8. 新增 "Speaker,abc,10" -> 數字格式錯誤，不中止
 * 9. 新增 "Cable,100" -> 欄位不足錯誤
 */
public class ProductDataManager {

    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<Integer> prices = new ArrayList<>();
    static ArrayList<Integer> stocks = new ArrayList<>();

    // 解析單筆文字資料，成功回傳 true，失敗顯示原因並回傳 false
    public static boolean parseRecord(String record) {
        String[] parts = record.split(",");

        if (parts.length != 3) {
            System.out.println("資料格式錯誤：欄位數量不正確 -> " + record);
            return false;
        }

        String name = parts[0].trim();
        if (name.isEmpty()) {
            System.out.println("資料格式錯誤：商品名稱不可為空 -> " + record);
            return false;
        }

        int price;
        int stock;
        try {
            price = Integer.parseInt(parts[1].trim());
            stock = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            System.out.println("數字轉換錯誤：價格或庫存不是合法整數 -> " + record);
            return false;
        }

        if (price < 0 || stock < 0) {
            System.out.println("資料格式錯誤：價格或庫存不可為負數 -> " + record);
            return false;
        }

        names.add(name);
        prices.add(price);
        stocks.add(stock);
        return true;
    }

    // 顯示解析後的商品表格
    public static void showAll() {
        System.out.println("商品名稱\t價格\t庫存");
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i) + "\t" + prices.get(i) + "\t" + stocks.get(i));
        }
    }

    // 完整名稱搜尋
    public static void findExact(String keyword) {
        String normalized = keyword.trim();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(normalized)) {
                System.out.println("找到商品：" + names.get(i)
                        + "，價格：" + prices.get(i)
                        + "，庫存：" + stocks.get(i));
                return;
            }
        }
        System.out.println("找不到商品：" + keyword);
    }

    // 部分名稱搜尋
    public static void findPartial(String keyword) {
        String normalized = keyword.trim().toLowerCase();
        boolean found = false;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).toLowerCase().contains(normalized)) {
                System.out.println(names.get(i) + "\t" + prices.get(i) + "\t" + stocks.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到相符商品。");
        }
    }

    // 顯示低庫存商品
    public static void showLowStock(int threshold) {
        System.out.println("庫存低於 " + threshold + " 的商品：");
        boolean found = false;
        for (int i = 0; i < names.size(); i++) {
            if (stocks.get(i) < threshold) {
                System.out.println(names.get(i) + "，庫存：" + stocks.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("沒有低庫存商品。");
        }
    }

    // 顯示庫存總價值
    public static void showTotalValue() {
        long totalValue = 0;
        for (int i = 0; i < names.size(); i++) {
            totalValue += (long) prices.get(i) * stocks.get(i);
        }
        System.out.println("庫存總價值：" + totalValue);
    }

    public static void main(String[] args) {
        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        for (String record : records) {
            parseRecord(record);
        }

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== 商品文字資料管理器 ===");
            System.out.println("1. 顯示解析後的商品表格");
            System.out.println("2. 完整名稱搜尋");
            System.out.println("3. 部分名稱搜尋");
            System.out.println("4. 顯示低庫存商品");
            System.out.println("5. 顯示庫存總價值");
            System.out.println("6. 新增一筆文字資料");
            System.out.println("7. 結束");
            System.out.print("請選擇功能：");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    showAll();
                    break;
                case "2":
                    System.out.print("請輸入完整商品名稱：");
                    findExact(sc.nextLine());
                    break;
                case "3":
                    System.out.print("請輸入部分商品名稱：");
                    findPartial(sc.nextLine());
                    break;
                case "4":
                    System.out.print("請輸入低庫存門檻：");
                    try {
                        int threshold = Integer.parseInt(sc.nextLine().trim());
                        showLowStock(threshold);
                    } catch (NumberFormatException e) {
                        System.out.println("輸入的門檻不是合法整數。");
                    }
                    break;
                case "5":
                    showTotalValue();
                    break;
                case "6":
                    System.out.print("請輸入新資料（格式：名稱,價格,庫存）：");
                    String newRecord = sc.nextLine();
                    if (parseRecord(newRecord)) {
                        System.out.println("新增成功。");
                    }
                    break;
                case "7":
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
