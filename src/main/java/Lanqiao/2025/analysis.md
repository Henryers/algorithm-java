# 1. 逃离高塔
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/d07c83d8921a425db76a08fc5ca2401f.png)
## 思路
简单遍历就好，为了不溢出得取模，因为只看个位数，所以取模不会对结果产生影响

## 代码
```java
public class Main {
	public static void main(String[] args) {
		int res = 0;
		for (int i = 1; i <= 2025; i++) {
			int n = (((i * i) % 10) * i) % 10;
			if (n == 3)
				res++;
		}
		System.out.print(res);
	}
}
```

# 2. 消失的蓝宝
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/b122b5f632824642b933b26b90374080.png)
## 思路
直接模拟，第一个符合要求的就返回  
由于直接遍历可能会超时，因此选择一种条件跳着遍历，在循环里判断即可
## 代码
```java
public class Main {
	public static void main(String[] args) {
		long res = 0;
		for (long N = 20260411; N < Long.MAX_VALUE; N += 20250412) {
			if ((N + 20250412) % 20240413 == 0) {
				res = N;
				break;
			}
		}
		System.out.print(res);
	}
}
```

# 3. 电池分组
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/0f491b48db1944ef93766dee314e656e.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/9b064470b38b42f4a6366b09e4c00504.png)
## 思路
如果两组异或和相等的话，那他们两个再异或不就=0？那不就相当于所有电池都异或完=0就行？（感觉有点简单不知道有没有理解错...）
## 代码
```java
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] res = new int[T + 1]; // T组答案(舍弃0索引)
		for (int i = 1; i <= T; i++) {
			int sum = 0;
			int N = sc.nextInt(); // 本组电池的数量
			for (int j = 1; j <= N; j++) {
				int element = sc.nextInt();
				sum = sum ^ element;
			}
			if (sum == 0)
				res[i] = 1;
			else
				res[i] = 0;
		}
		for (int i = 1; i <= T; i++) {
			if (res[i] == 1)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
		sc.close();
	}
}
```

# 4. 魔法科考试
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/ffaff0d321824dfca2f5ebb79a44bb17.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/b81d782db0f44415941b0fc080e6aa0d.png)
## 思路
按题目模拟就行，好像也没涉及啥算法？  
记得用 `set` 去重一下
## 代码
```java
import java.util.HashSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[m];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		for (int i = 0; i < m; i++) {
			b[i] = sc.nextInt();
		}
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int tmp = a[i] + b[j];
				if (tmp <= m + n && isSu(tmp)) {
					set.add(tmp);
				}
			}
		}
		System.out.println(set.size());
		sc.close();
	}

	public static boolean isSu(int n) {
		for (int i = 2; i < n - 1; i++) {
			if (n % i == 0)
				return false; // 能被整除，不是素数
		}
		return true;
	}
}
```

# 5. 爆破
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/dc24f7e2ddfa41a18a44258c451fba3d.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/98508db9be4a4b3587e0f140a886e04c.png)
## 思路
依题意得：我感觉我脑袋要被爆破了...  
考前还去瞄了一眼`并查集`，一看题就觉得包是`最小生成树`，死脑子硬是想不起来，11点磕到13点最后交白卷早知道还不如11点开摆直接交算了😭

