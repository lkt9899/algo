import java.io.*;
import java.util.*;

public class Main_20926 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static final int INF = 987654321;
    static final int TERA = 'T' - '0';
    static final int ROCK = 'R' - '0';
    static final int HOLE = 'H' - '0';
    static final int EXIT = 'E' - '0';

    static int W, H;
    static int si, sj, ei, ej;
    static int[][] map;
    static int[][] dist;

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class Edge implements Comparable<Edge> {
        int x, y, c;

        public Edge(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }

    public static void main(String[] args) throws Exception {
        run();
    }
    
    static void run() throws Exception {
        input();
        System.out.println(dijkstra());
    }

    static int dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[si][sj] = 0;
        pq.add(new Edge(si, sj, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.c > dist[cur.x][cur.y])
                continue;

            for (int d = 0; d < 4; d++) {
                Edge next = getNext(cur.x, cur.y, d);

                if(next == null)
                    continue;

                int nextCost = cur.c + next.c;
                if (nextCost < dist[next.x][next.y]) {
                    dist[next.x][next.y] = nextCost;
                    next.c = nextCost;
                    pq.add(next);
                }
            }
        }
        
        if (dist[ei][ej] == INF)
            return -1;

        return dist[ei][ej];
    }

    static Edge getNext(int x, int y, int d) {
        int cost = 0;
        int ni = x + di[d];
        int nj = y + dj[d];
        while (!isNotValid(ni, nj)) {
            if (map[ni][nj] == EXIT) {
                return new Edge(ni, nj, cost);
            } else if(map[ni][nj] == ROCK)
                break;

            cost += map[ni][nj];
            ni += di[d];
            nj += dj[d];
        }

        if (isNotValid(ni, nj))
            return null;

        ni -= di[d];
        nj -= dj[d];

        return new Edge(ni, nj, cost);
    }
    
    static boolean isNotValid(int x, int y) {
        return (x < 0 || x >= H || y < 0 || y >= W || map[x][y] == HOLE);
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        dist = new int[H][W];
        for (int i = 0; i < H; i++)
            Arrays.fill(dist[i], INF);

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                map[i][j] = tmp[j] - '0';
                if (map[i][j] == TERA) {
                    si = i;
                    sj = j;
                } else if (map[i][j] == EXIT) {
                    ei = i;
                    ej = j;
                }
            }
        }
    }
}