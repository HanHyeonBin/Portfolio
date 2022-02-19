import React, { useRef, useState } from "react";
import UserList from "./UserList";
import CreateUser from "./CreateUser";

function App() {
  const [inputs, setInputs] = useState({
    username: "",
    email: "",
  });
  const { username, email } = inputs;
  const onChange = (e) => {
    const { name, value } = e.target;
    setInputs({
      ...inputs,
      [name]: value,
    });
  };
  const [users, setUsers] = useState([
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
  ]);

  const nextId = useRef(4);

  const onCreate = () => {
    const user = {
      id: nextId.current,
      username,
      email,
    };
    setUsers(users.concat(user));
    setInputs({
      username: "",
      email: "",
    });
    console.log(nextId.current); //4
    nextId.current += 1;
  };

  return (
    <>
      <CreateUser
        username={username}
        email={email}
        onChange={onChange}
        onCreate={onCreate}
      />
      <UserList users={users} />
    </>
  );
}

export default App;
