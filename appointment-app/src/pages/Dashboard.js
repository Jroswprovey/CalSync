// Dashboard.js
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Calendar from "react-calendar";
import 'react-calendar/dist/Calendar.css';
import './Dashboard.css';

const Dashboard = () => {
  const navigate = useNavigate();
  const [date, setDate] = useState(new Date());
  const [showOptions, setShowOptions] = useState(false);
  const [showForm, setShowForm] = useState(false);
  const [meetingType, setMeetingType] = useState("");
  const [duration, setDuration] = useState(30);

  const handleLogout = () => {
    navigate("/");
  };

  return (
    <div className="dashboard-layout">
      <header className="dashboard-header">
        <div className="header-left">
          <img src="/app-logo.png" alt="Logo" className="logo" />
          <h1 className="app-title">OptoMeet</h1>
        </div>
        <nav className="nav-links">
          <a href="#" className="nav-link">Home</a>
          <a href="#" className="nav-link">Appointments</a>
          <a href="#" className="nav-link">Profile</a>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </nav>
      </header>

      <main className="dashboard-body">
        <aside className="sidebar">
          <h2 className="sidebar-title">My Schedule</h2>
          <Calendar onChange={setDate} value={date} className="calendar-widget" />
          <p className="selected-date">📅 {date.toDateString()}</p>

          <div className="appointment-actions">
            <button className="primary-btn" onClick={() => setShowOptions(true)}>+ New Appointment</button>

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

          {showForm && (
            <div className="modal-overlay" onClick={() => setShowForm(false)}>
              <div className="modal" onClick={(e) => e.stopPropagation()}>
                <h3>{meetingType} - Duration</h3>
                <label>Duration: {duration} minutes</label>
                <input
                  type="range"
                  min="15"
                  max="60"
                  step="15"
                  value={duration}
                  onChange={(e) => setDuration(e.target.value)}
                />
                <div className="modal-buttons">
                  <button onClick={() => setShowForm(false)}>Cancel</button>
                  <button onClick={() => {
                    console.log(`Creating ${meetingType} for ${duration} minutes`);
                    setShowForm(false);
                  }}>
                    Confirm
                  </button>
                </div>
              </div>
            </div>
          )}
        </aside>

        <section className="main-content">
          <h2>Upcoming Appointments</h2>
          <ul className="appointments-list">
            <li>
              <div className="appointment-card">
                <span className="appointment-date">April 10, 2025</span>
                <span className="appointment-detail">10:00 AM with Dr. Chan</span>
              </div>
            </li>
            <li>
              <div className="appointment-card">
                <span className="appointment-date">April 15, 2025</span>
                <span className="appointment-detail">2:30 PM with Dr. Sok</span>
              </div>
            </li>
          </ul>
        </section>
      </main>
    </div>
  );
};

export default Dashboard;