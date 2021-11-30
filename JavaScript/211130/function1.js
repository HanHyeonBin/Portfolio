//function
//이름이 hello1인 함수를 선언

function hello1() {
  console.log("hello1");
}

console.log(hello1, typeof hello1);

// 함수의 매개변수
// 함수를 호출할 때 값을 지정

function hello2(name) {
  console.log("hello2", name);
}

// 함수의 리턴
// 함수를 실행하면 얻어지는 값

function hello3(name) {
  return `hello3 ${name}`;
}

console.log(hello3("Mark"));
