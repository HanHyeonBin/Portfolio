// 멤버 함수

class A {
  hello1() {
    console.log("hello1", this);
  }

  hello2 = () => {
    console.log("hello2", this);
  };
}

new A().hello1();
new A().hello2();

class B {
  name = "Mark";

  hello() {
    console.log("hello", this.name);
  }
}

new B().hello();
