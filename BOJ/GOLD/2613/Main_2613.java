import java.io.*;
import java.util.*;

public class Main_2613 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, start, end;
    static int[] tama;

    public static void main(String[] args) throws Exception {
        input();
        find(start, end);
        print();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tama = new int[N];

        st = new StringTokenizer(br.readLine());
        start = 0;
        end = 0;
        for (int i = 0; i < N; i++) {
            tama[i] = Integer.parseInt(st.nextToken());
            start = start > tama[i] ? start : tama[i];
            end += tama[i];
        }
    }

    static void find(int s, int e) {
        int mid = 0;
        while (s < e) {
            mid = (s + e) >> 1;
            if (divide(mid))
                e = mid;
            else
                s = mid + 1;
        }

        sb.append(s + "\n");
        getAns(s);
    }

    static boolean divide(int max) {
        int g = 1;
        int sum = 0;
        int idx = 0;
        while (idx < N) {
            if (sum + tama[idx] <= max) {
                sum += tama[idx];
            } else {
                sum = tama[idx];
                g++;
            }
            idx++;
        }

        return g <= M;
    }
    
    static void getAns(int max) {
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (sum + tama[i] <= max) {
                sum += tama[i];
                cnt++;
            } else {
                sum = tama[i];
                M--;
                sb.append(cnt + " ");
                cnt = 1;
            }

            if (N - i == M) {
                sb.append(cnt + " ");
                M--;
                break;
            }
        }
        
        while (M != 0) {
            sb.append("1 ");
            M--;
        }
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
