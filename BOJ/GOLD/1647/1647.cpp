#include <iostream>
#include <queue>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 100001
#define MAX_M 1000000

using namespace std;

struct Edge {
    int s, e, c;

    bool operator<(const Edge &e) const { return c > e.c; }
} edges[MAX_M];

int N, M;
int parents[MAX_N];
priority_queue<Edge> pq;

void init() {
    fi1(N) { parents[i] = i; }
}

void input() {
    i2(N, M);
    fi0(M) {
        i3(edges[i].s, edges[i].e, edges[i].c);
        pq.push(edges[i]);
    }
}

int find(int a) {
    if (parents[a] == a) return a;
    return parents[a] = find(parents[a]);
}

void unite(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);

    if (rootA == rootB) return;

    parents[rootB] = rootA;
}

void kruskal() {
    int cost = 0;
    int max_cost = 0;
    fi0(N) {
        while (!pq.empty()) {
            Edge cur = pq.top();
            pq.pop();
            if (find(cur.s) != find(cur.e)) {
                unite(cur.s, cur.e);
                cost += cur.c;
                max_cost = max(max_cost, cur.c);
                break;
            }
        }
    }
    cout << cost - max_cost;
}

void run() {
    input();
    init();
    kruskal();
}

int main() {
    fio;
    run();
    return 0;
}