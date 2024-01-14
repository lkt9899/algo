import java.io.*;
import java.util.*;

public class Main_15649 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int MAX = 8;
    static int[] arr = new int[MAX];
    static int[] p = new int[MAX];

    static int N, M;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();

        do {
            boolean flag = false;
            for (int i = 0; i < M; i++) {
                if (p[i] != arr[i]) {
                    flag = true;
                    break;
                }
            }

            if (!flag)
                continue;

            for (int i = 0; i < M; i++)
                p[i] = arr[i];

            print();
        } while (nextPermutation());

        bw.flush();
        bw.close();
    }

    static boolean nextPermutation() {
        // search part of increasing first time then set pivot
        int i = N - 1;
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;

        // if not exist, array is already sorted descending. then, exit.
        if (i == 0)
            return false;

        // search first number bigger than left number of pivot
        int j = N - 1;
        while (arr[i - 1] >= arr[j])
            j--;

        // i-1 <-> j swap
        swap(i - 1, j);

        // sort right side of pivot ascending
        int k = N - 1;
        while (i < k) {
            swap(i++, k--);
        }

        return true;
    }

    static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }
        br.close();
    }

    static void print() throws Exception {
        for (int i = 0; i < M; i++) {
            bw.write(p[i] + " ");
        }
        bw.newLine();
    }
}
