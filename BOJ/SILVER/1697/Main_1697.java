import java.io.*;
import java.util.*;

public class Main_1697 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 100001;

    static int N, K;
    static int[] dp = new int[MAX];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        solve();
        print();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(dp, -1);
    }

    static void solve() {
        Deque<Integer> dq = new ArrayDeque<>();
        dp[N] = 0;

        dq.add(N);
        while (!dq.isEmpty()) {
            int cur = dq.poll();

            if (cur == K)
                break;

            if (cur * 2 < MAX) {
                if (dp[cur * 2] == -1)
                    dp[cur * 2] = dp[cur] + 1;
                else
                    dp[cur * 2] = dp[cur * 2] > dp[cur] + 1 ? dp[cur] + 1 : dp[cur * 2];

                dq.add(cur * 2);
            }

            for (int i = -1; i <= 1; i++) {
                int next = cur + i;
                if (next < 0 || next >= MAX)
                    continue;

                if (dp[next] == -1 || dp[next] > dp[cur] + 1) {
                    dp[next] = dp[cur] + 1;
                    dq.add(next);
                }
            }
        }
        sb.append(dp[K] + "");
    }

    static void print() throws Exception {
        System.out.println(sb);
    }
}
