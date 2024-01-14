import java.io.*;
import java.util.*;

public class Main_2662 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int N_MAX = 301;
    static final int M_MAX = 21;

    static int N, M;
    static int[][] company = new int[M_MAX][N_MAX];
    static int[][][] path = new int[M_MAX][N_MAX][2];
    static int[][] dp = new int[M_MAX][N_MAX];
    static int[] ans = new int[M_MAX];

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }

    static void run() throws Exception {
        for (int i = 1; i <= M; i++) {
            for (int money = 0; money <= N; money++) {
                for (int k = money; k >= 0; k--) {
                    if (dp[i][money] < dp[i - 1][money - k] + company[i][k]) {
                        dp[i][money] = dp[i - 1][money - k] + company[i][k];
                        path[i][money] = new int[] { i, k };
                    }
                }
            }
        }
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            if (i != idx)
                return;
            for (int j = 1; j <= M; j++) {
                company[j][i] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void print() throws Exception {
        sb.append(dp[M][N] + "\n");
        int start = N;
        for (int i = M; i > 0; i--) {
            ans[i] = path[i][start][1];
            start -= path[i][start][1];
        }

        for (int i = 1; i <= M; i++) {
            sb.append(ans[i] + " ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
