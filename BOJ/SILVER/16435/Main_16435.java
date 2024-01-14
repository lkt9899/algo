import java.io.*;
import java.util.*;

public class Main_16435 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;

    static final int MAX = 1000;
    static int N, L;
    static int[] heights = new int[MAX];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        for (int i = 0; i < N; i++) {
            if (L >= heights[i])
                L++;
            else
                break;
        }
        sb = new StringBuilder(L + "");
        System.out.println(sb);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(heights, 0, N);
        br.close();
    }

    static void print() throws Exception {

    }
}
