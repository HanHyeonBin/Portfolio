const hello2 = () => {
    const age = 37;
    console.log(age);
};

hello2();

//var 함수 스코프

var a = 0;

(function(){
    a++;
    console.log(a)
})();

console.log(a);

(function(){
    var b = 0;
    console.log(b);
})();

b++;
console.log(b);