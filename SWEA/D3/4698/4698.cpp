#include <cmath>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

const int MAX = 1000001;
int D, A, B, ans;
int arr[MAX];

void PrimeNumberSieve() {
    fill(&arr[0], &arr[MAX], 1);
    for (int i = 2; i < sqrt(MAX); i++) {
        if (arr[i] == 0) continue;

        for (int j = i * i; j <= MAX; j += i) {
            arr[j] = 0;
        }
    }
}

void init() { ans = 0; }

void input() { cin >> D >> A >> B; }

void sol() {
    if (A < 2) A = 2;
    for (int i = A; i <= B; i++) {
        if (arr[i] == 0) continue;

        int tmp = i;
        while (tmp != 0) {
            if (tmp % 10 == D) {
                ans++;
                break;
            }
            tmp /= 10;
        }
    }
}

void run() {
    init();
    input();
    sol();
}

int main(int argc, char** argv) {
    fio;
    int T, test_case;

    freopen("input.txt", "r", stdin);
    cin >> T;
    PrimeNumberSieve();
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " " << ans << "\n";
    }
    return 0;
}