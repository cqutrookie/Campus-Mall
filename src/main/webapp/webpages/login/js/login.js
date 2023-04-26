var USERNAME_FAULT = "00";
var USERNAME_RIGHT = "01";

function checkUser(){
    let data = {};
    data.username = document.getElementById("username").value;
    data.password = document.getElementById("password").value;
    axios({
        url:'/LoverPlan/checkUser',
        params:data,
        method:'post',


    }).then( res => {
            if (USERNAME_FAULT == res.data.CODE){
                window.alert("PASSWORD ERROR");
            }
            else{
                if (res.data.access == 0){
                    window.location.href="/LoverPlan/webpages/mainpage/allLibrary.html";
                }

            }
    })
}