import './App.css'
import { useEffect } from "react"
import {
    BrowserRouter,
    Routes,
    Route,
} from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Tasks from "./pages/Tasks";
import ProtectedRoute from"./components/common/ProtectedRoute";

function App() {
  
  
  //below is used for testing
  useEffect(() => {
    fetch("http://localhost:8080/tasks", {
      credentials: "include" // needed for session cookies
    })
      .then(res => console.log("Status:", res.status))
      .catch(err => console.log("Error:", err))
  }, [])

  return (<div>
    <BrowserRouter>
      <Routes>
        <Route 
          path="/login"
          element={<Login />}
        />
        <Route 
          path="/register"
          element={<Register />}
        />
        <Route 
          path="/dashboard"
          element={<ProtectedRoute><Dashboard /></ProtectedRoute>}
        />
        <Route 
          path="/tasks"
          element={<ProtectedRoute><Tasks /></ProtectedRoute>}
        />
      </Routes>
    </BrowserRouter>
  </div>)
}

export default App
