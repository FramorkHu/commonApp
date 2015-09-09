<#assign shiro=JspTaglibs["http://shiro.apache.org/tags"] />
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/resources/bower_components/jquery/jquery.min.js"></script>
<script src="/resources/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


<@shiro.hasPermission name="admin:us">
    <a href="addUser">用户新增</a><br/>
</@shiro.hasPermission>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                DataTables Advanced Tables
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="dataTable_wrapper">
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
                </div>
                <!-- /.table-responsive -->

            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable({
        "processing": true,
        "serverSide": true,
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
</script>