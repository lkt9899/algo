#include <algorithm>
#include <iostream>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define fi(a) for (int i = 0; i < a; i++)

#define MAX_N 500001

using namespace std;

struct Edge {
    int s, e;

    bool operator<(const Edge& e2) const { return s < e2.s; }
};

int N;
int I[MAX_N];
int L[MAX_N];
vector<Edge> edges;
vector<int> ans;

void input() {
    i1(N);
    fi(N) {
        int s, e;
        i2(s, e);

        edges.push_back({s, e});
    }
    sort(edges.begin(), edges.end());
}

void sol() {
    int idx = 0;
    fi(N) {
        int num = edges[i].e;
        if (idx == 0) {
            L[idx++] = num;
            I[i] = 0;
        } else {
            if (L[idx - 1] < num) {
                I[i] = idx;
                L[idx++] = num;
            } else {
                I[i] = lower_bound(L, L + idx, num) - L;
                L[lower_bound(L, L + idx, num) - L] = num;
            }
        }
    }

    cout << edges.size() - idx << "\n";
    for (int i = N - 1; i >= 0; i--) {
        if (idx == I[i] + 1) {
            idx--;
            continue;
        }
        ans.push_back(edges[i].s);
    }

    for (int i = ans.size() - 1; i >= 0; i--) {
        cout << ans[i] << "\n";
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