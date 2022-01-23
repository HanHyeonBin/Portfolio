// async function 에서 return 되는 값은
// Promsie.resolve 함수로 감싸서 리턴된다.

function p(ms) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      //resolve(ms);
      reject(new Error("reason"));
    }, ms);
  });
}

async function asyncP() {
  return "Mark";
}

(async function main() {
  try {
    const name = await asyncP();
    console.log(name);
  } catch (error) {
    console.error();
  }
})();
