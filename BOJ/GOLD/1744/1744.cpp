#include <algorithm>
#include <iostream>
#include <vector>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int N;
vector<int> mns;
vector<int> pls;
int ans = 0;

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int num;
        cin >> num;
        if (num < 1)
            mns.push_back(num);
        else
            pls.push_back(num);
    }
    sort(mns.begin(), mns.end());
    sort(pls.begin(), pls.end(), greater<int>());
}

void sol() {
    for (int i = 0; i < mns.size(); i += 2) {
        if (i == mns.size() - 1) {
            ans += mns[i];
            break;
        }

        ans += mns[i] + mns[i + 1] > mns[i] * mns[i + 1] ? mns[i] + mns[i + 1] : mns[i] * mns[i + 1];
    }

    for (int i = 0; i < pls.size(); i += 2) {
        if (i == pls.size() - 1) {
            ans += pls[i];
            break;
        }

        ans += pls[i] + pls[i + 1] > pls[i] * pls[i + 1] ? pls[i] + pls[i + 1] : pls[i] * pls[i + 1];
    }

    cout << ans;
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