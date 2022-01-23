// Promise 객체를 리턴하는 함수

function p(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(ms);
    }, ms);
  });
}

// Promise 객체를 이용해서 비동기 로직을 수행할 때

p(1000).then((ms) => {
  console.log("${ms} ms 후에 실행된다.");
});

// Promise 객체를 리턴하는 함수를 await로 호출하는 방법
// await는 async function 안에서만 사용 할 수 있기 때문에 전역공간으로 처리 해놓은 아래 코드는 오류가 날 수 밖에 없다.
const ms = await p(1000); // resolve 된 return 값을 await를 활용하여 변수에 넣을 수 있다.
console.log("%{ms} ms 후에 실행된다.");
