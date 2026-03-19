import '../../styles/Tasks.css';

function TaskCard({task, onEditClick, onDeleteClick}){

    if(task==null){
    return(<div className="card" style={{height: '100%'}} >
        <div>
            <h1 className="header details-title">Task Details</h1>
            {/* <p> </p> subject goes here */}
        </div>
        <hr/>
        <div className="field" >
            <p className="label field-label">NAME </p>
            <p className="text"> </p> 
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
        <div style={{marginTop: 'auto'}}> 
            <button className="button" >Edit</button> 
            <button className="button button-delete" >Delete</button>
        </div>
    </div>)
    }
    return(
    <div className="card" style={{height: '100%',paddingLeft:'20px', paddingRight:'20px'}}>
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
            <p className="text">{task.status||""}</p>
        </div>
        <div className="field">
            <p className="label field-label">SUBJECT</p>
            <p className="text">{task.subject||""}</p>
        </div>
        <div className="field"> 
            <p className="label field-label">DESCRIPTION</p>
            <p className="field-desc">{task.description||"No description added yet."}</p>
        </div>
        <hr/>
        <div style={{marginTop: 'auto'}}> 
            <button className="button" onClick={onEditClick}>Edit</button> 
            <button className="button button-delete" onClick={onDeleteClick}>Delete</button>
        </div>
    </div>)
}
export default TaskCard