#include <cmath>
#include <iostream>
#include <string>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define pi 3.141592653589793238462643

using namespace std;

string N;
int ans;

void init() {}

void input() { cin >> N; }

int getN(string n) {
    if (n.size() > 18) {
        double ln_s = log(stoll(n.substr(0, 10))) + (n.size() - 10) * log(10);
        for (int i = 1;; i++) {
            double ln_fac = i * log(i) - i + 0.5 * log(2 * pi * i);
            if (ln_fac - ln_s > -1) return cout << i << '\n', 0;
        }
    } else {
        long long F = 1;
        for (int i = 1;; i++) {
            if (to_string(F *= i) == n) {
                return cout << i << '\n', 0;
            }
        }
    }
}

void sol() { ans = getN(N); }

void run() {
    init();
    input();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}