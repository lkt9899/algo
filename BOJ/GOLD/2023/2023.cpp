#include <cmath>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int N;
int start[4] = {2, 3, 5, 7};

void input() { cin >> N; }

bool isPrime(int num) {
    for (int i = 2; i <= sqrt(num); i++) {
        if (num % i == 0) return 0;
    }
    return 1;
}

void make(int num, int cnt) {
    if (cnt == 0) cout << num << "\n";

    for (int i = 1; i < 10; i += 2) {
        int tmp = num * 10 + i;
        if (isPrime(tmp)) {
            make(tmp, cnt - 1);
        }
    }
}

void sol() {
    for (int i = 0; i < 4; i++) {
        make(start[i], N - 1);
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