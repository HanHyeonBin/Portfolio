// Promise 객체가 rejected 된 경우의 처리를 위해
// try catch 를 이용한다.

function p(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      //resolve(ms);
      reject(new Error("reason"));
    }, ms);
  });
}

(async function main() {
  try {
    const ms = await p(1000);
  } catch (error) {
    console.log(error);
  }
})();
