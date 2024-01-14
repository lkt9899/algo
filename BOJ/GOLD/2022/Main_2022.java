import java.io.*;
import java.util.*;

public class Main_2022 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static double x, y, c, right;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        System.out.printf("%.3f", BS(0, right));
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        if(x > y)
            right = y;
        else
            right = x;
    }

    static double BS(double start, double end) {
        double mid = (start + end) * 0.5;
        double var = check(mid);

        if(Math.abs(var) < 0.001)
            return mid;
        else {
            if (var > 0) {
                return BS(mid, end);
            } else {
                return BS(start, mid);
            }
        }
    }

    static double check(double Z) {
        double h1 = Math.sqrt(Math.pow(y, 2) - Math.pow(Z, 2));
        double h2 = Math.sqrt(Math.pow(x, 2) - Math.pow(Z, 2));
        double result = (h1 * h2) / (h1 + h2);
        return result - c;
    }
}