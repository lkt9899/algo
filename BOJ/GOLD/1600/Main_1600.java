import java.io.*;
import java.util.*;

public class Main_1600 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static final int MAX = 200;
    static final int INF = 123456789;
    static final int MOVE = 0;
    static final int HORSE = 1;

    static int K, W, H, ans = INF;
    static int[][] map = new int[MAX][MAX];

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };
    static int[] hi = { -2, -1, 1, 2, 2, 1, -1, -2 };
    static int[] hj = { 1, 2, 2, 1, -1, -2, -2, -1 };

    static class Axis {
        int x;
        int y;
        int k;
        int move;

        public Axis(int x, int y, int k, int move) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.move = move;
        }
    }

    public static void main(String[] args) throws Exception {
        run();
    }
    
    static void run() throws Exception {
        input();
        bfs();
        System.out.println(ans == INF ? -1 : ans);
    }
    
    static void input() throws Exception {
        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    
    static void bfs() {
        boolean[][][] visited = new boolean[K + 1][H][W];
        Deque<Axis> dq = new ArrayDeque<>();
        dq.push(new Axis(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!dq.isEmpty()) {
            Axis cur = dq.poll();

            if (cur.x == H - 1 && cur.y == W - 1) {
                ans = ans < cur.move ? ans : cur.move;
            }

            for (int d = 0; d < 4; d++) {
                int ni = cur.x + di[d];
                int nj = cur.y + dj[d];

                if (isNotValid(ni, nj))
                    continue;

                if (visited[cur.k][ni][nj])
                    continue;

                visited[cur.k][ni][nj] = true;
                dq.add(new Axis(ni, nj, cur.k, cur.move + 1));
            }

            if (cur.k  < K) {
                for (int d = 0; d < 8; d++) {
                    int ni = cur.x + hi[d];
                    int nj = cur.y + hj[d];

                    if (isNotValid(ni, nj))
                        continue;

                    if (visited[cur.k + 1][ni][nj])
                        continue;

                    visited[cur.k + 1][ni][nj] = true;
                    dq.add(new Axis(ni, nj, cur.k + 1, cur.move + 1));
                }
            }
        }
    }
    
    static boolean isNotValid(int i, int j) {
        return (i < 0 || i >= H || j < 0 || j >= W || map[i][j] == 1);
    }
}
