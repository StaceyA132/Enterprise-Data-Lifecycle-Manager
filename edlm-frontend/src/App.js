
import './App.css';
import Login from './Login';

function App() {
  // Placeholder for login state
  const handleLogin = (email) => {
    alert(`Welcome, ${email}!`);
  };
  return (
    <div className="App">
      <Login onLogin={handleLogin} />
    </div>
  );
}

export default App;
