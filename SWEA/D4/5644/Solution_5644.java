import java.io.*;
import java.util.*;

public class Solution_5644 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int M_MAX = 100;
    static final int A_MAX = 8;
    static final int L_MAX = 11;

    static int M, A, ai, aj, bi, bj, charge;
    static AP[][] BC = new AP[L_MAX][L_MAX];
    static int[][] move = new int[2][M_MAX];
    static int[] di = { 0, -1, 0, 1, 0 };
    static int[] dj = { 0, 0, 1, 0, -1 };

    static class AP {
        List<int[]> val = new ArrayList<>();
        int num = 0;
        int size = 0;

        public void add(int[] val) {
            this.val.add(val);
            size++;
        }

        public int size() {
            return this.size;
        }

        public int getCharge(int idx) {
            return this.val.get(idx)[0];
        }

        public int getNum(int idx) {
            return this.val.get(idx)[1];
        }
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            run();
            sb.append("#" + test_case + " " + charge + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void run() throws Exception {
        init();
        input();
        start(0);
    }

    static void start(int cnt) {
        if (BC[ai][aj].size() != 0 && BC[bi][bj].size() != 0) {
            if (BC[ai][aj].size() == 1 && BC[bi][bj].size() == 1) {
                if (BC[ai][aj].getNum(0) == BC[bi][bj].getNum(0)) {
                    charge += BC[ai][aj].getCharge(0);
                } else {
                    charge += BC[ai][aj].getCharge(0);
                    charge += BC[bi][bj].getCharge(0);
                }
            } else {
                int max = 0;

                for (int i = 0; i < BC[ai][aj].size(); i++) {
                    for (int j = 0; j < BC[bi][bj].size(); j++) {
                        int anum = BC[ai][aj].getNum(i);
                        int bnum = BC[bi][bj].getNum(j);

                        int sum = BC[ai][aj].getCharge(i) + BC[bi][bj].getCharge(j);
                        if (sum > max && anum != bnum) {
                            max = sum;
                        }
                    }
                }

                charge += max;
            }
        } else {
            if (BC[ai][aj].size() != 0) {
                int max = 0;
                for (int i = 0; i < BC[ai][aj].size(); i++) {
                    max = max > BC[ai][aj].getCharge(i) ? max : BC[ai][aj].getCharge(i);
                }
                charge += max;
            } else if (BC[bi][bj].size() != 0) {
                int max = 0;
                for (int i = 0; i < BC[bi][bj].size(); i++) {
                    max = max > BC[bi][bj].getCharge(i) ? max : BC[bi][bj].getCharge(i);
                }
                charge += max;
            }
        }

        if (cnt == M) {
            return;
        }

        int AD = move[0][cnt];
        int BD = move[1][cnt];

        ai += di[AD];
        aj += dj[AD];
        bi += di[BD];
        bj += dj[BD];
        start(cnt + 1);
    }

    static void init() {
        for (int i = 1; i < L_MAX; i++) {
            for (int j = 1; j < L_MAX; j++) {
                BC[i][j] = new AP();
            }
        }

        charge = 0;
        ai = 1;
        aj = 1;
        bi = 10;
        bj = 10;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                move[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= A; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            fill(x, y, d, c, i);
        }
    }

    static void fill(int i, int j, int d, int c, int num) {
        int cnt = d;
        while (cnt != 0) {
            int ui = i - cnt;
            int bi = i + cnt;
            for (int dj = j - (d - cnt); dj <= j + (d - cnt); dj++) {
                if (dj > 0 && dj < L_MAX) {
                    if (ui > 0 && ui < L_MAX)
                        BC[ui][dj].add(new int[] { c, num });
                    if (bi > 0 && bi < L_MAX)
                        BC[bi][dj].add(new int[] { c, num });
                }
            }
            cnt--;
        }

        for (int dj = j - d; dj <= j + d; dj++) {
            if (dj < 1 || dj >= L_MAX)
                continue;
            BC[i][dj].add(new int[] { c, num });
        }
    }
}
