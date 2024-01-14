import java.io.*;
import java.util.*;

public class Solution_1873_이경태 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final char GROUND = '.';
    static final char BRICK = '*';
    static final char STEEL = '#';
    static final char WATER = '-';

    static final int MAX = 20;
    static final int N_MAX = 100;

    static int H, W, N;
    static int ti, tj, td;
    static char[][] map = new char[MAX][MAX];
    static char[] command;
    // 0 : R, 1 : D, 2 : L, 3 : U
    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#" + test_case + " ");
            run();
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    static void run() throws Exception {
        input();
        for (int i = 0; i < N; i++) {
            char cmd = command[i];
            switch (cmd) {
                case 'U':
                    move(3);
                    break;
                case 'D':
                    move(1);
                    break;
                case 'L':
                    move(2);
                    break;
                case 'R':
                    move(0);
                    break;
                case 'S':
                    shoot();
                    break;
                default:
                    break;
            }
        }

        setTank();
        print();
    }

    static void move(int D) {
        td = D;
        int ni = ti + di[D];
        int nj = tj + dj[D];

        if (boundCheck(ni, nj) || map[ni][nj] != GROUND)
            return;

        ti += di[D];
        tj += dj[D];
        return;
    }

    static void shoot() {
        int ni = ti + di[td];
        int nj = tj + dj[td];

        while (true) {
            if (boundCheck(ni, nj) || map[ni][nj] == STEEL) {
                break;
            } else if (map[ni][nj] == BRICK) {
                map[ni][nj] = GROUND;
                break;
            } else {
                ni += di[td];
                nj += dj[td];
            }
        }
    }

    static void setTank() {
        if (td == 0)
            map[ti][tj] = '>';
        else if (td == 1)
            map[ti][tj] = 'v';
        else if (td == 2)
            map[ti][tj] = '<';
        else
            map[ti][tj] = '^';
    }

    static boolean boundCheck(int i, int j) {
        return (i < 0 || i >= H || j < 0 || j >= W);
    }

    static int isTank(char c) {
        if (c == '<')
            return 2;
        else if (c == '>')
            return 0;
        else if (c == '^')
            return 3;
        else if (c == 'v')
            return 1;
        else
            return -1;
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        for (int i = 0; i < H; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                map[i][j] = tmp[j];
                int d = isTank(map[i][j]);
                if (d != -1) {
                    ti = i;
                    tj = j;
                    td = d;
                    map[i][j] = '.';
                }
            }
        }

        N = Integer.parseInt(br.readLine());
        char[] tmp = br.readLine().toCharArray();
        command = Arrays.copyOf(tmp, N);
    }

    static void print() throws Exception {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
    }
}
