<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="">

    <title>body</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

      <style type="text/css">
          *{
              margin:0;
              padding:0;
          }
          .div{
              width: 720px;
              height: 405px;
              margin:20px auto;
              overflow: hidden;
              position: relative;
          }
          #index{
              position: absolute;
              left:320px;
              bottom: 20px;

          }
          #index li{
              width:8px;
              height: 8px;
              border: solid 1px gray;
              border-radius: 100%;
              background-color: #eee;
              display: inline-block;
          }
          #img{
              width: 3600px;/*不给宽高无法移动*/
              height: 405px;
              position: absolute;/*不定义absolute，js无法设置left和top*/
              z-index: -1;
          }
          #img li{
              width: 720px;
              height: 405px;
              float: left;
          }
          #index .on{
              background-color: black;
          }
      </style>
  <body>

  <div>
      <h1>欢迎来到小奉天书店, 多多购买欧^+^</h1>
  </div>


  <!--从左向右滑动-->
  <div class="div">
      <ul id="index">
          <li class="on"></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
      </ul>
      <ul id="img">
          <li><img src="../images/11.jpg" alt="img1"></li>
          <li><img src="../images/22.jpg" alt="img2"></li>
          <li><img src="../images/33.jpg" alt="img3"></li>
          <li><img src="../images/44.jpg" alt="img4"></li>
          <li><img src="../images/55.jpg" alt="img5"></li>
      </ul>
  </div>

  <script>
      function moveElement(ele,x_final,y_final,interval){//ele为元素对象
          var x_pos=ele.offsetLeft;
          var y_pos=ele.offsetTop;
          var dist=0;
          if(ele.movement){//防止悬停
              clearTimeout(ele.movement);
          }
          if(x_pos==x_final&&y_pos==y_final){//先判断是否需要移动
              return;
          }
          dist=Math.ceil(Math.abs(x_final-x_pos)/10);//分10次移动完成
          x_pos = x_pos<x_final ? x_pos+dist : x_pos-dist;

          dist=Math.ceil(Math.abs(y_final-y_pos)/10);//分10次移动完成
          y_pos = y_pos<y_final ? y_pos+dist : y_pos-dist;

          ele.style.left=x_pos+'px';
          ele.style.top=y_pos+'px';

          ele.movement=setTimeout(function(){//分10次移动，自调用10次
              moveElement(ele,x_final,y_final,interval);
          },interval)
      }
      function moveIndex(list,num){//移动小圆点
          for(var i=0;i<list.length;i++){
              if(list[i].className=='on'){//清除li的背景样式
                  list[i].className='';
              }
          }
          list[num].className='on';
      }
      var img=document.getElementById('img');
      var list=document.getElementById('index').getElementsByTagName('li');
      var index=0;//这里定义index是变量，不是属性

      var nextMove=function(){//一直向右移动，最后一个之后返回
          index+=1;
          if(index>=list.length){
              index=0;
          }
          moveIndex(list,index);
          moveElement(img,-720*index,0,20);
      };
      var play=function(){
          timer=setInterval(function(){
              nextMove();
          },2500);
      };
      for(var i=0;i<list.length;i++){//鼠标覆盖上哪个小圆圈，图片就移动到哪个小圆圈，并停止
          list[i].index=i;//这里是设置index属性，和index变量没有任何联系
          list[i].onmouseover= function () {
              var clickIndex=parseInt(this.index);
              moveElement(img,-720*clickIndex,0,20);
              index=clickIndex;
              moveIndex(list,index);
              clearInterval(timer);
          };
          list[i].onmouseout= function () {//移开后继续轮播
              play();
          };
      }
  </script>
  </body>
</html>