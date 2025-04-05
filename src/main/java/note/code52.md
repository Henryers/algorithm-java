# LeetCode_52：N皇后Ⅱ

问你n时有多少种填法

# 解答

看题解吧，写了很多了...

```java
public class code52 {
    public static int totalNQueens(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 如果你是合法的n，那么生成一个n对应的32位的二进制数，注意不是换算！！！
        // 而是比如 n = 6，那就对应111111
        // n == 32 是合法的，此时-1的补码正好是32个1
        // 补码：-1的绝对值1作为源码，取反得到31位1和最后一位的0，+1得到32个1即为补码
        // (1 << n) - 1 左移n位-1，如4，左移四位变成10000，-1变成 1111
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // --- 目的：利用这3个位运算的限制，来替代我方法1的record，提高效率 ---
    // colLim 列的限制，1的位置不能放皇后，0的位置可以
    // leftDialim 左斜线的限制，1的位置不能放皇后，0的位置可以
    // rightDialim 右斜线的限制，1的位置不能放皇后，0的位置可以
    // 例如：第0行： 00001000

    // 那么第一行就有三个限制：
    // 左斜线限制:   00010000
    // 该列的限制:   00001000
    // 右斜线限制:   00000100
    // 三限制求或:   00011100  说明第一行占1的位置不能放皇后

    // 第一行填上皇后:     01000000  (假设填到第二个位置)

    // 第二行的列限制:     01001000
    // 左斜线限制：两个左移 10100000  (包括第一次斜线的左移和第二次填的位置的左移，所以共两个1)
    // 右斜线限制：两个右移 00100010  (同理)
    // 求或:              11101010   (这是下一轮递归要做的事了)

    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        // 列上填的皇后 == limit == 111111...(n个1),说明填完了，找到一个解return
        if (colLim == limit) {
            return 1;
        }
        // colLim | leftDiaLim | rightDiaLim 三个限制取反，得到1可以放皇后，0不行
        // 举例子： 前面的(0)00001000 -或-> (0)00011100 -取反-> (1)11100011 -求与-> 左侧截掉
        // 取反是为了等下能截掉左侧24个0，也方便进递归 (0...0)11100011  ()里面的0有 32-8=24 个
        // 因此：所有候选能填皇后的位置，都在 pos(position)=1 的位置上
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        // 在所有为1的pos位置上进行尝试，每填好一个都去递归
        while (pos != 0) {
            //提取出候选皇后中最右侧的位置，就是最右边的1
            mostRightOne = pos & (~pos + 1);
            //减掉最右侧的1，使得下一次while拿到右边倒数第二个1
            pos = pos - mostRightOne;
            //也是跟方法1一样的递归，注意参数写法！
            res += process2(limit,
                    //我在最右侧的1位置放了皇后，所以接下来的列限制是它们两个或的形式
                    colLim | mostRightOne,
                    //左斜线的限制是或完左移
                    (leftDiaLim | mostRightOne) << 1,
                    //右斜线的限制是或完右移   >> 用符号位补    >>> 用0来补
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }
}
```