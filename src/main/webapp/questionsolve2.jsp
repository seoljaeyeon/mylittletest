<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quill Editor with Custom Blot for Shapes</title>
    <link href="https://cdn.quilljs.com/1.3.7/quill.snow.css" rel="stylesheet">
    <style>
        #editor-container {
            width: 600px;
            height: 400px;
            border: 1px solid #ccc;
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body>
    <div id="editor-container"></div>

    <button id="insert-circle-btn">Insert Circle</button>
    <button id="insert-square-btn">Insert Square</button>
    <button id="save-btn">Save Content</button>

    <script src="https://cdn.quilljs.com/1.3.7/quill.min.js"></script>
    <script>
        // Define a custom Shape blot
        var BlockEmbed = Quill.import('blots/block/embed');

        class CustomShapeBlot extends BlockEmbed {
            static create(value) {
                var node = super.create();
                node.setAttribute('data-shape-type', value.type);
                node.setAttribute('data-shape-color', value.color);
                node.setAttribute('data-shape-width', value.width);
                node.setAttribute('data-shape-height', value.height);
                return node;
            }

            static value(node) {
                return {
                    type: node.getAttribute('data-shape-type'),
                    color: node.getAttribute('data-shape-color'),
                    width: node.getAttribute('data-shape-width'),
                    height: node.getAttribute('data-shape-height')
                };
            }
        }

        CustomShapeBlot.blotName = 'customShape';
        CustomShapeBlot.tagName = 'div';
        Quill.register(CustomShapeBlot);

        document.addEventListener('DOMContentLoaded', function() {
            var editor = new Quill('#editor-container', {
                theme: 'snow',
                modules: {
                    toolbar: [
                        [{ 'header': [1, 2, 3, false] }],
                        ['bold', 'italic', 'underline'],
                        ['link', 'image', 'video'],
                        [{ 'list': 'ordered'}, { 'list': 'bullet' }],
                        ['clean']
                    ]
                }
            });

            function insertShape(shapeType, color, width, height) {
                var range = editor.getSelection();
                if (range) {
                    var value = {
                        type: shapeType,
                        color: color,
                        width: width,
                        height: height
                    };
                    editor.insertEmbed(range.index, 'customShape', value);
                }
            }

            document.getElementById('insert-circle-btn').onclick = function() {
                insertShape('circle', 'red', 50, 50);
            };

            document.getElementById('insert-square-btn').onclick = function() {
                insertShape('square', 'blue', 80, 60);
            };

            document.getElementById('save-btn').onclick = function() {
                var editorContent = document.querySelector('#editor-container .ql-editor').innerHTML;
                console.log(editorContent);
                // Save logic here
            };
        });
    </script>
</body>
</html>
