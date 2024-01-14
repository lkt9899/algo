#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 1001;
int ans;
string s1, s2;
int max_i, max_j;
int dp[MAX][MAX];

void init() { memset(dp, 0, sizeof(dp)); }

void input() {
    cin >> s1 >> s2;
    max_i = s1.length();
    max_j = s2.length();
}

void sol() {
    for (int i = 1; i <= max_i; i++) {
        for (int j = 1; j <= max_j; j++) {
            if(s1[i - 1] == s2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
            }
        }
    }

    ans = dp[max_i][max_j];
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