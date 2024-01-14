#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int L, C;
vector<char> all;
vector<string> ans;

bool aeCheck(char c) { return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'; }

void input() {
    cin >> L >> C;
    char c;
    for (int i = 0; i < C; i++) {
        cin >> c;
        all.push_back(c);
    }
    sort(all.begin(), all.end());
}

void check(string s, int idx, int cflag, int mflag) {
    if (s.length() == L) {
        if (cflag != 0 && mflag >= 2) ans.push_back(s);
        return;
    }

    for (int i = idx; i < C; i++) {
        if (aeCheck(all[i])) {
            check(s + all[i], i + 1, cflag + 1, mflag);
        } else
            check(s + all[i], i + 1, cflag, mflag + 1);
    }
}

void sol() {
    string s = "";
    check(s, 0, 0, 0);

    for (int i = 0; i < (int)ans.size(); i++) {
        cout << ans[i] << "\n";
    }
}

int main() {
    ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);
    input();
    sol();
    return 0;
}