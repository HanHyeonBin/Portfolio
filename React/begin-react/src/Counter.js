import React, { useReducer } from "react";

function reducer(state, action) {
  switch (action.type) {
    case "INCREMENT": //action 이름 직접 설정 가능
      return state + 1;
    case "DECREMENT":
      return state - 1;
    default:
      throw new Error("Unhandled action");
  }
}

function Counter() {
  const [number, dispatch] = useReducer(reducer, 0); // reducer 함수, 0은 초기 값
  const onIncrease = () => {
    dispatch({
      type: "INCREMENT",
    });
  };

  const onDecrease = () => {
    dispatch({
      type: "DECREMENT",
    });
  };
  return (
    <div>
      <h1>{number}</h1>
      <button onClick={onIncrease}>+1</button>
      <button onClick={onDecrease}>-1</button>
    </div>
  );
}

export default Counter;
