var startPage = 1;

function loaddata(pageN){
    let html = "";
    let data={};
    let pagehtml="";
    data.pageNum = pageN;
    axios({
        url:'/LoverPlan/getPage',
        params:data,
        method:'post',


    }).then( res => {
        let html = "";
        let url = "";
        let pagehtml="";
        let plans = res.data.page.list;
        for (let i = 0; i < plans.length; i++) {
            html += '<tr>';
            html += '<td>'+plans[i].bookname+'</td>';
            html += '<td>'+plans[i].bookmessage+'</td>';
            html += '<td>'+plans[i].booknumber+'</td>';
            html += '<td>'+plans[i].booktype+'</td>';
            let bookId = plans[i].bookid
            html += '<td><h3 onclick="gotoplan('+bookId+')">借阅</h3></td>';
            html += '</tr>';
        }
        $('#plans').html(html);
        if (startPage == 1){
            pagehtml += '<h3  onclick="changepage2()">'+startPage+'</h3>';
            pagehtml += '<h3  onclick="changepage3()">'+(startPage+1)+'</h3>';
            pagehtml += '<h3  onclick="changepage4()">'+(startPage+2)+'</h3>';
        }
        else{
            pagehtml += '<h3  onclick="changepage5()">'+(startPage-1)+'</h3>';
            pagehtml += '<h3  onclick="changepage2()">'+startPage+'</h3>';
            pagehtml += '<h3  onclick="changepage4()">'+(startPage+1)+'</h3>';
        }
        $('#pagenumber').html(pagehtml);
    })
}
function changepage(){
    loaddata(1);
    startPage=1;
}
function changepage2(){
    loaddata(startPage);
}
function changepage3(){
    loaddata(startPage+1);
    startPage++;
}
function changepage4(){
    loaddata(startPage+2);
    startPage+=2;
}
function changepage5(){
    loaddata(startPage-1);
    startPage--;
}

function changepage1(){
    if (startPage == 1){
        loaddata(1);
    }
    else{
        loaddata(startPage-1);
        startPage--;
    }
}
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
function gotoplan(planid){
    let data={};
    data.planid = planid;
    axios({
        url:'/LoverPlan/workplan',
        params:data,
        method:'post',


    }).then( res => {
        alert(res.data.CODE+","+res.data.plan.planid);
    })
}
function changeTwoDecimal_f(x) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        alert('function:changeTwoDecimal->parameter error');
        return false;
    }
    var f_x = Math.round(x * 10) / 10;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 1) {
        s_x += '0';
    }
    return s_x;
}