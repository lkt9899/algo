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

#define MAX_N 501

using namespace std;

int N, M, num;
bool isCycle;
vector<int> edges[MAX_N];
int visited[MAX_N];

void init() {
    num = 0;
    memset(visited, 0, sizeof(visited));
    fi1(N) edges[i].clear();
}

void input() {
    int s, e;
    fi0(M) {
        i2(s, e);
        edges[s].push_back(e);
        edges[e].push_back(s);
    }
}

void dfs(int prev, int node) {
    if(visited[node]) {
        isCycle = true;
        return;
    }

    visited[node] = 1;
    for (int next : edges[node]) {
        if (next != prev) {
            dfs(node, next);
        }
    }
}

void sol() {
    int T = 1;
    while (1) {
        i2(N, M);
        if (N == 0 && M == 0) break;

        init();
        input();
        fi1(N) {
            if (!visited[i]) {
                num++;
                isCycle = false;
                dfs(0, i);

                if (isCycle) num--;
            }
        }

        cout << "Case " << T++ << ": ";
        if (num == 0)
            cout << "No trees.\n";
        else if (num == 1)
            cout << "There is one tree.\n";
        else
            cout << "A forest of " << num << " trees.\n";
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