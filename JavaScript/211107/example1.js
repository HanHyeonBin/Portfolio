// 함수 먼저
function hello1() {
    console.log('hello1');
}

hello1();

//함수의 호출을 먼저

hello2();

function hello2() {
    console.log('hello2');
}

//var 변수 사용을 먼저하고 변수를 선언함

age = 6;
age++;
console.log(age);

var age;

console.log(name);

name = 'Mark';

console.log(name);

var name = 'HyeonBin';

console.log(name);

name = 'Mark';

//console.log(name1);

//let name1;
