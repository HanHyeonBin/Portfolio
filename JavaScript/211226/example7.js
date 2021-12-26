// 표준 내장 객체 : Array

const a = new Array("red", "black", "white");

console.log(a, typeof a);
console.log(a instanceof Array);
console.log(a instanceof Object);

const b = ["red", "green", "yello"]; //리터럴 표현법

console.log(b, typeof b);
console.log(b instanceof Array);
console.log(b instanceof Object);

console.log(b.slice(0, 1)); //slice는 Array에 있는 기능 -> slice(0,1) 해당 Array에 0번째부터 1개만 가져오겠다는 뜻
console.log(Array.prototype.slice, Object.prototype.slice);
