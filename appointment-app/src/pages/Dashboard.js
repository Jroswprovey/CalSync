import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Calendar, dateFnsLocalizer } from "react-big-calendar";
import format from "date-fns/format";
import parse from "date-fns/parse";
import startOfWeek from "date-fns/startOfWeek";
import getDay from "date-fns/getDay";
import "react-big-calendar/lib/css/react-big-calendar.css";
import "./Dashboard.css";

const locales = {
  "en-US": require("date-fns/locale/en-US"),
};

const localizer = dateFnsLocalizer({
  format,
  parse,
  startOfWeek,
  getDay,
  locales,
});

const Dashboard = () => {
  const navigate = useNavigate();
  const [showOptions, setShowOptions] = useState(false);
  const [showForm, setShowForm] = useState(false);
  const [meetingType, setMeetingType] = useState("");
  const [duration, setDuration] = useState(30);
  const [location, setLocation] = useState("In-Person");
  const [participants, setParticipants] = useState([
    { firstName: "", lastName: "", email: "" },
  ]);
  const [appointments, setAppointments] = useState([]);

  const handleLogout = () => navigate("/");

  const handleParticipantChange = (index, field, value) => {
    const updated = [...participants];
    updated[index][field] = value;
    setParticipants(updated);
  };

  const handleAddParticipant = () => {
    setParticipants([
      ...participants,
      { firstName: "", lastName: "", email: "" },
    ]);
  };

  const handleConfirm = () => {
    const startDate = new Date();
    const endDate = new Date(startDate.getTime() + duration * 60000);

    const title = `${meetingType} @ ${location}`;
    const newAppointment = {
      title,
      start: startDate,
      end: endDate,
      participants,
      location,
    };

    setAppointments([...appointments, newAppointment]);
    setShowForm(false);
    setParticipants([{ firstName: "", lastName: "", email: "" }]);
    setLocation("In-Person");
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <img src="/logo192.png" alt="Logo" style={{ width: 40 }} />
        <button className="btn logout-btn" onClick={handleLogout}>Logout</button>
      </div>

      <h2>Welcome to Your Dashboard</h2>
      <p>Book, reschedule, or follow up on your appointments.</p>

      <div className="dashboard-section">
        <h3>📅 Calendar</h3>
        <Calendar
          localizer={localizer}
          events={appointments}
          startAccessor="start"
          endAccessor="end"
          style={{ height: 500 }}
        />
      </div>

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

      {showForm && (
        <div className="modal-overlay" onClick={() => setShowForm(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h3>{meetingType} - Set Duration</h3>
            <label>Duration: {duration} minutes</label>
            <input
              type="range"
              min="0"
              max="60"
              value={duration}
              onChange={(e) => setDuration(Number(e.target.value))}
            />

            <h4>Location</h4>
            <select value={location} onChange={(e) => setLocation(e.target.value)}>
              <option value="In-Person">In-Person</option>
              <option value="Phone Call">Phone Call</option>
              <option value="Google Meet">Google Meet</option>
            </select>

            <h4>Participants</h4>
            {participants.map((p, idx) => (
              <div key={idx} className="participant-input">
                <input
                  type="text"
                  placeholder="First Name"
                  value={p.firstName}
                  onChange={(e) =>
                    handleParticipantChange(idx, "firstName", e.target.value)
                  }
                />
                <input
                  type="text"
                  placeholder="Last Name"
                  value={p.lastName}
                  onChange={(e) =>
                    handleParticipantChange(idx, "lastName", e.target.value)
                  }
                />
                <input
                  type="email"
                  placeholder="Email"
                  value={p.email}
                  onChange={(e) =>
                    handleParticipantChange(idx, "email", e.target.value)
                  }
                />
              </div>
            ))}
            {meetingType === "Group Meeting" && (
              <button onClick={handleAddParticipant}>➕ Add Another Person</button>
            )}

            <div className="modal-buttons">
              <button onClick={() => setShowForm(false)}>Cancel</button>
              <button onClick={handleConfirm}>Confirm</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Dashboard;

