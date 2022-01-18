/*
executor 의 resolve 함수를 실행할때 인자를 넣어 실행하면, then의 callback 함수의 인자로 받을 수 있다.
resolve('hello');
then((message) => {...})
*/

function p() {
  return new Promise((resolve, reject) => {
    //pening
    setTimeout(() => {
      //reject();
      resolve("hello");
    }, 10000);
  });
}

p()
  .then((message) => {
    //then() = fulfilled
    console.log("10000ms 후에 fulfilled 됩니다.", message);
  })
  .catch(() => {
    //catch() = rejected
    console.log("10000ms 후에 rejected 됩니다.");
  });
