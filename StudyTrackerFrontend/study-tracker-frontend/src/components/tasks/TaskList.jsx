

function TaskList({tasks, onTaskClick}){
    return(
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Subject</th>
                </tr>
            </thead>
            <tbody>
                {tasks.map(task =>(
                    <tr key = {task.id} onClick ={() => onTaskClick(task)} >
                        <td>{task.name}</td>
                        <td>{task.status}</td>
                        <td>{task.subject}</td>
                    </tr> 
                ))}
            </tbody>
        </table>
    )
}
export default TaskList