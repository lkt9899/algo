import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main_7662 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int K;
    static TreeMap<Integer, Integer> map = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            run();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void run() throws Exception {
        K = Integer.parseInt(br.readLine());

        char cmd;
        int n;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken().charAt(0);
            n = Integer.parseInt(st.nextToken());

            if (cmd == 'I') {
                if (!map.containsKey(n))
                    map.put(n, 0);
                else
                    map.put(n, map.get(n) + 1);
            } else {
                if (map.isEmpty())
                    continue;

                if (n == 1) {
                    int key = map.lastKey();
                    if (map.get(key) == 0)
                        map.remove(key);
                    else
                        map.put(key, map.get(key) - 1);
                } else {
                    int key = map.firstKey();
                    if (map.get(key) == 0)
                        map.remove(key);
                    else
                        map.put(key, map.get(key) - 1);
                }
            }
        }

        if (map.isEmpty())
            sb.append("EMPTY\n");
        else
            sb.append(map.lastKey() + " " + map.firstKey() + "\n");
        map.clear();
    }
}
