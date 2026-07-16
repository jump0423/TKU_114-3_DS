public class Q09_MatrixReport {
    public static void main(String[] args) {
        int[][] data = {
            {5, 8, 2},
            {9, 4, 7},
            {3, 6, 10}
        };
        System.out.println("第 1 列總和：" + rowSum(data, 1));
        System.out.println("第 2 欄總和：" + columnSum(data, 2));
        System.out.println("大於等於 7 的筆數：" + countAtLeast(data, 7));
        System.out.println("總和最大的列索引：" + findRowWithLargestTotal(data));
    }

    public static int rowSum(int[][] data, int row) {
        if (data == null || row < 0 || row >= data.length) {
            return -1;
        }
        int sum = 0;
        for (int value : data[row]) {
            sum += value;
        }
        return sum;
    }

    public static int columnSum(int[][] data, int column) {
        if (data == null || data.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int[] rowData : data) {
            if (column < 0 || column >= rowData.length) {
                return -1;
            }
            sum += rowData[column];
        }
        return sum;
    }

    public static int countAtLeast(int[][] data, int target) {
        int count = 0;
        if (data == null) {
            return count;
        }
        for (int[] rowData : data) {
            for (int value : rowData) {
                if (value >= target) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int findRowWithLargestTotal(int[][] data) {
        if (data == null || data.length == 0) {
            return -1;
        }
        int bestRow = -1;
        int bestSum = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            int sum = 0;
            for (int value : data[i]) {
                sum += value;
            }
            if (sum > bestSum) {
                bestSum = sum;
                bestRow = i;
            }
        }
        return bestRow;
    }
}