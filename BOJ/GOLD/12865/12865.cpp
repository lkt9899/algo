#include <ios>

int main() {
    int N, K, W, V, j;
    scanf("%d %d", &N, &K);
    int dp[K + 1] = {};
    while (N--) {
        scanf("%d %d", &W, &V);
        for (j = K - W; j >= 0; j--) dp[j + W] = dp[j + W] > dp[j] + V ? dp[j + W] : dp[j] + V;
    }
    printf("%d", dp[K]);
}