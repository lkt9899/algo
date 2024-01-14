#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

string need;
int clap, ans;

void init() {
    clap = 0;
    ans = 0;
}

void input() { cin >> need; }

void sol() {
    for (int i = 0; i < need.length(); i++) {
        if (need[i] == '0') continue;

        int toAdd = 0;
        if (clap < i) {
            toAdd = i - clap;
            ans += toAdd;
        }
        clap += toAdd + (need[i] - '0');
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

    freopen("input.txt", "r", stdin);
    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " " << ans << "\n";
    }
    return 0;
}