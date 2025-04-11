# LeetCode_935：骑士拨号器

就是一个电话，“🐎”字跳n步，多少种跳法？

# 解答

- 由于每次跳只和上一次有关，因此搞个滚动数组就好
- 状态有限，先记录每个数字可以往哪个地方跳？（实际实现是这个地方上一次从哪里跳过来）
```java
public class code935 {
    public int knightDialer(int n) {
        int mod = 1000000007;
        // 0-9每个数字下一次能跳到的位置moves（感觉像是上一步的moves...）
        int[][] moves = new int[][]{
                {4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0},
                {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}
        };
        // 使用滚动数组优化空间复杂度，因为当前n步只依赖n-1步的状态
        int[][] dp = new int[2][10];
        // 初始状态，第一步在每个位置都有可能
        Arrays.fill(dp[1], 1);
        for (int i = 2; i <= n; i++) {
            Arrays.fill(dp[i & 1], 0);  // 重置当前步数的状态
            for (int j = 0; j <= 9; j++) {
                for (int k : moves[j]) {  // 前一步为k（moves不就是前一步？？？）
                    // 从前一步贡献来
                    dp[i & 1][j] += dp[~i & 1][k];
                    dp[i & 1][j] %= mod;
                }
            }
        }
        // 对跳了n步到数字i[0-9]所有可能情况进行累加和取模
        int ans = 0;
        for (int x : dp[n & 1]) {
            ans += x;
            ans %= mod;
        }
        return ans % mod;
    }
}
```