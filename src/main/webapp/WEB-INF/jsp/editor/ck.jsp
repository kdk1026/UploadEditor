<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/translations/ko.js"></script>
<script src="/js/ckeditor/UploadAdapter.js"></script>

<style>
.ck-editor__editable { height: 500px; }
</style>
</head>
<body>

	<div>
		<div id="editor"></div>
	</div>

	<script>
		const content = '<h1>테스트</h1><br/><p>테스트1234</p>';
		document.querySelector('#editor').innerHTML = content;

		ClassicEditor
			.create( document.querySelector( '#editor' ), {
				language: 'ko',
				extraPlugins: [MyCustomUploadAdapterPlugin]
			})
			.catch( error => {
				console.error(error);
			});

		function MyCustomUploadAdapterPlugin(editor) {
		    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
		        return new UploadAdapter(loader)
		    }
		}
	</script>

</body>
</html>