import '../../styles/Tasks.css';
import {useState} from "react";
function TaskList({tasks, onTaskClick}){

    function getPill(task){
        if(task.status === "NOT_STARTED"){
            return <td className='tableText todo-pill'>Not Started</td>
        }else if(task.status === "IN_PROGRESS"){
            return <td className='tableText inprogress-pill'>In Progress</td>
        }else if(task.status === "COMPLETED"){
            return <td className='tableText completed-pill'>Completed</td>
        }
    }
    return(
        <div className='card'>
        <table style = {{width: '100%', tableLayout: 'fixed'}}>
            <thead>
                <tr>
                    <th className='header'>Name</th>
                    <th className='header'>Subject</th>
                    <th className='header'>Status</th>
                </tr>
            </thead>
            <tbody>
                {tasks.map(task =>(
                    <tr key = {task.id} onClick ={() => onTaskClick(task)} >
                        <td className='tableText'>{task.name}</td>
                        <td className='tableText'>{task.subject}</td>
                        {getPill(task)}
                    </tr> 
                ))}
            </tbody>
        </table>
        </div>
    )
}
export default TaskList