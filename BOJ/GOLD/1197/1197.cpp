#include <algorithm>
#include <iostream>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define E_MAX 100000
#define V_MAX 10001

using namespace std;

struct Edge {
    int from, to, weight;
} edges[E_MAX];

int V, E, ans;
int parents[V_MAX];

bool compare(Edge e1, Edge e2) { return e1.weight < e2.weight; }

void init() { ans = 0; }

void input() {
    cin >> V >> E;
    int from, to, weight;
    for (int i = 0; i < E; i++) {
        cin >> from >> to >> weight;
        edges[i] = {from, to, weight};
    }

    sort(edges, edges + E, compare);
}

void make() {
    for (int i = 1; i <= V; i++) {
        parents[i] = i;
    }
}

int find(int n) {
    if (parents[n] == n) return n;
    return parents[n] = find(parents[n]);
}

bool unionNode(int p, int c) {
    int rootP = find(p);
    int rootC = find(c);
    if (rootP == rootC) return false;

    parents[rootC] = rootP;
    return true;
}

void sol() {
    int cnt = 0;
    for (int i = 0; i < E; i++) {
        if (unionNode(edges[i].from, edges[i].to)) {
            ans += edges[i].weight;
            if (++cnt == V - 1) break;
        }
    }
    cout << ans;
}

void run() {
    init();
    input();
    make();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}