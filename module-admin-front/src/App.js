import Topbar from './components/topbar/Topbar'
import Sidebar from './components/sidebar/Sidebar'
import './App.css'
import { Route, Routes } from 'react-router-dom';
import Home from './pages/home/Home'
import User from './pages/user/User';
import Product from './pages/product/Product';

function App() {
  return (
    <div className="App">
      <Topbar/>
      <div className='container'>
        <Sidebar/>
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/users' element={<User/>}/>
          <Route path='/products' element={<Product/>}/>
        </Routes>
      </div>
    </div>
  );
}

export default App;
