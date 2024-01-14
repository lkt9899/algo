import java.io.*;
import java.util.*;

public class Main_15591 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int N_MAX = 5001;
    static final int K_MAX = 5000;

    static int N, Q;
    static Question[] question = new Question[K_MAX];
    static int[] USADO = new int[N_MAX];
    static List<Edge>[] list = new ArrayList[N_MAX];

    static class Edge {
        int e;
        int val;

        public Edge(int e, int val) {
            this.e = e;
            this.val = val;
        }
    }

    static class Question {
        int k;
        int idx;

        public Question(int k, int idx) {
            this.k = k;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        for (int i = 0; i < Q; i++) {
            Arrays.fill(USADO, 0);
            bfs(question[i]);
        }
        print();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            list[u].add(new Edge(v, val));
            list[v].add(new Edge(u, val));
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            question[i] = new Question(k, v);
        }
    }

    static void bfs(Question q) {
        int ans = 0;
        boolean[] visited = new boolean[N + 1];

        int start = q.idx;
        int limit = q.k;
        USADO[start] = Integer.MAX_VALUE;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(start);
        visited[start] = true;

        while (!dq.isEmpty()) {
            int cur = dq.poll();
            for (Edge next : list[cur]) {
                int nextIdx = next.e;
                int nextUsado = next.val;
                if (!visited[nextIdx]) {
                    USADO[nextIdx] = USADO[cur] < nextUsado ? USADO[cur] : nextUsado;
                    if (USADO[nextIdx] >= limit)
                        ans++;
                    visited[nextIdx] = true;
                    dq.add(nextIdx);
                }
            }
        }
        sb.append(ans + "\n");
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
