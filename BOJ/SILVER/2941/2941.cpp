#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int ans;
string s;

void init() { ans = 0; }

void input() { cin >> s; }

void sol() {
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == 'c' && (s[i + 1] == '=' || s[i + 1] == '-')) {
            i++;
        } else if (s[i] == 'd') {
            if (s[i + 1] == 'z' && s[i + 2] == '=')
                i += 2;
            else if (s[i + 1] == '-')
                i++;
        } else if (s[i] == 'l' && s[i + 1] == 'j') {
            i++;
        } else if (s[i] == 'n' && s[i + 1] == 'j') {
            i++;
        } else if (s[i] == 's' && s[i + 1] == '=') {
            i++;
        } else if (s[i] == 'z' && s[i + 1] == '=') {
            i++;
        }
        ans++;
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