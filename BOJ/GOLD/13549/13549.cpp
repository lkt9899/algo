#include <cstring>
#include <deque>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define forward(x) x + 1
#define back(x) x - 1
#define teleport(x) x * 2

using namespace std;

const int MAX_N = 100001;
int N, K;
int time[MAX_N];

void init() { memset(time, -1, sizeof(time)); }

void input() { cin >> N >> K; }

int bfs() {
    deque<int> dq;
    dq.push_back(N);
    time[N] = 0;
    while (!dq.empty()) {
        int next = dq.front();
        dq.pop_front();

        if (next == K) {
            return time[K];
        }

        int f = forward(next);
        int b = back(next);
        int t = teleport(next);
        if (t < MAX_N && time[t] == -1) {
            time[t] = time[next];
            dq.push_front(t);
        }

        if (b >= 0 && time[b] == -1) {
            time[b] = time[next] + 1;
            dq.push_back(b);
        }

        if (f < MAX_N && time[f] == -1) {
            time[f] = time[next] + 1;
            dq.push_back(f);
        }
    }

    return 0;
}

void run() {
    init();
    input();
    cout << bfs();
}

int main() {
    fio;
    run();
    return 0;
}