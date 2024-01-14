#include <bits/stdc++.h>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

int ans, N, M, *parent;

void init() {
    ans = 0;
    parent = new int[N+1];
    for(int i = 1; i <= N; i++) {
        parent[i] = i;
    }
}

void input() {
    cin >> N >> M;
    init();
}

int getParent(int c) {
    if(parent[c] == c) return c;
    else return parent[c] = getParent(parent[c]);
}

void unionParent(int x, int y) {
    x = getParent(x);
    y = getParent(y);

    if(x < y) parent[y] = x;
    else parent[x] = y;
}

void run() {
    for(int i = 0; i < M; i++) {
        int s, e;
        cin >> s >> e;
        unionParent(s, e);
    }

    for(int i = 1; i <= N; i++) {
        if(parent[i] == i) ans++;
    }
}

void output(const int &t) {
    cout << "#" << t << " " << ans << "\n";
}

int main(int argc, char** argv)
{
    fio;
    int T, test_case;
    
    cin>>T;
    for(test_case = 1; test_case <= T; ++test_case) {
        input();
        run();
        output(test_case);
    }
    delete []parent;
    return 0;
}