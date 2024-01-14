import java.io.*;
import java.util.*;

public class Main_24230 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, ans;
    static int[] colors;
    static int[] paint;
    static int[] parentColor;
    static List<Integer>[] list;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        visited[1] = true;
        dq.add(1);
        while (!dq.isEmpty()) {
            int cur = dq.poll();
            if (colors[cur] != parentColor[cur]) {
                ans++;
            }

            paint[cur] = colors[cur];
            if (list[cur] == null)
                continue;

            for (int next : list[cur]) {
                if (!visited[next]) {
                    parentColor[next] = paint[cur];
                    visited[next] = true;
                    dq.add(next);
                }
            }
        }
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        colors = new int[N + 1];
        paint = new int[N + 1];
        list = new ArrayList[N + 1];
        parentColor = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            colors[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list[s].add(e);
            list[e].add(s);
        }
    }
}