public class Q04_LoopRepair {
    public static void main(String[] args) {
        System.out.println(sumOddRange(3, 7));
        System.out.println(sumOddRange(7, 3));
        System.out.println(sumOddRange(2, 2));
        System.out.println(sumOddRange(-3, 3));
    }

    public static int sumOddRange(int start, int end) {
        int lo = start;
        int hi = end;
        if (lo > hi) {
            int temp = lo;
            lo = hi;
            hi = temp;
        }

        int sum = 0;
        for (int i = lo; i <= hi; i++) {
            int remainder = i % 2;
            if (remainder == 1 || remainder == -1) {
                sum += i;
            }
        }
        return sum;
    }
}