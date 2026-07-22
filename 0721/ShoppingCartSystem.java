import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<CartItem> cart = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("1. 加入商品  2. 修改數量  3. 移除商品  4. 計算總額  5. 列出全部  6. 離開");
            System.out.print("請選擇：");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("代碼：");
                    String code = scanner.nextLine();
                    System.out.print("名稱：");
                    String name = scanner.nextLine();
                    System.out.print("單價：");
                    double price = scanner.nextDouble();
                    System.out.print("數量：");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    addItem(cart, code, name, price, quantity);
                    break;
                case 2:
                    System.out.print("請輸入要修改的代碼：");
                    String updateCode = scanner.nextLine();
                    System.out.print("請輸入新數量：");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine();
                    updateQuantity(cart, updateCode, newQuantity);
                    break;
                case 3:
                    System.out.print("請輸入要移除的代碼：");
                    String removeCode = scanner.nextLine();
                    removeItem(cart, removeCode);
                    break;
                case 4:
                    System.out.println("總額：" + calculateTotal(cart));
                    break;
                case 5:
                    listAll(cart);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("無效選項。");
            }
        }

        scanner.close();
    }

    public static void addItem(ArrayList<CartItem> cart, String code, String name, double price, int quantity) {
        CartItem found = findItem(cart, code);
        if (found != null) {
            found.addQuantity(quantity);
            System.out.println("已增加數量。");
            return;
        }
        cart.add(new CartItem(code, name, price, quantity));
        System.out.println("新增成功。");
    }

    public static CartItem findItem(ArrayList<CartItem> cart, String code) {
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }

    public static void updateQuantity(ArrayList<CartItem> cart, String code, int quantity) {
        CartItem found = findItem(cart, code);
        if (found == null) {
            System.out.println("找不到此商品。");
            return;
        }
        if (!found.setQuantity(quantity)) {
            System.out.println("數量必須大於 0。");
        } else {
            System.out.println("修改成功。");
        }
    }

    public static void removeItem(ArrayList<CartItem> cart, String code) {
        CartItem found = findItem(cart, code);
        if (found == null) {
            System.out.println("找不到此商品。");
            return;
        }
        cart.remove(found);
        System.out.println("移除成功。");
    }

    public static double calculateTotal(ArrayList<CartItem> cart) {
        double total = 0;
        for (CartItem item : cart) {
            total += item.getSubtotal();
        }
        return total;
    }

    public static void listAll(ArrayList<CartItem> cart) {
        if (cart.size() == 0) {
            System.out.println("購物車是空的。");
            return;
        }
        for (CartItem item : cart) {
            System.out.println(item);
        }
    }
}
