#include <iostream>
#include <string>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

int ans;
string iron;
vector<int> v;

void init() {
    ans = 0;
    v.clear();
    v.push_back(0);
}

void input() { cin >> iron; }

void sol() {
    int cnt = 0;
    for (int i = 0; i < iron.length(); i++) {
        if (iron[i] == '(') {
            if (iron[i + 1] == ')') {
                v.push_back(-2);
                v.push_back(cnt);
                i++;
            } else {
                cnt++;
                v.push_back(cnt);
            }
        } else {
            v.push_back(cnt--);
            v.push_back(cnt);
        }
    }

    for (int i = 1; i < v.size(); i++) {
        if (v[i] == -2) {
            ans += v[i - 1];
        } else if (v[i] == v[i - 1] - 1) {
            ans++;
        }
    }
}
//  () ((( () () ) ( ())  ()  ) ) ( ())
// 0-1 123 -1 -1 323 -132 -1  21101 -110
// (((()(()()))(())()))(()())
// 0123-134-14-143323-1332-1221101-11-110
void run() {
    init();
    input();
    sol();
}

int main(int argc, char** argv) {
    fio;
    int T, test_case;

    freopen("input.txt", "r", stdin);
    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " " << ans << "\n";
    }
    return 0;
}