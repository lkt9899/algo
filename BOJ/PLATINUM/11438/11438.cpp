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

#define MAX_N 100001
#define MAX_TABLE_SIZE 1 << 18 + 1

using namespace std;

int N, M, nextIdx;
int seg[MAX_TABLE_SIZE];
int firstVisitIdx[MAX_N];
int nodeIdx[MAX_N];
int nodeVal[MAX_N];
vector<int> searchSeq;
vector<int> tree[MAX_N];

void input() {
    i1(N);
    fi0(N - 1) {
        int s, e;
        i2(s, e);
        tree[s].push_back(e);
        tree[e].push_back(s);
    }
    i1(M);
}

void search(int node, int parent) {
    nodeIdx[node] = nextIdx;
    nodeVal[nextIdx++] = node;

    firstVisitIdx[node] = searchSeq.size();
    searchSeq.push_back(nodeIdx[node]);
    for (int next : tree[node]) {
        if (next == parent) continue;
        search(next, node);
        searchSeq.push_back(nodeIdx[node]);
    }
}

int initSeg(int node, int start, int end) {
    if (start == end) return seg[node] = searchSeq[start];

    int mid = (start + end) >> 1;
    return seg[node] = min(initSeg(node * 2, start, mid), initSeg(node * 2 + 1, mid + 1, end));
}

int query(int node, int start, int end, int left, int right) {
    if (right < start || end < left) return INT32_MAX;
    if (left <= start && end <= right) return seg[node];

    int mid = (start + end) >> 1;
    return min(query(node * 2, start, mid, left, right), query(node * 2 + 1, mid + 1, end, left, right));
}

int getLCA(int left, int right) {
    int leftIdx = firstVisitIdx[left];
    int rightIdx = firstVisitIdx[right];
    if (leftIdx > rightIdx) swap(leftIdx, rightIdx);

    int lcaIdx = query(1, 0, searchSeq.size() - 1, leftIdx, rightIdx);
    return nodeVal[lcaIdx];
}

void sol() {
    nextIdx = 0;
    search(1, 1);
    initSeg(1, 0, searchSeq.size() - 1);
    fi0(M) {
        int l, r;
        i2(l, r);
        cout << getLCA(l, r) << "\n";
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