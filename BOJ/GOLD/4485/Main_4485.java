import java.io.*;
import java.util.*;

public class Main_4485 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int INF = 987654321;

    static int N, ans;
    static int[][] dist;
    static int[][] map;

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class Axis implements Comparable<Axis> {
        int x, y, cost;

        public Axis(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Main_4485.Axis o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        int idx = 1;
        while (true) {
            input();
            if (N == 0)
                break;

            dijkstra();
            sb.append("Problem " + idx++ + ": " + dist[N - 1][N - 1] + "\n");
        }
        print();
    }

    static void dijkstra() {
        PriorityQueue<Axis> pq = new PriorityQueue<>();
        pq.add(new Axis(0, 0, map[0][0]));
        while (!pq.isEmpty()) {
            Axis cur = pq.poll();

            if (cur.cost > dist[cur.x][cur.y])
                continue;

            for (int d = 0; d < 4; d++) {
                int ni = cur.x + di[d];
                int nj = cur.y + dj[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= N)
                    continue;

                if (cur.cost + map[ni][nj] < dist[ni][nj]) {
                    dist[ni][nj] = cur.cost + map[ni][nj];
                    pq.add(new Axis(ni, nj, dist[ni][nj]));
                }
            }
        }
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        if (N == 0)
            return;

        init();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void init() {
        dist = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(dist[i], INF);
        map = new int[N][N];
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
