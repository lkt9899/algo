#include <iostream>

using namespace std;

int main() {
    int A, B, N;
    int ans;
    cin >> A >> B >> N;
    A %= B;
    while(N--) {
        A = (A % B) * 10;
        ans = A / B;
        A -= (B * ans);
    }
    cout << ans;
}