function c(callback) {
  setTimeout(() => {
    callback();
  }, 1000);
}

c(() => {
  console.log("1000ms 후에 callback 함수가 실행됩니다.");
});

c(() => {
  c(() => {
    c(() => {
      console.log("3000ms 후에 callback 함수가 실행됩니다.");
    });
  });
});
