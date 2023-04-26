var filename = '';

function mInput() {
    let num = '';
    let input = document.getElementById("input");
    let input_txt = input.value;
    input_txt = input_txt.replace(/\r/gi, "");
    input_txt = input_txt.split("\n");
    n = input_txt.length;
    for (let i = 1; i <= n; i++) {
        num += i + "\n";
    }
    document.getElementById("left_txt").value = num;
}

function triggerScroll() {
    document.getElementById("left_txt").scrollTop = document.getElementById("input").scrollTop;
}

function getSel(){
    let input = document.getElementById("input");
    let text = input.value;
    let leftText = text.substr(0,input.selectionStart)
    console.log(leftText.split("\n").length)//行
    console.log(leftText.split("\n")[leftText.split("\n").length - 1].length)//列
}



function mInput1() {
    let num = '';
    let input = document.getElementById("input1");
    let input_txt = input.value;
    input_txt = input_txt.replace(/\r/gi, "");
    input_txt = input_txt.split("\n");
    n = input_txt.length;
    for (let i = 1; i <= n; i++) {
        num += i + "\n";
    }
    document.getElementById("left_1").value = num;
}

function triggerScroll1() {
    document.getElementById("left_1").scrollTop = document.getElementById("input1").scrollTop;
}

function getSel1(){
    let input = document.getElementById("input1");
    let text = input.value;
    let leftText = text.substr(0,input.selectionStart)
    console.log(leftText.split("\n").length)//行
    console.log(leftText.split("\n")[leftText.split("\n").length - 1].length)//列
}

function mInput2() {
    let num = '';
    let input = document.getElementById("input2");
    let input_txt = input.value;
    input_txt = input_txt.replace(/\r/gi, "");
    input_txt = input_txt.split("\n");
    n = input_txt.length;
    for (let i = 1; i <= n; i++) {
        num += i + "\n";
    }
    document.getElementById("left_2").value = num;
}

function triggerScroll2() {
    document.getElementById("left_2").scrollTop = document.getElementById("input2").scrollTop;
}

function getSel2(){
    let input = document.getElementById("input2");
    let text = input.value;
    let leftText = text.substr(0,input.selectionStart)
    console.log(leftText.split("\n").length)//行
    console.log(leftText.split("\n")[leftText.split("\n").length - 1].length)//列
}






function fileopen(){
    var inputObj=document.createElement('input')
    inputObj.setAttribute('id','my_inputObj');
    inputObj.setAttribute('type','file');
    inputObj.setAttribute("style",'visibility:hidden');
    inputObj.setAttribute("onchange","xml_parse.call(this)");
    document.body.appendChild(inputObj);
    inputObj.click();
    // axios({
    //     url:'/LoverPlan/message',
    //     params:data,
    //     method:'post',
    //
    //
    // }).then( res => {
    //
    // })
}
function  xml_parse() {
    console.log(this.files);
    var reader = new FileReader();
    reader.readAsText(this.files[0])
    filename = this.files[0].name;
    reader.onload = function (){
        document.getElementById("input").value = this.result;

    }
    console.log(getObjectURL(this.files[0]));
}

function getObjectURL(file) {
    var url = null;
    if (window.createObjcectURL != undefined) {
        url = window.createOjcectURL(file);
    } else if (window.URL != undefined) {
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) {
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

function Wordanalysis(){
    if (filename == ''){
        alert("还没有导入文件，请导入文件");
    }
    else{
        let data = {};
        data.filename = filename;
        axios({
            url:'/LoverPlan/Wordanalysis',
            params:data,
            method:'post',


        }).then( res => {
            $('#input1').html("");
            $('#input2').html("");
            $('#input1').html(res.data.right);
            $('#input2').html(res.data.error);

        })
    }

}
