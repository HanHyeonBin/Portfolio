// get,set

class A {
  _name = "no name";

  get name() {
    return this._name;
  }

  set name(value) {
    this._name = value + "!!!";
  }
}

const a = new A();
console.log(a);

//setter 의 사용법
a.name = "Mark"; // set 함수를 설정해서 가능
console.log(a);
console.log(a.name);
console.log(a._name);

// readonly

class B {
  _name = "no name";

  get name() {
    return this._name + "@@@";
  }
}

const b = new B();
console.log(b);
b.name = "Mark";
console.log(b);
