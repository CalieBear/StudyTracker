//todo
//display tasks
//create tasks
//delete tasks
//edit tasks
//find tasks by filter

import { useEffect,useState } from "react"
import TaskList from "../components/tasks/TaskList";
import TaskCard from "../components/tasks/TaskCard";
import CreateTaskForm from "../components/tasks/CreateTaskForm";
import EditTaskForm from "../components/tasks/EditTaskForm";

function Tasks(){
    const [tasks, setTasks]= useState([]);
    const [filter, setFilter] = useState({subject: "", status:""});
    const [error, setError]= useState("");

    const [selectedTask, setSelectedTask] =  useState(null);
    const [showTaskCard, setShowTaskCard] =  useState(false);
    const [showCreateForm, setShowCreateForm] =  useState(false);
    const [showEditForm, setShowEditForm] =  useState(false);

    function openTaskCard(task){
        setSelectedTask(task);
        setShowTaskCard(true);
        //do i put an if statement in the return?
    }
    function exitTaskCard(){
        setShowTaskCard(false);
        setSelectedTask(null);
    }
    function openEditForm(){
        setShowTaskCard(false);
        setShowEditForm(true);
    }
    function exitEditForm(){
        setShowEditForm(false);
        setSelectedTask(null);
    }
    function openCreateForm(){
        setShowCreateForm(true);
    }
    function exitCreateForm(){
        setShowCreateForm(false);
    }
    function taskCreated(newTask){
        if(newTask!=null){
            setTasks([...tasks,newTask])
        }
    }

    function deleteTask(taskId){
        fetch("http://localhost:8080/tasks/"+taskId,{
            method: "DELETE",
            credentials: "include"
        })
        .then(res => {
            if(res.ok){
                setShowTaskCard(false);
                setSelectedTask(null);
                setTasks(tasks.filter(task=> task.id !== taskId));
            }else{
                setError("Failed to delete task");
            }
        })
        .catch(()=> setError("Unexpected Error Occured"));
    }
    

    useEffect(()=>{
        fetch("http://localhost:8080/tasks",{
            credentials:"include"
        }).then (res=> res.json())
        .then (data =>setTasks(data))
        .catch(()=> setError("Unexepcted Error Occured"))
    },[])//want this to run when filter changes and when first loaded, [] for first loaded, and when task is created or edited 



    return <div>
        <TaskList tasks = {tasks} onTaskClick={openTaskCard} />
        {showTaskCard && <TaskCard task = {selectedTask} onExitClick = {exitTaskCard} onEditClick = {openEditForm} onDeleteClick ={()=> deleteTask(selectedTask.id)}/>}
        {showEditForm && <EditTaskForm/>}
        {showCreateForm && <CreateTaskForm onClose = {exitCreateForm} onTaskSubmit={taskCreated}/>}
        <button onClick={openCreateForm}>Create</button>
    </div>
}
export default Tasks