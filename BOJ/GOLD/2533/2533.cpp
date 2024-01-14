#include <cstring>
#include <iostream>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX 1000001
#define ADAPTER 1
#define NORMAL 0

using namespace std;

int N;
vector<int> Edges[MAX];
int dp[MAX][2];
int visit[MAX];

void init() {
    memset(visit, 0, sizeof(visit));
    memset(dp, 0, sizeof(dp));
}

void input() {
    cin >> N;
    for (int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        Edges[u].push_back(v);
        Edges[v].push_back(u);
    }
}

void find(int node) {
    visit[node] = 1;
    dp[node][ADAPTER] = 1;
    for (int next : Edges[node]) {
        if (!visit[next]) {
            find(next);
            dp[node][NORMAL] += dp[next][ADAPTER];
            dp[node][ADAPTER] += dp[next][ADAPTER] < dp[next][NORMAL] ? dp[next][ADAPTER] : dp[next][NORMAL];
        }
    }
}

void sol() {
    find(1);
    for (int i = 1; i <= N; i++) {
        cout << "dp[" << i << "][NORMAL] = " << dp[i][NORMAL] << "\tdp[" << i << "][ADAPTER] = " << dp[i][ADAPTER] << "\n";
    }
    cout << (dp[1][0] < dp[1][1] ? dp[1][0] : dp[1][1]);
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