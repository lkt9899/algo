#include <iostream>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define MAX_N 1000000
#define INF 1000000001

#define f0(a) for (int i = 0; i < a; i++)
#define i1(a) cin >> a

using namespace std;

int N;
int A[MAX_N];
int L[MAX_N];
int I[MAX_N];
vector<int> ans;

void lis() {
    // input N
    i1(N);

    // A[i], idx
    int tmp, idx = 0;
    f0(N) {
        // input A[i]
        i1(tmp);

        A[i] = tmp;
        // first time
        if (idx == 0) {
            L[idx++] = tmp;
            I[i] = 0;
        } else {
            // last element < tmp => is increasing
            if (L[idx - 1] < tmp) {
                // save A[i]'s idx
                I[i] = idx;
                // LIS[idx] = A[i]
                L[idx++] = tmp;
            }
            // else => is decreasing
            else {
                // find best place by lower_bound
                I[i] = lower_bound(L, L + idx, tmp) - L;
                // LIS[idx] = A[i]
                L[lower_bound(L, L + idx, tmp) - L] = tmp;
            }
        }
    }

    // save LIS's elements through index array I
    cout << idx << "\n";
    for (int i = N - 1; i >= 0; i--) {
        if (idx == I[i] + 1) {
            ans.push_back(A[i]);
            idx--;
        }
    }

    // print from last index
    for (int i = ans.size() - 1; i >= 0; i--) {
        cout << ans[i] << " ";
    }
}

int main() {
    fio;
    lis();
    return 0;
}