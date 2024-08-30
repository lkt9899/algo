#include <iostream>
#include <queue>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 1001

using namespace std;

int N, M;
int in[MAX_N];
vector<int> edges[MAX_N];
vector<int> ans;
queue<int> q;

void input() {
    i2(N, M);

    fi0(M) {
        int cnt, s, e;
        i2(cnt, s);
        fj0(cnt - 1) {
            i1(e);
            edges[s].push_back(e);
            in[e]++;
            s = e;
        }
    }
}

void tp_sort() {
    fi1(N) {
        if (!in[i]) q.push(i);
    }

    fi0(N) {
        if (q.empty()) break;
        int cur = q.front();
        ans.push_back(cur);
        q.pop();
        fj0(edges[cur].size()) {
            int next = edges[cur][j];
            if (--in[next] == 0) q.push(next);
        }
    }

    if (ans.size() != N)
        cout << 0 << "\n";
    else {
        fi0(N) cout << ans[i] << "\n";
    }
}

void run() {
    input();
    tp_sort();
}

int main() {
    fio;
    run();
    return 0;
}