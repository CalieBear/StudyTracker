import {useState} from "react";
import {useNavigate} from 'react-router-dom';

function Login(){
    const [username, setUsername] = useState("");
    const [password,setPassword] = useState("");
    const [error,setError] = useState("");
    const navigate = useNavigate();

    function submit(e){
        e.preventDefault();
        if(!username || !password){
            setError("Username and Password are Required");
        }
        fetch("http://localhost:8080/login", {
            method:"POST",
            credentials: "include",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                username: username,
                password: password
            })
            })
              .then(res => 
                {if(res.ok){
                    navigate('/dashboard');
                }else{
                    setError("Login Credentals Invalid");
                }
                })
              .catch(err => setError("Login Error"));
          }
    return(
        <div>
            <form onSubmit={submit}>
                <label>Username
                    <input type ="text"
                    value = {username}
                    onChange={e => setUsername(e.target.value)}
                    />
                </label>
                <label>Password
                    <input type ="password"
                    value = {password}
                    onChange={e => setPassword(e.target.value)}
                    />
                </label>
                <button type = "submit">Submit</button>
            </form>
            {error && <p>{error}</p>}
        </div>
    )
}
export default Login