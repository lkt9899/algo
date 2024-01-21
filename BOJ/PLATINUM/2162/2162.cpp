#include <cstring>
#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 3000

using namespace std;

struct Axis {
    int x, y;
    bool operator<(const Axis& a) const {
        if (x == a.x) return y < a.y;
        return x < a.x;
    }

    bool operator<=(const Axis& a) const {
        if (x == a.x) return y <= a.y;
        return x <= a.x;
    }
};

struct Line {
    Axis a1, a2;

} lines[MAX_N];

int N;
int parents[MAX_N];
int count[MAX_N];
bool isP[MAX_N];

void init() {
    memset(isP, false, sizeof(isP));
    fi0(N) { parents[i] = i; }
}

void input() {
    i1(N);
    fi0(N) {
        i2(lines[i].a1.x, lines[i].a1.y);
        i2(lines[i].a2.x, lines[i].a2.y);
    }
}

long long ccw(Axis p1, Axis p2, Axis p3) {
    long long s = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
    s -= p2.x * p1.y + p3.x * p2.y + p1.x * p3.y;

    if (s == 0)
        return 0;
    else
        return s / abs(s);
}

void swap(Axis& p1, Axis& p2) {
    Axis tmp = p1;
    p1 = p2;
    p2 = tmp;
}

bool isIntersect(int i1, int i2) {
    Axis p1 = lines[i1].a1;
    Axis p2 = lines[i1].a2;
    Axis p3 = lines[i2].a1;
    Axis p4 = lines[i2].a2;

    long long p1p2 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
    long long p3p4 = ccw(p3, p4, p1) * ccw(p3, p4, p2);

    if (!(p1p2 || p3p4)) {
        if (p2 < p1) swap(p1, p2);
        if (p4 < p3) swap(p3, p4);

        return p3 <= p2 && p1 <= p4;
    }

    return p1p2 <= 0 && p3p4 <= 0;
}

int find(int a) {
    if (parents[a] == a) return a;
    return parents[a] = find(parents[a]);
}

void unite(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);

    if (rootA == rootB) return;
    parents[rootB] = rootA;
}

void sol() {
    fi0(N) {
        fj0(N) {
            if (i == j) continue;
            if (isIntersect(i, j)) unite(i, j);
        }
    }

    int maxCount = 0;
    int groupCount = 0;
    fi0(N) {
        int idx = find(parents[i]);
        count[idx]++;
        if (!isP[idx]) {
            isP[idx] = true;
            groupCount++;
        }

        maxCount = max(maxCount, count[idx]);
    }

    cout << groupCount << "\n" << maxCount;
}

void run() {
    input();
    init();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}