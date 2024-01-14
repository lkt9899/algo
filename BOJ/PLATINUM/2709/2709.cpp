#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define ull unsigned long long

using namespace std;

ull answer[20];

ull add_mod(ull a, ull b, ull m) {
    a %= m;
    b %= m;
    if (b >= m - a || a >= m - b)
        return (a - m) + b;
    else
        return a + b;
}

ull mul_mod(ull a, ull b, ull m) {
    a %= m;
    b %= m;

    ull res = 0;
    while (b) {
        if (b % 2) {
            res = add_mod(res, a, m);
        }
        a = add_mod(a, a, m);
        b /= 2;
    }
    return res;
}

ull pow_mod(ull x, ull n, ull m) {
    x %= m;
    ull result = 1;

    if (m > 1000000000ll) {
        while (n) {
            if (n % 2) {
                result = mul_mod(result, x, m);
            }
            x = mul_mod(x, x, m);
            n /= 2;
        }
    } else {
        while (n) {
            if (n % 2) {
                result = (result * x) % m;
            }
            x = (x * x) % m;
            n /= 2;
        }
    }

    return result;
}

void getAns() {
    ull cnt = 1, r = 1, mod = 10, delta = 4;
    while (1) {
        ull result = pow_mod(2, r, mod);
        ull tmp = result;
        bool flag = 0;
        while (tmp > 0) {
            if (tmp % 10 != 1 && tmp % 10 != 2) {
                flag = 1;
                break;
            }
            tmp /= 10;
            if (tmp < 10) {
                if (tmp % 10 != 1 && tmp % 10 != 2) {
                    flag = 1;
                }
                break;
            }
        }

        if (r == 1 || flag) {
            if (r == 1) {
                answer[cnt - 1] = r;
                cnt++;
                mod *= 10;
            }
            r += delta;
        } else {
            answer[cnt - 1] = r;
            cnt++;
            mod *= 10;
            delta *= 5;
            if (cnt == 20) break;
        }
    }
}

void sol() {
    getAns();
    int T;
    cin >> T;
    while (T--) {
        int r;
        cin >> r;
        if (r == 20)
            cout << "65539031565589\n";
        else
            cout << answer[r - 1] << "\n";
    }
}

int main() {
    fio;
    sol();
    return 0;
}