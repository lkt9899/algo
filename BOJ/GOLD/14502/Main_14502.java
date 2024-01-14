import java.io.*;
import java.util.*;

public class Main_14502 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;

    static int N, M, ans, eCnt;

    static int[][] map;
    static List<int[]> virus = new ArrayList<>();

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        input();
        find(0, 0, 0);
        System.out.println(ans);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == VIRUS) {
                    virus.add(new int[] { i, j });
                } else if (map[i][j] == EMPTY)
                    eCnt++;
            }
        }
    }

    static void find(int pi, int pj, int cnt) {
        if (cnt == 3) {
            int max = bfs();
            ans = ans > max ? ans : max;
            return;
        }

        for (int i = pi; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int nj = pj + j;
                if (isNotValid(i, nj))
                    continue;
                if (map[i][nj] == EMPTY) {
                    map[i][nj] = WALL;
                    if (j == M - 1) {
                        find(i + 1, 0, cnt + 1);
                    } else {
                        find(i, nj + 1, cnt + 1);
                    }
                    map[i][nj] = EMPTY;
                }
            }
            pj = 0;
        }
    }

    static int bfs() {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int cnt = eCnt - 3;
        for (int[] v : virus) {
            if (!visited[v[0]][v[1]]) {
                dq.add(new int[] { v[0], v[1] });
                visited[v[0]][v[1]] = true;
                while (!dq.isEmpty()) {
                    int[] cur = dq.poll();
                    for (int d = 0; d < 4; d++) {
                        int ni = cur[0] + di[d];
                        int nj = cur[1] + dj[d];

                        if (isNotValid(ni, nj) || map[ni][nj] == WALL || visited[ni][nj])
                            continue;
                        if (map[ni][nj] == EMPTY)
                            cnt--;
                        if (cnt == 0)
                            break;
                        visited[ni][nj] = true;
                        dq.add(new int[] { ni, nj });
                    }
                }
            }
        }

        return cnt;
    }

    static boolean isNotValid(int i, int j) {
        return (i < 0 || i >= N || j < 0 || j >= M);
    }
}