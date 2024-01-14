#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

constexpr int SK = 0;
constexpr int CY = 1;
constexpr int MAX = 1001;
int N;
int dp[MAX];

void input() { cin >> N; }

void sol() {
    dp[1] = CY;

    for (int i = 2; i <= N; i++) {
        if (i - 3 > 0)
            dp[i] = (dp[i - 3] ^ 1);
        else if (i - 1 > 0)
            dp[i] = (dp[i - 1] ^ 1);
    }

    cout << (dp[N] == SK ? "SK\n" : "CY\n");
}

int main() {
    fio;

    input();
    sol();
    return 0;
}