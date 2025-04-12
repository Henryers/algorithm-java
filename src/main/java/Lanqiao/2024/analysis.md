# 1. 报数游戏
​​![在这里插入图片描述](https://i-blog.csdnimg.cn/blog_migrate/e9f4e63aae3c9fe0a75f85a487f458fd.png)
## 思路
LCM找到最小公倍数为120，每10个数就是120，那么202420242020个数就是202420242020 × 12，最后4个数就再加48即可，最终答案为：2429042904288
## 代码
```java
public class Main {
	public static void main(String[] args) {
		System.out.print(202420242020L * 12 + 48);
	}
}
```
# 2. 类斐波那契循环数
![在这里插入图片描述](https://i-blog.csdnimg.cn/blog_migrate/5779309ddad35df7511f17ee161e8e27.png)
## 思路
找规律，然后用动态数组，倒序遍历一下，找到第一个满足条件的就行
我考试时没想出来规律，之后自己脑抽了用静态数组跑，每遍历一个数都开辟百万个空间，没想到这个地方耗时巨长...

找规律过程：
```
1 9 7      17           33              57       
1 9 7     1+9+7      9+7+1+9+7      7+17+9+7+17
1 9 7      17         2*17-1          2*33-9  
1 9 7   前面数字总和    后面都是： 2*前1个数-前k+1个数
```
## 代码
```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        for (int i = 10000000; i > 0; i--) {
            if (check(i)) {
                System.out.println(i);
                break;
            }
        }
    }

    public static boolean check(int n) {
        List<Integer> tmp = new ArrayList<>();
        List<Integer> dp = new ArrayList<>();
        tmp.add(0);
        dp.add(0);
        int k = 0; // k个进制位，比如197是3位数，k=3
        int tmp_n = n;
        // tmp数组放反过来的数字： 197 -> [0,7,9,1,...]
        while (tmp_n > 0) {
            k++;
            tmp.add(tmp_n % 10);  // 舍弃0索引
            tmp_n /= 10;
        }
        // 1,9,7后面接下来的第一个数one
        int one = 0;
        // 逆序，最终dp的初始值类似 197 -> [0,1,9,7,...]
        for (int i = k; i >= 1; i--) {
            dp.add(tmp.get(i));
            one += tmp.get(i); // 顺便累加
        }
        dp.add(one); // 累加好的数刚好作为下一个数
        for (int i = k + 2; i < n; i++) {
            // 规律核心：当前值 = 2倍前一位的数 - 前k+1位的数
            dp.add(dp.get(i - 1) * 2 - dp.get(i - k - 1));
            if (dp.get(i) == n) return true; // 找到了，就是答案
            if (dp.get(i) >= n) return false; // 超过了还没找到，肯定错误
        }
        return false;
    }
}
```
​
# 3. 分布式队列
【问题描述】
小蓝最近学习了一种神奇的队列：分布式队列。简单来说，分布式队列包含 N 个节点（编号为 0 至 N − 1，其中 0 号为主节点），其中只有一个主节点，其余为副节点。主/副节点中都各自维护着一个队列，当往分布式队列中添加元素时都是由主节点完成的（每次都会添加元素到队列尾部）；副节点只负责同步主节点中的队列。可以认为主/副节点中的队列是一个长度无限的一维数组，下标为 0, 1, 2, 3 . . . ，同时副节点中的元素的同步顺序和主节点中的元素添加顺序
保持一致。
由于副本的同步速度各异，因此为了保障数据的一致性，元素添加到主节点后，需要同步到所有的副节点后，才具有可见性。
给出一个分布式队列的运行状态，所有的操作都按输入顺序执行。你需要回答在某个时刻，队列中有多少个元素具有可见性。

【输入格式】
第一行包含一个整数 N，表示节点个数。
接下来包含多行输入，每一行包含一个操作，操作类型共有以下三种：
add、sync 和 query，各自的输入格式如下：
1. add element：表示这是一个添加操作，将元素 element 添加到队列中；
2. sync follower_id：表示这是一个同步操作，follower_id 号副节点会从主
   节点中同步下一个自己缺失的元素；
3. query：查询操作，询问当前分布式队列中有多少个元素具有可见性。

【输出格式】
对于每一个 query 操作，输出一行，包含一个整数表示答案。

【样例输入】
3
add 1
add 2
query
add 1
sync 1
sync 1
sync 2
query
sync 1
query
sync 2
sync 2
sync 1
query

【样例输出】
0
1
1
3

【样例说明】
执行到第一个 query 时，队列内容如下:
0：[1,2]
1：[]
2：[]
两个副节点中都无元素，因此答案为 0。
执行到第二个 query 时，队列内容如下:
0：[1,2,1]
1：[1,2]
2：[1]
只有下标为 0 的元素被所有节点同步，因此答案为 1。
执行到第三个 query 时，队列内容如下:
0：[1,2,1]
1：[1,2,1]
2：[1]
只有下标为 0 的元素被所有节点同步，因此答案为 1。
执行到第四个 query 时，队列内容如下:
0：[1,2,1]
1：[1,2,1]
2：[1,2,1]
三个元素都被所有节点同步，因此答案为 3。

【评测用例规模与约定】
对于 30% 的评测用例：1 ≤ 输入的操作数 ≤ 100。
对于 100% 的评测用例：1 ≤ 输入的操作数 ≤ 2000，1 ≤ N ≤ 10，1 ≤
f ollower_id < N，0 ≤ element ≤ 105。
## 思路
当时考试时看这么长的题干，以为这个题很难，直接跳过去了，血亏，结果看起来比较容易的食堂写了2小时......
n个节点n个队列，其实用一维数组就行
索引存放节点编号，0索引是主节点
值存放节点个数，比如 `queue[1] = 3` 代表副节点1同步了3个元素
add操作：主节点+1
sync操作：副节点+1（不能超过主节点个数）
query操作：找到当前一维数组的最小值即可

省流：看个图就行
![在这里插入图片描述](https://i-blog.csdnimg.cn/blog_migrate/58d69077401e02abee5ad40ca62994f1.png)


## 代码
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine(); // 消耗掉 nextInt() 后的换行符
		// N 个节点的队列
		int[] queue = new int[N];
		// 存放所有 query 答案的数组
		List<Integer> res = new ArrayList<>();
		String order;
		while (sc.hasNextLine()) {
			order = sc.nextLine();
			if (order.startsWith("add")) {
				queue[0]++;
			} else if (order.startsWith("sync")) {
				int num = Integer.parseInt(order.substring(5));
				queue[num] = queue[0] == queue[num] ? queue[0] : ++queue[num];
			} else if (order.startsWith("query")) {
				// 查出 queue 数组最小值（即同步长度最短的队列）
				int min = Integer.MAX_VALUE;
				for (int i = 0; i < N; i++) {
					min = Math.min(min, queue[i]);
				}
				res.add(min);
			}
		}
		for (int result : res) {
			System.out.println(result);
		}
		sc.close();
	}
}
```

# 4. 食堂
【问题描述】
S 学校里一共有 a2 个两人寝、a3 个三人寝，a4 个四人寝，而食堂里有 b4个四人桌和 b6 个六人桌。学校想要安排学生们在食堂用餐，并且满足每个寝室里的同学都在同一桌就坐，请问这个食堂最多同时满足多少同学用餐？

【输入格式】
采用多组数据输入。  输入共 q + 1 行。
第一行为一个正整数 q 表示数据组数。
后面 q 行，每行五个非负整数 a2，a3，a4，b4，b6 表示一组数据。

【输出格式】
输出共 q 行，每行一个整数表示对应输入数据的答案。

【样例输入】
2
3 0 1 0 1
0 2 2 1 1

【样例输出】
6
10

【样例说明】
对于第一组数据，只有一个六人桌，因此最多安排三个两人寝的同学就餐，
答案为 (2 + 2 + 2) = 6。
对于第二组数据，用一个六人桌安排两个三人寝的同学，用一个四人桌安
排一个四人寝的同学，答案为 (3 + 3) + (4) = 10。

【评测用例规模与约定】
对于 20% 的评测用例，保证 a2 + a3 + a4 ≤ 8。
对于 100% 的评测用例，保证 q ≤ 100，b4 + b6 ≤ a2 + a3 + a4 ≤ 100。
## 思路
当时考试时我只想到根据不同情况去 `if else` 枚举，本来想会不会有更好的方法，但发现别人的代码好像暴力枚举的方式和我差不多。
感觉题目看起来不是很难，但是根据贪心策略，分类的情况很多，很容易数漏情况，我也无法保证我的代码就完全正确，至少我列出来的几种示例情况都符合要求，这里放的代码仅供大家参考。
## 代码
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt(); // 数据组数
		List<Integer> ans = new ArrayList<>(); // 存放q组数据的结果
		// 循环q组数据
		while (q-- > 0) {
			int res = 0; // 存放这组数据的答案
			int a2 = sc.nextInt();
			int a3 = sc.nextInt();
			int a4 = sc.nextInt();
			int b4 = sc.nextInt();
			int b6 = sc.nextInt();
			int max_shitang = b4 * 4 + b6 * 6; // 食堂最大容量
			int all_student = a2 * 2 + a3 * 3 + a4 * 4; // 学生总人数
			// 一、优先把所有的3人寝室都成对装在b6里，放不下or单数再放到b4中
			if ((b6 * 6) > (a3 * 3)) {
				// 装得下,成对装入b6
				res += (a3 / 2) * 6; // a3 / 2 表示成对数量
				b6 -= (a3 / 2); // 剩余的桌子数
				// 放好成对a3后，考虑将a4都放入b4
				if (b4 >= a4) {
					// b4能装满a4，a4全都装进去
					res += a4 * 4;
					b4 -= a4;
					// 再把a2全部装进b4
					if ((a2 * 2) > (b4 * 4)) {
						// a2能装满b4
						res += a2 * b4 * 4;
						a2 -= (b4 * 2);
						// 那就在b6里继续放，如果不能完全放满，那才将单独的a3放进去
						if ((a2 * 2) > (b6 * 6)) {
							// a2能装满b6，那么食堂都被装满了，输出答案
							ans.add(max_shitang);
						}
						// 不能完全放满，将单独的a3放进去
						else {
							// 如果剩2个位置没装满，那就填a3，即所有学生共有2人没坐
							if ((a2 * 2 + 2) == b6 * 6)
								ans.add(all_student - 2);
							// 此时剩下的位置 > 2， 说明a3也装得下，所有学生都能坐进去
							else
								ans.add(all_student);
						}
					} else {
						// a2不能装满b4,那就成对装进去b4,剩下的一个/0个a2和单独的a3放进a6食堂
						// 省流：所有学生都能坐满
						ans.add(all_student);
					}
				} else {
					// a4太多把b4食堂挤满了，能进就进
					res += b4 * 4;
					a4 -= b4;
					// 再将a4 a2成对装进b6
					// 1. 成对能塞满b6那最好
					if (a4 >= b6 && a2 >= b6) {
						ans.add(max_shitang);  // 两个食堂都被塞满了
					} else {
						// 2. 成对塞不满，先把所有成对的加进去
						res += Math.min(a2, a4) * 6;
						b6 -= Math.min(a2, a4);
						a2 -= Math.min(a2, a4);
						a4 -= Math.min(a2, a4);
						if (a2 < a4) {
							// 2.1 a4 过多，a2不够
							// 此时只剩下a4，可能还有多余的一个a3
							// 没办法了，只放a3的话还不如直接放a4
							// 所有a4都装进去
							ans.add(res + b6 * 4);
						} else {
							// 2.2 a2过多，a4不够
							// 此时只剩下a2，可能还有多余的一个a3
							// 看看a2能不能装满
							if ((a2 * 2) > (b6 * 6)) {
								// a2能装满b6，那么食堂都被装满了，输出答案
								ans.add(max_shitang);
							}
							// 不能完全放满，将单独的a3放进去
							else {
								// 如果剩2个位置没装满，那就填a3，即所有食堂共剩1个位置没坐人
								if ((a2 * 2 + 2) == b6 * 6)
									ans.add(max_shitang - 1);
								// 此时剩下的位置 > 2， 说明a3也装得下，所有学生都能坐进去
								else
									ans.add(all_student);
							}
						}
					}
				}
			}
			// 二、3人寝室把b6挤满了，剩下的先装a4的进去，再装成对的a2，剩下一个a2的话，如果还有a3就放a3
			else {
				int a3_single = a3 % 2; // 记录a3是否有单独的
				a3 -= b6 * 2; // a3 有 b6 *2 个寝室去b6食堂坐了
				res += b6 * 6; // b6食堂都坐满了人
				if (a4 >= b4) {
					// 1. a4全部挤满b4，然后直接return
					ans.add(res + b4 * 4);
				} else {
					// 2. 把a4都放进去，再装成对的a2
					res += a4 * 4;
					b4 -= a4; // 一部分数量被a4寝室坐了
					if (a2 * 2 > b4 * 4) {
						// 3. a2寝室都能将b4挤满
						ans.add(res + b4 * 4);
					} else {
						// 4. a2寝室成对放入，其他放a3
						int a2_single = a2 % 2; // 是否成对
						b4 -= a2 / 2; // 放 a2/2 个位置给a2坐
						res += (a2 / 2) * 4; // 确定坐人res才累加
						if (a3 > 0 && a3 >= b4) {
							ans.add(res + b4 * 3);  // a3装满b4
						} else if (a2_single == 1) {
							ans.add(res + a3 * 3 + 2);  // 装全部a3，有a2再放入a2
						} else {
							ans.add(res + a3 * 3);  // 人都坐满了，输出结果
						}
					}
				}
			}
		}
		// 打印q组数据结果
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i));
		}
		sc.close();
	}
}
```
# 5. 最优分组
【问题描述】
小蓝开了一家宠物店，最近有一种 X 病毒在动物之间进行传染，小蓝为了以防万一打算购买测试剂对自己的宠物进行病毒感染测试。为了减少使用的测试剂数目，小蓝想到了一个好方法：将 N 个宠物平均分为若干组，使得每组恰好有 K 只宠物，这样对同一组的宠物进行采样并混合后用一个试剂进行检测，如果测试结果为阴性则说明组内宠物都未感染 X 病毒；如果是阳性的话则需要对组内所有 K 只宠物单独检测，需要再消耗 K 支测试剂（当 K = 1 时，就没必要再次进行单独检测了，因为组内只有一只宠物，一次检测便能确认答案）。
现在我们已知小蓝的宠物被感染的概率为 p，请问 K 应该取值为多少才能使得期望的测试剂的消耗数目最少？如果有多个答案输出最小的 K。

【输入格式】
第一行，一个整数 N。
第二行，一个浮点数 p。

【输出格式】
输出一行，一个整数 K 表示答案。

【样例输入】
1000
0.05

【样例输出】
5

【评测用例规模与约定】
对于 30% 的评测用例：1 ≤ N ≤ 10。
对于 60% 的评测用例：1 ≤ N ≤ 1000。
对于 100% 的评测用例：1 ≤ N ≤ 106，0 ≤ p ≤ 1。
## 思路

一组 K 只宠物中，一次检测为阳性(其中至少有一只被感染)的概率
$$P = 1-(1-p)^k$$

得到一组需要再次消耗试剂的期望
$$P × K$$

得到所有组的消耗试剂的期望
$$P × K × 组数 zu = P × N$$

刚开始分成 zu 个组，就要消耗 zu 这么多只试剂
$$P × N + zu$$

总和就是期望target
$$(1-(1-p)^k)*N+zu$$

**整体思路过程：**
枚举不同的K算出不同期望target，记录最小target对应的minK，即为答案
## 代码
```java
import java.util.Scanner;

public class Main {
	public static int n = 0; // 进制位

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		double p = sc.nextDouble();
		int K = 1; // 每组要K只宠物
		int minK = 1; // 答案
		double min_target = N; // 最少消耗试剂数目期望P
		for (int i = 2; i <= N; i++) {
			K = i;
			if (N % K != 0) continue; // 倍数判断，不成倍直接跳过
			int zu = N / K; // 分的组数
			// 计算当前K下，对应的target和min_target比较
			double target = (1 - Math.pow(1 - p, K)) * N + zu;
			// System.out.println("当前" + K + "只的试剂消耗数目期望P: " + target);
			if (target < min_target) {
				minK = K; // 更新记录
				min_target = target;
			}
		}
		System.out.println(minK);
	}
}
```
# 6. 星际旅行
【问题描述】
小明国庆节准备去某星系进行星际旅行，这个星系里一共有 n 个星球，其中布置了 m 道双向传送门，第 i 道传送门可以连接 ai，bi 两颗星球（ai , bi 且任意两颗星球之间最多只有一个传送门）。
他看中了一款 “旅游盲盒”，一共有 Q 个盲盒，第 i 个盲盒里的旅行方案规定了旅行的起始星球 xi 和最多可以使用传送门的次数 yi。只要从起始星球出发，使用传送门不超过规定次数能到达的所有星球都可以去旅行。
小明关心在每个方案中有多少个星球可以旅行到。小明只能在这些盲盒里随机选一个购买，他想知道能旅行到的不同星球的数量的期望是多少。

【输入格式】
输入共 m + Q + 1 行。
第一行为三个正整数 n，m，Q。
后面 m 行，每行两个正整数 ai，bi。
后面 Q 行，每行两个整数 xi，yi。

【输出格式】
输出共一行，一个浮点数（四舍五入保留两位小数）。

【样例输入】
3 2 3
1 2
2 3
2 1
2 0
1 1

【样例输出】
2.00

【样例说明】
第一个盲盒可以旅行到 1, 2, 3。
第二个盲盒可以旅行到 2。
第三个盲盒可以旅行到 1, 2。
所以期望是 (3 + 1 + 2)/3 = 2.00。

【评测用例规模与约定】
对于 20% 的评测用例，保证 n ≤ 300。
对于 100% 的评测用例，保证 n ≤ 1000，m ≤ min{$\frac {n(n−1)}{2}$, 5n}，Q ≤ 50000，0 ≤ $y_i$ ≤ n。

## 思路
先构建邻接表，每个节点有一个点集，存放其所有邻居（相邻之间有线连接的星球就互为邻居）
再利用 BFS 宽度优先遍历，从 x 点开始向外扩展 y 层邻居，看能经过多少邻居，将这些所有星球放到集合中，集合的个数就是当前旅行方案能经过的星球总个数
最后加和得到 allResult，再除以Q ，求出期望即可
## 代码
```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static List<List<Integer>> biglist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int Q = sc.nextInt();
		biglist = new ArrayList<>();
		// 每个元素都有一个点集，点集的索引是自己，集合是邻居，舍弃0号索引的点集
		for (int i = 0; i <= n; i++) {
			List<Integer> list = new ArrayList<>();
			biglist.add(list);
		}
		// 输入m行，构建邻接表
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			biglist.get(a).add(b); // a 的所有邻居加入 b
			biglist.get(b).add(a); // b 的所有邻居加入 a
		}
		// Q行数据，表示Q个方案，期望 P = 所有方案的到达星球数量总和 allResult / Q
		int allResult = 0;
		for (int i = 0; i < Q; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			// 调用函数，求x开始走y步，能到达多少个星球，即set的数量
			allResult += bfs(x, y);
		}
		double E = (allResult * 1.0) / Q;
		// 保留两位小数
		System.out.printf("%.2f", E);
		sc.close();
	}

	// bfs，求从星球x开始走y步，能到达多少个星球，即set的数量
	public static int bfs(int x, int y) {
		HashSet<Integer> set = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		queue.add(x);
		set.add(x);
		while (!queue.isEmpty() && y > 0) {
			// 记录该层的节点数，一次循环弹出这么多次星球
			int ceng_num = queue.size();
			for (int k = 0; k < ceng_num; k++) {
				int planet = queue.poll();
				// 将其所有"新"邻居加进队列
				for (int i = 0; i < biglist.get(planet).size(); i++) {
					// planet 的第 i个邻居
					int neighbor = biglist.get(planet).get(i);
					// 如果是新邻居才加进队列，避免死循环！
					if (!set.contains(neighbor)) {
						queue.add(neighbor);
						set.add(neighbor); // 并且新邻居加到集合里
					}
				}
			}
			y--; // 一层扩散完毕，可用步数-1
		}
		return set.size();
	}
}
```

# 7. LITS游戏
![在这里插入图片描述](https://i-blog.csdnimg.cn/blog_migrate/27ad69c70f1a678c30862ce87d1dcd0b.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/blog_migrate/0aae8301417d21ba5b0cc1a20cde274d.png)
## 思路
这道题我水平有限，没想出来，看别人的题解好像要枚举一堆情况，所以我选择性放弃了...
# 8. 拼十字
![在这里插入图片描述](https://i-blog.csdnimg.cn/blog_migrate/26af2a07dfba393f7e3e868057cbd1c7.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/blog_migrate/3ee72b5626a24cda6fcc2d20bfd451ad.png)
## 思路
涉及 排序 和 树状数组 的知识，树状数组的讲解可以看其他博客
或者看b站这个动画视频理解下，我是感觉这个动画好理解一些：
[b站树状数组动画讲解](https://www.bilibili.com/video/BV1ce411u7qP)
我理解后感觉挺难用文字表述清楚，太头晕了，于是画成图供大家参考
下面代码中，两个树状数组的操作函数如下：
![在这里插入图片描述](https://i-blog.csdnimg.cn/blog_migrate/3023fe52d145b7e9ffc56832a498e715.png)
举例，具体讲解过程的图如下：
![在这里插入图片描述](https://i-blog.csdnimg.cn/blog_migrate/23d244bca4e62771d963b7103d9332cf.png)
## 代码
这个代码是我看B站一位UP主写的 [b站蓝桥杯Java代码讲解](https://www.bilibili.com/video/BV1gt421P7pX)
不过他没讲解得很细致，我刚开始还不知道啥叫树状数组，看得很吃力，后来补了下其他知识才理清思路

颜色的话，他是分成三个树状数组，然后放到一个大的 `tree[3][N]` 中
长度的话，在外层的排序就可以解决了（具体见我上面画的图）
而里面的树状数组只是包含宽度的数据，因此第一次看我疑惑了好久
```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static final int N = 100010;
    public static int[][] tree = new int[3][N];

    // 在树状数组中增加一个宽度 p
    public static void add(int id, int p) {
        for (int i = p; i < N; i += (i & -i)) {
            tree[id][i] += 1;
        }
    }

    // 获取树状数组中所有宽度小于等于 p 的计数和
    public static int get(int id, int p) {
        int s = 0;
        for (int i = p; i > 0; i -= (i & -i)) {
            s += tree[id][i];
        }
        return s;
    }

    public static class Node {
        int x, y, z;

        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    // 先按长降序排序，再按宽降序排序
    public static class NodeComparator implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return (a.x == b.x) ? (a.y > b.y ? -1 : (a.y == b.y ? 0 : 1)) : (a.x > b.x ? -1 : 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0;
        int mod = (int) 1e9 + 7;
        Node[] nodes = new Node[N]; // 舍弃0位置
        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            nodes[i] = new Node(x, y, z);
        }
        Arrays.sort(nodes, 1, n + 1, new NodeComparator());
        for (int i = 1; i <= n; i++) {
            if (nodes[i].z != 0) ans += get(0, nodes[i].y - 1);
            if (nodes[i].z != 1) ans += get(1, nodes[i].y - 1);
            if (nodes[i].z != 2) ans += get(2, nodes[i].y - 1);
            ans %= mod;
            if (nodes[i].z == 0) add(0, nodes[i].y);
            if (nodes[i].z == 1) add(1, nodes[i].y);
            if (nodes[i].z == 2) add(2, nodes[i].y);
        }
        System.out.println(ans % mod);
        sc.close();
    }
}
```
