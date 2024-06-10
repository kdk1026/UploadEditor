<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script src="https://uicdn.toast.com/editor/latest/i18n/ko-kr.js"></script>
</head>
<body>

	<div>
		<div id="editor"></div>
	</div>

	<script>
		const content = '<h1>테스트</h1><br/><p>테스트1234</p>';
		document.querySelector('#editor').innerHTML = content;

		const editor = new toastui.Editor({
			el: document.querySelector('#editor'),
			height: '500px',
			initialEditType: 'wysiwyg',
			previewStyle: 'vertical',
			language: 'ko-KR',
			hooks: {
				addImageBlobHook: function (blob, callback) {
					fnImageUpload(blob, function(data) {
						const fileUrl = data;
						callback(fileUrl);
					});
				}
			}
		});

		async function fnImageUpload(blob, callback) {
			const formData = new FormData();
			formData.append('image', blob);

			let fileUrl;

			await fetch('/editor/image-upload', {
				method: 'post',
				body: formData
			})
			.then((res) => {
				return res.json();
			})
			.then((data) => {
				fileUrl = data.fileUrl;
				callback(fileUrl);
			});
		}
	</script>

</body>
</html>