- then 함수에서 다시 프로미스 객체를 리턴하는 방법을 통해 체이닝하면, 비동기 작업을 순차적으로 아래로 표현할 수 있다.

- value가 프로미스 객체인지 아닌지 알 수 없는 경우, 사용하면 연결된 then 메서드를 실행한다.
- value가 프로미스 객체면, resolve 된 then 메서드를 실행한다.
- value가 프로미스 객체가 아니면, value를 인자로 보내면서 then 메서드를 실행한다.

- Promsie.reject를 사용하면, catch 로 연결된 rejected 상태로 변경된다

# finally

- fullfilled 되거나 rejected 된 후에 최종적으로 실행할 것이 있다면, .finally()를 설정하고, 함수를 인자로 넣어준다.

# callback

- 비동기 작업을 할때, callback 함수를 인자로 넣어 로직이 끝나면 callback 함수를 호출한다.
- 위 경우 함수가 아래로 진행되지 않고, callback 함수 안으로 진행된다.

.

# Promise.all

- 프로미스 객체 여러개를 생성하여 배열로 만들어 인자를 넣고 Promise.all 을 실행하면 배열의 모든 프로미스 객체들이 fulfilled 되었을 때, then의 함수가 실행된다.
- then의 함수의 인자로 프로미스 객체들의 resolve 인자값을 배열로 돌려준다.

# Promise.race

- 프로미스 객체 여러개를 생성하여, 배열로 만들어 인자로 넣고 Promise.race 를 실행하면, 배열의 모든 프로미스 객체들 중 가장 먼저 fulfilled 된 것으로, then의 함수가  
  실행된다.
- then의 함수의 인자로 가장 먼저 fulfilled 된 프로미스 객체의 resolve 인자값을 돌려준다.
