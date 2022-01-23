# async function과 await

- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/async_function

- async function 함수이름() {}
- const 함수이름 = async() => {}

# Promise 객체를 리턴하는 함수를 await로 호출하는 방법

- await는 async function 안에서만 사용 할 수 있다.
- async function에서는 reject 된 값을 자동으로 throw 해줌.<br>
  정상 동작을 원하면 해당 p 함수로 넘어가는 코드를 try catch 문으로 묶어준 후 에러에 대한 처리를 하면 return 값을 넘겨줄 수 있다.

# Promise 와 async await의 연속된 처리의 차이

- Promsie는 비동기가 연속되서 처리 되는 느낌
- async await는 한 줄 한 줄 비동기처리가 끝나면 진행되는 느낌
