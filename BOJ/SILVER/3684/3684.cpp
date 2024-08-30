#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_T 100

using namespace std;

int T;
int ans[2 * MAX_T + 2];

void getNum() {
    for (int a = 0; a <= 10000; a++) {
        for (int b = 0; b <= 10000; b++) {
            bool flag = true;
            for (int i = 2; i < 2 * T; i += 2) {
                int res = ans[i - 1] * a + b;
                res %= 10001;

                int next = res * a + b;
                next %= 10001;

                if (ans[i + 1] == next) {
                    ans[i] = res;
                } else {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ans[2 * T] = (a * ans[2 * T - 1] + b) % 10001;
                return;
            }
        }
    }
}

void input() {
    i1(T);
    fi1(T) {
        int num;
        i1(num);
        ans[2 * i - 1] = num;
    }
}

void sol() {
    getNum();
    for (int i = 2; i <= 2 * T; i += 2) cout << ans[i] << "\n";
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