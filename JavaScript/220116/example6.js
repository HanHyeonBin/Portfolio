// p라는 프로미스 객체는 10000ms(10초) 후에 fulfilled 되는 코드

const p = new Promise((resolve, reject) => {
  //pening
  setTimeout(() => {
    resolve(); //fulfilled
  }, 10000);
});

p.then(() => {
  //콜백을 작성하는 공간
  console.log("10000ms 후에 fulfilled 됩니다.");
});
