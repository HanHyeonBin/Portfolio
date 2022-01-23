// await 를 사용하는 경우, 항상 async 함수 안에서 사용되어야 한다.

function p(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(ms);
    }, ms);
  });
}

(async function main() {
  const ms = await p(1000);
  console.log(`${ms} ms 후에 실행된다.`);
})(); // 함수 바로 사용할때 쓸 수 있는 문법

//main(); // 이렇게 사용해도 됨
