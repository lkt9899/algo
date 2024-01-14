import java.io.*;
import java.util.*;

public class Main_1010 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static final int MAX = 31;

    static int N, M;
    static long[][] dp = new long[MAX][MAX];

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            run();
        }
        print();
    }

    static void run() throws Exception {
        input();
        sb.append(comb2(M, N) + "\n");
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    static long comb(int n, int k) {
        long res = 1;

        for (int i = 0; i < N; i++) {
            res *= (M - i);
            res /= (i + 1);
        }

        return res;
    }

    static long comb2(int n, int k) {
        if (dp[n][k] != 0)
            return dp[n][k];
        if(k == 0 || n == k)
            return dp[n][k] = 1;

        return dp[n][k] = comb2(n - 1, k - 1) + comb2(n - 1, k);
    }
    
    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}