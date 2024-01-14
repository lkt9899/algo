import java.io.*;
import java.util.*;

public class Main_1535 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, ans;
    static int[] lose, happy;
    static int[] dp = new int[101];

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        lose = new int[N];
        happy = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lose[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void run() throws Exception {
        for (int i = 0; i < N; i++)
            for (int j = 100; j > lose[i]; j--)
                dp[j] = Math.max(dp[j - lose[i]] + happy[i], dp[j]);

        System.out.println(dp[100]);
    }
}