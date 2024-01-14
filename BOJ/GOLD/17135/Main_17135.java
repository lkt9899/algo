import java.io.*;
import java.util.*;

public class Main_17135 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 15;

    static int N, M, D, ans, limitMax;

    static int[][] map = new int[MAX][MAX];
    static int[][] tmp;
    static int[] p;
    static int[] archerCol = new int[3];
    static boolean[][] visited;

    static int[] di = { 0, -1, 0 };
    static int[] dj = { -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();

        do {
            int idx = 0;
            for (int i = 0; i < M; i++) {
                if (p[i] == 1)
                    archerCol[idx++] = i;
                if (idx == 3)
                    break;
            }

            tmp = new int[N][M];
            for (int i = 0; i < N; i++) {
                tmp[i] = Arrays.copyOf(map[i], M);
            }

            int max = shoot();
            ans = ans > max ? ans : max;
            if (ans == limitMax)
                break;
        } while (nextPermutation());

        System.out.println(ans);
    }

    static boolean nextPermutation() {
        int i = M - 1;
        while (i > 0 && p[i - 1] >= p[i])
            i--;

        if (i == 0)
            return false;

        int j = M - 1;
        while (p[i - 1] >= p[j])
            j--;

        swap(i - 1, j);

        int k = M - 1;
        while (i < k)
            swap(i++, k--);

        return true;
    }

    static void swap(int i, int j) {
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        p = new int[M];
        for (int i = M - 1; i > M - 4; i--) {
            p[i] = 1;
        }

        limitMax = 3 * N;

        br.close();
    }

    static int shoot() {
        int ret = 0;
        Deque<int[]> dq = new ArrayDeque<>();
        for (int row = N; row > 0; row--) {
            if (!isExist(row))
                break;

            final int r = row;
            List<int[]> shot = new LinkedList<>();
            for (int c : archerCol) {
                visited = new boolean[N][M];
                List<int[]> enemy = new ArrayList<>();
                dq.add(new int[] { row, c });

                while (!dq.isEmpty()) {
                    int[] cur = dq.poll();
                    int ci = cur[0];
                    int cj = cur[1];

                    for (int d = 0; d < 3; d++) {
                        int ni = ci + di[d];
                        int nj = cj + dj[d];

                        if (isNotValid(ni, nj, row) || getDist(row, c, ni, nj) > D)
                            continue;

                        visited[ni][nj] = true;
                        if (tmp[ni][nj] == 1) {
                            enemy.add(new int[] { ni, nj });
                        }
                        dq.add(new int[] { ni, nj });
                    }
                }

                if (enemy.isEmpty())
                    continue;

                Collections.sort(enemy, (o1, o2) -> {
                    int d1 = getDist(r, c, o1[0], o1[1]);
                    int d2 = getDist(r, c, o2[0], o2[1]);
                    if (d1 == d2)
                        return o1[1] - o2[1];
                    return d1 - d2;
                });

                shot.add(enemy.get(0));
            }

            for (int[] e : shot) {
                if (tmp[e[0]][e[1]] == 1) {
                    tmp[e[0]][e[1]] = 0;
                    ret++;
                }
            }
        }

        return ret;
    }

    static boolean isExist(int r) {
        for (int i = 0; i < r; i++) {
            for (int n : tmp[i]) {
                if (n == 1)
                    return true;
            }
        }

        return false;
    }

    static boolean isNotValid(int i, int j, int limitRow) {
        return (i < 0 || i >= limitRow || j < 0 || j >= M || visited[i][j]);
    }

    static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
