/*
executor의 reject 함수를 실행할때 인자를 넣어 실행하면, catch의 callback 함수의 인자로 받을 수 있다.
reject('error');
then((reason) => {...})
 */

function p() {
  return new Promise((resolve, reject) => {
    //pening
    setTimeout(() => {
      reject("error"); //rejected
    }, 10000);
  });
}

p()
  .then(() => {
    //then() = fulfilled
    console.log("10000ms 후에 fulfilled 됩니다.");
  })
  .catch((reason) => {
    //catch() = rejected
    console.log("10000ms 후에 rejected 됩니다.", reason);
  });
