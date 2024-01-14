import java.io.*;

public class Main_13294 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final double PI = 3.141592653589793238462643;
    static String nFac;

    public static void main(String[] args) throws Exception {
        run();
    }

    static void run() throws Exception {
        input();
        double ln_Fac, ln_NFac;
        if (nFac.length() > 18)
            ln_NFac = Math.log(Long.parseLong(nFac.substring(0, 10))) + (nFac.length() - 10) * Math.log(10);
        else
            ln_NFac = Math.log(Long.parseLong(nFac));

        for (int n = 1;; n++) {
            ln_Fac = 0.5 * Math.log(2 * PI * n) + n * Math.log(n) - n;
            if (ln_Fac - ln_NFac > -0.5) {
                System.out.println(n);
                break;
            }
        }
    }

    static void input() throws Exception {
        nFac = br.readLine();
        br.close();
    }
}
