
import { useEffect,useState } from "react"
import TaskList from "../components/tasks/TaskList";
import TaskCard from "../components/tasks/TaskCard";
import CreateTaskForm from "../components/tasks/CreateTaskForm";
import EditTaskForm from "../components/tasks/EditTaskForm";
import '../styles/Tasks.css';
import Navbar from "../components/common/Navbar";
import TaskStats from "../components/tasks/TaskStats"

function Tasks(){
    const [tasks, setTasks]= useState([]);
    const [filter, setFilter] = useState({subject: "", status:""});
    const [displayTasks, setDisplayTasks]=useState([]);

    const [selectedTask, setSelectedTask] =  useState(null);
    const [showCreateForm, setShowCreateForm] =  useState(false);
    const [showEditForm, setShowEditForm] =  useState(false);

    //Filter/Sorting/Search

    //TASK CARD
    function openTaskCard(task){
        setSelectedTask(task);
    }
    //EDIT FORM
    function openEditForm(){
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

    return (
    <div style={{display: 'flex', flexDirection: 'column', height: '100vh'}}>
        <Navbar />
        <div style={{padding: '2%', boxSizing: 'border-box', flex: 1, overflow: 'hidden'}}>
            <div style={{display: 'grid', gap: '5%', height: '100%', gridTemplateColumns: '1fr 400px' }}>
                <div style={{display: 'flex', flexDirection: 'column'}}>
                    <TaskStats tasks={tasks}/>
                    {/* search bar and filter goes here */}
                    <br/><br/>
                    <TaskList tasks = {tasks} onTaskClick={openTaskCard} />
                </div>
                <div style={{display: 'flex', flexDirection: 'column'}}>
                    <button className="button" style={{width: '100%', marginBottom:'5%'}} onClick={openCreateForm}>Create New Task</button>
                    <div style={{flex: 1, display: 'flex', flexDirection: 'column'}}>
                        <TaskCard task = {selectedTask} onEditClick = {openEditForm} onDeleteClick ={()=> deleteTask(selectedTask.id)}/>
                    </div>
                </div>
            </div>
            {showEditForm && <EditTaskForm task = {selectedTask} onClose={exitEditForm} onTaskSubmit={taskEdited} />}
            {showCreateForm && <CreateTaskForm onClose = {exitCreateForm} onTaskSubmit={taskCreated}/>}
        </div>
    </div>)
}
export default Tasks