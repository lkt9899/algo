#include <iostream>
#include <queue>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX_N 200001

using namespace std;

struct Edge {
    // end node
    int e;
    // cost
    long long c;
    // path index, path start node
    int idx, pStart;
};

struct comp {
    bool operator()(Edge e1, Edge e2) { return e1.c > e2.c; }
};

const long long INF = 9223372036854775807ll;

int N, M, K;
// [0] : cost when the path not include K's info
// [1] : cost when the path include K's info
long long dist[2][MAX_N];
vector<Edge> edges[MAX_N];
vector<int> infos[MAX_N];
priority_queue<Edge, vector<Edge>, comp> pq;

void input() {
    cin >> N >> M >> K;
    // input path
    for (int i = 0; i < M; i++) {
        int s, e, c;
        cin >> s >> e >> c;
        edges[s].push_back({e, c, -1, 0});
    }

    // input forbid path info
    for (int i = 0; i < K; i++) {
        int cnt, start;
        cin >> cnt >> start;
        for (int j = 1; j < cnt; j++) {
            int next;
            cin >> next;
            infos[start].push_back(next);
        }
    }

    // init dist array
    for (int i = 1; i <= N; i++) {
        dist[0][i] = INF;
        dist[1][i] = INF;
    }
}

void sol() {
    // start at node 1
    Edge start = {1, 0, -1, 0};

    // if node 1 is start of forbid path
    if (!infos[1].empty()) {
        start.idx = 0;
        start.pStart = 1;
        dist[1][1] = 0;
    }

    // dijkstra
    pq.push(start);
    dist[0][1] = 0;
    while (!pq.empty()) {
        Edge cur = pq.top();
        pq.pop();

        // is current node in forbid path?
        int isInc = cur.pStart ? 1 : 0;
        if (dist[isInc][cur.e] < cur.c) continue;

        for (Edge next : edges[cur.e]) {
            Edge newNext = next;
            long long nextDist = dist[isInc][cur.e] + next.c;
            // is next node start of forbid path?
            int isInc2 = infos[next.e].empty() ? 0 : 1;
            // is min cost?
            if (nextDist < dist[isInc2][next.e]) {
                // is next node start of forbid path?
                if (isInc2) {
                    newNext.idx = 0;
                    newNext.pStart = next.e;
                } else {
                    // is current node in path?
                    if (isInc) {
                        // is next node linked at current node?
                        if (infos[cur.pStart][cur.idx] == next.e) {
                            // is last node of forbid path?
                            if (cur.idx == infos[cur.pStart].size() - 1) continue;
                            newNext.idx = cur.idx + 1;
                            newNext.pStart = cur.pStart;
                        }
                    }
                }

                // update dist array in min dist by is in forbid path or not
                dist[(newNext.pStart ? 1 : 0)][next.e] = nextDist;
                newNext.c = nextDist;
                pq.push(newNext);
            }
        }
    }

    // print
    for (int i = 1; i <= N; i++) {
        long long d = min(dist[0][i], dist[1][i]);
        if (d == INF)
            cout << -1 << " ";
        else
            cout << d << " ";
    }
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