import java.io.*;
import java.util.*;

public class Main_2248 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static final int MAX = 32;

    static int N, L;
    static long I;

    static long[][] dp = new long[MAX][MAX];

    public static void main(String[] args) throws Exception {
        run();
    }
    
    static void run() throws Exception {
        input();
        get(N, L, I);
        System.out.println(sb);
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken());
    }

    static void get(int n, int r, long i) {
        if (n == 0)
            return;
        


        if(r == 0 || n == r)
            return dp[n][r] = 1;

        return dp[n][r] = get(n - 1, r - 1) + get(n - 1, r);
    }
    
    static void print() throws Exception {
    
    }
}
