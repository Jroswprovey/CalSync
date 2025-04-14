import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
// Alex 
//  receive the Google token in your React frontend and send it to your Spring Boot backend.
import { GoogleOAuthProvider } from "@react-oauth/google";
// import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  // insert google client id - alex 
  <GoogleOAuthProvider clientId="1085714409158-abcxyz123.apps.googleusercontent.com">
    <App />
  </GoogleOAuthProvider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
