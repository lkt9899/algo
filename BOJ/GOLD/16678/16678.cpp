#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

const int MAX = 100000;
int N;
long long ans;
int people[MAX];

void init() { ans = 0; }

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> people[i];
    }
    sort(people, people + N);

    if (people[0] != 1) {
        ans += people[0] - 1;
        people[0] = 1;
    }
}

void sol() {
    for (int i = 1; i < N; i++) {
        if (people[i] > people[i - 1]) {
            ans += people[i] - (people[i - 1] + 1);
            people[i] = people[i - 1] + 1;
        }
    }

    cout << ans;
}

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