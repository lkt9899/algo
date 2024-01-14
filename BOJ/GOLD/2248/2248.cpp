#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

const int MAX = 32;

string ans = "";
int N, L;
long long I, dp[MAX][MAX];

void init() { memset(dp, -1, sizeof(dp)); }

void input() { cin >> N >> L >> I; }

long long getBinCnt(int n, int l) {
    if (dp[n][l] != -1) return dp[n][l];
    if (n == 0 || l == 0) return dp[n][l] = 1;

    dp[n][l] = getBinCnt(n - 1, l);
    if (l > 0) dp[n][l] += getBinCnt(n - 1, l - 1);

    cout << "dp[" << n << "][" << l << "] : " << dp[n][l] << "\n";
    return dp[n][l];
}

void find(int n, int l, long long i) {
    if (n == 0) return;

    if (l == 0) {
        for (int j = 0; j < n; j++) {
            ans += "0";
        }
        return;
    }

    long long idx = getBinCnt(n - 1, l);
    if (idx >= i) {
        ans += "0";
        find(n - 1, l, i);
    } else {
        ans += "1";
        find(n - 1, l - 1, i - idx);
    }
}

void sol() {
    find(N, L, I);
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