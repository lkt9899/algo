import java.io.*;
import java.util.*;

public class Main_14002 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static final int MAX = 1000;
    static int N;
    static int[] arr = new int[MAX];
    static int[] dp = new int[MAX];
    
    public static void main(String[] args) throws Exception {
        input();
        run();
        System.out.println(sb);
    }
    
    static void run() throws Exception {
        dp[0] = 1;
        int idx = 0;
        int max_v = 1;
        for (int i = 1; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    max = max > dp[j] ? max : dp[j];
                }
            }

            dp[i] = max + 1;
            if (max_v < dp[i]) {
                max_v = dp[i];
                idx = i;
            }
        }
        sb.append(max_v + "\n");

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = idx; i >= 0 && max_v != 0; i--) {
            if (dp[i] == max_v) {
                dq.addFirst(arr[i]);
                max_v--;
            }
        }

        if (dq.isEmpty()) {
            sb.append("1\n" + arr[0]);
            return;
        }

        while (!dq.isEmpty()) {
            int c = dq.pollFirst();
            sb.append(c + " ");
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}