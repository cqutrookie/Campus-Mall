function loaduser(){
    let html = "";
    let data={};
    axios({
        url:'/LoverPlan/getusermessage',
        params:data,
        method:'post',


    }).then( res => {
        $('#usermessage').html("<a href='/LoverPlan/webpages/mainpage/homepage.html'>欢迎您，"+res.data.username+"</a>");
    })
}
window.onload=function(){
    loaddata(1);
    loaduser();
}