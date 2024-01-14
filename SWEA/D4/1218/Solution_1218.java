import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Solution_1218 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 정답, 길이
    static int ans, len;
    // 괄호 넣을 스택
    static Stack<Character> s = new Stack<>();
    // 입력 문자열
    static String parenthesis;
    // 체크를 위한 문자열
    static String openParenthesis = "{([<";
    static String closeParenthesis = "})]>";

    public static void main(String[] args) throws Exception {
        for (int t = 1; t <= 10; t++) {
            // 입력
            input();
            // 처리
            run();
            // 출력
            print(t);
        }
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * 처리 함수
     */
    static void run() {
        ans = 1;
        for (int i = 0; i < len; i++) {
            char tmp = parenthesis.charAt(i);
            if (openParenthesis.indexOf(tmp) >= 0) {
                s.add(tmp);
            } else {
                if (check(tmp)) {
                    s.pop();
                } else {
                    ans = 0;
                    break;
                }
            }
        }
    }

    static boolean check(char c) {
        return closeParenthesis.indexOf(c) == openParenthesis.indexOf(s.peek());
    }

    static void input() throws Exception {
        len = Integer.parseInt(br.readLine());
        parenthesis = br.readLine();
    }

    static void print(int t) throws Exception {
        bw.write("#" + t + " " + ans);
        bw.newLine();
    }
}