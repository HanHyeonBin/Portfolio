// for of
for (const i of [1, 2, 3]) {
  console.log(i);
}

// for in

Object.prototype.test = function () {};

for (const i in { a: 1, b: 2, c: 3 }) {
  console.log(i);
}
