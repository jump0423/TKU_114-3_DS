import java.util.Scanner;

/**
 * 課堂實作題三：除錯挑戰（已修正）
 *
 * 1. 編譯錯誤：println 結尾缺分號 -> 補上 ;
 * 2. 陣列越界：迴圈用 i <= scores.length -> 改為 i < scores.length
 * 3. 字串比較錯誤：command == "exit" -> 改用 "exit".equals(command.trim())
 * 4. 整數除法邏輯錯誤：total / scores.length -> (double) total / scores.length
 * 5. Scanner 換行問題：nextInt() 後補一次 sc.nextLine() 清換行
 * 6. Breakpoint：設在 total += scores[i]，Step Over 觀察 i / total 變化，
 *    確認迴圈跑到 i=2 結束，不再越界
 */

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine(); // 清除 nextInt() 留下的換行字元

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if ("exit".equals(command.trim())) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}
