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

int N;
int pages[10];

void input() { i1(N); }

void add(int num) { fi0(10) pages[i] += num; }

void adj(int n, int digit) {
    while (n > 0) {
        pages[n % 10] += digit;
        n /= 10;
    }
}

void sol(int s, int e, int digit) {
    while (s % 10 != 0 && s <= e) {
        adj(s, digit);
        s++;
    }

    if (s > e) return;
    while (e % 10 != 9 && e >= s) {
        adj(e, digit);
        e--;
    }

    long long cnt = e / 10 - s / 10 + 1;
    add(cnt * digit);
    sol(s / 10, e / 10, digit * 10);
}

void run() {
    input();
    sol(1, N, 1);
    fi0(10) cout << pages[i] << " ";
}

int main() {
    fio;
    run();
    return 0;
}