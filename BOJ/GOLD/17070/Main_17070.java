import java.io.*;
import java.util.*;

public class Main_17070 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static final int MAX = 17;
    // current state
    static final int HOR = 0;
    static final int DIA = 1;
    static final int PER = 2;

    static int N;
    static int[][] house;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        run();
    }
    
    static void run() throws Exception {
        input();
        System.out.println(get());
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        dp = new int[3][N + 1][N + 1];
        house = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /**
     * print for debug
     */
    static void print() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append("{");
                for (int d = 0; d < 3; d++) {
                    sb.append(dp[d][i][j] + " ");
                }
                sb.append("} ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int get() {
        // row 1 init
        dp[HOR][1][2] = 1;
        for (int j = 3; j <= N; j++) {
            if(house[1][j] == 0)
                dp[HOR][1][j] = dp[HOR][1][j - 1];
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= N; j++) {
                if (house[i][j] == 0 && house[i - 1][j] == 0 && house[i][j - 1] == 0) {
                    dp[DIA][i][j] = dp[DIA][i - 1][j - 1] + dp[PER][i - 1][j - 1] + dp[HOR][i - 1][j - 1];
                }

                if (house[i][j] == 0) {
                    dp[HOR][i][j] = dp[HOR][i][j - 1] + dp[DIA][i][j - 1];
                    dp[PER][i][j] = dp[PER][i - 1][j] + dp[DIA][i - 1][j];
                }
            }
        }

        // print()
        return dp[HOR][N][N] + dp[DIA][N][N] + dp[PER][N][N];
    }
}