import java.io.*;
import java.util.*;

public class Main_15650 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 8;
    static int N, M;
    static int[] arr = new int[MAX];
    static int[] p;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();

        for (int i = 0; i < M; i++)
            p[i] = 1;

        do {
            for (int i = 0; i < N; i++) {
                if (p[i] == 1)
                    sb.append(arr[i] + " ");
            }
            sb.append("\n");
        } while (nextPermutation());

        print();
    }

    static boolean nextPermutation() {
        int i = N - 1;
        while (i > 0 && p[i - 1] <= p[i])
            i--;

        if (i == 0)
            return false;

        int j = i;
        while (p[i - 1] <= p[j])
            j++;

        swap(i - 1, j);

        int k = N - 1;
        while (++j < k)
            swap(j, k--);

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

        p = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = i + 1;

        br.close();
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
