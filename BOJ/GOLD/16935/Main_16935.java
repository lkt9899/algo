import java.io.*;
import java.util.*;

public class Main_16935 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;

    static final int MAX = 100;
    static final int R_MAX = 1000;
    static int N, M, R;
    static int[][] arr;
    static int[] cmd = new int[R_MAX];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();

        for (int i = 0; i < R; i++) {
            int c = cmd[i];
            // 1, 2, 5, 6 용 배열
            int[][] cpy = new int[arr.length][arr[0].length];
            // 3, 4 용 배열
            int[][] cpy2 = new int[arr[0].length][arr.length];

            switch (c) {
                case 1:
                    func1(cpy);
                    break;
                case 2:
                    func2(cpy);
                    break;
                case 3:
                    func3(cpy2);
                    break;
                case 4:
                    func4(cpy2);
                    break;
                case 5:
                    func5(cpy);
                    break;
                case 6:
                    func6(cpy);
                    break;
                default:
                    break;
            }
        }

        print();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++)
            cmd[i] = Integer.parseInt(st.nextToken());
        br.close();
    }

    static void print() {
        sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void func1(int[][] cpy) {
        for (int ci = 0, ai = cpy.length - 1; ci < cpy.length; ci++, ai--) {
            for (int j = 0; j < cpy[0].length; j++) {
                cpy[ci][j] = arr[ai][j];
            }
        }
        arrCopy(cpy);
    }

    static void func2(int[][] cpy) {
        for (int i = 0; i < cpy.length; i++) {
            for (int cj = 0, aj = cpy[0].length - 1; cj < cpy[0].length; cj++, aj--) {
                cpy[i][cj] = arr[i][aj];
            }
        }
        arrCopy(cpy);
    }

    static void func3(int[][] cpy) {
        for (int cj = cpy[0].length - 1, ai = 0; ai < cpy[0].length; cj--, ai++) {
            for (int ci = 0, aj = 0; aj < cpy.length; ci++, aj++) {
                cpy[ci][cj] = arr[ai][aj];
            }
        }
        arrCopy(cpy);
    }

    static void func4(int[][] cpy) {
        for (int cj = 0, ai = 0; ai < cpy[0].length; cj++, ai++) {
            for (int ci = cpy.length - 1, aj = 0; aj < cpy.length; ci--, aj++) {
                cpy[ci][cj] = arr[ai][aj];
            }
        }
        arrCopy(cpy);
    }

    static void func5(int[][] cpy) {
        // 3 -> 2
        for (int ci = 0, ai = cpy.length / 2; ai < cpy.length; ci++, ai++) {
            for (int j = 0; j < cpy[0].length / 2; j++) {
                cpy[ci][j] = arr[ai][j];
            }
        }

        // 2 -> 1
        for (int i = 0; i < cpy.length / 2; i++) {
            for (int cj = cpy[0].length / 2, aj = 0; aj < cpy[0].length / 2; cj++, aj++) {
                cpy[i][cj] = arr[i][aj];
            }
        }

        // 1 -> 4
        for (int ci = cpy.length / 2, ai = 0; ai < cpy.length / 2; ci++, ai++) {
            for (int j = cpy[0].length / 2; j < cpy[0].length; j++) {
                cpy[ci][j] = arr[ai][j];
            }
        }

        // 4 -> 3
        for (int i = cpy.length / 2; i < cpy.length; i++) {
            for (int cj = 0, aj = cpy[0].length / 2; aj < cpy[0].length; cj++, aj++) {
                cpy[i][cj] = arr[i][aj];
            }
        }
        arrCopy(cpy);
    }

    static void func6(int[][] cpy) {
        // 1 -> 2
        for (int i = 0; i < cpy.length / 2; i++) {
            for (int cj = 0, aj = cpy[0].length / 2; aj < cpy[0].length; cj++, aj++) {
                cpy[i][cj] = arr[i][aj];
            }
        }

        // 2 -> 3
        for (int ci = cpy.length / 2, ai = 0; ai < cpy.length / 2; ci++, ai++) {
            for (int j = 0; j < cpy[0].length / 2; j++) {
                cpy[ci][j] = arr[ai][j];
            }
        }

        // 3 -> 4
        for (int i = cpy.length / 2; i < cpy.length; i++) {
            for (int cj = cpy[0].length / 2, aj = 0; aj < cpy[0].length / 2; cj++, aj++) {
                cpy[i][cj] = arr[i][aj];
            }
        }

        // 4 -> 1
        for (int ci = 0, ai = cpy.length / 2; ai < cpy.length; ci++, ai++) {
            for (int j = cpy[0].length / 2; j < cpy[0].length; j++) {
                cpy[ci][j] = arr[ai][j];
            }
        }

        arrCopy(cpy);
    }

    static void arrCopy(int[][] cpy) {
        arr = new int[cpy.length][cpy[0].length];
        for (int i = 0; i < cpy.length; i++)
            arr[i] = Arrays.copyOf(cpy[i], cpy[i].length);
    }

}
