import java.io.*;
import java.util.*;

public class Main_6987 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int R_MAX = 4;
    static final int C_MAX = 3;
    static final int T_MAX = 6;
    static final int G_MAX = 15;

    static final int WIN = 0;
    static final int DRAW = 1;
    static final int LOSE = 2;

    static int[][][] result = new int[R_MAX][T_MAX][C_MAX];
    static int[] ans = new int[4];
    static int[] t1 = new int[G_MAX];
    static int[] t2 = new int[G_MAX];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        makeGame();
        check();
        print();
    }

    static void makeGame() {
        int idx = 0;
        for (int i = 0; i < T_MAX; i++) {
            for (int j = i + 1; j < T_MAX; j++) {
                t1[idx] = i;
                t2[idx] = j;
                idx++;
            }
        }
    }

    static void check() {
        for (int i = 0; i < R_MAX; i++) {
            if (ans[i] == -1) {
                ans[i] = 0;
                continue;
            }

            if (simulation(i, 0, false))
                ans[i] = 1;
        }
    }

    static boolean simulation(int simulationNum, int cnt, boolean isEnd) {
        if (cnt == 15) {
            return true;
        }

        int home = t1[cnt];
        int away = t2[cnt];

        result[simulationNum][home][WIN]--;
        result[simulationNum][away][LOSE]--;
        if (result[simulationNum][home][WIN] >= 0 && result[simulationNum][away][LOSE] >= 0)
            isEnd = simulation(simulationNum, cnt + 1, isEnd);
        result[simulationNum][home][WIN]++;
        result[simulationNum][away][LOSE]++;

        result[simulationNum][home][LOSE]--;
        result[simulationNum][away][WIN]--;
        if (result[simulationNum][home][LOSE] >= 0 && result[simulationNum][away][WIN] >= 0)
            isEnd = simulation(simulationNum, cnt + 1, isEnd);
        result[simulationNum][home][LOSE]++;
        result[simulationNum][away][WIN]++;

        result[simulationNum][home][DRAW]--;
        result[simulationNum][away][DRAW]--;
        if (result[simulationNum][home][DRAW] >= 0 && result[simulationNum][away][DRAW] >= 0)
            isEnd = simulation(simulationNum, cnt + 1, isEnd);
        result[simulationNum][home][DRAW]++;
        result[simulationNum][away][DRAW]++;

        return isEnd;
    }

    static void input() throws Exception {

        for (int i = 0; i < R_MAX; i++) {
            int total = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < T_MAX; j++) {
                result[i][j][WIN] = Integer.parseInt(st.nextToken());
                result[i][j][DRAW] = Integer.parseInt(st.nextToken());
                result[i][j][LOSE] = Integer.parseInt(st.nextToken());
                int sum = result[i][j][WIN] + result[i][j][DRAW] + result[i][j][LOSE];
                total += sum;
                if (sum > 5)
                    ans[i] = -1;
            }

            if (total != 30)
                ans[i] = -1;
        }
        br.close();
    }

    static void print() throws Exception {
        for (int i = 0; i < R_MAX; i++)
            sb.append(ans[i] + " ");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
