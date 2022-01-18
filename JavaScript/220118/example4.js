//finally로 resolve나 reject된 후 최종실행 할 것을 설정

function p() {
  return new Promise((resolve, reject) => {
    //pening
    setTimeout(() => {
      reject(new Error("bad")); //rejected
    }, 10000);
  });
}

p()
  .then((message) => {
    //then() = fulfilled
    console.log("10000ms 후에 fulfilled 됩니다.", message);
  })
  .catch((reason) => {
    //catch() = rejected
    console.log("10000ms 후에 rejected 됩니다.", reason);
  })
  .finally(() => {
    console.log("끝났습니다.");
  });
