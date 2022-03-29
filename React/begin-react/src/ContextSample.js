import React, { createContext, useContext, useState } from "react";

const MyContext = createContext("defaultValue");

function Child() {
  const text = useContext(MyContext);
  return <div>안녕하세요? {text}</div>;
}

function Parent() {
  //   const text2 = useContext(MyContext);
  //   return <div>안녕하세요?? {text2}</div>;
  return <Child />;
}

function GrandParent() {
  return <Parent />;
}

// const GrandParent = () => {
//   return <Parent />;
// };

function ContextSample() {
  const [value, setValue] = useState(true);
  return (
    <MyContext.Provider value={value ? "Good" : "Bad"}>
      {/* <Child /> */}
      <GrandParent />
      <button onClick={() => setValue(!value)}>Click Me</button>
    </MyContext.Provider>
  );
}

export default ContextSample;
