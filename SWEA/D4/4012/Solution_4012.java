import java.io.*;
import java.util.*;

public class Solution_4012 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;

    static final int MAX = 16;

    static int N, min;
    static int[][] si = new int[MAX][MAX];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb = new StringBuilder("#" + test_case + " ");
            run();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    static void run() throws Exception {
        init();
        input();
        getCombination(0, 0, 0);
        print();
    }

    static void getCombination(int idx, int cnt, int used) {
        if (cnt == N / 2) {
            calcScore(used);
            return;
        }

        for (int i = idx; i < N; i++) {
            if ((used & (1 << i)) == 0) {
                getCombination(i + 1, cnt + 1, used | (1 << i));
            }
        }
    }

    static void calcScore(int used) {
        int[] A = new int[N / 2];
        int[] B = new int[N / 2];
        int aidx = 0, bidx = 0;
        for (int i = 0; i < N; i++) {
            if ((used & (1 << i)) != 0) {
                A[aidx++] = i;
            } else {
                B[bidx++] = i;
            }
        }

        int Ascore = 0;
        int Bscore = 0;
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                if (i != j) {
                    Ascore += si[A[i]][A[j]];
                    Bscore += si[B[i]][B[j]];
                }
            }
        }

        int total = Math.abs(Ascore - Bscore);
        min = min < total ? min : total;
    }

    static void init() {
        min = Integer.MAX_VALUE;
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                si[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void print() throws Exception {
        sb.append(min + "\n");
        bw.write(sb.toString());
    }
}
