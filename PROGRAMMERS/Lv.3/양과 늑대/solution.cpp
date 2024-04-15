#include <queue>
#include <vector>

#define MAX_N 18
#define WOLF 1
#define SHEEP 0

using namespace std;

int N, ans;
int infos[MAX_N];
vector<int> mEdges[MAX_N];

void dfs(int sheep, int wolf, int visit) {
    if (sheep <= wolf) return;

    ans = max(ans, sheep);
    for (int i = 0; i < N; i++) {
        for (int child : mEdges[i]) {
            if ((visit & (1 << i)) && !(visit & (1 << child))) {
                if (infos[child] == SHEEP)
                    dfs(sheep + 1, wolf, visit | 1 << child);
                else
                    dfs(sheep, wolf + 1, visit | 1 << child);
            }
        }
    }
}

int solution(vector<int> info, vector<vector<int>> edges) {
    N = 0;
    for (int n : info) infos[N++] = n;
    for (vector<int> v : edges) mEdges[v[0]].push_back(v[1]);
    dfs(1, 0, 1);
    return ans;
}

#include <iostream>
int main() {
    freopen("input.txt", "r", stdin);
    int T, M;
    cin >> T;
    while (T--) {
        cin >> M;
        vector<int> info;
        for (int i = 0; i < M; i++) {
            int n;
            cin >> n;
            info.push_back(n);
        }
        vector<vector<int>> edges;
        for (int i = 1; i < M; i++) {
            int s, e;
            cin >> s >> e;
            edges.push_back({s, e});
        }
        cout << solution(info, edges) << "\n";
        for (int i = 0; i < N; i++) mEdges[i].clear();
    }
}