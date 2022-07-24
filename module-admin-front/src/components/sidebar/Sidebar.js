import './Sidebar.css'
import HomeIcon from '@mui/icons-material/HomeWork'
import MonetizationOnIcon from '@mui/icons-material/MonetizationOn'
import PersonIcon from '@mui/icons-material/PersonOutline'
import ProductIcon from '@mui/icons-material/Storefront'
import ReportIcon from '@mui/icons-material/Report'
import { Link } from 'react-router-dom'

export default function Sidebar() {
    return (
        <div className="sidebar">
            <div className='sidebarWrapper'>
                <div className='sidebarMenu'>
                    <h3 className='sidebarTitle'>Dashboard</h3>
                    <ul className='sidebarList'>
                        <Link className='sidebarText' to='/'>
                            <li className='sidebarListItem'><HomeIcon className='sidebarIcon'/>홈</li>
                        </Link>

                        <Link className='sidebarText' to='/products'>
                            <li className='sidebarListItem'><ProductIcon className='sidebarIcon'/>상품관리</li>
                        </Link>
                        {/* 
                        <Link className='sidebarText' to='/'>
                            <li className='sidebarListItem'><MonetizationOnIcon className='sidebarIcon'/>주문</li>
                        </Link>
                        <Link className='sidebarText' to='/users'>
                            <li className='sidebarListItem'><PersonIcon className='sidebarIcon'/>유저</li>
                        </Link>
                        <Link className='sidebarText' to='/'>
                            <li className='sidebarListItem'><ReportIcon className='sidebarIcon'/>Reports</li>
                        </Link>
                         */}
                    </ul>
                </div>
            </div>
        </div>
    )
}