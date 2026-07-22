import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Equipment> equipmentList = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("1. 新增設備  2. 依代碼搜尋  3. 借出  4. 歸還  5. 列出可借設備  6. 離開");
            System.out.print("請選擇：");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("代碼：");
                    String code = scanner.nextLine();
                    System.out.print("名稱：");
                    String name = scanner.nextLine();
                    addEquipment(equipmentList, code, name);
                    break;
                case 2:
                    System.out.print("請輸入要搜尋的代碼：");
                    String searchCode = scanner.nextLine();
                    Equipment found = findByCode(equipmentList, searchCode);
                    if (found == null) {
                        System.out.println("找不到此設備。");
                    } else {
                        System.out.println(found);
                    }
                    break;
                case 3:
                    System.out.print("請輸入要借出的代碼：");
                    String borrowCode = scanner.nextLine();
                    borrowEquipment(equipmentList, borrowCode);
                    break;
                case 4:
                    System.out.print("請輸入要歸還的代碼：");
                    String returnCode = scanner.nextLine();
                    returnEquipment(equipmentList, returnCode);
                    break;
                case 5:
                    listAvailable(equipmentList);
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

    public static void addEquipment(ArrayList<Equipment> equipmentList, String code, String name) {
        if (findByCode(equipmentList, code) != null) {
            System.out.println("代碼重複，新增失敗。");
            return;
        }
        equipmentList.add(new Equipment(code, name));
        System.out.println("新增成功。");
    }

    public static Equipment findByCode(ArrayList<Equipment> equipmentList, String code) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getCode().equalsIgnoreCase(code)) {
                return equipment;
            }
        }
        return null;
    }

    public static void borrowEquipment(ArrayList<Equipment> equipmentList, String code) {
        Equipment found = findByCode(equipmentList, code);
        if (found == null) {
            System.out.println("找不到此設備。");
            return;
        }
        if (!found.isAvailable()) {
            System.out.println("此設備已被借出。");
            return;
        }
        found.borrow();
        System.out.println("借出成功。");
    }

    public static void returnEquipment(ArrayList<Equipment> equipmentList, String code) {
        Equipment found = findByCode(equipmentList, code);
        if (found == null) {
            System.out.println("找不到此設備。");
            return;
        }
        found.returnEquipment();
        System.out.println("歸還成功。");
    }

    public static void listAvailable(ArrayList<Equipment> equipmentList) {
        boolean any = false;
        for (Equipment equipment : equipmentList) {
            if (equipment.isAvailable()) {
                System.out.println(equipment);
                any = true;
            }
        }
        if (!any) {
            System.out.println("目前沒有可借設備。");
        }
    }
}
