#include <cstring>
#include <iostream>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX_N 100000

using namespace std;

int N, M;
int parents[MAX_N];
bool isUnited[MAX_N];

int find(int a) {
    if (parents[a] == a) return a;
    return parents[a] = find(parents[a]);
}

void unite(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);

    if (rootA == rootB) return;

    parents[rootB] = rootA;
}

void input() {
    cin >> N >> M;

    memset(isUnited, false, sizeof(bool) * N);
    for (int i = 0; i < N; i++) {
        parents[i] = i;
    }

    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        unite(a, b);
    }
}

void sol() {
    int ans = 0;
    for (int i = 0; i < N; i++) {
        if (parents[i] == i) {
            ans++;
        }
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