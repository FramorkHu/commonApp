<#assign shiro=JspTaglibs["http://shiro.apache.org/tags"] />
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/resources/bower_components/jquery/jquery.min.js"></script>
<script src="/resources/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


<@shiro.hasPermission name="admin:us">
    <a href="addUser">用户新增</a><br/>
</@shiro.hasPermission>


<table class="table table-striped table-bordered table-hover" id="dataTables-example">
    <thead>
    <tr>
        <th>Rendering engine</th>
        <th>Browser</th>
        <th>Platform(s)</th>
        <th>Engine version</th>
        <th>CSS grade</th>
    </tr>
    </thead>
</table>

<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable({
        "processing": true,
        "serverSide": true,
        "searching": false,
        "language": {
                "lengthMenu": "_MENU_ 条记录每页",
                "zeroRecords": "没有找到记录",
                "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                "infoEmpty": "无记录",
                "infoFiltered": "(从 _MAX_ 条记录过滤)",
                "paginate": {
                    "previous": "上一页",
                    "next": "下一页"
                }
            },
        "ajax":{
            "url":"/admin/user/findUser",
            "data":function(d){
                d.value = "myValue";
             }
        },
        "columns": [
            { "data": "id" },
            { "data": "userName" },
            { "data": "password" },
            { "data": "enabled" },
            { "data": "isSuperAdmin" }
        ]
    });
});
function initComplete(data){
    $('#mytoolbox').append('asadwadwa');
}

</script>