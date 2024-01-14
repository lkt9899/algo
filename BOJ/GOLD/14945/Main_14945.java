import java.io.*;
import java.util.*;

public class Main_14945 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        for (int i = 3; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j] * 2 + dp[i - 1][j + 1]) % 10007;
            }
        }

        int sum = 0;
        for (int i = 1; i < N; i++) {
            sum += dp[N][i];
            sum %= 10007;
        }

        System.out.println(sum);
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 2];
        dp[2][1] = 2;
    }
}