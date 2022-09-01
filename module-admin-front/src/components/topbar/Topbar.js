import './Topbar.css'
import NotificationsNoneIcon from '@mui/icons-material/NotificationsNone'
import LanguageIcon from '@mui/icons-material/Language'
import SettingsIcon from '@mui/icons-material/Settings'

export default function Topbar() {
    return (
        <div className="topbar">
            <div className="topbarWrapper">
                <div className="topLeft">
                    <span className='logo'>Bemyplan Admin</span>
                </div>
                <div className="topRight">
                    <div className='topbarIconContainer' onClick={() => alert('힝 속았지?')}>
                        <NotificationsNoneIcon/>
                        <span className='topIconBadge'>2</span>
                    </div>
                    <div className='topbarIconContainer' onClick={() => alert('미구현이지롱~')}>
                        <LanguageIcon/>
                        <span className='topIconBadge'>2</span>
                    </div>
                    <div className='topbarIconContainer' onClick={() => alert('그냥 이뻐서 넣어둠')}>
                        <SettingsIcon/>
                    </div>
                </div>
            </div>
        </div>
    );
}