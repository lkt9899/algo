import java.io.*;
import java.util.*;

public class Main_1786 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static String T, P;
    static int cnt, si = -1;
    static Deque<Integer> ans = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        input();
        run();
    }
    
    static void run() throws Exception {
        kmp(T, P);
        print();
    }

    static int[] getPi(String p) {
        int m = p.length();
        int j = 0;
        int[] pi = new int[m];
        for (int i = 1; i < m; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j))
                j = pi[j - 1];

            if (p.charAt(i) == p.charAt(j))
                pi[i] = ++j;
        }

        return pi;
    }

    static void kmp(String s, String p) {
        int[] pi = getPi(p);

        int n = s.length();
        int m = p.length();
        int j = 0;
        for (int i = 0; i < n; i++) {
            while(j > 0 && s.charAt(i) != p.charAt(j))
                j = pi[j - 1];
            
            if (s.charAt(i) == p.charAt(j)) {
                si = i - j;

                if (j == m - 1) {
                    cnt++;
                    j = pi[j];
                    ans.add(si + 1);
                } else
                    j++;
            }
        }
    }
    
    static void input() throws Exception {
        T = br.readLine();
        P = br.readLine();
    }
    
    static void print() throws Exception {
        sb.append(cnt + "\n");
        while(!ans.isEmpty())
            sb.append(ans.poll() + " ");
        System.out.println(sb);
    }
}
