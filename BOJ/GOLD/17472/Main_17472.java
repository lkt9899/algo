import java.io.*;
import java.util.*;

public class Main_17472 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, num;
    static int[][] map;
    static int[] parent;
    static PriorityQueue<Edge> Edges = new PriorityQueue<>();

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class Axis {
        int x, y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int s, e, c;

        public Edge(int s, int e, int c) {
            this.s = s;
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        getIslands();
        System.out.println(Kruskal());
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
    }

    static void getIslands() {
        num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    bfs(i, j, num);
                    num++;
                }
            }
        }
    }

    static void bfs(int si, int sj, int num) {
        Deque<Axis> dq = new ArrayDeque<>();
        map[si][sj] = num;
        dq.add(new Axis(si, sj));
        while (!dq.isEmpty()) {
            Axis cur = dq.poll();
            makeEdge(cur);
            for (int d = 0; d < 4; d++) {
                int ni = cur.x + di[d];
                int nj = cur.y + dj[d];

                if (isNotValid(ni, nj))
                    continue;

                if (map[ni][nj] != 0)
                    continue;

                map[ni][nj] = num;
                dq.add(new Axis(ni, nj));
            }
        }
    }

    static void makeEdge(Axis ax) {
        for (int d = 0; d < 4; d++) {
            int ni = ax.x + di[d];
            int nj = ax.y + dj[d];

            if (isNotValid(ni, nj))
                continue;

            if (map[ni][nj] == -1) {
                int dist = 0;
                while (!isNotValid(ni, nj)) {
                    if (map[ni][nj] != -1) {
                        if (dist < 2)
                            break;
                        int curNum = map[ax.x][ax.y];
                        int nextNum = map[ni][nj];
                        if (curNum * nextNum != 0)
                            Edges.add(new Edge(curNum, nextNum, dist));
                        break;
                    }
                    dist++;
                    ni += di[d];
                    nj += dj[d];
                }
            }
        }
    }

    static int Kruskal() {
        parent = new int[num];
        for (int i = 1; i < num; i++)
            parent[i] = i;

        int cost = 0;
        int cnt = 0;
        while (!Edges.isEmpty()) {
            Edge cur = Edges.poll();
            if (find(cur.s) != find(cur.e)) {
                union(cur.s, cur.e);
                cost += cur.c;
                cnt++;
            }
        }
        if (cnt != num - 2)
            return -1;
        return cost;
    }

    static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return;

        parent[rootB] = rootA;
    }

    static boolean isNotValid(int i, int j) {
        return (i < 0 || i >= N || j < 0 || j >= M);
    }
}