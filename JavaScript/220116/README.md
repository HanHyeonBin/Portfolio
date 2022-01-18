# extends

- 클래스의 상속의 기본

# override

- 클래스의 상속 멤버 변수 및 함수 오버라이딩, 추가

# static

- 클래스의 상속 static 상속

# Promise

- https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Promise
- executor 함수는 resoleve와 reject 를 인자로 가진다.
  (resolve, reject) => {...}resolve와 reject는 함수이다.
  resolve(), reject()

- 생성자를 통해서 프로미스 객체를 만드는 순간 pending(대기) 상태라고 한다.
- executor 함수 인자 중 하나인 resolve 함수를 실행하면, fulfilled(이행) 상태가 된다.
- executor 함수 인자 중 하나인 reject 함수를 실행하면, rejected(거부) 상태가 된다.
- then 을 설정하는 시점을 정확히하고,
  함수의 실행과 동시에 프로미스 객체를 만들면서 pending이 시작하도록 하기 위해
  프로미스 객체를 생성하면서 리턴하는 함수 (p)를 만들어 함수(p) 실행과 동시에 then을 설정한다.
