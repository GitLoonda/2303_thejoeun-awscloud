import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from 'react-bootstrap/Button';
// import { Button } from 'react-bootstrap';

function Header() {
  // console.log('bbbbbbbbbbbbbbb');

  return (
    <div className="header p-4 mb-4 bg-light">
        <h1>My Blog</h1>
        <h4>블로그에 오신 것을 환영합니다.</h4>
    </div>
  )
}

export default Header