import java.io.*;
import java.util.*;

public class Main_15961 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 3001;

    static int N, d, k, c, ans, maxCnt = 0, free = 0;

    static boolean[] sushi;
    static int[] sushiList;

    public static void main(String[] args) throws Exception {
        input();
        sw();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new boolean[d + 1];
        sushiList = new int[N];

        for (int i = 0; i < N; i++) {
            int s = Integer.parseInt(br.readLine());

            sushiList[i] = s;

            if (!sushi[s]) {
                maxCnt++;
                sushi[s] = true;
            }
        }

        if (!sushi[c])
            free = 1;
    }

    static void sw() {
        int[] visit = new int[d + 1];
        int cnt = 0;

        for (int i = 0; i < k; i++) {
            int s = sushiList[i];
            if (visit[s] == 0) {
                cnt++;
            }
            visit[s]++;
        }

        ans = cnt;
        if (visit[c] == 0 || free == 1)
            ans++;

        for (int i = 0; i < N; i++) {
            int s = sushiList[i];
            visit[s]--;
            if (visit[s] == 0)
                cnt--;

            s = sushiList[(i + k) % N];
            if (visit[s] == 0)
                cnt++;
            visit[s]++;

            if (visit[c] == 0 || free == 1)
                ans = ans > cnt + 1 ? ans : cnt + 1;
            else
                ans = ans > cnt ? ans : cnt;

            if (ans == maxCnt || ans == k + 1)
                break;
        }
        System.out.println(ans);
    }
}