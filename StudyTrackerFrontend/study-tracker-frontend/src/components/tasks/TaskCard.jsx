

function TaskCard({task, onExitClick, onEditClick, onDeleteClick}){
    return(
    <div><div>
        <h1>{task.name}</h1>
        <button onClick={onExitClick}>Exit</button> {/*want this to be in the top right corner*/}
        <ul>
            <li>{task.status}</li>
            <li>{task.subject}</li>
            <li>{task.description}</li>
        </ul>
        <button onClick={onEditClick}>Edit</button> {/*want these buttons to be next to each other*/}
        <button onClick={onDeleteClick}>Delete</button>
    </div></div>)
}
export default TaskCard