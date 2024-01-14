import java.io.*;
import java.util.*;

public class Main_2839 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        long start, end;

        start = System.nanoTime();
        run1();
        end = System.nanoTime();
        System.out.printf("Greedy : %.4fms\n\n", (end - start) / 1_000_000.0);

        start = System.nanoTime();
        run2();
        end = System.nanoTime();
        System.out.printf("DP : %.4fms\n\n", (end - start) / 1_000_000.0);

        start = System.nanoTime();
        run3();
        end = System.nanoTime();
        System.out.printf("Arithmetic : %.4fms\n\n", (end - start) / 1_000_000.0);
    }

    static void run1() {
        int ans = 0, n = N;
        while (n >= 0) {
            if (n % 5 == 0) {
                ans += n / 5;
                System.out.println(ans);
                return;
            } else {
                n -= 3;
                ans++;
            }
        }

        System.out.println(-1);
    }

    static void run2() {
        int[] dp = new int[5001];
        Arrays.fill(dp, -1);
        dp[3] = dp[5] = 1;

        for (int i = 6; i <= N; i++) {
            if (dp[i - 3] != -1)
                dp[i] = dp[i - 3] + 1;
            if (dp[i - 5] != -1)
                dp[i] = dp[i] != -1 ? Math.min(dp[i], dp[i - 5] + 1) : dp[i - 5] + 1;
        }

        System.out.println(dp[N]);
    }

    static void run3() {
        int ans = N / 5;

        if (N == 4 || N == 7)
            ans = -1;
        else if (N % 5 == 1 || N % 5 == 3)
            ans += 1;
        else if (N % 5 == 2 || N % 5 == 4)
            ans += 2;
        System.out.println(ans);
    }
}