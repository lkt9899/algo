#include <iostream>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define pow(x) x* x

int X, Y, D, T;

using namespace std;

double m(double x1, double x2) { return x1 <= x2 ? x1 : x2; }

double sqrt(double x) {
    double s = x / 2;
    double t = 0;
    for (; s != t;) {
        t = s;
        s = ((x / t) + t) / 2;
    }

    return s;
}

void input() { cin >> X >> Y >> D >> T; }

void sol() {
    double dist = sqrt(pow(X) + pow(Y));
    int jump = dist / D;

    double result = 0;

    // 걸어가기 vs 점프 1번 + 남은 거리 걸어가기
    result = m(dist, (T - D) * jump + dist);
    // result vs 점프 한 번 더 + 남은 거리 돌아가기
    result = m(result, (jump + 1) * T + abs(dist - D * (jump + 1)));

    // 점프 한 번이라도 했으면
    if (jump) {
        // result vs 이등변 삼각형
        result = m(result, T * (jump + 1));
    } else {
        // 점프 안했으면 걸어가기 vs 이등변 삼각형
        result = m(result, 2 * T);
    }

    cout << fixed;
    cout.precision(9);
    cout << result;
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