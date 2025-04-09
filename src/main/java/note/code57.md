# LeetCode57：插入区间
给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，其中 intervals[i] = [starti, endi] 表示第 i 个区间的开始和结束，并且 intervals 按照 starti 升序排列。同样给定一个区间 newInterval = [start, end] 表示另一个区间的开始和结束。

在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。

返回插入之后的 intervals。

注意 你不需要原地修改 intervals。你可以创建一个新数组然后返回它。

# 示例

**示例 1：**

>输入：intervals = [[1,3],[6,9]], newInterval = [2,5]  
输出：[[1,5],[6,9]]

**示例 2：**

>输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]  
输出：[[1,2],[3,10],[12,16]]  
解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。

# 提示

- 0 <= intervals.length <= 104
- intervals[i].length == 2
- 0 <= starti <= endi <= 105
- intervals 根据 starti 按 升序 排列
- newInterval.length == 2
- 0 <= start <= end <= 105

# 解答
- 分类讨论，左侧 重叠 右侧
- 有重叠先别着急加，合并后记录该`扩容区间`的左侧和右侧
```java
public class code57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;      // 插入的newInterval是否加入到答案中
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 当前区间在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});  //（还没加入，那得加！）
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 当前区间在插入区间的左侧且无交集（都没遍历到并添加怎么可能有交集？placed判断也省了）
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});  // 插入区间在最右侧且无交集，要加啊！
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);  // 为啥不用遍历两遍？因为List里面套的<int[]>
        }
        return ans;
    }
}
```