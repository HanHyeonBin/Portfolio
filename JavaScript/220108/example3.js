// 멤버 변수

class A {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
}

console.log(new A("Mark", 37));

// class field 는 런타임 확인 node 12버전 이상

class B {
  name; // this.name과 같음
  age; // this.age와 같음
}

console.log(new B());

class C {
  name = "no name";
  age = 0;

  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
}

console.log(new C("Mark", 26));
