import { useParams } from "react-router-dom"
import { AiOutlineLoading3Quarters } from 'react-icons/ai';
import { Link } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css';

function ArticleList({data}) {
  // const params = useParams();
  // const item = data?.response?.body?.items[params.id];
  // console.log(params); 
  console.log('list: ', data);
  // console.log('list-body: ', data.get(0));
  // console.log('list-response: ', data?.response);
  // console.log('list-body: ', data?.body);
  

  return (
    <>
      <div>
        <Link className='btn btn-primary' type='button' to={`/article-new`}>글 등록</Link>
      </div>
      <br />
      <div className="row-6">
        {
          data && data.map((item, i)=>{
            return (
              <div className="card" key={i}>
                  <div className="card-header">
                    {i + 1}
                  </div>
                  <div className="card-body">
                      <h5 className="card-title">{item.title}</h5>
                      <p className="card-text">{item.content}</p>
                      <a href="" className="btn btn-primary">보러가기</a>
                  </div>
              </div>
            )    
          })
        }
        <br/>
      </div>
    </>
  )
}

export default ArticleList