import java.io.*;
import java.util.*;

/**
 * Main_1260
 */
public class Main_1260 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 1001;

    static int N, M, V;

    static List<Integer>[] list = new ArrayList[MAX];
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();

        visited = new boolean[N + 1];
        dfs(V);
        sb.append("\n");

        visited = new boolean[N + 1];
        bfs(V);
        print();
    }

    static void init() {
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        init();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            list[A].add(B);
            list[B].add(A);
        }

        for (int i = 1; i <= N; i++) {
            if (list[i].size() > 1) {
                Collections.sort(list[i]);
            }
        }

        br.close();
    }

    static void dfs(int start) {
        visited[start] = true;
        sb.append(start + " ");

        for (int next : list[start]) {
            if (!visited[next])
                dfs(next);
        }
    }

    static void bfs(int start) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(start);
        visited[start] = true;

        while (!dq.isEmpty()) {
            int cur = dq.poll();
            sb.append(cur + " ");

            for (int next : list[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dq.add(next);
                }
            }
        }
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}