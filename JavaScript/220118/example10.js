function p(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(ms);
    }, ms);
  });
}

Promise.race([p(1000), p(2000), p(3000)]).then((message) => {
  console.log("가장 빠른 하나가 fulfilled 된 이후에 실행된다.", message); // 가장 빠른 1000 ms 가 실행된 후 바로 해당 줄 코드 실행
});
