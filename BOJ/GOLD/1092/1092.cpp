#include <iostream>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define i1(a) cin >> a
#define i2(a, b) cin >> a >> b
#define i3(a, b, c) cin >> a >> b >> c

#define fi0(e) for (int i = 0; i < e; i++)
#define fi1(e) for (int i = 1; i <= e; i++)
#define fj0(e) for (int j = 0; j < e; j++)
#define fj1(e) for (int j = 1; j <= e; j++)

#define MAX_N 50
#define MAX_M 10001

using namespace std;

int N, M, ans, cnt;
vector<int> sorted(MAX_M);
vector<int> box;
vector<int> crane;

void merge(vector<int> &arr, int s, int e) {
    int i, j, k, mid;

    mid = (s + e) >> 1;
    i = s;
    j = mid + 1;
    k = 0;

    while (i <= mid && j <= e) {
        if (arr[i] >= arr[j])
            sorted[k++] = arr[i++];
        else
            sorted[k++] = arr[j++];
    }

    while (i <= mid) sorted[k++] = arr[i++];
    while (j <= e) sorted[k++] = arr[j++];

    for (i = s; i <= e; i++) arr[i] = sorted[i - s];
}

void merge_sort(vector<int> &arr, int s, int e) {
    if (s < e) {
        int mid = (s + e) >> 1;
        merge_sort(arr, s, mid);
        merge_sort(arr, mid + 1, e);
        merge(arr, s, e);
    }
}

void input() {
    i1(N);
    crane.assign(N, 0);
    fi0(N) i1(crane[i]);
    i1(M);
    box.assign(M, 0);
    fi0(M) i1(box[i]);
}

int binary_search(int s, int e, int t) {
    int result = -1;
    while (s <= e) {
        int mid = (s + e) >> 1;
        if (box[mid] <= t) {
            result = mid;
            e = mid - 1;
        } else {
            s = mid + 1;
        }
    }
    return result;
}

void sol() {
    merge_sort(crane, 0, N - 1);
    merge_sort(box, 0, M - 1);

    if (crane[0] < box[0])
        cout << "-1\n";
    else {
        ans = 0;
        while (!box.empty()) {
            ans++;
            fi0(crane.size()) {
                int toRemove = binary_search(0, box.size() - 1, crane[i]);
                if (toRemove != -1)
                    box.erase(box.begin() + toRemove);
                else
                    crane.erase(crane.begin() + i);
            }
        }

        cout << ans;
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