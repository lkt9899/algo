import java.io.*;
import java.util.*;

public class Main_1759 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int L, C;
    static Character[] all;
    static int[] p;
    static List<String> ans = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        sol();
        print();
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        all = new Character[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            all[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(all);

        p = new int[C];
        for (int i = C - 1; i >= L; i--) {
            p[i] = 1;
        }
    }

    static void sol() {
        do {
            String s = "";
            int cflag = 0;
            int mflag = 0;
            for (int i = 0; i < C; i++) {
                if (p[i] == 0) {
                    s += all[i];
                    if (aeCheck(all[i]))
                        cflag++;
                    else
                        mflag++;
                }
            }
            if (cflag != 0 && mflag >= 2)
                ans.add(s);
        } while (nextPermutation());

        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i) + "\n");
        }
    }

    static boolean nextPermutation() {
        int i = C - 1;
        while (i > 0 && p[i - 1] >= p[i])
            i--;

        if (i == 0)
            return false;

        int j = C - 1;
        while (p[i - 1] >= p[j])
            j--;

        swap(i - 1, j);

        int k = C - 1;
        while (i < k)
            swap(i++, k--);

        return true;
    }

    static void swap(int i, int j) {
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }

    static void check(String s, int idx, int cFlag, int mFlag) {
        if (s.length() == L) {
            if (cFlag != 0 && mFlag >= 2)
                ans.add(s);
            return;
        }

        for (int i = idx; i < C; i++) {
            if (aeCheck(all[i])) {
                check(s + all[i], i + 1, cFlag + 1, mFlag);
            } else {
                check(s + all[i], i + 1, cFlag, mFlag + 1);
            }
        }
    }

    static boolean aeCheck(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    static void print() throws Exception {
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
