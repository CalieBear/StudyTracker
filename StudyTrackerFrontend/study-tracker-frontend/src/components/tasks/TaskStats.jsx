import '../../styles/Tasks.css';
function TaskStats({tasks}){
    const num = tasks.length;
    const inprogress = tasks.filter(t => t.status === "IN_PROGRESS").length;
    const completed = tasks.filter(t => t.status === "COMPLETED").length;


    return(
    <div style={{display: 'grid', gap: '16px', gridTemplateColumns: '1fr 1fr 1fr',textAlign: 'center', maxWidth: '600px', alignSelf: 'center'}}>
        <div className="card">
            <p className="header">{num}</p>
            <p className="label">TOTAL TASKS</p>
        </div>
        <div className="card">
            <p className="header">{inprogress}</p>
            <p className="label inprogress-pill">IN PROGRESS</p>
        </div>
        <div className="card">
            <p className="header">{completed}</p>
            <p className="label completed-pill" >COMPLETED</p>
        </div>
    </div>
    )
}
export default TaskStats