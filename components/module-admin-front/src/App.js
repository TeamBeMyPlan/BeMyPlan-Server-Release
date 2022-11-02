import Topbar from './components/topbar/Topbar'
import Sidebar from './components/sidebar/Sidebar'
import './App.css'
import { Route, Routes } from 'react-router-dom';
import Home from './pages/home/Home'
import ProductPage from './pages/product/ProductPage';
import ProductCreatePage from './pages/product/ProductCreatePage';

function App() {
  return (
    <div className="App">
      <Topbar/>
      <div className='container'>
        <Sidebar/>
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/products' element={<ProductPage/>}/>
          <Route path='/products/new' element={<ProductCreatePage/>}/>
        </Routes>
      </div>
    </div>
  );
}

export default App;
