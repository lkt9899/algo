#include <iostream>
#include <queue>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define pii pair<int, int>

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

using namespace std;

int N;
priority_queue<int, vector<int>, greater<int>> stpq;
priority_queue<int, vector<int>, greater<int>> epq;

void input() {
    i1(N);
    fi0(N) {
        int n, s, e;
        i3(n, s, e);
        stpq.push(s);
        epq.push(e);
    }
}

void sol() {
    int ans = 0;
    int _max = 0;
    while (!stpq.empty()) {
        if (stpq.top() < epq.top()) {
            ans++;
            stpq.pop();
        } else if (stpq.top() == epq.top()) {
            stpq.pop();
            epq.pop();
        } else {
            ans--;
            epq.pop();
        }

        _max = max(_max, ans);
    }

    cout << _max;
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