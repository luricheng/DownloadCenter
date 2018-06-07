function getSoftwareHtmlBlock(software) {
  htmlCode="<div style='border: 5px solid chartreuse;" +
    "background-color: whitesmoke;" +
    "height: fit-content;"+
    "width: fit-content;"+
    "margin-top: 20px;" +
    "text-align: left'>"

  htmlCode+="<table style='text-align: left'>"
  htmlCode+="<tr><td>"
  htmlCode+="<img width='200px' height='200px' src='"+software.imgLink+"' alt='"+software.name+"' />"
  htmlCode+="</td><td>"

  htmlCode+="<div>"
  htmlCode+="软件名: "+software.name+"<br>"
  htmlCode+="来源: "+software.source+"<br>"
  htmlCode+="简介: "+software.introduce+"<br>"
  htmlCode+="<a href='"+software.downloadLink+"'><button onclick='newDownload("+software.id+")'>点击下载</button></a><br>"
  htmlCode+="</div>"

  htmlCode+="</td></tr></table>"

  htmlCode+="</div>"
  return htmlCode
}