#include <algorithm>
#include <cstring>
#include <iostream>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define INF 2147483647
#define MAX_N 40001
#define MAX_NODE 1 << 18

using namespace std;

struct Edge {
    int e, c;
};

int N, M, A, B, root, cnt;
vector<vector<Edge> > edges(MAX_N);
vector<int> searchSeq;
int nodeIdx[MAX_N];
int nodeValue[MAX_N];
int firstVisit[MAX_N];
int dist[MAX_N];
int seg[MAX_NODE];

void init() {
    root = 1;
    cnt = 0;
}

void input() {
    i1(N);
    fi0(N - 1) {
        int s, e, c;
        i3(s, e, c);
        edges[s].push_back({e, c});
        edges[e].push_back({s, c});
        if (edges[s].size() > edges[root].size()) root = s;
        if (edges[e].size() > edges[root].size()) root = e;
    }
}

void dfs(int node, int parent, int cost) {
    // node index
    nodeIdx[node] = cnt;
    // node value
    nodeValue[cnt++] = node;

    // distance from root
    dist[node] = cost;
    // first visit along dfs
    firstVisit[node] = searchSeq.size();
    // save path include its parent
    searchSeq.push_back(nodeIdx[node]);
    for (Edge next : edges[node]) {
        if (next.e == parent) continue;
        dfs(next.e, node, cost + next.c);
        searchSeq.push_back(nodeIdx[node]);
    }
}

// seg init
int initSeg(int node, int start, int end) {
    if (start == end) return seg[node] = searchSeq[start];

    int mid = (start + end) >> 1;
    int leftMin = initSeg(2 * node, start, mid);
    int rightMin = initSeg(2 * node + 1, mid + 1, end);
    return seg[node] = min(leftMin, rightMin);
}

// seg query
int lca(int node, int start, int end, int left, int right) {
    if (right < start || end < left) return INF;
    if (left <= start && end <= right) return seg[node];

    int mid = (start + end) >> 1;
    return min(lca(2 * node, start, mid, left, right), lca(2 * node + 1, mid + 1, end, left, right));
}

int getDist(int left, int right) {
    if (left == right) return 0;
    int lu = firstVisit[left];
    int lv = firstVisit[right];
    if (lu > lv) swap(lu, lv);

    int p = nodeValue[lca(1, 0, searchSeq.size() - 1, lu, lv)];
    return dist[left] + dist[right] - 2 * dist[p];
}

void sol() {
    // init
    dfs(root, root, 0);
    initSeg(1, 0, searchSeq.size() - 1);

    // run
    i1(M);
    fi0(M) {
        i2(A, B);
        cout << getDist(A, B) << "\n";
    }
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