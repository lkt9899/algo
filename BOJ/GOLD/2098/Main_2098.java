import java.io.*;
import java.util.*;

public class Main_2098 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 1 << 16;
    static final int INF = 987654321;

    static int N;
    static int[][] dp;
    static int[][] cost;

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(TSP(0, 1));
    }

    static int TSP(int start, int visit) {
        if (visit == (1 << N) - 1) {
            if (cost[start][0] == 0)
                return INF;

            return cost[start][0];
        }

        if (dp[start][visit] != -1)
            return dp[start][visit];

        dp[start][visit] = INF;
        for (int i = 0; i < N; i++) {
            if (cost[start][i] != 0 && (visit & (1 << i)) == 0) {
                dp[start][visit] = Math.min(dp[start][visit], TSP(i, visit | (1 << i)) + cost[start][i]);
            }
        }

        return dp[start][visit];
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        dp = new int[N][1 << N];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
