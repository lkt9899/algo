import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16441 {
    static final int MAX = 100;
    static char[][] map = new char[MAX][MAX];
    static int[][] visited = new int[MAX][MAX];
    static int N, M;
    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };
    static ArrayList<Axis> wolf = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        run();
    }

    static class Axis {
        int x, y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void run() throws IOException {
        input();
        for (int i = 0; i < wolf.size(); i++)
            if (visited[wolf.get(i).x][wolf.get(i).y] == 0)
                bfs(wolf.get(i));
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'W')
                    wolf.add(new Axis(i, j));
                else if (map[i][j] == '.')
                    map[i][j] = 'P';
            }
        }
        br.close();
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map[i]);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void bfs(Axis start) {
        initVisited();
        Queue<Axis> q = new ArrayDeque<>();
        q.add(start);
        visited[start.x][start.y] = 1;
        while (!q.isEmpty()) {
            Axis c = q.poll();

            for (int i = 0; i < 4; i++) {
                int ni = c.x + di[i];
                int nj = c.y + dj[i];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == '#')
                    continue;
                if (map[ni][nj] == '+') {
                    while (map[ni][nj] == '+') {
                        ni += di[i];
                        nj += dj[i];
                    }
                    if (map[ni][nj] == '#') {
                        ni -= di[i];
                        nj -= dj[i];
                    }
                }

                if (visited[ni][nj] == 1)
                    continue;
                if (map[ni][nj] == 'P')
                    map[ni][nj] = '.';
                visited[ni][nj] = 1;
                q.add(new Axis(ni, nj));
            }
        }

    }

    static void initVisited() {
        for (int[] a : visited)
            Arrays.fill(a, 0);
    }
}
