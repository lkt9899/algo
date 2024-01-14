#include <iostream>
#include <stack>
#include <string>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

int ans, len;
string parenthesis;
stack<char> s;

void init() { ans = 1; }

void input() {
    cin >> len;
    cin >> parenthesis;
}

bool check(char c) {
    if (c == ')') {
        return s.top() == '(';
    } else if (c == '}') {
        return s.top() == '{';
    } else if (c == ']') {
        return s.top() == '[';
    } else {
        return s.top() == '<';
    }
}

void sol() {
    for (int i = 0; i < len; i++) {
        char tmp = parenthesis[i];
        switch (tmp) {
            case '{':
            case '(':
            case '[':
            case '<':
                s.push(tmp);
                break;
            case '}':
            case ')':
            case ']':
            case '>':
                if (check(tmp)) {
                    s.pop();
                    break;
                } else {
                    ans = 0;
                    return;
                }
            default:
                break;
        }
    }
}

void run() {
    init();
    input();
    sol();
}
int main(int argc, char** argv) {
    fio;
    int test_case, T = 10;

    freopen("input.txt", "r", stdin);
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " " << ans << "\n";
    }
    return 0;
}