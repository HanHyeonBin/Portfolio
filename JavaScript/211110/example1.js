// 표현식 && 표현식

if (true && true){
    console.log('두개 모두 참이면 ');
}

if (true && false){
    console.log('한개만 참이면 거짓');
}

if (false && false){
    console.log('두개 모두 거짓이면 거짓');
}

// 표현식 || 표현식

if (true || true) {
    console.log('두개 모두 참이면 참');
}

if (false || true) {
    console.log('한개만 참이면 참');
}

if (false || false) {
    console.log('두개 모두 거짓이면 거짓');
}

// !표현식

if (!true){
    console.log('참이면 거짓');
}

if (!false){
    console.log('거짓이면 참');
}