//todo
//find tasks by filter

import { useEffect,useState } from "react"
import TaskList from "../components/tasks/TaskList";
import TaskCard from "../components/tasks/TaskCard";
import CreateTaskForm from "../components/tasks/CreateTaskForm";
import EditTaskForm from "../components/tasks/EditTaskForm";

function Tasks(){
    const [tasks, setTasks]= useState([]);
    const [filter, setFilter] = useState({subject: "", status:""});
    const [displayTasks, setDisplayTasks]=useState([]);

    const [selectedTask, setSelectedTask] =  useState(null);
    const [showTaskCard, setShowTaskCard] =  useState(false);
    const [showCreateForm, setShowCreateForm] =  useState(false);
    const [showEditForm, setShowEditForm] =  useState(false);

    //FILTER
    function setFilter(){
        //would it be better to sort them by collumns then have a search box
        
    }

    //TASK CARD
    function openTaskCard(task){
        setSelectedTask(task);
        setShowTaskCard(true);
        //do i put an if statement in the return?
    }
    function exitTaskCard(){
        setShowTaskCard(false);
        setSelectedTask(null);
    }
    //EDIT FORM
    function openEditForm(){
        setShowTaskCard(false);
        setShowEditForm(true);
    }
    function exitEditForm(){
        setShowEditForm(false);
        setSelectedTask(null);
    }
    function taskEdited(task){
        setTasks(tasks.map(t => t.id === task.id ? task : t));
    }
    //CREATE FORM
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
    //DELETE TASK
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
    },[])//NEEDS TO RUN WHEN FILTER CHANGED

    return <div>
        {(!showCreateForm && !showEditForm && !showTaskCard) && <TaskList tasks = {displayTasks} onTaskClick={openTaskCard} /> }
        {showTaskCard && <TaskCard task = {selectedTask} onExitClick = {exitTaskCard} onEditClick = {openEditForm} onDeleteClick ={()=> deleteTask(selectedTask.id)}/>}
        {showEditForm && <EditTaskForm task = {selectedTask} onClose={exitEditForm} onTaskSubmit={taskEdited} />}
        {showCreateForm && <CreateTaskForm onClose = {exitCreateForm} onTaskSubmit={taskCreated}/>}
        {(!showCreateForm && !showEditForm && !showTaskCard) && <button onClick={openCreateForm}>Create</button>}
    </div>
}
export default Tasks