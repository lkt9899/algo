import java.io.*;

public class Main_1463 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(get(N));
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 4];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
    }

    static int get(int n) {
        for (int i = 4; i <= n; i++) {
            int tmp = dp[i - 1] + 1;
            if (i % 3 == 0) {
                tmp = tmp < dp[i / 3] + 1 ? tmp : dp[i / 3] + 1;
            }
            if (i % 2 == 0) {
                tmp = tmp < dp[i / 2] + 1 ? tmp : dp[i / 2] + 1;
            }

            dp[i] = dp[i] == 0 ? tmp : (tmp < dp[i] ? tmp : dp[i]);
        }

        return dp[n];
    }
}
