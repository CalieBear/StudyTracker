import '../../styles/Tasks.css';
function TaskStats({tasks}){
    const num = tasks.length;
    const inprogress = tasks.filter(t => t.status === "IN_PROGRESS").length;
    const completed = tasks.filter(t => t.status === "COMPLETED").length;


    return(
    <div style={{display: 'grid', gap: '16px', gridTemplateColumns: '1fr 1fr 1fr',textAlign: 'center', alignSelf: 'center'}}>
        <div className="card stats-card">
            <p className="header stats-header">{num}</p>
            <p className="label stats-label todo-pill">TOTAL TASKS</p>
        </div>
        <div className="card stats-card">
            <p className="header stats-header">{inprogress}</p>
            <p className="label stats-label inprogress-pill">IN PROGRESS</p>
        </div>
        <div className="card stats-card">
            <p className="header stats-header">{completed}</p>
            <p className="label stats-label completed-pill" >COMPLETED</p>
        </div>
    </div>
    )
}
export default TaskStats