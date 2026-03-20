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
        <div className="card modal-card" onClick={e => e.stopPropagation()}>
            <div className="modal-header">
                <span >Edit Task</span>
                <button type="button" className="button-basic" onClick={onClose}>X</button>
            </div>
            <form onSubmit ={submit}>
                <div className="form-group">   
                    <label className="modal-label">TASK NAME</label>
                    <input className="form-input" type = "text" value = {name} placeholder="e.g. Assignment 1"
                        onChange={e=> setName(e.target.value)} />
                </div>
                <div className="form-group">   
                    <label className="modal-label">SUBJECT</label>
                    <input className="form-input" type = "text" placeholder="e.g. Math"
                    value = {subject}
                    onChange={e=> setSubject(e.target.value)} />
                </div>

                <label className="modal-label">Status</label>
                <div style={{ display: 'flex', gap: '8px' }}>
                    
                    <input type="radio" id="not-started" value="NOT_STARTED"
                        checked={status === "NOT_STARTED"}
                        onChange={e => setStatus(e.target.value)}/>
                    <label htmlFor="not-started" className="pill todo-pill">Not Started</label>
                    <input type="radio" id="in-progress" value="IN_PROGRESS"
                        checked={status === "IN_PROGRESS"}
                        onChange={e => setStatus(e.target.value)}/>
                    <label htmlFor="in-progress" className="pill inprogress-pill">In Progress</label>
                    <input type="radio" id="completed" value="COMPLETED"
                        checked={status === "COMPLETED"}
                        onChange={e => setStatus(e.target.value)}/>
                    <label htmlFor="completed" className="pill completed-pill">Done</label>
                </div>
                <div className="form-group">   
                    <label className="modal-label">DESCRIPTION</label>
                    <textarea className="form-input form-description" type = "text" placeholder="Any notes or details..."
                    value = {description}
                    onChange={e=> setDescription(e.target.value)} />
                </div>
                    <button style={{marginTop: 'auto'}} className="button" type = "submit">Submit</button>
            </form>
            {error && <p>{error}</p>}
            
        </div>
    </div>)
}
export default EditTaskForm