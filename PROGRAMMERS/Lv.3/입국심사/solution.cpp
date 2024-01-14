#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

vector<int> *vp;
int cnt, N;
long long answer;

long long calc(long long mid) {
    long long sum = 0;
    for (auto it = vp->begin(); it != vp->end(); it++) {
        sum += (mid / *it);
        if (sum > N) break;
    }

    return sum;
}

void find(long long start, long long end) {
    if (start == end) return;

    long long mid = (start + end) >> 1;
    long long sum = calc(mid);
    if (sum < N) {
        find(mid + 1, end);
    } else {
        answer = mid;
        find(start, mid);
    }
}

long long solution(int n, vector<int> times) {
    cnt = times.size();
    N = n;
    vp = &times;

    long long start = 1;
    long long end = 1000000000 / cnt;
    end *= n;

    find(start, end);

    return answer;
}

int main() {
    vector<int> times = {7, 10};
    cout << solution(6, times);
}