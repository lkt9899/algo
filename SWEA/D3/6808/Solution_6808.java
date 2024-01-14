import java.io.*;
import java.util.*;

public class Solution_6808 {
    static BufferedReader br;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;

    static final int TOTAL = 362880;
    static final int MAX = 19;
    static final int SIZE = 9;

    static int winCnt;

    static boolean[] cards = new boolean[MAX];
    static int[] myCards = new int[SIZE];
    static int[] kyu = new int[SIZE];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb = new StringBuilder("#" + test_case + " ");
            run();
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static void run() throws Exception {
        init();
        input();
        int idx = 0;
        for (int i = 1; i < MAX; i++) {
            if (!cards[i]) {
                myCards[idx++] = i;
            }
        }

        Arrays.sort(myCards);
        do {
            getScore();
        } while (nextPermutation());
        print();
    }

    static boolean nextPermutation() {
        int i = SIZE - 1;
        while (i > 0 && myCards[i - 1] >= myCards[i])
            --i;

        if (i == 0)
            return false;

        int j = SIZE - 1;
        while (myCards[i - 1] >= myCards[j])
            --j;

        int temp = myCards[i - 1];
        myCards[i - 1] = myCards[j];
        myCards[j] = temp;

        int k = SIZE - 1;
        while (i < k) {
            temp = myCards[i];
            myCards[i] = myCards[k];
            myCards[k] = temp;
            ++i;
            --k;
        }

        return true;
    }

    static void getScore() {
        int Ascore = 0;
        int Bscore = 0;
        for (int i = 0; i < SIZE; i++) {
            if (myCards[i] > kyu[i]) {
                Ascore += myCards[i] + kyu[i];
            } else {
                Bscore += kyu[i] + myCards[i];
            }
        }

        if (Ascore < Bscore)
            winCnt++;
    }

    static void init() {
        winCnt = 0;
        Arrays.fill(cards, false);
    }

    static void input() throws Exception {
        int idx = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 9; i++) {
            int card = Integer.parseInt(st.nextToken());
            cards[card] = true;
            kyu[idx++] = card;
        }
    }

    static void print() throws Exception {
        sb.append(winCnt + " " + (TOTAL - winCnt) + "\n");
        bw.write(sb.toString());
    }
}