package com.lxyup.Login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

@Controller
public class CompilerController {
    /*
     * 1表示关键字
     * 2表示标识符
     * 3表示常数
     * 4表示运算符
     * 5表示界符
     * 6表示字符串
     * */

    static String output1 = "";
    static String output2 = "";
    //关键字
    static String []keyWord={"private","protected","public","abstract","class","extends","final","implements",
            "interface","native","new","static","strictfp","break","continue","return","do","while","if","else","for",
            "instanceof","switch","case","default","boolean","byte","char","double","float","int","long","short",
            "String","null","true","false","void","this","goto"};
    //运算符
    static String []operation={"+","-","*","/","%","++","--","-=","*=","/=","&","|","^","~","<<",">>",">>>","==","!=",
            ">","<","=",">=","<=","&&","||","!","."};
    //界符
    static String []symbol={",",";",":","(",")","{","}"};
    static ArrayList<String> keyWords=null;
    static ArrayList<String> operations=null;
    static ArrayList<String> symbols=null;

    //指向当前所读到字符串的位置的指针
    static int p,lines;

    @RequestMapping("/Wordanalysis")
    @ResponseBody
    public Map<String,Object> checkUser(HttpServletRequest request) throws FileNotFoundException {
        init();
        Map<String,Object> result = new HashMap<>();
        String filename = request.getParameter("filename");
        File file=new File("C:/Users/Rekkles/Desktop/"+filename);
        lines=1;
        boolean isExplan = false;
        boolean isexist = false;
        String existstr = "";
         //每次读取一行并进行解析
        try(Scanner input = new Scanner(file)) {
            while (input.hasNextLine()){
                String str=input.nextLine();
                //如果/*还存在
                if (isexist==true){
                    for (int i = 0;i<str.length();i++){
                        //找到了
                        if ((str.charAt(i)=='*')&&(str.charAt(i+1)=='/')){
                            for (int j = i+2;j<str.length();j++){
                                existstr+=str.charAt(j);
                            }
                            isexist=false;
                        }
                    }
                }
                if (str.length()>1){
                    if (isexist==false){
                        for (int i = 0 ; i<str.length() ; i++){
                            //出现了两个连续的斜杠或者/*则进行注释处理
                            if (((str.charAt(i)=='/')&&(str.charAt(i+1)=='/'))||((str.charAt(i)=='/')&&(str.charAt(i+1)=='*'))){
                                if((str.charAt(i)=='/')&&(str.charAt(i+1)=='/'))
                                {
                                    //首列就检测到
                                    if (i==0){
                                        isExplan = true;
                                        break;
                                    }
                                    else{
                                        String newstr = "";
                                        for (int j = 0 ; j<i ;j++){
                                            newstr+=str.charAt(j);
                                        }
                                        isExplan = true;
                                        analyze(newstr);
                                        lines++;
                                    }
                                }
                                else{
                                    isExplan = true;
                                    for (int j = 0;j<i;j++){
                                        existstr+=str.charAt(j);
                                    }
                                    //如果本行找到了*/
                                    //溢出判断
                                    if(str.length()-i>4){
                                        for (int j=i+2;j<str.length();j++){
                                            if((str.charAt(j)=='*')&&(str.charAt(i+1)=='/')){
                                                if ((i+1)==str.length()-1){
                                                    isexist = false;
                                                    break;
                                                }
                                                else{
                                                    isexist=true;
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }

                }
                if (existstr!=""){
                    analyze(existstr);
                    lines++;
                    existstr="";
                }
                if (isExplan == false){
                    analyze(str);
                    lines++;
                }
                isExplan = false;
            }
        }
        result.put("right",output1);
        result.put("error",output2);
        //清空
        output1="";
        output2="";
        return result;
    }

    //初始化把数组转换为ArrayList
    public static void init(){
        keyWords=new ArrayList<>();
        operations=new ArrayList<>();
        symbols=new ArrayList<>();
        Collections.addAll(keyWords, keyWord);
        Collections.addAll(operations, operation);
        Collections.addAll(symbols, symbol);
    }

    public static void analyze(String str){

        p=0;
        char ch;
        str=str.trim();
        for (;p<str.length();p++){
            ch=str.charAt(p);
            if (Character.isDigit(ch)){
                digitCheck(str);
            }else if (Character.isLetter(ch)||ch=='_'){
                letterCheck(str);
            }else if (ch=='"'){
                stringCheck(str);
            }
            else if (ch==' '){
                continue;
            }else {
                symbolCheck(str);
            }
        }
    }

    /*数字的识别
     * 1、识别退出：
     *   1.1、遇到空格符
     *   1.2、遇到运算符或者界符
     * 2、错误情况：
     *   2.1、两个及以上小数点
     *   2.2、掺杂字母
     * */
    public static void digitCheck(String str){
        String token= String.valueOf(str.charAt(p++));
        int flag=0;
        boolean err=false;
        char ch;

        boolean binary = false;//二进制锁
        boolean octonary = false;//八进制锁
        boolean hexadecimal = false;//十六进制锁


        if ((token.equals("0"))&&(str.charAt(p)!='.')){
            int one = 0;
            //判断二进制
            for (;p<str.length();p++) {
                ch = str.charAt(p);
                if (ch==' '||(!Character.isLetterOrDigit(ch)&&ch!='.')) {
                    break;
                }else if (err){
                    token+=ch;
                }
                else if (one == 0){
                    token+=ch;
                    //符合二进制数
                    if ((ch == 'b') || (ch == 'B')){
                        binary = true;//打开锁检查二进制
                    }
                    else if((ch == 'x') || (ch == 'X')){
                        hexadecimal = true;//打开锁检查十六进制
                    }
                    else if ((ch!='9')&&(ch!='8')&&(ch!='0')){
                        octonary = true;//打开锁检查八进制
                    }
                }
                //如果都没有打开锁，则数字有错误
                if ((binary==false)&&(octonary==false)&&(hexadecimal==false)){
                    err = true;
                }
                if ((one!=0)&&(err==false)){
                    token+=ch;
                    //二进制锁打开状态，检查二进制
                    if (binary == true){
                        if((ch!='0')&&(ch!='1')){
                            err=true;
                        }
                    }
                    //八进制锁打开状态，检查八进制
                    if (octonary == true){
                        if((ch-'0')>7){
                            err=true;
                        }
                    }
                    //十六进制锁打开状态，检查二进制
                    if (hexadecimal == true){
                        if((ch>'F')||((ch<'A')&&(ch>'9'))||(ch<'0')){
                            err=true;
                        }
                    }

                }
                one = 1;
            }
            if (err) {
                output2 += (lines + "line" + ": " + token + " is wrong" + "\n");
            } else {
                if (binary == true)
                output1 += ("(" + "二进制数" + "," + token + ")" + "\n");
                else if (octonary == true)
                    output1 += ("(" + "八进制数" + "," + token + ")" + "\n");
                else if (hexadecimal == true)
                    output1 += ("(" + "十六进制数" + "," + token + ")" + "\n");
            }
            if (p != str.length() - 1 || (p == str.length() - 1 && !Character.isDigit(str.charAt(p)))) {
                p--;
            }

        }
        //判断数字的小数点是否有且是否大于1
        else {
            for (; p < str.length(); p++) {
                ch = str.charAt(p);
                if (ch == ' ' || (!Character.isLetterOrDigit(ch) && ch != '.')) {
                    break;
                } else if (err) {
                    token += ch;
                } else {
                    token += ch;
                    if (ch == '.') {
                        if (flag == 1) {
                            err = true;
                        } else {
                            flag++;
                        }
                    } else if (Character.isLetter(ch)) {
                        err = true;
                    }
                }
            }
            if (token.charAt(token.length() - 1) == '.') {
                err = true;
            }
            if (err) {
                output2 += (lines + "line" + ": " + token + " is wrong" + "\n");
            } else {
                if (str.contains(".")){
                    output1 += ("(" + "浮点数" + "," + token + ")" + "\n");
                }
                output1 += ("(" + "整数" + "," + token + ")" + "\n");
            }
            if (p != str.length() - 1 || (p == str.length() - 1 && !Character.isDigit(str.charAt(p)))) {
                p--;
            }
        }
    }

    //标识符，关键字的识别
    public static void letterCheck(String str){
        String token= String.valueOf(str.charAt(p++));
        char ch;
        for (;p<str.length();p++){
            ch=str.charAt(p);
            if (!Character.isLetterOrDigit(ch)&&ch!='_'){
                break;
            }else{
                token+=ch;
            }
        }
        if (keyWords.contains(token)){
            output1+=("("+"关键字"+","+token+")"+"\n");
        }else {
            output1+=("("+"标识符"+","+token+")"+"\n");
        }
        if (p!=str.length()-1||(p==str.length()-1&&(!Character.isLetterOrDigit(str.charAt(p))&&str.charAt(p)!='_'))){
            p--;
        }
    }

    //符号的识别
    public static void symbolCheck(String str){
        String token= String.valueOf(str.charAt(p++));
        char ch;
        if (symbols.contains(token)){
            output1+=("("+"界符"+","+token+")"+"\n");
            p--;
        }else {
            if (operations.contains(token)){
                if (p<str.length()){
                    ch=str.charAt(p);
                    if (operations.contains(token+ch)){
                        token+=ch;
                        p++;
                        if (p<str.length()){
                            ch=str.charAt(p);
                            if (operations.contains(token+ch)){
                                token+=ch;
                                output1+=("("+"运算符"+","+token+")"+"\n");
                            }else{
                                p--;
                               output1+=("("+"运算符"+","+token+")"+"\n");
                            }
                        }else{
                            output1+=("("+"运算符"+","+token+")"+"\n");
                        }
                    }else {
                        p--;
                        output1+=("("+"运算符"+","+token+")"+"\n");
                    }
                }
            }else {
                p--;
                output2+=(lines+"line"+": "+token+" is wrong"+"\n");
            }
        }
    }

    //字符串检查
    public static void stringCheck(String str){
        String token= String.valueOf(str.charAt(p++));
        char ch;
        for (;p<str.length();p++){
            ch=str.charAt(p);
            token+=ch;
            if (ch=='"'){
                break;
            }
        }
        if (token.charAt(token.length()-1)!='"'){
            output2+=(lines+"line"+": "+token+" is wrong"+"\n");
        }else {
            output1+=("("+"字符串"+","+token+")"+"\n");
        }
    }
}

