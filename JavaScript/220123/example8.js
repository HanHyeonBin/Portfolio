function P(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(ms);
    }, ms);
  });
}

//Promise 와 async await의 연속된 처리의 차이

// Promsie
// 비동기가 연속되서 처리 되는 느낌

P(1000)
  .then(() => P(1000))
  .then(() => P(1000))
  .then(() => {
    console.log("3000 ms 후에 실행");
  });

// async await
// 한 줄 한 줄 비동기처리가 끝나면 진행되는 느낌

(async function main() {
  await P(1000);
  await P(1000);
  await P(1000);
  console.log("3000 ms 후에 실행");
})();
