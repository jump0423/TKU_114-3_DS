import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("1. 新增  2. 搜尋  3. 修改  4. 刪除  5. 列出全部  6. 離開");
            System.out.print("請選擇：");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("請輸入姓名：");
                    String newName = scanner.nextLine();
                    addName(names, newName);
                    break;
                case 2:
                    System.out.print("請輸入要搜尋的姓名：");
                    String searchName = scanner.nextLine();
                    int foundIndex = findName(names, searchName);
                    if (foundIndex == -1) {
                        System.out.println("找不到此姓名。");
                    } else {
                        System.out.println("找到，索引為：" + foundIndex);
                    }
                    break;
                case 3:
                    System.out.print("請輸入要修改的姓名：");
                    String oldName = scanner.nextLine();
                    System.out.print("請輸入新姓名：");
                    String updatedName = scanner.nextLine();
                    updateName(names, oldName, updatedName);
                    break;
                case 4:
                    System.out.print("請輸入要刪除的姓名：");
                    String deleteTarget = scanner.nextLine();
                    deleteName(names, deleteTarget);
                    break;
                case 5:
                    listAll(names);
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

    public static void addName(ArrayList<String> names, String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("姓名不可為空白。");
            return;
        }
        names.add(name);
        System.out.println("新增成功。");
    }

    public static int findName(ArrayList<String> names, String name) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateName(ArrayList<String> names, String oldName, String newName) {
        int index = findName(names, oldName);
        if (index == -1) {
            System.out.println("找不到此姓名，修改失敗。");
            return;
        }
        if (newName == null || newName.trim().isEmpty()) {
            System.out.println("姓名不可為空白。");
            return;
        }
        names.set(index, newName);
        System.out.println("修改成功。");
    }

    public static void deleteName(ArrayList<String> names, String name) {
        int index = findName(names, name);
        if (index == -1) {
            System.out.println("找不到此姓名，刪除失敗。");
            return;
        }
        names.remove(index);
        System.out.println("刪除成功。");
    }

    public static void listAll(ArrayList<String> names) {
        if (names.size() == 0) {
            System.out.println("名單目前是空的。");
            return;
        }
        for (String name : names) {
            System.out.println(name);
        }
    }
}
