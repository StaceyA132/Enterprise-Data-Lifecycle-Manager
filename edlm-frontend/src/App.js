import './App.css';
import Login from './Login';
import Dashboard from './Dashboard';
import { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate, useNavigate } from 'react-router-dom';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(() => {
    return !!localStorage.getItem('authToken');
  });

  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/login" element={<LoginWrapper setIsLoggedIn={setIsLoggedIn} />} />
          <Route path="/dashboard" element={isLoggedIn ? <Dashboard /> : <Navigate to="/login" />} />
          <Route path="*" element={<Navigate to={isLoggedIn ? "/dashboard" : "/login"} />} />
        </Routes>
      </div>
    </Router>
  );
}

function LoginWrapper({ setIsLoggedIn }) {
  const navigate = useNavigate();
  const handleLogin = (email) => {
    setIsLoggedIn(true);
    navigate('/dashboard');
  };
  return <Login onLogin={handleLogin} />;
}

export default App;
