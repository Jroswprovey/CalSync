import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

    const handleLogin = async () => {
        if (email && password) {
            try {
                const response = await fetch("http://localhost:8080/api/auth/login", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ email, password }),
                });

                if (response.ok) {
                    const data = await response.json();
                    // Optionally, store the token if needed
                    // localStorage.setItem("token", data.token);
                    alert(data.message); // "Login successful"
                    navigate("/dashboard");
                } else {
                    // If the response is not ok, display an error message.
                    const errorText = await response.text();
                    alert("Login failed: " + errorText);
                }
            } catch (error) {
                console.error("Error during login:", error);
                alert("An error occurred during login. Please try again.");
            }
        } else {
            alert("Please enter email and password.");
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
      <button className="btn" onClick={handleLogin}>Login</button>
      <p>
        Don't have an account? <Link to="/signup">Sign up</Link>
      </p>
    </div>
  );
};

export default LoginPage;
