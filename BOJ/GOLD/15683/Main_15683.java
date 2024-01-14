import java.io.*;
import java.util.*;

public class Main_15683 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 8;
    static final int WALL = 6;

    static int N, M, initEmpty, cctvCnt, max;

    static int[][] map;
    static boolean[][] visit;
    static CCTV[] cctvList = new CCTV[MAX];

    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    static class CCTV {
        int kind;
        int row;
        int col;

        public CCTV(int kind, int row, int col) {
            this.kind = kind;
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        init();
        input();
        backTracking(0, 0, 0);
        print();
    }

    static void init() throws IOException {
        initEmpty = 0;
        cctvCnt = 0;
        max = 0;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
    }

    static void input() throws Exception {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0)
                    initEmpty++;
                else if (map[i][j] != WALL) {
                    cctvList[cctvCnt++] = new CCTV(map[i][j], i, j);
                }
            }
        }
        br.close();
    }

    static void backTracking(int cctvNum, int rangeSum, int cnt) {
        if (cnt == cctvCnt) {
            max = max > rangeSum ? max : rangeSum;
            return;
        }

        int kind = cctvList[cctvNum].kind;
        int row = cctvList[cctvNum].row;
        int col = cctvList[cctvNum].col;
        int count = 0;

        List<int[]>[] watch = new ArrayList[4];
        for (int i = 0; i < 4; i++)
            watch[i] = new ArrayList<>();

        if (kind == 1) {
            for (int d = 0; d < 4; d++) {
                watch[d] = addAxis(row, col, d);
                count = watch[d].size();

                change(watch[d], -1);

                backTracking(cctvNum + 1, rangeSum + count, cnt + 1);

                change(watch[d], 0);
            }
        } else if (kind == 2) {
            for (int d = 0; d < 2; d++) {
                watch[d] = addAxis(row, col, d);
                watch[d + 2] = addAxis(row, col, d + 2);
                count = watch[d].size() + watch[d + 2].size();

                change(watch[d], -1);
                change(watch[d + 2], -1);

                backTracking(cctvNum + 1, rangeSum + count, cnt + 1);

                change(watch[d], 0);
                change(watch[d + 2], 0);
            }
        } else if (kind == 3) {
            for (int d = 0; d < 4; d++) {
                watch[d] = addAxis(row, col, d);
                watch[(d + 1) % 4] = addAxis(row, col, (d + 1) % 4);
                count = watch[d].size() + watch[(d + 1) % 4].size();

                change(watch[d], -1);
                change(watch[(d + 1) % 4], -1);

                backTracking(cctvNum + 1, rangeSum + count, cnt + 1);

                change(watch[d], 0);
                change(watch[(d + 1) % 4], 0);
            }
        } else if (kind == 4) {
            for (int d = 0; d < 4; d++) {
                watch[d] = addAxis(row, col, d);
                watch[(d + 1) % 4] = addAxis(row, col, (d + 1) % 4);
                watch[(d + 2) % 4] = addAxis(row, col, (d + 2) % 4);
                count = watch[d].size() + watch[(d + 1) % 4].size() + watch[(d + 2) % 4].size();

                change(watch[d], -1);
                change(watch[(d + 1) % 4], -1);
                change(watch[(d + 2) % 4], -1);

                backTracking(cctvNum + 1, rangeSum + count, cnt + 1);

                change(watch[d], 0);
                change(watch[(d + 1) % 4], 0);
                change(watch[(d + 2) % 4], 0);
            }
        } else {
            for (int d = 0; d < 4; d++) {
                watch[d] = addAxis(row, col, d);
                count += watch[d].size();
                change(watch[d], -1);
            }

            backTracking(cctvNum + 1, rangeSum + count, cnt + 1);

            for (int d = 0; d < 4; d++) {
                change(watch[d], 0);
            }
        }
    }

    static List<int[]> addAxis(int row, int col, int dir) {
        List<int[]> list = new ArrayList<>();
        int ni = row + di[dir];
        int nj = col + dj[dir];
        while (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] != WALL) {
            if (map[ni][nj] == 0) {
                list.add(new int[] { ni, nj });
            }

            ni += di[dir];
            nj += dj[dir];
        }

        return list;
    }

    static void change(List<int[]> list, int val) {
        for (int[] axis : list) {
            map[axis[0]][axis[1]] = val;
        }
    }

    static void print() throws Exception {
        sb.append((initEmpty - max) + "\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
