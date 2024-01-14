#include <iostream>
#include <queue>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX_N 1 << 10
#define INF 1 << 17

using namespace std;

struct Edge {
    int e, c;
    bool operator<(const Edge& e2) const { return c > e2.c; }
};

int N, M, S, E;
int dist[MAX_N];
vector<Edge> edges[MAX_N];
priority_queue<Edge, vector<Edge>> pq;

void input() {
    int s, e, c;

    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        cin >> s >> e >> c;
        edges[s].push_back({e, c});
    }

    cin >> S >> E;
    for (int i = 1; i <= N; i++) {
        dist[i] = INF;
    }
}

void dijkstra() {
    pq.push({S, 0});
    dist[S] = 0;

    while (!pq.empty()) {
        Edge cur = pq.top();
        pq.pop();

        if (dist[cur.e] < cur.c) continue;

        for (Edge next : edges[cur.e]) {
            int nextDist = dist[cur.e] + next.c;

            if (nextDist < dist[next.e]) {
                dist[next.e] = nextDist;
                pq.push({next.e, nextDist});
            }
        }
    }

    cout << dist[E] << "\n";
}

int main() {
    fio;
    input();
    dijkstra();
    return 0;
}