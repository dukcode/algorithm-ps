# 알고리즘 문제별 특이점 정리

## [chap03] linkedlist

### [B1406](https://www.acmicpc.net/problem/1406) - 에디터

`LinkedList`의 `Iterator`의 작동방식에 대해서 알아두자

- 생성자 : `index`는 `next()`로 불렀을 때 나올 위치
- `remove()` : `next()`나 `previous()`로 불러왔던 마지막 요소를 제거
- `next()` :  다음과 같이 작동한다.
  ![](https://i.imgur.com/nevyeLP.png)
- `previous()` : 다음과 같이 작동한다.
  ![](https://i.imgur.com/RSeEirh.png)
- `add()` : 다음과 같이 작동한다.
  ![](https://i.imgur.com/YGeJ7x0.png)

## [chap05] stack

### [B17298](https://www.acmicpc.net/problem/17298) - 오큰수

두가지 풀이가 존재한다.

- 앞쪽에서 부터 순차적으로 나가는 `stack`으로 푸는 방법
- 왼쪽부터 나가면서, 해당 요소의 오른쪽 큰수의 `index`를 배열에 담고, 새로운 요소에서 오른쪽 수가 작으면 오른쪽 수의 `index`를 따라가는 방법, 따라갔을 때 해당 요소가 자신보다 크면
  해당 `index`를
  적는다.

### [B3015](https://www.acmicpc.net/problem/3015) - 오아시스 재결합

처음에 같은 키의 중복에 대해서 고려했지만 처음 pop 시킬때 고려한 것이 아니라 후에 push할때 중복을 고려했다.

![](https://i.imgur.com/YqzPeag.png)

그러면 `if`문에서 중복된 덩어리와 이전 사람이 고려가 안된다. `if`문 안에서 문에 1을 더 더해줘야한다.(이전 사람이 empty가 아닐때) 코드가 복잡해지므로 앞에서 한방에..

### [B6549](https://www.acmicpc.net/problem/6549) - 히스토그램에서 가장 큰 직사각형

★★★★★★★★★★★★★★


## [chap07] stack

### [B11003](https://www.acmicpc.net/problem/11003) - 최솟값 찾기

최소값을 찾으려면 두가지 조건이 필요한 것을 잘 생각해야 한다.

1. 구간 내에 존재할 것
2. 새로 들어온 수 보다 작은 것들은 고려할 필요가 없다.

위의 두가지 조건을 어떤 자료구조에서 어떤 순서로 적용할 것인지가 관건이다.
