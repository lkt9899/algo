#include <algorithm>
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

#define MAX_N 100000

using namespace std;

struct Line {
    int s, e;
    bool operator<(const Line& line) const { return s < line.s; }
} lines[MAX_N];

int N, D;
priority_queue<int, vector<int>, greater<int>> pq;

bool comp(Line l1, Line l2) {
    if (l1.e == l2.e) return l1.s < l2.s;
    return l1.e < l2.e;
}

void input() {
    i1(N);
    fi0(N) {
        int n1, n2;
        i2(n1, n2);
        if (n1 < n2) {
            lines[i].s = n1;
            lines[i].e = n2;
        } else {
            lines[i].s = n2;
            lines[i].e = n1;
        }
    }
    i1(D);
    sort(lines, lines + N, comp);
}

void sol() {
    int ans = 0;
    fi0(N) {
        int start = lines[i].e - D;
        int end = lines[i].e;
        if(lines[i].s >= start)
            pq.push(lines[i].s);

        while (!pq.empty() && pq.top() < start)
            pq.pop();

        ans = ans > pq.size() ? ans : pq.size();
    }
    cout << ans;
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