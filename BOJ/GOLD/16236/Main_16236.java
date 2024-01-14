import java.io.*;
import java.util.*;

public class Main_16236 {
    static final int MAX = 20;
    static int N, age, ans;
    static int[][] map = new int[MAX][MAX];
    static int[][] visited = new int[MAX][MAX];
    static int[] di = { -1, 0, 0, 1 };
    static int[] dj = { 0, -1, 1, 0 };
    static Axis start;

    public static void main(String[] args) throws IOException {
        run();
    }

    static class Axis implements Comparable<Axis> {
        public Integer x, y, t;

        public Axis(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        @Override
        public int compareTo(Axis o) {
            if (t == o.t) {
                if (x == o.x) {
                    return y.compareTo(o.y);
                }
                return x.compareTo(o.x);
            }
            return t.compareTo(o.t);
        }
    }

    static void run() throws IOException {
        init();
        input();
        find(start, 0);

        System.out.println(new StringBuilder(ans + "").toString());
    }

    static void init() {
        age = 2;
        ans = 0;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    start = new Axis(i, j, 0);
                    map[i][j] = 0;
                }
            }
        }
        br.close();
    }

    static void find(Axis cur, int cnt) {
        if (cnt == age) {
            cnt = 0;
            age++;
        }

        initVisited();

        ArrayList<Axis> toEat = new ArrayList<>(50);
        Queue<Axis> q = new ArrayDeque<>();
        q.add(cur);
        visited[cur.x][cur.y] = 1;
        while (!q.isEmpty()) {
            Axis c = q.poll();

            if (!toEat.isEmpty() && c.t > toEat.get(0).t)
                break;

            for (int i = 0; i < 4; i++) {
                int ni = c.x + di[i];
                int nj = c.y + dj[i];

                if (ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj] != 0)
                    continue;
                if (map[ni][nj] > age)
                    continue;

                visited[ni][nj] = 1;
                Axis tmp = new Axis(ni, nj, c.t + 1);
                if (map[ni][nj] != 0 && map[ni][nj] < age) {
                    toEat.add(tmp);
                } else {
                    q.add(tmp);
                }
            }
        }

        if (!toEat.isEmpty()) {
            Collections.sort(toEat);

            Axis fish = toEat.get(0);
            map[fish.x][fish.y] = 0;
            cnt++;
            ans += fish.t;
            fish.t = 0;
            find(fish, cnt);
        }
    }

    static void initVisited() {
        for (int[] a : visited)
            Arrays.fill(a, 0);
    }
}
