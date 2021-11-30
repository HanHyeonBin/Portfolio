// 생성자 함수로 함수를 만드는 방법
// const hello = new Function();

const sum = new Function("a", "b", "c", "return a+b+c");

console.log(sum(1, 2, 3));

// function 과 new Function()의 차이점
global.a = 0;
{
  const a = 1;

  const test = new Function("return a"); // 전역 변수를 사용해야 할 때 쓸 수 있는 방법

  console.log(test());
}

{
  const a = 2;

  const test = function () {
    return a;
  };

  console.log(test());
}
