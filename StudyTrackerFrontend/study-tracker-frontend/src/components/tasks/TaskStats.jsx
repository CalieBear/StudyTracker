import '../../styles/Tasks.css';
/**
 * TaskStats - displays the users stats for their current tasks
 * @param {Object} props
 * @param {Array} props.tasks - the list of task objects to compute stats from 
 */
function TaskStats({tasks}){
    const num = tasks.length;
    const inprogress = tasks.filter(t => t.status === "IN_PROGRESS").length;
    const completed = tasks.filter(t => t.status === "COMPLETED").length;

    return(
    <div style={{display: 'grid', gap: '16px', gridTemplateColumns: '1fr 1fr 1fr',textAlign: 'center', alignSelf: 'stretch'}}>
        <div className="card stats-card">
            <p className="header stats-header">{num}</p>
            <p className="label stats-label pill todo-pill">TOTAL TASKS</p>
        </div>
        <div className="card stats-card">
            <p className="header stats-header">{inprogress}</p>
            <p className="label stats-label pill inprogress-pill">IN PROGRESS</p>
        </div>
        <div className="card stats-card">
            <p className="header stats-header">{completed}</p>
            <p className="label stats-label pill completed-pill" >COMPLETED</p>
        </div>
    </div>
    )
}
export default TaskStats
