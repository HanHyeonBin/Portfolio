import React from "react";
import Hello from "./Hello";
import "./App.css";

function App() {
  const name = "react";
  const style = {
    backgroundColor: "black",
    color: "aqua",
    fontSize: 24,
    padding: "1rem",
  };
  return (
    <>
      {/* JSX 주석 사용법은 대괄호 + 별 슬래시 */}
      <Hello
      // 이건또 되네;
      />
      <div style={style}>{name}</div>{" "}
      {/* JS 값을 보여줘야 할때는 {}로 묶어준다 */}
      <div className="gray-box"></div>
    </>
  );
}

export default App;
