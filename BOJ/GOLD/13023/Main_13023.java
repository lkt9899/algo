import java.io.*;
import java.util.*;

public class Main_13023 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 2000;

    static int N, M, ans;

    static List<Integer>[] list = new ArrayList[MAX];
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        solve();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            list[A].add(B);
            list[B].add(A);
        }
    }

    static void init() {
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        visit = new boolean[N];
        ans = 0;
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                dfs(i, 1);
            }
            if (ans == 1)
                break;
        }
        System.out.println(ans);
    }

    static void dfs(int node, int cnt) {
        if (cnt == 5) {
            ans = 1;
            return;
        }

        visit[node] = true;

        for (int next : list[node]) {
            if (!visit[next]) {
                dfs(next, cnt + 1);
            }
        }
        visit[node] = false;
    }
}