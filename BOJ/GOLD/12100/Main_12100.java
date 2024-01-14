import java.io.*;
import java.util.*;

public class Main_12100 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, ans;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        input();
        backTracking(board, 0);
        if (ans < 2)
            throw new IllegalArgumentException("max value is greater than 2.");
        System.out.println(ans);
    }

    static void backTracking(int[][] b, int cnt) {
        if (cnt == 5) {
            ans = Math.max(ans, getMax(b));
            return;
        }

        int[][] cpy = new int[N][N];
        backTracking(U(copy(b, cpy)), cnt + 1);
        backTracking(R(copy(b, cpy)), cnt + 1);
        backTracking(D(copy(b, cpy)), cnt + 1);
        backTracking(L(copy(b, cpy)), cnt + 1);
    }

    static int[][] U(int[][] b) {
        for (int j = 0; j < N; j++) {
            int limit = 0;
            boolean[] sum = new boolean[N];
            for (int i = 1; i < N; i++) {
                if (b[i][j] == 0)
                    continue;

                int row = i;
                while (row > limit && b[row - 1][j] == 0) {
                    row--;
                }

                if (row > 0 && b[i][j] == b[row - 1][j] && !sum[row - 1]) {
                    b[row - 1][j] = b[i][j] * 2;
                    b[i][j] = 0;
                    sum[row - 1] = true;
                    limit = row - 1;
                } else {
                    if (row == i)
                        continue;

                    b[row][j] = b[i][j];
                    b[i][j] = 0;
                    limit = row;
                }
            }
        }

        return b;
    }

    static int[][] R(int[][] b) {
        for (int i = 0; i < N; i++) {
            int limit = N - 1;
            boolean[] sum = new boolean[N];
            for (int j = N - 2; j >= 0; j--) {
                if (b[i][j] == 0)
                    continue;

                int col = j;
                while (col < limit && b[i][col + 1] == 0) {
                    col++;
                }
                if (col < N - 1 && b[i][j] == b[i][col + 1] && !sum[col + 1]) {
                    b[i][col + 1] = b[i][j] * 2;
                    b[i][j] = 0;
                    sum[col + 1] = true;
                    limit = col + 1;
                } else {
                    if (col == j)
                        continue;

                    b[i][col] = b[i][j];
                    b[i][j] = 0;
                    limit = col;
                }
            }
        }

        return b;
    }

    static int[][] D(int[][] b) {
        for (int j = 0; j < N; j++) {
            int limit = N - 1;
            boolean[] sum = new boolean[N];
            for (int i = N - 2; i >= 0; i--) {
                if (b[i][j] == 0)
                    continue;

                int row = i;
                while (row < limit && b[row + 1][j] == 0) {
                    row++;
                }

                if (row < N - 1 && b[i][j] == b[row + 1][j] && !sum[row + 1]) {
                    b[row + 1][j] = b[i][j] * 2;
                    b[i][j] = 0;
                    sum[row + 1] = true;
                    limit = row + 1;
                } else {
                    if (row == i)
                        continue;

                    b[row][j] = b[i][j];
                    b[i][j] = 0;
                    limit = row;
                }
            }
        }

        return b;
    }

    static int[][] L(int[][] b) {
        for (int i = 0; i < N; i++) {
            int limit = 0;
            boolean[] sum = new boolean[N];
            for (int j = 1; j < N; j++) {
                if (b[i][j] == 0)
                    continue;

                int col = j;
                while (col > limit && b[i][col - 1] == 0) {
                    col--;
                }

                if (col > 0 && b[i][j] == b[i][col - 1] && !sum[col - 1]) {
                    b[i][col - 1] = b[i][j] * 2;
                    b[i][j] = 0;
                    sum[col - 1] = true;
                    limit = col - 1;
                } else {
                    if (col == j)
                        continue;

                    b[i][col] = b[i][j];
                    b[i][j] = 0;
                    limit = col;
                }
            }
        }

        return b;
    }

    static int[][] copy(int[][] b, int[][] cpy) {
        for (int i = 0; i < N; i++)
            cpy[i] = Arrays.copyOf(b[i], N);

        return cpy;
    }

    static int getMax(int[][] b) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, b[i][j]);
            }
        }

        return max;
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
