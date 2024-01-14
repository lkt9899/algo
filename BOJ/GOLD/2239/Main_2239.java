import java.io.*;
import java.util.*;

public class Main_2239_이경태 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int[][] sdoku = new int[9][9];
    static boolean[][] row = new boolean[9][10];
    static boolean[][] col = new boolean[9][10];
    static boolean[][][] sq = new boolean[3][3][10];

    public static void main(String[] args) throws Exception {
        input();
        find(0, 0);
    }
    
    static boolean find(int pi, int pj) throws Exception {
        for (int i = pi; i < 9; i++) {
            for (int j = pj; j < 9; j++) {
                if (sdoku[i][j] == 0) {
                    boolean flag = false;
                    for (int num = 1; num <= 9; num++) {
                        if (!row[i][num] && !col[j][num] && !sq[i / 3][j / 3][num]) {
                            flag = true;
                            sdoku[i][j] = num;

                            change(i, j, num, true);
                            if (j == 8) {
                                flag = find(i + 1, 0);
                            } else {
                                flag = find(i, j + 1);
                            }

                            sdoku[i][j] = 0;
                            change(i, j, num, false);
                        }
                    }
                    if (!flag)
                        return flag;
                }
            }
            pj = 0;
        }
        print();
        return true;
    }

    static void change(int i, int j, int num, boolean b) {
        row[i][num] = b;
        col[j][num] = b;
        sq[i / 3][j / 3][num] = b;
    }
    
    static void input() throws Exception {
        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                int num = s.charAt(j) - '0';
                sdoku[i][j] = num;
                row[i][num] = true;
                col[j][num] = true;
                sq[i / 3][j / 3][num] = true;
            }
        }
    }
    
    static void print() throws Exception {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(sdoku[i][j] + "");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        System.exit(0);
    }
}