import {useState} from "react";
function SearchFilter({filter, setFilter}){
    const [showDropdown,setShowDropdown] = useState(false);

    return(
    <div style={{display:'flex',gap:'10px'}}>
        <input className="search-bar" id="search-bar" placeholder="Search tasks..."
            onChange={(e)=>setFilter({...filter, search: e.target.value})}
        />
        <button className= "search-btn button-basic">Filter</button>
        {/* {showDropdown && <div className="dropDown">
            <span >STATUS</span>
            <div >
            </div>
            <span>Not Started</span>
            <span>In Progress</span>
            <span>Completed </span>
            <hr/>
            <span>clear all</span>
            </div>} */}
    </div>);
}
export default SearchFilter;