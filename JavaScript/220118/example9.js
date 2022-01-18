// 프로미스 객체 여러개를 생성하여 배열로 만들어 인자를 넣고 Promise.all 을 실행하면 배열의 모든 프로미스 객체들이 fulfilled 되었을 때, then의 함수가 실행된다.
// - then의 함수의 인자로 프로미스 객체들의 resolve 인자값을 배열로 돌려준다.

// Promise.all([프로미스 객체들]);

function p(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(ms);
    }, ms);
  });
}

Promise.all([p(1000), p(2000), p(3000)]).then((messages) => {
  console.log("모두 fulfilled 된 이후에 실행된다.", messages); // [p(1000), p(2000), p(3000)] 1초,2초,3초 뒤 해당 줄 코드 실행됨
});
