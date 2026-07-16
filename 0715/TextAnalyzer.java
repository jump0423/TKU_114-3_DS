import java.util.Scanner;

/**
 * 課堂實作題一：文字分析器
 * 輸入一行文字後，分析字元數、單字數、母音數、最長單字，
 * 並可搜尋關鍵字出現次數（忽略大小寫）。
 */
public class TextAnalyzer {

    // 讀取一行非空白文字，空字串或全空白會要求重新輸入
    public static String readNonBlankLine(Scanner sc, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (!input.trim().isEmpty()) {
                return input;
            }
            System.out.println("輸入不能是空白，請重新輸入。");
        }
    }

    // 將文字以空白切割成單字，處理連續空白
    public static String[] splitWords(String text) {
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return new String[0];
        }
        return trimmed.split("\\s+");
    }

    // 計算母音總數 a, e, i, o, u（忽略大小寫）
    public static int countVowels(String text) {
        int count = 0;
        String lower = text.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    // 找出最長單字
    public static String findLongestWord(String[] words) {
        if (words.length == 0) {
            return "";
        }
        String longest = words[0];
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    // 計算關鍵字出現次數（忽略大小寫，包含重疊計算採不重疊方式）
    public static int countKeywordOccurrences(String text, String keyword) {
        if (keyword.isEmpty()) {
            return 0;
        }
        String lowerText = text.toLowerCase();
        String lowerKeyword = keyword.toLowerCase();

        int count = 0;
        int index = 0;
        while ((index = lowerText.indexOf(lowerKeyword, index)) != -1) {
            count++;
            index += lowerKeyword.length();
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String original = readNonBlankLine(sc, "請輸入一行文字：");
        String trimmed = original.trim();

        System.out.println("原始字元數：" + original.length());
        System.out.println("移除前後空白後字元數：" + trimmed.length());

        String[] words = splitWords(trimmed);
        System.out.println("單字數量：" + words.length);

        int vowelCount = countVowels(trimmed);
        System.out.println("母音總數：" + vowelCount);

        String longestWord = findLongestWord(words);
        System.out.println("最長單字：" + longestWord);

        System.out.print("請輸入要搜尋的關鍵字：");
        String keyword = sc.nextLine().trim();
        int occurrences = countKeywordOccurrences(trimmed, keyword);
        System.out.println("關鍵字「" + keyword + "」出現次數：" + occurrences);

        sc.close();
    }
}
