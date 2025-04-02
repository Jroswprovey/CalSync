
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Calendar from "react-calendar";
import 'react-calendar/dist/Calendar.css';
import './Dashboard.css';

const Dashboard = () => {
  const navigate = useNavigate();
  const [date, setDate] = useState(new Date());

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
      <p>Connect with people around the world!.</p>

      {/* Calendar section in Dashboard - Alex */}
      <div className="dashboard-section">
  <h3>📆 Your Schedule</h3>
  <Calendar onChange={setDate} value={date} />
  <p>Selected date: {date.toDateString()}</p>
</div>
</div> 
      
    );
  };

export default Dashboard;
