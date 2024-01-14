import java.io.*;
import java.util.*;

public class Main_16234 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, L, R, ans;
    static boolean flag;

    static int[][] map;
    static boolean[][] visited;

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
        while (true) {
            flag = true;
            run();
            if (flag)
                break;
            ans++;
        }
        System.out.println(ans);
    }

    static void run() throws Exception {
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(new Axis(i, j));
                }
            }
        }
    }

    static void bfs(Axis start) {
        Deque<Axis> dq = new ArrayDeque<>();
        Deque<Axis> toAdd = new ArrayDeque<>();

        int sum = map[start.x][start.y];
        int cnt = 1;

        visited[start.x][start.y] = true;
        dq.add(start);
        toAdd.add(start);
        while (!dq.isEmpty()) {
            Axis cur = dq.poll();

            for (int d = 0; d < 4; d++) {
                int ni = cur.x + di[d];
                int nj = cur.y + dj[d];

                if (isNotValid(ni, nj))
                    continue;

                int diff = Math.abs(map[cur.x][cur.y] - map[ni][nj]);
                if (diff < L || diff > R)
                    continue;

                sum += map[ni][nj];
                cnt++;
                visited[ni][nj] = true;
                dq.add(new Axis(ni, nj));
                toAdd.add(new Axis(ni, nj));
            }
        }

        if (cnt > 1)
            flag = false;

        int newVal = sum / cnt;
        while (!toAdd.isEmpty()) {
            Axis c = toAdd.poll();
            map[c.x][c.y] = newVal;
        }
    }

    static boolean isNotValid(int i, int j) {
        return (i < 0 || i >= N || j < 0 || j >= N || visited[i][j]);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
