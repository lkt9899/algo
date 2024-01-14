#include <iostream>
#include <string>
#include <vector>
#define cstr(x) x.c_str()

using namespace std;

vector<vector<int>> v;

int strcmp(const char *s1, const char *s2) {
    int res = 0;
    while(*s1 != '\0' && *s2 != '\0') {
        if (*s1 != *s2) res++;
        s1++;
        s2++;
    }

    return res;
}

int solution(string begin, string target, vector<string> words) {
    

    return answer;
}

int main() {
    string begin = "hit";
    string target = "cog";  
    vector<string> words[2] = {{"hot", "dot", "dog", "lot", "log", "cog"}, {"hot", "dot", "dog", "lot", "log"}};

    for (int i = 0; i < 2; i++) {
        cout << solution(begin, target, words[i]) << "\n";
    }
}