import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'

function ArticleNew() {

const [inputs, setInputs] = useState({
  title1: '',
  content1: '' 
});

const { title1, content1 } = inputs;

const onChange = (e) => {
  const { value, id } = e.target;
  setInputs({
    ...inputs,   // spread연산자
    [id]: value
  })
};

const navigate = useNavigate();

const onRegClick = e => {
  fetch('/api/articles', {
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      title: title,
      content: content
    })
  }).then(data => {
    alert("등록되었습니다.");
    navigate('/articles');
  })
}

  return (
    <>
      <div className="row">
            <div className="col-lg-8">
                <article>
                    <header style={{ marginBottom: "10px" }}>
                        <label htmlFor="title1" className="form-label">제목</label>
                        <input type="text"
                         className="form-control" 
                         id="title1" 
                         placeholder="제목을 입력하세요." 
                         onChange={onChange} 
                         value={title1} />
                    </header>
                    <section style={{ marginBottom: "30px" }}>
                        <label htmlFor="content1" className="form-label">내용</label>
                        <textarea className="form-control"
                         id="content1" 
                         rows="3" 
                         onChange={onChange}>{content1}</textarea>
                    </section>
                    <button type="button" className="btn btn-primary" id="create-btn" onClick={onRegClick}>등록</button>
                </article>
            </div>
        </div>
    </>
  )
}

export default ArticleNew