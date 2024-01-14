#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX_N 100001

using namespace std;

struct Node {
    int idx, p;
};

bool comp(Node a, Node b) { return a.p < b.p; }

struct Edge {
    int c, s, e;
};

struct compare {
    bool operator()(const Edge& a, const Edge& b) { return a.c > b.c; }
};

int N;
int parents[MAX_N];
vector<Node> xEdges, yEdges, zEdges;
priority_queue<Edge, vector<Edge>, compare> pq;

void getEdges() {
    for (int i = 0; i < N - 1; i++) {
        pq.push({yEdges[i + 1].p - yEdges[i].p, yEdges[i].idx, yEdges[i + 1].idx});
        pq.push({zEdges[i + 1].p - zEdges[i].p, zEdges[i].idx, zEdges[i + 1].idx});
        pq.push({xEdges[i + 1].p - xEdges[i].p, xEdges[i].idx, xEdges[i + 1].idx});
    }
}

void init() {
    for (int i = 0; i < N; i++) {
        parents[i] = i;
    }
}

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        xEdges.push_back({i, x});
        yEdges.push_back({i, y});
        zEdges.push_back({i, z});
    }

    sort(xEdges.begin(), xEdges.end(), comp);
    sort(yEdges.begin(), yEdges.end(), comp);
    sort(zEdges.begin(), zEdges.end(), comp);
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

void sol() {
    int cost = 0;
    for (int i = 1; i < N; i++) {
        while (!pq.empty()) {
            Edge e = pq.top();
            pq.pop();

            if (find(e.s) != find(e.e)) {
                unite(e.s, e.e);
                cost += e.c;
                break;
            }
        }
    }

    cout << cost << "\n";
}

void run() {
    input();
    init();
    getEdges();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}