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

#define MAX_N 300000

using namespace std;

struct Jewel {
    int m, v;

    bool operator<(const Jewel& j) const { return m < j.m; }
} jewels[MAX_N];

int N, K;
int bags[MAX_N];
priority_queue<int> pq;

void input() {
    i2(N, K);
    fi0(N) { i2(jewels[i].m, jewels[i].v); }
    fi0(K) { i1(bags[i]); }
}

void sol() {
    sort(jewels, jewels + N);
    sort(bags, bags + K);

    int ji = 0;
    int bi = 0;
    long long ans = 0;
    while (ji < N && bi < K) {
        if (jewels[ji].m <= bags[bi]) {
            pq.push(jewels[ji].v);
            ji++;
        } else {
            if (!pq.empty()) {
                ans += pq.top();
                pq.pop();
            }
            bi++;
        }
    }

    while (bi < K && !pq.empty()) {
        ans += pq.top();
        pq.pop();
        bi++;
    }

    cout << ans << "\n";
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