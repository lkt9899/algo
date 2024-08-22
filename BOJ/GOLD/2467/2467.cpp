#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

using namespace std;

#define MAX_N 100000

int N, al, ar, ans = 2000000001;
int solution[MAX_N];

void input() {
    i1(N);
    fi0(N) i1(solution[i]);
}

int abs(int n) { return n > 0 ? n : -n; }

void sol() {
    int l = 0, r = N - 1;
    while (l < r && ans != 0) {
        int sum = solution[l] + solution[r];
        if (abs(sum) < ans) {
            ans = abs(sum);
            al = l, ar = r;
        }
        if (sum < 0)
            l++;
        else
            r--;
    }
    cout << solution[al] << " " << solution[ar];
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