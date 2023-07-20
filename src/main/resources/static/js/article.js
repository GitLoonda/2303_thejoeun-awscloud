// 등록 버튼 이벤트 기능
var createBtn = document.getElementById('create-btn');
var updateBtn = document.getElementById('update-btn');
var deleteBtn = document.getElementById('delete-btn');
var backBtn = document.getElementById('back-btn');

if(createBtn) {
    createBtn.addEventListener('click', evt => {
    fetch('/api/articles', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        })
    }).then(data => {
        alert('등록되었습니다.');
        location.replace("/articles");
    })
    })
}

//if(updateBtn) {
//    createBtn.addEventListener('click', evt => {
//    fetch('/api/articles', {
//        method: 'GET',
//        headers: {
//            "Content-Type": "application/json",
//        },
//        body: JSON.stringify({
//            title: document.getElementById('title').value,
//            content: document.getElementById('content').value
//        })
//    })
//})
//}

//if(deleteBtn) {
//    createBtn.addEventListener('click', evt => {
//    if(!confirm("정말 삭제하시겠습니까?")) {
//        return;
//    }
//    fetch('/api/articles', {
//        method: 'DELETE',
//        headers: {
//            "Content-Type": "application/json",
//        },
//        body: JSON.stringify({
//            id: document.getElementById('article-id').value
//        })
//    }).then(data => {
//        alert('삭제되었습니다.');
//        location.replace("/articles");
//    })
//    })
//}

if(backBtn) {
    backBtn.addEventListener('click', evt => {
         location.replace("/articles");
    })
}