package leetcode;

public class code7 {
    public static int reverse(int x) {
        int flag = x >= 0 ? 1 : -1;
        int abx = Math.abs(x);
        int res = 0;
        while (abx > 0){
            int tmp = abx % 10;
            // 先判断，其实如果原先就是十位数，不管正负号，原先的最高位肯定是1或2
            // 逆转后，原先的最高位到这里变成了最低位，这两个数都<=7，所以不用判断个位
            if (res > 214748364) return 0;
            res = res * 10 + tmp;
            abx = abx / 10;
        }
        res = flag * res;
        return res;
    }

    public static void main(String[] args) {
        int x = 1463847412;
        int res = reverse(x);
        System.out.println(res);
    }
}
