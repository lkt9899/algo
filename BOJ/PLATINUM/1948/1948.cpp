#include <cstring>
#include <iostream>
#include <queue>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 10001

using namespace std;

struct Edge {
    int e, c;
};

int N, M, S, E, ans = 0;
vector<Edge> edges[MAX_N];
vector<Edge> r_edges[MAX_N];
int inDegree[MAX_N];
int timeToNode[MAX_N];
bool visited[MAX_N];

void init() {
    memset(inDegree, 0, sizeof(inDegree));
    memset(timeToNode, 0, sizeof(timeToNode));
    memset(visited, false, sizeof(visited));
}

void input() {
    i2(N, M);
    fi0(M) {
        int s, e, c;
        i3(s, e, c);
        inDegree[e]++;
        edges[s].push_back({e, c});
        r_edges[e].push_back({s, c});
    }
    i2(S, E);
}

void topology_sort() {
    queue<int> q;
    q.push(S);
    while (!q.empty()) {
        int cur = q.front();
        q.pop();
        for (Edge next : edges[cur]) {
            timeToNode[next.e] = max(timeToNode[next.e], timeToNode[cur] + next.c);
            if (--inDegree[next.e] == 0) q.push(next.e);
        }
    }
}

void reverse_bfs() {
    queue<int> q;
    q.push(E);

    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        if (cur == S) break;
        for (Edge prev : r_edges[cur]) {
            int prevCost = timeToNode[cur] - prev.c;
            if (prevCost == timeToNode[prev.e]) {
                ans++;
                if (!visited[prev.e]) {
                    visited[prev.e] = true;
                    q.push(prev.e);
                }
            }
        }
    }
}

void sol() {
    topology_sort();
    reverse_bfs();
    cout << timeToNode[E] << "\n" << ans;
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