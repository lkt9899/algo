import java.io.*;
import java.util.*;

public class Main_17144 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R, C, T;
    static List<Axis> airs = new ArrayList();
    static Deque<Axis> dusts = new ArrayDeque<>();
    static int[][] map;

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class Axis {
        int x, y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        for (int i = 0; i < T; i++) {
            spread();
            clean();
            addDust();
        }
        System.out.println(getAns());
    }

    static void spread() {
        int[][] toAdd = new int[R][C];
        while (!dusts.isEmpty()) {
            Axis dust = dusts.poll();
            int total = map[dust.x][dust.y];
            for (int d = 0; d < 4; d++) {
                int ni = dust.x + di[d];
                int nj = dust.y + dj[d];

                if (isNotValid(ni, nj))
                    continue;

                int tmp = total / 5;
                if (tmp != 0) {
                    toAdd[ni][nj] += tmp;
                    toAdd[dust.x][dust.y] -= tmp;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += toAdd[i][j];
            }
        }
    }

    static boolean isNotValid(int x, int y) {
        return (x < 0 || x >= R || y < 0 || y >= C || map[x][y] == -1);
    }

    static void clean() {
        int[][] dir = { { 3, 0, 1, 2 },
                { 1, 0, 3, 2 } };
        int[][] limit = { { 0, 0, airs.get(0).x, C - 1 },
                { airs.get(1).x, 0, R - 1, C - 1 } };

        for (int i = 0; i < 2; i++) {
            Axis air = airs.get(i);
            int ci = air.x + di[dir[i][0]];
            int cj = air.y + dj[dir[i][0]];
            for (int j = 0; j < 4; j++) {
                int d = dir[i][j];
                int ni = ci + di[d];
                int nj = cj + dj[d];
                while (ni >= limit[i][0] && nj >= limit[i][1] && ni <= limit[i][2] && nj <= limit[i][3]) {
                    if (ni == air.x && nj == air.y)
                        break;
                    map[ci][cj] = map[ni][nj];
                    map[ni][nj] = 0;
                    ci = ni;
                    cj = nj;
                    ni += di[d];
                    nj += dj[d];
                }
            }
        }
    }

    static void addDust() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0)
                    dusts.add(new Axis(i, j));
            }
        }
    }

    static int getAns() {
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0)
                    ans += map[i][j];
            }
        }
        return ans;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1)
                    airs.add(new Axis(i, j));
                else if (map[i][j] != 0)
                    dusts.add(new Axis(i, j));
            }
        }
    }
}