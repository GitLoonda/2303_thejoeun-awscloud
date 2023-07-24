import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react'
import { useNavigate } from 'react-router-dom';

function ArticleNew() {
  const [inputs, setInputs] = useState({
    title: '',
    content: ''
  });
  const navigate = useNavigate();

  const { title, content } = inputs; // 비구조화 할당을 통해 값 추출

  const onChange = (e) => {
    const { value, name } = e.target; // 우선 e.target 에서 name 과 value 를 추출
    setInputs({
      ...inputs, // 기존의 input 객체를 복사한 뒤
      [name]: value // name 키를 가진 값을 value 로 설정
    });
  };

  const onClick = (e) => {
    // const { value, name } = e.target; // 우선 e.target 에서 name 과 value 를 추출
    // setInputs({
    //   ...inputs, // 기존의 input 객체를 복사한 뒤
    //   [name]: value // name 키를 가진 값을 value 로 설정
    // });
    // console.log('title2 : ', title)
    // console.log('content2 : ', content)

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
      // console.log('postData: ', data)
      // alert('등록 완료되었습니다.');
      // navigate('/', {
      //   state: {
      //     id: 1,
      //     job: '개발자'
      //   }
      // });
      navigate('/')
      // window.location.reload()
    })

  };

  return (
    <>
      <div className="row">
        <div className="col-lg-8">
          <article>
            {/* <header style={{margin-bottom: 10px}}> */}
            <header>
                <label htmlFor="title1" className="form-label">제목</label>
                <input type="text" name="title" className="form-control" placeholder="제목을 입력하세요." onChange={onChange} value={title} />
            </header>
            {/* <section style="{{margin-bottom: 10px}}"> */}
            <section>
                <label htmlFor="content1" className="form-label">내용</label>
                <textarea className="form-control" name="content" rows="3" onChange={onChange} value={content} />
            </section>
            <button type="button" className="btn btn-primary" onClick={onClick}>등록</button>
          </article>
        </div>
    </div>
    </>
  )
}

export default ArticleNew