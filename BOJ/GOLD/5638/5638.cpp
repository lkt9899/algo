#include <algorithm>
#include <iostream>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define pii pair<int, int>

using namespace std;

struct Info {
    int f, c;
};

const long long INF = 20000000000;
int N, T, A, H;
long long minC;
long long minS;
long long checkSum = 0;
vector<Info> dam;

bool compare(Info a, Info b) { return a.c < b.c; }

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int f, c;
        cin >> f >> c;
        checkSum += f;
        dam.push_back({f, c});
    }
    sort(dam.begin(), dam.end(), compare);
}

void calc(int next, int sum, int cost, int visit) {
    if (sum != 0 && (double)(A / sum) <= H) {
        if (minC > cost) {
            minC = cost;
            minS = sum;
        }
        return;
    }

    for (int i = next; i < N; i++) {
        if (!(visit & (1 << i))) {
            if (sum + dam[i].f > minS) continue;
            calc(i + 1, sum + dam[i].f, cost + dam[i].c, visit | (1 << i));
        }
    }
}

void sol() {
    cin >> T;
    for (int i = 1; i <= T; i++) {
        cout << "Case " << i << ": ";

        cin >> A >> H;
        if((long long)(A / checkSum) > H) {
            cout << "IMPOSSIBLE\n";
        } else {
            minC = INF;
            minS = INF;
            calc(0, 0, 0, 0);
            cout << minC << "\n";
        }
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