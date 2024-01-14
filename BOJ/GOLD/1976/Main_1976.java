import java.io.*;
import java.util.*;

public class Main_1976 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] plan;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        int p = find(parents[plan[0]]);
        String ans = "YES";
        for (int i = 1; i < M; i++) {
            if (find(parents[plan[i]]) != p) {
                ans = "NO";
                break;
            }
        }
        System.out.println(ans);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return;

        parents[rootB] = rootA;
    }

    static int find(int n) {
        if (parents[n] == n)
            return n;

        return parents[n] = find(parents[n]);
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        plan = new int[M];
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++)
            parents[i] = i;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int possible = Integer.parseInt(st.nextToken());
                if (possible == 1)
                    union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
    }
}
