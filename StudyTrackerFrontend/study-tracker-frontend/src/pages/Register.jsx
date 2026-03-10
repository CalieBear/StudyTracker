import {useState} from "react";
import {useNavigate} from 'react-router-dom';

function Register(){
    const [username, setUsername]=useState("");
    const [password, setPassword]=useState("");
    const [retypedPassword, setRetypedPassword]=useState("");
    const [email, setEmail]=useState("");
    const [error, setError]=useState("");
    const navigate = useNavigate();

    function submit(e){
        e.preventDefault();
        if(!username || !password){
            setError("Username and password are required");
            return;
        }else if(password!=retypedPassword){
            setError("Passwords don't match");
            return;
        }
        fetch("http://localhost:8080/users", {
            method:"POST",
            credentials: "include",
            headers:{ "Content-Type": "application/json"},
            body: JSON.stringify({
                username,password,
                email: email || null
            })
        })
            .then(res => 
                {if(res.ok){
                    navigate('/login');
                }else{
                    setError("Credentals Invalid");
                }
            })
            .catch(err => setError("Registration Error"));
    }
    return(<div>
        <form onSubmit={submit}>
            <label>Username
                <input type = "text"
                value = {username}
                onChange={e=> setUsername(e.target.value)}
                />
            </label>
            <label>Password
                <input type = "password"
                value = {password}
                onChange={e=> setPassword(e.target.value)}
                />
            </label>
            <label>Retype Password
                <input type = "password"
                value = {retypedPassword}
                onChange={e=> setRetypedPassword(e.target.value)}
                />
            </label>
            <label>Email
                <input type = "email"
                value = {email}
                onChange={e=> setEmail(e.target.value)}
                />
            </label>
            <button type = "submit">Submit</button>
        </form>
        {error && <p>{error}</p>}
    </div>)
}
export default Register