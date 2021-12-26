// prototype 상속

function Person() {}

Person.prototype.hello = function () {
  console.log("hello");
};

function Korea(region) {
  this.region = region;
  this.where = function () {
    console.log("where", this.region);
  };
}

Korea.prototype = Person.prototype; // 쉽게 생각하면 Korea함수와 Person함수 내용을 공유 할 수 있음

const k = new Korea("Seoul");

k.hello(); // Korea.prototype = Person.prototype; 해당 코드로 prototype 처리 하지 않았다면 k객체는 Person함수 안에 있는 hello함수를 사용할 수 없기 때문에 에러코드 발생
k.where();
