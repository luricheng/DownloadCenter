
function index2Search() {
  var key = $('#searchKey').val()
  $(window).attr('location','/fileUpdate?id='+id)
}

function search() {
  var key = $('#searchKey').val()
  if (key == '') {
    alert('请输入搜索内容')
    return
  }
  $.post('/search', {
    'key': key
  }, function (data) {
    list = eval(data)
    $('#resultList').empty()
    $('#resultList').append("<tr><td>搜索" + list[0].key + "结果为:</td></tr>")
    for (var i = 1; i < list.length; ++i) {
      software = list[i]
      console.log(software.name)
      $('#resultList').append(getSoftwareHtmlBlock(software))
    }
  }, 'JSON')
}