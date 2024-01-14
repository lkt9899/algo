import java.io.*;
import java.util.*;

public class Main_15686 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static final int N_MAX = 50;
    static final int M_MAX = 13;

    static int N, M, hcnt, ccnt, ans = Integer.MAX_VALUE;
    static int[][] chk = new int[M_MAX][2];
    static int[][] hm = new int[2 * N_MAX][2];
    static int[][] dist = new int[M_MAX][2 * N_MAX];
    static int[] p;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        for (int i = 0; i < ccnt; i++) {
            for (int j = 0; j < hcnt; j++) {
                dist[i][j] = getDist(chk[i], hm[j]);
            }
        }

        p = new int[ccnt];
        for (int i = ccnt - M; i < ccnt; i++) {
            p[i] = 1;
        }

        do {
            int md = getChickenDist();
            ans = ans < md ? ans : md;
        } while (nextPermutation());

        print();
    }

    static int getDist(int[] c, int[] h) {
        return Math.abs(c[0] - h[0]) + Math.abs(c[1] - h[1]);
    }

    static int getChickenDist() {
        int minDistSum = 0;
        for (int i = 0; i < hcnt; i++) {
            int minDist = Integer.MAX_VALUE;
            for (int j = 0; j < ccnt; j++) {
                if (p[j] == 1) {
                    minDist = minDist < dist[j][i] ? minDist : dist[j][i];
                }
            }
            minDistSum += minDist;
        }

        return minDistSum;
    }

    static boolean nextPermutation() {
        int i = ccnt - 1;
        while (i > 0 && p[i - 1] >= p[i])
            i--;

        if (i == 0)
            return false;

        int j = ccnt - 1;
        while (p[i - 1] >= p[j])
            j--;

        swap(i - 1, j);

        int k = ccnt - 1;
        while (i < k)
            swap(i++, k--);

        return true;
    }

    static void swap(int s, int e) {
        int tmp = p[s];
        p[s] = p[e];
        p[e] = tmp;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ccnt = hcnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    hm[hcnt++] = new int[] { i, j };
                } else if (num == 2) {
                    chk[ccnt++] = new int[] { i, j };
                }
            }
        }

        br.close();
    }

    static void print() throws Exception {
        bw.write(ans + "");
        bw.flush();
        bw.close();
    }
}