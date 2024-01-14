# [[GOLD III] 16441. 아기돼지와 늑대](https://www.acmicpc.net/problem/16441)
<!-- # [[SWEA_난이도] 문제번호. 문제이름(https://www.) -->

### 문제 설명

```
산으로 둘러싸인 고리분지에 사는 아기돼지 삼형제는 엄마돼지로부터 독립하여 새 집을 지으려 합니다.

고리분지는 N × M 크기의 2차원 격자로 나타낼 수 있고 각 칸의 지형은 초원, 빙판, 산 중 하나입니다.

고리분지에는 돼지가족들 뿐만 아니라 늑대들도 살고 있습니다. 늑대는 상하좌우 인접한 칸 중 산이 아닌 칸으로 이동할 수 있습니다. 만약 이동한 칸이 빙판이라면 초원을 밟거나 산에 부딪칠 때까지 이동한 방향으로 미끄러집니다. 산에 부딪친 경우 늑대는 빙판 위에 가만히 서있을 수 있고 다시 다른 방향으로 이동할 수 있습니다.

게으른 아기돼지들은 지푸라기로 집을 지을 예정이기 때문에 늑대가 집이 있는 칸에 도착하기만 한다면 손쉽게 침입할 수 있습니다. 고리분지에 사는 늑대들이 도달할 수 없고 지형이 초원인 칸을 '안전한 곳'이라고 부릅니다. 게으른 아기돼지들을 위해 고리분지의 지도가 주어지면 지도 위에 모든 안전한 곳의 위치를 표시해주세요.
```

### 입력

```
첫 번째 줄에는 격자의 행의 수를 나타내는 N (3 ≤ N ≤ 100) 과 격자의 열의 수를 나타내는 M (3 ≤ M ≤ 100) 이 주어집니다.

두 번째 줄부터 N개의 줄에 지도의 정보를 나타내는 길이가 M인 문자열들이 주어집니다. 

i+1번째 줄의 j번째 문자는 i번째 행 j번째 열의 지형 종류를 의미하며 '.' 일 경우 초원, '+' 일 경우 빙판, '#' 일 경우 산, 그리고 'W'는 늑대가 살고 있음을 나타냅니다. 늑대가 사는 칸은 여러 개일 수 있고 늑대가 사는 지형은 항상 초원입니다. 지도의 첫 번째, N번째 행과 첫 번째, M번째 열은 항상 산입니다.
```

### 출력

```
입력으로 주어진 지도를 출력하되 아기돼지가 살 수 있는 초원은 문자 'P'로 대체하여 출력합니다.
```

### 문제접근

```
- 칸 유형에 따른 행동
    1. 초원
        방문 처리 후 안전지역 후보에서 제외
    2. 빙판
        빙판은 계속 지나갈 수 있으므로 방문처리 X
        빙판이 끝난 후 산이면 그 자리에서 방향 전환
        빙판이 끝난 후 초원이면 초원으로 이동
    3. 산
        산은 지나갈 수 없으므로 벽으로 취급

- 각 늑대들의 위치로부터 BFS를 통해 갈 수 있는 모든 초원 방문
- 이전 늑대가 지나간 곳에 늑대가 있다면 그 늑대는 다시 BFS를 할 필요가 없음
```

## 풀이

### run()

```java
static void run() throws IOException {
    input();
    for (int i = 0; i < wolf.size(); i++)
        if (visited[wolf.get(i).x][wolf.get(i).y] == 0)
            bfs(wolf.get(i));
    print();
}
```

### 입력

```java
static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    for (int i = 0; i < N; i++) {
        map[i] = br.readLine().toCharArray();
        for (int j = 0; j < M; j++) {
            // 늑대 좌표 저장
            if (map[i][j] == 'W')
                wolf.add(new Axis(i, j));
            else if (map[i][j] == '.')
                map[i][j] = 'P';
        }
    }
    br.close();
}
``` 

### 안전지대 판별 (BFS)

```java
static void bfs(Axis start) {
    initVisited();
    Queue<Axis> q = new ArrayDeque<>();
    q.add(start);
    visited[start.x][start.y] = 1;
    while (!q.isEmpty()) {
        Axis c = q.poll();

        for (int i = 0; i < 4; i++) {
            int ni = c.x + di[i];
            int nj = c.y + dj[i];

            if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == '#')
                continue;
            if (map[ni][nj] == '+') {
                while (map[ni][nj] == '+') {
                    ni += di[i];
                    nj += dj[i];
                }
                if (map[ni][nj] == '#') {
                    ni -= di[i];
                    nj -= dj[i];
                }
            }

            if (visited[ni][nj] == 1)
                continue;
            if (map[ni][nj] == 'P')
                map[ni][nj] = '.';
            visited[ni][nj] = 1;
            q.add(new Axis(ni, nj));
        }
    }
}

static void initVisited() {
    for (int[] a : visited)
        Arrays.fill(a, 0);
}
```