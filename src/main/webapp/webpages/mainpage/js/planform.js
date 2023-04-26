function submitplan(){
    let data = {};
    //计划类型
     data.plantype = $('#template  option:selected').val();
     //计划名称
     data.planname = $('#planname').val();
     //计划发起人姓名
     data.planer = $('#planer').val();
     //计划信息
     data.planmessage = $('#planmessage').val();
     //计划属性
     data.planproperties = $('#planproperties').val();
     //计划金额
     data.planmoney = $('#planmoney').val();

    axios({
        url:'/LoverPlan/insertPlan',
        params:data,
        method:'post',


    }).then( res => {
        alert(res.data.CODE+","+res.data.message);
    })
}