import java.io.*;
import java.util.*;

public class Solution_3289 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 1_000_001;

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        print();
    }

    static void run() throws Exception {
        input();
        init();
        solve();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    static void init() {
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    static void solve() throws IOException {
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (cmd == 0)
                union(A, B);
            else {
                sb.append(findParent(A, B) + "");
            }
        }
        sb.append("\n");
    }

    static void union(int A, int B) {
        int rootA = find(A);

        parent[B] = rootA;
    }

    static int findParent(int A, int B) {
        if (find(A) == find(B))
            return 1;
        return 0;
    }

    static int find(int N) {
        if (N == parent[N])
            return N;
        else
            return parent[N] = find(parent[N]);
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
