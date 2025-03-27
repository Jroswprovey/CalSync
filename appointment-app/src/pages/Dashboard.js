import React from "react";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    navigate("/");
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <img src="/logo192.png" alt="Logo" style={{ width: 40 }} />
        <button className="btn logout-btn" onClick={handleLogout}>Logout</button>
      </div>
      <h2>Welcome to Your Dashboard</h2>
      <p>Book, reschedule, or follow up on your appointments.</p>
      <p>Track your points and redeem them for discounts.</p>
    </div>
  );
};

export default Dashboard;
