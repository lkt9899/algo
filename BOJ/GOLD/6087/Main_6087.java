import java.io.*;
import java.util.*;

public class Main_6087 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final char WALL = '*';
    static final char RAZOR = 'C';

    static final int INF = 987654321;

    static int W, H, ans = INF;
    static char[][] map;
    static int[][][] dist;
    static Move start, end;

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class Move implements Comparable<Move> {
        int x, y, d, c;

        public Move(int x, int y, int d, int c) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.c = c;
        }

        @Override
        public int compareTo(Move o) {
            return this.c - o.c;
        }

        public boolean equals(Move o) {
            if (x != o.x || y != o.y)
                return false;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        dijkstra();
        System.out.println(ans);
    }

    static void dijkstra() {
        PriorityQueue<Move> pq = new PriorityQueue<>();
        pq.add(start);
        for (int i = 0; i < 4; i++)
            dist[start.x][start.y][i] = 0;

        while (!pq.isEmpty()) {
            Move cur = pq.poll();
            if (cur.equals(end))
                continue;

            if (cur.d != -1 && cur.c > dist[cur.x][cur.y][cur.d])
                continue;

            for (int d = 0; d < 4; d++) {
                int ni = cur.x + di[d];
                int nj = cur.y + dj[d];

                if (isNotValid(ni, nj))
                    continue;

                int nextCost;
                if (cur.d == -1)
                    nextCost = cur.c;
                else
                    nextCost = cur.d != d ? cur.c + 1 : cur.c;

                if (nextCost < dist[ni][nj][d]) {
                    dist[ni][nj][d] = nextCost;
                    pq.add(new Move(ni, nj, d, nextCost));
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            ans = Math.min(dist[end.x][end.y][i], ans);
        }
    }

    static boolean isNotValid(int x, int y) {
        return (x < 0 || x >= H || y < 0 || y >= W || map[x][y] == WALL);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        dist = new int[H][W][4];
        for (int[][] arr : dist)
            for (int[] a : arr)
                Arrays.fill(a, INF);

        map = new char[H][W];
        for (int i = 0; i < H; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                map[i][j] = tmp[j];
                if (map[i][j] == RAZOR) {
                    if (start == null)
                        start = new Move(i, j, -1, 0);
                    else
                        end = new Move(i, j, -1, 0);
                }
            }
        }
    }
}