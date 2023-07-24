import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './layout/Header'
import ArticleList from './pages/ArticleList';
import ArticleNew from './pages/ArticleNew';
import { useState, useEffect } from 'react';

function App() {
  const [articleData, setArticle] = useState(null);

  const articleListData = async () => {
    await fetch('/api/articles')
      .then(function(response) {
        return response.json();
      })
      .then(articles => {
        console.log('articles data :', articles)
        setArticle(articles)
      })
    }
  
  useEffect(() => {
    articleListData()
  }, [])

  return (
    <BrowserRouter>
      <Header></Header>
      <div className="container">
        <Routes>
          <Route path='/article-new' element={<ArticleNew />} />
          <Route path='/articles' element={<ArticleList articles={articleData}  />} />
        </Routes>
      </div>
    </BrowserRouter>
  )
}

export default App;
