<style>
        body{
            position: absolute;
            width:100%;
            padding: 0;margin: 0;
        }
        .hex{
            cursor:pointer;
        }
        .hex:hover .fil0{
            fill:#373435;fill-opacity:0.1
        }
        .str0 {stroke:#ff0000;stroke-width:27.7756;stroke-opacity:0.7}
        .fil1 {fill:#FEFEFE}
        .fil0 {
            fill:#000000;fill-opacity:0.5;
        }
        .fnt0 {font-weight:normal; text-align: center; font-size:333.335px;font-family:'Arial'}
        .hidden{display: none;}
    </style>
</head>
<body>
    <svg id="hexGrid" style="" xmlns="http://www.w3.org/2000/svg" xml:space="preserve" version="1.1" style="shape-rendering:geometricPrecision; text-rendering:geometricPrecision; image-rendering:optimizeQuality; fill-rule:evenodd; clip-rule:evenodd"
viewBox="0 0 1200 520"
 xmlns:xlink="http://www.w3.org/1999/xlink">
    <defs id="svgDefs">
    </defs>
 </svg>
    
    <script>
        function createLink (title,description,bgImg,href,id,x,y,width){
            var link = document.createElementNS("http://www.w3.org/2000/svg", 'a');
            link.setAttribute('class','hex');
            
            var image = document.createElementNS("http://www.w3.org/2000/svg", 'image');
            image.setAttributeNS(null,'width',width);
            height = width/0.866025;
            image.setAttributeNS(null,'height',height);
            image.setAttributeNS(null,'id','img'+id);
            image.setAttributeNS('http://www.w3.org/1999/xlink','href',bgImg);
            image.setAttributeNS(null,'x',x-width/2);
            image.setAttributeNS(null,'y',y-height/2);
            image.setAttributeNS(null,'preserveAspectRatio',"none");
            
            
            link.appendChild(image);
            //var side = width/2;
            link.appendChild(createHexagon(x,y,width));
            createClipPath(id, x , y, width);            
            link.setAttribute('clip-path', 'url(#'+id+')');
            link.setAttributeNS('http://www.w3.org/1999/xlink', 'xlink:href', "content.jsp");
            return link;
        }
        
        function  createClipPath (id, x , y, a){
            var clipPath = document.createElementNS("http://www.w3.org/2000/svg", 'clipPath');
            clipPath.setAttribute("id",id);
            clipPath.appendChild(createHexagon(x,y,a));
            document.getElementById("svgDefs").appendChild(clipPath);
        }
        function createHexagon(x,y,a){
            a = a/1.73205080757;
            root3aBy2 = a*0.866025;
            aBy2 = a/2;
            
            points =         x        +","+  (a+y)    + " ";
            points += (root3aBy2+x)   +","+  (aBy2+y) + " ";
            points += (root3aBy2+x)   +","+  (-aBy2+y)+ " ";
            points +=        x        +","+  (-a+y)   + " ";
            points += (-root3aBy2+x)  +","+  (-aBy2+y)+ " ";
            points += (-root3aBy2+x)  +","+  (aBy2+y);
           
            var hexagon = document.createElementNS("http://www.w3.org/2000/svg", 'polygon');
            hexagon.setAttribute('points', points);
            hexagon.setAttribute('class','fil0');
            
            return hexagon;
        }
        function createHexagon1(x,y,a){
            root3aBy2 = a*0.866025;
            aBy2 = a/2;
            
            points =(-a+x)       +","+  (y)    + " ";
            points += (-aBy2+x)  +","+  (root3aBy2+y) + " ";
            points += (aBy2+x)   +","+  (root3aBy2+y)+ " ";
            points +=  (a+x)     +","+  (y)   + " ";
            points += (aBy2+x)   +","+  (-root3aBy2+y)+ " ";
            points += (-aBy2+x)  +","+  (-root3aBy2+y);
           
            var hexagon = document.createElementNS("http://www.w3.org/2000/svg", 'polygon');
            hexagon.setAttribute('points', points);
            hexagon.setAttribute('class','fil0');
            
            return hexagon;
        }
        
        var mySVG = document.getElementById("hexGrid");
        width= 200;padding = 5;
        h = width/2; v=(width/0.866025);
        row=1;count=0;
        <%for (int i=0; i<10; i++){%>
            mySVG.appendChild(createLink("title","description","./img/hex.jpg","href","id"+count, h  , row*v,width-padding));
            h+=width;count++;
            <%if (i==4){%> row+=0.75;h=width;<%}%>
        <%}%>
        
        
        /****** code for horizontal hex******/
        /*var mySVG = document.getElementById("hexGrid");
        width= 200;padding = 5;
        h = width/2; v=(width*1.73205080757)/4;
        row=0;count=0;
        <%for (int i=0; i<2; i++){%>
        <% if (i==1){%>
        
        <%}%>
            
        mySVG.appendChild(createLink("title","description","href","id"+count,  h  , row+ v,width-padding));
        count++;
        <% if (i!=1){%>
            mySVG.appendChild(createLink("title","description","href","id"+count,2.5*h, row+2*v,width-padding));
            count++;
        <%}%>
        mySVG.appendChild(createLink("title","description","href","id"+count,  4*h, row+v,width-padding));
        count++;
        <% if (i!=1){%>
        mySVG.appendChild(createLink("title","description","href","id"+count,5.5*h, row+2*v,width-padding));
        count++;
        <%}%>
        mySVG.appendChild(createLink("title","description","href","id"+count,  4*h, row+v,width-padding));
        count++;
        mySVG.appendChild(createLink("title","description","href","id"+count,7*h, row+v,width-padding));
        count++;
        
        row+=2*v;
        <% } %>
        */
</script>

<!--svg id="hexGrid" xmlns="http://www.w3.org/2000/svg" xml:space="preserve" width="100%" version="1.1" style="shape-rendering:geometricPrecision; text-rendering:geometricPrecision; image-rendering:optimizeQuality; fill-rule:evenodd; clip-rule:evenodd"
viewBox="0 0 5246 6376"
 xmlns:xlink="http://www.w3.org/1999/xlink">
 <defs>
    <clipPath id="id0">
     <path d="M0 894l258 -447 258 -447 516 0 516 0 258 447 258 447 -258 446 -258 447 -516 0 -516 0 -258 -447 -258 -446z"/>
    </clipPath>
    <clipPath id="id1">
     <path d="M3182 894l258 -447 258 -447 516 0 516 0 258 447 258 447 -258 446 -258 447 -516 0 -516 0 -258 -447 -258 -446z"/>
    </clipPath>
    <clipPath id="id2">
     <path d="M1588 1825l258 -447 258 -446 516 0 516 0 258 446 258 447 -258 447 -258 447 -516 0 -516 0 -258 -447 -258 -447z"/>
    </clipPath>
    <clipPath id="id3">
     <path d="M0 2733l258 -447 258 -447 516 0 516 0 258 447 258 447 -258 446 -258 447 -516 0 -516 0 -258 -447 -258 -446z"/>
    </clipPath>
    <clipPath id="id4">
     <path d="M3182 2733l258 -447 258 -447 516 0 516 0 258 447 258 447 -258 446 -258 447 -516 0 -516 0 -258 -447 -258 -446z"/>
    </clipPath>
    <clipPath id="id5">
     <path d="M1588 3654l258 -447 258 -447 516 0 516 0 258 447 258 447 -258 447 -258 446 -516 0 -516 0 -258 -446 -258 -447z"/>
    </clipPath>
    <clipPath id="id6">
     <path d="M0 4561l258 -446 258 -447 516 0 516 0 258 447 258 446 -258 447 -258 447 -516 0 -516 0 -258 -447 -258 -447z"/>
    </clipPath>
    <clipPath id="id7">
     <path d="M3182 4561l258 -446 258 -447 516 0 516 0 258 447 258 446 -258 447 -258 447 -516 0 -516 0 -258 -447 -258 -447z"/>
    </clipPath>
    <clipPath id="id8">
     <path d="M1588 5482l258 -446 258 -447 516 0 516 0 258 447 258 446 -258 447 -258 447 -516 0 -516 0 -258 -447 -258 -447z"/>
    </clipPath>
 </defs>
   <a xlink:href="/svg/index.html"  class="hex" style="clip-path:url(#id0)">
    <image x="-53" y="-554" width="2155" height="3204" xlink:href="nav_Images\nav_ImgID1.jpg"/>
   <polygon class="fil0" points="0,894 258,447 516,0 1032,0 1548,0 1806,447 2064,894 1806,1340 1548,1787 1032,1787 516,1787 258,1340 "/>
   
   <text x="1058" y="700" style="text-anchor: middle" class="fil1 fnt0">This is</text>
   <text x="1058" y="1013" style="text-anchor: middle" class="fil1 fnt0">Feel Good</text>
   <text x="1058" y="1326" style="text-anchor: middle" class="fil1 fnt0">Title</text>
   <text class="description hidden">Feel Good</text>
  </a>
   <a xlink:href="/svg/index.html"  class="hex" style="clip-path:url(#id1)">
    <image x="3130" y="-554" width="2155" height="3204" xlink:href="nav_Images\nav_ImgID2.jpg"/>
   <polygon class="fil0" points="3182,894 3440,447 3698,0 4214,0 4730,0 4988,447 5246,894 4988,1340 4730,1787 4214,1787 3698,1787 3440,1340 "/>
   <text x="4230" y="700" style="text-anchor: middle" class="fil1 fnt0">This is</text>
   <text x="4230" y="1013" style="text-anchor: middle" class="fil1 fnt0">Motivational</text>
   <text x="4230" y="1326" style="text-anchor: middle" class="fil1 fnt0">Title</text>
   <text class="description hidden">Motivational</text>
  </a>
   <a xlink:href="/svg/index.html"  class="hex" style="clip-path:url(#id2)">
    <image x="1536" y="378" width="2155" height="3204" xlink:href="nav_Images\nav_ImgID3.jpg"/>
   <polygon class="fil0" points="1588,1825 1846,1378 2104,932 2620,932 3136,932 3394,1378 3652,1825 3394,2272 3136,2719 2620,2719 2104,2719 1846,2272 "/>
   <text x="2646" y="1787" style="text-anchor: middle" class="fil1 fnt0">Heart</text>
   <text x="2646" y="2100" style="text-anchor: middle" class="fil1 fnt0">Broken</text>
   <text class="description hidden">Heart Broken description</text>
  </a>
   <a xlink:href="/svg/index.html"  class="hex" style="clip-path:url(#id3)">
    <image x="-53" y="1285" width="2155" height="3204" xlink:href="nav_Images\nav_ImgID4.jpg"/>
   <polygon class="fil0" points="0,2733 258,2286 516,1839 1032,1839 1548,1839 1806,2286 2064,2733 1806,3179 1548,3626 1032,3626 516,3626 258,3179 "/>
   
   <text x="1058" y="2539" style="text-anchor: middle" class="fil1 fnt0">This is</text>
   <text x="1058" y="2852" style="text-anchor: middle" class="fil1 fnt0">Loved</text>
   <text x="1058" y="3165" style="text-anchor: middle" class="fil1 fnt0">Title</text>
   <text class="description hidden">Loved</text>
  </a>
   <a xlink:href="/svg/index.html"  class="hex" style="clip-path:url(#id4)">
    <image x="3130" y="1285" width="2155" height="3204" xlink:href="nav_Images\nav_ImgID5.jpg"/>
   <polygon class="fil0" points="3182,2733 3440,2286 3698,1839 4214,1839 4730,1839 4988,2286 5246,2733 4988,3179 4730,3626 4214,3626 3698,3626 3440,3179 "/>
   <text x="4240" y="2852" style="text-anchor: middle" class="fil1 fnt0">Sexy</text>
   <text class="description hidden">Sexy</text>
  </a>
   <a xlink:href="/svg/index.html"  class="hex" style="clip-path:url(#id5)">
    <image x="1536" y="2207" width="2155" height="3204" xlink:href="nav_Images\nav_ImgID6.jpg"/>
   <polygon class="fil0" points="1588,3654 1846,3207 2104,2760 2620,2760 3136,2760 3394,3207 3652,3654 3394,4101 3136,4547 2620,4547 2104,4547 1846,4101 "/>
   <text x="2036" y="3773"  class="fil1 fnt0">Humor</text>
   <text class="description hidden">Humor</text>
  </a>
   <a xlink:href="/svg/index.html"  class="hex" style="clip-path:url(#id6)">
    <image x="-53" y="3114" width="2155" height="3204" xlink:href="nav_Images\nav_ImgID7.jpg"/>
   <polygon class="fil0" points="0,4561 258,4115 516,3668 1032,3668 1548,3668 1806,4115 2064,4561 1806,5008 1548,5455 1032,5455 516,5455 258,5008 "/>
   <text x="1058" y="4525" style="text-anchor: middle" class="fil1 fnt0">Clever</text>
   <text x="1058" y="4837" style="text-anchor: middle" class="fil1 fnt0">Witty</text>
   <text class="description hidden">Clever Description</text>
  </a>
   <a xlink:href="/svg/index.html" class="hex" style="clip-path:url(#id7)">
    <image x="3130" y="3114" width="2155" height="3204" xlink:href="nav_Images\nav_ImgID8.jpg"/>
   <polygon class="fil0" points="3182,4561 3440,4115 3698,3668 4214,3668 4730,3668 4988,4115 5246,4561 4988,5008 4730,5455 4214,5455 3698,5455 3440,5008 "/>
   <text x="3630" y="4681"  class="fil1 fnt0">Fantasy</text>
   <text class="description hidden">Fantasy</text>
  </a>
   <a xlink:href="/svg/index.html"  class="hex" style="clip-path:url(#id8)">
    <image x="1536" y="4035" width="2155" height="3204" xlink:href="nav_Images\nav_ImgID9.jpg"/>
   <polygon class="fil0" points="1588,5482 1846,5036 2104,4589 2620,4589 3136,4589 3394,5036 3652,5482 3394,5929 3136,6376 2620,6376 2104,6376 1846,5929 "/>
   <text x="2036" y="5602"  class="fil1 fnt0">Action</text>
   <text class="description hidden">Action</text>
  </a>
</svg><!---->