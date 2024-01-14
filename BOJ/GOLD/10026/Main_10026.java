import java.io.*;
import java.util.*;

public class Main_10026 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 100;
    static final char RED = 'R';
    static final char GREEN = 'G';
    static final char BLUE = 'B';

    static int N;

    static int norm = 0, weak = 0;

    static char[][] normal = new char[MAX][MAX];
    static boolean[][] nVisit;

    static char[][] weakness = new char[MAX][MAX];
    static boolean[][] wVisit;

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!nVisit[i][j]) {
                    BFS(normal, nVisit, i, j);
                    norm++;
                }
                if (!wVisit[i][j]) {
                    BFS(weakness, wVisit, i, j);
                    weak++;
                }
            }
        }
        print();
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        nVisit = new boolean[N][N];
        wVisit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (tmp[j] == GREEN) {
                    normal[i][j] = tmp[j];
                    weakness[i][j] = RED;
                } else {
                    normal[i][j] = tmp[j];
                    weakness[i][j] = tmp[j];
                }
            }
        }
    }

    static void BFS(char[][] sector, boolean[][] visit, int si, int sj) {
        char color = sector[si][sj];

        Deque<int[]> dq = new ArrayDeque<>();
        visit[si][sj] = true;
        dq.add(new int[] { si, sj });
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();

            for (int i = 0; i < 4; i++) {
                int ni = cur[0] + di[i];
                int nj = cur[1] + dj[i];

                if (ni < 0 || ni >= N || nj < 0 || nj >= N || visit[ni][nj] || sector[ni][nj] != color)
                    continue;

                visit[ni][nj] = true;
                dq.add(new int[] { ni, nj });
            }
        }
    }

    static void print() throws Exception {
        sb.append(norm + " " + weak);
        System.out.println(sb);
    }
}
