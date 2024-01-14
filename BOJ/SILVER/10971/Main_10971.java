import java.io.*;
import java.util.*;

public class Main_10971 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int N_MAX = 10;
    static final int INF = 10000000;

    static int N, s;
    static int ALL;
    static int[][] adjMatrix = new int[N_MAX][N_MAX];
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(TSP(0, 1));
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        ALL = (1 << N);

        dp = new int[N][ALL];
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
    }

    static int TSP(int start, int visit) {
        if (visit == ALL - 1) {
            if (adjMatrix[start][s] == 0)
                return INF;
            return adjMatrix[start][s];
        }

        if (dp[start][visit] != -1)
            return dp[start][visit];

        dp[start][visit] = INF;
        for (int i = 1; i < N; i++) {
            if (adjMatrix[start][i] != 0 && (visit & (1 << i)) == 0) {
                dp[start][visit] = Math.min(dp[start][visit], TSP(i, visit | (1 << i)) + adjMatrix[start][i]);
            }
        }

        return dp[start][visit];
    }
}