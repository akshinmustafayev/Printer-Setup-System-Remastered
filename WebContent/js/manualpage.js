$(document).ready(function() {
	$('#adminmanualInput').summernote({
		 spellCheck: false,
		 disableGrammar: false,
		 toolbar: [
	            [ 'style', [ 'style' ] ],
	            [ 'font', [ 'bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'clear'] ],
	            [ 'fontname', [ 'fontname' ] ],
	            [ 'fontsize', [ 'fontsize' ] ],
	            [ 'color', [ 'color' ] ],
	            [ 'para', [ 'ol', 'ul', 'paragraph', 'height' ] ],
	            [ 'table', [ 'table' ] ],
	            [ 'insert', [ 'link'] ],
	            [ 'view', [ 'undo', 'redo', 'fullscreen', 'codeview' ] ]
	        ]
	});
});