import {useEffect, useState} from "react";
import {Navigate} from "react-router-dom";
function ProtectedRoute({children}){
    const [authenticated, setAuthenciated] = useState(false);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("")
    useEffect(()=>{
        fetch("http://localhost:8080/users/me", {
            credentials: "include"
        }).then(res => {
            if(!res.ok){
                setAuthenciated(false);
                setLoading(false);

            }else{
                setAuthenciated(true);
                setLoading(false);
            }
        }).catch(()=> setError("Unexpected Error Occured"))
        
    },[])
    if(loading) return <p>"Loading"</p>;
    if(!authenticated) return <Navigate to ="/login"/>;
    if(error)return <p>{error}</p>
    else return children;
}
export default ProtectedRoute