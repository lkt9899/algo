import java.io.*;
import java.util.*;

public class Main_1062 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K, ans;
    static String[] str;
    static boolean[] alpha = new boolean[26];

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        if (K < 5)
            ans = 0;
        else {
            init();
            check(0, 0);
        }

        System.out.println(ans);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
            str[i] = str[i].substring(4, str[i].length() - 4);
            str[i] = removeDuplication(str[i]);
        }
    }

    static String removeDuplication(String s) {
        if(s == null || s.isEmpty())
            return s;

        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.add(c))
                sb.append(c);
        }
        
        return sb.toString();
    }
    
    static void init() {
        String s = "antatica";
        for (int i = 0; i < s.length(); i++) {
            if (!alpha[s.charAt(i) - 'a']) {
                alpha[s.charAt(i) - 'a'] = true;
            }
        }
        K -= 5;
    }

    static void check(int start, int cnt) {
        if (cnt == K) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                boolean flag = true;
                for (int j = 0; j < str[i].length(); j++) {
                    if (!alpha[str[i].charAt(j) - 'a']) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    sum++;
            }
            ans = ans > sum ? ans : sum;
            return;
        }
        
        for (int i = start; i < 26; i++) {
            if (!alpha[i]) {
                alpha[i] = true;
                check(i + 1, cnt + 1);
                alpha[i] = false;
            }
        }
    }
}
