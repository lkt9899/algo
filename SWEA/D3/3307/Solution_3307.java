import java.io.*;
import java.util.*;

public class Solution_3307 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 1000;

    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void run() throws Exception {
        input();
        int max_v = 0;
        for (int i = 1; i < N; i++) {
            int max = 0;
            for (int j = 0; j < N; j++) {
                if (arr[j] < arr[i]) {
                    max = max > dp[j] ? max : dp[j];
                }
            }

            dp[i] = max + 1;
            max_v = max_v > dp[i] ? max_v : dp[i];
        }
        sb.append(max_v + "\n");
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        dp[0] = 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
