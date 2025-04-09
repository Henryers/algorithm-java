# LeetCode55：跳跃游戏

和T45一样，只不过更简单，判断能否跳到终点即可

# 解答
和T45差不多，只保留关键的`farthest`就行

```java
public class code55 {
    public boolean canJump(int[] nums) {
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == farthest) {
                return false;
            }
        }
        return true;
    }
}
```