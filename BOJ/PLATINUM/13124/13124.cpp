#include <deque>
#include <iostream>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX_N 100001

using namespace std;

int N, Q;
int A[MAX_N];

void input() {
    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> A[i];
    }

    cin >> Q;
}

void _swap(int a, int b) {
    int tmp = A[a];
    A[a] = A[b];
    A[b] = tmp;
}

bool check() {
    for (int i = 1; i <= N; i++) {
        deque<int> c;
        deque<int> nc;
        for (int j = 1; j <= N; j++) {
            if (i == j) continue;

            if (A[1] > A[i])
                c.push_back(i);
            else
                nc.push_back(i);
        }

        // degree 1, 2, n - 2, other
        if (c.size() == 1) {
        } else if (c.size() == 2) {
        } else if (c.size() == N - 2) {
        } else {
        }
    }
}

void sol() {
    for (int i = 0; i < Q; i++) {
        int a, b;
        cin >> a >> b;
        _swap(a, b);
        cout << (check() ? "YES\n" : "NO\n");
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