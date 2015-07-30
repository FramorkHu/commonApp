<#assign shiro=JspTaglibs["http://shiro.apache.org/tags"] />
<@shiro.hasPermission name="user:create">
    <a href="addUser">用户新增</a><br/>
</@shiro.hasPermission>
<a href="editUser">editUser</a>