#include <cmath>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define N_MAX 100001
#define D_MAX 18

using namespace std;

int N;
long ans;
vector<int> adjList[N_MAX];
int parents[N_MAX][D_MAX];
int depth[N_MAX];
int maxDepth = (int)floor(log2(N_MAX));

void init() {
    ans = 0;
    memset(parents, 0, sizeof(parents));
    for (int i = 0; i < N_MAX; i++) {
        adjList[i].clear();
    }
}

void input() {
    cin >> N;

    int p;
    for (int i = 2; i <= N; i++) {
        cin >> p;
        adjList[p].push_back(i);
    }
}

void set_tree(int node, int p, int d) {
    depth[node] = d;
    parents[node][0] = p;

    for (int i = 1; i <= maxDepth; i++) {
        parents[node][i] = parents[parents[node][i - 1]][i - 1];
    }
}

void make_tree() {
    queue<int> q;
    q.push(1);
    set_tree(1, 0, 0);
    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        for (int next : adjList[cur]) {
            int c = next;
            q.push(c);
            set_tree(c, cur, depth[cur] + 1);
        }
    }
}

int lca(int s, int e) {
    if (s == 1 || e == 1) return depth[s] + depth[e];

    int t = s, comp = e;
    if (depth[s] < depth[e]) {
        int tmp = t;
        t = comp;
        comp = tmp;
    }

    if (depth[t] != depth[comp]) {
        for (int i = maxDepth; i >= 0; i--) {
            if (depth[parents[t][i]] >= depth[comp]) {
                t = parents[t][i];
            }
        }
    }

    int ret = t;
    if (t != comp) {
        for (int i = maxDepth; i >= 0; i--) {
            if (parents[t][i] != parents[comp][i]) {
                t = parents[t][i];
                comp = parents[comp][i];
            }
            ret = parents[t][i];
        }
    }

    return depth[s] + depth[e] - 2 * depth[ret];
}

void sol() {
    queue<int> q;
    q.push(1);
    while (!q.empty()) {
        int len = q.size();
        while (len--) {
            int cur = q.front();
            q.pop();

            for (int next : adjList[cur]) {
                q.push(next);
            }

            if (!q.empty()) {
                ans += lca(cur, q.front());
            }
        }
    }
}

void run() {
    init();
    input();
    make_tree();
    sol();
}

int main(int argc, char** argv) {
    fio;
    int T, test_case;

    freopen("input.txt", "r", stdin);
    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " " << ans << "\n";
    }
    return 0;
}