
function newDownload(softwareId) {
  $.get('/addDownloadCount',{"id": softwareId})
  console.log("add cnt success")
  console.log($('#id').text())
  if($('#id').text()) {
    console.log("add Log")
    $.get('/addDownloadLog', {
      "softwareId": softwareId,
      "accountId": $('#id').text()
    })
  }
}

function getSofwareHtmlBlock(software) {
  htmlCode+="<div style='border: 5px solid chartreuse;" +
    "background-color: whitesmoke;" +
    "height: fit-content;"+
    "width: fit-content;"+
    "margin-top: 20px;" +
    "text-align: left'>"
  htmlCode+="<table style='text-align: left'><tr><td>"
  htmlCode+="<img width='200px' height='200px' src='"+software.imgLink+"' alt='"+software.name+"' />"
  htmlCode+="</td><td>"
  htmlCode+="<div>"
  htmlCode+="软件名: "+software.name+"<br>";
  htmlCode+="来源: "+software.source+"<br>"
  htmlCode+="简介: "+software.introduce+"<br>"
  htmlCode+="<a href='"+software.downloadLink+"'><button onclick='newDownload("+software.id+")'>点击下载</button></a><br>"
  htmlCode+="</div>"
  htmlCode+="</td></tr></table>"
  htmlCode+="</div>"
  return htmlCode
}

function getDownloadLogHtmlBlock(software) {
  htmlCode="<div style='border: 5px solid chartreuse;" +
    "background-color: whitesmoke;" +
    "height: fit-content;"+
    "width: fit-content;"+
    "margin-top: 20px;" +
    "text-align: left'>"
  htmlCode+="<table style='text-align: left'><tr><td>"
  htmlCode+="<img width='200px' height='200px' src='"+software.imgLink+"' alt='"+software.name+"' />"
  htmlCode+="</td><td>"
  htmlCode+="<div>"
  htmlCode+="软件名: "+software.name+"<br>";
  htmlCode+="来源: "+software.source+"<br>"
  htmlCode+="简介: "+software.introduce+"<br>"
  htmlCode+="<a href='"+software.downloadLink+"'><button onclick='newDownload("+software.id+")'>点击下载</button></a><br>"
  htmlCode+="最近下载时间: "+software.time+"<br>";
  htmlCode+="</div>"
  htmlCode+="</td></tr></table>"
  htmlCode+="</div>"
  return htmlCode
}

function rmDownloadLog() {
  $.get('/rmDownloadLog')
  $('#downloadLogDiv').empty()
}

function submitSoftware() {
  $.post($('#uploadFileForm').attr('action'),function (msg) {
    console.log(msg)
    $('#msg').empty()
    $('#msg').html("<h2 style='color: red'>"+msg+"</h2>")
  })
}