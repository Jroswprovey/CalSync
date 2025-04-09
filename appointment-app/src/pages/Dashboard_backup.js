
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Calendar from "react-calendar";
import 'react-calendar/dist/Calendar.css';
import './Dashboard.css';

const Dashboard = () => {
  const navigate = useNavigate();
  const [date, setDate] = useState(new Date());
  const [showOptions, setShowOptions] = useState(false);
  const [showForm, setShowForm] = useState(false); // For "Create Button"
  const [meetingType, setMeetingType] = useState(""); // "One-on-One" or "Group"
  const [duration, setDuration] = useState(30); // 30 minutes default



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
      <p>Easy scheduling ahead</p>


      {/* Calendar section in Dashboard - Alex */}
      <div className="dashboard-section">
        <h3>📆 Your Schedule</h3>
        <Calendar onChange={setDate} value={date} />
        <p>Selected date: {date.toDateString()}</p>
      </div>

{/* "Create" Button (to create a one-one appointment or group appointment) - Alex */}
<div className="dashboard-section">
  <button className="create-btn" onClick={() => setShowOptions(true)}>
    ➕ Create
  </button>

  {showOptions && (
  <div className="create-options">
    <button
      className="option-btn"
      onClick={() => {
        setMeetingType("One-on-One");
        setShowForm(true);
        setShowOptions(false);
      }}
    >
      One-on-One Meeting
    </button>

    <button
      className="option-btn"
      onClick={() => {
        setMeetingType("Group Meeting");
        setShowForm(true);
        setShowOptions(false);
      }}
    >
      Group Meeting
    </button>
  </div>
)}
  </div> 

  {/* Modal for meeting duration (CREATING AN APPOINTMENT) - Alex  */}
  {showForm && (
  <div
    className="modal-overlay"
    onClick={() => setShowForm(false)} // closes when background is clicked
  >
    <div
      className="modal"
      onClick={(e) => e.stopPropagation()} // prevents closing when clicking inside the box
    >
            <h3>{meetingType} - Set Duration</h3>
            <label>Duration: {duration} minutes</label>
            <input
              type="range"
              min="0"
              max="60"
              value={duration}
              onChange={(e) => setDuration(e.target.value)}
            />
            <div className="modal-buttons">
              <button onClick={() => setShowForm(false)}>Cancel</button>
              <button onClick={() => {
                console.log(`Creating ${meetingType} for ${duration} min`);
                setShowForm(false);
              }}>
                Confirm
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Dashboard;




  
