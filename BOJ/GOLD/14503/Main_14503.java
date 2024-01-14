import java.io.*;
import java.util.*;

public class Main_14503 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int WALL = 1;

    static int N, M, ans;
    static Axis robot;
    static int[][] map;
    static boolean[][] visited;

    static int[] di = { -1, 0, 1, 0 };
    static int[] dj = { 0, 1, 0, -1 };

    static class Axis {
        int x, y, d;

        public Axis(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public void rotate() {
            if (d == 0)
                this.d = 3;
            else
                this.d--;
        }

        public boolean isNextEmpty() {
            int ni = x + di[d];
            int nj = y + dj[d];
            
            if(ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == WALL || visited[ni][nj])
                return false;
            
            return true;
        }
        
        public boolean isNearEmpty() {
            for (int i = 0; i < 4; i++) {
                int ni = x + di[i];
                int nj = y + dj[i];
                
                if(ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == WALL || visited[ni][nj])
                    continue;
                
                return true;
            }

            return false;
        }

        public void move() {
            x += di[d];
            y += dj[d];
        }

        public boolean back() {
            d = (d + 2) % 4;
            int ni = x + di[d];
            int nj = y + dj[d];

            if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == WALL)
                return false;

            move();
            d = (d + 2) % 4;

            return true;
        }

        public void clean() {
            if (!visited[x][y]) {
                visited[x][y] = true;
                ans++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        run();
    }
    
    static void run() throws Exception {
        while (true) {
            robot.clean();
            if (robot.isNearEmpty()) {
                robot.rotate();
                while (!robot.isNextEmpty())
                    robot.rotate();
                robot.move();
            } else {
                if (!robot.back())
                    break;
            }
        }
        System.out.println(ans);
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        robot = new Axis(x, y, d);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
