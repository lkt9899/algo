#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define pii pair<int, int>
#define MAX_N 100001
#define INF 987654321

using namespace std;

struct Info {
    int s, e, c;
    bool operator<(const Info& a) const { return c > a.c; }
};

int N, M, S, E, ans, mk = 0;
vector<Info> v;
vector<pii> nodes[MAX_N];
int parents[MAX_N];

void input() {
    i2(N, M);
    i2(S, E);
    fi0(M) {
        int s, e, c;
        i3(s, e, c);
        v.push_back({s, e, c});
    }
}

int find(int x) {
    if (parents[x] == x) return x;
    return find(parents[x]);
}

void _union(int a, int b) {
    a = find(a);
    b = find(b);
    if (a < b)
        parents[b] = a;
    else
        parents[a] = b;
}

bool isCycle(int a, int b) {
    a = find(a);
    b = find(b);
    return a == b ? true : false;
}

void kruskal() {
    sort(v.begin(), v.end());
    fi1(N) parents[i] = i;
    fi0(v.size()) {
        int s = v[i].s;
        int e = v[i].e;
        int c = v[i].c;

        if (!isCycle(s, e)) {
            _union(s, e);
            nodes[s].push_back({e, c});
            nodes[e].push_back({s, c});
        }
    }
}

int bfs() {
    int visited[N + 1];
    fill(visited, visited + N + 1, INF);
    queue<int> q;
    q.push(S);
    while (!q.empty()) {
        int cur = q.front();
        q.pop();
        fi0(nodes[cur].size()) {
            int next = nodes[cur][i].first;
            int cost = nodes[cur][i].second;

            if (visited[next] != INF) continue;

            visited[next] = min(cost, visited[cur]);
            q.push(next);
        }
    }

    return visited[E];
}

void sol() {
    kruskal();
    ans = bfs();
    cout << (ans == INF ? 0 : ans);
}

void run() {
    input();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}