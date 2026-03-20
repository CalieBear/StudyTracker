import '../../styles/Tasks.css';

function TaskCard({task, onEditClick, onDeleteClick}){
    function getPill(task){
        if(task.status === "NOT_STARTED"){
            return <span className='pill todo-pill'>Not Started</span>
        }else if(task.status === "IN_PROGRESS"){
            return <span className='pill inprogress-pill'>In Progress</span>
        }else if(task.status === "COMPLETED"){
            return <span className='pill completed-pill'>Completed</span>
        }
    }

    if(task==null){
    return(<div className="card" style={{height: '100%'}} >
        <div>
            <h1 className="header details-title">Task Details</h1>
        </div>
        <hr/>
        <div className="field" >
            <p className="label field-label">NAME </p>
            <p className="text"></p> 
        </div>
        <div className="field" >
            <p className="label field-label">STATUS </p>
            <p className="text"> </p>
        </div>
        <div className="field">
            <p className="label field-label">SUBJECT </p>
            <p className="text"> </p>
        </div>
        <div className="field"> 
            <p className="label field-label">DESCRIPTION </p>
            <p className="text"> </p>
        </div>
        <hr/>
        <div style={{marginTop: 'auto',display:'flex',gap:'1rem'}}> 
            <button className="button" >Edit</button> 
            <button className="button button-delete" >Delete</button>
        </div>
    </div>)
    }
    return(
    <div className="card" style={{height: '100%',paddingLeft:'1.5rem', paddingRight:'1.5rem'}}>
        <div className="card-header">
            <h1 className="header  details-title">Task Details</h1>
            <span className="detail-subject-tag">{task.subject||""}</span>
        </div>
        <hr/>
        <div className="field">
            <p className="label field-label">NAME </p>
            <p className="text">{task.name ||"" }</p> 
        </div>
        <div className="field">
            <p className="label field-label">STATUS</p>
            <p className="text">{getPill(task)||""}</p>
        </div>
        <div className="field">
            <p className="label field-label">SUBJECT</p>
            <p className="text">{task.subject||""}</p>
        </div>
        <div className="field"> 
            <p className="label field-label">DESCRIPTION</p>
            <p className="field-desc">{task.description||<span style={{opacity:'0.45'}}>No description added yet.</span>}</p>
        </div>
        <hr/>
        <div style={{marginTop: 'auto',display:'flex',gap:'1rem'}}> 
            <button className="button" onClick={onEditClick}>Edit</button> 
            <button className="button button-delete" onClick={onDeleteClick}>Delete</button>
        </div>
    </div>)
}
export default TaskCard