package leetcode;

public class code7 {
    public static int reverse(int x) {
        int flag = x >= 0 ? 1 : -1;
        int abx = Math.abs(x);
        int res = 0;
        while (abx > 0){
            int tmp = abx % 10;
            // 先判断
            if ((res > 214748364 && tmp > 7) || res > 214748365) return 0;
            res = res * 10 + tmp;
            abx = abx / 10;
        }
        if (res < 0) res = 0;
        res = flag * res;
        return res;
    }

    public static void main(String[] args) {
        int x = 1463847412;
        int res = reverse(x);
        System.out.println(res);
    }
}
