/*
보통 reject 함수를 실행하며 rejected 되는 이유를 넘기는데, 표준 내장 객체인 Error의 생성자를 이용하여 Error 객체를 만들 수 있다.
*/

function p() {
  return new Promise((resolve, reject) => {
    //pening
    setTimeout(() => {
      reject(new Error("bad")); //rejected
    }, 10000);
  });
}

p()
  .then(() => {
    //then() = fulfilled
    console.log("10000ms 후에 fulfilled 됩니다.");
  })
  .catch((error) => {
    //catch() = rejected
    console.log("10000ms 후에 rejected 됩니다.", error);
  });
