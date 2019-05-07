<?
$value1 = $_POST["value1"];
$value2 = $_POST["value2"];
   
move_uploaded_file($_FILES["file1"]["tmp_name"],
    $_SERVER["DOCUMENT_ROOT"]."/upload/" . $_FILES["file1"]["name"]);
  
echo "服务器收到如下数据：\r";
echo $value1."\r";
echo $value2."\r";
echo $_FILES["file1"]["name"]."\r";
?>