import '../../styles/Tasks.css';
import {useState} from "react";
function TaskList({tasks,selectedTask, onTaskClick}){

    function getPill(task){
        if(task.status === "NOT_STARTED"){
            return <span className='list-pill pill todo-pill'><span className='pill-dot'/>Not Started</span>
        }else if(task.status === "IN_PROGRESS"){
            return <span className='list-pill pill inprogress-pill'><span className='pill-dot'/>In Progress</span>
        }else if(task.status === "COMPLETED"){
            return <span className='list-pill pill completed-pill'><span className='pill-dot'/>Completed</span>
        }
    }
    return(<div className='card' style={{position:'relative',padding:'0'}}>
        <div className='bookmark'/>
        <div className='task-table-header'>
            <span>Name</span>
            <span>Subject</span>
            <span>Status</span>
        </div>
        {tasks.map(task =>(
                    <div className= {`task-row ${selectedTask?.id === task.id ? 'active' : ''}`}
                        key = {task.id} onClick ={() => onTaskClick(task)}>
                        <span className='task-name'>{task.name}</span>
                        <span className='task-subject'>{task.subject}</span>
                        <span>{getPill(task)}</span>
                    </div>
                ))}
    </div>)
}
export default TaskList