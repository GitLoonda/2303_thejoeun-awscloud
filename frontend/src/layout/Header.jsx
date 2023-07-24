import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'

function Header() {


  return (
    <>
      <div className="p-4 mb-4 bg-light">
          <h1>My Blog(React)</h1>
          <h4>
              블로그에 오신 것을 환영합니다.
              <button type="button" className="btn btn-danger">로그아웃</button>
          </h4>
      </div>
    </>
  )
}

export default Header

