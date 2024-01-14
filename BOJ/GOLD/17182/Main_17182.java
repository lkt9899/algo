import java.io.*;
import java.util.*;

public class Main_17182 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 1 << 11;
    static final int INF = 98765432;

    static int N, K;
    static int[][] dist;
    static int[][] cost;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        FW();
        System.out.println(check(K, 0));
    }

    static void FW() {
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < N; s++) {
                for (int e = 0; e < N; e++) {
                    cost[s][e] = Math.min(cost[s][e], cost[s][k] + cost[k][e]);
                }
            }
        }
    }

    static int check(int idx, int visit) {
        if (visit == (1 << N) - 1) {
            return dist[K][visit] = 0;
        }

        if (dist[idx][visit] != INF)
            return dist[idx][visit];

        dist[idx][visit] = INF;
        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0) {
                dist[idx][visit] = Math.min(check(i, visit | 1 << i) + cost[idx][i], dist[idx][visit]);
            }
        }

        return dist[idx][visit];
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[N][MAX];
        for (int i = 0; i < N; i++)
            Arrays.fill(dist[i], INF);

        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}