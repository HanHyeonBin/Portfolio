// static 변수, 함수

class A {
  static age = 37;
  static hello() {
    console.log(A.age);
  }
}

console.log(A, A.age);

A.hello();

class B {
  age = 37;
  static hello() {
    console.log(this.age);
  }
}

console.log(B, B.age);
B.hello();
//new B().hello();

class C {
  static name = "이 클래스의 이름을 C 가 아니다."; //static name 변수는 클래스 명을 변경할 수 있음.
}

console.log(C);
