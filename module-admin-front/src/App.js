import Topbar from './components/topbar/Topbar'
import Sidebar from './components/sidebar/Sidebar'
import Home from './pages/home/Home'
import './App.css'

function App() {
  return (
    <div className="App">
      <Topbar/>
      <div className='container'>
        <Sidebar/>
        <Home/>
      </div>
    </div>
  );
}

export default App;
