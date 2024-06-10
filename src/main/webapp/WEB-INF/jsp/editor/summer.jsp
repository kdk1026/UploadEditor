<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/lang/summernote-ko-KR.js"></script>
</head>
<body>

	<div>
		<div id="editor"></div>
	</div>

	<script>
		const content = '<h1>테스트</h1><br/><p>테스트1234</p>';
		document.querySelector('#editor').innerHTML = content;

		$('#editor').summernote({
			tabsize: 2,
			height: 500,
			lang: 'ko-KR',
			callbacks: {
				onImageUpload: function(files) {
					fnImageUpload( files[0], this );
				}
			}
		});

		function fnImageUpload(file, editor) {
			const formData = new FormData();
			formData.append('image', file);

			$.ajax({
				enctype: 'multipart/form-data',
				type: 'post',
				url: '/editor/image-upload',
				data: formData,
				contentType : false,
				processData : false,
				success: function(data) {
					$('#editor').summernote('insertImage', data.fileUrl);
				},
				error: function(xhr, status, err) {
					alert("code = "+ xhr.status + " message = " + xhr.responseText + " error = " + err);
				}
			});
		}
	</script>

</body>
</html>