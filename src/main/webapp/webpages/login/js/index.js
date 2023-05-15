var navleft = document.getElementById("left")
var navright = document.getElementById("right")
var navmidright = document.getElementById("midright")
var navmidleft = document.getElementById("midleft")
var show1 = document.getElementById("show1")
var show2 = document.getElementById("show2")
var show3 = document.getElementById("show3")
var show4 = document.getElementById("show4")

//监听鼠标离开浏览器窗口
window.addEventListener('mouseup', () => {
   // mouseup 需要执行的代码块
   navleft.style.backgroundImage = "url('webpages/login/img/b1.png')"
   show1.style.display = "block"
   navright.style.backgroundImage = "url('webpages/login/img/b4.png')"
   show4.style.display = "block"
   navmidright.style.backgroundImage = "url('webpages/login/img/b3.png')"
   show3.style.display = "block"
   navmidleft.style.backgroundImage = "url('webpages/login/img/b2.png')"
   show2.style.display = "block"

})
navleft.onmouseover = function (){
   navleft.style.backgroundImage = "url('webpages/login/img/b1.png')"
   show1.style.display = "block"
}
navleft.onmouseout = function (){

   navleft.style.backgroundImage = "url('')"
   show1.style.display = "none"
}

navright.onmouseover = function (){

   navright.style.backgroundImage = "url('webpages/login/img/b4.png')"
   show4.style.display = "block"
}
navright.onmouseout = function (){
   navright.style.backgroundImage = "url('')"
   show4.style.display = "none"
}
navmidright.onmouseover = function (){

   navmidright.style.backgroundImage = "url('webpages/login/img/b3.png')"
   show3.style.display = "block"
}
navmidright.onmouseout = function (){
   navmidright.style.backgroundImage = "url('')"
   show3.style.display = "none"
}
navmidleft.onmouseover = function (){

   navmidleft.style.backgroundImage = "url('webpages/login/img/b2.png')"
   show2.style.display = "block"
}
navmidleft.onmouseout = function (){
   navmidleft.style.backgroundImage = "url('')"
   show2.style.display = "none"
}

