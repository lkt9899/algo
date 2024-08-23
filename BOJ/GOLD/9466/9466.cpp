#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 100001

using namespace std;

int T, N, cnt;
int students[MAX_N];
int visited[MAX_N];
int isCycle[MAX_N];

void init() {
    cnt = 0;
    memset(visited, 0, sizeof(visited));
    memset(isCycle, 0, sizeof(isCycle));
}

void dfs(int cur) {
    visited[cur] = 1;
    int next = students[cur];

    // not visited
    if (!visited[next])
        dfs(next);
    // visited but not in cycle(it means next == start of cycle or next == cur)
    else if (!isCycle[next]) {
        for (int i = next; i != cur; i = students[i]) cnt++;
        cnt++;
    }
    isCycle[cur] = 1;
}

void run() {
    i1(T);
    fi0(T) {
        // input
        i1(N);
        fj1(N) i1(students[j]);

        // init status
        init();

        // dfs
        fj1(N) {
            if (!visited[j]) dfs(j);
        }

        cout << N - cnt << "\n";
    }
}

int main() {
    fio;
    run();
    return 0;
}