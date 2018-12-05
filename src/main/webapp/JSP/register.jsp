<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        div{
            padding: 3%;
            border: solid 1px;
            width: 25%;
            margin: 7% auto;
        }

    </style>
</head>
<body>
    <div>
        <form action="/registerServlet" method="post">
            <h2 align="center">注册</h2><br>
            <table style=" position: relative; left: 18%">
                <tr>
                    <td width="10%">昵称</td>
                    <td width="20%"><input type="text" name="uaccount" required="required"></td>
                </tr>
                <tr>
                    <td width="10%">用户名</td>
                    <td width="20%"><input type="text" name="uname"  required="required"></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="password" name="pwd"  required="required"></td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td>男 <input type="radio" name="sex" value="1" checked="checked">  女 <input type="radio" name="sex" value="0"></td>
                </tr>
                <tr>
                    <td>籍贯</td>
                    <td>
                        <select name="address">
                            <option value="1">陕西</option>
                            <option value="2">山西</option>
                            <option value="3">河北</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>个人简介</td>
                    <td>
                        <textarea name="msg" cols="20%" rows="10%"  required="required"></textarea>
                    </td>
                </tr>
                <tr>

                </tr>
                <td colspan="2"  style="margin-right: 30%" >
                        <input type="checkbox"  required="required">
                    是否同意协议
                    </td>

                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="注册" style="position: relative; right: 20%">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>