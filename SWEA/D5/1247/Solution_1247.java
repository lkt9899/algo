import java.io.*;
import java.util.*;

public class Solution_1247 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int N_MAX = 12;

    static int N, ans;

    static class Axis {
        int x, y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDist(Axis b) {
            return Math.abs(this.x - b.x) + Math.abs(this.y - b.y);
        }
    }

    static Axis[] v = new Axis[N_MAX];
    static int[][] dp = new int[N_MAX][1 << N_MAX];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }

        print();
    }

    static void run() throws Exception {
        init();
        input();
        sb.append(TSP(0, 1) + "\n");
    }

    static void init() {
        ans = 0;
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
    }

    static void input() throws Exception {
        int x, y;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        v[0] = new Axis(x, y);

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        v[N + 1] = new Axis(x, y);

        for (int i = 1; i <= N; i++) {
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            v[i] = new Axis(x, y);
        }
    }

    static int TSP(int st, int visit) {
        if (visit == (1 << N + 1) - 1)
            return v[st].getDist(v[N + 1]);

        if (dp[st][visit] != -1)
            return dp[st][visit];

        dp[st][visit] = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            if ((visit & (1 << i)) == 0)
                dp[st][visit] = Math.min(dp[st][visit], TSP(i, (visit | (1 << i))) + v[st].getDist(v[i]));
        }

        return dp[st][visit];
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
