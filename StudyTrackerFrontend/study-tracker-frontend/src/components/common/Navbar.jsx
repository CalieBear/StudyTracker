import '../../styles/Tasks.css';
function Navbar() {
    const date =new Date().toLocaleDateString('en-US', { weekday: 'short', month: 'short', day: 'numeric' });
    const initals ="CB"; 
    // PLACEHOLDER!!!!
    return(
        <div className="navbar">
            <div>
                {/* icon here if i ever make one */}
                <span className='navbar-title'>StudyTracker</span>
                {/* eventually this should link to dashboard */}
            </div>
            <div style={{display:'flex',alignItems:'center',gap:'12px'}}>
                {/* date */}
                <span style={{fontSize:'var(--txt-sm)',color:'rgba(251, 247, 239, 0.65)'}}>{date}</span>
                {/* profile avatar */}
                <span className='avatar'>{initals}</span>
            </div>
        </div>
    )
}
export default Navbar;