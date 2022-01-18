Promise.resolve(/* value */);

Promise.resolve(
  new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve("foo");
    }, 1000);
  })
).then((data) => {
  console.log(
    "프로미스 객체인 경우, resolve된 결과를 받아 then이 실행 된다.",
    data
  );
});

Promise.resolve("bar").then((data) => {
  console.log("then 메서드가 없는 경우, fulfilled 된다.", data);
});
