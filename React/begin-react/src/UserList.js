import React from "react";

function User({ user }) {
  return (
    <div>
      <b>{user.username}</b>
      <span>({user.email})</span>
    </div>
  );
}

function UserList() {
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

  return (
    <div>
      {users.map((user) => (
        <User user={user} key={user.id} />
      ))}
    </div>
  );
}

export default UserList;
