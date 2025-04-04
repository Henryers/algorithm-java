package leetcode;

public class code50 {
    public double myPow(double x, int n) {
        if (n == 0) return 1D;
        double zhengx = x;
        // int全局最小无法转为最大，需要-2147483648 -> 2147483647,最后再乘一次x
        int zhengn = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double res = 1;
        while (zhengn > 0) {
            if ((zhengn & 1) == 1)
                res *= zhengx;
            zhengx *= zhengx;
            zhengn >>= 1;
        }
        if (n == Integer.MIN_VALUE)
            res *= x;
        return n > 0 ? res : 1 / res;
    }
}
