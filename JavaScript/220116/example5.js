/*
ES6 부터 JavaScript 의 표준 내장 객체 로 추가되었다.
ES6 를 지원하는 브라우저나 Node.js 에서 전역에 있는 Promise 를 확인할 수 있다.
*/

console.log(Promise);

/*
생성자를 통해서 Promise 객체를 만들 수 있다.
생성자의 인자로 executor 라는 함수를 이용한다.
new Promise( executor )
*/

/*
executor 함수는 resoleve와 reject 를 인자로 가니다.
(resolve, reject) => {...}
resolve와 reject는 함수이다.
resolve(), reject() */

/*
생성자를 통해서 프로미스 객체를 만드는 순간 pending(대기) 상태라고 한다. */

new Promise((resolve, reject) => {}); //pending

/*
executor 함수 인자 중 하나인 resolve 함수를 실행하면, fulfilled(이행) 상태가 된다.
*/

new Promise((resolve, reject) => {
  //
  //...
  //resolve(); fulfilled(이행 상태)
});

/*
executor 함수 인자 중 하나인 reject 함수를 실행하면, rejected(거부) 상태가 된다.
new Promise((resolve, reject) => {
    reject(); => rejected
});
*/
