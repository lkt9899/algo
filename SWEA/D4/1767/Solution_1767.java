import java.io.*;
import java.util.*;

public class Solution_1767 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 12;

    static int N, coreCnt, connected, ans;

    static int[][] arr = new int[MAX][MAX];
    static Axis[] cores = new Axis[MAX];
    static int[] p;

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class Axis {
        int x;
        int y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        print();
    }

    static void run() throws Exception {
        init();
        input();

        for (int i = coreCnt; i > 0; i--) {
            p = new int[coreCnt];
            for (int j = coreCnt - 1; j >= coreCnt - i; j--) {
                p[j] = 1;
            }

            do {
                search(0, 0, 0, i);
            } while (nextPermutation());

            if (ans != Integer.MAX_VALUE)
                break;
        }

        sb.append(ans + "\n");
    }

    static void init() {
        ans = Integer.MAX_VALUE;
        connected = 0;
        coreCnt = 0;
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    if (i == 0 || j == 0 || i == N - 1 || j == N - 1)
                        connected++;
                    else
                        cores[coreCnt++] = new Axis(i, j);
                }
            }
        }

    }

    static boolean nextPermutation() {
        int i = coreCnt - 1;
        while (i > 0 && p[i - 1] >= p[i])
            i--;
        if (i == 0)
            return false;

        int j = coreCnt - 1;
        while (p[i - 1] >= p[j])
            j--;

        swap(i - 1, j);

        int k = coreCnt - 1;
        while (i < k)
            swap(i++, k--);

        return true;
    }

    static void swap(int i, int j) {
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }

    static void search(int idx, int total_len, int cnt, int maxCnt) {
        if (cnt == maxCnt) {
            ans = ans < total_len ? ans : total_len;
            return;
        }

        if (p[idx] == 0)
            search(idx + 1, total_len, cnt, maxCnt);

        int len;
        for (int i = 0; i < 4; i++) {
            List<Axis> toGo = go(cores[idx], i);
            len = toGo.size();

            if (len == 0)
                continue;

            fill(toGo, -1);
            search(idx + 1, total_len + len, cnt + 1, maxCnt);
            fill(toGo, 0);
        }
    }

    static List<Axis> go(Axis cur, int dir) {
        List<Axis> result = new ArrayList<>();

        int ni = cur.x + di[dir];
        int nj = cur.y + dj[dir];
        while (isValid(ni, nj) && arr[ni][nj] == 0) {
            result.add(new Axis(ni, nj));
            ni += di[dir];
            nj += dj[dir];
        }

        if (isValid(ni, nj)) {
            result = new ArrayList<>();
        }

        return result;
    }

    static void fill(List<Axis> toGo, int val) {
        for (Axis a : toGo)
            arr[a.x][a.y] = val;
    }

    static boolean isValid(int i, int j) {
        return (i >= 0 && i < N && j >= 0 && j < N);
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}