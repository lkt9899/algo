import java.io.*;
import java.util.*;

public class Main_17471 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 11;

    static int N, ans = Integer.MAX_VALUE;

    static int[] people = new int[MAX];
    static List<Integer>[] list = new ArrayList[MAX];
    static int area1 = 0;
    static int area2 = 0;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        dfs(1);
        if (ans == Integer.MAX_VALUE)
            ans = -1;
        System.out.println(ans);
    }

    static void input() throws Exception {
        // total node
        N = Integer.parseInt(br.readLine());

        // people
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        // adjList
        for (int from = 1; from <= N; from++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            list[from] = new ArrayList<>();
            for (int j = 0; j < cnt; j++) {
                int to = Integer.parseInt(st.nextToken());
                list[from].add(to);
            }
        }
    }

    static void dfs(int node) {
        if (node == N + 1) {
            int sum1 = 0;
            int sum2 = 0;
            if (check(area1) && check(area2)) {
                for (int i = 1; i <= N; i++) {
                    if ((area1 & (1 << i)) != 0) {
                        sum1 += people[i];
                    } else if ((area2 & (1 << i)) != 0) {
                        sum2 += people[i];
                    }
                }

                int min = Math.abs(sum1 - sum2);
                ans = ans < min ? ans : min;
            }
            return;
        }

        area1 += (1 << node);
        dfs(node + 1);
        area1 -= (1 << node);

        area2 += (1 << node);
        dfs(node + 1);
        area2 -= (1 << node);
    }

    static boolean check(int area) {
        Deque<Integer> dq = new ArrayDeque<>();
        int visit = 0;
        for (int i = 1; i <= N; i++) {
            if ((area & (1 << i)) != 0) {
                dq.add(i);
                visit |= (1 << i);
                break;
            }
        }

        while (!dq.isEmpty()) {
            int cur = dq.poll();

            for (int next : list[cur]) {
                if ((area & (1 << next)) != 0 && (visit & (1 << next)) == 0) {
                    visit |= (1 << next);
                    dq.add(next);
                }
            }
        }

        if (visit == area)
            return true;
        return false;
    }
}
