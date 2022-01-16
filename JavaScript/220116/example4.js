// static 상속

class Parent {
  static age = 37;
}

class Child extends Parent {}

console.log(Parent.age, Child.age);
