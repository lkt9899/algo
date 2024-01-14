import java.io.*;
import java.util.*;

public class Main_1918 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Deque<Character> eq = new ArrayDeque<>();
    static Deque<Character> op = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        input();
        change();
        while(!op.isEmpty())
            sb.append(op.pollLast());
        System.out.println(sb);
    }

    static void change() throws Exception {
        while (!eq.isEmpty()) {
            char next = eq.poll();
            if (isAlpha(next - 'A')) {
                sb.append(next);
            } else if (next == '(') {
                op.add(next);
                change();
            } else if (next == ')') {
                while (!op.isEmpty() && op.peekLast() != '(') {
                    sb.append(op.pollLast());
                }
                op.pollLast();
            } else {
                if (op.isEmpty())
                    op.add(next);
                else {
                    if (op.peekLast() == '(')
                        op.add(next);
                    else if (priority(next) > priority(op.peekLast())) {
                        op.add(next);
                    } else {
                        while (!op.isEmpty() && priority(op.peekLast()) >= priority(next))
                            sb.append(op.pollLast());
                        op.add(next);
                    }
                }
            }
        }
    }

    static boolean isAlpha(int c) {
        return (c >= 0 && c < 26);
    }

    static int priority(char c) {
        switch (c) {
            case '-':
            case '+':
                return 0;
            case '*':
            case '/':
                return 1;
            default:
                return -1;
        }
    }

    static void input() throws Exception {
        char[] tmp = br.readLine().toCharArray();
        for (int i = 0; i < tmp.length; i++)
            eq.add(tmp[i]);
    }
}