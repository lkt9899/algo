import java.io.*;
import java.util.*;

public class Main_17281 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int MAX = 9;
    static final int FULL = 14;

    static int round, ans = 0;

    static int[][] result;
    static int[] order = new int[MAX];
    static int[] newOrder = new int[MAX];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        solve();
        print();
    }

    static void input() throws Exception {
        round = Integer.parseInt(br.readLine());

        result = new int[round][MAX];
        for (int i = 0; i < round; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < MAX; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        int idx = 1;
        order[0] = 4;
        for (int i = 1; i < MAX; i++) {
            if (i == 4)
                idx++;
            order[i] = idx++;
        }

        do {
            sort();
            play(0, 0, 0);
        } while (nextPermutation());

        sb.append(ans + "");
    }

    static boolean nextPermutation() {
        int i = MAX - 1;
        while (i > 1 && order[i - 1] >= order[i])
            i--;

        if (i == 1)
            return false;

        int j = MAX - 1;
        while (order[i - 1] >= order[j])
            j--;

        swap(i - 1, j);

        int k = MAX - 1;
        while (i < k)
            swap(i++, k--);

        return true;
    }

    static void swap(int i, int j) {
        int tmp = order[i];
        order[i] = order[j];
        order[j] = tmp;
    }

    static void play(int cRound, int num, int score) {
        if (cRound == round) {
            ans = ans > score ? ans : score;
            return;
        }

        int outCount = 0;
        int cScore = 0;
        int base = 0;
        while (outCount != 3) {
            int res = result[cRound][newOrder[num++]];
            if (res == 0)
                outCount++;
            else {
                base++;
                base <<= res;
            }
            num %= MAX;
            cScore += Integer.bitCount(base >> 4);
            base &= FULL;
        }
        play(cRound + 1, num, score + cScore);
    }

    static void sort() {
        for (int i = 0; i < MAX; i++)
            newOrder[order[i] - 1] = i;
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}