#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 100002

using namespace std;

int N;
int arr[MAX_N];
stack<int> s;

void input() {
    i1(N);
    fi1(N) i1(arr[i]);
}

void sol() {
    int ans = 0;
    s.push(0);
    fi1(N + 1) {
        while (!s.empty()) {
            int cur = s.top();
            if (arr[cur] <= arr[i]) break;
            s.pop();
            ans = max(ans, arr[cur] * (i - s.top() - 1));
        }
        s.push(i);
    }
    cout << ans;
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