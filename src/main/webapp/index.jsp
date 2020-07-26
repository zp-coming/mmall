<html>
<body>
<h2>Tomcat111111111</h2>

springMVC上传文件
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="springmvc文件上传" />
</form>

富文本图片上传
<form name="form1" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="富文本图片上传" />

</form>
</body>
</html>
