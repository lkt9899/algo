import java.io.*;
import java.util.*;

public class Main_22252 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int Q;
    static long ans;
    static HashMap<String, PriorityQueue<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        run();
        System.out.println(ans);
    }
    
    static void run() throws Exception {
        Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            int k = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                if (!map.containsKey(name)) {
                    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
                    map.put(name, pq);
                }

                for (int j = 0; j < k; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    map.get(name).add(val);
                }
            } else {
                buyInfo(name, k);
            }
        }
    }
    
    static void buyInfo(String name, int k) {
        if(!map.containsKey(name))
            return;

        PriorityQueue<Integer> pq = map.get(name);
        if (k > pq.size())
            k = pq.size();

        for (int i = 0; i < k; i++) {
            ans += pq.poll();
        }
    }
}
