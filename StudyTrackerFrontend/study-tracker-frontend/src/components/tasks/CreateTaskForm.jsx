import "./modalstyles.css";
import {useState} from "react";
function CreateTaskForm({onClose, onTaskSubmit}){
    const [name, setName]=useState("");
    const [subject, setSubject]=useState("");
    const [status, setStatus]=useState("");
    const [description, setDescription]=useState("");
    const [error, setError]=useState("");

    function submit(e){
        e.preventDefault();
        if(!name){
            setError("Name is required");
            return;
        }
        fetch("http://localhost:8080/tasks", {
            method:"POST",
            credentials: "include",
            headers:{ "Content-Type": "application/json"},
            body: JSON.stringify({
                name: name,
                subject: subject || null,
                status: status || "NOT_STARTED",
                description: description || null 
            })
        }).then(res => 
                {if(res.ok){
                    return res.json();
                }else{
                    setError("Task Values Invalid");
                }
            }).then(newTask => {
                if(newTask){
                    onTaskSubmit(newTask);
                    onClose();
                }
            })
            .catch(err => setError("Task Creation Error"));
    }

    return(
    <div className="backdrop" onClick={onClose}>
        <div className="card modal-card" onClick={e => e.stopPropagation()}>
            <div>
                <h2 style = {{fontSize: ' 18px', fontWeight: '600'}}>New Task</h2>
                {/* exit button goes here */}
            </div>
            <form onSubmit ={submit}>
                <label><br/>TASK NAME<br/>
                    <input type = "text"
                    value = {name}
                    onChange={e=> setName(e.target.value)} />
                </label>
                <label><br/>SUBJECT<br/>
                    <input type = "text"
                    value = {subject}
                    onChange={e=> setSubject(e.target.value)} />
                </label>
                <p className="label">STATUS</p>
                <select
                    value = {status}
                    onChange ={e=>setStatus(e.target.value)}
                >
                    <option value="">Select Status</option>
                    <option value="NOT_STARTED">Not Started</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="COMPLETED">Done</option>
                </select>
                <label><br/>DESCRIPTION<br/>
                    <input type = "text"
                    value = {description}
                    onChange={e=> setDescription(e.target.value)} />
                </label>
                <br/>
                <button type = "submit">Submit</button>
            </form>
            {error && <p>{error}</p>}
            <button onClick={onClose}>Exit</button>
        </div>
    </div>)
}
export default CreateTaskForm