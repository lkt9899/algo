#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)

#define MAX_N 1000000

using namespace std;

int N;
int arr[MAX_N];
int lis[MAX_N];

void input() {
    i1(N);
    fi0(N) i1(arr[i]);
}

int bin_search(int l, int r, int t) {
    if (l == r) return l;

    int mid = (l + r) >> 1;
    if (lis[mid] < t)
        return bin_search(mid + 1, r, t);
    else
        return bin_search(l, mid, t);
}

void sol() {
    int idx = 0;
    lis[idx] = arr[0];
    fi1(N - 1) {
        if (arr[i] < lis[idx])
            lis[bin_search(0, idx, arr[i])] = arr[i];
        else if (arr[i] > lis[idx])
            lis[++idx] = arr[i];
    }
    cout << idx + 1;
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