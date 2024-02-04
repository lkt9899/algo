#include <iostream>
#include <map>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 40

using namespace std;

int N, S;
int arr[MAX_N];
map<int, int> subSum;
long long cnt;

void input() {
    i2(N, S);
    fi0(N) i1(arr[i]);
}

void right(int mid, int sum) {
    if (mid == N) {
        subSum[sum]++;
        return;
    }

    right(mid + 1, sum + arr[mid]);
    right(mid + 1, sum);
}

void left(int st, int sum) {
    if (st == N / 2) {
        cnt += subSum[S - sum];
        return;
    }

    left(st + 1, sum + arr[st]);
    left(st + 1, sum);
}

void sol() {
    right(N / 2, 0);
    left(0, 0);
    if (!S)
        cout << cnt - 1;
    else
        cout << cnt;
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