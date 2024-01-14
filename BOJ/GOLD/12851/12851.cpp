#include <iostream>
#include <queue>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX_N 100001

#define f(s, e) for (int i = s; i <= e; i++)
#define i2(a, b) cin >> a >> b

using namespace std;

int N, K;
int dist[MAX_N];

void init() { f(0, 100000) dist[i] = MAX_N; }

void input() { i2(N, K); }

void sol() {
    if (N >= K) {
        cout << N - K << "\n1\n";
        return;
    }

    int ans = 0;

    queue<int> q;
    q.push(N);
    dist[N] = 0;
    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        if (dist[cur] + 1 > dist[K]) continue;

        int next[3] = {cur - 1, cur * 2, cur + 1};
        f(0, 2) {
            if (next[i] < 0 || next[i] >= MAX_N) continue;

            if (next[i] == K) {
                dist[K] = dist[cur] + 1;
                ans++;
            } else if (dist[next[i]] >= dist[cur] + 1) {
                q.push(next[i]);
                dist[next[i]] = dist[cur] + 1;
            }
        }
    }

    cout << dist[K] << "\n" << ans << "\n";
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