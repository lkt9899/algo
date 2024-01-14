import java.io.*;
import java.util.*;

public class Main_17406 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;

    static final int N_MAX = 51;
    static final int K_MAX = 6;

    static int N, M, K, ans = Integer.MAX_VALUE;
    static int[][] arr = new int[N_MAX][N_MAX];
    static int[][] calc = new int[K_MAX][3];
    static int[] order = new int[K_MAX];
    static boolean[] used = new boolean[K_MAX];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        getNextPermutation(0, K);
        print();
    }

    static void getNextPermutation(int idx, int k) {
        if (idx == k) {
            int[][] cpy = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                cpy[i] = Arrays.copyOf(arr[i], M + 1);
            }

            for (int i = 0; i < k; i++) {
                int si = calc[order[i]][0] - calc[order[i]][2];
                int sj = calc[order[i]][1] - calc[order[i]][2];
                int ei = calc[order[i]][0] + calc[order[i]][2];
                int ej = calc[order[i]][1] + calc[order[i]][2];

                turnArr(si, sj, ei, ej, cpy);
            }

            getMin(cpy);
        }

        for (int i = 0; i < k; i++) {
            if (!used[i]) {
                used[i] = true;
                order[idx] = i;
                getNextPermutation(idx + 1, k);
                used[i] = false;
            }
        }
    }

    static void turnArr(int si, int sj, int ei, int ej, int[][] cpy) {
        if (si >= ei || sj >= ej)
            return;

        int[] tmp = { cpy[si][ej], cpy[ei][ej], cpy[ei][sj] };

        // right
        for (int j = ej; j > sj; j--) {
            cpy[si][j] = cpy[si][j - 1];
        }
        cpy[si][sj] = cpy[si + 1][sj];

        // down
        for (int i = ei; i > si; i--) {
            cpy[i][ej] = cpy[i - 1][ej];
        }
        cpy[si + 1][ej] = tmp[0];

        // left
        for (int j = sj; j < ej; j++) {
            cpy[ei][j] = cpy[ei][j + 1];
        }
        cpy[ei][ej - 1] = tmp[1];

        // up
        for (int i = si; i < ei; i++) {
            cpy[i][sj] = cpy[i + 1][sj];
        }
        cpy[ei - 1][sj] = tmp[2];

        turnArr(si + 1, sj + 1, ei - 1, ej - 1, cpy);
    }

    static void getMin(int[][] cpy) {
        for (int i = 1; i <= N; i++) {
            int res = 0;
            for (int j = 1; j <= M; j++) {
                res += cpy[i][j];
            }
            ans = ans < res ? ans : res;
        }
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            calc[i] = new int[] { r, c, s };
        }
        br.close();
    }

    static void print() throws Exception {
        sb = new StringBuilder(ans + "");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
