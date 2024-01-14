#include <iostream>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

struct Shortcut {
    int s, c;
};

const int MAX = 10001;
int N, D;
int dist[MAX];
vector<Shortcut> sc[MAX];

void input() {
    cin >> N >> D;
    for (int i = 0; i < N; i++) {
        int s, e, c;
        cin >> s >> e >> c;
        sc[e].push_back({s, c});
    }
}

void sol() {
    dist[0] = 0;
    for (int i = 1; i <= D; i++) {
        if (sc[i].empty()) {
            dist[i] = dist[i - 1] + 1;
        } else {
            for (Shortcut s : sc[i]) {
                if(dist[i] == 0)
                    dist[i] = min(dist[i - 1] + 1, dist[s.s] + s.c);
                else {
                    dist[i] = min(dist[i], dist[s.s] + s.c);
                }
            }
        }
    }
    cout << dist[D];
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