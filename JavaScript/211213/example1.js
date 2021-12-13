// 생성자 함수를 이용하여 새로운 객체를 만들어 내는 방법

function Person(name, age) {
  console.log(this);
  this.name = name;
  this.age = age;
}

const p = new Person("Mark", 37);

console.log(p, p.name, p.age);

const a = new Person("Bin", 25);

console.log(a, a.name, a.age);

const Cat = (name, age) => {
  console.log(this);
  this.name = name;
  this.age = age;
};

const c = new Cat("냥이", 1);
