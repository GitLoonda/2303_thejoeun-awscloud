import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import { Link } from 'react-router-dom'

function ArticleList({ articles }) {
  console.log('articleList:', articles);

  return (
    <>
      <div>
        <Link type="button" className="btn btn-primary" to={`/article-new`}>
          글 등록
        </Link>
      </div>
      <br />
      <div className="row-6">
        {
          articles && articles.map((article, idx) => {
            return (
              <div className="card" key={idx}>
                <div className="card-header">
                  {article.id}
                </div>
                <div className="card-body">
                  <h5 className="card-title">{article.title}</h5>
                  <p className="card-text">{article.content}</p>
                  <a className="btn btn-primary">보러가기</a>
                </div>
              </div>
            )
          })
        }
        <br />
      </div>
    </>
  )
}

export default ArticleList



