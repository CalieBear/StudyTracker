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

    //which task is being edited/viewed
    /** @type {[Task | null, React.Dispatch<React.SetStateAction<Task | null>>]} */
    const [selectedTask, setSelectedTask] =  useState(null);
    //if create modal is visible
    const [showCreateForm, setShowCreateForm] =  useState(false);
    // if edit modal is visible
    const [showEditForm, setShowEditForm] =  useState(false);

    // gets the users tasks
    useEffect(()=>{
        fetch("http://localhost:8080/tasks",{
            credentials:"include"
        }).then (res=> res.json())
        .then (data =>setTasks(data))
        .catch(()=> setError("Unexepcted Error Occured"))
    },[])

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

    // HTML/CSS
    return (
    <div style={{display: 'flex', flexDirection: 'column', height: '100vh'}}>
        <Navbar />
        <div className="main">
            {/* LEFT COLUMN */}
            <div style={{display: 'flex', flexDirection: 'column',gap: '1rem'}}>
                <TaskStats tasks={tasks}/>
                {/* search bar and filter goes here */}
                {/* <div>
                    <input className="form-input" placeholder="Search tasks..."/>
                    <button className= "button-basic" style={{width:'auto',height:"auto"}}>Filter</button>
                </div> */}
                
                <TaskList tasks = {tasks} onTaskClick={openTaskCard} selectedTask={selectedTask}/>
            </div>
            {/* RIGHT COLUMN */}
            <div style={{display: 'flex', flexDirection: 'column'}}>
                <button className="button" style={{width: '100%', marginBottom:'5%'}} onClick={openCreateForm}>Create New Task</button>
                <div style={{flex: 1, display: 'flex', flexDirection: 'column'}}>
                    <TaskCard task = {selectedTask} onEditClick = {openEditForm} onDeleteClick ={()=> deleteTask(selectedTask.id)}/>
                </div>
            </div>
        </div>
        {/* MODALS */}
        {showEditForm && <EditTaskForm task = {selectedTask} onClose={exitEditForm} onTaskSubmit={taskEdited} />}
        {showCreateForm && <CreateTaskForm onClose = {exitCreateForm} onTaskSubmit={taskCreated}/>}
    </div>)
}
export default Tasks