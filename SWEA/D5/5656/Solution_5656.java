import java.io.*;
import java.util.*;

public class Solution_5656 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, W, H, ans;
    static int[][] map;
    static int[][] copy;
    static int[] nums;

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        print();
    }

    static void run() throws Exception {
        ans = Integer.MAX_VALUE;
        input();
        perm(0);
        sb.append(ans + "\n");
    }

    static void perm(int cnt) {
        if (cnt == N) {
            play();
            ans = Math.min(ans, calc());
            copy();
            return;
        }

        for (int i = 0; i < W; i++) {
            nums[cnt] = i;
            perm(cnt + 1);
        }
    }

    static void play() {
        for (int n = 0; n < N; n++) {
            int j = nums[n];
            for (int i = 0; i < H; i++) {
                if (map[i][j] != 0) {
                    crush(i, j);
                    break;
                }
            }
            move();
        }
    }

    static void crush(int si, int sj) {
        int cnt = map[si][sj];
        map[si][sj] = 0;
        for (int d = 0; d < 4; d++) {
            for (int n = 1; n < cnt; n++) {
                int ni = si + di[d] * n;
                int nj = sj + dj[d] * n;

                if (ni < 0 || ni >= H || nj < 0 || nj >= W || map[ni][nj] == 0)
                    continue;

                if (map[ni][nj] == 1)
                    map[ni][nj] = 0;
                else
                    crush(ni, nj);
            }
        }
    }

    static void move() {
        for (int j = 0; j < W; j++) {
            int blank = H - 1;
            for (int i = H - 1; i >= 0; i--) {
                if (map[i][j] != 0) {
                    if (blank > i) {
                        map[blank][j] = map[i][j];
                        map[i][j] = 0;
                    }
                    blank--;
                }
            }
        }
    }

    static int calc() {
        int sum = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0)
                    sum++;
            }
        }
        return sum;
    }

    static void copy() {
        for (int i = 0; i < H; i++)
            map[i] = Arrays.copyOf(copy[i], W);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        copy = new int[H][W];
        nums = new int[N];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = copy[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}