import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Solution_5215 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;

    static final int N_MAX = 20;
    static int N, L, max;

    static int[][] ingredients = new int[N_MAX][2];
    static int[] food = new int[N_MAX];

    public static void main(String[] args) throws Exception {
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
        input();

        max = 0;
        getCombination(0, 0, 0);
        print();
    }

    static void getCombination(int idx, int score, int kcal) {
        if (kcal > L)
            return;

        max = max > score ? max : score;
        for (int i = idx; i < N; i++) {
            getCombination(i + 1, score + ingredients[i][0], kcal + ingredients[i][1]);
        }
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ingredients[i][0] = Integer.parseInt(st.nextToken());
            ingredients[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void print() throws Exception {
        sb.append(max + "\n");
        bw.write(sb.toString());
    }
}
