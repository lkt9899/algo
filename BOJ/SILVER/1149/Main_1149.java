import java.io.*;
import java.util.*;

public class Main_1149 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int COLOR = 3;
    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;

    static int N;
    static int[][] house;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        for (int j = 0; j < COLOR; j++)
            dp[0][j] = house[0][j];

        for (int i = 1; i < N; i++) {
            int r = dp[i - 1][RED];
            int g = dp[i - 1][GREEN];
            int b = dp[i - 1][BLUE];

            dp[i][RED] = house[i][RED] + (g < b ? g : b);
            dp[i][GREEN] = house[i][GREEN] + (r < b ? r : b);
            dp[i][BLUE] = house[i][BLUE] + (g < r ? g : r);
        }

        Arrays.sort(dp[N - 1]);
        System.out.println(dp[N - 1][0]);
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        house = new int[N][COLOR];
        dp = new int[N][COLOR];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < COLOR; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
    }
}