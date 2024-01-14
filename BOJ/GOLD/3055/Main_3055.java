import java.io.*;
import java.util.*;

public class Main_3055 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final char ROCK = 'X';
    static final char WATER = '*';
    static final char EMPTY = '.';
    static final char GOAL = 'D';
    static final char START = 'S';

    static int R, C, ans = Integer.MAX_VALUE;
    static char[][] map;
    static Axis start;
    static List<Axis> waters = new ArrayList<>();

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class Axis {
        int x, y, move;

        public Axis(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        bfs();
        System.out.println(ans == Integer.MAX_VALUE ? "KAKTUS" : ans);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp[j];
                if (map[i][j] == START)
                    start = new Axis(i, j, 0);
                else if (map[i][j] == WATER)
                    waters.add(new Axis(i, j, 0));
            }
        }
    }

    static void bfs() {
        Deque<Axis> w = new ArrayDeque<>();
        for (Axis a : waters)
            w.add(a);
        Deque<Axis> dq = new ArrayDeque<>();
        dq.add(start);

        boolean[][] visited = new boolean[R][C];
        visited[start.x][start.y] = true;
        while (!dq.isEmpty()) {
            int len = dq.size();
            while (len-- > 0) {
                Axis cur = dq.poll();

                for (int d = 0; d < 4; d++) {
                    int ni = cur.x + di[d];
                    int nj = cur.y + dj[d];

                    if (isNotValid(ni, nj))
                        continue;

                    if (map[ni][nj] == GOAL) {
                        ans = cur.move + 1;
                        return;
                    }

                    if (map[ni][nj] == WATER || visited[ni][nj])
                        continue;
                    
                    if (nearWater(ni, nj))
                        continue;


                    visited[ni][nj] = true;
                    dq.add(new Axis(ni, nj, cur.move + 1));
                }
            }

            len = w.size();
            while (len-- > 0) {
                Axis cur = w.poll();

                for (int d = 0; d < 4; d++) {
                    int ni = cur.x + di[d];
                    int nj = cur.y + dj[d];

                    if (isNotValid(ni, nj))
                        continue;

                    if (map[ni][nj] == EMPTY) {
                        map[ni][nj] = WATER;
                        w.add(new Axis(ni, nj, 0));
                    }
                }
            }
        }
    }

    static boolean isNotValid(int x, int y) {
        return (x < 0 || x >= R || y < 0 || y >= C || map[x][y] == ROCK);
    }

    static boolean nearWater(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int ni = x + di[d];
            int nj = y + dj[d];
            if (isNotValid(ni, nj))
                continue;

            if (map[ni][nj] == WATER)
                return true;
        }

        return false;
    }
}
