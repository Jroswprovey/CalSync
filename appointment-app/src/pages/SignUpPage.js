import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const SignUpPage = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSignUp = async () => {
      if (name && email && password) {


          const credentials = { email, password };

          try {
              const response = await fetch("http://localhost:8080/api/auth/signup", {
                  method: "POST",
                  headers: {
                      "Content-Type": "application/json",
                  },
                  body: JSON.stringify(credentials),
              });

          }catch (error) {
              console.log(error);
          }


      } else {
          alert("Please fill out all fields.");
      }
  };

  return (
    <div className="auth-container">
      <img src="/app-logo.png" alt="App Logo" style={{ width: 50, marginBottom: 20 }} />
      <h2>Sign Up</h2>
      <input
        type="text"
        placeholder="Full Name"
        className="input-field"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <input
        type="email"
        placeholder="Email"
        className="input-field"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        className="input-field"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button className="btn" onClick={handleSignUp}>Sign Up</button>
      <p>
        Already have an account? <Link to="/">Login</Link>
      </p>
    </div>
  );
};

export default SignUpPage;