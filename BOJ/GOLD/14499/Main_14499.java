import java.io.*;
import java.util.*;

public class Main_14499 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int N_MAX = 20;
    static final int K_MAX = 1000;

    static int N, M, si, sj, K;
    static int[][] dice = {
            { 0, 0, 0 },
            { 0, 0, 0 },
            { 0, 0, 0 },
            { 0, 0, 0 }
    };

    static int[][] map = new int[N_MAX][N_MAX];
    // 1 : E | 2 : W | 3 : N | 4 : S
    static int[] di = { 0, 0, 0, -1, 1 };
    static int[] dj = { 0, 1, -1, 0, 0 };

    static int[] commands = new int[K_MAX];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        for (int i = 0; i < K; i++) {
            if (move(commands[i])) {
                update();
                read();        
            }
        }
        print();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        si = Integer.parseInt(st.nextToken());
        sj = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }
    }

    static boolean move(int dir) {
        int ni = si + di[dir];
        int nj = sj + dj[dir];

        if (ni < 0 || ni >= N || nj < 0 || nj >= M)
            return false;

        si = ni;
        sj = nj;

        int[][] tmp = new int[4][3];
        if (dir == 1) {
            tmp[1][0] = dice[3][1];
            tmp[1][1] = dice[1][0];
            tmp[1][2] = dice[1][1];
            tmp[3][1] = dice[1][2];
            tmp[2][1] = dice[2][1];
            tmp[0][1] = dice[0][1];
        } else if (dir == 2) {
            tmp[1][0] = dice[1][1];
            tmp[1][1] = dice[1][2];
            tmp[1][2] = dice[3][1];
            tmp[3][1] = dice[1][0];
            tmp[2][1] = dice[2][1];
            tmp[0][1] = dice[0][1];
        } else if (dir == 3) {
            tmp[1][0] = dice[1][0];
            tmp[1][2] = dice[1][2];
            tmp[0][1] = dice[1][1];
            tmp[1][1] = dice[2][1];
            tmp[2][1] = dice[3][1];
            tmp[3][1] = dice[0][1];
        } else if (dir == 4) {
            tmp[1][0] = dice[1][0];
            tmp[1][2] = dice[1][2];
            tmp[0][1] = dice[3][1];
            tmp[1][1] = dice[0][1];
            tmp[2][1] = dice[1][1];
            tmp[3][1] = dice[2][1];
        }

        for (int i = 0; i < 4; i++) {
            dice[i] = Arrays.copyOf(tmp[i], 3);
        }

        return true;
    }

    static void update() {
        if (map[si][sj] == 0) {
            map[si][sj] = dice[3][1];
        } else {
            dice[3][1] = map[si][sj];
            map[si][sj] = 0;
        }
    }
    
    static void read() {
        sb.append(dice[1][1] + "\n");
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
