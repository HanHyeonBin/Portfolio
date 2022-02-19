import React, { useRef } from "react";
import UserList from "./UserList";

function App() {
  const users = [
    // 배열을 만들때 Key값을 줘야 나중에 추가,삭제 등 관리할때 용이하다.
    {
      id: 1,
      username: "Han",
      email: "123@gmail.com",
    },
    {
      id: 2,
      username: "Hyeon",
      email: "456@gmail.com",
    },
    {
      id: 3,
      username: "Bin",
      email: "789@gmail.com",
    },
  ];

  const nextId = useRef(4);

  const onCreate = () => {
    console.log(nextId.current); //4
    nextId.current += 1;
  };

  return <UserList users={users} />;
}

export default App;
