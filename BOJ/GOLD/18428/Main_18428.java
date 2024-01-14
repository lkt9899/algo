import java.io.*;
import java.util.*;

public class Main_18428 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static char[][] map;
    static List<int[]> teachers = new ArrayList<>();
    static List<int[]> students = new ArrayList<>();
    static List<int[]> candidate = new ArrayList<>();
    static int[] di = { 0, 1, 0, -1 };
    static int[] dj = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        if (teachers.isEmpty() || students.isEmpty())
            System.out.println("YES");
        if (check())
            System.out.println("NO");
        else {
            getCand();
            if (candidate.isEmpty())
                System.out.println("YES");
            else {
                find(0, 0);
                System.out.println("NO");
            }
        }
    }

    static void find(int idx, int cnt) {
        if (cnt == 3 || cnt == candidate.size()) {
            if (isPossible()) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for (int i = idx; i < candidate.size(); i++) {
            int[] axis = candidate.get(i);
            if (map[axis[0]][axis[1]] == 'X') {
                map[axis[0]][axis[1]] = 'O';
                find(i + 1, cnt + 1);
                map[axis[0]][axis[1]] = 'X';
            }
        }
    }

    static boolean isPossible() {
        for (int[] teacher : teachers) {
            int ci = teacher[0];
            int cj = teacher[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                while (!isNotValid(ni, nj)) {
                    if (map[ni][nj] == 'O' || map[ni][nj] == 'T')
                        break;
                    if (map[ni][nj] == 'S') {
                        return false;
                    }
                    ni += di[d];
                    nj += dj[d];
                }
            }
        }

        return true;
    }

    static void getCand() {
        boolean[][] visited = new boolean[N][N];
        for (int[] teacher : teachers) {
            int ci = teacher[0];
            int cj = teacher[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                int cnt = 0;
                while (!isNotValid(ni, nj)) {
                    if (map[ni][nj] == 'T')
                        break;
                    if (map[ni][nj] == 'S') {
                        ni = ci;
                        nj = cj;
                        for (int i = 0; i < cnt; i++) {
                            ni += di[d];
                            nj += dj[d];
                            if (!visited[ni][nj]) {
                                visited[ni][nj] = true;
                                candidate.add(new int[] { ni, nj });
                            }
                        }
                        break;
                    }
                    ni += di[d];
                    nj += dj[d];
                    cnt++;
                }
            }
        }
    }

    static boolean check() {
        for (int[] teacher : teachers) {
            for (int d = 0; d < 4; d++) {
                int ni = teacher[0] + di[d];
                int nj = teacher[1] + dj[d];
                if (isNotValid(ni, nj))
                    continue;
                if (map[ni][nj] == 'S')
                    return true;
            }
        }

        return false;
    }

    static boolean isNotValid(int i, int j) {
        return (i < 0 || i >= N || j < 0 || j >= N);
    }

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T')
                    teachers.add(new int[] { i, j });
                else if (map[i][j] == 'S')
                    students.add(new int[] { i, j });
            }
        }
    }
}
