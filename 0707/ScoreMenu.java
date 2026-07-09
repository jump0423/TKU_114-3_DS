import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        System.out.print("請輸入 Java 成績：");
        int javaScore = sc.nextInt();

        System.out.print("請輸入 English 成績：");
        int englishScore = sc.nextInt();

        System.out.print("請輸入 Math 成績：");
        int mathScore = sc.nextInt();

        double average = (javaScore + englishScore + mathScore) / 3.0;

        String passStatus;
        if (average >= 60) {
            passStatus = "Pass";
        } else {
            passStatus = "Fail";
        }

        String grade;
        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        int option = -1;

        while (option != 0) {
            System.out.println();
            System.out.println("=== " + name + " 的成績查詢 ===");
            System.out.println("1. 顯示平均分數");
            System.out.println("2. 顯示及格狀態");
            System.out.println("3. 顯示等第");
            System.out.println("0. 離開");
            System.out.print("請選擇功能：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("平均分數：" + average);
                    break;
                case 2:
                    System.out.println("及格狀態：" + passStatus);
                    break;
                case 3:
                    System.out.println("等第：" + grade);
                    break;
                case 0:
                    System.out.println("再見！");
                    break;
                default:
                    System.out.println("無效選項");
            }
        }

        sc.close();
    }
}