import java.io.*;
import java.util.*;

public class Main_1194_달이_차오른다_가자 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int KEY_NUM = 1 << 6;

    static int N, M, ans = Integer.MAX_VALUE;
    static Axis start;

    static char[][] map;
    static boolean visited[][][];

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class Axis {
        int x, y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Info {
        Axis a;
        int move;
        int visit;

        public Info(Axis a, int move, int visit) {
            this.a = a;
            this.move = move;
            this.visit = visit;
        }

        public int getX() {
            return a.x;
        }

        public int getY() {
            return a.y;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        bfs();
        System.out.println(ans);
    }

    static void bfs() throws Exception {
        Deque<Info> dq = new ArrayDeque<>();
        dq.add(new Info(start, 0, 0));
        visited[0][start.x][start.y] = true;
        while (!dq.isEmpty()) {
            Info cur = dq.poll();

            for (int d = 0; d < 4; d++) {
                int ni = cur.getX() + di[d];
                int nj = cur.getY() + dj[d];

                if (isNotValid(ni, nj))
                    continue;
                if (visited[cur.visit][ni][nj])
                    continue;
                if (!isOpen(map[ni][nj], cur.visit))
                    continue;

                if (map[ni][nj] == '1') {
                    ans = ans < cur.move + 1 ? ans : cur.move + 1;
                }

                int visit = cur.visit;
                if (isKey(map[ni][nj])) {
                    visit |= (1 << (map[ni][nj] - 'a'));
                }

                visited[visit][ni][nj] = true;
                dq.add(new Info(new Axis(ni, nj), cur.move + 1, visit));
            }
        }
        if (ans == Integer.MAX_VALUE)
            ans = -1;
    }
    
    static boolean isNotValid(int x, int y) {
        return (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == '#');
    }

    static boolean isOpen(char d, int v) {
        if (d >= 'A' && d <= 'F') {
            return (v & 1 << (d - 'A')) != 0;
        }

        return true;
    }

    static boolean isKey(char k) {
        return (k >= 'a' && k <= 'f');
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[KEY_NUM][N][M];
        for (int i = 0; i < N; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = row[j];
                if (map[i][j] == '0')
                    start = new Axis(i, j);
            }
        }
    }
}
