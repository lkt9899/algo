#include <iostream>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

typedef pair<int, int> pii;
const int INF = int(1e9);
const int MAX_N = 100001;

// min seg
struct MinSeg {
    // segTree
    int tree[1 << 18];
    // size
    int sz = 1 << 17;

    // constructor
    void construct() {
        for (int i = sz - 1; i > 0; i--) {
            tree[i] = min(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    void update(int i, int val) {
        // leaf node idx
        --i |= sz;
        tree[i] = val;
        // i /= 2
        while (i >>= 1) {
            tree[i] = min(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    // [l, r] : 1 - idx
    int query(int l, int r) {
        --l |= sz;
        --r |= sz;
        int ret = INF;
        while (l <= r) {
            if (l & 1) ret = min(ret, tree[l++]);
            if (~r & 1) ret = min(ret, tree[r--]);
            l >>= 1, r >>= 1;
        }
        return ret;
    }
};

struct MaxSeg {
    // segTree
    int tree[1 << 18];
    // size
    int sz = 1 << 17;

    // constructor
    void construct() {
        for (int i = sz - 1; i > 0; i--) {
            tree[i] = max(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    // i : 1 - idx
    void update(int i, int val) {
        // leaf node idx
        // start = sz
        --i |= sz;
        tree[i] = val;
        // i /= 2
        while (i >>= 1) {
            tree[i] = max(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    // [l, r] : 1 - idx
    int query(int l, int r) {
        --l |= sz;
        --r |= sz;
        int ret = -INF;
        while (l <= r) {
            if (l & 1) ret = max(ret, tree[l++]);
            if (~r & 1) ret = max(ret, tree[r--]);
            l >>= 1, r >>= 1;
        }
        return ret;
    }
};

struct HLD {
    int n, cost[MAX_N];
    int sz[MAX_N], dep[MAX_N], par[MAX_N];
    int top[MAX_N], in[MAX_N], out[MAX_N];
    vector<pii> inp[MAX_N];
    vector<int> adj[MAX_N];

    bool visited[MAX_N];
    void dfs(int cur = 1) {
        visited[cur] = true;
        for (auto [nextCost, next] : inp[cur]) {
            if (visited[next]) continue;
            adj[cur].push_back(next);
            cost[next] = nextCost;
            dfs(next);
        }
    }

    void dfs1(int cur = 1) {
        sz[cur] = 1;
        for (auto &next : adj[cur]) {
            dep[next] = dep[cur] + 1;
            par[next] = cur;
            dfs1(next);
            sz[cur] += sz[next];
            if (sz[next] > sz[adj[cur][0]]) swap(next, adj[cur][0]);
        }
    }

    int temp;
    void dfs2(int cur = 1) {
        in[cur] = ++temp;
        for (auto next : adj[cur]) {
            top[next] = (next == adj[cur][0] ? top[cur] : next);
            dfs2(next);
        }
        out[cur] = temp;
    }

    MinSeg minSeg;
    MaxSeg maxSeg;
    void precalc() {
        cin >> n;
        for (int i = 1 << 17; i < 1 << 18; i++) {
            minSeg.tree[i] = INF;
            maxSeg.tree[i] = -INF;
        }

        for (int i = 0; i < n - 1; i++) {
            int s, e, c;
            cin >> s >> e >> c;
            inp[s].push_back({c, e});
            inp[e].push_back({c, s});
        }
        top[1] = 1;
        dfs();
        dfs1();
        dfs2();
        for (int i = 1; i <= n; i++) {
            minSeg.tree[minSeg.sz - 1 + in[i]] = cost[i];
            maxSeg.tree[maxSeg.sz - 1 + in[i]] = cost[i];
        }

        minSeg.construct();
        maxSeg.construct();
    }
} HLD;

pii query(int a, int b) {
    int mn = INF, mx = -INF;
    while (HLD.top[a] != HLD.top[b]) {
        if (HLD.dep[HLD.top[a]] < HLD.dep[HLD.top[b]]) swap(a, b);
        int st = HLD.top[a];
        mn = min(mn, HLD.minSeg.query(HLD.in[st], HLD.in[a]));
        mx = max(mx, HLD.maxSeg.query(HLD.in[st], HLD.in[a]));
        a = HLD.par[st];
    }

    if (a == b) return {mn, mx};
    if (HLD.dep[a] > HLD.dep[b]) swap(a, b);
    int next = -1;
    for (auto i : HLD.adj[a]) {
        if (HLD.top[a] == HLD.top[i]) next = i;
    }
    mn = min(mn, HLD.minSeg.query(HLD.in[next], HLD.in[b]));
    mx = max(mx, HLD.maxSeg.query(HLD.in[next], HLD.in[b]));
    return {mn, mx};
}

int main() {
    fio;
    HLD.precalc();
    int q;
    cin >> q;
    while (q--) {
        int D, E;
        cin >> D >> E;
        pii ans = query(D, E);
        cout << ans.first << " " << ans.second << "\n";
    }
    return 0;
}