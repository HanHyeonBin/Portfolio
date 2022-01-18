//then 에 함수를 넣는 여러 방법

function p() {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve();
    }, 1000);
  });
}

//체이닝
p()
  .then(() => {
    return p();
  })
  .then(() => p())
  .then(p)
  .then(() => {
    console.log("4000ms 후에 fulfilled됨");
  });
