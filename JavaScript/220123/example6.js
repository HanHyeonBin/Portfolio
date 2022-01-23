//error 의 전파

function p(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      //resolve(ms);
      reject(new Error("reason"));
    }, ms);
  });
}

async function asyncP() {
  const ms = await p(1000);
  // async function에서는 reject 된 값을 자동으로 throw 해줌
  // 정상 동작을 원하면 해당 p 함수로 넘어가는 코드를 try catch 문으로 묶어준 후 에러에 대한 처리를 하면 return 값을 넘겨줄 수 있음
  return "Mark : " + ms;
}

(async function main() {
  try {
    const name = await asyncP();
    console.log(name);
  } catch (error) {
    console.log(error);
  }
})();
