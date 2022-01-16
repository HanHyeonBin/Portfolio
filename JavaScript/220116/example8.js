/**
 마찬가지로 프로미스 객체가 rejected 되는 시점에 p.catch 안에 설정한 callback 함수가 실행된다.
 */

function p() {
  return new Promise((resolve, reject) => {
    //pening
    setTimeout(() => {
      reject(); //rejected
    }, 10000);
  });
}

p()
  .then(() => {
    //then() = fulfilled
    console.log("10000ms 후에 fulfilled 됩니다.");
  })
  .catch(() => {
    //catch() = rejected
    console.log("10000ms 후에 rejected 됩니다.");
  });
