import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    if (!email || !password) {
      alert("Please enter email and password.");
      return;
    }

    const credentials = { email, password };

    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(credentials),
      });

      if (response.ok) {
        const data = await response.json();
        alert(data.message || "Login successful!");
        // localStorage.setItem("token", data.token); // Uncomment if using auth tokens
        navigate("/dashboard");
      } else {
        const errorText = await response.text();
        alert("Login failed: " + errorText);
      }
    } catch (error) {
      console.error("Error logging in:", error);
      alert("An error occurred while logging in.");
    }
  };

  return (
    <div className="auth-container">
      <img src="/app-logo.png" alt="App Logo" style={{ width: 50, marginBottom: 20 }} />
      <h2>Login</h2>

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

      <button className="btn" onClick={handleLogin}>
        Login
      </button>

      <p>
        Don't have an account? <Link to="/signup">Sign up</Link>
      </p>
    </div>
  );
};

export default LoginPage;





