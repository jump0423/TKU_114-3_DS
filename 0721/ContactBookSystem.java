import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("1. 新增聯絡人  2. 搜尋  3. 修改電話  4. 刪除  5. 完整清單  6. 離開");
            System.out.print("請選擇：");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("代碼：");
                    String code = scanner.nextLine();
                    System.out.print("姓名：");
                    String name = scanner.nextLine();
                    System.out.print("電話：");
                    String phone = scanner.nextLine();
                    System.out.print("電子郵件：");
                    String email = scanner.nextLine();
                    addContact(contacts, code, name, phone, email);
                    break;
                case 2:
                    System.out.print("請輸入要搜尋的代碼：");
                    String searchCode = scanner.nextLine();
                    Contact found = findContact(contacts, searchCode);
                    if (found == null) {
                        System.out.println("找不到此聯絡人。");
                    } else {
                        System.out.println(found);
                    }
                    break;
                case 3:
                    System.out.print("請輸入要修改的代碼：");
                    String updateCode = scanner.nextLine();
                    System.out.print("請輸入新電話：");
                    String newPhone = scanner.nextLine();
                    updatePhone(contacts, updateCode, newPhone);
                    break;
                case 4:
                    System.out.print("請輸入要刪除的代碼：");
                    String deleteCode = scanner.nextLine();
                    deleteContact(contacts, deleteCode);
                    break;
                case 5:
                    listAll(contacts);
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

    public static void addContact(ArrayList<Contact> contacts, String code, String name, String phone, String email) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("姓名不可為空白。");
            return;
        }
        if (findContact(contacts, code) != null) {
            System.out.println("代碼重複，新增失敗。");
            return;
        }
        contacts.add(new Contact(code, name, phone, email));
        System.out.println("新增成功。");
    }

    public static Contact findContact(ArrayList<Contact> contacts, String code) {
        for (Contact contact : contacts) {
            if (contact.getCode().equalsIgnoreCase(code)) {
                return contact;
            }
        }
        return null;
    }

    public static void updatePhone(ArrayList<Contact> contacts, String code, String newPhone) {
        Contact found = findContact(contacts, code);
        if (found == null) {
            System.out.println("找不到此聯絡人，修改失敗。");
            return;
        }
        found.setPhone(newPhone);
        System.out.println("修改成功。");
    }

    public static void deleteContact(ArrayList<Contact> contacts, String code) {
        Contact found = findContact(contacts, code);
        if (found == null) {
            System.out.println("找不到此聯絡人，刪除失敗。");
            return;
        }
        contacts.remove(found);
        System.out.println("刪除成功。");
    }

    public static void listAll(ArrayList<Contact> contacts) {
        if (contacts.size() == 0) {
            System.out.println("目前沒有聯絡人。");
            return;
        }
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