## 代码
下面的代码是下午补的，太久没刷题连冒泡排序，比较器啥的都不会，就下面这段我写到快4点才写完还不知道对不对...
```java
import java.util.*;

public class Main {
    public static class Node {
        int x;
        int y;
        int r;

        public Node() {
        }

        public Node(int xx, int yy, int rr) {
            this.x = xx;
            this.y = yy;
            this.r = rr;
        }
    }

    public static class Edge {
        Node from;
        Node to;
        double edgeLen;

        public Edge() {
        }

        public Edge(Node fromNode, Node toNode, double len) {
            this.from = fromNode;
            this.to = toNode;
            this.edgeLen = len;
        }
    }

    public static class MySets {
        public HashMap<Node, List<Node>> setMap; // 拿到一个点所对应的点集，setMap为多个点集

        public MySets(List<Node> nodes) {
            setMap = new HashMap<>();
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(cur); // 刚开始每个点都自成一个点集
                setMap.put(cur, set);
            }
        }

        // 判断两个点是否在同一个集合中
        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet; // 看两个点所在的点集，地址是否相同
        }

        // 合并集合，把to所在的集合中，所有的元素都加到fromSet集合里
        public void unionSets(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node toNode : toSet) {
                fromSet.add(toNode); // 两点连通，集合合并到fromSet里
                setMap.put(toNode, fromSet); // 修改toNode节点对应的点集，由toSet改为fromSet
            }
        }
    }

    public static MySets mysets;
    public static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[][] boom = new int[N][3]; // N个炸弹的信息
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            boom[i][0] = sc.nextInt(); // x
            boom[i][1] = sc.nextInt(); // y
            boom[i][2] = sc.nextInt(); // r
            nodes.add(new Node(boom[i][0], boom[i][1], boom[i][2]));
        }
        mysets = new MySets(nodes);

        int edgeNum = (N * (N - 1)) / 2; // 边的数量
        Edge[] edges = new Edge[edgeNum];
        int edgeIndex = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double d = getDistance(boom[i][0], boom[i][1], boom[j][0], boom[j][1]) - boom[i][2] - boom[j][2];
                if (d < 0) {
                    // 合并
                    mysets.unionSets(nodes.get(i), nodes.get(j));
                    d = 0;
                }
                edges[edgeIndex++] = new Edge(nodes.get(i), nodes.get(j), d);
            }
        }

        // 比较器，按边长度排序
        Arrays.sort(edges, (edge1, edge2) -> Double.compare(edge1.edgeLen, edge2.edgeLen));

        // 最小生成树
        // 对边遍历，舍弃距离为0的边
        // 判断边的左右两侧点是否是在一个集合里？
        // 如果是则跳过
        // 如果不是，须进行union
        double res = 0;
        for (int i = 0; i < edgeNum; i++) {
            if (edges[i].edgeLen == 0)
                continue;
            if (mysets.isSameSet(edges[i].from, edges[i].to)) {
                continue;
            }
            res += edges[i].edgeLen;
            mysets.unionSets(edges[i].from, edges[i].to);
        }
        System.out.print(res);

    }

    public static double getDistance(int x1, int y1, int x2, int y2) {
        int X = Math.abs(x1 - x2) * Math.abs(x1 - x2);
        int Y = Math.abs(y1 - y2) * Math.abs(y1 - y2);
        return Math.sqrt(X + Y);
    }
}
```

# 6. 数组翻转
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/4e4b0b7550ed44739fc702a65e76271f.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/d3e9b2a6436041eca880f5d632540ba0.png)
## 思路
`上一题当时没想出来，考试时先写了这道`

翻转后就是两段数字相等的合并成一段大的，所以只需找到翻转前相同数字和最大的两段  
多说无益，我语文小白难以表述，直接上图：
```
example    4 4 5 3 3 3 2 1 3 4 4 3 3 3 5 5 4 12
hashmap    4   [8, 8, 4]
           5   [5, 10]
           3   [9, 3, 9]
           2   [2]
           1   [1]
           12  [12]
sorted     4   [8, 8, 4]
           5   [10, 5]
           3   [9, 9, 3]
           2   [2]
           1   [1]
           12  [12]         
select_2   4   8 + 8 = 16
           5   10 + 5 = 15
           3   9 + 9 = 18   (max)
           2   2
           1   1
           12  12
answer     18       
```
来个好看点的图~
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/05c13135a9e14b868a0a955c66e3c1a5.png)

不知道大家有没有看懂，我的hashmap的键key是一个数字，值是一个列表，代表相同数字组成的一小段一小段，比如原始数组刚开始那一段是`[4, 4]`，那这段总和就是8，加到map里
## 代码
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] nums = new int[N];
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		for (int i = 0; i < N; i++) {
			if (map.get(nums[i]) == null) {
				map.put(nums[i], new ArrayList<>());
			}
			int sum = nums[i];
			while (i + 1 < N && nums[i + 1] == nums[i]) {
				sum += nums[i];
				i++;
			}
			map.get(nums[i]).add(sum);
		}
		int max = 0;
		Set<Entry<Integer, List<Integer>>> set = map.entrySet();
		for (Entry<Integer, List<Integer>> ele : set) {
			int key = ele.getKey();
			List<Integer> list = ele.getValue();
			int[] newlist = new int[list.size()];
			for (int i = 0; i < list.size(); i++) {
				newlist[i] = list.get(i);
			}
			Arrays.sort(newlist);
			int sum = list.size() > 1 ? 
				newlist[list.size() - 1] + newlist[list.size() - 2] 
				: newlist[list.size() - 1];
			max = Math.max(max, sum);
		}
		System.out.print(max);
	}
}
```

# 7. 2的幂
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/b1043850cf9f4511ad38c2e119a9d4af.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/12c3b9483e214c71a5bd5ea07e5b3b2e.png)
# 8. 研发资源分配
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/954f9d09aabb4358a060b0f470a1f315.png)
![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/242ce1d16c454e8a9827857d94e14e1e.png)

