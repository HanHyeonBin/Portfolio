// constructor(생성자)

class A {}

console.log(new A());

class B {
  constructor() {
    console.log("constructor");
  }
}

console.log(new B());

class C {
  constructor(name, age) {
    console.log("constructor", name, age);
  }
}

console.log(new C("Mark", 37));
console.log(new C());
