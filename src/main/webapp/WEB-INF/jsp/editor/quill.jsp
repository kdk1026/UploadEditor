<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/quill@2.0.2/dist/quill.snow.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/quill@2.0.2/dist/quill.js"></script>

<style>
.ql-editor {
	min-height: 500px !important;
}
</style>
</head>
<body>

	<div>
		<div id="editor"></div>
	</div>

	<script>
		const toolbarOptions = [
			['bold', 'italic', 'underline', 'strike'],
			['blockquote', 'code-block'],
			['link', 'image', 'video', 'formula'],

			[{ 'header': 1 }, { 'header': 2 }],
			[{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
			[{ 'script': 'sub'}, { 'script': 'super' }],
			[{ 'indent': '-1'}, { 'indent': '+1' }],
			[{ 'direction': 'rtl' }],

			[{ 'size': ['small', false, 'large', 'huge'] }],
			[{ 'header': [1, 2, 3, 4, 5, 6, false] }],

			[{ 'color': [] }, { 'background': [] }],
			[{ 'font': [] }],
			[{ 'align': [] }],

			['clean']
		];

		const quill = new Quill('#editor', {
			modules: {
				toolbar: toolbarOptions
			},
			theme: 'snow'
		});

		quill.getModule('toolbar').addHandler('image', function () {
			selectLocalImage();
		});

		function selectLocalImage() {
			const fileInput = document.createElement('input');
	        fileInput.setAttribute('type', 'file');
	        fileInput.accept = "image/*";

	        fileInput.click();

	        fileInput.addEventListener('change', function () {
				if ( fileInput.value !== '' ) {
					const formData = new FormData();
					formData.append('image', fileInput.files[0]);

					fetch('/editor/image-upload', {
						method: 'post',
						body: formData
					})
					.then((res) => {
						return res.json();
					})
					.then((data) => {
						const range = quill.getSelection();
						quill.insertEmbed(range.index, 'image', data.fileUrl);
					});
				}
	        });
		}
	</script>

</body>
</html>