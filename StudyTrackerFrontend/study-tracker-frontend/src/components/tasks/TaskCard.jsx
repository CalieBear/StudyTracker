import '../../styles/Tasks.css';

function TaskCard({task, onEditClick, onDeleteClick}){

    if(task==null){
    return(<div className="card" >
        <h1 className="header">Task Card</h1>
        <div >
            <p className="label">Name: </p>
            <p className="text"> </p> 
        </div>
        <div >
            <p className="label">Status: </p>
            <p className="text"> </p>
        </div>
        <div >
            <p className="label">Subject: </p>
            <p className="text"> </p>
        </div>
        <div > 
            <p className="label">Description: </p>
            <p className="text"> </p>
        </div>
        <div style={{marginTop: 'auto'}}> 
            <button className="button" >Edit</button> 
            <button className="button button-delete" >Delete</button>
        </div>
    </div>)
    }
    return(
    <div className="card">
        <h1 className="header">Task Card</h1>
        <div >
            <p className="label">Name: </p>
            <p className="text">{task.name ||"" }</p> 
        </div>
        <div>
            <p className="label">Status: </p>
            <p className="text">{task.status||""}</p>
        </div>
        <div>
            <p className="label">Subject: </p>
            <p className="text">{task.subject||""}</p>
        </div>
        <div> 
            <p className="label">Description: </p>
            <p className="text">{task.description||""}</p>
        </div>
        <div style={{marginTop: 'auto'}}> 
            <button className="button" onClick={onEditClick}>Edit</button> 
            <button className="button button-delete" onClick={onDeleteClick}>Delete</button>
        </div>
    </div>)
}
export default TaskCard