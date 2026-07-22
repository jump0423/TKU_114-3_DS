import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        while (true) {
            System.out.print("請輸入成績（輸入 -1 結束）：");
            int input = scanner.nextInt();

            if (input == -1) {
                break;
            }

            if (input < 0 || input > 100) {
                System.out.println("成績必須介於 0 到 100 之間，請重新輸入。");
                continue;
            }

            scores.add(input);
        }

        printSummary(scores);

        scanner.close();
    }

    public static void printSummary(ArrayList<Integer> scores) {
        if (scores.size() == 0) {
            System.out.println("沒有輸入任何成績。");
            return;
        }

        System.out.println("筆數：" + scores.size());
        System.out.println("平均：" + calculateAverage(scores));
        System.out.println("最高：" + findMax(scores));
        System.out.println("最低：" + findMin(scores));
        System.out.println("及格名單：" + filterPassed(scores));
    }

    public static double calculateAverage(ArrayList<Integer> scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return (double) total / scores.size();
    }

    public static int findMax(ArrayList<Integer> scores) {
        int max = scores.get(0);
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    public static int findMin(ArrayList<Integer> scores) {
        int min = scores.get(0);
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    public static ArrayList<Integer> filterPassed(ArrayList<Integer> scores) {
        ArrayList<Integer> passed = new ArrayList<>();
        for (int score : scores) {
            if (score >= 60) {
                passed.add(score);
            }
        }
        return passed;
    }
}
