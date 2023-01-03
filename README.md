# 알고리즘 문제별 특이점 정리

## [chap03] linkedlist

### [B1406](https://www.acmicpc.net/problem/1406)

`LinkedList`의 `Iterator`의 작동방식에 대해서 알아두자

- 생성자 : `index`는 `next()`로 불렀을 때 나올 위치
- `remove()` : `next()`나 `previous()`로 불러왔던 마지막 요소를 제거
- `next()` :  다음과 같이 작동한다.
![](https://i.imgur.com/nevyeLP.png)
- `previous()` : 다음과 같이 작동한다.
![](https://i.imgur.com/RSeEirh.png)
- `add()` : 다음과 같이 작동한다.
![](https://i.imgur.com/YGeJ7x0.png)

