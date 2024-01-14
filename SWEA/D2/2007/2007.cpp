#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int ans;
string s;

void init() { ans = 0; }

void input() { cin >> s; }

void sol() {
    string tmp = "";
    tmp += s[0];
    for (int i = 1; i < 10; i++) {
        if (s[i] == tmp[0]) {
            int tidx = 0;
            int sidx = i;
            while (tidx != tmp.size()) {
                if (tmp[tidx] != s[sidx]) break;
                tidx++;
                sidx++;
            }

            if (tidx == tmp.size()) {
                ans = tmp.size();
                break;
            }
        }
        tmp += s[i];
    }
}

void run() {
    init();
    input();
    sol();
}
int main(int argc, char** argv) {
    fio;
    int T, test_case;

    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " " << ans << "\n";
    }
    return 0;
}