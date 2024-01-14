#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 101;
int W, K, ans;
int dp[MAX];

void init() { memset(dp, 0, sizeof(dp)); }

void run() {
    init();

    cin >> W >> K;

    for (int i = 0; i < W; i++) {
        int w, v;
        cin >> w >> v;

        for (int j = K; j >= w; j--) {
            dp[j] = dp[j] > dp[j - w] + v ? dp[j] : dp[j - w] + v;
        }
    }

    ans = dp[K];
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