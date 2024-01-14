import java.io.*;
import java.util.*;

public class Main_20187 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int K_MAX = 8;
    static final int N_MAX = (1 << K_MAX);

    static int K, H, edge, ni = 1, nj = 1;
    static char lastRL, lastUD;
    static int[][] arr = new int[N_MAX][N_MAX];

    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        input();
        edge();
        for (int i = 0; i < ni; i += 2) {
            for (int j = 0; j < nj; j += 2) {
                fill(i, j);
            }
        }
        print();
    }

    static void edge() {
        if (lastRL == 'R') {
            if (lastUD == 'U') {
                edge = 1;
            } else {
                edge = 3;
            }
        } else {
            if (lastUD == 'U') {
                edge = 0;
            } else {
                edge = 2;
            }
        }

        if (edge == 0)
            return;
        else if (edge == 1) {
            if (H == 0)
                H = 1;
            else if (H == 1)
                H = 0;
            else if (H == 2)
                H = 3;
            else
                H = 2;
        } else if (edge == 2) {
            if (H == 0)
                H = 2;
            else if (H == 1)
                H = 3;
            else if (H == 2)
                H = 0;
            else
                H = 1;
        } else if (edge == 3) {
            if (H == 0)
                H = 3;
            else if (H == 1)
                H = 2;
            else if (H == 2)
                H = 1;
            else
                H = 0;
        }
    }

    static void fill(int i, int j) {
        int num = 0;
        if (H == 0) {
            for (int di = 0; di < 2; di++) {
                for (int dj = 0; dj < 2; dj++) {
                    arr[i + di][j + dj] = num++;
                }
            }
        } else if (H == 1) {
            for (int di = 0; di < 2; di++) {
                for (int dj = 1; dj >= 0; dj--) {
                    arr[i + di][j + dj] = num++;
                }
            }
        } else if (H == 2) {
            for (int di = 1; di >= 0; di--) {
                for (int dj = 0; dj < 2; dj++) {
                    arr[i + di][j + dj] = num++;
                }
            }
        } else {
            for (int di = 1; di >= 0; di--) {
                for (int dj = 1; dj >= 0; dj--) {
                    arr[i + di][j + dj] = num++;
                }
            }
        }
    }

    static void input() throws IOException {
        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * K; i++) {
            char cmd = st.nextToken().charAt(0);

            if (cmd == 'R' || cmd == 'L') {
                nj *= 2;
                lastRL = cmd;
            } else {
                ni *= 2;
                lastUD = cmd;
            }
        }
        H = Integer.parseInt(br.readLine());
        br.close();
    }

    static void print() throws IOException {
        for (int i = 0; i < ni; i++) {
            for (int j = 0; j < nj; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}