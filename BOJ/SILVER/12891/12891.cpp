#include <iostream>
#include <string>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int S, P, ans;
string s;
char chars[4] = {'A', 'C', 'G', 'T'};
int count[26], cnt[26];

void init() {
    ans = 0;
    s += "B";
}

void input() {
    string tmp;
    cin >> S >> P >> tmp;
    s += tmp;
    for (int i = 0; i < 4; i++) {
        cin >> count[chars[i] - 'A'];
    }
}

bool check() {
    for (int i = 0; i < 4; i++) {
        if (count[chars[i] - 'A'] > cnt[chars[i] - 'A']) return 0;
    }

    return 1;
}

void sol() {
    for (int i = 1; i < P; i++) {
        cnt[s[i] - 'A']++;
    }

    int start = 0;
    for (int e = P; e <= S; e++) {
        cnt[s[e] - 'A']++;
        if (check()) ans++;
        cnt[s[++start] - 'A']--;
    }

    cout << ans;
}

void run() {
    init();
    input();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}