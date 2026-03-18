import "./modalstyles.css";
import {useState } from "react"
function EditTaskForm({onClose, onTaskSubmit, task}){
    const [name, setName]=useState(task.name);
    const [subject, setSubject]=useState(task.subject);
    const [status, setStatus]=useState(task.status);
    const [description, setDescription]=useState(task.description);
    const [error, setError]=useState("");

    function submit(e){
        e.preventDefault();
        if(!name){
            setError("Name is required");
            return;
        }
        fetch("http://localhost:8080/tasks/"+task.id,{
            method: "PATCH",
            credentials: "include",
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify({
                name:name,
                subject: subject || null,
                status: status || "NOT_STARTED",
                description: description || null 
            })
        }).then(res =>
                {if(res.ok){
                    return res.json();
                }else{
                    setError("Task Values Invalid")
                }}).then(task => {
                    if(task){
                        onTaskSubmit(task);
                        onClose();
                    }
                }).catch(err => setError("Task Edit Error"))
    }

    return(
    <div className="backdrop" onClick={onClose}>
        <div className="modal-card">
            <form onSubmit ={submit}>
                <label>Name
                    <input type = "text"
                    value = {name}
                    onChange={e=> setName(e.target.value)} />
                </label>
                <label>Subject
                    <input type = "text"
                    value = {subject}
                    onChange={e=> setSubject(e.target.value)} />
                </label>
                <select
                    value = {status}
                    onChange ={e=>setStatus(e.target.value)}
                >
                    <option value="">Select Status</option>
                    <option value="NOT_STARTED">Not Started</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="COMPLETED">Done</option>
                </select>
                <label>Description
                    <input type = "text"
                    value = {description}
                    onChange={e=> setDescription(e.target.value)} />
                </label>
                <button type = "submit">Submit</button>
            </form>
            {error && <p>{error}</p>}
            <button onClick={onClose}>Exit</button>
        </div>
    </div>)
}
export default EditTaskForm