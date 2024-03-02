#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 100001
#define MAX_TABLE 1 << 18 + 1

using namespace std;

struct Info {
    int idx, val;
    bool operator<(const Info& e) const { return val < e.val; }
};

int N;
int val[MAX_N];
Info seg[MAX_TABLE];

// seg init
Info init(int node, int start, int end) {
    if (start == end) return seg[node] = {start, val[start]};

    int mid = (start + end) >> 1;
    return seg[node] = min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
}

// get min in range
Info query(int node, int start, int end, int left, int right) {
    // out of range => return INF
    if (right < start || end < left) return {INT32_MAX, INT32_MAX};
    // return subtree's root
    if (left <= start && end <= right) return seg[node];

    int mid = (start + end) >> 1;
    return min(query(node * 2, start, mid, left, right), query(node * 2 + 1, mid + 1, end, left, right));
}

int getArea(int left, int right) {
    // get minHeight in range(left, right)
    Info minNode = query(1, 0, N - 1, left, right);
    // area = height(right - left + 1) * width
    int area = (right - left + 1) * minNode.val;

    // get area in range(left, minHeightIdx - 1)
    if (left < minNode.idx) area = max(area, getArea(left, minNode.idx - 1));
    // get area in range(minHeightIdx + 1, right)
    if (minNode.idx < right) area = max(area, getArea(minNode.idx + 1, right));

    return area;
}

void input() {
    i1(N);
    fi0(N) i1(val[i]);
}

void sol() {
    init(1, 0, N - 1);
    cout << getArea(0, N - 1);
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