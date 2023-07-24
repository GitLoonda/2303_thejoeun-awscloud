// import logo from './logo.svg';
import { BrowserRouter, Routes, Route, useLocation, Router, useNavigate } from 'react-router-dom'
import { useState, useEffect } from 'react'
import './App.css'
import Header from './components/Header'
import ArticleList from './pages/ArticleList'
import ArticleNew from './pages/ArticleNew'

function App() {
  const [data, setData] = useState(null);
  // https://stackoverflow.com/questions/66747556/react-js-error-uselocation-may-be-used-only-in-the-context-of-a-router-com
  const location = useLocation();
  const navigate = useNavigate();
  // console.log('lllllocation: ', location);
  // console.log('nnnnnavigation: ', navigate);

  const articleListData = async () => {
    await fetch("/api/articles")
      .then(res => res.json())
      .then(data => {
        console.log('article data :: ' + data);
        setData(data);
      })
  };

  useEffect(() => {
    // console.log('aaaaaaa: ', location.pathname)
    if (location.pathname !== '/article-new')
      articleListData();
  }, [location]);

  return (
    <>
      <Header />
      <div className='container'>
        <Routes>
            <Route path='/article-new' element={<ArticleNew />} />
            <Route path='/' element={<ArticleList data={data} />} />
        </Routes>
      </div>
    </>
  );
}

export default App;
